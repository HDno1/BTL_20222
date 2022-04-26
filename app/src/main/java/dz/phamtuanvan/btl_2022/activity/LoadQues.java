package dz.phamtuanvan.btl_2022.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.Database;
import dz.phamtuanvan.btl_2022.adapter.ListQuesAdapter;
import dz.phamtuanvan.btl_2022.model.Question;

public class LoadQues extends AppCompatActivity {

    Database database;
    public static ArrayList<Integer> vitri = new ArrayList<>();
    private ArrayList<Question> questionArrayList;
    ListQuesAdapter ListQuesAdapter;
    ListView lv_load;
    EditText txt_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dz.phamtuanvan.btl_2022.R.layout.activity_load_ques);

        lv_load = (ListView) findViewById(R.id.lv_loadques);
        Button btn_search_ques = (Button)findViewById(R.id.btn_search_ques);
        Button btn_dele_ques = findViewById(R.id.btn_delete_question1);
        txt_id = (EditText)findViewById(R.id.txt_search_ques);

        database = new Database(this);

        /*int search = txt_id.getText().;*/
        btn_search_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadDataInListView();
            }
        });

        btn_dele_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vitri.isEmpty()){
                    for (int k:vitri){
                         Boolean checkDeleteData = database.deleteQuestion(k);
                         if (checkDeleteData == true){
                             Toast.makeText(LoadQues.this,"Delete Completed",Toast.LENGTH_SHORT).show();
                             loadDataInListView();
                         }
                         else {
                             Toast.makeText(LoadQues.this,"Delete Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                            vitri.clear();//sau khi xóa xong các câu hỏi
                            }else {
                    Toast.makeText(LoadQues.this,"Bạn chưa chọn câu hỏi nào", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void loadDataInListView() {
                int id =Integer.parseInt(txt_id.getText().toString());
                questionArrayList = database.getQuestions(id);
                ListQuesAdapter = new ListQuesAdapter(LoadQues.this,questionArrayList);
                lv_load.setAdapter(ListQuesAdapter);
                ListQuesAdapter.notifyDataSetChanged();
    }


}