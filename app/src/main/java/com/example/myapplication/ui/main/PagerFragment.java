package com.example.myapplication.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.Services.OrganizationsService;
import com.example.myapplication.Services.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class PagerFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    ArrayList<String> user_string_array = new ArrayList<String>();
    ArrayList<String> organizations_string_array = new ArrayList<String>();

    private int mPage;

    public static PagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        ListView ListView = view.findViewById(R.id.ListView);
        if (mPage == 1) {

            UserService userService = new UserService(getActivity());
            userService.getJSONArryOfUsers(new UserService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    ArrayAdapter<String> arrayAdapter =
                            new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Collections.singletonList("Something wrong"));
                    ListView.setAdapter(arrayAdapter);
                }

                @Override
                public void onResponse(JSONObject user_object) {
                }

                @Override
                public void onResponse(JSONArray user_list) {
                    if (user_list != null) {
                        for (int i = 0; i < user_list.length(); i++) {
                            try {
                                user_string_array.add("        " + user_list.getJSONObject(i).getString("user_email").split("@")[0] +
                                        "        score: " + user_list.getJSONObject(i).getString("user_points"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ArrayAdapter<String> arrayAdapter =
                            new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, user_string_array);
                    ListView.setAdapter(arrayAdapter);
                }

                @Override
                public void onResponse(int points) {

                }
            });
        } else {
            OrganizationsService organizationsService = new OrganizationsService(getActivity());
            organizationsService.getJSONArryOfOrganizations(new OrganizationsService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    ArrayAdapter<String> arrayAdapter =
                            new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Collections.singletonList("Something wrong"));
                    ListView.setAdapter(arrayAdapter);
                }

                @Override
                public void onResponse(JSONArray organizations_list) {
                    if (organizations_list != null) {
                        for (int i = 0; i < organizations_list.length(); i++) {
                            try {
                                organizations_string_array.add("        " + organizations_list.getJSONObject(i).getString("organisation_email").split("@")[0] +
                                        "        score: " + organizations_list.getJSONObject(i).getString("organisation_points"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ArrayAdapter<String> arrayAdapter =
                            new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, user_string_array);
                    ListView.setAdapter(arrayAdapter);
                }
            });
        }
        return view;
    }
}
