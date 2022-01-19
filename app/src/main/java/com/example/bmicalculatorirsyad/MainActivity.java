package com.example.bmicalculatorirsyad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME1 = "dataWeight";
    private static final String FILE_NAME2 = "dataHeight";

    EditText weight, height;
    TextView resulttext;
    String calculation, BMIresult, HealthRisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttext = findViewById(R.id.result);
        resulttext = findViewById(R.id.risk);

        FileInputStream fis1 = null;

        try {
            fis1 = openFileInput(FILE_NAME1);
            InputStreamReader isr = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                weight.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fis2 = null;

        try {
            fis2 = openFileInput(FILE_NAME2);
            InputStreamReader isr = new InputStreamReader(fis2);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                height.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis2 != null){
                try {
                    fis2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void aboutpage(View view){
        Intent intent = new Intent(this,aboutUs.class);
        startActivity(intent);
    }

    public void calculateBMI (View view) {
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();

        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float bmi=0;

        if(heightValue>=5) {
            bmi = weightValue / ((heightValue/100) * (heightValue/100));

        }
        else{
            bmi = weightValue / (heightValue * heightValue);
        }
        if(bmi<=18.4){
            BMIresult = "Underweight";
            HealthRisk = "Malnutrition risk";
        }
        else if(bmi>18.4 && bmi<=24.9) {
            BMIresult = "Normal Weight";
            HealthRisk = "Low risk";
        }
        else if(bmi>24.9 && bmi<=29.9){
            BMIresult = "Overweight";
            HealthRisk = "Enchanced risk";
        }
        else if(bmi>29.9 && bmi<=34.9){
            BMIresult = "Moderately obese";
            HealthRisk = "Medium risk";
        }
        else if(bmi>34.9 && bmi<=39.9){
            BMIresult = "Severely obese";
            HealthRisk = "High risk";
        }
        else{
            BMIresult = "Very severely obese";
            HealthRisk = "Very high risk";
        }

        calculation = df.format(bmi)+"\n"+BMIresult+"\n"+HealthRisk;

        resulttext.setText(calculation);

        String txt1 = weight.getText().toString();
        String txt2 = height.getText().toString();
        FileOutputStream fos1 = null;
        try {
            fos1 = openFileOutput(FILE_NAME1, MODE_PRIVATE);
            fos1.write(txt1.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos1!=null){
                try {
                    fos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileOutputStream fos2 = null;
        try {
            fos2 = openFileOutput(FILE_NAME2, MODE_PRIVATE);
            fos2.write(txt2.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos2 != null) {
                try {
                    fos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
