package com.owen.nakurutravellers;

//this gas been replaced byvfirebase ui
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private ProgressDialog progressDialog;

    private Button btnCreate;
    private EditText et_Email, et_Passwd, eet_Uname;
    private TextView tv_AlreadyMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnCreate = (Button) findViewById(R.id.btn_createAccount);
        et_Email = (EditText) findViewById(R.id.Et_userEmail);
        et_Passwd = (EditText) findViewById(R.id.Et_userPassword);
        eet_Uname = (EditText)findViewById(R.id.Et_userName);
        tv_AlreadyMember = (TextView) findViewById(R.id.tv_AlreadyMember);

        btnCreate.setOnClickListener(this);
        tv_AlreadyMember.setOnClickListener(this);

    }


    public void createUserAccount()
    {
        String email = et_Email.getText().toString().trim();
        String password = et_Passwd.getText().toString().trim();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(SignUpActivity.this,"Created successful",Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("Registering User,,,");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this,"Created successful",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Try Again,,,,", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }

                    }
                });
    }



    @Override
    public void onClick(View view) {
        if (view == btnCreate)
        {
           createUserAccount();
        }
        if (view == tv_AlreadyMember)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}

