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
import dz.phamtuanvan.btl_2022.activity.MainActivity_dele_student;
import dz.phamtuanvan.btl_2022.model.Student;
import dz.phamtuanvan.btl_2022.model.StudentAccount;

public class LoadStudent extends BaseAdapter {
    Context context;
    ArrayList<Student> studentArrayList;

    public LoadStudent(Context context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @Override
    public int getCount() {
        return this.studentArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_view2,null);

        TextView txt_msv = view.findViewById(R.id.tv_dssv);
        CheckBox cb_dele_sv = view.findViewById(R.id.cb_dele_student);

        Student studentAccount = studentArrayList.get(i);

        txt_msv.setText(studentAccount.getName());
        cb_dele_sv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MainActivity_dele_student.vitri = studentAccount.getId();
            }
        });

        return view;
    }
}
