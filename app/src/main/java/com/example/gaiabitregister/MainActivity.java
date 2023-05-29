package com.example.gaiabitregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    Activity context = this;
    Button b1,b2,b3,b4;
    TextView tv4,tv8;
    EditText et1,et2,et3,et4;
    String email;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //註冊的部分
        //按鈕
        b1 = (Button)findViewById(R.id.bt_1);//註冊按鈕
        //cancel按鈕
        b2 = (Button) findViewById(R.id.bt_2);
        b3 = (Button)findViewById(R.id.bt_3);

        //EditText
        et1 = (EditText)findViewById(R.id.et_1);
        et2 = (EditText)findViewById(R.id.et_2);

        //textview

        mAuth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(et1.getText().toString(),et2.getText().toString())
                        .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if (task.isSuccessful()){ //如果註冊成功
                                    //獲取當前用戶


                                    FirebaseUser user= mAuth.getCurrentUser();
                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "驗證碼已寄出請至信箱驗證", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                    Toast.makeText(context, "註冊成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this,logoActivity2.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(context, "註冊失敗", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setText(null);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et2.setText(null);
            }
        });


    }
}