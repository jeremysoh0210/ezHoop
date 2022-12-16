package com.example.ezhoop.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezhoop.R;
import com.example.ezhoop.activities.MainActivity;
import com.example.ezhoop.activities.StartTrainingActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class StatisticsFragment extends Fragment {
    private Context context;

    private TextView shots, missed, percentages;

//    ListView listView;

    // Define array adapter for ListView
//    ArrayAdapter<String> adapter;

    // Define array List for List View data
//    ArrayList<String> mylist;

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    public StatisticsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

//        searchView = (SearchView) findViewById(R.id.search_view);
//        listView = (ListView) findViewById(R.id.recycler_view);
//
//        list = new ArrayList<>();
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Pineapple");
//        list.add("Orange");
//        list.add("Lychee");
//        list.add("Gavava");
//        list.add("Peech");
//        list.add("Melon");
//        list.add("Watermelon");
//        list.add("Papaya");
//
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if (list.contains(query)) {
//                    adapter.getFilter().filter(query);
//                } else {
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //    adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
    }

        @Override
    public void onStart() {
        super.onStart();

//        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        shots = view.findViewById(R.id.text_shotsNumber);
        missed = view.findViewById(R.id.text_missedNumber);
        percentages = view.findViewById(R.id.text_percentageNumber);
        return view;
    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String uid = ((MainActivity) context).currentUser.getUid();

        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Map<String, Object> data = document.getData();

                    shots.setText(data.get("shots").toString());
                    missed.setText(((MainActivity) context).currentUser.getEmail());
                    percentages.setText(data.get("percentages").toString());
                } else {
                    Log.e("Error", "No such document");
                }
            } else {
                Log.e("Error", "Failed to get data: ", task.getException());
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate menu with items using MenuInflator
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.layout.fragment_statistics, menu);
//
//        // Initialise menu item search bar
//        // with id and take its object
//        MenuItem searchViewItem = menu.findItem(R.id.search_view);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
//
//        // attach setOnQueryTextListener
//        // to search view defined above
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            // Override onQueryTextSubmit method which is call when submit query is searched
//            public boolean onQueryTextSubmit(String query) {
//                // If the list contains the search query than filter the adapter
//                // using the filter method with the query as its argument
//                if (list.contains(query)) {
//                    adapter.getFilter().filter(query);
//                } else {
//                    // Search query not found in List View
//                }
//                return false;
//            }
//
//            // This method is overridden to filter the adapter according
//            // to a search query when the user is typing search
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
}