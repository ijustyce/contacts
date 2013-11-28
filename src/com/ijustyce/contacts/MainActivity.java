package com.ijustyce.contacts;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ijustyce.sqlite.sqliteApi;

public class MainActivity extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static MainActivity main = new MainActivity();
	private Font font = new Font("Default",Font.PLAIN,21);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		main.setTitle(constant.mainTitle);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main.setSize(320,320);
		main.setVisible(true);
		
		createDb();
	}
	
	/**
	 * Constructor , build interface .
	 */
	public MainActivity(){
		setLayout(new GridLayout(4,1));
		
		JButton  addButton = new JButton(constant.addFriend);
		addButton.addActionListener(this);
		addButton.setFont(font);
		add(addButton);
		
		JButton  viewButton = new JButton(constant.viewFriend);
		viewButton.addActionListener(this);
		viewButton.setFont(font);
		add(viewButton);
		
		JButton  settingButton = new JButton(constant.setting);
		settingButton.addActionListener(this);
		settingButton.setFont(font);
		add(settingButton);
		
		JButton  exitButton = new JButton(constant.exit);
		exitButton.addActionListener(this);
		exitButton.setFont(font);
		add(exitButton);
	}
	
	/**
	 * event of button click . 
	 */
	public void actionPerformed(ActionEvent e) {
		
		String s = ((JButton)e.getSource()).getText();
		if(s.equals(constant.addFriend)){
			add addFriend = new add();
			addFriend.showAdd();
		}
		
		else if(s.equals(constant.exit)){
			main.setVisible(false);
			main = null;
			System.exit(0);
		}
		
		else if(s.equals(constant.setting)){
			
			JOptionPane.showMessageDialog(null, constant.error_wait, constant.error, 
					JOptionPane.ERROR_MESSAGE);
			return ;
			
//			add addFriend = new add();
//			addFriend.showAdd();
		}
		
		else if(s.equals(constant.viewFriend)){
			view v = new view();
			v.showView();
		}
	}
	
	/**
	 * initialize dbFile .
	 */
	private static void createDb(){
		
		File f = new File(constant.dbFile);
		if(!f.exists()){
			sqliteApi sqlite = new sqliteApi();
			sqlite.createDb(constant.dbFile);
			
			sqlite.createTable(constant.dbFile, constant.tableName, constant.row);
		}
	}
}
