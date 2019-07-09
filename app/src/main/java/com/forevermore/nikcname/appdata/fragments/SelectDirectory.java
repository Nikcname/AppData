package com.forevermore.nikcname.appdata.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.forevermore.nikcname.appdata.R;

public class SelectDirectory extends Fragment {

    CheckBox checkBoxInternal;
    CheckBox checkBoxExternal;
    CallbackSelectDirectory callbackSelectDirectory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_select, container, false);

        checkBoxInternal = v.findViewById(R.id.check_box_internal);
        checkBoxExternal = v.findViewById(R.id.check_box_external);

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

        callbackSelectDirectory.booleans(checkBoxInternal.isChecked(), checkBoxExternal.isChecked());
    }

    public interface CallbackSelectDirectory{
        void booleans(boolean internal, boolean external);
    }

    public void setListener(CallbackSelectDirectory callbackSelectDirectory){
        this.callbackSelectDirectory = callbackSelectDirectory;
    }
}
