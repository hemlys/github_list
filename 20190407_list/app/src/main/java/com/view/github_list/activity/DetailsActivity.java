package com.view.github_list.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.view.github_list.R;
import com.view.github_list.adapter.RecyclerViewAdapter;
import com.view.github_list.base.UserInfo;
import com.view.github_list.base.Userdetails;
import com.view.github_list.net.APIurl;
import com.view.github_list.net.HttpVolley;
import com.view.unit.CustomItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

public class DetailsActivity extends AppCompatActivity {

    private HttpVolley mhttpVolley;
    private String names ;
    private ImageView img;
    private TextView login,id,node_id,gravatar_id,url,html_url,followers_url,following_url,gists_url,starred_url;
    private TextView subscriptions_url,organizations_url,repos_url,events_url,received_events_url,type,site_admin;
    private TextView name,company,blog,location,email,hireable,bio,public_repos,public_gists,followers,following,created_at,updated_at;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mhttpVolley = new HttpVolley(this);
        init();
        Intent intent = getIntent();
        names = intent.getStringExtra("name");
        getlist();
    }
    private void init(){
        img = (ImageView)findViewById(R.id.imageView);
        login= (TextView)findViewById(R.id.textView_login);
        id = (TextView)findViewById(R.id.textView_id);
        node_id = (TextView)findViewById(R.id.textView_node_id);
        gravatar_id = (TextView)findViewById(R.id.textView_gravatar_id);
        url = (TextView)findViewById(R.id.textView_url);
        html_url = (TextView)findViewById(R.id.textView_html_url);
        followers_url = (TextView)findViewById(R.id.textView_followers_url);
        following_url = (TextView)findViewById(R.id.textView_following_url);
        gists_url = (TextView)findViewById(R.id.textView_gists_url);
        starred_url = (TextView)findViewById(R.id.textView_starred_url);
        subscriptions_url = (TextView)findViewById(R.id.textView_subscriptions_url);

        organizations_url= (TextView)findViewById(R.id.textView_organizations_url);
        repos_url= (TextView)findViewById(R.id.textView_repos_url);
        events_url= (TextView)findViewById(R.id.textView_events_url);
        received_events_url= (TextView)findViewById(R.id.textView_received_events_url);
        type= (TextView)findViewById(R.id.textView_type);
        site_admin= (TextView)findViewById(R.id.textView_site_admin);
        name= (TextView)findViewById(R.id.textView_name);
        company= (TextView)findViewById(R.id.textView_company);
        blog= (TextView)findViewById(R.id.textView_blog);
        location= (TextView)findViewById(R.id.textView_location);
        email= (TextView)findViewById(R.id.textView_email);
        hireable= (TextView)findViewById(R.id.textView_hireable);
        bio= (TextView)findViewById(R.id.textView_bio);
        public_repos= (TextView)findViewById(R.id.textView_public_repos);
        public_gists= (TextView)findViewById(R.id.textView_public_gists);

        followers= (TextView)findViewById(R.id.textView_followers);
        following= (TextView)findViewById(R.id.textView_following);
        created_at= (TextView)findViewById(R.id.textView_created_at);
        updated_at= (TextView)findViewById(R.id.textView_updated_at);
    }

    private void getlist(){
        mhttpVolley.get2(APIurl.GET_USER_details+names, new HttpVolley.VolleyCallback() {
            @Override
            public void onSuccessResponse(final String result) {

                Userdetails userInfo = new Gson().fromJson(result, Userdetails.class);
                Glide.with(DetailsActivity.this)
                        .load(userInfo.getAvatar_url())
                        .placeholder(R.mipmap.ic_launcher)//loading時候的Drawable
                        .centerCrop()
                        .fitCenter()
                        .into(img);
                login.setText("login: "+userInfo.getLogin());
                id.setText("id: "+userInfo.getId());
                node_id.setText("node_id: "+userInfo.getNode_id());
                gravatar_id.setText("gravatar_id: "+userInfo.getGravatar_id());
                url.setText("url: "+userInfo.getUrl());
                html_url.setText("html_url: "+userInfo.getHtml_url());
                followers_url.setText("followers_url: "+userInfo.getFollowers_url());
                following_url.setText("following_url: "+userInfo.getFollowing_url());
                gists_url.setText("gists_url: "+userInfo.getGists_url());
                starred_url.setText("starred_url: "+userInfo.getStarred_url());
                subscriptions_url.setText("subscriptions_url: "+userInfo.getSubscriptions_url());

                organizations_url.setText("organizations_url: "+userInfo.getOrganizations_url());
                repos_url.setText("repos_url: "+userInfo.getRepos_url());
                events_url.setText("events_url: "+userInfo.getEvents_url());
                received_events_url.setText("received_events_url: "+userInfo.getReceived_events_url());
                type.setText("type: "+userInfo.getType());
                site_admin.setText("site_admin: "+userInfo.isSite_admin());
                name.setText("name: "+userInfo.getName());
                company.setText("company: "+userInfo.getCompany());
                blog.setText("blog: "+userInfo.getBlog());
                location.setText("location: "+userInfo.getLocation());
                email.setText("email: "+userInfo.getEmail());
                hireable.setText("hireable: "+userInfo.getHireable());
                bio.setText("bio: "+userInfo.getBio());
                public_repos.setText("public_repos: "+userInfo.getPublic_repos());
                public_gists.setText("public_gists: "+userInfo.getPublic_gists());

                followers.setText("followers: "+userInfo.getFollowers());
                following.setText("following: "+userInfo.getFollowing());
                created_at.setText("created_at: "+userInfo.getCreated_at());
                updated_at.setText("updated_at: "+userInfo.getUpdated_at());

            }

            @Override
            public void onFailResponse(String result) {
            }
        });
    }

}
