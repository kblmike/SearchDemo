package com.example.kin.volley.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kin on 6/25/2016.
 */
public class ListingImages  implements Serializable {

    @SerializedName("MediaId")
    private int MediaId;

    @SerializedName("ListingID")
    private int ListingID;

    @SerializedName("ImageUrl")
    private String ImageUrl;

    @SerializedName("Sequence")
    private int Sequence;

    public int getMediaId() {
        return MediaId;
    }

    public void setMediaId(int mediaId) {
        MediaId = mediaId;
    }

    public int getListingID() {
        return ListingID;
    }

    public void setListingID(int listingID) {
        ListingID = listingID;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        Sequence = sequence;
    }
}
