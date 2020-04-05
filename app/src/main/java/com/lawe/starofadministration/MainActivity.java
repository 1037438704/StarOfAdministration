package com.lawe.starofadministration;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wynsbin.vciv.VerificationCodeInputView;

public class MainActivity extends AppCompatActivity implements VerificationCodeInputView.OnInputListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VerificationCodeInputView verificationCodeInputView1 = findViewById(R.id.vciv_code1);
        final VerificationCodeInputView verificationCodeInputView2 = findViewById(R.id.vciv_code2);
        final VerificationCodeInputView verificationCodeInputView3 = findViewById(R.id.vciv_code3);
        verificationCodeInputView1.setOnInputListener(this);
        verificationCodeInputView2.setOnInputListener(this);
        verificationCodeInputView3.setOnInputListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationCodeInputView1.clearCode();
                verificationCodeInputView2.clearCode();
                verificationCodeInputView3.clearCode();
            }
        });
    }

    @Override
    public void onComplete(String code) {
        Toast.makeText(MainActivity.this, code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInput() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
