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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocationService {
    public static final String QUERY_FOR_LOCATION = "http://78.60.209.53:8000/heap/";


    Context context;
    ArrayList<String> Location_address_array = new ArrayList<String>();
    JSONObject Location_Object = new JSONObject();
    JSONArray LocationJSONList = new JSONArray();
    public LocationService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(ArrayList<String> Location_address_array);
        void onResponse(JSONObject Location_object);
        void onResponse(JSONArray LocationJSONList);
    }

    public void getJSONArryOfLocations(VolleyResponseListener volleyResponseListener, String status){
        String url =QUERY_FOR_LOCATION;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "locations", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                                LocationJSONList.put(jArray.getJSONObject(i));
                        }
                    }
                    volleyResponseListener.onResponse(LocationJSONList);
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
