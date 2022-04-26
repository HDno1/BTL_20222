package dz.phamtuanvan.btl_2022.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;

public class MainActivity_add_acount_sv extends AppCompatActivity {
    EditText edt_add_email, edt_add_pass, edt_add_name, edt_add_msv;
    ImageButton btn_add_user;
    SqlLogin sqlLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_acount_sv);
        edt_add_email = findViewById(R.id.edt_add_email);
        edt_add_pass = findViewById(R.id.edt_add_pass_sv);
        edt_add_name = findViewById(R.id.edt_add_name);
        edt_add_msv = findViewById(R.id.edt_add_msv);
        btn_add_user = findViewById(R.id.ima_btn_add_student);
        sqlLogin = new SqlLogin(this);
        btn_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_add_email.getText().toString();
                String pass = edt_add_pass.getText().toString();
                String name = edt_add_name.getText().toString();
                String msv = edt_add_msv.getText().toString();
                Boolean checkaddSV = sqlLogin.addUser(email,pass,name,msv);
                if (checkaddSV == true){
                    Toast.makeText(MainActivity_add_acount_sv.this,"Bạn đã thêm sinh viên mới thành công",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity_add_acount_sv.this,"Thêm mới thất bại, vui lòng nhập đầy đủ tài khoản, mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}