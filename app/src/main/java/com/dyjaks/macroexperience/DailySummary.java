package com.dyjaks.macroexperience;

import android.app.Activity;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class DailySummary extends Activity {
    private List<Meal> meals;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_summary);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rv = ((RecyclerView)findViewById(R.id.mealRecyclerView));
        rv.setHasFixedSize(false);
        rv.setLayoutManager(llm);

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

        initData();
        initAdapter();
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


    private void initData() {
        meals = new ArrayList<>();
        List<Ingredient> iList = new ArrayList<>();
        List<Ingredient> iList2 = new ArrayList<>();
        Ingredient a = new Ingredient("pizza", "slice", "1", 8.8, 20.0, 50.0, 10.0, 20.0);
        Ingredient b = new Ingredient("pasta", "bowl", "2/3", 8.8, 20.0, 50.0, 10.0, 20.0);
        iList.add(a);
        Meal ml = new Meal("Lunch", iList);
        iList2.add(a);
        iList2.add(b);
        Meal ml2 = new Meal("Dinner", iList2);
        meals.add(ml);
        meals.add(ml2);
    }

    private void initAdapter() {
        MealAdapter ca = new MealAdapter(meals);
        rv.setAdapter(ca);
    }
}
