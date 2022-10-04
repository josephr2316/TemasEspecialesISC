package com.pucmm.myfirstapp;


import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {

    TextView tvDate;
    EditText etName;
    EditText etSurname;
    Button bSend;
    Button bClear;
    Spinner eGenero;
    RadioButton yesB;
    RadioButton noB;
    CheckBox java;
    CheckBox python;
    CheckBox js;
    CheckBox goland;
    CheckBox cc;
    CheckBox csharp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bSend = (Button) findViewById(R.id.bSend);
        bClear = (Button) findViewById(R.id.bClear);
        tvDate = (TextView) findViewById(R.id.edDatePicker);
        etName = (EditText) findViewById(R.id.name);
        etSurname = (EditText) findViewById(R.id.surname);
        eGenero = (Spinner) findViewById(R.id.spinner);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        final int year = calendar.get (Calendar.YEAR);
        final int month = calendar.get (Calendar.MONTH);
        final int day = calendar.get (Calendar.DAY_OF_MONTH);
        yesB = (RadioButton) findViewById(R.id.r_yes);
        noB = (RadioButton) findViewById(R.id.r_no);
        java = (CheckBox) findViewById(R.id.cb_java);
        python = (CheckBox) findViewById(R.id.cb_python);
        js = (CheckBox) findViewById(R.id.cb_js);
        goland = (CheckBox) findViewById(R.id.cb_goland);
        cc = (CheckBox) findViewById(R.id.cb_ccplus);
        csharp = (CheckBox) findViewById(R.id.cb_csharp);
        tvDate.setText(dateFormat.format(calendar.getTime()));
        yesB.setChecked(true);

        noB.setOnClickListener(view ->{
            java.setChecked(false);
            java.setClickable(false);
            python.setChecked(false);
            python.setClickable(false);
            js.setChecked(false);
            js.setClickable(false);
            goland.setChecked(false);
            goland.setClickable(false);
            cc.setChecked(false);
            cc.setClickable(false);
            csharp.setChecked(false);
            csharp.setClickable(false);

        });
        yesB.setOnClickListener(view ->{
            java.setClickable(true);
            python.setClickable(true);
            js.setClickable(true);
            goland.setClickable(true);
            cc.setClickable(true);
            csharp.setClickable(true);
        });
        bSend.setOnClickListener(view ->  {
            Intent dataActivity = new Intent(MainActivity.this, DataActivity.class);
            String name = etName.getText().toString();
            String surName = etSurname.getText().toString();
            String genero  = eGenero.getSelectedItem().toString();
            String birthday = tvDate.getText().toString();
            String sendText;

            if (name.isEmpty() || surName.isEmpty())
            {
                if (name.isEmpty() && !surName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes introducir un nombre", Toast.LENGTH_LONG).show();
                }
                if (surName.isEmpty() && !name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes introducir un apellido", Toast.LENGTH_LONG).show();

                }
                if (name.isEmpty() && surName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes introducir un nombre y un apellido", Toast.LENGTH_LONG).show();
                }
            }
            else {
                sendText = "Hola! Mi nombre es: " + name + " " + surName + ".\n\n" + "Soy " + genero.toLowerCase();
                sendText += ", y naci en la fecha " + birthday + ".\n\n";

                if (yesB.isChecked())
                {
                    int cbCounter = 0;
                    String newString = "";
                    sendText += "Me gusta programar.";

                    if (java.isChecked())
                    {
                        newString += " Java,";
                        cbCounter++;
                    }
                    if (python.isChecked())
                    {
                        newString += " Python,";
                        cbCounter++;
                    }
                    if (js.isChecked())
                    {
                        newString += " Js,";
                        cbCounter++;
                    }
                    if (goland.isChecked())
                    {
                        newString += " Go Land,";
                        cbCounter++;
                    }
                    if (cc.isChecked())
                    {
                        newString += " C/C++,";
                        cbCounter++;
                    }
                    if (csharp.isChecked())
                    {
                        newString += " C#,";
                        cbCounter++;
                    }
                    if (cbCounter == 0) {
                        Toast.makeText(MainActivity.this,"Debe seleccionar por lo menos un lenguage",Toast.LENGTH_LONG).show();
                    }
                    else if (cbCounter ==1) {
                        newString = newString.replaceFirst(".$","");
                        sendText += " Mi lenguaje favorito es:" + newString +".\n";
                        dataActivity.putExtra("dataP", sendText);
                        startActivity(dataActivity);
                    }
                    else{
                        newString = newString.replaceFirst(".$","");
                        sendText += " Mis lenguajes favoritos  son:" + newString +".\n";
                        dataActivity.putExtra("dataP", sendText);
                        startActivity(dataActivity);
                    }
                }
                else if(java.isChecked() || python.isChecked() || js.isChecked() || goland.isChecked() || cc.isChecked() || csharp.isChecked()){
                    Toast.makeText(MainActivity.this,"No puede seleccionar ningun lenguaje la casilla No esta marcada",Toast.LENGTH_LONG).show();

                }
                else {
                    sendText += " No me gusta programar.";
                    dataActivity.putExtra("dataP", sendText);
                    startActivity(dataActivity);
                }

            }
        });

        tvDate.setOnClickListener(view ->  {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        tvDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
        });
        bClear.setOnClickListener(view ->  {
            etName.setText("");
            etSurname.setText("");
            eGenero.setSelection(0);
            tvDate.setText(dateFormat.format(calendar.getTime()));
            yesB.setChecked(true);
            java.setChecked(false);
            python.setChecked(false);
            js.setChecked(false);
            goland.setChecked(false);
            cc.setChecked(false);
            csharp.setChecked(false);
            java.setClickable(true);
            python.setClickable(true);
            js.setClickable(true);
            goland.setClickable(true);
            cc.setClickable(true);
            csharp.setClickable(true);
        });
    }


/*
 private void SendActivity (View view){
     String namePro = findViewById(R.id.name).toString();
     String surnamePro = findViewById(R.id.surname).toString();
     Intent dataActivity = new Intent(MainActivity.this, DataActivity.class);
     startActivity(dataActivity);
 }

*/
}