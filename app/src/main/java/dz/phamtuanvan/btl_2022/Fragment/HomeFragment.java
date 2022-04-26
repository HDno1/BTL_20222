package dz.phamtuanvan.btl_2022.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.Database;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.activity.QuestionActivity;
import dz.phamtuanvan.btl_2022.model.Category;

public class HomeFragment extends Fragment {
    private static final int RESULT_OK = -1 ;
    SqlLogin sqlLogin;
    private TextView txtHighScore;
    private Spinner spinnerCategory;
    private Button buttonStartQuestion;
    private Button btnScoreView;
    public static final int REQUEST_CODE_QUESTION = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        txtHighScore =rootView.findViewById(R.id.txt_high_score);
        buttonStartQuestion = rootView.findViewById(R.id.btn_start);
        spinnerCategory = rootView.findViewById(R.id.spinner_category);
        btnScoreView = rootView.findViewById(R.id.btn_score);

        loadCategories();
        sqlLogin = new SqlLogin(getActivity());
        buttonStartQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuestion();
            }
        });
        Intent intent = getActivity().getIntent();
        String msv = intent.getStringExtra("name");

        btnScoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score = txtHighScore.getText().toString();
                Boolean checkinsertData = sqlLogin.addStudentTest(msv,score);
                if (checkinsertData == true){
                    Toast.makeText(getActivity(),"Điểm danh thành công ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"Điểm danh thất bại",Toast.LENGTH_SHORT).show();
                }

                /*Intent intent = new Intent(getApplicationContext(), activity_score.class);
                startActivity(intent);*/
            }
        });
        return rootView;
    }

    //Hàm bắt đầu câu hỏi qua activity question
    private void startQuestion() {
        Category category = (Category) spinnerCategory.getSelectedItem();

        int categoryID = category.getId();
        String categoryName  = category.getName();


        //Chuyển qua ActivityQuestion
        Intent intent  = new Intent(getActivity(), QuestionActivity.class);

        //Gửi dữ liệu name, id
        intent.putExtra("idcategory",categoryID);
        intent.putExtra("catgoriesname",categoryName);


        //Sử dụng startActivityForResult để có thể nhận lại dữ liệu trả về thông qua phương thức onActivityResult
        startActivityForResult(intent,REQUEST_CODE_QUESTION);

    }

    /*private void AnhXa(){
        txtHighScore =findViewById(R.id.txt_high_score);
        buttonStartQuestion = findViewById(R.id.btn_start);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnScoreView = findViewById(R.id.btn_score);
    }*/


    //Load chủ đề
    public void loadCategories(){
        Database database = new Database(getActivity());

        List<Category> categories = database.getDataCategories();

        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,categories);

        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(categoryArrayAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUESTION && resultCode == RESULT_OK){
            txtHighScore.setText(String.valueOf(data.getIntExtra("score",0)));
        }
    }

}
