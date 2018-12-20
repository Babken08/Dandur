package com.example.armen.dandur.activity;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;
import com.example.armen.dandur.R;
import com.example.armen.dandur.adapters.MyVideosAdapter;
import com.example.armen.dandur.adapters.RecyclerAdapterBottom;
import com.example.armen.dandur.adapters.RecyclerAdapterTop;
import com.example.armen.dandur.adapters.ResyclerAdapter;
import com.example.armen.dandur.http.APIService;
import com.example.armen.dandur.http.APIUtil;
import com.example.armen.dandur.util.DandurConstants;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static String TAG = HomeActivity.class.getName();
    private RecyclerView recyclerViewTop;
    private RecyclerView recyclerViewBottom;
//    private RecyclerView recyclerView;
    private AAH_CustomRecyclerView recyclerView;
    private APIService mApiService;
//    private ResyclerAdapter adapter;
    private MyVideosAdapter adapterTest;

    private FrameLayout layoutProgress;

    private ArrayList<LinkedTreeMap<String, Object>> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layoutProgress = findViewById(R.id.progress_layout);
        mApiService = APIUtil.Companion.getAPIService();
        listData  = new ArrayList<>();
        getData();

        createBottmTopRecycler();
        recyclerView = findViewById(R.id.recycler_middle);

        adapterTest = new MyVideosAdapter();
        final RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setActivity(this);
        recyclerView.setAdapter(adapterTest);
        recyclerView.setPlayOnlyFirstVideo(true);
        recyclerView.setVisiblePercent(50);

    }
    private void createBottmTopRecycler(){
        recyclerViewTop = findViewById(R.id.recycler_top);
        recyclerViewBottom = findViewById(R.id.recycler_bottom);

        RecyclerAdapterTop topAdapter = new RecyclerAdapterTop(this);
        RecyclerView.LayoutManager topLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTop.setLayoutManager(topLayoutManager);
        recyclerViewTop.setAdapter(topAdapter);

        RecyclerAdapterBottom bottomAdapter = new RecyclerAdapterBottom(this);
        RecyclerView.LayoutManager bottomLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewBottom.setLayoutManager(bottomLayoutManager);
        recyclerViewBottom.setAdapter(bottomAdapter);
    }

    private void getData() {
        mApiService.getPostsInCategory(DandurConstants.Companion.getCategories_Animal_pets()).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "response isSuccessful");
                    if(response.body() != null) {
                        listData = (ArrayList<LinkedTreeMap<String, Object>>) response.body();
                        adapterTest.setList(listData);
                        recyclerView.smoothScrollBy(0,1);
                        recyclerView.smoothScrollBy(0,-1);
                    }
                    progressVisiable(false);
                }else {
                    Log.i(TAG, "response is not Successful");
                    progressVisiable(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure " + t.getMessage());
                progressVisiable(false);
            }
        });
    }

    private void progressVisiable(boolean visiable){
        if(visiable){
            layoutProgress.setVisibility(View.VISIBLE);
        }else {
            layoutProgress.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        recyclerView.clearFocus();
        adapterTest.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.playAvailableVideos(0);
    }
}
