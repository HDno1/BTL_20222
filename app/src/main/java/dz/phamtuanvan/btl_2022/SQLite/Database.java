package dz.phamtuanvan.btl_2022.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dz.phamtuanvan.btl_2022.model.Category;
import dz.phamtuanvan.btl_2022.model.Question;
import dz.phamtuanvan.btl_2022.model.Table;

public class Database extends SQLiteOpenHelper {


    //Tên database
    private static final String DATABASE_NAME = "Question.db";


    private static final int VERSION = 1;

    private SQLiteDatabase db;


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
         //biến bảng chuyên mục
        final String CATEGORIES_TABLE = "CREATE TABLE " +
                Table.CategoriesTable.TABLE_NAME + "( " +
                Table.CategoriesTable._ID + " INTEGER PRIMARY KEY , " +
                Table.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";


        /*db.execSQL(" create Table users ( username TEXT primary key, password TEXT)");
        //
        db.execSQL("insert into users values ('191505650','12345')");
        db.execSQL("insert into users values ('191202620','12345')");

        db.execSQL("insert into users values ('3412342525','12345')");
        db.execSQL("insert into users values ('5422436563','12345')");
        db.execSQL("insert into users values ('13414132','12345')");*/
        //biến bảng question
        final String QUESTIONS_TABLE = "CREATE TABLE " +
                Table.QuestionsTable.TABLE_NAME + " ( " +
                Table.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                Table.QuestionsTable.COLUMN_ANSWER + " INTEGER, " +
                Table.QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + Table.QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                Table.CategoriesTable.TABLE_NAME + "(" + Table.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";



