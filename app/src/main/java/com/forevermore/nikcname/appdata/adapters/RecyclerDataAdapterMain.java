package com.forevermore.nikcname.appdata.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forevermore.nikcname.appdata.R;
import com.forevermore.nikcname.appdata.containers.DataElement;

import java.util.List;

public class RecyclerDataAdapterMain extends RecyclerView.Adapter<RecyclerDataAdapterMain.RecyclerViewHolder> {

    private List<DataElement> dataElements;

    public RecyclerDataAdapterMain(List<DataElement> dataElements){
        this.dataElements = dataElements;
    }

    @NonNull
    @Override
    public RecyclerDataAdapterMain.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_view_element_main, viewGroup, false);

        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapterMain.RecyclerViewHolder recyclerViewHolder, int i) {

        recyclerViewHolder.textViewName.setText(dataElements.get(i).getName());
        recyclerViewHolder.textViewInfo.setText(dataElements.get(i).getInfo());

    }

    @Override
    public int getItemCount() {
        return dataElements.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewInfo;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewInfo = itemView.findViewById(R.id.text_view_info);

        }
    }
}
