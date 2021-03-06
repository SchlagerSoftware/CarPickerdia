package com.example.carpickerdia.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.carpickerdia.HomeAppActivity;
import com.example.carpickerdia.R;
import com.example.carpickerdia.viewmodels.mainapp.LoginViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.SignInButton;

import java.util.Arrays;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class LoginFragment extends Fragment {

    private final int RC_SIGN_IN = 1;
    private LoginViewModel viewModel;
    private SignInButton loginButton;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        view = inflater.inflate(R.layout.fragment_login, container, false);
        checkIfSignedIn();
        prepareUI();
        prepareOnClickActions();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void checkIfSignedIn(){
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), firebaseUser -> {
            if(firebaseUser != null){
                getActivity().startActivity(new Intent(getActivity(), HomeAppActivity.class));
            }
        });
    }

    private void prepareUI(){
        loginButton = view.findViewById(R.id.button_google_sign_in);
    }

    private void prepareOnClickActions() {
        loginButton.setOnClickListener(v-> {
            signIn();
        });
    }

    private void signIn(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInRequest(int resultCode){
        if(resultCode == RESULT_OK) {
            getActivity().startActivity(new Intent(getActivity(), HomeAppActivity.class));
        }
        else{
            Toasty.error(getContext(), "Invalid data", Toasty.LENGTH_SHORT).show();
        }
    }
}
