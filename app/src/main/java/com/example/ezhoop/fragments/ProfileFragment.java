package com.example.ezhoop.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ezhoop.R;
import com.example.ezhoop.activities.MainActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private Context context;

    private TextView name, email, location, dateOfBirth, gender;

    private Button BSelectImage;

    // One Preview Image
    private ImageView profile_image;

    int SELECT_PICTURE = 200;

    public ProfileFragment() {
    }

    public void onCreate(LayoutInflater inflater, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

//        ViewGroup container = null;
//        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
//        BSelectImage = (Button) view.findViewById(R.id.BSelectImage);
//        profile_image = (ImageView) view.findViewById(R.id.profile_image);
//
//        // handle the Choose Image button to trigger
//        // the image chooser function
//        BSelectImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageChooser();
//            }
//        });
    }
//    void imageChooser() {
//
//        // create an instance of the
//        // intent of the type image
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//
//        // pass the constant to compare it
//        // with the returned requestCode
//        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//
//            // compare the resultCode with the
//            // SELECT_PICTURE constant
//            if (requestCode == SELECT_PICTURE) {
//                // Get the url of the image from data
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    profile_image.setImageURI(selectedImageUri);
//                }
//            }
//        }
//    }

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
        if (context != null) {
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
}