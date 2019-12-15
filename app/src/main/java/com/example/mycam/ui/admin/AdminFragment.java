package com.example.mycam.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mycam.R;

public class AdminFragment extends Fragment {

    private AdminViewModel adminViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adminViewModel =
                ViewModelProviders.of(this).get(AdminViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin, container, false);
        return root;
    }
}