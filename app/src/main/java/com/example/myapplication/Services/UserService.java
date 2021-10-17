package com.example.myapplication.Services;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    public static final String QUERY_FOR_USER_SERVICE = "http://192.168.215.178:8000/heap/";
    Context context;
    JSONArray UserJSONList = new JSONArray();

    public UserService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(JSONObject user_object);
        void onResponse(JSONArray user_list);
    }

    public void Login(VolleyResponseListener volleyResponseListener, JSONObject user_object) {
        String url = QUERY_FOR_USER_SERVICE;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url + "login", user_object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("It WORKED", response.toString());
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        MySingleton.getInstance(context).addToRequestQueue(jsonObjReq);
    }
    public void Registration(VolleyResponseListener volleyResponseListener, JSONObject user_object) {
        String url = QUERY_FOR_USER_SERVICE;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url + "registration", user_object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("It WORKED", response.toString());
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        MySingleton.getInstance(context).addToRequestQueue(jsonObjReq);
    }
    public void getJSONArryOfUsers(VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_USER_SERVICE;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "allusers", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                                UserJSONList.put(jArray.getJSONObject(i));
                        }
                    }
                    volleyResponseListener.onResponse(UserJSONList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    volleyResponseListener.onError("Something wrong");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Error: Response", "" + error.getMessage());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
