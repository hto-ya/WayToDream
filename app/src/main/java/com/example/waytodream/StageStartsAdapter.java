package com.example.waytodream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StageStartsAdapter extends RecyclerView.Adapter<StageStartsAdapter.MyViewHolder> {
    private List<Stage> sDataset;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mtextBut;
        public EditText mTextView;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.stage_start);
            mtextBut = v.findViewById(R.id.addGoal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("ClickStage", "on item click");
                }
            });
        }
    }

    public StageStartsAdapter(List<Stage> Dataset, Context ctx) {
        sDataset = Dataset;
        context = ctx;
    }

    @Override
    public StageStartsAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_start, parent, false);
        StageStartsAdapter.MyViewHolder vh = new StageStartsAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StageStartsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mTextView.setText(sDataset.get(position).getStageName());

        View v = holder.itemView;
        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_in_list_start);

        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GoalsStartsAdapter(sDataset.get(position).getGoals());
        mRecyclerView.setAdapter(mAdapter);

        holder.mtextBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (sDataset.get(position).getGoals().get(sDataset.get(position).getGoals().size()-1).getGoalName() == "") {
                    Toast.makeText(v.getContext(), "Данные не заполнены", Toast.LENGTH_SHORT).show();
                } else {
                    sDataset.get(position).addGoal(new Goal(""));
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        holder.mTextView.setOnKeyListener(new View.OnKeyListener()
                                          {
                                              public boolean onKey(View v, int keyCode, KeyEvent event)
                                              {
                                                  if(event.getAction() == KeyEvent.ACTION_DOWN && (
                                                          (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_BACK)) && (!holder.mTextView.getText().toString().equals("")))
                                                  {
                                                      // сохраняем текст, введённый до нажатия Enter в переменную
                                                      sDataset.get(position).setStageName(holder.mTextView.getText().toString());
                                                      return true;
                                                  }
                                                  return false;
                                              }
                                          }
        );
    }


    @Override
    public int getItemCount() {
        return sDataset.size();
    }
}
