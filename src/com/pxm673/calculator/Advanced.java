package com.pxm673.calculator;

import com.pxm673.calculator.advancedcontroller.AdvancedController;
import com.pxm673.calculator.advancedmodel.AdvancedModel;
import com.pxm673.calculator.advancedview.AdvancedView;

public class Advanced {
	private AdvancedController controller;
	public Advanced(){
		 controller = new AdvancedController();
		controller.setModel(new AdvancedModel());
		controller.setView (new AdvancedView(controller));
		controller.updateView("");
	}
	public void start(){
		controller.updateView("");
	}
	public AdvancedController getController(){
		return controller;
	}
	public void hide(){
		controller.hide();
	}
	public void show(){
		controller.show();
	}
	public static void main(String[] args){
		Advanced calc = new Advanced();
		calc.start();
	}
}
