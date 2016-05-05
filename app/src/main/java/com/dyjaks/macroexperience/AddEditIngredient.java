package com.dyjaks.macroexperience;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditIngredient extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText ingName, ingServingSize, ingServing, intFat, ingSfat, ingmFat, ingPFat,
                    ingChol, ingSod, ingCarb, ingFiber, ingSug, ingPro;

    private TextInputLayout ilName, ilServing, ilServingSize, ilFat, ilSfat, ilMfat, ilpFat,
                            ilChol, ilSod, ilCarb, ilFiber, ilSug, ilPro;
    private Button btnSave;
    private IngredientDataSource idb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredient);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ilName = (TextInputLayout) findViewById(R.id.input_layout_name);
        ilServing = (TextInputLayout) findViewById(R.id.input_layout_servings);
        ilServingSize = (TextInputLayout) findViewById(R.id.input_layout_servingsize);
        ilFat = (TextInputLayout) findViewById(R.id.input_layout_tFat);
        ilSfat = (TextInputLayout) findViewById(R.id.input_layout_sFat);
        ilMfat = (TextInputLayout) findViewById(R.id.input_layout_mFat);
        ilpFat = (TextInputLayout) findViewById(R.id.input_layout_pFat);
        ilChol = (TextInputLayout) findViewById(R.id.input_layout_chol);
        ilSod = (TextInputLayout) findViewById(R.id.input_layout_sod);
        ilCarb = (TextInputLayout) findViewById(R.id.input_layout_carb);
        ilFiber = (TextInputLayout) findViewById(R.id.input_layout_fiber);
        ilSug = (TextInputLayout) findViewById(R.id.input_layout_sugar);
        ilPro = (TextInputLayout) findViewById(R.id.input_layout_protein);

        ingName = (EditText) findViewById(R.id.input_edit_name);
        ingServing = (EditText) findViewById(R.id.input_edit_servings);
        ingServingSize = (EditText) findViewById(R.id.input_edit_servingsize);
        intFat = (EditText) findViewById(R.id.input_edit_tFat);
        ingSfat = (EditText) findViewById(R.id.input_edit_sFat);
        ingmFat = (EditText) findViewById(R.id.input_edit_mFat);
        ingPFat = (EditText) findViewById(R.id.input_edit_pFat);
        ingChol = (EditText) findViewById(R.id.input_edit_chol);
        ingSod = (EditText) findViewById(R.id.input_edit_sod);
        ingCarb = (EditText) findViewById(R.id.input_edit_carb);
        ingFiber = (EditText) findViewById(R.id.input_edit_fiber);
        ingSug = (EditText) findViewById(R.id.input_edit_sugar);
        ingPro = (EditText) findViewById(R.id.input_edit_protein);

        ingName.addTextChangedListener(new MyTextWatcher(ingName));
        ingServing.addTextChangedListener(new MyTextWatcher(ingServing));
        ingServingSize.addTextChangedListener(new MyTextWatcher(ingServingSize));
        intFat.addTextChangedListener(new MyTextWatcher(intFat));
        ingSfat.addTextChangedListener(new MyTextWatcher(ingSfat));
        ingmFat.addTextChangedListener(new MyTextWatcher(ingmFat));
        ingPFat.addTextChangedListener(new MyTextWatcher(ingPFat));
        ingChol.addTextChangedListener(new MyTextWatcher(ingChol));
        ingSod.addTextChangedListener(new MyTextWatcher(ingSod));
        ingCarb.addTextChangedListener(new MyTextWatcher(ingCarb));
        ingFiber.addTextChangedListener(new MyTextWatcher(ingFiber));
        ingSug.addTextChangedListener(new MyTextWatcher(ingSug));
        ingPro.addTextChangedListener(new MyTextWatcher(ingPro));


        btnSave = (Button) findViewById(R.id.btn_save_meal);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        idb = new IngredientDataSource();
        idb.initializeInstance(MacroSqliteOpenHelper.getInstance(this));
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateString(ingName, ilName, "Ingredient Name")) {
            return;
        }
        if (!validateString(ingServingSize, ilServingSize, "Serving Size")) {
            return;
        }
        if (!validateNonZeroMacro(ingServing, ilServing, "Serving")) {
            return;
        }
        if (!validateNonZeroMacro(ingPro, ilPro, "Protein")) {
            return;
        }
        if (!validateNonZeroMacro(ingCarb, ilCarb, "Carb")) {
            return;
        }
        if (!validateNonZeroMacro(intFat, ilFat, "Fat")) {
            return;
        }

        idb.createIngredient(ingName.getText().toString(),
                ingServing.getText().toString(),
                ingServingSize.getText().toString(),
                ingPro.getText().toString(),
                intFat.getText().toString(),
                ingSfat.getText().toString(),
                ingmFat.getText().toString(),
                ingPFat.getText().toString(),
                ingChol.getText().toString(),
                ingCarb.getText().toString(),
                ingFiber.getText().toString(),
                ingSug.getText().toString()
        );

        finish();
    }

    private Ingredient addOptionIngredients(Ingredient i ) {
        if (!ingSug.getText().toString().trim().isEmpty())
            i.SetSugar(Double.parseDouble(ingSug.getText().toString()));
        if (!ingFiber.getText().toString().trim().isEmpty())
            i.SetFiber(Double.parseDouble(ingFiber.getText().toString()));
        if (!ingChol.getText().toString().trim().isEmpty())
            i.SetChol(Double.parseDouble(ingChol.getText().toString()));
        if (!ingmFat.getText().toString().trim().isEmpty())
            i.SetMonoFat(Double.parseDouble(ingmFat.getText().toString()));
        if (!ingPFat.getText().toString().trim().isEmpty())
            i.SetPolyFat(Double.parseDouble(ingPFat.getText().toString()));
        if (!ingSfat.getText().toString().trim().isEmpty())
            i.SetSatFat(Double.parseDouble(ingSfat.getText().toString()));

        return i;
    }

    private boolean validateString(EditText mText, TextInputLayout mLayout, String mName) {
        if (mText.getText().toString().trim().isEmpty()) {
            mLayout.setError(mName + " is required!");
            requestFocus(mText);
            return false;
        } else {
            mLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNonZeroMacro(EditText mText, TextInputLayout mLayout, String mName) {
        try {
            double p = Double.parseDouble(mText.getText().toString());
            if (p < 0) {
                mLayout.setError(mName + " is required!");
                requestFocus(mText);
                return false;
            } else {
                mLayout.setErrorEnabled(false);
            }

            return true;
        } catch (Exception e) {
            mText.setError(mName + " macro is required!");
            requestFocus(mText);
            return false;
        }
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_edit_name:
                    validateString(ingName, ilName, "Ingredient Name");
                    break;
                case R.id.input_edit_servingsize:
                    validateString(ingServingSize, ilServingSize, "Serving Size");
                    break;
                case R.id.input_edit_servings:
                    validateNonZeroMacro(ingServing, ilServing, "Serving Name");
                    break;
                case R.id.input_edit_tFat:
                    validateNonZeroMacro(intFat, ilFat, "Total fat");
                    break;
                case R.id.input_edit_chol:
                    validateNonZeroMacro(ingCarb, ilCarb, "Carb");
                    break;
                case R.id.input_edit_protein:
                    validateNonZeroMacro(ingPro, ilPro, "Protein");
                    break;
            }
        }
    }
}