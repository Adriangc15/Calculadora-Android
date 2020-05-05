package com.mobile.calculadora;

import androidx.annotation.Nullable;

public class LogicaNegocio {

    private static LogicaNegocio instance = null;
    private LogicaNegocio(){ }

    public static LogicaNegocio getInstance(){
        if (instance == null){
            instance = new LogicaNegocio();
        }
        return instance;
    }

    public String compute(float num1, float num2, String operador){
        switch (operador){
            case "+":
                return String.valueOf(suma(num1, num2));
            case "-":
                return String.valueOf(resta(num1,num2));
            case "*":
                return String.valueOf(mult(num1,num2));
            case "/":
                return String.valueOf(division(num1, num2));
            case "%":
                return String.valueOf(percentage(num1));
            case "SIN":
                return String.valueOf(sin(num1));
            case "COS":
                return String.valueOf(cos(num1));
            case "TAN":
                return String.valueOf(tan(num1));
            case "CSC":
                return String.valueOf(csc(num1));
            case "SEC":
                return String.valueOf(sec(num1));
            case "CTG":
                return String.valueOf(ctg(num1));
            case "FAQ":
                return String.valueOf(faq(num1));
            case "SQRT":
                return String.valueOf(sqrt(num1));
            case "POW":
                return String.valueOf(pow(num1, num2));
            default:
                return "Err";
        }
    }

    private float suma(float num1, float num2){
        return num1 + num2;
    }
    private float resta(float num1, float num2){
        return num1 - num2;
    }
    private float division(float num1, float num2){
        return num1 / num2;
    }
    private float mult(float num1, float num2){
        return num1 * num2;
    }
    private float sin(float num1) {
        return (float) Math.sin(num1);
    }
    private float cos(float num1) {
        return (float) Math.cos(num1);
    }
    private float tan(float num1) {
        return (float) Math.tan(num1);
    }
    private float csc(float num1) {
        return (float) Math.acos(num1);
    }
    private float sec(float num1) {
        return (float) Math.asin(num1);
    }
    private float ctg(float num1) {
        return (float) Math.atan(num1);
    }
    private float faq(float num1) {
        if (num1 < 0)
            return 0;
        if (num1 == 0)
            return 1;
        return num1 * faq(--num1);
    }
    private float sqrt(float num1) {
        return (float) Math.sqrt(num1);
    }
    private float pow(float num1, float num2) {
        return (float) Math.pow(num1, num2);
    }
    private float percentage(float num1) {
        return (float) num1 / 100;
    }
}
