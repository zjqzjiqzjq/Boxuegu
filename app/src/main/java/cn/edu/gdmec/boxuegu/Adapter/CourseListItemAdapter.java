package cn.edu.gdmec.boxuegu.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdmec.boxuegu.Bean.CourseBean;
import cn.edu.gdmec.boxuegu.R;

public class CourseListItemAdapter extends RecyclerView.Adapter<CourseListItemAdapter.ViewHolder> {

    private List<CourseBean> objects = new ArrayList<CourseBean>();
    private Context context;
    private LayoutInflater layoutInflater;

    public CourseListItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_list_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        initializeViews(objects.get(position),holder);
    }

    public void setData(List<CourseBean> objects){
        this.objects = objects;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    private void initializeViews(CourseBean object, ViewHolder holder) {
        //TODO implement
        if (object != null){
            holder.tvContentImgTitle.setText(object.imgTitle);
            holder.tvContentTitle.setText(object.title);
            switch (object.id){
                case 1:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_1_icon);
                    break;
                case 2:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_2_icon);
                    break;
                case 3:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_3_icon);
                    break;
                case 4:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_4_icon);
                    break;
                case 5:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_5_icon);
                    break;
                case 6:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_6_icon);
                    break;
                case 7:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_7_icon);
                    break;
                case 8:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_8_icon);
                    break;
                case 9:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_9_icon);
                    break;
                case 10:
                    holder.ivCourseImg.setImageResource(R.drawable.chapter_10_icon);
                    break;
            }
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCourseImg;
        private TextView tvContentImgTitle;
        private TextView tvContentTitle;

        public ViewHolder(View view) {
            super(view);
            ivCourseImg = (ImageView) view.findViewById(R.id.iv_course_img);
            tvContentImgTitle = (TextView) view.findViewById(R.id.tv_content_img_title);
            tvContentTitle = (TextView) view.findViewById(R.id.tv_content_title);
        }
    }
}
