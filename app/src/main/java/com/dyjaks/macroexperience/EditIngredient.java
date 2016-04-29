package com.dyjaks.macroexperience;

import android.os.Bundle;
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

public class EditIngredient extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText ingName, ingServingSize, ingServing, intFat, ingSfat, ingmFat, ingPFat,
                    ingChol, ingSod, ingCarb, ingFiber, ingSug, ingPro;

    private TextInputLayout ilName, ilServing, ilServingSize, ilFat, ilSfat, ilMfat, ilpFat,
                            ilChol, ilSod, ilCarb, ilFiber, ilSug, ilPro;
    private Button btnSave;

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
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }
        if (!validateProtein()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (ingName.getText().toString().trim().isEmpty()) {
            ilName.setError("Name is required!");
            requestFocus(ingName);
            return false;
        } else {
            ilName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateProtein() {
        double p = Double.parseDouble(ingPro.getText().toString());
        if (p < 0) {
            ingPro.setError("Protein macro is required!");
            requestFocus(ingPro);
            return false;
        } else {
            ilPro.setErrorEnabled(false);
        }

        return true;
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
                    validateName();
                    break;
            }
        }
    }
}