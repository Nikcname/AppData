package com.forevermore.nikcname.appdata.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.forevermore.nikcname.appdata.R;

import java.util.List;

public class DialogDataPicker extends DialogFragment {

    TextView textViewTitle;
    Button buttonNext;
    ViewPagerContainer viewPagerContainer;
    FragmentManager fm;
    SelectDirectory selectDirectory;
    List<String> listData;
    CallbackDialogDataPicker callbackDialogDataPicker;
    boolean internalDirectory, externalDirectory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_picker, container, false);

        textViewTitle = v.findViewById(R.id.text_view_title_picker);
        buttonNext = v.findViewById(R.id.button_next_picker);

        viewPagerContainer = new ViewPagerContainer();
        viewPagerContainer.setListener(new ViewPagerContainer.CallbackViewPagerContainer() {
            @Override
            public void getList(List<String> list) {
                listData = list;
            }
        });

        selectDirectory = new SelectDirectory();
        selectDirectory.setListener(new SelectDirectory.CallbackSelectDirectory() {
            @Override
            public void booleans(boolean internal, boolean external) {
                internalDirectory = internal;
                externalDirectory = external;
            }
        });

        fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_layout_picker, viewPagerContainer);
        ft.commit();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonNext.getText().equals(getResources().getString(R.string.next))){
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right
                    );
                    ft.replace(R.id.frame_layout_picker, selectDirectory);
                    ft.addToBackStack(null);
                    ft.commit();
                } else if (buttonNext.getText().equals(getResources().getString(R.string.create))){

                    if (listData.size() == 1){
                        callbackDialogDataPicker.dataDirectory(internalDirectory,
                                externalDirectory, listData.get(0));
                    } else if (listData.size() == 2){
                        callbackDialogDataPicker.dataFile(internalDirectory, externalDirectory,
                                listData.get(0), listData.get(1));
                    }
                    dismiss();
                }

                buttonNext.setText(getResources().getString(R.string.create));
            }
        });

    }

    public interface CallbackDialogDataPicker{
        void dataFile(boolean internal, boolean external, String name, String text);
        void dataDirectory(boolean internal, boolean external, String name);
    }

    public void setListener(CallbackDialogDataPicker callbackDialogDataPicker){
        this.callbackDialogDataPicker = callbackDialogDataPicker;
    }
}
