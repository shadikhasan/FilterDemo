package com.example.filterdemo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends AppCompatActivity {


    Button clickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickButton = findViewById(R.id.clickButtonId);


        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog();
//                Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
//                startActivity(intent);
            }
        });

    }


    private void showAlertDialog() {

        SimpleRangeView rangeBar;
        TextView rangeBarTextViewId;
        RadioGroup zoneRadioGroup, optionalRentRadioGroup;

        ViewGroup viewGroup = findViewById(android.R.id.content);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_filter, viewGroup, false);

        rangeBar = view.findViewById(R.id.rangeViewId);
        rangeBarTextViewId = view.findViewById(R.id.rangeBarTextViewId);


        zoneRadioGroup = view.findViewById(R.id.zoneRadioGroupId);
        optionalRentRadioGroup = view.findViewById(R.id.optionalRentRadioGroupId);

        RadioButton rentRadioButton1 = view.findViewById(R.id.doubleRBId);

        rentRadioButton1.setChecked(true);

        builder.setView(view);

        builder.setCancelable(false);


        //range bar sections
        rangeBar.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NonNull SimpleRangeView simpleRangeView, int i, int i1) {
                rangeBarTextViewId.setText(String.valueOf(i) + " - " + String.valueOf(i1));
            }
        });

        rangeBar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NonNull SimpleRangeView simpleRangeView, int i) {
                rangeBarTextViewId.setText(String.valueOf(i));
            }

            @Override
            public void onEndRangeChanged(@NonNull SimpleRangeView simpleRangeView, int i) {
                rangeBarTextViewId.setText(String.valueOf(i));
            }
        });

        ArrayList<String> zone = new ArrayList<String>();
        zone.add("Binodpur");
        zone.add("Kazla");
        zone.add("Vodra");
        zone.add("Bornali");
        zone.add("Saheb Bazar");


        zoneRadioGroup.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < zone.size(); i++) {
            RadioButton zoneRadioButton = new RadioButton(this);
            zoneRadioButton.setId(View.generateViewId());
            zoneRadioButton.setText(zone.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            zoneRadioButton.setLayoutParams(params);
            zoneRadioGroup.addView(zoneRadioButton);
        }

       zoneRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               RadioButton zoneRadioButton = view.findViewById(checkedId);
               Toast.makeText(view.getContext(), zoneRadioButton.getText(), Toast.LENGTH_LONG).show();
           }
       });
        optionalRentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rentRadioButton = view.findViewById(checkedId);
                Toast.makeText(view.getContext(), rentRadioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        alertDialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
        alertDialog.show();


    }

}