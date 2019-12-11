package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    //数字 Number 输入
    private int[] idNum = {R.id.txt0, R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4, R.id.txt5,
            R.id.txt6,
            R.id.txt7, R.id.txt8, R.id.txt9};
    private int[] idCal = {R.id.plus, R.id.minus, R.id.txtMul, R.id.div, R.id.dot};
    private Button[] buttonsCal = new Button[idCal.length];
    private Button[] buttonsNum = new Button[idNum.length];
    private Button buttonEqu; //=
    private Button buttonClear; // AC
    private Button buttonDel;
    private TextView textView;
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate);
        textView = (TextView)findViewById(R.id.txtView);
        textView.setText("");
        buttonEqu = (Button)findViewById(R.id.equ);
        buttonEqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=textView.getText().toString();
                Calculate c=new Calculate(str);
                textView.setText(c.singleEval()+"");
            }
        });
        buttonClear = (Button) findViewById(R.id.clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                text = "";
            }
        });
        buttonDel = (Button) findViewById(R.id.del);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView.getText().toString().isEmpty()) {
                    text = textView.getText().toString();
                    text = text.substring(0, text.length() - 1);
                    textView.setText(text);
                }
            }

        });
// 给数字键注册单击事件
        for (int i = 0; i < idNum.length; i++) {
            buttonsNum[i] = (Button) findViewById(idNum[i]);
            final String msg = buttonsNum[i].getText().toString();
            buttonsNum[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.append(msg);
                }
            });
        }
        // 给运算符和点注册单击事件
        for (int idcal = 0; idcal < idCal.length; idcal++) {
            buttonsCal[idcal] = (Button) findViewById(idCal[idcal]);
            buttonsCal[idcal].setOnClickListener(new
                    CalOnClick(buttonsCal[idcal].getText().toString()));
        }
    }

    private class CalOnClick implements View.OnClickListener {
        String msg;
        String[] calSymbol = {"+", "-", "*", "/", "."};

        public CalOnClick(String msg) {
            this.msg = msg;
        }
        @Override
        public void onClick(View v) {
            if (!textView.getText().toString().equals("")) {
                if (!isRepeat())
                    textView.append(msg);
            }
        }
        // 检查运算符是否重复输入
        private boolean isRepeat() {
            String[] arr = textView.getText().toString().split("");
            for (int i = 0; i < calSymbol.length; i++)
                if (arr[arr.length - 1].equals(calSymbol[i]))
                    return true;
            return false;
        }
    }
}