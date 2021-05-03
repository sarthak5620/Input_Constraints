package com.example.android.inputconstraints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.inputconstraints.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {
ActivityInputBinding b ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInputBinding.inflate(getLayoutInflater());
        setTitle("Input Activity");
        setContentView(b.getRoot());
    }
    //Method to send user input to other activity
    public void sendBack(View view) {
        String data=b.input.getEditText().getText().toString().trim();
        //Validate data input
        if(data.isEmpty()){
            b.input.setError("Please enter data");
            return;
        }
        //method to check the constraint is justified in the given input
       else if(!data.matches(checkInput())){
            b.input.setError("Invalid");
            return;
        }
       //Send result to other activity as intent
       else {
            Intent intent=new Intent(this,InputConstraintActivity.class);
            intent.putExtra(Constants.INPUT,data);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
    //Method to check constraints
    private String checkInput() {
        Bundle bundle=getIntent().getExtras();
        for (String str:bundle.keySet()){
           bundle.getString(str);
        }
        //StringBuilder is used to append string
        StringBuilder regex = new StringBuilder();

        regex.append("^([");
        if(Boolean.parseBoolean(bundle.getString(Constants.UPPERCASE,"false"))){
            regex.append("A-Z");
        }

        if(Boolean.parseBoolean(bundle.getString(Constants.LOWERCASE,"false"))){
            regex.append("a-z");

        }

        if(Boolean.parseBoolean(bundle.getString(Constants.DIGITS,"false"))){
            regex.append("0-9");

        }

        if(Boolean.parseBoolean(bundle.getString(Constants.ALPHABETICAL_OPERATIONS,"false"))){
            regex.append("+-/*%");

        }

        if(Boolean.parseBoolean(bundle.getString(Constants.OTHERS,"false"))){
            regex.append("@#\\\\^{}\\]\"\"^()?`~!;:''.,|\\[");
        }
        regex.append("])+");
        //Return final string
        return regex.toString();
    }
}