package com.ijustyce.contacts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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
import com.ijustyce.ui.IFont;

public class add extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font ;
	private String name,phone,email,birthday;
	private boolean edit = false;
	private JTextField nameText,phoneText,emailText,birthdayText;
	private JButton button;
	private IFont iFont;
	private String id;
	
	/**
	 * Constructor 
	 */
	public add(){
		
		iFont = new IFont();
		font = iFont.getDefaultFont();
		
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
			nameText = new JTextField(20);
		}
		else{
			nameText = new JTextField(name,20);
		}
		
		nameText.setFont(font);
		
		JLabel nameLabel = new JLabel(constant.name);
		nameLabel.setFont(font);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		add(namePanel,BorderLayout.CENTER);
		
		if(!edit){
			phoneText = new JTextField(20);
		}
		else{
			phoneText = new JTextField(phone,20);
		}
		
		phoneText.setFont(font);
		
		JLabel phoneLabel = new JLabel(constant.phone);
		phoneLabel.setFont(font);
		
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout());
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneText);
		add(phonePanel,BorderLayout.CENTER);
		
		if(!edit){
			emailText = new JTextField(20);
		}
		else{
			emailText = new JTextField(email,20);
		}
		
		emailText.setFont(font);
		
		JLabel emailLabel = new JLabel(constant.email);
		emailLabel.setFont(font);
		
		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout());
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);
		add(emailPanel,BorderLayout.CENTER);
		
		if(!edit){
			birthdayText = new JTextField(20);
		}
		else{
			birthdayText = new JTextField(birthday,20);
		}
		
		birthdayText.setFont(font);
		
		JLabel birthdayLabel = new JLabel(constant.birthday);
		birthdayLabel.setFont(font);
		
		JPanel birthdayPanel = new JPanel();
		birthdayPanel.setLayout(new FlowLayout());
		birthdayPanel.add(birthdayLabel);
		birthdayPanel.add(birthdayText);
		add(birthdayPanel,BorderLayout.CENTER);
		
		if(!edit){
			button = new JButton(constant.addFriend);
		}
		else{
			button = new JButton(constant.updateInfo);
		}
		button.setFont(font);
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
