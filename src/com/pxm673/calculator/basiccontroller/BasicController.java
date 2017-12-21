package com.pxm673.calculator.basiccontroller;

import javax.swing.JPanel;

import com.pxm673.calculator.basicmodel.BasicModel;
import com.pxm673.calculator.basicview.BasicView;

public class BasicController  {
	
	private BasicModel basicModel ;
	private BasicView  basicView ;
	
	public BasicController(){
		
	}
	public void setModel(BasicModel model){
		basicModel = model;
	}
	public void hide(){
		basicView.setVisible(false);
	}
	public void show(){
		basicView.setVisible(true);
	}
	public void setView(BasicView view){
		basicView = view;
	}
	public void updateCurrent(long x){
		basicModel.updateCurrent(x);
	}
	public void updateMemored(long x){
		basicModel.setMemored(x);
	}
	public void setScreenText(String s){
		basicModel.setScreenText(s);
	}
	public long getCurrent(){
		return basicModel.getCurrent();
	}
	public void updateScreenText(String text){
		basicModel.setScreenText(text);
		basicView.update(text);
	}
	public String getScreenText(){
		return basicModel.getScreenText();
	}
	public long getMemored(){
		return basicModel.getMemored();
	}
	public void setresultState(boolean state){
		basicModel.setresultState(state);
	}
	public boolean getresultState(){
		return basicModel.getresultState();
	}
	public void evaluate(){
		basicView.update(basicModel.getScreenText());
		basicModel.evaluate();
		basicView.update(basicModel.getScreenText());
		basicModel.setScreenText("");
	}
	public void updateView(String s){
		basicView.update(s);
		basicModel.setScreenText(s);
	}
	public BasicView getView(){
		return basicView;
	}
	public BasicModel getModel(){
		return basicModel;
	}
	public JPanel getPanel(){
		return basicView.getJPanel();
	}
}
