package com.aveteam.avewallpaper.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.aveteam.avewallpaper.Activity.MainActivity;
import com.aveteam.avewallpaper.Activity.MenuPagerViewer;
import com.aveteam.avewallpaper.Adapter.ImageAdapter;
import com.aveteam.avewallpaper.Model.Image;
import com.aveteam.avewallpaper.R;
import com.aveteam.avewallpaper.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Lorenzo on 23/08/2016.
 */
public class Home extends Fragment {
    private GridView lista;
    private ArrayList<Image> images;
    private ImageAdapter AdapterIm;
    private String TAG = MainActivity.class.getSimpleName();
    private static final String endpoint = "http://www.gnexushd.altervista.org/beta/wall/wall.json";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.content_main,container,false);


        images = new ArrayList<>();
        AdapterIm = new ImageAdapter(getActivity(), images);

        lista = (GridView)v.findViewById(R.id.list);
        lista.setAdapter(AdapterIm);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {

                // Sending image id to ViewPagerAdapter
                Intent i = new Intent(getActivity(), MenuPagerViewer.class);
                // passing array index
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("id", position);
                //  i.putExtra("id", position);
                i.putExtras(bundle);
                startActivity(i);

            }
        });
        fetchImages();

        return v;
    }
    private void fetchImages() {

        JsonArrayRequest req = new JsonArrayRequest(endpoint,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        images.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Image image = new Image();
                                image.setName(object.getString("name"));

                                JSONObject url = object.getJSONObject("url");
                                image.setSmall(url.getString("small"));
                                image.setMedium(url.getString("medium"));
                                image.setLarge(url.getString("large"));
                                image.setTimestamp(object.getString("timestamp"));
                                images.add(image);

                            } catch (JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                            }
                        }

                        AdapterIm.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
}
