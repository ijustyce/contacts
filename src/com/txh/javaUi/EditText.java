package com.txh.javaUi;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class EditText extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Font font = new Font("Default", Font.PLAIN, 21);

	public static JTextField createEditText(String value, int width) {

		JTextField b = new JTextField(value, width);
		b.setFont(font);
		return b;
	}

	public static JTextField createEditText(int width) {

		JTextField b = new JTextField(width);
		b.setFont(font);
		return b;
	}
}
