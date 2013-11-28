package com.ijustyce.contacts;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.ijustyce.sqlite.sqliteApi;
import com.txh.javaUi.List;
import com.txh.javaUi.TextArea;

public class view extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JTextArea textArea;
	private sqliteApi sqlite;
	private JPopupMenu popup = new JPopupMenu();
	private boolean noInfo = false;
	private int id;
	private String[][] value;
	
	/**
	 * Constructor init list
	 */
	public view(){
		
		textArea = TextArea.createTextArea(8, 16);
		textArea.setEditable(false);
		sqlite = new sqliteApi();
		listModel = new DefaultListModel<String>();
		value = sqlite.getValue(constant.dbFile, 
				constant.tableName, constant.getRow);
		
		if(value.length<1){
			
			JOptionPane.showMessageDialog(null, constant.error_null, constant.error, 
					JOptionPane.ERROR_MESSAGE);
			noInfo = true;
			return ;
		}
		
		for(int i = 0;i<value.length;i++){
			
			listModel.addElement(value[i][1]);
		}
		
		list = List.createList(listModel);
		if(value.length<20){
			list.setVisibleRowCount(value.length);
		}
		else{
			list.setVisibleRowCount(20);
		}
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JScrollPane(list));
		add(new JScrollPane(textArea));
	}
	
	MouseAdapter mouseEvent = new MouseAdapter(){
		public void mouseClicked(MouseEvent e) {
			textArea.setText("");
            int Index = list.locationToIndex(e.getPoint());
            if(Index<0){
            	return ;
            }
            for(int i =1;i<value[0].length;i++){
            	textArea.append(value[Index][i]+"\n");
            }
		}
		
		public void mousePressed(MouseEvent e){
			id = list.locationToIndex(e.getPoint());
			System.out.println("Pressed: " + id);
			if(e.isPopupTrigger()){
				popup.show(e.getComponent(),e.getX(),e.getY());
			}
		}
		public void mouseReleased(MouseEvent e){
			id = list.locationToIndex(e.getPoint());
			System.out.println("Released: " + id);
			if(e.isPopupTrigger()){
				popup.show(e.getComponent(),e.getX(),e.getY());
			}
		}
	};
	
	ActionListener actListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			
			String s = event.getActionCommand();
			if(s.equals(constant.menu1)&&id>-1){
				sqlite.deleteData(constant.dbFile, constant.tableName, 
						"id", value[id][0]);
				listModel.remove(id);
			}
			
			else if(s.equals(constant.menu2)&&id>-1){
				add a = new add();
				a.showEdit(value[id][1], value[id][2], value[id][3], 
						value[id][4], value[id][0]);
				
				new Thread(exitRun).start();
			}
			
			else if(s.equals(constant.menu3)){
				dispose();
			}
		}
	};
	
	Runnable exitRun = new Runnable() {

		public void run() {
			// TODO Auto-generated method stub
			dispose();
		}
	};
	
	/**
	 * show windows .
	 */
	public void showView(){
		if(noInfo){
			return ;
		}
		list.addMouseListener(mouseEvent);		
		JMenuItem[] popupItem = {new JMenuItem(constant.menu1),
				new JMenuItem(constant.menu2),new JMenuItem(constant.menu3)};
		
		for(int j = 0;j<popupItem.length;j++){
			popupItem[j].addActionListener(actListener);
			popup.add(popupItem[j]);
		}
		
		setTitle(constant.viewFriend);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320,300);
		setVisible(true);
	}
}
