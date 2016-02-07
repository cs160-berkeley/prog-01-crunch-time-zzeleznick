package com.zeleznick.crunchtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ExerciseAdapter extends ArrayAdapter<Exercise> {

    Context context;
    int layoutResourceId;
    Exercise data[] = null;
    ArrayList<Float> raw_values = new ArrayList<>();


    public ExerciseAdapter(Context context, int layoutResourceId, Exercise[] data, ArrayList<Float> raw_values ) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.raw_values = raw_values;
    }

    public void update(int selected, String exercise, int inputValue){
        for (int i=0; i<data.length; i++){
            float floatValue = (raw_values.get(i) / raw_values.get(selected)) * inputValue;;
            // Log.i("value", "pre-cast value is: " + floatValue);
            int value = (int) floatValue;
            // Log.i("value", "post-cast value is: " + value);
            String label;
            if (i == 0) {
                label = "Calories";
            }
            else if(i>2) {
                label = "minutes";
            }
            else {
                label = "reps";
            }
            data[i].title = Integer.toString(value) + "  " + label;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        exHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new exHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (exHolder)row.getTag();
        }

        Exercise exercise = data[position];
        holder.txtTitle.setText(exercise.title);
        holder.imgIcon.setImageResource(exercise.icon);

        return row;
    }

    static class exHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}