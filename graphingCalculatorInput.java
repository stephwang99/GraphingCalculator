

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class graphingCalculatorInput extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JLabel lblTitle;
	JLabel lblColour;
	JTextField txtEquation1;
	JTextField txtEquation2;
	JTextField txtEquation3;
	JTextField txtX1;
	JTextField txtX2;
	JTextField txtY1;
	JTextField txtY2;
	JButton btnhide1;
	JButton btnhide2;
	JButton btnhide3;
	JButton btnGO;
    
    public graphingCalculatorInput() {
    	frame.setSize(470,200);
    	frame.setTitle("Input");
    	frame.setLayout(new FlowLayout(FlowLayout.LEFT));
    	frame.setResizable(false);
    	
    	lblTitle = new JLabel("Equation 1 ");
    	frame.add(lblTitle);
    	
    	txtEquation1 = new JTextField();
    	txtEquation1.setColumns(24);
    	frame.add(txtEquation1);
    	
    	lblColour = new JLabel(" Red    ");
    	lblColour.setForeground(Color.RED);
    	frame.add(lblColour);
    	
    	btnhide1 = new JButton("Hide");
    	frame.add(btnhide1);
    	
    	
    	lblTitle = new JLabel("Equation 2 ");
    	frame.add(lblTitle);
    	
    	txtEquation2 = new JTextField();
    	txtEquation2.setColumns(24);
    	frame.add(txtEquation2);
    	
    	lblColour = new JLabel(" Blue   ");
    	lblColour.setForeground(Color.BLUE);
    	frame.add(lblColour);
    	
    	btnhide2 = new JButton("Hide");
    	frame.add(btnhide2);
    	
    	
    	lblTitle = new JLabel("Equation 3 ");
    	frame.add(lblTitle);
    	
    	txtEquation3 = new JTextField();
    	txtEquation3.setColumns(24);
    	frame.add(txtEquation3);
    	
    	lblColour = new JLabel(" Green");
    	lblColour.setForeground(Color.GREEN);
    	frame.add(lblColour);
    	
    	btnhide3 = new JButton("Hide");
    	frame.add(btnhide3);
    	
    	
    	lblTitle = new JLabel(" X bounds: ");
    	frame.add(lblTitle);
    	
    	txtX1 = new JTextField("-10");
    	txtX1.setColumns(4);
    	frame.add(txtX1);
    	
    	txtX2 = new JTextField("10");
    	txtX2.setColumns(4);
    	frame.add(txtX2);
    	
    	lblTitle = new JLabel("Y bounds: ");
    	frame.add(lblTitle);
    	
    	txtY1 = new JTextField("-10");
    	txtY1.setColumns(4);
    	frame.add(txtY1);
    	
    	txtY2 = new JTextField("10");
    	txtY2.setColumns(4);
    	frame.add(txtY2);
    	
    	btnGO = new JButton("     GRAPH IT!     ");
    	frame.add(btnGO);
    	
    	frame.setVisible(true);
    	
    	btnhide1.addActionListener(this);
    	btnhide2.addActionListener(this);
    	btnhide3.addActionListener(this);
    	btnGO.addActionListener(this);
    }
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnhide1)){
			if(btnhide1.getText()=="Hide"){
				btnhide1.setText("UnHide");
			}
			else
				btnhide1.setText("Hide");
		}
		if(e.getSource().equals(btnhide2)){
			if(btnhide2.getText()=="Hide"){
				btnhide2.setText("UnHide");
			}
			else
				btnhide2.setText("Hide");
		}
		if(e.getSource().equals(btnhide3)){
			if(btnhide3.getText()=="Hide"){
				btnhide3.setText("UnHide");
			}
			else
				btnhide3.setText("Hide");
		}
		if(e.getSource().equals(btnGO)){
			graphingCalculatorOutput graph1 = new graphingCalculatorOutput(Integer.parseInt(txtX2.getText()),Integer.parseInt(txtX1.getText()),Integer.parseInt(txtY2.getText()),Integer.parseInt(txtY1.getText()),txtEquation1.getText());
		}
		
	}

    
}