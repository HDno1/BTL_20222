package dz.phamtuanvan.btl_2022.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.adapter.ListScoreAdapter;
import dz.phamtuanvan.btl_2022.model.Student;

public class activity_score extends AppCompatActivity {

    private ArrayList<Student> arrayList;
    private ListView lv_hienthi;
    SqlLogin sqlLogin;
    public static int vitri;
    private Button btn_delete;
    ListScoreAdapter listScoreAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        lv_hienthi = findViewById(R.id.lv_ds_thi);
        btn_delete = findViewById(R.id.btn_delete_diemdanh);
        sqlLogin = new SqlLogin(this);
        loadDataInListView();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkDeleteData = sqlLogin.deleteData(vitri);
                if (checkDeleteData == true){
                    Toast.makeText(activity_score.this,"Delete Completed",Toast.LENGTH_SHORT).show();

                    loadDataInListView();

                }
                else {
                    Toast.makeText(activity_score.this,"Delete Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*lv_hienthi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Boolean checkDeleteData = sqlLogin.deleteData(vitri);
                if (checkDeleteData == true){
                    Toast.makeText(activity_score.this,"Delete Completed",Toast.LENGTH_SHORT).show();

                    loadDataInListView();

                }
                else {
                    Toast.makeText(activity_score.this,"Delete Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });*/


    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUESTION && resultCode == RESULT_OK){
            txt_score.setText(String.valueOf(data.getIntExtra("score",0)));
        }
    }*/

    private void loadDataInListView() {
        arrayList = sqlLogin.getaAllData();
        listScoreAdapter = new ListScoreAdapter(this, arrayList);
        lv_hienthi.setAdapter(listScoreAdapter);
        listScoreAdapter.notifyDataSetChanged();
    }

}