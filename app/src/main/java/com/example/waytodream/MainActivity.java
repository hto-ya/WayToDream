package com.example.waytodream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView header;
    private EditText dream;
    private EditText motivation;
    private UserDream userDream;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        open();

        setContentView(R.layout.activity_start);
        button = findViewById(R.id.button1);
        header = findViewById(R.id.headerStart);
        dream = findViewById(R.id.dream);
        motivation = findViewById(R.id.motivation);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dream.getText().length() == 0  || motivation.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(),"Данные не заполнены", Toast.LENGTH_SHORT).show();
                } else {
                    String name = dream.getText().toString();
                    String motivat = motivation.getText().toString();;
                    userDream = null;
                    userDream = new UserDream(name, motivat);;

                    save();

                    Intent myIntent = new Intent(context, StartActivityNext.class);
                    startActivity(myIntent);
                    finish();
                }
            }
        });
    }

    public void save(){
        boolean result = GsonHelperDream.exportToJSON(this, userDream);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }


    public void open(){
        userDream = GsonHelperDream.importFromJSON(this);
        if(userDream!=null){
            Intent myIntent = new Intent(context, StartActivityNext.class);
            startActivity(myIntent);
            finish();
         //   Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();
        }
    }

}
