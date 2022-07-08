package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

//数据模型
public class MyViewModel extends ViewModel {
    public MutableLiveData<String> mainNum;//主数值
    public String sign1 = "";//存储运算符号
    public String sign2 = "";//存储运算符号
    public String num[] = {"",""};//存储参与运算的数值
    public boolean havePoint = false;//主数值是否含有小数点

    public MutableLiveData<String> getMainNum() {
        if (mainNum == null) {
            mainNum = new MutableLiveData<>();
            mainNum.setValue("0");
        }
        return mainNum;
    }

    public void setMainNum(String n) {
        if (Objects.equals(mainNum.getValue(), "0")) {
            mainNum.setValue(n);
        } else {
            mainNum.setValue(mainNum.getValue() + n);
        }
    }

    public String mainNumWithNum_0_Tocal() {
        String value = "0";
        if (mainNum.getValue().contains(".") || num[0].contains(".")) {//如果运算数有小数点
            switch (sign1) {
                case "+":
                    value = String.valueOf(Double.valueOf(num[0]) + Double.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Double.valueOf(num[0]) - Double.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Double.valueOf(num[0]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) {
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
                    break;
            }
        }
        else {
            switch (sign1) {
                case "+":
                    value = String.valueOf(Integer.valueOf(num[0]) + Integer.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Integer.valueOf(num[0]) - Integer.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Integer.valueOf(num[0]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) {
                        mainNum.setValue("000");
                    }
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
                    break;
            }
        }
        return value;
    }

    public String mainNumWithNum_1_Tocal() {
        String value = "0";
        if (mainNum.getValue().contains(".") || num[1].contains(".")) {//如果运算数有小数点
            switch (sign2) {
                case "*":
                    value = String.valueOf(Double.valueOf(num[1]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) {
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
                    break;
            }
        }
        else {
            switch (sign2) {
                case "*":
                    value = String.valueOf(Integer.valueOf(num[1]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) {
                        mainNum.setValue("000");
                    }
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
                    break;
            }
        }
        return value;
    }
}
