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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_qrFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_qrFragment extends Fragment {

    View view;
    ImageView Qr_CodeIv;
    Bitmap bitmap;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User_qrFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserQR.
     */
    // TODO: Rename and change types and number of parameters
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