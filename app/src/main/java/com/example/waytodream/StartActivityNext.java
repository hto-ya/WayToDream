package com.example.waytodream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class StartActivityNext extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Stage> stages;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_next);

        open();

        mRecyclerView = (RecyclerView)
                findViewById(R.id.recycler_start_next);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        stages = new ArrayList<Stage>();
        stages.add(new Stage(""));
        mAdapter = new StageStartsAdapter(stages, this);
        mRecyclerView.setAdapter(mAdapter);

        Button addStage = findViewById(R.id.addStage);

        addStage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (stages.get(stages.size() - 1).getStageName() == "") {
                    Toast.makeText(getApplicationContext(), "Данные не заполнены", Toast.LENGTH_SHORT).show();
                } else {
                    stages.add(new Stage(""));
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        Button next = findViewById(R.id.start_next);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (getStatus(stages)){
                    save();

                    Intent myIntent = new Intent(context, ActivityList.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(getApplicationContext(),"Данные не заполнены", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void save(){
        boolean result = GsonHelperStage.exportToJSON(this, stages);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }


    private void open(){
        stages = GsonHelperStage.importFromJSON(this);
        if(stages!=null){
            Intent myIntent = new Intent(context, ActivityList.class);
            startActivity(myIntent);
            finish();
          //  Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();
        }
    }

    private boolean getStatus(ArrayList<Stage> stages) {

        for (Stage stage : stages) {
            Log.i("Stage", stage.getStageName());
            if (stage.getStageName().equals(""))
            {
                return false;
            }

            for (Goal goal : stage.getGoals()) {
                Log.i("Goal", goal.getGoalName());
                if (goal.getGoalName().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

}