package com.dbest.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner mSpinner;
    private String mSelectedTemperature;
    private TextView mResultConverted;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.temperature,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);

        mSpinner.setOnItemSelectedListener(this);
        mResultConverted = findViewById(R.id.resultFromConversion);
        mResultConverted.setText(mSelectedTemperature);
    }
    public void convertTemp(View view){
        mSelectedTemperature = mSpinner.getSelectedItem().toString();
        if (mSelectedTemperature.equals("Fahrenheit")){
            calculateTemperature('F');
        }
        if (mSelectedTemperature.equals("Celsius")){
            calculateTemperature('C');
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void calculateTemperature(char character){
        mEditText = findViewById(R.id.editText);
        try {
            if (mEditText.getText() != null){
                double numberEntered = Double.parseDouble(mEditText.getText().toString());
                double result;
                String resultString;
                if (character == 'F'){
                    result = ((numberEntered - 32 ) * 5) / 9;
                    result = (int)(result * 10000)/ 10000.0;
                    resultString = String.valueOf(result) + "°C";
                }
                else if (character == 'C'){
                    result = ((numberEntered * 9) / 5) + 32;
                    result = (int)(result * 10000)/ 10000.0;
                    resultString = String.valueOf(result) + "°F";
                }
                else{
                    result = 0;
                    resultString = String.valueOf(result);
                }

                mResultConverted.setText(resultString + "");
            }
        } catch (NumberFormatException e){
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();
        }


    }

}
