package org.study.noneeditableedittext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.pattern)
    EditText pattern;
    @BindView(R.id.string)
    EditText string;
    @BindView(R.id.name)
    CustomEditText name;

    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.match)
    protected void onMatch(){
        String strPattern = pattern.getText().toString().trim();
        String strGivenString = string.getText().toString().trim();
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(strGivenString);
        name.setText("string "+ matcher.matches());
    }


    @OnClick(R.id.pickPattern)
    protected void onPick(){
        Intent intent = new Intent(this,StringPatternActivity.class);
        intent.putExtra("selectedPosition",selectedPosition);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK && data != null){
                selectedPosition = data.getIntExtra("selectedPosition",-1);
                pattern.setText(data.getStringExtra("pattern"));

            }else if (resultCode == Activity.RESULT_CANCELED){
                selectedPosition = -1;
            }
        }

    }
}
