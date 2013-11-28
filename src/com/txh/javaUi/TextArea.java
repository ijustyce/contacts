package com.txh.javaUi;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TextArea extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Font font = new Font("Default",Font.PLAIN,21);

	public static JTextArea createTextArea(int height , int width){
		
		JTextArea t = new JTextArea(height , width);
		t.setFont(font);
		return t;
	}
}
