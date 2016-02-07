package com.zeleznick.crunchtime;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseListFragment extends ListFragment {
    int fragNum;
    EditText inputValue;
    TextView unitsText;
    ListView listView1;
    Button  mSubmitButton;
    int selected;
    private ExerciseAdapter mAdapter;

    final Exercise exercise_data_base[] = new Exercise[]
            {
                new Exercise(R.drawable.flame, "Calories"),
                new Exercise(R.drawable.pushup, "Pushups"),
                new Exercise(R.drawable.situp, "Situps"),
                new Exercise(R.drawable.jumping_jacks, "Jumping Jacks"),
                new Exercise(R.drawable.jogging, "Jogging"),
                new Exercise(R.drawable.walking, "Walking")
            };
    ArrayList<Float> base_values = new ArrayList<>();

    public ArrayList<Float> makeBase() {
        base_values.add(0, 100f);
        base_values.add(1, 350f);
        base_values.add(2, 200f);
        base_values.add(3, 10f);
        base_values.add(4, 12f);
        base_values.add(5, 20f);
        return base_values;
    }


    static ExerciseListFragment init(int val) {
        ExerciseListFragment exList = new ExerciseListFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        exList.setArguments(args);
        return exList;
    }

    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
        Log.i("init_tag", "Found bundle value: " + fragNum);
        base_values = makeBase();
        mAdapter = new ExerciseAdapter(getActivity(), R.layout.listview_item_row, exercise_data_base, base_values);
    }

    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.content_main,
                container, false);

        listView1 = (ListView) rootView.findViewById(android.R.id.list);
        // Log.i("my_tag", "Checking for listview: " + listView1);

        // 1.   Create a spinnner to fill and select values
        final Spinner spinner = (Spinner)  rootView.findViewById(R.id.my_spinner);
        // Log.i("my_tag", "Creating Spinner " + listView1);

        // 2.   Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.exercises_array_base, android.R.layout.simple_spinner_item);
        // 3.   Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 4.   Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected
                unitsText = (TextView) rootView.findViewById(R.id.current_units);
                String item = parent.getItemAtPosition(position).toString();
                if (item.equalsIgnoreCase("Pushups") || item.equalsIgnoreCase("Situps")
                        || item.equalsIgnoreCase("Pullups") || item.equalsIgnoreCase("Squats") ) {
                    unitsText.setText("reps");
                }
                else if (item.equalsIgnoreCase("Calories")) {
                    unitsText.setText("calories");
                }
                else {unitsText.setText("minutes");}
                selected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        mSubmitButton = (Button) rootView.findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index = "10"; //new Random().nextInt(10);
                Log.i("my_tag", "Updating");
                String currentExercise = spinner.getSelectedItem().toString();
                Log.i("my_tag", "Found exercise " + currentExercise);
                inputValue = (EditText) rootView.findViewById(R.id.input_amount);
                String valueString = inputValue.getText().toString();
                int intValue = 10;
                Log.i("my_tag", "Input amount: " + valueString);
                try {
                    intValue = Integer.parseInt(valueString);
                    if (intValue <= 0) {
                        intValue = 10;
                    }
                    ;
                } catch (NumberFormatException e) {
                    Log.i("error", "Caught error with amount");
                }
                Log.i("selected", "found id of " + selected);
                mAdapter.update(selected, currentExercise, intValue);
                mAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Log.i("my_tag", "Checking for mAdapter: " + mAdapter);
        Log.i("my_tag", "Current fragment number: " + fragNum);
        listView1.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("Zach FragmentList", "Item clicked: " + id);
    }
}

