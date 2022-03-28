package com.example.finalproject.Profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Profile.Cars.CarsActivity;
import com.example.finalproject.Profile.EditProfile.EditProfileActivity;
import com.example.finalproject.Profile.Wallets.WalletsActivity;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{
    View view;
    ConstraintLayout EditProfileLayout,WalletsLayout,CarsLayout;
    ImageView ProfileImage;
    TextView UserName,Logout;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        inti();
        return view;
    }

    private void inti() {
        ProfileImage=view.findViewById(R.id.ProfileImage);
        UserName=view.findViewById(R.id.UserName);
        Logout=view.findViewById(R.id.Logout);
        EditProfileLayout=view.findViewById(R.id.EditProfileLayout);
        WalletsLayout=view.findViewById(R.id.WalletsLayout);
        CarsLayout=view.findViewById(R.id.CarsLayout);
        EditProfileLayout.setOnClickListener(this);
        WalletsLayout.setOnClickListener(this);
        CarsLayout.setOnClickListener(this);
        Logout.setOnClickListener(this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mUserRef= FirebaseDatabase.getInstance().getReference().child("Users");

        LoadUser();
    }

    private void LoadUser() {
        mUserRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String profileImageUrl=snapshot.child("profileImage").getValue().toString();
                    String Username=snapshot.child("username").getValue().toString();

                    Picasso.get().load(profileImageUrl).into(ProfileImage);
                    UserName.setText(Username);

                }else {
                    Toast.makeText(view.getContext(), "Data not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.EditProfileLayout:
                startActivity(new Intent(view.getContext(), EditProfileActivity.class));
                break;
            case R.id.WalletsLayout:
                startActivity(new Intent(view.getContext(), WalletsActivity.class));
                break;
            case R.id.CarsLayout:
                startActivity(new Intent(view.getContext(), CarsActivity.class));
                break;
        }
    }
}