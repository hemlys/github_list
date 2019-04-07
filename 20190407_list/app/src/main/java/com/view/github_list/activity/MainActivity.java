package com.view.github_list.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.view.github_list.R;
import com.view.github_list.adapter.RecyclerViewAdapter;
import com.view.github_list.net.APIurl;
import com.view.github_list.net.HttpVolley;
import com.view.unit.CustomItemClickListener;

public class MainActivity extends AppCompatActivity {
    private HttpVolley mhttpVolley;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private GridLayoutManager mLayoutManager;


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
            public void onSuccessResponse(String result) {



                recyclerView.setAdapter(mAdapter = new RecyclerViewAdapter(MainActivity.this,result, new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Log.e("hemly", "position = " + position);

                    }
                }));
            }

            @Override
            public void onFailResponse(String result) {
            }
        });
    }


}
