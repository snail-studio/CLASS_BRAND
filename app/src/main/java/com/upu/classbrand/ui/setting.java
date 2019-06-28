package com.upu.classbrand.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.upu.classbrand.R;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.util.PreferencesUtil;

public class setting extends BaseActivity {
    private Spinner spinnerClass;
    private Button btnSetOk;
    private TextView txtUserschool;
    private int position=0;
    private int itemsum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        txtUserschool=findViewById(R.id.txtUserschool);
        txtUserschool.setText(publicParam.schoolname);
        if (publicParam.classid.equals("0")){
            itemsum=publicParam.classes.size()+1;
        }else{
            itemsum=publicParam.classes.size();
        }
        spinnerClass=findViewById(R.id.spinnerClass);
        String[] spinnerItems=new String[itemsum];
        if (publicParam.classid.equals("0")){
            spinnerItems[0]="请选择使用班级";
            for (int i=1;i< publicParam.classes.size()+1;i++){
                spinnerItems[i]=publicParam.classes.get(i-1).getClassname();
            }
        }else{
            for (int i=0;i< publicParam.classes.size();i++){
                spinnerItems[i]=publicParam.classes.get(i).getClassname();
                if (publicParam.classname.equals(spinnerItems[i])){
                    position=i;
                }
            }
        }

        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_select,spinnerItems);
        adapter.setDropDownViewResource(R.layout.item_drop);
        spinnerClass.setAdapter(adapter);
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
                publicParam.classname=adapter.getItem(position);
                publicParam.classid=getclassid(publicParam.classname);
                PreferencesUtil.putString("classname",publicParam.classname);
                PreferencesUtil.putString("classid",publicParam.classid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (publicParam.classid.equals("0")){
            spinnerClass.setSelection(-1);
        }else{
            spinnerClass.setSelection(position);
        }
        btnSetOk=findViewById(R.id.btnSetOK);
        btnSetOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(setting.this,register.class);
                startActivity(intent1);
                finish();
            }
        });

    }
    private String getclassid(String classname){
        for (int i = 0;  i<publicParam.classes.size(); i++) {
            if (publicParam.classes.get(i).getClassname().equals(classname)){
                return publicParam.classes.get(i).getClassid();
            }
        }
        return "0";
    }
}
