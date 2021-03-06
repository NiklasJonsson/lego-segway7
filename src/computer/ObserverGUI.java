package computer;

import utility.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ObserverGUI {
	private JTextField k1Field;
	private JTextField k2Field;
	private JTextField l1Field;
	private JTextField l2Field;
	private JTextField rField;
	private JTextField lrField;
	private JTextField kvField;
	private JRadioButton integralOn;
	private JRadioButton integralOff;
	private ObserverParameters startParameters;
	
	private Font lblFont = new Font("Serif", Font.PLAIN, 20);
	private Font tfFont = new Font("Serif", Font.PLAIN, 18);

	private ParameterMonitor paraMon;

	public ObserverGUI(ParameterMonitor paraMon, ObserverParameters parameters) {
		this.paraMon = paraMon;
		this.startParameters = parameters;
	}

	/*
	 * OBS! Överväg att inför SwingWorker
	 */
	public void createAndShow() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(300, 400);

		JButton applyButton = new JButton("Apply");
		applyButton.setFont(lblFont);
		frame.add(paramPane(), BorderLayout.CENTER);
		frame.add(applyButton, BorderLayout.SOUTH);
		frame.setVisible(true);

		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double l1, l2, lr, k1, k2, r, kv = 0;
				boolean integralAction;
				try {
					k1 = Double.parseDouble(k1Field.getText());
					k2 = Double.parseDouble(k2Field.getText());
					l1 = Double.parseDouble(l1Field.getText());
					l2 = Double.parseDouble(l2Field.getText());
					lr = Double.parseDouble(lrField.getText());
					r = Double.parseDouble(rField.getText());
					kv = Double.parseDouble(kvField.getText());
					integralAction = integralOn.isSelected();
					System.out.println("l1:" + l1);
					System.out.println("l2" + l2);
					System.out.println("lr" + lr);
					System.out.println("k1" + k1);
					System.out.println("k2" + k2);
					System.out.println("r" + r);
					System.out.println("integralaction" + integralAction);
					System.out.println("kv" + kv);

					Parameters p = new ObserverParameters(k1, k2, l1, l2, lr, kv, integralAction);

					try {
						paraMon.newParameters(p);
						System.out.println("Sent the following parameters to the client Monitor:" + p.toString());
					} catch (CloneNotSupportedException error) {
						System.out.println("Writing new parameters to the monitor, was interrupted");
						error.printStackTrace();
					}
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(null, "Please enter numbers in all fields", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

	private JPanel paramPane() {
		JPanel pane = new JPanel(new GridLayout(8, 2));

		l1Field = tf(""+startParameters.L1);
		pane.add(label("L1:"));
		pane.add(l1Field);

		l2Field = tf(""+startParameters.L2);
		pane.add(label("L2:"));
		pane.add(l2Field);

		lrField = tf(""+startParameters.Lr);
		pane.add(label("Lr:"));
		pane.add(lrField);

		k1Field = tf(""+startParameters.K1);
		pane.add(label("K1:"));
		pane.add(k1Field);

		k2Field = tf(""+startParameters.K2);
		pane.add(label("K2:"));
		pane.add(k2Field);
		
		kvField = tf(""+startParameters.Kv);
		pane.add(label("Kv:"));
		pane.add(kvField);

		rField = tf(""+startParameters.R);
		pane.add(label("R:"));
		pane.add(rField);

		pane.add(label("Integral action:"));
		integralOn=new JRadioButton("On", startParameters.integratorOn);
		integralOff=new JRadioButton("Off", !startParameters.integratorOn);
		integralOn.setFont(lblFont);
		integralOff.setFont(lblFont);
		ButtonGroup integralOnOff=new ButtonGroup();
		integralOnOff.add(integralOn);
		integralOnOff.add(integralOff);
		JPanel integralPane=new JPanel(new GridLayout(1, 2));
		integralPane.add(integralOn);
		integralPane.add(integralOff);
		pane.add(integralPane);

		

		return pane;
	}
	
	private JLabel label(String text){
		JLabel ret=new JLabel(text);
		ret.setFont(lblFont);
		ret.setHorizontalAlignment(JLabel.CENTER);
		return ret;
	}
	
	private JTextField tf(String text){
		JTextField ret = new JTextField(text);
		ret.setFont(tfFont);
		return ret;
	}

	public static void main(String[] args) {
		ObserverGUI gui = new ObserverGUI(new ParameterMonitor(), new ObserverParameters(1,1,1,1,1,1,true));
		gui.createAndShow();
	}
}
