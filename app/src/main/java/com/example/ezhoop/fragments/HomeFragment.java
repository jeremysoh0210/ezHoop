package com.example.ezhoop.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ezhoop.R;
import com.example.ezhoop.activities.StartTrainingActivity;

public class HomeFragment extends Fragment {
    private Context context;

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnStartTraining = view.findViewById(R.id.btn_start_training);
        btnStartTraining.setOnClickListener(v -> {
            Intent intent = new Intent(context, StartTrainingActivity.class);
            startActivity(intent);
        });

        return view;
    }
}