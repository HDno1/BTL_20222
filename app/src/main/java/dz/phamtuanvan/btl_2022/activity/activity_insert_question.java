package dz.phamtuanvan.btl_2022.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.Database;

public class activity_insert_question extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Database database;
    Button btn_add_question,btn_delete_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_question);

        ed1 =(EditText) findViewById(R.id.txt_question);
        ed2 =(EditText) findViewById(R.id.txt_question2);
        ed3 =(EditText) findViewById(R.id.txt_question3);
        ed4 =(EditText) findViewById(R.id.txt_question4);
        ed5 =(EditText) findViewById(R.id.txt_question5);
        ed6 =(EditText) findViewById(R.id.txt_question6);
        ed7 =(EditText) findViewById(R.id.txt_question7);

        database = new Database(this);

        btn_add_question = (Button) findViewById(R.id.btn_insert_question);
        btn_delete_question = (Button) findViewById(R.id.btn_delete_question1);

        btn_add_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = ed1.getText().toString();
                String s2 = ed2.getText().toString();
                String s3 = ed3.getText().toString();
                String s4 = ed4.getText().toString();
                String s5 = ed5.getText().toString();
                int s6 = Integer.parseInt(ed6.getText().toString());
                int s7 = Integer.parseInt(ed7.getText().toString());


                Boolean checkinsert = database.insertQuestion(s1,s2,s3,s4,s5,s6,s7);
                if (checkinsert == true){
                    Toast.makeText(activity_insert_question.this,"Insert Completed",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(activity_insert_question.this,"Insert Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}