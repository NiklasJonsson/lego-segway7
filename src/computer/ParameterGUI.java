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

public class ParameterGUI {
	private JTextField k1Field;
	private JTextField k2Field;
	private JTextField l1Field;
	private JTextField l2Field;
	private JTextField rField;
	private JTextField lrField;
	private JTextField kvField;
	private JTextField integralInputField;
	private JRadioButton integralOn;
	private JRadioButton integralOff;
	
	private Font font = new Font("Arial", Font.PLAIN, 20);

	private ParameterMonitor paraMon;

	public ParameterGUI(ParameterMonitor paraMon) {
		this.paraMon = paraMon;
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
		applyButton.setFont(font);
		frame.add(paramPane(), BorderLayout.CENTER);
		frame.add(applyButton, BorderLayout.SOUTH);
		frame.setVisible(true);

		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double l1, l2, lr, k1, k2, r, integralInput, kv = 0;
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

					Parameters p = new Parameters(k1, k2, l1, l2, lr, kv, integralAction, r);

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

		l1Field = new JTextField();
		pane.add(label("L1:"));
		pane.add(l1Field);

		l2Field = new JTextField();
		pane.add(label("L2:"));
		pane.add(l2Field);

		lrField = new JTextField();
		pane.add(label("Lr:"));
		pane.add(lrField);

		k1Field = new JTextField();
		pane.add(label("K1:"));
		pane.add(k1Field);

		k2Field = new JTextField();
		pane.add(label("K2:"));
		pane.add(k2Field);
		
		kvField = new JTextField();
		pane.add(label("Kv:"));
		pane.add(kvField);

		rField = new JTextField();
		pane.add(label("R:"));
		pane.add(rField);

		integralInputField = new JTextField();
		pane.add(label("Integral action:"));
		integralOn=new JRadioButton("On", true);
		integralOff=new JRadioButton("Off");
		integralOn.setFont(font);
		integralOff.setFont(font);
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
		ret.setFont(font);
		ret.setHorizontalAlignment(JLabel.CENTER);
		return ret;
	}

	public static void main(String[] args) {
		ParameterGUI gui = new ParameterGUI(new ParameterMonitor());
		gui.createAndShow();
	}
}
