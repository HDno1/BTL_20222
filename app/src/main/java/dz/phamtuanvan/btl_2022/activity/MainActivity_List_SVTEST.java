package dz.phamtuanvan.btl_2022.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.adapter.ListStudentTestAdapter;
import dz.phamtuanvan.btl_2022.model.Student;

public class MainActivity_List_SVTEST extends AppCompatActivity {
    public static ArrayList<Integer> vitri = new ArrayList<>();
    private ArrayList<Student> arrayList;
    private ListView lv_hienthi;
    SqlLogin sqlLogin;
    private ImageButton btn_delete;
    ListStudentTestAdapter listStudentTestAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dz.phamtuanvan.btl_2022.R.layout.activity_main_list_svtest);
        lv_hienthi = findViewById(R.id.lv_student_test);
        btn_delete = findViewById(R.id.ima_btn_dele_sv);
        sqlLogin = new SqlLogin(this);
        loadDataInListView();
        Spinner spinner = findViewById(R.id.spinner_up_down);
        ArrayList<String> arrayListArrange = new ArrayList<String>();
        arrayListArrange.add("Điểm cao - thấp");
        arrayListArrange.add("Điểm thấp - cao");
        ArrayAdapter arrayAdapterArrange = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListArrange);
        arrayAdapterArrange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapterArrange);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vitri.isEmpty()){
                    for (int k:vitri){
                         Boolean checkDeleteData = sqlLogin.deleteData(k);
                         if (checkDeleteData == true){
                             Toast.makeText(MainActivity_List_SVTEST.this,"Delete Completed",Toast.LENGTH_SHORT).show();
                             loadDataInListView();
                         }
                         else {
                             Toast.makeText(MainActivity_List_SVTEST.this,"Delete Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                            vitri.clear();//sau khi xóa xong các nhân viên
                            }else {
                    Toast.makeText(MainActivity_List_SVTEST.this,"Bạn chưa chọn sinh viên nào", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int id = adapterView.getSelectedItemPosition();
                if (id == 0){
                    arrayList = sqlLogin.arrangeScore();
                    listStudentTestAdapter = new ListStudentTestAdapter(MainActivity_List_SVTEST.this, arrayList);
                    lv_hienthi.setAdapter(listStudentTestAdapter);
                    listStudentTestAdapter.notifyDataSetChanged();
                }
               else if (id == 1){
                    arrayList = sqlLogin.arrangeScore1();
                    listStudentTestAdapter = new ListStudentTestAdapter(MainActivity_List_SVTEST.this, arrayList);
                    lv_hienthi.setAdapter(listStudentTestAdapter);
                    listStudentTestAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadDataInListView() {
        arrayList = sqlLogin.getaAllData();
         listStudentTestAdapter = new ListStudentTestAdapter(this, arrayList);
        lv_hienthi.setAdapter(listStudentTestAdapter);
        listStudentTestAdapter.notifyDataSetChanged();
    }
}