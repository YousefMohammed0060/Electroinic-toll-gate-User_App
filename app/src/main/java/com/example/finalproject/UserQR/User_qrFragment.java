package com.example.finalproject.UserQR;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class User_qrFragment extends Fragment {

    View view;
    ImageView Qr_CodeIv;
    Bitmap bitmap;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public User_qrFragment() {
    }

    public static User_qrFragment newInstance(String param1, String param2) {
        User_qrFragment fragment = new User_qrFragment();
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
        view=inflater.inflate(R.layout.fragment_user_qr, container, false);
        inti();
        return view;
    }

    private void inti() {
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        Qr_CodeIv=view.findViewById(R.id.Qr_CodeIv);

        QRGEncoder qrgEncoder = new QRGEncoder(mUser.getUid(), null, QRGContents.Type.TEXT, 300);
        bitmap=qrgEncoder.getBitmap();
        Qr_CodeIv.setImageBitmap(bitmap);
    }
}