package com.mobile.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String num1;
    private String num2;
    private String operacion;
    private EditText editText;
    private LogicaNegocio logicaNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.calculatorGrid);
        ArrayList<Button> listaBotones = this.getAllButtons(gridLayout);
        this.num1 = "";
        this.num2 = "";
        this.operacion = "";
        this.logicaNegocio = LogicaNegocio.getInstance();
        this.editText = (EditText) findViewById(R.id.editText);
        for (Button b : listaBotones){
            b.setOnClickListener(this);
        }
    }

    private ArrayList<Button> getAllButtons(GridLayout layout){
        ArrayList<Button> listaBotones = new ArrayList<Button>();
        for (int index = 0; index < layout.getChildCount(); index++){
            View element = layout.getChildAt(index);
            if (element instanceof Button){
                listaBotones.add((Button) element);
            }
        }
        return listaBotones;
    }

    private void refreshText(String text){
        this.editText.setText(text);
    }

    private boolean validatePoint(String number){
        return !number.contains(".");
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonTxt = button.getText().toString();
        String resultado;

        if(buttonTxt.isEmpty()){
            return;
        }
        if (this.num1.isEmpty() && this.num2.isEmpty()){
            this.editText.setText("");
        }
        if (buttonTxt.matches("[0-9]")){
            if (this.operacion.isEmpty() && this.num2.isEmpty()){
                this.num1 += buttonTxt;
                this.editText.setText(this.num1);
            }
            else{
                this.num2 += buttonTxt;
                refreshText(this.num2);
            }
        }
        else if(buttonTxt.matches("[\\-+/*]")){
            if (!this.num2.isEmpty()){
                resultado = logicaNegocio.compute(Float.parseFloat(this.num1), Float.parseFloat(this.num2), this.operacion );
                this.operacion = buttonTxt;
                this.num1 = resultado;
                this.num2 = "";
                refreshText(this.num2);
            }else{
                this.operacion = buttonTxt;
                refreshText(this.num2);
            }
        } else if (buttonTxt.equals("=")){
            if (!this.num2.isEmpty()){
                resultado = logicaNegocio.compute(Float.parseFloat(this.num1), Float.parseFloat(this.num2), this.operacion );
                this.operacion = "";
                this.num1 = "";
                this.num2 = "";
                refreshText(resultado);
            }
        }else if (buttonTxt.equals("DEL")){
            this.num1 = "";
            this.num2 = "";
            this.operacion = "";
            refreshText("");
        }else if(buttonTxt.equals("C")){
            if (!this.num2.isEmpty()){
                this.num2 = this.num2.substring(0, this.num2.length() - 1);
                refreshText(this.num2);
            }else if (!this.num1.isEmpty()){
                this.num1 = this.num1.substring(0, this.num1.length() - 1);
                refreshText(this.num1);
            }
        }else if(buttonTxt.equals(".")){
            if (this.num2.isEmpty()){
                if (validatePoint(this.num1)){
                    this.num1 += buttonTxt;
                    refreshText(this.num1);
                }
            }else{
                if (validatePoint(this.num2)){
                    this.num2 += buttonTxt;
                    refreshText(this.num2);
                }
            }
        }else{
            if (!this.num1.isEmpty()){
                switch (buttonTxt){
                    case "√":
                        resultado = logicaNegocio.compute(Float.parseFloat(this.num1),0, "SQRT");
                        break;
                    case "X!":
                        resultado = logicaNegocio.compute(Float.parseFloat(this.num1),0, "FAQ");
                        break;
                    case "X^":
                        resultado = logicaNegocio.compute(Float.parseFloat(this.num1),0, "POW");
                        break;
                    default:
                        resultado = logicaNegocio.compute(Float.parseFloat(this.num1),0, buttonTxt);
                        break;
                }

                this.num1 = "";
                this.operacion = "";
                this.num2 = "";
                refreshText(resultado);
            }
        }
    }
}
