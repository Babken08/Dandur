package com.example.armen.dandur.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;
import com.example.armen.dandur.R;
import com.example.armen.dandur.adapters.MyVideosAdapter;
import com.example.armen.dandur.adapters.RecyclerAdapterBottom;
import com.example.armen.dandur.adapters.RecyclerAdapterTop;
import com.example.armen.dandur.http.APIService;
import com.example.armen.dandur.http.APIUtil;
import com.example.armen.dandur.models.VideoItemModel;
import com.example.armen.dandur.util.DandurConstants;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements RecyclerAdapterBottom.RecyclerAdapterBottomDelegate,
        RecyclerAdapterTop.RecyclerAdapterTopDelegate, MyVideosAdapter.MyVideosAdapterDelegate{

    private static String TAG = HomeActivity.class.getName();
    private RelativeLayout mainLayout;
    private RecyclerView recyclerViewTop;
    private RecyclerView recyclerViewBottom;
    private AAH_CustomRecyclerView recyclerView;
    private APIService mApiService;
    private MyVideosAdapter adapter;
    private WebView webView;
    private RecyclerAdapterTop topAdapter;
    private RecyclerAdapterBottom bottomAdapter;

    private FrameLayout layoutProgress;

    private ArrayList<LinkedTreeMap<String, Object>> listData;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainLayout = findViewById(R.id.main_layout);
        if(DandurConstants.Companion.isDarkBackground()){
            mainLayout.setBackgroundResource(R.color.colorBlack);
        } else  {
            mainLayout.setBackgroundResource(R.color.colorWhite);
        }

        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        layoutProgress = findViewById(R.id.progress_layout);
        mApiService = APIUtil.Companion.getAPIService();
        listData  = new ArrayList<>();
        getDataofType(DandurConstants.Companion.getType_Random());

        createBottmTopRecycler();
        recyclerView = findViewById(R.id.recycler_middle);

        adapter = new MyVideosAdapter(this);
        adapter.setDelegate(this);
        final RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setActivity(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setPlayOnlyFirstVideo(true);
        recyclerView.setVisiblePercent(50);
        recyclerView.setCheckForMp4(false);

    }
    private void createBottmTopRecycler(){
        recyclerViewTop = findViewById(R.id.recycler_top);
        recyclerViewBottom = findViewById(R.id.recycler_bottom);

        topAdapter = new RecyclerAdapterTop(this);
        topAdapter.setDelegate(this);
        RecyclerView.LayoutManager topLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTop.setLayoutManager(topLayoutManager);
        recyclerViewTop.setAdapter(topAdapter);


        bottomAdapter = new RecyclerAdapterBottom(this);
        bottomAdapter.setDelegate(this);
        RecyclerView.LayoutManager bottomLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBottom.setLayoutManager(bottomLayoutManager);
        recyclerViewBottom.setAdapter(bottomAdapter);
    }

    private void getData(String categories) {
//        final String url  = "https://www.dandur.com/wp-json/wp/v2/posts?categories=" + categories + "&_embed";
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    byte result[]=HttpService.doGet(url);
//                    String data = new String(result);
//                    Log.i("ssssssssss", "result == " + data);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


        mApiService.getPostsInCategory(categories).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "response isSuccessful");
                    if(response.body() != null) {
                        ArrayList<VideoItemModel> modelsList = new ArrayList<>();
                        listData = (ArrayList<LinkedTreeMap<String, Object>>) response.body();
                        for (int i = 0; i < listData.size(); i++) {
                            VideoItemModel model = new VideoItemModel(listData.get(i));
                            modelsList.add(model);
                        }
//                        adapter.setList(listData);
                        adapter.setList(modelsList);
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
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.playAvailableVideos(0);

    }

    @Override
    public void bottomBarItemClick(int position) {
        topAdapter.setChangeItemPosition(-1);
        topAdapter.iconColorChange();
        progressVisiable(true);
        switch (position){
            case 0:{
                getData(DandurConstants.Companion.getCategories_Funny());
                break;
            }
            case 1:{
                getData(DandurConstants.Companion.getCategories_Anime());
                break;
            }
            case 2:{
                getData(DandurConstants.Companion.getCategories_Music());
                break;
            }
            case 3:{
                getData(DandurConstants.Companion.getCategories_Gaming());
                break;
            }
            case 4:{
                getData(DandurConstants.Companion.getCategories_Cartoons());
                break;
            }
            case 5:{
                getData(DandurConstants.Companion.getCategories_Cats());
                break;
            }
            case 6:{
                getData(DandurConstants.Companion.getCategories_Dogs());
                break;
            }
            case 7:{
                getData(DandurConstants.Companion.getCategories_Dance());
                break;
            }
            case 8:{
                getData(DandurConstants.Companion.getCategories_Extreme());
                break;
            }
            case 9:{
                getData(DandurConstants.Companion.getCategories_Nsfw());
                break;
            }
            case 10:{
                getData(DandurConstants.Companion.getCategories_Art_And_Design());
                break;
            }
            case 11:{
                getData(DandurConstants.Companion.getCategories_Nature());
                break;
            }
            case 12:{
                getData(DandurConstants.Companion.getCategories_News_And_Politics());
                break;
            }
            case 13:{
                getData(DandurConstants.Companion.getCategories_Fashion());
                break;
            }
            case 14:{
                getData(DandurConstants.Companion.getCategories_Cars());
                break;
            }
            case 15:{
                getData(DandurConstants.Companion.getCategories_Science());
                break;
            }
            case 16:{
                getData(DandurConstants.Companion.getCategories_Sport());
                break;
            }
            case 17:{
                getData(DandurConstants.Companion.getCategories_Movies());
                break;
            }
        }
    }

    private void getDataofType(String type){
        mApiService.getTypofResult(type).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "response isSuccessful");
                    if(response.body() != null) {
                        ArrayList<VideoItemModel> modelsList = new ArrayList<>();
                        listData = (ArrayList<LinkedTreeMap<String, Object>>) response.body();
                        for (int i = 0; i < listData.size(); i++) {
                            VideoItemModel model = new VideoItemModel(listData.get(i));
                            modelsList.add(model);
                        }
                        adapter.setList(modelsList);
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

    @Override
    public void topBarItemClick(int position) {

        if(position != 4){

            bottomAdapter.setChangeItemPosition(-1);
            bottomAdapter.setColorsItems(0, true);
        }


        switch (position) {
            case 0:{
                getDataofType(DandurConstants.Companion.getType_Random());
                progressVisiable(true);
                break ;
            }
            case 1:{
                getDataofType(DandurConstants.Companion.getType_Trending());
                progressVisiable(true);
                break;
            }
            case 2:{
                getDataofType(DandurConstants.Companion.getType_Hot());
                progressVisiable(true);
                break;
            }
            case 3:{
                getDataofType(DandurConstants.Companion.getType_Popular());
                progressVisiable(true);
                break;
            }
            case 4:{
//                Intent i  = new Intent(this, NavigationDrawerActivity.class);
//                startActivity(i);
//                drawerLayout.setVisibility(View.VISIBLE);
//                drawerLayout.openDrawer(GravityCompat.END);
                Context wrapper;
                if(DandurConstants.Companion.isDarkBackground()) {
                    wrapper =  new ContextThemeWrapper(HomeActivity.this, R.style.MyPopupBlack);
                } else  {
                   wrapper =  new ContextThemeWrapper(HomeActivity.this, R.style.MyPopupWhite);

                }

                PopupMenu popupMenu = new PopupMenu(wrapper, recyclerViewTop.getChildAt(4));
                popupMenu.getMenuInflater().inflate(R.menu.activity_navigation_drawer_drawer, popupMenu.getMenu());

                if(DandurConstants.Companion.isDarkBackground()) {
                    MenuItem menuItem = popupMenu.getMenu().findItem(R.id.dark_mode);
                    menuItem.setTitle("White Mode");
                } else {
                    MenuItem menuItem = popupMenu.getMenu().findItem(R.id.dark_mode);
                    menuItem.setTitle("Dark Mode");
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.rate_us:{

                                openWebPage("www.google.com");
//                                Intent intent = new Intent(HomeActivity.this, WebViewActivty.class);
//                                intent.putExtra("url", "www.google.com");
//                                startActivity(intent);
                                break;
                            }
                            case R.id.contact_us:{
//                                Intent intent = new Intent(HomeActivity.this, WebViewActivty.class);
//                                intent.putExtra("url", "www.google.com");
//                                startActivity(intent);
                                openWebPage("www.google.com");
                                break;
                            }
                            case R.id.dark_mode:{
                                boolean backgroundMode  = !DandurConstants.Companion.isDarkBackground();
                                SharedPreferences preferences = getSharedPreferences("DARK_BACKROUND", Context.MODE_PRIVATE);
                                preferences.edit().putBoolean("dark_background", backgroundMode).apply();
                                DandurConstants.Companion.setDarkBackground(backgroundMode);
                                if(DandurConstants.Companion.isDarkBackground()){
                                    mainLayout.setBackgroundResource(R.color.colorBlack);
                                } else  {
                                    mainLayout.setBackgroundResource(R.color.colorWhite);
                                }
                                topAdapter.changeMode();
                                recyclerViewTop.setAdapter(topAdapter);
                                bottomAdapter.changeMode();
                                recyclerViewBottom.setAdapter(bottomAdapter);
                                adapter.changeMode();
                                recyclerView.setAdapter(adapter);
                                break;
                            }
                        }
                        return false;
                    }
                });
                popupMenu.show();
                break;
            }
        }
    }

    @Override
    public void shareFunctional(@NonNull String videoLink) {
        Intent videoshare = new Intent(Intent.ACTION_SEND);
        videoshare.setType("text/plain");
        videoshare.putExtra(Intent.EXTRA_TEXT, videoLink);
        Intent chooser = Intent.createChooser(videoshare, "Dandur");

        if (videoshare.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    @Override
    public void likeFunctional(double id) {
        likeRequset(id);
    }

    private void likeRequset(double id){
        mApiService.likeRequest(id).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "response isSuccessful");

                }else {
                    Log.i(TAG, "response is not Successful");

                }
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure " + t.getMessage());
            }
        });
    }

    private void openWebPage(String url) {
        progressVisiable(true);
        if (!url.isEmpty()) {
            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                url = "http://" + url;
            }
            webView.loadUrl(url);
        }
    }

    private void configWebView(String url) {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }

        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return false;  if (choosenCountryInfoList.getVisibility() == View.VISIBLE) {
//                    return true;
//                } else {
//                    choosenCountryInfoList.setVisibility(View.VISIBLE);
//                    layoutAddCredit.setVisibility(View.GONE);
//                    return false;
//                }
                return false;
            }
        });

        progressVisiable(true);
        if (!url.isEmpty()) {
            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                url = "http://" + url;
            }
            webView.loadUrl(url);
        }
    }
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        }else {
            webView.setVisibility(View.GONE);
            progressVisiable(false);
            super.onBackPressed();
        }

    }
}
