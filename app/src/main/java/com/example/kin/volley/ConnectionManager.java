package com.example.kin.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Kin on 6/16/2016.
 */
public class ConnectionManager {
    private static RequestQueue queue;

    private static ImageLoader sImageLoader;

    public static RequestQueue getInstance(Context context){
        if(queue==null){
            queue = Volley.newRequestQueue(context);
        }

        return queue;
    }


    public static ImageLoader getImageLoader(Context context){
        if(sImageLoader==null){
            sImageLoader = new ImageLoader(getInstance(context),new ImageLoader.ImageCache(){

                private final LruCache<String,Bitmap> mCache=new LruCache<String,Bitmap>(10);


                @Override
                public Bitmap getBitmap(String url) {
                    return mCache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    mCache.put(url,bitmap);
                }
            });
        }

        return sImageLoader;
    }
}
