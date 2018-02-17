package com.example.himanshu.employeedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button btnAdd,btnDelete,btnUpdate,btnView;
    EditText etID,empname,etsalary;
 TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
empname=findViewById(R.id.empname);
btnView=findViewById(R.id.btnView);
etID=findViewById(R.id.etID);
btnAdd=findViewById(R.id.btnAdd);
btnDelete=findViewById(R.id.btnDelete);
btnUpdate=findViewById(R.id.btnUpdate);
tvResult=findViewById(R.id.tvResult);
etsalary=findViewById(R.id.etsalary);
final myDataHelper dbh=new myDataHelper(this);
btnAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      int id =Integer.parseInt(etID.getText().toString());
      String name=empname.getText().toString();
        int salary= Integer.parseInt(etsalary.getText().toString());
      if(etID.getText().toString().length()==0)
      {etID.setError("");
       etID.requestFocus(); return;           }
      else
      {dbh.addEmployee(id,name,salary);


          }
    }
});
btnView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        int id =Integer.parseInt(etID.getText().toString());
        String name=empname.getText().toString();
int salary= Integer.parseInt(etsalary.getText().toString());
        if(etID.getText().toString().length()==0)
        {etID.setError("");
            etID.requestFocus(); return;           }
        else {
            dbh.addEmployee(id, name,salary);
            String result = dbh.getAllEmpl();
            //tvResult.setText(result);//
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            i.putExtra("result", result);

            startActivity(i);
            finish();
        }
        }
});
btnUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int id =Integer.parseInt(etID.getText().toString());
        String name=empname.getText().toString();

        if(etID.getText().toString().length()==0)
        {etID.setError("");
            etID.requestFocus(); return;           }
        else
        {dbh.UpdateEmployee(id,name);
            String result=dbh.getAllEmpl();
            tvResult.setText(result);}

    }
});

btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int id= Integer.parseInt(etID.getText().toString());
        if(etID.getText().toString().length()==0)
        {etID.setError("enter valid id");
          etID.requestFocus();
        return;}

        else
        {dbh.deleteEmployee(id);
        String result=dbh.getAllEmpl();
        tvResult.setText(result);

        }


    }
});



    }
}
