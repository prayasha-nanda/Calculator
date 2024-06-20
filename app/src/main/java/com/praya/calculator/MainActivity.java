package com.praya.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDel, btnAC, btnPlus, btnMinus, btnDivide, btnMulti, btnEquals, btnDot;

    private TextView textViewResult, textViewHistory;

    int operation = -1;

    private String number = null;

    double firstNumber = 0;
    double lastNumber = 0;

    boolean operator = false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String currentResult = null;

    boolean dot = false;

    private final int DIVIDE = 0;
    private final int MULTIPLY = 1;
    private final int ADD = 2;
    private final int MINUS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);

        btnPlus = findViewById(R.id.buttonAdd);
        btnMinus = findViewById(R.id.buttonSub);
        btnMulti = findViewById(R.id.buttonMul);
        btnDivide = findViewById(R.id.buttonDiv);

        btnAC = findViewById(R.id.buttonAC);
        btnDel = findViewById(R.id.buttonDel);
        btnDot = findViewById(R.id.buttonPoint);
        btnEquals = findViewById(R.id.buttonEqual);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);

        btn0.setOnClickListener(v -> numberClick("0"));
        btn1.setOnClickListener(v -> numberClick("1"));
        btn2.setOnClickListener(v -> numberClick("2"));
        btn3.setOnClickListener(v -> numberClick("3"));
        btn4.setOnClickListener(v -> numberClick("4"));
        btn5.setOnClickListener(v -> numberClick("5"));
        btn6.setOnClickListener(v -> numberClick("6"));
        btn7.setOnClickListener(v -> numberClick("7"));
        btn8.setOnClickListener(v -> numberClick("8"));
        btn9.setOnClickListener(v -> numberClick("9"));

        btnDivide.setOnClickListener(v -> divide());
        btnMulti.setOnClickListener(v -> multiply());
        btnMinus.setOnClickListener(v -> subtract());
        btnPlus.setOnClickListener(v -> add());

        btnEquals.setOnClickListener(v -> equalTo(firstNumber, lastNumber, operation));
        btnAC.setOnClickListener(v -> allClear());
        btnDel.setOnClickListener(v -> delete());
        btnDot.setOnClickListener(v -> dot());

    }

        public void numberClick (String view)
        {
            if (number == null) {
                number = view;
            }
            else {
                number = number + view;

            }
            if (operator){
                lastNumber = Double.parseDouble(number);
            }
            else {
                firstNumber = Double.parseDouble(number);
            }
            textViewResult.setText(number);
            currentResult = textViewHistory.getText().toString();
            textViewHistory.setText(currentResult + view);
        }

    public void divide()
    {
        if (operator)
        {
            firstNumber = equalTo(firstNumber, lastNumber, operation);
        }
        operation = DIVIDE;
        currentResult = textViewHistory.getText().toString();
        textViewHistory.setText(currentResult + "/");
        number = null;
        operator = true;
    }

    public void multiply()
    {
        if (operator)
        {
            firstNumber = equalTo(firstNumber, lastNumber, operation);
        }
        operation = MULTIPLY;
        currentResult = textViewHistory.getText().toString();
        textViewHistory.setText(currentResult + "*");
        operator = true;
        number = null;
    }

    public void add()
    {

        if (operator)
        {
            firstNumber = equalTo(firstNumber, lastNumber, operation);
        }
        operation = ADD;
        currentResult = textViewHistory.getText().toString();
        textViewHistory.setText(currentResult + "+");
        operator = true;
        number = null;
    }

    public void subtract()
    {

        if (operator)
        {
            firstNumber = equalTo(firstNumber, lastNumber, operation);
        }
        operation = MINUS;
        currentResult = textViewHistory.getText().toString();
        textViewHistory.setText(currentResult + "-");
        operator = true;
        number = null;
    }

    public double equalTo (double firstNumber, double lastNumber, int operation)
    {
        switch(operation)
        {
            case DIVIDE:

                if (firstNumber == 0)
                {
                    textViewResult.setText("0");
                }
                else if (lastNumber == 0)
                {
                    textViewResult.setText("Cannot divide by 0");
                }
                else
                {
                    firstNumber = firstNumber / lastNumber;
                    textViewResult.setText(myFormatter.format(firstNumber));
                }
                break;

            case MULTIPLY:

                if (firstNumber == 0 || lastNumber == 0)
                {
                    textViewResult.setText("0");
                }
                else
                {
                    firstNumber = firstNumber * lastNumber;
                    textViewResult.setText(myFormatter.format(firstNumber));
                }
                break;

            case ADD:

                firstNumber = firstNumber + lastNumber;
                textViewResult.setText(myFormatter.format(firstNumber));
                break;

            case MINUS:

                firstNumber = firstNumber - lastNumber;
                textViewResult.setText(myFormatter.format(firstNumber));
                break;

            default:
                textViewResult.setText("0");
                break;
        }
        textViewHistory.setText(""+myFormatter.format(firstNumber));
        number = null;
        dot = false;
        operation = -1;
        operator = false;
        return firstNumber;
    }

    public void allClear() {

    firstNumber = 0;
    lastNumber = 0;
    number = null;
    operator = false;
    dot = false;
    textViewHistory.setText("");
    textViewResult.setText("0");

    }

    public void delete(){

        if (number!= null) {
            if (!number.equals("0") && number.length() > 1) {
                number = number.substring(0, number.length() - 1);
                if (number.contains(".")) {
                    dot = true;
                } else {
                    dot = false;
                }
                textViewResult.setText(number);
                if (operator){
                    lastNumber = Double.parseDouble(number);
                }
                else {
                    firstNumber = Double.parseDouble(number);
                }
            } else if (number.length() == 1 && !number.equals("0")) {
                number = null;
                dot = false;
                textViewResult.setText("0");

            }
            currentResult = textViewHistory.getText().toString();
            currentResult = currentResult.substring(0, currentResult.length() - 1);
            textViewHistory.setText(currentResult);
        }
    }

    public void dot(){
        if (!dot) {

            if (number == null) {
                number = "0.";
            }
            else {
                number = number + ".";
            }
            currentResult = textViewHistory.getText().toString();
            textViewHistory.setText(currentResult+".");

        }
        textViewResult.setText(number);
        dot = true;
    }

}