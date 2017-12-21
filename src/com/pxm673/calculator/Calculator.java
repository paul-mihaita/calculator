package com.pxm673.calculator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.pxm673.calculator.advancedcontroller.AdvancedController;
import com.pxm673.calculator.advancedmodel.AdvancedModel;
import com.pxm673.calculator.advancedview.AdvancedView;
import com.pxm673.calculator.basiccontroller.BasicController;
import com.pxm673.calculator.basicmodel.BasicModel;
import com.pxm673.calculator.basicview.BasicView;
import com.pxm673.calculator.intermediatecontroller.IntermediateController;
import com.pxm673.calculator.intermediatemodel.IntermediateModel;
import com.pxm673.calculator.intermediateview.IntermediateView;

public class Calculator extends JFrame {
	private String state="";
	private BasicController basicController;
	private IntermediateController intermediateController;
	private AdvancedController advancedController;
	
	private Basic basic = new Basic();
	private Intermediate intermediate = new Intermediate();
	private Advanced advanced = new Advanced();
	private JPanel PANEL = new JPanel();
	private JMenuBar menuBar;
	
	@SuppressWarnings("deprecation")
	public Calculator(){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		basicController = basic.getController()  ;
		intermediateController = intermediate.getController();
		advancedController = advanced.getController();
		
		
		
		menuBar = new JMenuBar();
		JMenu mode = new JMenu("MODE");
		menuBar.add(mode);
		JMenuItem Basic = new JMenuItem(new AbstractAction("Basic") {
		    public void actionPerformed(ActionEvent e) {
		    	JPanel x = basicController.getPanel();
				PANEL.removeAll();
				PANEL.add(x);
				PANEL.revalidate();
				state = "b";
		    }
		});
		JMenuItem Intermediate = new JMenuItem(new AbstractAction("Intermediate") {
		    public void actionPerformed(ActionEvent e) {
		    	JPanel x = intermediateController.getPanel();
				PANEL.removeAll();
				PANEL.add(x);
				PANEL.revalidate();
				state = "i";
		    }
		});
		JMenuItem Advanced = new JMenuItem(new AbstractAction("Scientific") {
		    public void actionPerformed(ActionEvent e) {
		    	JPanel x = advancedController.getPanel();
				PANEL.removeAll();
				PANEL.add(x);
				PANEL.revalidate();
				state = "a";
		    }
		});
		mode.add(Basic);
		mode.add(Intermediate);
		mode.add(Advanced);
		basic.hide();
		intermediate.hide();
		advanced.hide();
		
		JPanel x = basicController.getPanel();
		PANEL.removeAll();
		PANEL.add(x);
		PANEL.revalidate();
		state = "b";
		
		setJMenuBar(menuBar);
		add(PANEL);
		setVisible(true);
		
		super.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				update();
			}
			public void mouseEntered(MouseEvent e){

				update();
			}
			public void mouseExited(MouseEvent e){

				update();
						}
			public void mousePressed(MouseEvent e){

				update();
			}
			public void mouseReleased(MouseEvent e){

				update();
			}
		});
		
		pack();
		validate();
		repaint();
		
	}
	
	public void update(){
		if(state.equals("b")){
			JPanel x = basicController.getPanel();
			PANEL.removeAll();
			PANEL.add(x);
			PANEL.revalidate();
		}
		if(state.equals("i")){
			JPanel x = intermediateController.getPanel();
			PANEL.removeAll();
			PANEL.add(x);
			PANEL.revalidate();
		}
		if(state.equals("a")){
			JPanel x = advancedController.getPanel();
			PANEL.removeAll();
			PANEL.add(x);
			PANEL.revalidate();
		}
		setVisible(true);
		pack();
		validate();
		repaint();
	}
	
}
