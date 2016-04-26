package com.dyjaks.macroexperience;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class DailySummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        // BodyWeight tab
        TabHost.TabSpec spec = host.newTabSpec(getString(R.string.bw_tab));
        spec.setContent(R.id.bodyWeightLayout);
        spec.setIndicator(getString(R.string.bw_tab));
        host.addTab(spec);

        // Meals tab
        spec = host.newTabSpec(getString(R.string.meal_tab));
        spec.setContent(R.id.mealLayout);
        spec.setIndicator(getString(R.string.meal_tab));
        host.addTab(spec);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        List<Food> foodItems = new ArrayList<Food>();
        Food a = new Food("pizza", "slice", "1", 8.8, 20.0, 50.0, 10.0, 20.0);
        Food b = new Food("pasta", "bowl", "2/3", 8.8, 20.0, 50.0, 10.0, 20.0);

        foodItems.add(a);
        foodItems.add(b);

        IngredientListAdapter ila = new IngredientListAdapter(this, this.getApplicationContext(), foodItems);
        ((ListView) findViewById(R.id.mealListView)).setAdapter(ila);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_summary, menu);
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
