package com.example.waytodream;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GsonHelperDream {
    private static final String FILE_NAME = "dataDream.json";

    static boolean exportToJSON(Context context, UserDream data) {

        Gson gson = new Gson();
        DataItem dataItems = new DataItem();
        dataItems.setUsers(data);
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

    static UserDream importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItem dataItems = gson.fromJson(streamReader, DataItem.class);
            return  dataItems.getUsers();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

    private static class DataItem {
        private UserDream user;

        UserDream getUsers() {
            return user;
        }
        void setUsers(UserDream users) {
            this.user = users;
        }
    }
}
