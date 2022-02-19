package com.example.finalproject.Bills;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Bills.RecyclerView.BillsAdapter;
import com.example.finalproject.Bills.RecyclerView.Model;
import com.example.finalproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bills#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bills extends Fragment {
    View view;
    RecyclerView billsRv;
    BillsAdapter billsAdapter;
    ArrayList<Model> bills=new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Bills() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bills.
     */
    // TODO: Rename and change types and number of parameters
    public static Bills newInstance(String param1, String param2) {
        Bills fragment = new Bills();
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
        view=inflater.inflate(R.layout.fragment_bills, container, false);
        billsRv=view.findViewById(R.id.billsRv);
        Model m1=new Model("Success","Yousef Mohammed","2022/09/10","12:00 AM","10 L.E");
        Model m2=new Model("Failed","Yousef Mohammed","2022/09/10","12:00 AM","10 L.E");
        bills.add(m1);
        bills.add(m2);
        bills.add(m1);
        bills.add(m2);
        bills.add(m1);
        bills.add(m2);
        bills.add(m1);
        bills.add(m2);
        billsAdapter=new BillsAdapter(bills);
        billsRv.setAdapter(billsAdapter);
        RecyclerView.LayoutManager LOM=new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        billsRv.setLayoutManager(LOM);
        return view;
    }
}