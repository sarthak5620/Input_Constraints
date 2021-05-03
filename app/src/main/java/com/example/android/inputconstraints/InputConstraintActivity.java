package com.example.android.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.inputconstraints.databinding.ActivityInputConstraintBinding;


public class InputConstraintActivity extends AppCompatActivity {
    private static final int REQUEST_INPUT = 0;
    ActivityInputConstraintBinding b ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInputConstraintBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
    //Take input from user and validate
    public void takeInput(View view) {
        Bundle bundle  = new Bundle();
        //Validate if uppercase checkbox is checked
        if (b.upperCase.isChecked()) bundle.putString(Constants.UPPERCASE , "true");
        //Validate if lowercase checkbox is checked
        if (b.LowerCase.isChecked()) bundle.putString(Constants.LOWERCASE , "true");
        //Validate if Digits checkbox is checked
        if (b.Digits.isChecked()) bundle.putString(Constants.DIGITS , "true");
        //Validate if Alphabetical operations checkbox is checked
        if (b.AlphabeticalOperations.isChecked()) bundle.putString(Constants.ALPHABETICAL_OPERATIONS , "true");
        //Validate if other symbols checkbox is checked
        if (b.OtherSymbols.isChecked()) bundle.putString(Constants.OTHERS , "true");
        //In case if nothing is chosen..We show toast
        if (bundle.isEmpty()){
            Toast.makeText(this, "Please select something!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Intent to request action from other component of apps
        Intent intent=new Intent(this,InputActivity.class);
        intent.putExtras(bundle);
        //Start Activity to get result from other activity
        startActivityForResult(intent,REQUEST_INPUT);
    }
    // Overridden method to start getting result from an activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_INPUT && resultCode == RESULT_OK){
            b.Result.setText("Input is " + data.getStringExtra(Constants.INPUT ));
            b.Result.setVisibility(View.VISIBLE);
        }
    }
}