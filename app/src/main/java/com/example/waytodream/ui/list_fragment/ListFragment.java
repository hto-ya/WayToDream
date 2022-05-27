package com.example.waytodream.ui.list_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waytodream.ActivityList;
import com.example.waytodream.R;
import com.example.waytodream.Stage;
import com.example.waytodream.StageStartsAdapter;
import com.example.waytodream.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.Objects;

public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Stage> stages;
    private ListViewModel listViewModel;
    private FragmentListBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listViewModel =
                new ViewModelProvider(this).get(ListViewModel.class);

        binding = FragmentListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//      //  final TextView textView = binding.textDashboard;
//        listViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        ActivityList mYourActivity = (ActivityList) getActivity();
        assert mYourActivity != null;

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(mYourActivity.label());


        mRecyclerView = root.findViewById(R.id.recycler_goal_list);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        stages = mYourActivity.getData();
        mAdapter = new GoalsListAdapter(stages.get(0).getGoals(), this);
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}