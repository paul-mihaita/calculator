package com.pxm673.calculator;

import com.pxm673.calculator.basiccontroller.BasicController;
import com.pxm673.calculator.basicmodel.BasicModel;
import com.pxm673.calculator.basicview.BasicView;

public class Basic {
	private BasicController controller;
	public Basic(){
		controller = new BasicController();
		controller.setModel(new BasicModel());
		controller.setView (new BasicView(controller));
		controller.updateView("");
	}
	public void start(){
		controller.updateView("");
	}
	public BasicController getController(){
		return controller;
	}
	public void hide(){
		controller.hide();
	}
	public void show(){
		controller.show();
	}
	public static void main (String[] args){
		Basic calc = new Basic();
		calc.start();
	}
}
