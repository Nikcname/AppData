package com.forevermore.nikcname.appdata.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.forevermore.nikcname.appdata.R;

public class PageFile extends Fragment {

    TextView textViewName;
    TextView textViewText;
    EditText editTextName;
    EditText editTextText;
    CallbackPageFile callbackPageFile;

    public PageFile(){};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_file, container, false);

        textViewName = v.findViewById(R.id.text_view_name_file);
        textViewText = v.findViewById(R.id.text_view_text_file);
        editTextName = v.findViewById(R.id.edit_text_name_file);
        editTextText = v.findViewById(R.id.edit_text_text_file);

        return v;
    }

    @Override
    public void onPause(){
        super.onPause();

        callbackPageFile.text(editTextName.getText().toString(), editTextText.getText().toString());
    }

    public interface CallbackPageFile{
        void text(String fileName, String text);
    }

    public void setListener(CallbackPageFile callbackPageFile){
        this.callbackPageFile = callbackPageFile;
    }
}
