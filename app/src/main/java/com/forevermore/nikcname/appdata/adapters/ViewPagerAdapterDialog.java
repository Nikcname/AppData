package com.forevermore.nikcname.appdata.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.forevermore.nikcname.appdata.fragments.PageDirectory;
import com.forevermore.nikcname.appdata.fragments.PageFile;

public class ViewPagerAdapterDialog extends FragmentStatePagerAdapter {

    int tabNum;
    CallbackViewPagerAdapterDialog callbackViewPagerAdapterDialog;

    public ViewPagerAdapterDialog(FragmentManager fm, int tabNum) {
        super(fm);
        this.tabNum = tabNum;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                PageFile pageFile = new PageFile();
                pageFile.setListener(new PageFile.CallbackPageFile() {
                    @Override
                    public void text(String fileName, String text) {
                        callbackViewPagerAdapterDialog.dataFile(fileName, text);
                    }
                });
                return pageFile;
            case 1:
                PageDirectory pageDirectory = new PageDirectory();
                pageDirectory.setListener(new PageDirectory.CallbackPageDirectory() {
                    @Override
                    public void name(String name) {
                        callbackViewPagerAdapterDialog.dataDirectory(name);
                    }
                });
                return pageDirectory;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabNum;
    }

    public interface CallbackViewPagerAdapterDialog{
        void dataFile(String name, String text);
        void dataDirectory(String name);
    }

    public void setListener(CallbackViewPagerAdapterDialog callbackViewPagerAdapterDialog){
        this.callbackViewPagerAdapterDialog = callbackViewPagerAdapterDialog;
    }
}
