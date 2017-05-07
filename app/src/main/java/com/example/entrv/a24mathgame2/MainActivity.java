package com.example.entrv.a24mathgame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

public class MainActivity extends AppCompatActivity {
    List<Integer> r_number;
    CheckBox button_1,button_2,button_3,button_4 ;
    RadioButton button_plus,button_minus,button_multiple,button_divider ;
    SegmentedGroup segmented31;
    Button button_reset,button_replay;
    int button_one_click = 0;
    String oldButtonText;
    CompoundButton oldcb;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRnumber();
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(onCheckedChangeListener);
        button_1 = (CheckBox) findViewById(R.id.button_1);
        button_1.setOnCheckedChangeListener(onCheckedChangeListener2);
        button_2 = (CheckBox) findViewById(R.id.button_2);
        button_2.setOnCheckedChangeListener(onCheckedChangeListener2);
        button_3 = (CheckBox) findViewById(R.id.button_3);
        button_3.setOnCheckedChangeListener(onCheckedChangeListener2);
        button_4 = (CheckBox) findViewById(R.id.button_4);
        button_4.setOnCheckedChangeListener(onCheckedChangeListener2);

        button_plus = (RadioButton) findViewById(R.id.button_plus);
        button_minus = (RadioButton) findViewById(R.id.button_minus);
        button_multiple = (RadioButton) findViewById(R.id.button_multiple);
        button_divider = (RadioButton) findViewById(R.id.button_divider);

        segmented31 = (SegmentedGroup) findViewById(R.id.segmented31);
        segmented31.setOnCheckedChangeListener(onCheckedChangeListener);

        button_1.setText(r_number.get(0).toString());
        button_2.setText(r_number.get(1).toString());
        button_3.setText(r_number.get(2).toString());
        button_4.setText(r_number.get(3).toString());

