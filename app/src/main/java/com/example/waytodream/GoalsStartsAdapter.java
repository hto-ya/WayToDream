package com.example.waytodream;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GoalsStartsAdapter extends RecyclerView.Adapter<GoalsStartsAdapter.MyViewHolder> {
    private List<Goal> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText mTextView;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.elementListGoalStart);
           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Click", "on item click");
                }
            });
        }
    }

    public GoalsStartsAdapter(List<Goal> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public GoalsStartsAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_start, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mTextView.setText(mDataset.get(position).getGoalName());

        holder.mTextView.setOnKeyListener(new View.OnKeyListener()
                                  {
                                      public boolean onKey(View v, int keyCode, KeyEvent event)
                                      {
                                          if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                  ((keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_BACK)) && (!holder.mTextView.getText().toString().equals("")))
                                          {
                                              // сохраняем текст, введённый до нажатия Enter в переменную
                                              mDataset.get(position).setGoalName(holder.mTextView.getText().toString());
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
