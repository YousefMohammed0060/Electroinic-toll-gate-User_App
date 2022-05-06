package com.example.finalproject.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Bills.billModel;
import com.example.finalproject.Profile.Wallets.WalletDetailsActivity;
import com.example.finalproject.Profile.Wallets.WalletsModel;
import com.example.finalproject.Profile.Wallets.WalletsViewHolder;
import com.example.finalproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    View view;
    RecyclerView HomeWalletsRv;
    TextView HomeBalance, HomeLastTransaction;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mUserRef, walletRef, billRef;
    FirebaseRecyclerAdapter<WalletsModel, WalletsViewHolder> adapter;
    FirebaseRecyclerOptions<WalletsModel> options;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        inti();
        LoadWallets();
        return view;
    }

    private void LoadWallets() {
        billRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<billModel> billModels = new ArrayList<>();
                List<Integer> priceModels = new ArrayList<>();
                billModels.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    billModel billModel = postSnapshot.getValue(billModel.class);
                    billModels.add(billModel);
                }
                for (int i = 0; i < billModels.size(); i++) {
                    String userId = billModels.get(i).getUserID();
                    if (mUser.getUid().equals(userId)) {
                        priceModels.add(billModels.get(i).getPrice());
                    }
                }
                HomeLastTransaction.setText(priceModels.get(priceModels.size() - 1) + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        options = new FirebaseRecyclerOptions.Builder<WalletsModel>().setQuery(walletRef, WalletsModel.class).build();
        adapter = new FirebaseRecyclerAdapter<WalletsModel, WalletsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull WalletsViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull WalletsModel model) {
                if (model.getUserID().equals(mUser.getUid())) {
                    holder.WalletName.setText(" " + model.getWalletName());
                    holder.WalletName.setBackgroundResource(R.color.layouts);
                } else {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), WalletDetailsActivity.class);
                        intent.putExtra("walletKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public WalletsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.walltes_item, parent, false);
                return new WalletsViewHolder(view);
            }
        };
        adapter.startListening();
        HomeWalletsRv.setAdapter(adapter);
    }

    private void inti() {
        HomeBalance = view.findViewById(R.id.HomeBalance);
        HomeLastTransaction = view.findViewById(R.id.HomeLastTransaction);
        HomeWalletsRv = view.findViewById(R.id.HomeWalletsRv);
        HomeWalletsRv.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        walletRef = FirebaseDatabase.getInstance().getReference().child("Wallets");
        billRef = FirebaseDatabase.getInstance().getReference().child("Bills");

        walletRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HomeBalance.setText(" " + snapshot.child("WalletBalance").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}