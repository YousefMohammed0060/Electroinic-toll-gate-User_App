package com.example.finalproject.Profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.Profile.EditProfile.EditProfile;
import com.example.finalproject.Profile.Wallets.Wallets;
import com.example.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment implements View.OnClickListener{
    View view;
    ConstraintLayout EditProfileLayout,WalletsLayout;
    ImageView ProfileImage;
    TextView UserName,Logout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
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
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        EditProfileLayout.setOnClickListener(this);
        WalletsLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.EditProfileLayout:
                startActivity(new Intent(view.getContext(), EditProfile.class));
                break;
            case R.id.WalletsLayout:
                startActivity(new Intent(view.getContext(), Wallets.class));
                break;
        }
    }
}