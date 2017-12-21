package com.pxm673.calculator.advancedmodel;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

public class AdvancedModel {
	private double memored ;
	private double current ;
	private boolean resultCalculated = true;
	private String screenText = "";
	public AdvancedModel(){
		memored = 0;
		current = 0;
	}
	public void setMemored(double x){
		memored = x;
	}
	public void setCurrent(double x){
		current = x;
	}
	public double getMemored(){
		return memored;
	}
	public double getCurrent(){
		return current;
	}
	public double add(double x,double y){
		return x+y;
	}
	public void updateCurrent(double x){
		current = current*10+x;
	}
	public void setresultState(boolean state){
		resultCalculated = state;
	}
	public boolean getresultState(){
		return resultCalculated;
	}
	public void setScreenText(String s){
		screenText = s;
	}
	public String getScreenText(){
		return screenText;
	}
	public void evaluate(){
		String symbols = new String();
		Hashtable <String,Integer> tbl = new Hashtable <String,Integer>() ;
		tbl.put("*", 3);
		tbl.put("/", 3);
		tbl.put("+", 2);
		tbl.put("-", 2);
		tbl.put("(", 1);
		tbl.put(")", 1);
		tbl.put("!", 2);
		tbl.put("^", 3);
		String opstack = "";
		String prefixEx = "";
		symbols = "+-*/()";
		String aux="";
		if(screenText.equals(""))
			screenText = Double.toString(current);
		String[] tokens = screenText.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
		for(int i=0;i<tokens.length;i++){
			if(i == 0){
				if(tokens[i].equals("-")){
					aux+="!"+tokens[i+1];
					i++;
				}
				else aux+=tokens[i];
			}
			else{
				if(tokens[i].matches("[+-/*%^(]")){
						if(tokens[i+1].equals("-")){
							if(i<tokens.length-2){
								if(tokens[i].equals("(")){
									aux+=tokens[i]+"!";
									i+=1;
								}
								else{
									aux+=tokens[i]+"("+"!"+tokens[i+2]+")";
									i+=2;
								}
							}
							else{
								if(tokens[i].equals("(")){
									aux+=tokens[i]+"!";
									i+=1;
								}
								else{
								aux+=tokens[i]+"("+"!"+"0"+")";
								i++;
								}
							}
						}
						else
							aux+=tokens[i];
					
				}
				else aux+=tokens[i];
			}
		}
		tokens = aux.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
		for(int i=0;i<tokens.length;i++){
			if(tokens[i].matches("[0-9\\.]+")){
				prefixEx = prefixEx + tokens[i]+"_";
			}
			else{
				if(tokens[i].equals("(")){
					opstack = "("+opstack;
				}
				if(tokens[i].equals(")")){
					while(opstack.charAt(0) != '('){
						prefixEx = prefixEx + opstack.charAt(0)+"_";
						opstack = opstack.substring(1);
					}
					opstack = opstack.substring(1);
				}
				if(tokens[i].matches("[\\*\\!+-/^]{1}")){
					while(opstack.length()> 0 && tbl.get(opstack.charAt(0)+"") > tbl.get(tokens[i])){
						prefixEx = prefixEx + opstack.charAt(0)+"_";
						opstack = opstack.substring(1);
					}
					opstack = tokens[i] + opstack;
				}
			}
			
		}
		while(opstack.length()>0){
			prefixEx = prefixEx + opstack.charAt(0)+"_";
			opstack = opstack.substring(1);
		}
		
		prefixEx = prefixEx.substring(0,prefixEx.length()-1);
		
		
		screenText = "";
		
		String[] tokens2 = prefixEx.split("_");
		String[] stack = new String[tokens2.length];
		int stackDim = -1;
		
		for(int i=0;i<tokens2.length;i++){
			if(tokens2[i].matches("[0-9.]+")){
				stackDim++;
				stack[stackDim] = tokens2[i];
				
			}
			else{
				if(tokens2[i].equals("!")){
					double x = Double.parseDouble(stack[stackDim]);
					stackDim--;
					
					stackDim++;
					stack[stackDim] = Double.toString(x*(-1));
				}
				else{
					double rez = 0 ;
					double x = Double.parseDouble(stack[stackDim]);
					stackDim--;
					double y = Double.parseDouble(stack[stackDim]);
					stackDim--;
					if(tokens2[i].equals("+")){
						rez = y+x;
					}
					if(tokens2[i].equals("-")){
						rez = y-x;
					}
					if(tokens2[i].equals("*")){
						rez = y*x;
					}
					if(tokens2[i].equals("/")){
						rez = y/x;
					}
					if(tokens2[i].equals("^")){
						rez = Math.pow(y,x);
					}
					stackDim++;
					stack[stackDim] = Double.toString(rez);
				}
			}
			
		}
		if(Double.compare(Double.parseDouble(stack[stackDim]),999999999999999.9999999999) <= 0){
			DecimalFormat df = new DecimalFormat("#.##########");
			df.setRoundingMode(RoundingMode.CEILING);
			current = Double.parseDouble(stack[stackDim]);
			double value;
			if(current <0)
				value = current*-1;
			else value = current;
			if((value - (double)((int) value) > 0.00000001 )){
				screenText = df.format(current);
				resultCalculated = true;
			}
			else{
				int ind = stack[stackDim].indexOf('.');
				if(ind == -1)
					ind = stack[stackDim].length();
				screenText = stack[stackDim].substring(0, ind);
				resultCalculated = true;
			}
			
		}
		else{
			screenText = "ERROR";
			resultCalculated = true;
			current = 0;
		}
		
		
	}
}
