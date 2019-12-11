
package com.example.calculator;

import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.TextViewCompat;

public class Calculate {
    private String expression, operator;
    private double num1,num2;
    public Calculate(String expression) {
        this.expression = expression;
    }
    public void convert(){
        String[] calSymbol = {"+", "-", "*", "/"};
        for (int i = 0; i < calSymbol.length; i++){
            int n=expression.indexOf(calSymbol[i]);
            if( n!=-1){
                operator =calSymbol[i];
                // num1=Double.valueOf(expression.substring(0,n));
                // num2=Double.valueOf(expression.substring(n+1));
                String[] numstr=expression.split("["+operator +"]");
                num1=Double.valueOf(numstr[0]);
                num2=Double.valueOf(numstr[1]);
                break;
            }
        }
    }

    public double singleEval(){
        convert();
        double result=0.0;
        switch(operator){
            case "+":
                result =num1+num2; break;
            case "-":
                result =num1-num2;break;
            case "*":
                result =num1*num2;break;
            case "/": {
                
                result = num1 / num2;
                break;
            }
        }
        return result;
    }
    @Override
    public String toString() {
        return expression + "="+ singleEval();
    }
}

