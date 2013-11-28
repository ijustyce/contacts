package com.txh.javaUi;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Font font = new Font("Default",Font.PLAIN,21);

	public static JButton createButton(String value){
		
		JButton b = new JButton(value);	
		b.setFont(font);
		return b;		
	}
}
