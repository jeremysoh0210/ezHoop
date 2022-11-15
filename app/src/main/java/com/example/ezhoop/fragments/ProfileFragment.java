package com.example.ezhoop.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ezhoop.R;
import com.example.ezhoop.activities.MainActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class ProfileFragment extends Fragment {
    private Context context;

    private TextView name, email, location, dateOfBirth, gender;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        location = view.findViewById(R.id.location);
        dateOfBirth = view.findViewById(R.id.date_of_birth);
        gender = view.findViewById(R.id.gender);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        getData();
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

                    name.setText(data.get("fullName").toString());
                    email.setText(((MainActivity) context).currentUser.getEmail());
                    location.setText(data.get("country").toString());
                    dateOfBirth.setText(data.get("dateOfBirth").toString());
                    gender.setText(data.get("gender").toString());
                } else {
                    Log.e("Error", "No such document");
                }
            } else {
                Log.e("Error", "Failed to get data: ", task.getException());
            }
        });
    }
}