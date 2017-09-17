package com.example.uddishverma.hackthenorth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HotelsListActivity extends AppCompatActivity {

    public static final String TAG = "HotelsListActivity";

    String url = "http://138.197.165.209:5000/suggestion/";

    RequestQueue requestQueue;

    public RecyclerView recyclerView;
    public HotelsAdapter mAdapter;
    JSONArray jsonArray;
    ArrayList<HotelsModel> hotelsList = new ArrayList<>();
    HotelsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        Intent i = getIntent();
        url = url + i.getStringExtra("review") + "/" + i.getStringExtra("crime") + "/" + i.getStringExtra("price");
        Log.d(TAG, "onCreate: URL " + url);
        fetchData(requestQueue);
    }

    private void fetchData(RequestQueue requestQueue) {

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);
                        try {
                            jsonArray = response.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                model = new HotelsModel();
                                model.name = jsonArray.getString(i);
                                hotelsList.add(model);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        mAdapter = new HotelsAdapter(hotelsList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HotelsListActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(mAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
