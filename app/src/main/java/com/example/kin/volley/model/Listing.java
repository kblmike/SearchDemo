package com.example.kin.volley.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kin on 6/16/2016.
 */
public class Listing implements Serializable {
    @SerializedName("Id")
    private String ListingID;

    @SerializedName("Address")
    private String Address;

    @SerializedName("Neighborhood")
    private String Neighborhood;


    @SerializedName("Price")
    private String Price;

    @SerializedName("Beds")
    private String Beds;

    @SerializedName("Baths")
    private String Baths;

    @SerializedName("ImageUrl")
    private String Image;

    @SerializedName("Description")
    private String Description;

    @SerializedName("ListingMedias")
    private List<ListingImages> Images;

    public List<ListingImages> getImages() {
        return Images;
    }

    public String getListingID() {
        return ListingID;
    }

    public String getAddress() {
        return Address;
    }

    public String getNeighborhood() {
        return Neighborhood;
    }

    public String getPrice() {
        return Price;
    }

    public String getBeds() {
        return Beds;
    }

    public String getBaths() {
        return Baths;
    }

    public String getImage() {
        return Image;
    }

    public String getDescription() {
        return Description;
    }
}
