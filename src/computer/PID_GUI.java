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

public class PID_GUI {
	private JTextField kField;
	private JTextField tiField;
	private JTextField trField;
	private JTextField tdField;
	private JTextField nField;
	private JRadioButton integralOn;
	private JRadioButton integralOff;
	private PIDParameters startParameters;

	private Font lblFont = new Font("Serif", Font.PLAIN, 20);
	private Font tfFont = new Font("Serif", Font.PLAIN, 18);

	private ParameterMonitor paraMon;

	public PID_GUI(ParameterMonitor paraMon, PIDParameters parameters) {
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
				double tr, N, td, k, ti;
				boolean integralAction;
				try {
					k = Double.parseDouble(kField.getText());
					ti = Double.parseDouble(tiField.getText());
					tr = Double.parseDouble(trField.getText());
					td = Double.parseDouble(tdField.getText());
					N = Double.parseDouble(nField.getText());
					integralAction = integralOn.isSelected();
					System.out.println("K:" + k);
					System.out.println("Ti" + ti);
					System.out.println("Tr" + tr);
					System.out.println("Td" + td);
					System.out.println("N" + N);
					System.out.println("integralaction" + integralAction);

					PIDParameters p = new PIDParameters(k, ti, tr, td, N, integralAction);

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
		JPanel pane = new JPanel(new GridLayout(6, 2));

		kField = tf("" + startParameters.k);
		pane.add(label("K:"));
		pane.add(kField);

		tiField = tf("" + startParameters.ti);
		pane.add(label("Ti:"));
		pane.add(tiField);

		trField = tf("" + startParameters.tr);
		pane.add(label("Tr:"));
		pane.add(trField);

		tdField = tf("" + startParameters.td);
		pane.add(label("Td"));
		pane.add(tdField);

		nField = tf("" + startParameters.N);
		pane.add(label("N:"));
		pane.add(nField);


		pane.add(label("Integral action:"));
		integralOn = new JRadioButton("On", startParameters.integratorOn);
		integralOff = new JRadioButton("Off", !startParameters.integratorOn);
		integralOn.setFont(lblFont);
		integralOff.setFont(lblFont);
		ButtonGroup integralOnOff = new ButtonGroup();
		integralOnOff.add(integralOn);
		integralOnOff.add(integralOff);
		JPanel integralPane = new JPanel(new GridLayout(1, 2));
		integralPane.add(integralOn);
		integralPane.add(integralOff);
		pane.add(integralPane);

		return pane;
	}

	private JLabel label(String text) {
		JLabel ret = new JLabel(text);
		ret.setFont(lblFont);
		ret.setHorizontalAlignment(JLabel.CENTER);
		return ret;
	}

	private JTextField tf(String text) {
		JTextField ret = new JTextField(text);
		ret.setFont(tfFont);
		return ret;
	}

	public static void main(String[] args) {
		PID_GUI gui = new PID_GUI(new ParameterMonitor(), new PIDParameters(1, 1, 1, 1, 1, true));
		gui.createAndShow();
	}
}
