package com.example.waytodream.ui.list_fragment;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.waytodream.Goal;
import com.example.waytodream.GoalsStartsAdapter;
import com.example.waytodream.R;

import java.util.List;

public class GoalsListAdapter extends RecyclerView.Adapter<GoalsListAdapter.MyViewHolder> {
    private List<Goal> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView goalName;

        public MyViewHolder(View v) {
            super(v);
            goalName = v.findViewById(R.id.goalNameList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Click", "on item click");
                }
            });
        }
    }

    public GoalsListAdapter(List<Goal> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public GoalsListAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goal_list, parent, false);
        GoalsListAdapter.MyViewHolder vh = new GoalsListAdapter.MyViewHolder(v);
        return vh;
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
