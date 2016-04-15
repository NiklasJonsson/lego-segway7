package computer;

import utility.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParameterGUI {
	private JTextField k1Field;
	private JTextField k2Field;
	private JTextField l1Field;
	private JTextField l2Field;
	private JTextField rField;
	private JTextField lrField;
	private JTextField integralInputField;
	private JTextField kvField;

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

		frame.setSize(400, 400);

		JButton applyButton = new JButton("Apply");

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
					integralInput = Double.parseDouble(integralInputField.getText());
					integralAction = (integralInput > 0);
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
		pane.add(new JLabel("L1:"));
		pane.add(l1Field);

		l2Field = new JTextField();
		pane.add(new JLabel("L2:"));
		pane.add(l2Field);

		lrField = new JTextField();
		pane.add(new JLabel("Lr:"));
		pane.add(lrField);

		k1Field = new JTextField();
		pane.add(new JLabel("k1:"));
		pane.add(k1Field);

		k2Field = new JTextField();
		pane.add(new JLabel("k2:"));
		pane.add(k2Field);

		rField = new JTextField();
		pane.add(new JLabel("r:"));
		pane.add(rField);

		integralInputField = new JTextField();
		pane.add(new JLabel("Integral action: (0=no, 1=yes):"));
		pane.add(integralInputField);

		kvField = new JTextField();
		pane.add(new JLabel("kv:"));
		pane.add(kvField);

		return pane;
	}

	public static void main(String[] args) {
		ParameterGUI gui = new ParameterGUI(new ParameterMonitor());
		gui.createAndShow();
	}
}
