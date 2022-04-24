package com.example.filterdemo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
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


    int min = 1000, max = 2000;
    String selectedZone;
    String selectedSeatType = "Double Seat";
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


        LinearLayout advancedFilterLayout;
        CheckBox advancedFilterCheckBox;
        ImageView filterDialogCancelButton;
        Button filterDialogApplyButton;
        SimpleRangeView filterDialogRangeBar;
        TextView filterDialogRangeBarTextView;
        RadioGroup filterDialogZoneRadioGroup, filterDialogRentRadioGroup;

        ViewGroup viewGroup = findViewById(android.R.id.content);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_filter, viewGroup, false);

        advancedFilterLayout = view.findViewById(R.id.advancedFilterLayoutId);
        advancedFilterCheckBox = view.findViewById(R.id.advancedFilterId);
        filterDialogRangeBar = view.findViewById(R.id.rangeViewId);
        filterDialogRangeBarTextView = view.findViewById(R.id.rangeBarTextViewId);

        filterDialogApplyButton = view.findViewById(R.id.applyButtonAlertDialogId);
        filterDialogCancelButton = view.findViewById(R.id.cancelDialogId);

        filterDialogZoneRadioGroup = view.findViewById(R.id.zoneRadioGroupId);
        filterDialogRentRadioGroup = view.findViewById(R.id.optionalRentRadioGroupId);

        RadioButton rentRadioButton1 = view.findViewById(R.id.doubleRBId);

        filterDialogZoneRadioGroup.setOrientation(LinearLayout.VERTICAL);

        rentRadioButton1.setChecked(true);
        builder.setView(view);
        builder.setCancelable(false);

        //zone radioGroup sections
        ArrayList<String> zone = new ArrayList<String>();
        zone.add("Binodpur");
        zone.add("Kazla");
        zone.add("Vodra");
        zone.add("Bornali");


        for (int i = 0; i < zone.size(); i++) {
            RadioButton zoneRadioButton = new RadioButton(this);
            zoneRadioButton.setId(View.generateViewId());
            zoneRadioButton.setText(zone.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            zoneRadioButton.setLayoutParams(params);
            filterDialogZoneRadioGroup.addView(zoneRadioButton);
        }


        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        alertDialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
        alertDialog.show();


        //listener sections

        advancedFilterCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    advancedFilterLayout.setVisibility(View.VISIBLE);
                }
                else {
                    advancedFilterLayout.setVisibility(View.GONE);
                }
            }
        });

        filterDialogZoneRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton zoneRadioButton = view.findViewById(checkedId);
                selectedZone = zoneRadioButton.getText().toString();
                Log.i("TAG", zoneRadioButton.getText().toString());
            }
        });
        filterDialogRentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rentRadioButton = view.findViewById(checkedId);
                selectedSeatType = rentRadioButton.getText().toString();
                Log.i("TAG", rentRadioButton.getText().toString());
            }
        });

        //range bar sections
        filterDialogRangeBar.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NonNull SimpleRangeView simpleRangeView, int i, int i1) {
                filterDialogRangeBarTextView.setText(String.valueOf(i) + " - " + String.valueOf(i1));
                min = i;
                max = i1;
            }
        });

        filterDialogRangeBar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NonNull SimpleRangeView simpleRangeView, int i) {
                filterDialogRangeBarTextView.setText("min = " + String.valueOf(i));
            }

            @Override
            public void onEndRangeChanged(@NonNull SimpleRangeView simpleRangeView, int i) {
                filterDialogRangeBarTextView.setText("max = " + String.valueOf(i));
            }
        });
        filterDialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        filterDialogApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(filterDialogZoneRadioGroup.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getApplicationContext(), "Select a zone",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(advancedFilterCheckBox.isChecked())
                    {
                        Toast.makeText(getApplicationContext(),"Checked "+selectedZone + " " + selectedSeatType + " " + min +" " + max,Toast.LENGTH_SHORT).show();
                    } else if(!advancedFilterCheckBox.isChecked())
                    {
                        Toast.makeText(getApplicationContext(),"not Checked " + selectedZone,Toast.LENGTH_SHORT).show();
                    }

                    alertDialog.dismiss();
                }
            }
        });


    }

}