package com.zeleznick.crunchtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView welcomeText;
    // TextView inputValueText;
    TextView unitsText;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*
        // 0.   Create list view
        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(listAdapter);
        */

        Weather weather_data[] = new Weather[]
                {
                        new Weather(R.drawable.pushup, "Push-ups"),
                        new Weather(R.drawable.situp, "Sit-ups"),
                        new Weather(R.drawable.jumping_jacks, "Jumping Jacks"),
                        new Weather(R.drawable.jogging, "Jogging"),
                        new Weather(R.drawable.walking, "Walking")
                };

        WeatherAdapter wAdapter = new WeatherAdapter(this,
                R.layout.listview_item_row, weather_data);


        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(wAdapter);

        Spinner spinner = (Spinner) findViewById(R.id.my_spinner);
        // 1.   Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises_array, android.R.layout.simple_spinner_item);

        // 2.   Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 3.   Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        // An item was selected
        // welcomeText = (TextView) findViewById(R.id.welcome);
        // inputValueText = (TextView) findViewById(R.id.input_amount);

        unitsText = (TextView) findViewById(R.id.current_units);
        String item = parent.getItemAtPosition(position).toString();
        unitsText.setText(item);

        // inputValueText.setHint(item);
        /* mGenerateTestButton = (Button) findViewById(R.id.testButton);
        mGenerateTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index = "10"; //new Random().nextInt(10);
                mtestText.setText(index);
        */
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
