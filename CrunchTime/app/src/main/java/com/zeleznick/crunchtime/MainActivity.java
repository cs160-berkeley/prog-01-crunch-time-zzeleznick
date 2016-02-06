package com.zeleznick.crunchtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //TextView welcomeText;
    EditText inputValue;
    TextView unitsText;
    ListView listView1;
    Button  mSubmitButton;
    int selected;
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

        // 0.   Init weather class instance and create adapter
        final Exercise exercise_data[] = new Exercise[]
                {
                        new Exercise(R.drawable.flame, "Calories"),
                        new Exercise(R.drawable.pushup, "Push-ups"),
                        new Exercise(R.drawable.situp, "Sit-ups"),
                        new Exercise(R.drawable.jumping_jacks, "Jumping Jacks"),
                        new Exercise(R.drawable.jogging, "Jogging"),
                        new Exercise(R.drawable.walking, "Walking")
                };

        final exerciseAdapter wAdapter = new exerciseAdapter(this,
                R.layout.listview_item_row, exercise_data);

        // 1.   Create list view and link with adapter
        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(wAdapter);

        final Spinner spinner = (Spinner) findViewById(R.id.my_spinner);
        // 2.   Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises_array, android.R.layout.simple_spinner_item);

        // 3.   Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 4.   Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //5.    Create button that updates the values
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mSubmitButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index = "10"; //new Random().nextInt(10);
                Log.i("my_tag", "Updating");
                String currentExercise = spinner.getSelectedItem().toString();
                Log.i("my_tag", "Found exercise " + currentExercise);
                inputValue = (EditText) findViewById(R.id.input_amount);
                String valueString = inputValue.getText().toString();
                int intValue = 10;
                Log.i("my_tag", "Input amount: " + valueString);
                try
                {
                    intValue = Integer.parseInt(valueString);
                    if (intValue <= 0) { intValue = 10; };
                }
                catch (NumberFormatException e)
                {
                    Log.i("error", "Caught error with amount");
                }
                Log.i("selected", "found id of " + selected);
                wAdapter.update(selected, currentExercise, intValue);
                wAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        // An item was selected
        unitsText = (TextView) findViewById(R.id.current_units);
        String item = parent.getItemAtPosition(position).toString();
        if (item.equalsIgnoreCase("Jogging") || item.equalsIgnoreCase("Walking")) {
            unitsText.setText("minutes");
        }
        else {unitsText.setText("reps");}
        selected = position;
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
