package com.example.controlviews;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    SharedPreferences sharePref;


    ProgressBar progressBar;
    EditText editTextProgressBar;
    TextView textViewProgressBar;

    SeekBar seekBar;
    EditText editTextSeekBar;
    TextView textViewSeekBar;

    ToggleButton toggleButton1, toggleButton2, toggleButton3, toggleButton4;
    CheckBox checkBox;

    RadioButton radioButton1, radioButton2, radioButton3;
    RadioGroup radioGroup;
    Button radioButtonClear;

    Switch switch1, switch2, switch3;

    Button save, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextProgressBar = (EditText) findViewById(R.id.inputProgress);
        textViewProgressBar = (TextView) findViewById(R.id.textProgress);
        editTextProgressBar.setText(progressBar.getMax() + "");
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        editTextSeekBar = (EditText) findViewById(R.id.inputSeek);
        textViewSeekBar = (TextView) findViewById(R.id.textSeek);
        editTextSeekBar.setText(seekBar.getMax() + "");
        textViewSeekBar.setText("Seekbar: " + seekBar.getProgress());
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
        toggleButton4 = (ToggleButton) findViewById(R.id.toggleButton4);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButtonClear = (Button) findViewById(R.id.radioButtonClear);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);

        editTextProgressBar.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        checkBox.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        toggleButton1.setOnClickListener(this);
        toggleButton2.setOnClickListener(this);
        toggleButton3.setOnClickListener(this);
        toggleButton4.setOnClickListener(this);
        radioGroup.setOnClickListener(this);
        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);
        switch3.setOnCheckedChangeListener(this);
        save.setOnClickListener(this);
        load.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                saveInfo();
                break;
            case R.id.load:
                loadInfo();
                break;
            case R.id.checkBox:
                if (checkBox.isChecked()) {
                    toggleButton1.setEnabled(true);
                    toggleButton2.setEnabled(true);
                    toggleButton3.setEnabled(true);
                    toggleButton4.setEnabled(true);
                } else {
                    toggleButton1.setEnabled(false);
                    toggleButton2.setEnabled(false);
                    toggleButton3.setEnabled(false);
                    toggleButton4.setEnabled(false);
                }
                break;
            case R.id.toggleButton1:
                if (toggleButton1.isChecked()) {
                    progressBar.setProgress(progressBar.getProgress() * 2);
                    textViewProgressBar.setText("Progress: " + progressBar.getProgress());
                }
                break;
            case R.id.toggleButton2:
                if (toggleButton2.isChecked()) {
                    progressBar.setProgress(progressBar.getProgress() * 4);
                    textViewProgressBar.setText("Progress: " + progressBar.getProgress());
                }
                break;
            case R.id.toggleButton3:
                if (toggleButton3.isChecked()) {
                    progressBar.setProgress(progressBar.getProgress() * 6);
                    textViewProgressBar.setText("Progress: " + progressBar.getProgress());
                }
                break;
            case R.id.toggleButton4:
                if (toggleButton4.isChecked()) {
                    progressBar.setProgress(progressBar.getProgress() * 8);
                    textViewProgressBar.setText("Progress: " + progressBar.getProgress());
                }
                break;
        }
    }

    private void saveInfo() {
        SharedPreferences sharePref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();
        Integer valueOfProgress = progressBar.getProgress();
        editor.putInt("progressValue", valueOfProgress);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        editor.apply();
    }
    private void loadInfo() {
        sharePref = getPreferences(MODE_PRIVATE);
        Integer valueOfProgress = sharePref.getInt("progressValue",999);
        progressBar.setProgress(valueOfProgress);
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
        textViewProgressBar.setText("Progress loaded: " + valueOfProgress);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d("aa", "aa");
        switch (checkedId) {
            case R.id.radioButton1:
                progressBar.setProgress(progressBar.getProgress() / 2);
                textViewProgressBar.setText("Progress: " + progressBar.getProgress());
                break;
            case R.id.radioButton2:
                progressBar.setProgress(progressBar.getProgress() / 4);
                textViewProgressBar.setText("Progress: " + progressBar.getProgress());
                break;
            case R.id.radioButton3:
                progressBar.setProgress(progressBar.getProgress() / 8);
                textViewProgressBar.setText("Progress: " + progressBar.getProgress());
                break;
            case R.id.radioButtonClear:
                radioButton1.setChecked(false);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                break;

        }
    }

    int progressValue;
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressValue = progress;
        textViewSeekBar.setText("Seekbar: " + progressValue);
        progressBar.setProgress(progressValue);
        textViewProgressBar.setText("Progress: " + progressValue);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        progressBar.setProgress(progressValue);

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        textViewSeekBar.setText("Seekbar: " + progressValue);
        progressBar.setProgress(progressValue);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(switch1.isChecked()){
            progressBar.setProgress(progressBar.getProgress() + 10);
            textViewProgressBar.setText("Progress: " + progressBar.getProgress());
        }
        if(switch2.isChecked()){
            progressBar.setProgress(progressBar.getProgress() + 20);
            textViewProgressBar.setText("Progress: " + progressBar.getProgress());

        }if(switch3.isChecked()) {
            progressBar.setProgress(progressBar.getProgress() + 30);
            textViewProgressBar.setText("Progress: " + progressBar.getProgress());
        }
    }
}