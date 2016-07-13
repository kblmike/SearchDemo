package com.example.kin.volley;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kin.volley.model.ListingImages;

import java.util.List;

/**
 * Created by kbl on 6/28/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    ListingImages[] mimages;

    public CustomPagerAdapter(Context context,ListingImages[] images) {
        mContext=context;
        mLayoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mimages=images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        TextView mimage_count=(TextView)itemView.findViewById(R.id.image_count);
        mimage_count.setText(position+1 +" of " + mimages.length);
        mimage_count.bringToFront();
        NetworkImageView imageView = (NetworkImageView) itemView.findViewById(R.id.NetworkImageViewPagePage);
        imageView.setImageUrl(mimages[position].getImageUrl(),ConnectionManager.getImageLoader(mContext));

        container.addView(itemView);

        return itemView;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public int getCount() {

        return mimages.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
