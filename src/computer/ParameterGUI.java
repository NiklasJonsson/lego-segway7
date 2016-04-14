package computer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParameterGUI {
	private JTextField l1Field;
	private JTextField l2Field;
	private JTextField lrField;
	private JTextField k1Field;
	private JTextField k2Field;
	private JTextField rField;

	public void createAndShow() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(100, 150);

		JButton applyButton = new JButton("Apply");

		frame.add(paramPane(), BorderLayout.CENTER);
		frame.add(applyButton, BorderLayout.SOUTH);
		frame.setVisible(true);

		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double l1, l2, lr, k1, k2, r = 0;
				try {
					l1 = Double.parseDouble(l1Field.getText());
					l2 = Double.parseDouble(l2Field.getText());
					lr = Double.parseDouble(lrField.getText());
					k1 = Double.parseDouble(k1Field.getText());
					k2 = Double.parseDouble(k2Field.getText());
					r = Double.parseDouble(rField.getText());
					
					System.out.println(l1);
					System.out.println(l2);
					System.out.println(lr);
					System.out.println(k1);
					System.out.println(k2);
					System.out.println(r);
					
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(null, "Please enter numbers in all fields", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

	private JPanel paramPane() {
		JPanel pane = new JPanel(new GridLayout(6, 2));

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

		return pane;
	}

	public static void main(String[] args) {
		ParameterGUI gui = new ParameterGUI();
		gui.createAndShow();
	}
}
