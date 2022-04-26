package dz.phamtuanvan.btl_2022.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.activity.MainActivity2;

public class Change_PassFragment extends Fragment {
    private EditText edtOldpass, edtNewpass, checkNewpass;
    private Button btn_change_pass;
    SqlLogin sqlLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_pass,container,false);

        sqlLogin = new SqlLogin(getActivity());
        edtOldpass = rootView.findViewById(R.id.old_pass);
        edtNewpass = rootView.findViewById(R.id.new_pass);
        checkNewpass = rootView.findViewById(R.id.check_pass);

        btn_change_pass = rootView.findViewById(R.id.btn_change_pass);
        Intent intent = getActivity().getIntent();


        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = edtOldpass.getText().toString();
                String newpass = edtNewpass.getText().toString();
                String checkpass = checkNewpass.getText().toString();
                String msv = intent.getStringExtra("MSV");
                if (oldpass.equals("")||newpass.equals("")||checkpass.equals("")){
                    Toast.makeText(getActivity(),"Hãy điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {

                    if (!checkpass.equals(newpass)){

                        Toast.makeText(getActivity(),"Hãy nhập đúng mật khẩu mới",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Boolean checkupdate = sqlLogin.updateUser(msv,newpass);
                        if (checkupdate == true)
                        {
                            Toast.makeText(getActivity(),"Bạn đã đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        return rootView;


    }
}
