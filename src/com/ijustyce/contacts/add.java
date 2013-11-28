package com.ijustyce.contacts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ijustyce.sqlite.sqliteApi;
import com.txh.javaUi.Button;
import com.txh.javaUi.EditText;
import com.txh.javaUi.Label;

public class add extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name,phone,email,birthday;
	private boolean edit = false;
	private JTextField nameText,phoneText,emailText,birthdayText;
	private JButton button;
	private String id;
	
	/**
	 * Constructor 
	 */
	public add(){

		GridLayout layout = new GridLayout(5,1);
		setLayout(layout);
		if(!edit){
			setTitle(constant.addFriend);
		}
		else{
			setTitle(constant.updateInfo);
		}
		setSize(300,300);
	}
	
	/**
	 * build interface .
	 */
	public void showAdd(){
		
		if(!edit){
			nameText = EditText.createEditText(constant.EditTextSize);
		}
		else{
			nameText = EditText.createEditText(name,constant.EditTextSize);
		}
		
		JLabel nameLabel = Label.createLabel(constant.name);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		add(namePanel,BorderLayout.CENTER);
		
		if(!edit){
			phoneText = EditText.createEditText(constant.EditTextSize);
		}
		else{
			phoneText = EditText.createEditText(phone,constant.EditTextSize);
		}
		
		JLabel phoneLabel = Label.createLabel(constant.phone);
		
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout());
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneText);
		add(phonePanel,BorderLayout.CENTER);
		
		if(!edit){
			emailText = EditText.createEditText(constant.EditTextSize);
		}
		else{
			emailText = EditText.createEditText(email,constant.EditTextSize);
		}
		
		JLabel emailLabel = Label.createLabel(constant.email);
		
		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout());
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);
		add(emailPanel,BorderLayout.CENTER);
		
		if(!edit){
			birthdayText = EditText.createEditText(constant.EditTextSize);
		}
		else{
			birthdayText = EditText.createEditText(birthday,constant.EditTextSize);
		}
		
		JLabel birthdayLabel = Label.createLabel(constant.birthday);
		
		JPanel birthdayPanel = new JPanel();
		birthdayPanel.setLayout(new FlowLayout());
		birthdayPanel.add(birthdayLabel);
		birthdayPanel.add(birthdayText);
		add(birthdayPanel,BorderLayout.CENTER);
		
		if(!edit){
			button = Button.createButton(constant.addFriend);
		}
		else{
			button = Button.createButton(constant.updateInfo);
		}

		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addInfo(nameText.getText(),phoneText.getText(),
						emailText.getText(),birthdayText.getText());
				
			}
			
		});
		add(button);
		
		this.setVisible(true);
	}
	
	/**
	 * Override a record with new value .
	 * @param name name of friend .
	 * @param phone phone of friend .
	 * @param email email of friend .
	 * @param birthday birthday of friend .
	 * @param id friend's id in dbFile .
	 */
	public void showEdit(String name,String phone,String email,String birthday,String id){
		
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.id = id;
		edit = true;
		
		showAdd();
	}
	
	/**
	 * add info of a new friend .
	 * @param name name of friend .
	 * @param phone phone of friend .
	 * @param email email of friend .
	 * @param birthday birthday of friend .
	 */
	private void addInfo(String name,String phone,String email,String birthday){
		
		if(name.equals("")||phone.equals("")||email.equals("")||birthday.equals("")){
			JOptionPane.showMessageDialog(null, constant.error_blank, constant.error, 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		sqliteApi sqlite = new sqliteApi();
		String[] value = {name,phone,email,birthday};
		sqlite.insertData(constant.dbFile, constant.tableName, constant.row, value);
		if(edit){
			System.out.println("delete :" +id);
			sqlite.deleteData(constant.dbFile, constant.tableName, "id", id);
			view v = new view();
			v.showView();
			dispose();
		}
	}
}
