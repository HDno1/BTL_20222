package dz.phamtuanvan.btl_2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.activity.MainActivity_List_SVTEST;
import dz.phamtuanvan.btl_2022.model.Student;

public class ListStudentTestAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> studentArrayList;

    public ListStudentTestAdapter(Context context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.studentArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_view_list_student_test,null);
        TextView tv_msv =(TextView) view.findViewById(R.id.tv_msv);
        TextView tv_score = (TextView) view.findViewById(R.id.tv_score);
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkBox);

        Student student =studentArrayList.get(i);
        tv_msv.setText("Tên sinh viên: "+student.getName());
        tv_score.setText(" "+"Điểm: "+student.getScore()+" ");

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()){
                    //gọi array list bên ListViewlv2
                    MainActivity_List_SVTEST.vitri.add(student.getId());
                }else {
                    for (int x:MainActivity_List_SVTEST.vitri){
                        if (x == i){
                            MainActivity_List_SVTEST.vitri.remove(x);
                        }
                    }
                }
            }
        });
        return view;
    }
}