        //tạo bảng
        db.execSQL(CATEGORIES_TABLE);
        db.execSQL(QUESTIONS_TABLE);
        //insert dữ liệu
        CreateCategories();
        CreateQuestions();


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        db.execSQL("DROP TABLE IF EXISTS "+Table.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Table.QuestionsTable.TABLE_NAME);
        /*db.execSQL("DROP TABLE IF EXISTS users");*/
        onCreate(db);
    }

    //insert chủ đề vào cơ sở dữ liệu
    public boolean insertCategories(int id,String name){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.CategoriesTable._ID, id);
        values.put(Table.CategoriesTable.COLUMN_NAME, name);
        long result = db.insert(Table.CategoriesTable.TABLE_NAME,null,values);

        if (result == -1) return false;
        else
            return true;

    }

    public boolean deleteCategory(String columname){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+Table.CategoriesTable.TABLE_NAME+" where " + Table.CategoriesTable.COLUMN_NAME + " = ?",new String[]{columname});
        if (c.getCount()>0){
            long result = db.delete(Table.CategoriesTable.TABLE_NAME,Table.CategoriesTable.COLUMN_NAME +"=?",new String[]{columname});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }

    }


    //Các giá trị Insert
    private void CreateCategories(){

        //Môn Văn id = 1
        Category c1 = new Category(1,"Ngữ Văn");
        insertCategories(c1.getId(),c1.getName());

        //Môn Lịch Sử id = 2;
        Category c2 = new Category(2,"Lịch Sử");
        insertCategories(c2.getId(), c2.getName());

        //Môn Địa Lý id = 3
        Category c3 = new Category(3,"Địa Lý");
        insertCategories(c3.getId(),c3.getName());
    }

    //insert đáp án vào cơ sở dữ liệu
    public boolean insertQuestion(String question,String op1,String op2,String op3,String op4,int answer,int catId){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.QuestionsTable.COLUMN_QUESTION,question);
        values.put(Table.QuestionsTable.COLUMN_OPTION1,op1);
        values.put(Table.QuestionsTable.COLUMN_OPTION2,op2);
        values.put(Table.QuestionsTable.COLUMN_OPTION3,op3);
        values.put(Table.QuestionsTable.COLUMN_OPTION4,op4);
        values.put(Table.QuestionsTable.COLUMN_ANSWER,answer);
        values.put(Table.QuestionsTable.COLUMN_CATEGORY_ID,catId);
        long result = db.insert(Table.QuestionsTable.TABLE_NAME,null,values);

        if (result == -1) return false;
        else
            return true;

    }

    public boolean deleteQuestion(int id){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+Table.QuestionsTable.TABLE_NAME+" where " + Table.QuestionsTable._ID + " = ?",new String[]{String.valueOf(id)});
        if (c.getCount()>0){
            long result = db.delete(Table.QuestionsTable.TABLE_NAME,Table.QuestionsTable._ID +"=?",new String[]{String.valueOf(id)});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }

    }

    /*public boolean updateData(int id,String question,String op1,String op2,String op3,String op4,int answer,int catId){
        ContentValues values = new ContentValues();
        values.put(Table.QuestionsTable.COLUMN_QUESTION,question);
        values.put(Table.QuestionsTable.COLUMN_OPTION1,op1);
        values.put(Table.QuestionsTable.COLUMN_OPTION2,op2);
        values.put(Table.QuestionsTable.COLUMN_OPTION3,op3);
        values.put(Table.QuestionsTable.COLUMN_OPTION4,op4);
        values.put(Table.QuestionsTable.COLUMN_ANSWER,answer);
        values.put(Table.QuestionsTable.COLUMN_CATEGORY_ID,catId);
        Cursor c = db.rawQuery("select * from "+Table.QuestionsTable.TABLE_NAME+" where " + Table.QuestionsTable._ID + " = ?",new String[]{String.valueOf(id)});
        if (c.getCount()>0){
            long result = db.update(Table.QuestionsTable.TABLE_NAME,values,Table.QuestionsTable._ID +"=?",new String[]{String.valueOf(id)});

        if (result == -1) return false;
        else
            return true;
        }else {
            return false;
        }
    }*/
    //Tạo dữ liệu bảng câu hỏi
    private void CreateQuestions(){
        //Dữ liệu bảng question
 Question q1 = new Question(1,"Cuộc khai thác thuộc địa lần thứ hai (1919-1929) của thực dân Pháp ở Đông Dương được diễn ra trong hoàn cảnh nào?",
                "A. Nước Pháp đang chuyển sang giai đoạn chủ nghĩa đế quốc",
                "B. Nước Pháp bị thiệt hại nặng nề do cuộc chiến tranh xâm lược Việt Nam",
                "C. Nước Pháp bị thiệt hại nặng nề do cuộc chiến tranh thế giới thứ nhất (1914-1918)",
                "D. Tình hình kinh tế, chính trị ở Pháp ổn định", 3, 2);
        insertQuestion(q1.getQuestion(),q1.getOption1(),q1.getOption2(),q1.getOption3(),q1.getOption4(),q1.getAnswer(),q1.getCategoryID());
        Question q2 = new Question(2,"Thực dân Pháp tiến hành cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929) khi",
                "A. Hệ thống thuộc địa của chủ nghĩa đế quốc tan rã.",
                "B. Thế giới tư bản đang lâm vào khủng hoảng thừa.",
                "C. Cuộc chiến tranh thế giới thứ nhất kết thúc.",
                "D. Kinh tế các nước tư bản đang trên đà phát triển.", 3, 2);
        insertQuestion(q2.getQuestion(),q2.getOption1(),q2.getOption2(),q2.getOption3(),q2.getOption4(),q2.getAnswer(),q2.getCategoryID());
        Question q3 = new Question(3,"Ngành kinh tế nào được thực dân Pháp đầu tư nhiều nhất trong cuộc khai thác thuộc địa lần thứ hai (1919 – 1929) ở Đông Dương?",
                "A. Nông nghiệp", "B. Công nghiệp", "C. Tài chính- ngân hàng","D. Giao thông vận tải", 1, 2);
        insertQuestion(q3.getQuestion(),q3.getOption1(),q3.getOption2(),q3.getOption3(),q3.getOption4(),q3.getAnswer(),q3.getCategoryID());

        Question q4 = new Question(4,"Trong cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929), thực dân Pháp tập trung đầu tư vào",
                "A. Đồn điền cao su.", "B. Công nghiệp hóa chất.", "C. Công nghiệp luyện kim. ","D. Ngành chế tạo máy.", 1, 2);
        insertQuestion(q4.getQuestion(),q4.getOption1(),q4.getOption2(),q4.getOption3(),q4.getOption4(),q4.getAnswer(),q4.getCategoryID());

        Question q5 = new Question(5,"Trong cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929), thực dân Pháp chú trọng đầu tư vào",
                "A. Chế tạo máy.", "B. Công nghiệp luyện kim.", "C. Công nghiệp hóa chất.","D. Khai thác mỏ.", 4, 2);
        insertQuestion(q5.getQuestion(),q5.getOption1(),q5.getOption2(),q5.getOption3(),q5.getOption4(),q5.getAnswer(),q5.getCategoryID());

        Question q6 = new Question(6,"Trong cuộc khai thác thuộc địa lần thứ hai (1919), thực dân Pháp sử dụng biện pháp nào để tăng ngân sách Đông Dương?",
                "A. Mở rộng quy mô sản xuất", "B. Khuyến khích phát triển công nghiệp nhẹ", "C. Tăng thuế và cho vay lãi","D. Mở rộng trao đổi buôn bán", 3, 2);
        insertQuestion(q6.getQuestion(),q6.getOption1(),q6.getOption2(),q6.getOption3(),q6.getOption4(),q6.getAnswer(),q6.getCategoryID());
        Question q7 = new Question(7,"Giai cấp nào trong xã hội Việt Nam đầu thế kỉ XX có quan hệ gắn bó với giai cấp nông dân?",
                "A. Công nhân", "B. Địa chủ", "C. Tư sản","D. Tiểu tư sản", 1, 2);
        insertQuestion(q7.getQuestion(),q7.getOption1(),q7.getOption2(),q7.getOption3(),q7.getOption4(),q7.getAnswer(),q7.getCategoryID());
        Question q8 = new Question(8,"Sau chiến tranh thế giới thứ nhất, lực lượng xã hội có khả năng vươn lên nắm ngọn cờ lãnh đạo cách mạng Việt Nam là",
                "A. Đại địa chủ", "B. Trung địa chủ", "C. Tiểu địa chủ","D. Trung, tiểu địa chủ", 4, 2);
        insertQuestion(q8.getQuestion(),q8.getOption1(),q8.getOption2(),q8.getOption3(),q8.getOption4(),q8.getAnswer(),q8.getCategoryID());
        Question q9 = new Question(9,"Trung và tiểu địa chủ Việt Nam sau Chiến tranh thế giới thứ nhất là lực lượng",
                 "A. có tinh thần chống Pháp và tay sai. ", "B. làm tay sai cho Pháp.","C. bóc lột nông dân và làm tay sai cho Pháp.","D. thỏa hiệp với Pháp.", 1, 2);
        insertQuestion(q9.getQuestion(),q9.getOption1(),q9.getOption2(),q9.getOption3(),q9.getOption4(),q9.getAnswer(),q9.getCategoryID());
        Question q10 = new Question(10,"Ai là tác giả của chương chương trình khai thác thuộc địa lần thứ hai của thực dân Pháp ở Đông Dương?",
                "A. Pô-đu-me", "B. Anbe-xarô", "C. Pôn-bô","D. Va-ren", 2, 2);
        insertQuestion(q10.getQuestion(),q10.getOption1(),q10.getOption2(),q10.getOption3(),q10.getOption4(),q10.getAnswer(),q10.getCategoryID());
        Question q11 = new Question(11,"Hà Nội là thủ đô nước nào?",
                "A. Mỹ", "B. Cà Màu", "C.Nam Cực","D.Việt Nam", 4, 3);
        insertQuestion(q11.getQuestion(),q11.getOption1(),q11.getOption2(),q11.getOption3(),q11.getOption4(),q11.getAnswer(),q11.getCategoryID());
        Question q12 = new Question(12,"Trong câu “Thưa ông, chúng cháu ở Gia Lâm lên đấy ạ. Đi bốn năm hôm mới lên đến đây, vất vả quá!”. Câu nói “Thưa ông” thuộc thành phần gì của câu?",
                " A. Phụ chú", " B. Cảm thán", "C. Gọi đáp","D. Tình thái", 3, 1);
        insertQuestion(q12.getQuestion(),q12.getOption1(),q12.getOption2(),q12.getOption3(),q12.getOption4(),q12.getAnswer(),q12.getCategoryID());
        Question q13 = new Question(13,"Đỉnh núi Pan-xi-păng có độ cao bao nhiêu mét?",
                "a. 3134 mét.", "b. 3143 mét.", "c. 3314 mét.","a. 1 mét 2", 2, 3);
        insertQuestion(q13.getQuestion(),q13.getOption1(),q13.getOption2(),q13.getOption3(),q13.getOption4(),q13.getAnswer(),q13.getCategoryID());
    }


    //Hàm trả về dữ liệu chủ đề
    public List<Category> getDataCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+Table.CategoriesTable.TABLE_NAME,null);

        if (c.moveToFirst()){
            do {
                Category category = new Category();
                category.setId((c.getInt(c.getColumnIndexOrThrow(Table.CategoriesTable._ID))));
                category.setName(c.getString(c.getColumnIndexOrThrow(Table.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            }
            while (c.moveToNext());
        }
        c.close();
        return categoryList;

    }


    //Lấy dữ liệu câu hỏi và đáp án có id = id_catgory theo chủ đề đã chọn
    public ArrayList<Question> getQuestions(int catgoryID){

        ArrayList<Question> questionArrayList = new ArrayList<>();

        db = getReadableDatabase();
        String selection  = Table.QuestionsTable.COLUMN_CATEGORY_ID+" = ?";

        String[] selectionArg = new String[]{String.valueOf(catgoryID)};

        Cursor c = db.query(Table.QuestionsTable.TABLE_NAME,null,selection,selectionArg,null,
                null,null);
        if (c.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndexOrThrow(Table.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(Table.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndexOrThrow(Table.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndexOrThrow(Table.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndexOrThrow(Table.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndexOrThrow(Table.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswer(c.getInt(c.getColumnIndexOrThrow(Table.QuestionsTable.COLUMN_ANSWER)));
                question.setCategoryID(c.getInt(c.getColumnIndexOrThrow(Table.QuestionsTable.COLUMN_CATEGORY_ID)));

                questionArrayList.add(question);
            }while (c.moveToNext());
        }
            c.close();
            return questionArrayList;
    }


}
