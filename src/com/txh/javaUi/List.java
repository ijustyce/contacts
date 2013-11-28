package com.txh.javaUi;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class List extends Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Font font = new Font("Default",Font.PLAIN,21);

	public static JList<String> createList
	(DefaultListModel<String> value){
		
		JList<String> b = new JList<String>(value);	
		b.setFont(font);
		return b;		
	}

}
