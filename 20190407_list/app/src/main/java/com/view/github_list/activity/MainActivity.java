package com.view.github_list.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.view.github_list.R;
import com.view.github_list.adapter.RecyclerViewAdapter;
import com.view.github_list.base.UserInfo;
import com.view.github_list.net.APIurl;
import com.view.github_list.net.HttpVolley;
import com.view.unit.CustomItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private HttpVolley mhttpVolley;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private JSONArray array = null;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mhttpVolley = new HttpVolley(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager=new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        getlist();
    }

    private void getlist(){
        mhttpVolley.get2(APIurl.GET_USER+"since=135", new HttpVolley.VolleyCallback() {
            @Override
            public void onSuccessResponse(final String result) {



                recyclerView.setAdapter(mAdapter = new RecyclerViewAdapter(MainActivity.this,result, new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                        try {
                            array = new JSONArray(result);
                            userInfo = new Gson().fromJson(String.valueOf(array.get(position)), UserInfo.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                        intent.putExtra("name",userInfo.getLogin());
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailResponse(String result) {
            }
        });
    }


}
