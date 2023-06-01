package com.example.gaiabitregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public class logoActivity2 extends AppCompatActivity {
    Activity context = this;
    Button bt1, bt2,bt4,bt3;
    EditText et1, et2;
    String email;
    FirebaseAuth mAuth; //全域
//git測試0601
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo2);


        bt1 = (Button) findViewById(R.id.bt_1);
        bt2 = (Button) findViewById(R.id.bt_2);
        bt4 = (Button) findViewById(R.id.bt_4);
        bt3 = (Button) findViewById(R.id.bt_3);

        et1 = (EditText) findViewById(R.id.et_1);
        et2 = (EditText) findViewById(R.id.et_2);
        mAuth = FirebaseAuth.getInstance();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用註冊過的電子郵件登入
                mAuth.signInWithEmailAndPassword(et1.getText().toString(), et2.getText().toString()).addOnCompleteListener
                        (context, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    email = user.getEmail();
                                    Toast.makeText(context, "登入成功", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    Toast.makeText(context, "您輸入的電子郵件或密碼錯誤，請註冊帳號後並登入。", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                //判斷網路
                ConnectivityManager connManager = (ConnectivityManager)//取得此 CONNECTIVITY_SERVICE
                        getSystemService(CONNECTIVITY_SERVICE); //取得網路相關資訊
                NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
                //判斷是否有網路
                if (networkInfo == null || !networkInfo.isConnected()){
                    Toast.makeText(context, "目前沒有沒網路，請打開行動網路或WIFI", Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
        //END
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logoActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //清除EditText裡面的字
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setText(null);

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et2.setText(null);
            }
        });
    }
}