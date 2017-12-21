package com.pxm673.calculator.intermediatecontroller;

import javax.swing.JPanel;

import com.pxm673.calculator.intermediatemodel.IntermediateModel;
import com.pxm673.calculator.intermediateview.IntermediateView;

public class IntermediateController  {
	private IntermediateModel IntermediateModel ;
	private IntermediateView  IntermediateView ;
	
	public IntermediateController(){
		
	}
	public void setModel(IntermediateModel model){
		IntermediateModel = model;
	}
	public void hide(){
		IntermediateView.setVisible(false);
	}
	public void show(){
		IntermediateView.setVisible(true);
	}
	public void setView(IntermediateView view){
		IntermediateView = view;
	}
	public void updateCurrent(double x){
		IntermediateModel.updateCurrent(x);
	}
	public void updateMemored(double x){
		IntermediateModel.setMemored(x);
	}
	public void setScreenText(String s){
		IntermediateModel.setScreenText(s);
	}
	public double getCurrent(){
		return IntermediateModel.getCurrent();
	}
	public void updateScreenText(String text){
		IntermediateModel.setScreenText(text);
		IntermediateView.update(text);
	}
	public String getScreenText(){
		return IntermediateModel.getScreenText();
	}
	public double getMemored(){
		return IntermediateModel.getMemored();
	}
	public void setresultState(boolean state){
		IntermediateModel.setresultState(state);
	}
	public boolean getresultState(){
		return IntermediateModel.getresultState();
	}
	public void evaluate(){
		IntermediateView.update(IntermediateModel.getScreenText());
		IntermediateModel.evaluate();
		IntermediateView.update(IntermediateModel.getScreenText());
		IntermediateModel.setScreenText("");
	}
	public void updateView(String s){
		IntermediateView.update(s);
		IntermediateModel.setScreenText(s);
	}
	public IntermediateView getView(){
		return IntermediateView;
	}
	public IntermediateModel getModel(){
		return IntermediateModel;
	}
	public JPanel getPanel(){
		return IntermediateView.getJPanel();
	}
}
