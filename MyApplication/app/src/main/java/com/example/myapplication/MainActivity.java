package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    // CLEARボタンを押したときの処理
    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            recentOperator = R.id.button_equal;
            result = 0;
            isOperatorKeyPushed = false;

            textView.setText("");
            editText.setText("");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textview);
        editText = (EditText)findViewById(R.id.edittext);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(buttonListener);

        // 数字キーを押したらEditTextに数字が表示されるようにする
        // それぞれの数字ボタンにOnClickListenerを設定する
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        // 演算子キー
        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);

    }

    // 最近押された計算キー
    int recentOperator = R.id.button_equal;
    // 計算結果
    double result;
    // 計算機ーが押されたことを記憶
    boolean isOperatorKeyPushed;

    // 演算子キーを押したときの処理
    View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        @Override
        public void onClick (View view) {
            Button operatorButton = (Button) view;
            double value = Double.parseDouble(editText.getText().toString());

            // =(イコール)ボタンが押されたらvalueを計算結果に入れる
            // そうでない場合、計算してeditTextに計算結果を入れる
            if (recentOperator == R.id.button_equal) {
                result = value;
            } else {
                result = calc(recentOperator, result, value);
                editText.setText(String.valueOf(result));
            }

            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            isOperatorKeyPushed = true;
        }
    };

    // 数字キーを押したときの処理
    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;

            if(isOperatorKeyPushed == true) {
                editText.setText(button.getText());
            }else{
                // EditTextの後ろに文字を追加
                editText.append(button.getText());
            }

            isOperatorKeyPushed = false;
        }
    };

    // 計算メソッド
    double calc(int operator, double value1, double value2) {
        switch (operator) {
            case R.id.button_add:
                return value1 + value2;
            case R.id.button_subtract:
                return value1 - value2;
            case R.id.button_multiply:
                return value1 * value2;
            case R.id.button_divide:
                return value1 / value2;
            default:
                return value1;
        }
    }
}

