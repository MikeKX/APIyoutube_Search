package com.example.mike.youtubeapi;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mc on 10/7/16.
 */

public class DataEntry {
    private Context context;

    public static final String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&key=AIzaSyAOl9P42AVJLTOniUzDBw5RmC161bJ3irI";

    public DataEntry(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String readStringFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open(url);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<objectItems> getAllMedias() {
        List<objectItems> result = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONObject(readStringFromAsset()).getJSONArray("items");
            int length = jsonArray.length();

            JSONObject jsonObjectTmp;
            objectItems objTmp;
            String titleTmp;
            String imageTmp;

            for (int i = 0; i < length; i++) {

                jsonObjectTmp = jsonArray.getJSONObject(i).getJSONObject("snippet");

                titleTmp = jsonObjectTmp.getString("title");
                imageTmp = jsonObjectTmp.getJSONObject("thumbnails").getJSONObject("medium").getString("url");

                objTmp = new objectItems();

                objTmp.setTitle(titleTmp);
                objTmp.setImage(imageTmp);

                result.add(objTmp);
//                Log.d("MediaObject", "Title : " + mediaObjectTmp.getTitle());
//                Log.d("MediaObject", "ImageUrl : " + mediaObjectTmp.getImageUrl());
            }
            titleTmp = null;
            imageTmp = null;
            jsonObjectTmp = null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;

    }
}
