package dz.phamtuanvan.btl_2022.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.Database;
import dz.phamtuanvan.btl_2022.model.Question;

public class QuestionActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;
    private TextView textViewCountDown;

    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;


    private Button buttonConfirmNext;
    private Button getButtonConfirmNPrevious;

    private CountDownTimer countDownTimer;
    private ArrayList<Question> questionArrayList;
    private long timeLeftMillis;
    private int questionCounter;
    private int questionSize;

    private int Score;
    private boolean answerd;

    private int count = 0;

    public Question currentQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        AnhXa();
        //Nhận dữ liệu chủ đề;
        Intent intent = getIntent();
        int categoryID = intent.getIntExtra("idcategory",0);
        String categoryName = intent.getStringExtra("catgoriesname");

        //Hiển thị chủ đề
        textViewCategory.setText("Chủ đề:"+categoryName);

        Database database = new Database(this);

        //danh sách list chứa câu hỏi
        questionArrayList = database.getQuestions(categoryID);

        //Lấy kích cỡ danh sách  = tổng số câu hỏi
        questionSize = questionArrayList.size();

        //đảo vị trí các phần tử câu hỏi
        /*Collections.shuffle(questionArrayList);*/

        //show câu hỏi và đáp án
        showNextQuestion();

        //button xác nhận ,câu tiếp, hoàn thành
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nếu chưa trả lời câu hỏi
                if (!answerd){
                    //Nếu chọn 1 trong 4 đáp án
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        //Kiểm tra đáp án
                        checkAnswer();
                    }
                    else {
                    Toast.makeText(QuestionActivity.this,"Hãy chọn đáp án", Toast.LENGTH_SHORT).show();
                    }
                }
                //Nếu đã trả lời, chuyển đến câu tiếp theo
                else {
                    showNextQuestion();
                }

            }
        });

        /*getButtonConfirmNPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviousQuestion();
            }
        });*/


    }
    //Hiển thị câu hỏi
    private void showNextQuestion() {

        //Set lại màu đen cho đáp án
        textViewQuestion.setTextColor(Color.BLACK);
        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);

        //Xóa chọn
        radioGroup.clearCheck();

        //Nếu còn câu hỏi
        if (questionCounter<questionSize){
            //lấy dữ liệu ở vị trí counter
            currentQuestion = questionArrayList.get(questionCounter);

            //hiển thị câu hỏi
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            //tăng sau mỗi câu hỏi
            questionCounter++;
            //set vị trí câu hỏi hiện tại
            textViewQuestionCount.setText("Câu hỏi:"+questionCounter+" / "+questionSize);

            //giá trị false, chưa trà lời, đang show
            answerd = false;

            //Gán tên cho Button
            buttonConfirmNext.setText("Xác nhận");

            //thời gian chạy 30s
            timeLeftMillis = 30000;

            //đếm ngược thời gian trả lời
            startCountDown();


        }
        else {
            finishQuestion();
        }

    }


    //Thời gian đếm ngược
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftMillis,1000) {
            @Override
            public void onTick(long l) {
                timeLeftMillis = l;

                //update
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                //hết giờ
                timeLeftMillis = 0;
                updateCountDownText();
                //phương thức kiểm tra đáp án
                checkAnswer();
            }
        }.start();
    }

    //kiểm tra đáp án
    private void checkAnswer() {
        //true đã trả lời
        answerd = true;

        //Trả về radiobutton
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        //vị trí của câu đã chọn
        int answer  = radioGroup.indexOfChild(rbSelected) + 1;
        //Nếu trả lời đúng đáp án
        if (answer == currentQuestion.getAnswer()){

            //Tăng 10 điểm
            Score += 10;
            //hiển thị
            textViewScore.setText("Điểm:"+Score);

        }
        //hiển thị đáp án
        showSolution();


    }
    //đáp án
    private void showSolution() {
        //set màu cho radiobutton đáp án
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        //kiểm tra đáp án set màu và hiển thị đáp án lên mà hình
        switch (currentQuestion.getAnswer()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Đáp án là A");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Đáp án là B");
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Đáp án là C");
                break;

            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Đáp án là D");
                break;
        }
        //Nếu còn câu trả lời thì button là câu tiếp
        if (questionCounter < questionSize){
            buttonConfirmNext.setText("Câu tiếp");

        }//Không sẽ để là hoàn thành
        else {
            buttonConfirmNext.setText("Hoàn thành");
        }
        //dừng thời gian lại
        countDownTimer.cancel();
    }

    //update thời gian
    private void updateCountDownText() {
        //tính phút
        int minutes = (int) ((timeLeftMillis/1000)/60);
        //tính giây
        int second = (int) ((timeLeftMillis/1000)%60);
        //Định dạng kiểu time
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,second);

        textViewCountDown.setText(timeFormatted);

        //Nếu thời gian dưới 10s sẽ chuyển màu đỏ
        if (timeLeftMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }//Không thì màu đen
        else {
            textViewCountDown.setTextColor(Color.BLACK);
        }
    }

    //Thoáy qua giao diện chính
    private void finishQuestion() {
        //chứa dữ liệu gửi qua activity main
        Intent resultIntent = new Intent(QuestionActivity.this, activity_score.class);
        resultIntent.putExtra("score",Score);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
    //back
    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            finishQuestion();
        }
        count = 0;
    }

    private void AnhXa(){
        textViewQuestion = findViewById(R.id.txt_view_question);
        textViewScore = findViewById(R.id.txt_view_score);
        textViewQuestionCount = findViewById(R.id.txt_view_question_count);
        textViewCategory = findViewById(R.id.txt_view_category);

        textViewCountDown = findViewById(R.id.txt_view_countdown);
        radioGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_btn1);
        rb2 = findViewById(R.id.radio_btn2);
        rb3 = findViewById(R.id.radio_btn3);
        rb4 = findViewById(R.id.radio_btn4);

        buttonConfirmNext = findViewById(R.id.btn_confirm_next);

        /*getButtonConfirmNPrevious = findViewById(R.id.btn_confirm_previous);*/



    }
}