package com.view.github_list.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.view.github_list.R;
import com.view.github_list.base.UserInfo;
import com.view.unit.CustomItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private String myData;
    private CustomItemClickListener listener;

    private UserInfo userInfo;
    private JSONArray array = null;

    public RecyclerViewAdapter(Context context, String datas, CustomItemClickListener listener) {
        this.mContext = context;
        this.myData = datas;
        this.listener = listener;
        try {
            array = new JSONArray(datas);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.activity_cardview, parent,
                false);
        final  ViewHolder holder = new ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        //顯示資料的地方

        try {
            array = new JSONArray(myData);
            this.userInfo = new Gson().fromJson(String.valueOf(array.get(position)), UserInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.textId.setText(userInfo.getId()+"");
        holder.textName.setText(userInfo.getLogin());




        Glide.with(mContext)
                .load(userInfo.getAvatar_url())
                .placeholder(R.mipmap.ic_launcher)//loading時候的Drawable
                .centerCrop()
                .fitCenter()
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return  array.length();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView textId, textName;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.item_live_cover);
            textId = (TextView) itemView.findViewById(R.id.item_live_title);
            textName = (TextView) itemView.findViewById(R.id.item_live_user);
            cardView = (CardView) itemView.findViewById(R.id.item_live_layout);
        }
    }
}
