package com.example.try2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public String convertFrom;
    public String convertTo;
    public Spinner fromDrop;
    public Spinner toDrop;

    public class Crypto {
        private String name;
        private double rate;


        public Crypto(String name, double rate) {
            this.name = name;
            this.rate = rate;
        }
    }

        Crypto btcToUsd = new Crypto("btcToUsd", 3415.84);
        Crypto btcToEur = new Crypto("btcToEur", 3006.49);
        Crypto btcToJpy = new Crypto("btcToJpy", 375503);

        Crypto ltcToUsd = new Crypto("ltcToUsd", 0.03009234);
        Crypto ltcToEur = new Crypto("ltcToEur", 0.03418955);
        Crypto ltcToJpy = new Crypto("ltcToJpy", 0.00027374);

        Crypto linxToUsd = new Crypto("linxToUsd", 431.14338363);
        Crypto linxToEur = new Crypto("linxToEur", 489.84548711);
        Crypto linxToJpy = new Crypto("linxToJpy", 3.92198111);

        Crypto btcLtc = new Crypto("btcLtc", 102.79051221);
        Crypto btcLinx = new Crypto("btcLinx", 1472715);
        Crypto ltcLinx = new Crypto("ltcLinx", 14327);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromDrop = findViewById(R.id.fromSpinner);
        ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(this, R.array.fromDrop_array,
                android.R.layout.simple_spinner_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromDrop.setAdapter(fromAdapter);

        toDrop = findViewById(R.id.toSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.toDrop_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toDrop.setAdapter(adapter);

        Button refresh = findViewById(R.id.refreshButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText from = findViewById(R.id.fromInput);
                from.setText(null);

                EditText to = findViewById(R.id.to);
                to.setText(null);
            }
        });


        Button convert = findViewById(R.id.button);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText from = findViewById(R.id.fromInput);
                String input = from.getText().toString();
                double amt = Double.parseDouble(input);

                convertFrom = fromDrop.getSelectedItem().toString();
                convertTo = toDrop.getSelectedItem().toString();
                System.out.println("convert from: " + convertFrom);
                System.out.println("convert to: " + convertTo);

                String output = selection(convertFrom, convertTo, amt);

                TextView showOutput = findViewById(R.id.to);
                showOutput.setText(output);

            }
        });
    }

    public String conversion(double amt, Crypto crypto, boolean mult){
        String output;
        double converted;
        if (mult){
            converted = amt * crypto.rate;
            output = Double.toString(converted);
            return output;
        }

        converted = crypto.rate/amt;
        output = Double.toString(converted);
        return output;
    }

    public String selection(String from, String to, double amt) {

        if (from.contains("USD")) {
            if (to.contains("BTC")) {
                return conversion(amt, btcToUsd, true);
            } else if (to.contains("LTC")) {
                return conversion(amt, ltcToUsd, true);
            } else if (to.contains("LINX")) {
                return conversion(amt, linxToUsd, true);
            }
            else if (to.contains("USD")){
                String amount = Double.toString(amt);
                return amount;
            }
        }
        else if (from.contains("EUR")){
            if (to.contains("BTC")) {
                return conversion(amt, btcToEur, true);
            } else if (to.contains("LTC")) {
                return conversion(amt, ltcToEur, true);
            }
            else if (from.contains("EUR") && to.contains("LINX")) {
                return conversion(amt, linxToEur, true);
            }
            else if (to.contains("EUR")){
                String amount = Double.toString(amt);
                return amount;
            }
        }

        else if (from.contains("JPY")){
            if (to.contains("BTC")) {
                return conversion(amt, btcToJpy, true);
            }else if (to.contains("LTC")) {
                return conversion(amt, ltcToJpy, true);
            }
            else if (to.contains("LINX")) {
                return conversion(amt, linxToJpy, true);
            }
            else if (to.contains("JPY")){
                String amount = Double.toString(amt);
                return amount;
            }
        }

        if (from.contains("BTC")) {
            if (to.contains("USD")) {
                return conversion(amt, btcToUsd, false);
            } else if (to.contains("EUR")) {
                return conversion(amt, btcToEur, false);
            } else if (to.contains("JPY")) {
                return conversion(amt, btcToJpy, false);
            }
            else if (to.contains("LTC")) {
                return conversion(amt, btcLtc, true);
            }
            else if (to.contains("LINX")) {
                return conversion(amt, btcLinx, true);
            }
            else if (to.contains("BTC")){
                String amount = Double.toString(amt);
                return amount;
            }
        }
        else if (from.contains("LTC")){
            if (to.contains("USD")) {
                return conversion(amt, ltcToUsd, false);
            } else if (to.contains("EUR")) {
                return conversion(amt, ltcToEur, false);
            }
            else if (to.contains("JPY")) {
                return conversion(amt, ltcToJpy, false);
            }
            else if (to.contains("BTC")) {
                return conversion(amt, btcLtc, false);
            }
            else if (to.contains("LINX")) {
                return conversion(amt, ltcLinx, true);
            }
            else if (to.contains("LTC")){
                String amount = Double.toString(amt);
                return amount;
            }
        }

        else if (from.contains("LINX")){
            if (to.contains("USD")) {
                return conversion(amt, linxToUsd, false);
            }else if (to.contains("EUR")) {
                return conversion(amt, linxToEur, false);
            }
            else if (to.contains("JPY")) {
                return conversion(amt, linxToJpy, false);
            }
            else if (to.contains("BTC")) {
                return conversion(amt, btcLinx, false);
            }
            else if (to.contains("LTC")) {
                return conversion(amt, ltcLinx, false);
            }
            else if (to.contains("LINX")){
                String amount = Double.toString(amt);
                return amount;
            }
        }


        return "Error";
    }
}


