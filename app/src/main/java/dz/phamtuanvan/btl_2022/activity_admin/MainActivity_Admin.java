package dz.phamtuanvan.btl_2022.activity_admin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.activity.LoadQues;
import dz.phamtuanvan.btl_2022.activity.MainActivity_List_SVTEST;
import dz.phamtuanvan.btl_2022.activity.MainActivity_add_acount_sv;
import dz.phamtuanvan.btl_2022.activity.activity_insert_question;
import dz.phamtuanvan.btl_2022.activity.insert_content;
import dz.phamtuanvan.btl_2022.activity.MainActivity_dele_student;

public class MainActivity_Admin extends AppCompatActivity {
    ImageButton btn_add_student,btn_add_ques,btn_dele_ques,btn_add_cate,btn_dele_sinhvien,btn_DSSV_TEST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        btn_add_student = findViewById(R.id.ima_btn_add_student);
        btn_add_ques = findViewById(R.id.ima_btn_add_ques);
        btn_dele_ques = findViewById(R.id.ima_btn_dele_ques);
        btn_add_cate = findViewById(R.id.ima_btn_add_cat);
        btn_dele_sinhvien = findViewById(R.id.ima_btn_dele_sv);
        btn_DSSV_TEST = findViewById(R.id.ima_btn_dssv_test);

        btn_add_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Admin.this, activity_insert_question.class);
                startActivity(intent);
            }
        });

        btn_dele_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Admin.this, LoadQues.class);
                startActivity(intent);
            }
        });
        btn_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Admin.this, MainActivity_add_acount_sv.class);
                startActivity(intent);
            }
        });

        btn_add_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Admin.this, insert_content.class);
                startActivity(intent);
            }
        });

        btn_dele_sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Admin.this, MainActivity_dele_student.class);
                startActivity(intent);
            }
        });

        btn_DSSV_TEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Admin.this, MainActivity_List_SVTEST.class);
                startActivity(intent);
            }
        });
    }
}