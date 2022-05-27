package com.example.waytodream.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private SharedViewModel() {}
    private static SharedViewModel instance = null;
    public static SharedViewModel getInstance () {
        if (instance == null) {
            instance = new SharedViewModel();
        }
        return instance;
    }
    private MutableLiveData<String> myData;

    public MutableLiveData<String> getMyData() {
        if (myData == null) {
            myData = new MutableLiveData<>();
            myData.setValue("Not changed");
        }
        return myData;
    }

    public void setMyData(String value) {
        this.myData.setValue("Changed");
    }



}
