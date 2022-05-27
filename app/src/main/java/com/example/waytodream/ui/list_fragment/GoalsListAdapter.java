package com.example.waytodream.ui.list_fragment;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waytodream.Goal;
import com.example.waytodream.GoalsStartsAdapter;
import com.example.waytodream.R;
import com.example.waytodream.view_models.SharedViewModel;

import java.util.List;

public class GoalsListAdapter extends RecyclerView.Adapter<GoalsListAdapter.MyViewHolder> {
    private final List<Goal> mDataset;
    private final Fragment fragment;

    public GoalsListAdapter(List<Goal> myDataset, Fragment fragment) {
        mDataset = myDataset;
        this.fragment = fragment;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView goalName;

        public MyViewHolder(View v, Fragment fragment) {
            super(v);
            SharedViewModel sharedViewModel = SharedViewModel.getInstance();

            goalName = v.findViewById(R.id.goalNameList);
            itemView.setOnClickListener(view -> {
                sharedViewModel.setMyData(String.valueOf(getItemId()));
            });
        }
    }



    @NonNull
    @Override
    public GoalsListAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goal_list, parent, false);
        return new MyViewHolder(v, fragment);
    }

    @Override
    public void onBindViewHolder(GoalsListAdapter.MyViewHolder holder, int position) {
        holder.goalName.setText(mDataset.get(position).getGoalName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
