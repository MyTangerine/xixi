package com.example.tangerine.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tangerine on 16/9/3.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<String> mDatas = new ArrayList<String>();

    private SlidingButtonView mMenu = null;

    public Adapter(Context context) {

        mContext = context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;

        for (int i = 0; i < 10; i++) {
            mDatas.add(i+"");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.layout_content.getLayoutParams().width = Utils.getScreenWidth(mContext);

        holder.profileText.setText("傻逼朱韬的逗比日常");
        holder.titleText.setText("SBer");
        holder.authorText.setText("朱坑坑");

//
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_share;
        public TextView authorText;
        public TextView profileText;
        public TextView titleText;
        public ImageView headImage;
        public ViewGroup layout_content;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_share = (TextView) itemView.findViewById(R.id.tv_share);
            headImage = (ImageView) itemView.findViewById(R.id.head_img);
            authorText = (TextView) itemView.findViewById(R.id.projectcard_author_text);
            titleText = (TextView) itemView.findViewById(R.id.projectcard_title_text);
            profileText = (TextView) itemView.findViewById(R.id.projectcard_profile_text);

            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);

            ((SlidingButtonView) itemView).setSlidingButtonListener(Adapter.this);
        }
    }


    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }


    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if(menuIsOpen()){
            if(mMenu != slidingButtonView){
                closeMenu();
            }
        }
    }


    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }

    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        return false;
    }



    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view,int position);
    }
}
