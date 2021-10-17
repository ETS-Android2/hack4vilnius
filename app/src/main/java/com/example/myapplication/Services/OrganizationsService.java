package com.example.myapplication.Services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrganizationsService {
    public static final String QUERY_FOR_USER_SERVICE = "http://192.168.215.178:8000/heap/";
    Context context;
    JSONArray OrganizationsJSONList = new JSONArray();

    public OrganizationsService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(JSONArray organizations_list);
    }

    public void getJSONArryOfOrganizations(OrganizationsService.VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_USER_SERVICE;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "organizations", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i = 0; i < jArray.length(); i++) {
                            OrganizationsJSONList.put(jArray.getJSONObject(i));
                        }
                    }
                    volleyResponseListener.onResponse(OrganizationsJSONList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    volleyResponseListener.onError("Something wrong");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: Response", "" + error.getMessage());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
