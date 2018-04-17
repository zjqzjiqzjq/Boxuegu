package cn.edu.gdmec.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.boxuegu.Bean.ExercisesBean;

/**
 * Created by apple on 18/4/3.
 */

public class AnalysisUtils {
    //读取用户名
    public static String readLoginUserName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("loginUserName","");
        return userName;
    }

    //读取登录状态
    public static boolean readLoginStatus(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
        return isLogin;
    }

    //清除登录状态
    public static void cleanLoginStatus(Context context){
        SharedPreferences sp = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }
    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception{
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<ExercisesBean> exercisesInfos = null;
        ExercisesBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if("infos".equals(parser.getName())){
                        exercisesInfos = new ArrayList<ExercisesBean>();
                    }else if("exercises".equals(parser.getName())){
                        exercisesInfo= new ExercisesBean();
                        String ids = parser.getAttributeValue(0);
                        exercisesInfo.subjectId = Integer.parseInt(ids);

                    }else if("subject".equals(parser.getName())){
                        String subject = parser.nextText();
                        exercisesInfo.subject = subject;
                    }else if("a".equals(parser.getName())){
                        String a = parser.nextText();
                        exercisesInfo.a = a;
                    }else if("b".equals(parser.getName())){
                        String b = parser.nextText();
                        exercisesInfo.b = b;
                    }else if("c".equals(parser.getName())){
                        String c = parser.nextText();
                        exercisesInfo.c = c;
                    }else if("d".equals(parser.getName())){
                        String d = parser.nextText();
                        exercisesInfo.d = d;
                    }else if("answer".equals(parser.getName())){
                        String answer = parser.nextText();
                        exercisesInfo.answer = Integer.parseInt(answer);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("exercises".equals(parser.getName())){
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return exercisesInfos;

    }
    public static void setABCDEnable(boolean value, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d){
        iv_a.setEnabled(value);
        iv_b.setEnabled(value);
        iv_c.setEnabled(value);
        iv_d.setEnabled(value);


    }

}
