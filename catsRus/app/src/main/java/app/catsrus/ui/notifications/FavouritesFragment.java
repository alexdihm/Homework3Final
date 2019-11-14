package app.catsrus.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import app.catsrus.R;
import app.catsrus.ui.Cat;
import app.catsrus.ui.adapter.SearchAdapter;
import app.catsrus.ui.datastorage.CatDatabase;

public class FavouritesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);
        return root;
    }


    public void onViewCreated(View v, Bundle savedInstnceState){
        RecyclerView recyclerView = getView().findViewById(R.id.id_rv_favourites);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // reuses the same adapter as the search adapter for the favourites recyclerview
        HashMap<Integer, String> map = CatDatabase.favourites;
        HashMap<Integer, Cat> favourites = new HashMap<>();
        for(Integer key : map.keySet()){
            favourites.put(key, new Cat(map.get(key)));
        }

        SearchAdapter itemAdapter = new SearchAdapter(favourites, null);
        recyclerView.setAdapter(itemAdapter);
    }

}