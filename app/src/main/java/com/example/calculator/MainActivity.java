package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;//组件存储
    private MyViewModel myViewModel;//数据模型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);//获取数据模型
        
        //事件监听
        myViewModel.getMainNum().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.myTextView.setText(myViewModel.getMainNum().getValue());//让myTextView显示mainNum

                //TextView显示内容

                if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("")){
                        binding.textView.setText(myViewModel.getMainNum().getValue());
                    }
                    else{//为运算式
                        binding.textView.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.getMainNum().getValue());
                    }
                }
                else{
                    binding.textView.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.num[1] + myViewModel.sign2 + myViewModel.mainNum.getValue());
                }


            }
        });
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("0");
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("3");
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("4");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("5");
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("6");
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("9");
            }
        });

        //小数点按钮
        binding.button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue()+".");
                    myViewModel.havePoint = true;
                }
            }
        });

        //加法
        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "+";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
                else if(myViewModel.sign2.equals("")){//形如a+b
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
                else{//a+b*c
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });

        //减法
        binding.button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "-";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
                else if(myViewModel.sign2.equals("")){//形如a+b
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
                else{//a+b*c
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });

        //乘法
        binding.button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "*";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
                else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                    else{
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }
                else{
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2 = "*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });

        //除法
        binding.button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "/";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
                else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                    else{
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }
                else{
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2 = "/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });

        //清空
        binding.button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.num[0] = "";
                myViewModel.num[1] = "";
                myViewModel.sign1 = "";
                myViewModel.sign2 = "";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint = false;
            }
        });

        //等于
        binding.button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign2.equals("")){
                    if(!myViewModel.sign1.equals("")){
                        myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                        myViewModel.num[0] = "";
                        myViewModel.sign1 = "";
                        if(myViewModel.getMainNum().getValue().contains(".")){
                            myViewModel.havePoint = true;
                        }
                        else{
                            myViewModel.havePoint = false;
                        }
                    }
                }
                else{
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.num[1] = "";
                    myViewModel.sign2 = "";
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                    if(myViewModel.getMainNum().getValue().contains(".")){
                        myViewModel.havePoint = true;
                    }
                    else{
                        myViewModel.havePoint = false;
                    }
                    myViewModel.sign1 = "";
                    myViewModel.num[0] = "";
                }
                binding.textView.setText(myViewModel.getMainNum().getValue());
            }
        });

        //回退
        binding.button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.getMainNum().getValue().equals("0")){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    if(myViewModel.getMainNum().getValue().equals(""))
                        myViewModel.getMainNum().setValue("0");
                }
            }
        });
    }
}