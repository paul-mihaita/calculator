package com.pxm673.calculator.button;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class Button extends JButton {
	private long num;
	private char symbol;
	private String symbols;
	public Button(Action x,Icon icon){
		super(x);
		setIcon(icon);
		
	}
	public Button(String text){
		super(text);
		
	}
	public Button(){
		super();
		
	}
	public void setSize(Dimension x){
		this.setPreferredSize(x);
	}
	public void setNum(long x){
		num = x;
	}
	public long getNum(){
		return num;
	}
	public void setSymbol(char x){
		symbol = x;
	}
	public char getSymbol(){
		return symbol;
	}
	public void setSymbols(String x){
		symbols = x;
	}
	public String getSymbols(){
		return symbols;
	}
}
