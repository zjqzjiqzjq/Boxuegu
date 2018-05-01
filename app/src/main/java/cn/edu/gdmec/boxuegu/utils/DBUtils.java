package cn.edu.gdmec.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.boxuegu.Bean.UserBean;
import cn.edu.gdmec.boxuegu.Bean.VideoBean;
import cn.edu.gdmec.boxuegu.Sqlite.SQLiteHelper;

/**
 * Created by apple on 18/4/10.
 */

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db ;

    public DBUtils(Context context){
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context){
        if(instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }

    public void saveUserInfo(UserBean bean){
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName",bean.userName);
        contentValues.put("nickname",bean.nickName);
        contentValues.put("sex",bean.sex);
        contentValues.put("signature",bean.signature);
        contentValues.put("qq",bean.qq);      ////
        db.insert(SQLiteHelper.U_USERINFO,null,contentValues);
    }

    public  UserBean getUserInfo(String userName){
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql,new String[]{userName});
        UserBean userBean = null;
        while (cursor.moveToNext()){
            userBean = new UserBean();
            userBean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            userBean.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            userBean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            userBean.signature = cursor.getString(cursor.getColumnIndex("signature"));
            userBean.qq = cursor.getString(cursor.getColumnIndex("qq"));  ////

        }
        cursor.close();
        return userBean;
    }
    public  void updateUserInfo(String key, String value, String userName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, value);
        db.update(SQLiteHelper.U_USERINFO,contentValues, "userName=?",new String[]{userName});
    }
    public void saveVideoPlayList(VideoBean videoBean,String userName){
        if (hasVideoPlay(videoBean.chapterId,videoBean.videoId,userName)){
            boolean isDelete = delVideoPlay(videoBean.chapterId,videoBean.videoId,userName);
            if (!isDelete){
                return;
            }
        }
        ContentValues cv = new ContentValues();
        cv.put("userName",userName);
        cv.put("chapterId",videoBean.chapterId);
        cv.put("videoId",videoBean.videoId);
        cv.put("videoPath",videoBean.videoPath);
        cv.put("title",videoBean.title);
        cv.put("secondTitle",videoBean.secondTitle);
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LISI,null,cv);
    }
    private boolean delVideoPlay(int chapterId,int videoId,String userName){
        boolean delSuccess = false;
        int row = db.delete(SQLiteHelper.U_VIDEO_PLAY_LISI," chapterId=? AND videoId=? AND userName=?",new String[]{chapterId + "",videoId + "",userName});
        if (row > 0){
            delSuccess =true;
        }
        return  delSuccess;
    }
    private boolean hasVideoPlay(int chapterId,int videoId, String userName){
        boolean hasVideo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LISI + " WHERE chapterId=? AND videoId=? AND userName=?";
        Cursor cursor = db.rawQuery(sql,new String[]{ chapterId + "",videoId + "",userName});
        if (cursor.moveToNext()){
            hasVideo = true;
        }
        cursor.close();
        return hasVideo;
    }
    public List<VideoBean> getVideoHistory(String s){
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LISI + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql,new String[]{s});
        List<VideoBean> vbl = new ArrayList<>();
        VideoBean bean = null;
        while (cursor.moveToNext()){
            bean = new VideoBean();
            bean.chapterId = cursor.getInt(cursor.getColumnIndex("chapterId"));
            bean.videoId = cursor.getInt(cursor.getColumnIndex("videoId"));
            bean.videoPath = cursor.getString(cursor.getColumnIndex("videoPath"));
            bean.title = cursor.getString(cursor.getColumnIndex("title"));
            bean.secondTitle = cursor.getString(cursor.getColumnIndex("secondTitle"));
            vbl.add(bean);
            bean = null;
        }
        cursor.close();
        return  vbl;
    }

}
