package com.example.kin.volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kin.volley.model.Listing;
import com.example.kin.volley.model.ListingImages;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kin on 6/18/2016.
 */
public class DetailPage extends Activity {

    TextView mtextViewAddress,mtextViewPrice,mtextViewBeds,mtextViewBaths,mtextViewDescription,mtextViewNeighborhood;
    NetworkImageView mListingImage;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);
        Intent intent = getIntent();
        Listing listing = (Listing) intent.getExtras().getSerializable("listing_details");

        //mListingImage=(NetworkImageView)findViewById(R.id.NetworkImageView);
        mtextViewAddress=(TextView)findViewById(R.id.textViewAddress);
        mtextViewPrice=(TextView)findViewById(R.id.textViewPrice);
        mtextViewBeds=(TextView)findViewById(R.id.textViewBeds);
        mtextViewBaths=(TextView)findViewById(R.id.textViewBaths);
        mtextViewDescription=(TextView)findViewById(R.id.textViewDescription);
        mtextViewNeighborhood=(TextView)findViewById(R.id.textViewNeighborhood);

        //mListingImage.setImageUrl(listing.getImage(),ConnectionManager.getImageLoader(this));
        mtextViewAddress.setText(listing.getAddress());
        mtextViewNeighborhood.setText(listing.getNeighborhood());

        List<ListingImages> images= listing.getImages();
        ListingImages[] arrayImages = images.toArray(new ListingImages[images.size()]);


        mCustomPagerAdapter = new CustomPagerAdapter(this,arrayImages);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        //set currency
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        try {
            long mprice= (long) nf.parse(listing.getPrice());
            mtextViewPrice.setText("$"+nf.format(mprice));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mtextViewBeds.setText("Beds: "+listing.getBeds());
        mtextViewBaths.setText("Baths: "+listing.getBaths());
        mtextViewDescription.setText(listing.getDescription());



    }
}
