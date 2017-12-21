package com.pxm673.calculator.basicmodel;

import java.util.ArrayList;
import java.util.Hashtable;

public class BasicModel {
	private long memored ;
	private long current ;
	private boolean resultCalculated = true;
	private String screenText = "";
	public BasicModel(){
		memored = 0;
		current = 0;
	}
	public void setMemored(long x){
		memored = x;
	}
	public void setCurrent(long x){
		current = x;
	}
	public long getMemored(){
		return memored;
	}
	public long getCurrent(){
		return current;
	}
	public long add(long x,long y){
		return x+y;
	}
	public void updateCurrent(long x){
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
		tbl.put("!", 2);
		String opstack = "";
		String prefixEx = "";
		symbols = "+-*/()";
		String aux="";
		if(screenText.equals(""))
			screenText = Long.toString(current);
		String[] tokens = screenText.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
		for(int i=0;i<tokens.length;i++){
			if(i == 0){
				if(tokens[i].equals("-")){
					aux+="("+"!"+tokens[i+1]+")";
					i++;
				}
				else aux+=tokens[i];
			}
			else{
				if(tokens[i].matches("[+-/*%]")){
						if(tokens[i+1].equals("-")){
							if(i<tokens.length-2){
								aux+=tokens[i]+"("+"!"+tokens[i+2]+")";
								i+=2;
							}
							else{
								aux+=tokens[i]+"("+"!"+"0"+")";
								i++;
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
			if(tokens[i].matches("[0-9]+")){
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
				if(tokens[i].matches("[\\*\\!+-/]{1}")){
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
			if(tokens2[i].matches("[0-9]+")){
				stackDim++;
				stack[stackDim] = tokens2[i];
				
			}
			else{
				if(tokens2[i].equals("!")){
					long x = Long.parseLong(stack[stackDim]);
					stackDim--;
					
					stackDim++;
					stack[stackDim] = Long.toString(x*(-1));
				}
				else{
					long rez = 0 ;
					long x = Long.parseLong(stack[stackDim]);
					stackDim--;
					long y = Long.parseLong(stack[stackDim]);
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
					stackDim++;
					stack[stackDim] = Long.toString(rez);
				}
			}
			
		}
		if(Long.parseLong(stack[stackDim]) <= 9999999999L){
			screenText = stack[stackDim];
			resultCalculated = true;
			current = Long.parseLong(stack[stackDim]);
		}
		else{
			screenText = "ERROR";
			resultCalculated = true;
			current = 0;
		}
		
		
	}
}
