package dz.phamtuanvan.btl_2022.activity;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.Database;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.model.Category;
import dz.phamtuanvan.btl_2022.model.Student;

public class MainActivity2 extends AppCompatActivity {
    SqlLogin sqlLogin;
    Student student;
    ArrayList<Student> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText account = (EditText) findViewById(R.id.edt_tk);
        TextView show_pass = findViewById(R.id.tv_tv1);
        Button btn_forgot = findViewById(R.id.btn_forgot);
        sqlLogin = new SqlLogin(this);


        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = account.getText().toString();
                arrayList = sqlLogin.getPass(username);
                    student = arrayList.get(0);
                show_pass.setText("MẬT KHẨU CỦA BẠN LÀ: "+student.getPassword());

            }
        });

    }
}