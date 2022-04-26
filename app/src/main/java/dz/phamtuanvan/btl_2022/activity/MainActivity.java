package dz.phamtuanvan.btl_2022.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.Fragment.Change_PassFragment;
import dz.phamtuanvan.btl_2022.Fragment.HomeFragment;
import dz.phamtuanvan.btl_2022.activity_admin.MainActivity_Admin;
import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.model.Student;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btn_login;
    SqlLogin sqlLogin;
    ArrayList<Student> arrayList;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btn_login);

        sqlLogin = new SqlLogin(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String pass = password.getText().toString();

                if (email.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this,"Hãy nhập tài khoản và mật khẩu",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = sqlLogin.checkusernamepassword(email,pass);
                    Boolean checkAdmin = sqlLogin.checkAdmin(email,pass);
                    if (checkuserpass == true){
                        arrayList = sqlLogin.getName(email);
                        student = arrayList.get(0);
                        String name =  student.getName();
                        Toast.makeText(MainActivity.this,"Đăng nhập thành công với tư cách sinh viên",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity_Test.class);
                        intent.putExtra("MSV",email);
                        intent.putExtra("name",name);
                        startActivity(intent);



                    }else if (checkAdmin == true){
                        Toast.makeText(MainActivity.this,"Đăng nhập thành công với tư cách quản trị viên",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity_Admin.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Bạn đã nhập sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        TextView forgot = findViewById(R.id.tv_ques2);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        /*btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        startActivity(intent);
            }
        });*/
    }




}