package com.forevermore.nikcname.appdata.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.forevermore.nikcname.appdata.R;

public class PageDirectory extends Fragment {

    EditText editTextDirName;
    TextView textViewDirName;
    CallbackPageDirectory callbackPageDirectory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_directory, container, false);

        editTextDirName = v.findViewById(R.id.edit_text_name_directory);
        textViewDirName = v.findViewById(R.id.text_view_name_directory);

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        callbackPageDirectory.name(editTextDirName.getText().toString());
    }

    public interface CallbackPageDirectory{
        void name(String name);
    }

    public void setListener(CallbackPageDirectory callbackPageDirectory){
        this.callbackPageDirectory = callbackPageDirectory;
    }
}
