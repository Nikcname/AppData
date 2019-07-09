package com.forevermore.nikcname.appdata;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.forevermore.nikcname.appdata.adapters.RecyclerDataAdapterMain;
import com.forevermore.nikcname.appdata.containers.DataElement;
import com.forevermore.nikcname.appdata.fragments.DialogDataPicker;
import com.forevermore.nikcname.appdata.fragments.ViewPagerContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewData;
    private RecyclerView.Adapter dataAdapter;
    private RecyclerView.LayoutManager manager;
    private String TAG = "AppData";
    private ImageButton imageButtonPlus;
    private List<DataElement> dataElements = new ArrayList<>();
    private DialogDataPicker dialogDataPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewData = findViewById(R.id.data_rec_view_main);
        manager = new LinearLayoutManager(this);
        recyclerViewData.setLayoutManager(manager);

        dataAdapter = new RecyclerDataAdapterMain(dataElements);
        recyclerViewData.setAdapter(dataAdapter);

        dialogDataPicker = new DialogDataPicker();
        dialogDataPicker.setListener(new DialogDataPicker.CallbackDialogDataPicker() {
            @Override
            public void dataFile(boolean internal, boolean external, String name, String text) {
                if (internal){
                    new File(getApplicationContext().getFilesDir(), name);
                    FileOutputStream fileOutputStream;
                    try{
                        fileOutputStream = openFileOutput(name, Context.MODE_PRIVATE);
                        fileOutputStream.write(text.getBytes());
                        fileOutputStream.close();
                    } catch (Exception e){
                        Log.e(TAG, e.getMessage());
                    }
                }
                getListOfFiles();
            }

            @Override
            public void dataDirectory(boolean internal, boolean external, String name) {
                if (internal){
                    String folderP = getApplicationContext().getFilesDir().toString();
                    File dir = new File(folderP, name);
                    dir.mkdir();
                    getListOfFiles();
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.custom_action_bar_main);
        setSupportActionBar(toolbar);

        imageButtonPlus = findViewById(R.id.image_button_plus_main);

        getListOfFiles();
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDataPicker.show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    private void getListOfFiles(){
        dataElements.clear();
        for (String filesNames : fileList()){

            File file = new File(getApplicationContext().getFilesDir() + "/" + filesNames);
            DataElement dataElement = new DataElement();

            if (file.isDirectory()){
                dataElement.setName(filesNames);
                dataElement.setInfo("dir");
            } else {
                dataElement.setName(filesNames);
                dataElement.setInfo("file");
            }
            dataElements.add(dataElement);

        }
        dataAdapter.notifyDataSetChanged();
    }
}
