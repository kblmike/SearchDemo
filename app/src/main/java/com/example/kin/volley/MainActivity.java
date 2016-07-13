package com.example.kin.volley;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kin.volley.model.Listing;
import com.example.kin.volley.model.Results;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //public final String durl="http://www.joincherry.com/api/getrandomlistings/";
    public final String durl="http://www.joincherry.com/api/searchlistings/?";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String input="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerListView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(this);

        final List<Listing> mvlist;



        mvlist=new ArrayList<Listing>();


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        Intent intent = getIntent();
        String keyword=intent.getStringExtra("keyword");


        try {
            input="keyword="+ URLEncoder.encode(keyword,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        loadData(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Listing[] listingArray=new Gson().fromJson(result,Listing[].class);
                List<Listing> mlist=new ArrayList<Listing>(Arrays.asList(listingArray));
                mvlist.addAll(mlist);
                CustomAdapter adapter=new CustomAdapter(mvlist,MainActivity.this);
                mRecyclerView.setAdapter(adapter);


            }
        },input);



        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager){
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadData(new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Listing[] listingArray=new Gson().fromJson(result,Listing[].class);
                        List<Listing> mlist=new ArrayList<Listing>(Arrays.asList(listingArray));
                        mvlist.addAll(mlist);
                        CustomAdapter adapter=new CustomAdapter(mvlist,MainActivity.this);
                        //mRecyclerView.setAdapter(adapter);
                        //adapter.notifyDataSetChanged();
                        //adapter.notifyItemInserted(adapter.getItemCount());

                    }
                },input);
            }
        });


    }

    public interface VolleyCallback{
        void onSuccess(String result);
    }



    private void loadData(final VolleyCallback callback,String input){
        // Retrieve the RequestQueue.
        RequestQueue queue=ConnectionManager.getInstance(this);

        StringRequest strReq=new StringRequest(Request.Method.GET, durl+input, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                //Listing[] listingArray=new Gson().fromJson(response,Listing[].class);

                //List<Listing> mlist=new ArrayList<Listing>(Arrays.asList(listingArray));

                callback.onSuccess(response);

                //CustomAdapter adapter=new CustomAdapter(mlist,MainActivity.this);

                //mRecyclerView.setAdapter(adapter);
                // Listing listing=new Gson().fromJson(response,Listing.class);


                //Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(strReq);

    }
}
