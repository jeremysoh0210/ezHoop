package com.example.ezhoop.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ezhoop.R;
import com.example.ezhoop.activities.MainActivity;
import com.example.ezhoop.activities.StartTrainingActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class StatisticsFragment extends Fragment {
    private Context context;

    private TextView shots, missed, percentages;

    public StatisticsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

//        shots = view.findViewById(R.id.shots);
//        missed = view.findViewById(R.id.missed);
//        percentages = view.findViewById(R.id.percentages);
        return view;
    }

//    private void getData() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        String uid = ((MainActivity) context).currentUser.getUid();
//
//        DocumentReference docRef = db.collection("users").document(uid);
//        docRef.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                DocumentSnapshot document = task.getResult();
//                if (document.exists()) {
//                    Map<String, Object> data = document.getData();
//
//                    shots.setText(data.get("shots").toString());
//                    missed.setText(((MainActivity) context).currentUser.getEmail());
//                    percentages.setText(data.get("percentages").toString());
//                } else {
//                    Log.e("Error", "No such document");
//                }
//            } else {
//                Log.e("Error", "Failed to get data: ", task.getException());
//            }
//        });
//    }
}