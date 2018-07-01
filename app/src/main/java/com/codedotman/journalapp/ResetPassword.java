package com.codedotman.journalapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_reset_password)
    Button btnReset;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.email)
    EditText inputEmail;
    @BindView(R.id.pb_loading)
    ProgressBar mPb;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // binds views
        ButterKnife.bind(this);

        // initialise authentication
        auth = FirebaseAuth.getInstance();

        // set clickListeners
        btnReset.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_reset_password:
                isNetworkAvailable();
            default:
                break;
        }
    }

    // test for network
    private void isNetworkAvailable() {
        if (!NetworkClass.isConnected(this)) {
            Toast.makeText(ResetPassword.this, R.string.network_error_message, Toast.LENGTH_LONG).show();
        } else {
            performPasswordReset();
        }
    }

    // performs request for password request
    private void performPasswordReset() {
        String email = inputEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError(getString(R.string.confirmation_email));
            return;
        }
        // sets progressbar to visible
        mPb.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPassword.this, R.string.reset_message, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ResetPassword.this, LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(ResetPassword.this, R.string.error_reset_message, Toast.LENGTH_LONG).show();
                        }
                        // hides progress
                        mPb.setVisibility(View.INVISIBLE);
                    }
                });
    }
}
