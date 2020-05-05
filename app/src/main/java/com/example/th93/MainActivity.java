package com.example.th93;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText location;
    EditText day;
    EditText open;
    EditText close;
    Spinner spinner;
    TextView textView;
    Button button;
    readXML xml = readXML.getInstance();
    ArrayList<String> postnames = new ArrayList<String>();
    ArrayList<Smartpost> posts = new ArrayList<Smartpost>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String name;
        String country;
        String address;
        String availability;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        location = (EditText) findViewById(R.id.editTextL);
        day = (EditText) findViewById(R.id.editTextD);
        open = (EditText) findViewById(R.id.editTextT1);
        close = (EditText) findViewById(R.id.editTextT2);
        spinner = (Spinner) findViewById(R.id.spinner);
        button = findViewById(R.id.button);
        textView.setText("SmartPost App");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //////////////  Spinneri -> ////////////////////////////////////////////////////////////////////////////////////////////////

        //Luodaan ArrayAdapter sijainnille//
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, xml.getPosts());
        //Määritellään spinnerin asettelutyyli//
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //lisätään adapteri spinneriin//
        spinner.setAdapter(adapterLocation);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (parent.getItemAtPosition(position).equals("Select SmartPost")){
                 System.out.println("Toimii");
             } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    posts = xml.getInfo();

                    for(int i=0; i < posts.size(); i++) {
                        if (posts.get(i).getName().equals(item)){
                            textView.setText(item +" " +posts.get(i).getLocation() + " " +posts.get(i).getAddress() + " " + posts.get(i).getAvailability());

                        }
                    }
             }
         }

         @Override
            public void onNothingSelected(AdapterView<?> parent) {
         }
        });
     }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////// button ////////////////////////////////////////////////////////////////////////////////////////

    /* public void readXML(View v) {
        postnames = xml.getPosts();
    } */
}


