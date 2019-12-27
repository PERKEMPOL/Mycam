package com.example.mycam.bottom.ui.kategori;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KategoriViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KategoriViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}