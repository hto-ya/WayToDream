package com.example.waytodream.ui.plant_fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.waytodream.ActivityList;
import com.example.waytodream.R;
import com.example.waytodream.databinding.FragmentPlantBinding;
import com.example.waytodream.view_models.SharedViewModel;


public class PlantFragment extends Fragment {

    private PlantViewModel plantViewModel;
    private FragmentPlantBinding binding;

    private int[] plantArray = {
            R.drawable.ic_plant0,
            R.drawable.ic_plant25,
            R.drawable.ic_plant50,
            R.drawable.ic_plant60,
            R.drawable.ic_plant75,
            R.drawable.ic_plant100
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantViewModel =
                new ViewModelProvider(this).get(PlantViewModel.class);


        binding = FragmentPlantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




//        final TextView textView = binding.textHome;
//        plantViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

         ActivityList mYourActivity = (ActivityList) getActivity();
        assert mYourActivity != null;

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(mYourActivity.label());

        @SuppressLint("UseCompatLoadingForDrawables") Drawable d = getResources().getDrawable(plantArray[0]);

        ImageView plant = root.findViewById(R.id.imagePlant);
        TextView textView = root.findViewById(R.id.example_of_usage);
        SharedViewModel sharedViewModel = SharedViewModel.getInstance();
        LiveData<String> myData = sharedViewModel.getMyData();
        myData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        plant.setImageResource(plantArray[3]);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}