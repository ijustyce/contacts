package com.txh.javaUi;

import java.awt.Font;

import javax.swing.JLabel;

public class Label {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Font font = new Font("Default",Font.PLAIN,21);

	public static JLabel createLabel(String value){
		
		JLabel b = new JLabel(value);	
		b.setFont(font);
		return b;		
	}
}
