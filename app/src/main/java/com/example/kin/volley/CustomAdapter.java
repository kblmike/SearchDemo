package com.example.kin.volley;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kin.volley.model.Listing;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kin on 6/17/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    List<Listing> mListingList;
    Context mContext;


    public CustomAdapter(List<Listing> listingList, Context context){
        mListingList=listingList;
        mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listing,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Listing currentListing=mListingList.get(position);
        holder.mTextViewAddress.setText(currentListing.getAddress());

        //set currency
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        try {
            long mprice= (long) nf.parse(currentListing.getPrice());
            holder.mTextViewPrice.setText("$"+nf.format(mprice));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if(!TextUtils.isEmpty(currentListing.getImage())){
            holder.mListingImage.setVisibility(View.VISIBLE);
            holder.mListingImage.setImageUrl(currentListing.getImage(),ConnectionManager.getImageLoader(mContext));
        }
        else{
            holder.mListingImage.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                // on click action here
                //-- use context to start the new Activity
                Intent intent = new Intent(mContext, DetailPage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listing_details", currentListing);

                intent.putExtras(bundle);


               // mediaStreamIntent.putExtra("listing_details", (android.os.Parcelable) currentListing);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewAddress;
        public TextView mTextViewPrice;
        public NetworkImageView mListingImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewAddress=(TextView) itemView.findViewById(R.id.rowTextViewName);
            mTextViewPrice=(TextView) itemView.findViewById(R.id.rowTextViewPrice);
            mListingImage=(NetworkImageView) itemView.findViewById(R.id.rowNetworkImageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(),DetailPage.class);
                    view.getContext().startActivity(intent);
                }
            });

        }

    }
}
