package com.example.movieapp.ui.sessions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapp.databinding.FragmentSessionsBinding;

public class SessionsFragment extends Fragment {

    private FragmentSessionsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SessionsViewModel sessionsViewModel =
                new ViewModelProvider(this).get(SessionsViewModel.class);

        binding = FragmentSessionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

            final TextView textView = binding.textSessions;
            sessionsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
            return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // binding = null;
    }
}