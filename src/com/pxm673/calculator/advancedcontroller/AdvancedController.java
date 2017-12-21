package com.pxm673.calculator.advancedcontroller;

import javax.swing.JPanel;

import com.pxm673.calculator.advancedmodel.AdvancedModel;
import com.pxm673.calculator.advancedview.AdvancedView;

public class AdvancedController  {
	private AdvancedModel AdvancedModel ;
	private AdvancedView  AdvancedView ;
	
	public AdvancedController(){
		
	}
	public void hide(){
		AdvancedView.setVisible(false);
	}
	public void show(){
		AdvancedView.setVisible(true);
	}
	public void setModel(AdvancedModel model){
		AdvancedModel = model;
	}
	public void setView(AdvancedView view){
		AdvancedView = view;
	}
	public void updateCurrent(double x){
		AdvancedModel.updateCurrent(x);
	}
	public void updateMemored(double x){
		AdvancedModel.setMemored(x);
	}
	public void setScreenText(String s){
		AdvancedModel.setScreenText(s);
	}
	public double getCurrent(){
		return AdvancedModel.getCurrent();
	}
	public void setCurrent(double x){
		AdvancedModel.setCurrent(x);
	}
	public void updateScreenText(String text){
		AdvancedModel.setScreenText(text);
		AdvancedView.update(text);
	}
	public String getScreenText(){
		return AdvancedModel.getScreenText();
	}
	public double getMemored(){
		return AdvancedModel.getMemored();
	}
	public void setresultState(boolean state){
		AdvancedModel.setresultState(state);
	}
	public boolean getresultState(){
		return AdvancedModel.getresultState();
	}
	public void evaluate(){
		AdvancedView.update(AdvancedModel.getScreenText());
		AdvancedModel.evaluate();
		AdvancedView.update(AdvancedModel.getScreenText());
		AdvancedModel.setScreenText("");
	}
	public void updateView(String s){
		AdvancedView.update(s);
		AdvancedModel.setScreenText(s);
	}
	public AdvancedView getView(){
		return AdvancedView;
	}
	public AdvancedModel getModel(){
		return AdvancedModel;
	}
	public JPanel getPanel(){
		return AdvancedView.getJPanel();
	}
}
