package app.catsrus.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;

import app.catsrus.CatInformationActivity;
import app.catsrus.R;
import app.catsrus.ui.Cat;
import app.catsrus.ui.adapter.SearchAdapter;

public class HomeFragment extends Fragment implements SearchAdapter.OnClickListener {

    private EditText searchBar;
    private ImageView searchIcon;
    private HashMap<Integer, Cat> map;
    private static final String URL = "https://api.thecatapi.com/v1/breeds/search?q=";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    public void onViewCreated(View v, Bundle savedInstnceState){
        searchBar = getView().findViewById(R.id.searchbar);
        searchIcon = getView().findViewById(R.id.searchbutton);
        map = new HashMap<Integer, Cat>();

        searchIcon.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clears the cat map everytime the search icon is clicked
                map.clear();
                String input = searchBar.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url = URL + input;
                final Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // update the recycler view everytime the search icon is clicked
                        update(response);
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
        };
    }

    private void update(String response){
        Cat[] res = new Gson().fromJson(response, Cat[].class);
        for(int i = 0; i < res.length; i++){
            map.put(i, res[i]);
        }
        RecyclerView recyclerView = getView().findViewById(R.id.searchrecyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SearchAdapter itemAdapter = new SearchAdapter(map, this);
        recyclerView.setAdapter(itemAdapter);
    }

    // when an item is clicked, move to the CatInformationActivity with all the information
    @Override
    public void onItemClick(int position) {
        Cat cat = map.get(position);
        Cat.url = null;
        Intent intent = new Intent(getActivity(), CatInformationActivity.class);
        intent.putExtra("cat_data", cat);
        startActivity(intent);
    }
}