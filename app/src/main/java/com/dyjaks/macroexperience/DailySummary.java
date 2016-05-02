package com.dyjaks.macroexperience;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class DailySummary extends AppCompatActivity {
    private MacroSqliteOpenHelper dbHelper;
    private IngredientDataSource ids;
    private List<Meal> meals;
    private List<Ingredient> ingList;
    private RecyclerView rv;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_summary);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_ingredient);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DailySummary.this, EditIngredient.class));
            }
        });

        dbHelper = dbHelper.getInstance(this);
        ids = new IngredientDataSource();
        ids.initializeInstance(dbHelper);
        ingList = ids.getAllIngredients();

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

    @Override
    protected void onResume() {
        //ids.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        ids.getInstance().close();
        super.onPause();
    }


    private void initData() {
        meals = new ArrayList<>();
        Meal ml = new Meal("Lunch", ingList);
        meals.add(ml);
    }

    private void initAdapter() {
        MealAdapter ca = new MealAdapter(meals);
        rv.setAdapter(ca);
    }
}
