package com.example.kin.volley;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.kin.volley.model.Keyword;
import com.example.kin.volley.model.Listing;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Kin on 6/19/2016.
 */
public class SearchActivity extends AppCompatActivity {
    AutoCompleteTextView acTextView;
    Button msearch;
    ArrayList<String> suggestions;
    ArrayAdapter<String> adapter;
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        suggestions=new ArrayList<>();

        acTextView = (AutoCompleteTextView) findViewById(R.id.search);
        msearch=(Button) findViewById(R.id.btnSearch);
        acTextView.setThreshold(2);



        acTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length()<=3){
                    suggestions=new ArrayList<>();
                    updateList(s.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        msearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),MainActivity.class);
                intent.putExtra("keyword",acTextView.getText().toString());
                view.getContext().startActivity(intent);
            }
        });


    }

    public void updateList(final String place){
        String input="";
        try {
            input="keyword="+ URLEncoder.encode(place,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Retrieve the RequestQueue.
        RequestQueue queue=ConnectionManager.getInstance(getApplicationContext());

        url="http://www.joincherry.com/api/SearchKeyword/?"+ input;

        StringRequest j=new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
            try
            {
                Keyword[] keywordArray=new Gson().fromJson(response,Keyword[].class);
                for(int i=0;i<keywordArray.length;i++){

                  suggestions.add(keywordArray[i].getName());

                }

                adapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1, suggestions) {
                    @Override
                    public View getView(int position,
                                        View convertView, ViewGroup parent) {
                        View view = super.getView(position,
                                convertView, parent);
                        TextView text = (TextView) view
                                .findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                acTextView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } catch (Exception e) {

            }}
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        queue.add(j);
    }


}
