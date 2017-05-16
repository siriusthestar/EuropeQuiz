package com.nevena.absudacity.europequiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        this method removes the keyboard when clicked anywhere outside EditText after the text is entered
        */
        final EditText capitalCity = (EditText) findViewById(R.id.capital_text);
        capitalCity.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(capitalCity.getWindowToken(), 0);
                    }
                }
        );

        /*
        this method removes focus (and cursor) from EditText when clicked anywhere else in the app
        */
        ((View) findViewById(R.id.activity_main)).setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        capitalCity.clearFocus();
                        return false;
                    }
                }
        );

    }

    /*
    here we check if correct answers are checked/entered and count the correct answers by adding 1 for each correct answer
     */
    public void sbmt(View v) {
        int correctAnswerCount = 0;

        EditText capitalCity = (EditText) findViewById(R.id.capital_text);
        String answer = capitalCity.getText().toString().trim();
        if (answer.equalsIgnoreCase("no")) correctAnswerCount++;

        RadioButton correctRadioOne = (RadioButton) findViewById(R.id.bratislava);
        if (correctRadioOne.isChecked()) correctAnswerCount++;

        CheckBox lichtensteinMonument = (CheckBox) findViewById(R.id.monument);
        CheckBox lichtensteinCountry = (CheckBox) findViewById(R.id.country);
        CheckBox lichtensteinMuseum = (CheckBox) findViewById(R.id.museum);
        CheckBox lichtensteinCastle = (CheckBox) findViewById(R.id.castle);
        if (
                !lichtensteinMonument.isChecked() &&
                        lichtensteinCountry.isChecked() &&
                        !lichtensteinMuseum.isChecked() &&
                        lichtensteinCastle.isChecked()
                )
            correctAnswerCount++;

        RadioButton correctRadioTwo = (RadioButton) findViewById(R.id.scotia);
        if (correctRadioTwo.isChecked()) correctAnswerCount++;

        RadioButton correctRadioThree = (RadioButton) findViewById(R.id.four);
        if (correctRadioThree.isChecked()) correctAnswerCount++;


        if (correctAnswerCount < 5) {
            displayToast(getString(R.string.toast_1) + " " + correctAnswerCount + " " + getString(R.string.toast_2));
        } else {
            displayToast(getString(R.string.toast_1) + " " + correctAnswerCount + " " + getString(R.string.toast_3));
        }
    }

    /*
    this method gives us test result with a toast message
     */
    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*
    this method resets all answers
     */
    public void rset(View v) {
        ((RadioGroup) findViewById(R.id.rg1)).clearCheck();
        ((EditText) findViewById(R.id.capital_text)).getText().clear();
        CheckBox monument = (CheckBox) findViewById(R.id.monument);
        monument.setChecked(false);
        CheckBox country = (CheckBox) findViewById(R.id.country);
        country.setChecked(false);
        CheckBox museum = (CheckBox) findViewById(R.id.museum);
        museum.setChecked(false);
        CheckBox castle = (CheckBox) findViewById(R.id.castle);
        castle.setChecked(false);
        ((RadioGroup) findViewById(R.id.rg2)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg3)).clearCheck();

            /*
    this method forces app to scroll to the top of the screen after reset
     */
        findViewById(R.id.activity_main).scrollTo(0, 0);

    }
}
