package com.example.companiesapplication;

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

public class OrganizationService {
    public static final String QUERY_FOR_USER_SERVICE = "http://192.168.215.178:8000/heap/";
    Context context;
    JSONObject AddPoint = new JSONObject();


    public OrganizationService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(JSONObject organization_object);
    }

    public void AddPoints(VolleyResponseListener volleyResponseListener, String username) {
        String url = QUERY_FOR_USER_SERVICE;
        try {
            AddPoint.put("user_email", username);
            AddPoint.put("user_points", 50);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url + "points/add", AddPoint,
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
}
