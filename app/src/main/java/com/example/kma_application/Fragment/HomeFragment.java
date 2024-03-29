package com.example.kma_application.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kma_application.Activity.GalleryActivity;
import com.example.kma_application.Activity.ParentAbsentActivity;
import com.example.kma_application.Activity.TeacherAbsentActivity;
import com.example.kma_application.Activity.TeacherChildHeathActivity2;
import com.example.kma_application.Activity.TeacherHealthActivity;
import com.example.kma_application.Activity.ParentHealthActivity;
import com.example.kma_application.Activity.ParentPrescriptionActivity;
import com.example.kma_application.Activity.TeacherLessonActivity;
import com.example.kma_application.Activity.TeacherPostLessonActivity;
import com.example.kma_application.Activity.TeacherPrescriptionActivity;
import com.example.kma_application.AsyncTask.LoadClassImageTask;
import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Person;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;


public class HomeFragment extends Fragment implements LoadInfosTask.AsyncResponse{
    String role, phone, _class, homeName;
    Person person;
    Boolean preload = true;

    Context context = getActivity();
    Button btHealth, btAbsent, btMedicine, btLesson, btViewGallerys;
    TextView txtName;
    ImageButton btViewGallery;
    GridView gridView;
    private Object parent;

    public void setLoadInfosTask(LoadInfosTask loadInfosTask) {
        this.loadInfosTask = loadInfosTask;
    }

    LoadInfosTask loadInfosTask;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btHealth = (Button)view.findViewById(R.id.buttonHomeHealth);
        btAbsent = (Button)view.findViewById(R.id.buttonHomeAbsent);
        btMedicine = (Button)view.findViewById(R.id.buttonHomeMedicine);
        btLesson = (Button)view.findViewById(R.id.buttonHomeLesson);
        txtName = (TextView) view.findViewById(R.id.textHome);
        btViewGallery = (ImageButton) view.findViewById( R.id.buttonViewGallery);
        btViewGallerys = (Button) view.findViewById( R.id.buttonViewGallerys);
//        gridView = (GridView) view.findViewById(R.id.gridViewHome);

        loadInfosTask.setTxtName(txtName);

        btHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtHealth();
            }
        });
        btMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtMedicine();
            }
        });
        btAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtAbsent();
            }
        });
        btViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtViewGallery();
            }
        });
        btViewGallerys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtViewGallerys();
            }
        });
        btLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtLesson();
            }
        });

        //if (person != null)
           // loadPreviewGallery();

        return view;
    }

    private void onClickBtViewGallerys() {
//        Intent intent = new Intent(getActivity(), GalleryActivity.class);
//
//        intent.putExtra("info", person);
//        intent.putExtra("role", role);
//        intent.putExtra("class", _class);
//        startActivity(intent);
    }

    private void onClickBtViewGallery() {
        Intent intent = new Intent(getActivity(), GalleryActivity.class);

        intent.putExtra("info", person);
        intent.putExtra("role", role);
        intent.putExtra("class", _class);
        startActivity(intent);
    }

    private void onClickBtHealth() {
        Intent intent = new Intent(getActivity(), TeacherChildHeathActivity2.class);
        //if (role.equals("teacher"))
            //intent = new Intent(getActivity(), TeacherChildHeathActivity2.class);

        //intent.putExtra("info", person);
        intent.putExtra("class", _class);
        intent.putExtra("role", role);
        startActivity(intent);
    }
    private void onClickBtMedicine() {
        Intent intent = new Intent(getActivity(), ParentPrescriptionActivity.class);
        if (role.equals("teacher"))
            intent = new Intent(getActivity(), TeacherPrescriptionActivity.class);

        intent.putExtra("info", person);
        intent.putExtra("class", _class);
        startActivity(intent);
    }
    private void onClickBtAbsent() {
        Intent intent = new Intent(getActivity(), ParentAbsentActivity.class);
        if (role.equals("teacher"))
            intent = new Intent(getActivity(), TeacherAbsentActivity.class);

        intent.putExtra("info", person);
        intent.putExtra("class", _class);

        startActivity(intent);
    }

    private void onClickBtLesson() {
        Intent intent = new Intent(getActivity(), TeacherPostLessonActivity.class);
        intent.putExtra("class", _class);
        intent.putExtra("role", role);

        startActivity(intent);
    }


    @Override
    public void onLoadInfoTaskFinish(Person output, String role) {
        this.person = output;
        this.role = role;
        if (output == null){
            Toast.makeText(this.context, "InfoModel is null", Toast.LENGTH_LONG).show();
        }else{
            this.phone = output.getPhone();
            if (role.equals("teacher")) {
                Teacher teacher = (Teacher) output;
                _class = teacher.get_class();
                txtName.setText("Cô: "+teacher.getName()+" - Lớp: "+teacher.get_class());
            }else {
                Parent parent = (Parent) output;
                _class = parent.get_class();
                txtName.setText("Bé: "+parent.getChildName()+" - Lớp: "+parent.get_class());
            }
            homeName = txtName.getText().toString();
            //Toast.makeText(this.context, homeName, Toast.LENGTH_LONG).show();
//            loadPreviewGallery();
        }
    }
    private void loadPreviewGallery(){
//        Teacher teacher = (Teacher) this.parent;
//        new LoadClassImageTask(
//                getActivity(),
//                gridView,
//                _class,
//                "main",
//                role
//        ).execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        if( !preload){
            txtName.setText(homeName);
            //Toast.makeText(this.context, homeName, Toast.LENGTH_LONG).show();
            //loadPreviewGallery();
        }
        preload = false;
    }

}