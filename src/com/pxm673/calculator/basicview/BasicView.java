package com.pxm673.calculator.basicview;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.pxm673.calculator.basiccontroller.BasicController;
import com.pxm673.calculator.button.*;

import javafx.geometry.Insets;


public class BasicView extends JFrame {
	
	private JPanel scrn = new JPanel ( new BorderLayout());
	
	private JPanel lefth = new JPanel ( new GridBagLayout());
	
	private JPanel righth = new JPanel ( new GridBagLayout());
	private JLabel scrnLabel = new JLabel();
	private BasicController controller = new BasicController();
	private ArrayList<Button> rightButtonsList = new ArrayList<Button>();
	private ArrayList<Button> leftButtonsList = new ArrayList<Button>();
	private long currentNumber = 0;
	public BasicView(BasicController x){
		super();
		controller = x;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(800,450));
		setLayout(new BorderLayout());
		setScrn();
		setLeftH();
		setRightH();
		setVisible(true);
		
	}
	public void setScrnLabel(String s){
		Font f = new Font("serif", Font.PLAIN, scrn.getHeight()/3);
		scrnLabel.setPreferredSize(scrn.getPreferredSize());
		scrnLabel.setFont(f);
		scrnLabel.setText(s);
	}
	private void setScrn(){
		scrn.setName("");
		scrn.setPreferredSize(new Dimension(800,150));
		scrnLabel.setPreferredSize(new Dimension(800,150));
		scrnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
	}
	private void setLeftH(){
		lefth.setName("LeftPad");
		lefth.setSize(new Dimension(400,300));
		Font font1 = new Font("serif", Font.PLAIN, 50);
		int num = -1;
		for(int i=1;i<=3;i++){
			for(int j =1;j<=3;j++){
				num++;
				Button x = new Button(num+"");
				x.setFont(font1);
				GridBagConstraints cx = new GridBagConstraints();
				cx.weightx=1;
				cx.weighty=1;
				cx.gridx= j-1;
				cx.gridy= i-1;
				x.setSize(new Dimension((int)(lefth.getSize().getWidth()/3),(int)(lefth.getSize().getHeight()/4)));
				x.setBorder(new EmptyBorder(0, 0, 0, 0));
				cx.fill= GridBagConstraints.BOTH;
				x.setNum(num);
				x.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e){
									controller.updateScreenText(controller.getScreenText()+x.getNum());
									currentNumber = currentNumber*10 + x.getNum();
									
								}
							});
				
				leftButtonsList.add(x);
				lefth.add(x, cx);
			}
		}
		Button x = new Button("9");
		x.setFont(font1);
		GridBagConstraints cx = new GridBagConstraints();
		cx.weightx=1;
		cx.weighty=1;
		cx.gridx= 0;
		cx.gridy= 3;
		x.setSize(new Dimension((int)(lefth.getSize().getWidth()/3),(int)(lefth.getSize().getHeight()/4)));
		x.setBorder(new EmptyBorder(0, 0, 0, 0));
		cx.fill= GridBagConstraints.BOTH;
		x.setNum(9);
		x.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				controller.updateScreenText(controller.getScreenText()+x.getNum());
				currentNumber = currentNumber*10 + x.getNum();
			}
		});
		leftButtonsList.add(x);
		lefth.add(x,cx);
		
		Button w = new Button("M");
		w.setFont(font1);
		GridBagConstraints cw = new GridBagConstraints();
		cw.weightx=1;
		cw.weighty=1;
		cw.gridx= 1;
		cw.gridy= 3;
		w.setSize(new Dimension((int)(lefth.getSize().getWidth()/3),(int)(lefth.getSize().getHeight()/4)));
		w.setBorder(new EmptyBorder(0, 0, 0, 0));
		cw.fill= GridBagConstraints.BOTH;
		w.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				
				if(controller.getresultState() == true){
					controller.updateMemored(controller.getCurrent());
				}
				else{
					controller.updateScreenText(controller.getScreenText()+controller.getMemored());
				}
				
			}
		});
		leftButtonsList.add(w);
		lefth.add(w,cw);
		
		Button z = new Button("C");
		z.setFont(font1);
		GridBagConstraints cz = new GridBagConstraints();
		cz.weightx=1;
		cz.weighty=1;
		cz.gridx= 2;
		cz.gridy= 3;
		z.setSize(new Dimension((int)(lefth.getSize().getWidth()/3),(int)(lefth.getSize().getHeight()/4)));
		z.setBorder(new EmptyBorder(0, 0, 0, 0));
		cz.fill= GridBagConstraints.BOTH;
		z.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				controller.updateCurrent(0);
				controller.updateMemored(0);
				controller.updateScreenText("");
				currentNumber = 0;
			}
		});
		leftButtonsList.add(z);
		lefth.add(z,cz);
	}
	private void setRightH(){
		righth.setName("RightPad");
		righth.setSize(new Dimension(400,300));
		Font font2 = new Font("serif", Font.PLAIN, 50);
		
		Button x = new Button("+");
		x.setFont(font2);
		GridBagConstraints cx = new GridBagConstraints();
		cx.weightx=1;
		cx.weighty=1;
		cx.gridx= 1;
		cx.gridy= 0;
		x.setSize(new Dimension((int)(righth.getSize().getWidth()),(int)(righth.getSize().getHeight()/3)));
		x.setBorder(new EmptyBorder(0, 0, 0, 0));
		cx.fill= GridBagConstraints.BOTH;
		x.setSymbol('+');
		x.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(currentNumber <= 9999999999L){
					String aux = scrnLabel.getText();
					if(aux.length()>0){
						if(aux.charAt(aux.length()-1) <= '9' && aux.charAt(aux.length()-1) >= '0'){
							controller.updateScreenText(scrnLabel.getText()+x.getSymbol());
							controller.setresultState(false);
						}
						else if(aux.charAt(aux.length()-1) == '-' && aux.length()>1 ){
							if(aux.charAt(aux.length()-2) <= '9' && aux.charAt(aux.length()-2) >= '0'){
								controller.updateScreenText(scrnLabel.getText().substring(0,aux.length()-1)+x.getSymbol());
								controller.setresultState(false);
							}
						}
					}
					
				} else {setScrnLabel("ERROR");controller.setScreenText(""); controller.setresultState(true);}
				currentNumber = 0;
			}
		});
		rightButtonsList.add(x);
		righth.add(x,cx);
		
		Button w = new Button("-");
		w.setFont(font2);
		GridBagConstraints cw = new GridBagConstraints();
		cw.weightx=1;
		cw.weighty=1;
		cw.gridx=1;
		cw.gridy=1;
		w.setSize(new Dimension((int)(righth.getSize().getWidth()),(int)(righth.getSize().getHeight()/3)));
		w.setBorder(new EmptyBorder(0, 0, 0, 0));
		cw.fill= GridBagConstraints.BOTH;
		w.setSymbol('-');
		w.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(currentNumber <= 9999999999L){
					String aux = scrnLabel.getText();
					if(aux.length()>0){
						if(aux.charAt(aux.length()-1) == '+'){
							controller.updateScreenText(scrnLabel.getText()+w.getSymbol());
							controller.setresultState(false);
						}
						else if(aux.charAt(aux.length()-1) <= '9' && aux.charAt(aux.length()-1) >= '0'){
							controller.updateScreenText(scrnLabel.getText()+w.getSymbol());
							controller.setresultState(false);
						}
					}
					else {
						controller.updateScreenText(scrnLabel.getText()+w.getSymbol());
					}
					
				}
				else {setScrnLabel("ERROR");controller.setScreenText(""); controller.setresultState(true);}
				currentNumber = 0;
			}
		});
		rightButtonsList.add(w);
		righth.add(w,cw);
		
		Button z = new Button("=");
		z.setFont(font2);
		GridBagConstraints cz = new GridBagConstraints();
		cz.weightx=1;
		cz.weighty=1;
		cz.gridx= 1;
		cz.gridy= 3;
		z.setSize(new Dimension((int)(righth.getSize().getWidth()),(int)(righth.getSize().getHeight()/3)));
		z.setBorder(new EmptyBorder(0, 0, 0, 0));
		cz.fill= GridBagConstraints.BOTH;
		z.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(scrnLabel.getText().length()>0){
					if(controller.getScreenText().charAt(scrnLabel.getText().length()-1) <='9' && controller.getScreenText().charAt(scrnLabel.getText().length()-1) >='0')
						controller.evaluate();
					else {setScrnLabel("ERROR");controller.setScreenText(""); controller.setresultState(true);}
					currentNumber = 0;
				}
			}
		});
		rightButtonsList.add(z);
		righth.add(z,cz);
	}
	public String getScreenText(){
		return scrnLabel.getText();
	}
	public void update(String s){
		
		super.remove(scrn);
		scrn.removeAll();

		setScrnLabel(s);
		
		scrn.add(scrnLabel,BorderLayout.WEST);
		scrn.revalidate();
		scrn.repaint();
		
		super.add(scrn,BorderLayout.NORTH);
		super.add(lefth,BorderLayout.WEST);
		super.add(righth,BorderLayout.EAST);
		super.invalidate();
		super.validate();
		super.repaint();
		
		
	}
	public JPanel getJPanel(){
		JPanel panel = new JPanel (new BorderLayout());
		panel.add(scrn,BorderLayout.NORTH);
		panel.add(lefth,BorderLayout.WEST);
		panel.add(righth,BorderLayout.EAST);
		return panel;
	}
	
}
