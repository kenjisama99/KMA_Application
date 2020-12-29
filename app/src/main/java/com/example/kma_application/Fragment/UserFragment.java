package com.example.kma_application.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kma_application.Activity.ChangePasswordActivity;
import com.example.kma_application.Activity.LoginActivity;
import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;


public class UserFragment extends Fragment implements LoadInfosTask.AsyncResponse{
    Button btLogout, btChangePassword, btEditAvatar;
    EditText txtName, txtPhone, txtId;
    Socket mSocket;
    ImageView imgAvatar;
    Emitter.Listener onNewImage;
    String phone, role;
    InfoModel infoModel;
    TextView txtStillThinkAboutTheName;
    
    private  final  String CLIENT_SEND_IMAGE = "CLIENT_SEND_IMAGE";
    private  final  String CLIENT_SEND_PHONE = "CLIENT_SEND_PHONE";
    private  final  String CLIENT_SEND_REQUEST = "CLIENT_SEND_REQUEST";
    private  final  String SERVER_SEND_IMAGE = "SERVER_SEND_IMAGE";
    private final int REQUEST_TAKE_PHOTO = 123;
    private final int REQUEST_CHOOSE_PHOTO = 321;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        loadInfoToView(view);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtLogout();
            }
        });

        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangePass();
            }
        });
        return view;
    }

    private void loadInfoToView(View view) {
        btLogout = (Button)view.findViewById(R.id.buttonLogout);
        btChangePassword = (Button)view.findViewById(R.id.buttonChangePass);
        txtPhone = (EditText)view.findViewById(R.id.editTextPersonPhone);
        txtId = (EditText)view.findViewById(R.id.editTextPersonID);
        txtName = (EditText)view.findViewById(R.id.editTextPersonName);
        txtStillThinkAboutTheName = (TextView) view.findViewById(R.id.textInfoChildName);

        txtName.setText(infoModel.getName());
        txtId.setText(infoModel.getId());
        txtPhone.setText(infoModel.getPhone());

        if (role.equals("parent")){
            Parent parent = (Parent) infoModel;
            txtStillThinkAboutTheName.setText(parent.getChildName()+" - Lớp "+parent.get_class());
        }else if (role.equals("teacher")){
            Teacher teacher = (Teacher) infoModel;
            txtStillThinkAboutTheName.setText("Lớp "+teacher.get_class());
        }else
            txtStillThinkAboutTheName.setText("Admin");

    }

    private void onClickChangePass() {
        Intent changePassActivity = new Intent(getActivity(), ChangePasswordActivity.class);
        changePassActivity.putExtra("phone",phone);
        startActivity(changePassActivity);
    }

    private void onClickBtLogout() {
        Intent loginActivity = new Intent(getActivity(), LoginActivity.class);
        startActivity(loginActivity);
        getActivity().finish();
    }
    @Override
    public void onLoadInfoTaskFinish(InfoModel output, String role) {
        this.infoModel = output;
        this.role = role;
    }

}