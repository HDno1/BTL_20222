package dz.phamtuanvan.btl_2022.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.Database;

public class insert_content extends AppCompatActivity {

    private EditText txt_add_category,txt_add_id;
    private Button btn_delete, btn_insert;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_content);

        btn_insert =(Button) findViewById(R.id.btn_insert);

        txt_add_category = findViewById(R.id.txt_add_category);
        txt_add_id = findViewById(R.id.txt_add_category_id);

        database = new Database(this);

        btn_delete =findViewById(R.id.btn_delete);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catID = Integer.parseInt(txt_add_id.getText().toString());
                String cat2 = txt_add_category.getText().toString();
                Boolean checkinsertData = database.insertCategories(catID,cat2);
                if (checkinsertData == true){
                    Toast.makeText(insert_content.this,"Insert Completed",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(insert_content.this,"Insert Failed",Toast.LENGTH_SHORT).show();
                }



            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cat2 = txt_add_category.getText().toString();
                Boolean checkdelete = database.deleteCategory(cat2);
                if (checkdelete == true){
                    Toast.makeText(insert_content.this,"Update Completed",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(insert_content.this,"Update Failed",Toast.LENGTH_SHORT).show();
                }



            }
        });




    }





}