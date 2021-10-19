package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoginTask;
import com.example.kma_application.R;


import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class LoginActivity extends AppCompatActivity implements MyBiometricManager.Callback{

    private static final String KEY_NAME = "";
    EditText txtPhone,txtPassword;
    Button btLogin;
    ImageButton imgBtBiometric;
    private MyBiometricManager myBiometricManager;
    private KeyGenerator keyGenerator;
    private KeyStore keyStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Init view
        txtPhone = (EditText)findViewById(R.id.editTextPhone);
        txtPassword = (EditText)findViewById(R.id.editTextTextPassword);
        btLogin = (Button)findViewById(R.id.buttonLogin);
        //Biometric
        imgBtBiometric = (ImageButton)findViewById(R.id.imgBtBiometric);
        myBiometricManager = MyBiometricManager.getInstance(this);
        
        //Event handle
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtLogin();
            }
        });

        imgBtBiometric.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                if(myBiometricManager.checkIfBiometricFeatureAvailable()){
                    myBiometricManager.authenticate();
                }
//                Object purpose;
//                KeyGenParameterSpec authPerOpKeyGenParameterSpec =
//                        new KeyGenParameterSpec.Builder("myKeystoreAlias", key-purpose)
//                                // Accept either a biometric credential or a device credential.
//                                // To accept only one type of credential, include only that type as the
//                                // second argument.
//                                .setUserAuthenticationParameters(0 /* duration */,
//                                        KeyProperties.AUTH_BIOMETRIC_STRONG |
//                                                KeyProperties.AUTH_DEVICE_CREDENTIAL)
//                                .build();
            }
//            Cipher cipher = getCipher();
//            SecretKey secretKey = getSecretKey();
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//            myBi.authenticate(promptInfo,
//                    new BiometricPrompt.CryptoObject(cipher));
        });
    }

    private void onClickBtLogin() {
        loginUser(txtPhone.getText().toString().trim(),
                txtPassword.getText().toString().trim());
    }

    // test data
    String userJson(String phone, String password) {
        return "{\"phone\":\"" + phone + "\","
                +"\"password\":\"" + password +"\"}";
    }
    private void loginUser(String phone, String password) {
        if (TextUtils.isEmpty(phone)){

            Toast.makeText(this,"Số điện thoại không được để trống",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){

            Toast.makeText(this,"Mật khẩu không được để trống",Toast.LENGTH_LONG).show();
            return;
        }
        LoginTask loginTask = new LoginTask(this, phone);
        loginTask.execute(userJson(phone,password));
    }

    @Override
    public void onBiometricAuthenticationResult(String result, CharSequence errString) {
        switch (result){
            case AUTHENTICATION_SUCCESSFUL:
                Toast.makeText(LoginActivity.this, "Xác thực thành công", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "AUTHENTICATION_SUCCESSFUL");
                txtPhone.setText("0123456789");
                txtPassword.setText("123");
                onClickBtLogin();
                break;
            case AUTHENTICATION_FAILED:
                Toast.makeText(LoginActivity.this, "Xác thực thất bại", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "AUTHENTICATION_FAILED");
                break;
            case AUTHENTICATION_ERROR:
                Toast.makeText(LoginActivity.this, "Lỗi xác thực", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "AUTHENTICATION_ERROR");
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyBiometricManager.REQUEST_CODE && resultCode == RESULT_OK){

        }
    }


    //SecretKey
    private void generateSecretKey(KeyGenParameterSpec keyGenParameterSpec) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        keyGenerator.init(keyGenParameterSpec);
        keyGenerator.generateKey();
    }

    private SecretKey getSecretKey() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");

        // Before the keystore can be accessed, it must be loaded.
        keyStore.load(null);
        return ((SecretKey)keyStore.getKey(KEY_NAME, null));
    }

    private Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                + KeyProperties.BLOCK_MODE_CBC + "/"
                + KeyProperties.ENCRYPTION_PADDING_PKCS7);
    }

//    //Xác thực chỉ bằng thông tin đăng nhập sinh trắc học
//    void generateSecretKey(new KeyGenParameterSpec.Builder(
//            KEY_NAME,
//                           KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
//        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
//        .setUserAuthenticationRequired(true)
//    // Invalidate the keys if the user has registered a new biometric
//    // credential, such as a new fingerprint. Can call this method only
//    // on Android 7.0 (API level 24) or higher. The variable
//    // "invalidatedByBiometricEnrollment" is true by default.
//        .setInvalidatedByBiometricEnrollment(true)
//        .build());
}