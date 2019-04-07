package com.view.github_list.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.view.github_list.List_APP;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;





public class HttpVolley {
    private String TAG = "HttpVolley";
    private static Context appContext;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    public HttpVolley(Context appContext) {
        this.appContext = appContext;
    }

    public interface VolleyCallback {
        void onSuccessResponse(String result);

        void onFailResponse(String result);
    }

    public void post(String url, JSONObject json, final VolleyCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(appContext);
        //  JSONObject jsonObject = new JSONObject(json);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //      Log.e(TAG,"response=  "+response);
                        callback.onSuccessResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                String statusCode ;
                try{
                    //   statusCode = String.valueOf(error.networkResponse.statusCode);
                    if(error.networkResponse.data!=null) {
                        try {
                            body = new String(error.networkResponse.data,"UTF-8");
                            callback.onFailResponse(body);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    JSONObject json = new JSONObject();
                    try {
                        json.put("errorCode",-999);
                        json.put("message","無法預期的錯誤");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    callback.onFailResponse(json.toString());
                }

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                //  headers.put("x-access-token", LadyliveApp.getInstance().getToken2());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }

    public String get(final String url, final VolleyCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(appContext);
        Map<String, String> json = new HashMap<String, String>();
        json.put("mac", "athentek_dev");
        JSONObject jsonObject = new JSONObject(json);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //   Log.e(TAG, "response=  " + response);
                        callback.onSuccessResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                String statusCode ;
                try{
                    //  statusCode = String.valueOf(error.networkResponse.statusCode);
                    if(error.networkResponse.data!=null) {
                        try {
                            body = new String(error.networkResponse.data,"UTF-8");
                            callback.onFailResponse(body);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    Log.e("hemly","error= "+e);
                    JSONObject json = new JSONObject();
                    try {
                        json.put("Url",url);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    callback.onFailResponse(json.toString());
           //         callback.onFailResponse("");
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
        return null;
    }

    public void get2(final String url, final VolleyCallback callback)  {


        mRequestQueue = Volley.newRequestQueue(appContext);
        // 2 创建StringRequest对象
        mStringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccessResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailResponse(error.toString());
            }
        });
        // 3 将StringRequest添加到RequestQueue
        mRequestQueue.add(mStringRequest);
    }


    public void put(String url, JSONObject json, final VolleyCallback callback){
        RequestQueue requestQueue = Volley.newRequestQueue(appContext);
        //  JSONObject jsonObject = new JSONObject(json);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.PUT,url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //      Log.e(TAG,"response=  "+response);
                        callback.onSuccessResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                String statusCode ;
                try{
                    // statusCode = String.valueOf(error.networkResponse.statusCode);
                    if(error.networkResponse.data!=null) {
                        try {
                            body = new String(error.networkResponse.data,"UTF-8");
                            callback.onFailResponse(body);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    JSONObject json = new JSONObject();
                    try {
                        json.put("errorCode",-999);
                        json.put("message","無法預期的錯誤");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    callback.onFailResponse(json.toString());
                }
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                //   headers.put("x-access-token", LadyliveApp.getInstance().getToken2());
                //    Log.e("hemly","token= "+LadyliveApp.getInstance().getToken2());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }
    public void delete(String url, JSONObject json, final VolleyCallback callback){
        RequestQueue requestQueue = Volley.newRequestQueue(appContext);
        //  JSONObject jsonObject = new JSONObject(json);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.DELETE,url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //      Log.e(TAG,"response=  "+response);
                        callback.onSuccessResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                String statusCode ;
                try{
                    // statusCode = String.valueOf(error.networkResponse.statusCode);
                    if(error.networkResponse.data!=null) {
                        try {
                            body = new String(error.networkResponse.data,"UTF-8");
                            callback.onFailResponse(body);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    JSONObject json = new JSONObject();
                    try {
                        json.put("errorCode",-999);
                        json.put("message","無法預期的錯誤");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    callback.onFailResponse(json.toString());
                }
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                //      headers.put("x-access-token", LadyliveApp.getInstance().getToken2());
                //   Log.e("hemly","token= "+LadyliveApp.getInstance().getToken2());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }

//    public void postImage(String url, Map<String, Integer> param, File file, final VolleyCallback callback){
//        Map<String, String> header = new HashMap<>();
//        header.put("Accept", "application/json");
//        header.put("x-access-token", LadyliveApp.getInstance().getToken2());
//        RequestQueue requestQueue = Volley.newRequestQueue(appContext);
//        MultipartRequest request = new MultipartRequest(url, header, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                String body;
//                String statusCode ;
//                try{
//                    //   statusCode = String.valueOf(error.networkResponse.statusCode);
//                    if(error.networkResponse.data!=null) {
//                        try {
//                            body = new String(error.networkResponse.data,"UTF-8");
//                            callback.onFailResponse(body);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }catch (Exception e){
//                    JSONObject json = new JSONObject();
//                    try {
//                        json.put("errorCode",-999);
//                        json.put("message","無法預期的錯誤");
//                    } catch (JSONException e1) {
//                        e1.printStackTrace();
//                    }
//                    callback.onFailResponse(json.toString());
//                }
//            }
//        }, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                callback.onSuccessResponse(response);
//            }
//        }, file, param);
//        requestQueue.add(request);
//    }
}
