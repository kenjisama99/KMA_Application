package com.example.kma_application.Activity;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
//import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.provider.Settings;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.widget.Toast;
import androidx.biometric.BiometricPrompt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;


public class MyBiometricManager {
    private static MyBiometricManager instance = null;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private  androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    private Context context;
    private FragmentActivity fragmentActivity;
    private Callback callback;
    public static final int REQUEST_CODE = 100;
    private Object purpose;

    private MyBiometricManager(){
    }

    public static MyBiometricManager getInstance(Context context){
        if(instance == null){
            instance = new MyBiometricManager();
        }
        instance.init(context);
        return instance;
    }

    private void init(Context context) {
        this.context = context;
        this.fragmentActivity = (FragmentActivity) context;
        this.callback = (Callback) context;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    boolean checkIfBiometricFeatureAvailable(){
        androidx.biometric.BiometricManager biometricManager = androidx.biometric.BiometricManager.from(context);

        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App xac thuc sinh trac hoc");
                return true;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "App khong xac thuc sinh trac hoc");
                Toast.makeText(context, "App khong xac thuc sinh trac hoc", Toast.LENGTH_LONG).show();
                return false;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Xac thuc sinh trac hoc khong kha dung");
                Toast.makeText(context, "Xac thuc sinh trac hoc khong kha dung", Toast.LENGTH_LONG).show();
                return false;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                fragmentActivity.startActivityForResult(enrollIntent, REQUEST_CODE);
                return false;
        }
        return false;

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    void authenticate(){
        setupBiometric();
        biometricPrompt.authenticate(promptInfo);}

    private void setupBiometric() {
        executor = ContextCompat.getMainExecutor(context);
        biometricPrompt = new BiometricPrompt(fragmentActivity, executor,
                new BiometricPrompt.AuthenticationCallback(){
                    @Override
                    public void onAuthenticationError(int errorCode,@NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        callback.onBiometricAuthenticationResult(Callback.AUTHENTICATION_ERROR, errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        callback.onBiometricAuthenticationResult(Callback.AUTHENTICATION_SUCCESSFUL, "");
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        callback.onBiometricAuthenticationResult(Callback.AUTHENTICATION_FAILED, "");
                    }
                });
        showBiometricPrompt();
    }

    private void showBiometricPrompt() {
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Xác thực vân tay")
                .setSubtitle("Đăng nhập dùng xác thực vân tay")
                .setNegativeButtonText("Dùng mật khẩu")
                .build();
    }

    interface Callback{
        void onBiometricAuthenticationResult(String result, CharSequence errString);

        String AUTHENTICATION_SUCCESSFUL = "AUTHENTICATION_SUCCESSFUL";
        String AUTHENTICATION_ERROR = "AUTHENTICATION_ERROR";
        String AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";

    }


}
