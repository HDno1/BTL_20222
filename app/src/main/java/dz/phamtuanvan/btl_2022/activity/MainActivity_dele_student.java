package dz.phamtuanvan.btl_2022.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.adapter.LoadStudent;
import dz.phamtuanvan.btl_2022.model.Student;

public class MainActivity_dele_student extends AppCompatActivity {
    public static int vitri;
    SqlLogin sqlLogin;
    private ArrayList<Student> studentArrayList;
    LoadStudent loadStudentadapter;
    ListView lv_load_student;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dz.phamtuanvan.btl_2022.R.layout.activity_main_dele_student);
        sqlLogin = new SqlLogin(this);
        lv_load_student = findViewById(R.id.lv_dssv);
        imageButton = findViewById(R.id.ima_btn_dele_sv);
        loadDataInListView();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLogin.deleUser(vitri);
                loadDataInListView();
            }
        });


    }

    private void loadDataInListView() {
                studentArrayList = sqlLogin.getMSV();
                loadStudentadapter = new LoadStudent(MainActivity_dele_student.this,studentArrayList);
                lv_load_student.setAdapter(loadStudentadapter);
                loadStudentadapter.notifyDataSetChanged();
    }
}