        button_reset = (Button) findViewById(R.id.button_reset);
        button_replay = (Button) findViewById(R.id.button_replay);

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initReset();

            }
        });
        button_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRnumber();
                initReset();
            }
        });
    }

    private void initReset() {
        button_1.setText(r_number.get(0).toString());
        button_2.setText(r_number.get(1).toString());
        button_3.setText(r_number.get(2).toString());
        button_4.setText(r_number.get(3).toString());
        oldcb = null;
        button_1.setAlpha(1);
        button_2.setAlpha(1);
        button_3.setAlpha(1);
        button_4.setAlpha(1);
        button_1.setChecked(false);
        button_2.setChecked(false);
        button_3.setChecked(false);
        button_4.setChecked(false);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener2 = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.d("entrv","selected234:" );
            if(isChecked){ count++; }
            else{ count--; }

            if (count == 2 ) {
                if (button_plus.isChecked() || button_minus.isChecked() || button_multiple.isChecked() || button_divider.isChecked()) {
                    int resultText = 0;
                    if (button_plus.isChecked()) {

                        resultText = Integer.parseInt((String) oldcb.getText()) + Integer.parseInt((String) buttonView.getText());
                    } else if (button_minus.isChecked()) {
                        resultText = Integer.parseInt( (String) oldcb.getText()) - Integer.parseInt( (String) buttonView.getText());

                    } else if (button_multiple.isChecked()) {
                        resultText = Integer.parseInt( (String) oldcb.getText()) * Integer.parseInt( (String) buttonView.getText());

                    } else if (button_divider.isChecked()) {
                        resultText = Integer.parseInt( (String) oldcb.getText()) / Integer.parseInt( (String) buttonView.getText());

                    } else {
                        // return;
                    }

                    oldcb.setAlpha(0);
                    buttonView.setText(String.valueOf(resultText));
                    oldcb.setChecked(false);
                    oldcb = buttonView;
                    float nowVisible = button_1.getAlpha() + button_2.getAlpha() + button_3.getAlpha() +button_4.getAlpha();

                    if (nowVisible == 1 ) {
                        if (resultText == 24) {
                            Log.d("entrv","right A");
                        } else {
                            Log.d("entrv","wrong A");
                        }
                    }
                } else{
                    oldcb.setChecked(false);

                    oldcb = buttonView;
                }
            } else {
                oldButtonText = (String) buttonView.getText();
                oldcb = buttonView;
            }
            Log.d("entrv","selected234:" + count );
        }
    };


    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            CompoundButton cb = (CompoundButton) group.findViewById(checkedId);
            Log.d("entrv","selected234:" );
            if ( button_one_click == 1 && (button_1.isChecked() || button_2.isChecked() || button_3.isChecked() || button_4.isChecked())) {

                if (button_plus.isChecked() || button_minus.isChecked() || button_multiple.isChecked() || button_divider.isChecked()) {
                    Log.d("entrv","selected234:" + oldcb + ">>" + button_1);
                    int resultText = 0;
                    if (oldcb == button_1) {
                       if (button_2.isChecked() || button_3.isChecked() || button_4.isChecked()) {
                           oldcb.setAlpha(0);
                           if (button_plus.isChecked()) {

                                   resultText = Integer.parseInt((String) oldcb.getText()) + Integer.parseInt((String) cb.getText());
                           } else if (button_minus.isChecked()) {
                               resultText = Integer.parseInt( (String) oldcb.getText()) - Integer.parseInt( (String) cb.getText());

                           } else if (button_multiple.isChecked()) {
                               resultText = Integer.parseInt( (String) oldcb.getText()) * Integer.parseInt( (String) cb.getText());

                           } else if (button_divider.isChecked()) {
                               resultText = Integer.parseInt( (String) oldcb.getText()) / Integer.parseInt( (String) cb.getText());

                           } else {
                              // return;
                           }



                           Log.d("entrv","resultText:" + resultText );
                           cb.setText(String.valueOf(resultText));
                           button_plus.setChecked(false);
                           button_minus.setChecked(false);
                           button_multiple.setChecked(false);
                           button_divider.setChecked(false);

                       }
                    }
                    if (oldcb == button_2) {
                        if (button_1.isChecked() || button_3.isChecked() || button_4.isChecked()) {
                            oldcb.setAlpha(0);
                            if (button_plus.isChecked()) {

                                resultText = Integer.parseInt((String) oldcb.getText()) + Integer.parseInt((String) cb.getText());
                            } else if (button_minus.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) - Integer.parseInt( (String) cb.getText());

                            } else if (button_multiple.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) * Integer.parseInt( (String) cb.getText());

                            } else if (button_divider.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) / Integer.parseInt( (String) cb.getText());

                            } else {
                                //return;
                            }
                            cb.setText(String.valueOf(resultText));
                            button_plus.setChecked(false);
                            button_minus.setChecked(false);
                            button_multiple.setChecked(false);
                            button_divider.setChecked(false);


                        }
                    }
                    if (oldcb == button_3) {
                        if (button_1.isChecked() || button_2.isChecked() || button_4.isChecked()) {
                            oldcb.setAlpha(0);
                            if (button_plus.isChecked()) {

                                resultText = Integer.parseInt((String) oldcb.getText()) + Integer.parseInt((String) cb.getText());
                            } else if (button_minus.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) - Integer.parseInt( (String) cb.getText());

                            } else if (button_multiple.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) * Integer.parseInt( (String) cb.getText());

                            } else if (button_divider.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) / Integer.parseInt( (String) cb.getText());

                            } else {
                                //return;
                            }
                            cb.setText(String.valueOf(resultText));
                            button_plus.setChecked(false);
                            button_minus.setChecked(false);
                            button_multiple.setChecked(false);
                            button_divider.setChecked(false);



                        }
                    }
                    if (oldcb == button_4) {
                        if (button_1.isChecked() || button_2.isChecked() || button_4.isChecked()) {
                            oldcb.setAlpha(0);
                            if (button_plus.isChecked()) {

                                resultText = Integer.parseInt((String) oldcb.getText()) + Integer.parseInt((String) cb.getText());
                            } else if (button_minus.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) - Integer.parseInt( (String) cb.getText());

                            } else if (button_multiple.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) * Integer.parseInt( (String) cb.getText());

                            } else if (button_divider.isChecked()) {
                                resultText = Integer.parseInt( (String) oldcb.getText()) / Integer.parseInt( (String) cb.getText());

                            } else {
                                //return;
                            }
                            cb.setText(String.valueOf(resultText));
                            button_plus.setChecked(false);
                            button_minus.setChecked(false);
                            button_multiple.setChecked(false);
                            button_divider.setChecked(false);


                        }
                    }

                } else {
                    oldButtonText = (String) cb.getText();
                    oldcb = cb;
                    return;
                }
            }
            if (button_1.isChecked() || button_2.isChecked() || button_3.isChecked() || button_4.isChecked()) {


            }

            switch (checkedId) {
                case R.id.button_plus:

                    break;
                case R.id.button_minus:

                    break;
                case R.id.button_multiple:

                    break;
                case R.id.button_divider:

                    break;

                default:
                    button_one_click = 1;
                    oldButtonText = (String) cb.getText();
                    oldcb = cb;
                    // Nothing to do
            }
            if(cb!=null && cb.isChecked()){
                Log.d("entrv","selected:" + cb.getText());
            }
        }
    };

    private void initRnumber() {
        List<Integer> d = new ArrayList<Integer>();
        d.add(1);d.add(2);d.add(3);d.add(4);
        r_number = new ArrayList<Integer>();
        for (int el : d) {

            r_number.add( (int) Math.floor(Math.random() * 8 + 1) );

        }
        Log.d("entrv","" + r_number);
    }
}
