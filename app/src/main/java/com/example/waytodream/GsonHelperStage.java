package com.example.waytodream;

import android.content.Context;

import com.example.waytodream.GsonHelperDream;
import com.example.waytodream.UserDream;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GsonHelperStage {
    private static final String FILE_NAME = "dataStage.json";

    static boolean exportToJSON(Context context, ArrayList<Stage> dataList) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setStages(dataList);
        String jsonString = gson.toJson(dataItems);

        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    static ArrayList<Stage> importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return  dataItems.getStages();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

    private static class DataItems {
        private ArrayList<Stage> stage;

        ArrayList<Stage> getStages() {
            return stage;
        }
        void setStages(ArrayList<Stage> stages) {
            this.stage = stages;
        }
    }
}