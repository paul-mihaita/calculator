package com.pxm673.calculator;

import com.pxm673.calculator.intermediatecontroller.IntermediateController;
import com.pxm673.calculator.intermediatemodel.IntermediateModel;
import com.pxm673.calculator.intermediateview.IntermediateView;

public class Intermediate {
	private IntermediateController controller;
	public Intermediate(){
		controller = new IntermediateController();
		controller.setModel(new IntermediateModel());
		controller.setView (new IntermediateView(controller));
		controller.updateView("");
	}
	public void start(){
		controller.updateView("");
	}
	public IntermediateController getController(){
		return controller;
	}
	public void hide(){
		controller.hide();
	}
	public void show(){
		controller.show();
	}
	public static void main (String[] args){
		Intermediate calc = new Intermediate();
		calc.start();
	}
}
