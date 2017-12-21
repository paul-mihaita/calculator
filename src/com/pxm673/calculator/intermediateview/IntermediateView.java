package com.pxm673.calculator.intermediateview;

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

import com.pxm673.calculator.intermediatecontroller.IntermediateController;
import com.pxm673.calculator.button.*;

import javafx.geometry.Insets;


public class IntermediateView extends JFrame {
	
	private JPanel scrn = new JPanel ( new BorderLayout());
	
	private JPanel lefth = new JPanel ( new GridBagLayout());
	private boolean point = true;
	private JPanel righth = new JPanel ( new GridBagLayout());
	private JLabel scrnLabel = new JLabel();
	private IntermediateController controller = new IntermediateController();
	private ArrayList<Button> rightButtonsList = new ArrayList<Button>();
	private ArrayList<Button> leftButtonsList = new ArrayList<Button>();
	private double currentNumber = 0;
	public IntermediateView(IntermediateController x){
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
		Font font1 = new Font("serif", Font.PLAIN, 50);
		lefth.setName("LeftPad");
		lefth.setSize(new Dimension(400,300));
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
		
		Button w = new Button(".");
		w.setFont(font1);
		w.setSymbol('.');
		GridBagConstraints cw = new GridBagConstraints();
		cw.weightx=1;
		cw.weighty=1;
		cw.gridheight = 1;
		cw.gridwidth = 2;
		cw.gridx= 1;
		cw.gridy= 3;
		w.setSize(new Dimension((int)(lefth.getSize().getWidth()/3),(int)(lefth.getSize().getHeight()/4)));
		w.setBorder(new EmptyBorder(0, 0, 0, 0));
		cw.fill= GridBagConstraints.BOTH;
		w.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(point == true){
					String aux = controller.getScreenText();
					if(aux.length()>0){
					if(aux.charAt(aux.length()-1) >= '0' && aux.charAt(aux.length()-1) <= '9'){
						controller.updateScreenText(controller.getScreenText()+w.getSymbol());
						point = false;
					}
					
					}
					controller.setresultState(false);
				}
				
			}
		});
		leftButtonsList.add(w);
		lefth.add(w,cw);
		
	}
	private void setRightH(){
		Font font2 = new Font("serif", Font.PLAIN, 50);
		righth.setName("RightPad");
		righth.setSize(new Dimension(400,300));

		
		Button x = new Button("+");
		x.setFont(font2);
		GridBagConstraints cx = new GridBagConstraints();
		cx.weightx=1;
		cx.weighty=1;
		cx.gridx= 1;
		cx.gridy= 0;
		x.setSize(new Dimension((int)(righth.getSize().getWidth()/2),(int)(righth.getSize().getHeight()/4)));
		x.setBorder(new EmptyBorder(0, 0, 0, 0));
		cx.fill= GridBagConstraints.BOTH;
		x.setSymbol('+');
		x.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(Double.compare(currentNumber,9999999999.9999999999)<=0){
					String aux = scrnLabel.getText();
					if(aux.length()>0){
						if(aux.charAt(aux.length()-1) <= '9' && aux.charAt(aux.length()-1) >= '0'){
							controller.updateScreenText(scrnLabel.getText()+x.getSymbol());
							controller.setresultState(false);
						}
						else if((aux.charAt(aux.length()-1) == '-'|| aux.charAt(aux.length()-1) == '/' || aux.charAt(aux.length()-1) == '*' )&& aux.length()>1 ){
							if(aux.charAt(aux.length()-2) <= '9' && aux.charAt(aux.length()-2) >= '0'){
								controller.updateScreenText(scrnLabel.getText().substring(0,aux.length()-1)+x.getSymbol());
								controller.setresultState(false);
							}
						}
						point = true;
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
		cw.gridx=0;
		cw.gridy=0;
		w.setSize(new Dimension((int)(righth.getSize().getWidth())/2,(int)(righth.getSize().getHeight()/4)));
		w.setBorder(new EmptyBorder(0, 0, 0, 0));
		cw.fill= GridBagConstraints.BOTH;
		w.setSymbol('-');
		w.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(Double.compare(currentNumber,9999999999.9999999999)<=0){
					String aux = scrnLabel.getText();
					if(aux.length()>0){
						if(aux.charAt(aux.length()-1) == '+'){
							controller.updateScreenText(scrnLabel.getText()+w.getSymbol());
							controller.setresultState(false);
							point = true;
						}
						else if(aux.charAt(aux.length()-1) <= '9' && aux.charAt(aux.length()-1) >= '0'){
							controller.updateScreenText(scrnLabel.getText()+w.getSymbol());
							controller.setresultState(false);
							point = true;
						}
					}
					else {
						controller.updateScreenText(scrnLabel.getText()+w.getSymbol());
						controller.setresultState(false);
						point = true;
					}
				}
				else {setScrnLabel("ERROR");controller.setScreenText(""); controller.setresultState(true);}
				currentNumber = 0;
			}
		});
		rightButtonsList.add(w);
		righth.add(w,cw);
		
		Button v = new Button("/");
		v.setFont(font2);
		GridBagConstraints cv = new GridBagConstraints();
		cv.weightx=1;
		cv.weighty=1;
		cv.gridx= 0;
		cv.gridy= 1;
		v.setSize(new Dimension((int)(righth.getSize().getWidth()/2),(int)(righth.getSize().getHeight()/4)));
		v.setBorder(new EmptyBorder(0, 0, 0, 0));
		cv.fill= GridBagConstraints.BOTH;
		v.setSymbol('/');
		v.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(Double.compare(currentNumber,9999999999.9999999999)<=0){
					String aux = scrnLabel.getText();
					if(aux.length()>0){
						if(aux.charAt(aux.length()-1) <= '9' && aux.charAt(aux.length()-1) >= '0'){
							controller.updateScreenText(scrnLabel.getText()+v.getSymbol());
							controller.setresultState(false);
							point = true;
						}
						else if(aux.charAt(aux.length()-1) == '-' && aux.length()>1 ){
							if(aux.charAt(aux.length()-2) <= '9' && aux.charAt(aux.length()-2) >= '0'){
								controller.updateScreenText(scrnLabel.getText().substring(0,aux.length()-1)+x.getSymbol());
								controller.setresultState(false);
								point = true;
							}
						}
						
					}
					
				} else {setScrnLabel("ERROR");controller.setScreenText(""); controller.setresultState(true);}
				currentNumber = 0;
			}
		});
		rightButtonsList.add(v);
		righth.add(v,cv);
		
		Button n = new Button("*");
		n.setFont(font2);
		GridBagConstraints cn = new GridBagConstraints();
		cn.weightx=1;
		cn.weighty=1;
		cn.gridx= 1;
		cn.gridy= 1;
		n.setSize(new Dimension((int)(righth.getSize().getWidth()/2),(int)(righth.getSize().getHeight()/4)));
		n.setBorder(new EmptyBorder(0, 0, 0, 0));
		cn.fill= GridBagConstraints.BOTH;
		n.setSymbol('*');
		n.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(Double.compare(currentNumber,9999999999.9999999999)<=0){
					String aux = scrnLabel.getText();
					if(aux.length()>0){
						if(aux.charAt(aux.length()-1) <= '9' && aux.charAt(aux.length()-1) >= '0'){
							controller.updateScreenText(scrnLabel.getText()+n.getSymbol());
							controller.setresultState(false);
							point = true;
						}
						else if(aux.charAt(aux.length()-1) == '-' && aux.length()>1 ){
							if(aux.charAt(aux.length()-2) <= '9' && aux.charAt(aux.length()-2) >= '0'){
								controller.updateScreenText(scrnLabel.getText().substring(0,aux.length()-1)+x.getSymbol());
								controller.setresultState(false);
								point = true;
							}
						}
						
					}
					
				} else {setScrnLabel("ERROR");controller.setScreenText(""); controller.setresultState(true);}
				currentNumber = 0;
			}
		});
		rightButtonsList.add(n);
		righth.add(n,cn);
		
		Button z = new Button("=");
		z.setFont(font2);
		GridBagConstraints cz = new GridBagConstraints();
		cz.weightx=1;
		cz.weighty=1;
		cz.gridx= 0;
		cz.gridy= 3;
		cz.gridheight = 1;
		cz.gridwidth = 2;
		z.setSize(new Dimension((int)(righth.getSize  ().getWidth()/2),(int)(righth.getSize().getHeight()/4)));
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
				point = true;
			}
		});
		rightButtonsList.add(z);
		righth.add(z,cz);
		
		Button q = new Button("M");
		q.setFont(font2);
		GridBagConstraints cq = new GridBagConstraints();
		cq.weightx=1;
		cq.weighty=1;
		cq.gridx= 0;
		cq.gridy= 2;
		q.setSize(new Dimension((int)(righth.getSize().getWidth()/2),(int)(righth.getSize().getHeight()/4)));
		q.setBorder(new EmptyBorder(0, 0, 0, 0));
		cq.fill= GridBagConstraints.BOTH;
		q.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				
				if(controller.getresultState() == true){
					double value = controller.getCurrent();
					controller.updateMemored(value);
				}
				else{
					double value = controller.getMemored();
					if(value - (int)(value) > 0.00000001){
						controller.updateScreenText(controller.getScreenText()+value);
						point = false;
					}
					else{
						String aux = Double.toString(value);
						int ind = aux.indexOf('.');
						aux= aux.substring(0, ind);
						controller.updateScreenText(controller.getScreenText()+aux);
						point = true;
					}
					
				}
				
			}
		});
		rightButtonsList.add(q);
		righth.add(q,cq);
		
		Button p = new Button("C");
		p.setFont(font2);
		GridBagConstraints cp = new GridBagConstraints();
		cp.weightx=1;
		cp.weighty=1;
		cp.gridx= 1;
		cp.gridy= 2;
		p.setSize(new Dimension((int)(righth.getSize().getWidth()/2),(int)(righth.getSize().getHeight()/4)));
		p.setBorder(new EmptyBorder(0, 0, 0, 0));
		cp.fill= GridBagConstraints.BOTH;
		p.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				controller.updateCurrent(0);
				controller.updateMemored(0);
				controller.updateScreenText("");
				currentNumber = 0;
				point = true;
			}
		});
		rightButtonsList.add(p);
		righth.add(p,cp);
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
