package app.catsrus;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import app.catsrus.ui.Cat;
import app.catsrus.ui.ImageLink;
import app.catsrus.ui.datastorage.CatDatabase;

public class CatInformationActivity extends AppCompatActivity {

    Cat cat;
    TextView name;
    ImageView image;
    TextView desc;
    TextView weight;
    TextView temperament;
    TextView origin;
    TextView lifespan;
    TextView wiki;
    TextView friendliness;
    private static final String IMAGE_URL = "https://api.thecatapi.com/v1/images/search?breed_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_information);

        //gets the cat from the intent
        cat = (Cat) getIntent().getSerializableExtra("cat_data");

        image = findViewById(R.id.id_image);

        name= findViewById(R.id.id_name);
        desc = findViewById(R.id.id_desc);
        origin = findViewById(R.id.id_origin);
        lifespan = findViewById(R.id.id_life_span_text);
        wiki = findViewById(R.id.id_wiki);
        friendliness = findViewById(R.id.id_friendliness);
        weight = findViewById(R.id.id_weight);
        temperament = findViewById(R.id.id_temp);
        image = findViewById(R.id.id_image);


        //sets the data in place to be viewed by the user
        name.setText(cat.getName() != null ? cat.getName() : "");
        desc.setText(cat.getDescription() != null ? cat.getDescription() : "");
        origin.setText(cat.getOrigin() != null ? "Origin: " + cat.getOrigin() : "");
        lifespan.setText(cat.getLife_span() != null ? "Life Span: " + cat.getLife_span() : "");
        wiki.setText(cat.getWikipedia_url() != null ? "More Information: " + cat.getWikipedia_url() : "");
        friendliness.setText(cat.getDog_friendly() != null ? "Friendliness: " + cat.getDog_friendly() : "");
        temperament.setText(cat.getTemperament() != null ? "Temperament: " + cat.getTemperament() : "");

        String imperial = cat.getWeight().get("imperial");
        String metric = cat.getWeight().get("metric");
        weight.setText("Imperial Weight: " + imperial + "\nMetric Weight: " + metric);

        getImageLink(cat.getId());
    }

    // on favourite button click
    public void onFavouriteButtonClick(View v){
        CatDatabase.addFavourite(cat.getName());
    }


    // api call to get the link of the image
    private void getImageLink(String id){
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final String url = IMAGE_URL + id;
        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ImageLink[] arr = new Gson().fromJson(response, ImageLink[].class);
                if(!(arr == null || arr.length == 0)){
                    getImage(arr[0].url);
                }
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR");
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        queue.add(stringRequest);
    }


    // api call to get the actual image itself
    private void getImage(String link){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                image.setImageBitmap(response);
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR");
            }
        };
        ImageRequest stringRequest = new ImageRequest(link, listener, 0,0,null,errorListener);
        queue.add(stringRequest);
    }
}
