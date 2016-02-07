package com.zeleznick.crunchtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class MainActivity extends FragmentActivity {
    //TextView welcomeText;
    EditText inputValue;
    TextView unitsText;
    ListView listView1;
    Button  mSubmitButton;
    int selected;
    // new for swiper
    static final int ITEMS = 3;
    SwiperAdapter mSwiperAdapter;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_pager);

        // ** if using AppCompatActivity:
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You can do it!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        // 0.   Create SwiperAdapter
        mSwiperAdapter = new SwiperAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mSwiperAdapter);
        /*
        Button button = (Button) findViewById(R.id.first);
        button .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(0);
            }
        });
        button = (Button) findViewById(R.id.last);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(ITEMS - 1);
            }
        }); */
    }

    public static class SwiperAdapter extends FragmentStatePagerAdapter {
        public SwiperAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            Log.i("get_item", "Called with position: " + position);
            switch (position) {
                case 0: // Fragment # 0 - This will show default exercises
                    return ExerciseListFragment.init(position);
                case 1: // Fragment # 1 - This will show extra exercises
                    return AltExerciseListFragment.init(position);
                default:// Fragment # 2-9 - Will show silly exersises
                    return ExerciseListFragment.init(position);
            }
        }
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
