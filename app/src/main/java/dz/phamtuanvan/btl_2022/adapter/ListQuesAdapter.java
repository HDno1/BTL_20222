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
import dz.phamtuanvan.btl_2022.activity.LoadQues;
import dz.phamtuanvan.btl_2022.model.Question;

public class ListQuesAdapter extends BaseAdapter {
    Context context;
    ArrayList<Question> questionArrayList;


    public ListQuesAdapter(Context context, ArrayList<Question> questionArrayList) {
        this.context = context;
        this.questionArrayList = questionArrayList;
    }


    @Override
    public int getCount() {
        return questionArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return questionArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.ques_view,null);
        TextView tv_ques = view.findViewById(R.id.tv_ques);
        CheckBox cb_dele_ques = view.findViewById(R.id.cb_delete_ques);

        Question question = questionArrayList.get(i);

        tv_ques.setText(question.getQuestion());

        cb_dele_ques.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /*LoadQues.vitri = question.getId();*/
                if (cb_dele_ques.isChecked()){
                    //gọi array list bên ListViewlv2
                    LoadQues.vitri.add(question.getId());
                }else {
                    for (int x:LoadQues.vitri){
                        if (x == i){
                            LoadQues.vitri.remove(x);
                        }
                    }
                }
            }
        });
        return view;
    }
}
