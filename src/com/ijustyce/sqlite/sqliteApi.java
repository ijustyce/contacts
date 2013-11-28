/**
 * sqlite api , create ,insert and so on .
 * @author yc
 * @date 2013-11-16
 * @platform win8.1 pro and eclipse Indigo Service Release 2
 * @encode : GBK
 */

package com.ijustyce.sqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class sqliteApi {
	
	boolean inUsing = false;
    
    /**
     * create .db file and named it as dbFile . 
     * @param dbFile name of sqlite file , end with .db
     */
    public void createDb(String dbFile){
    	p();
    	try {
             Class.forName("org.sqlite.JDBC");
             Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
             conn.close();
    	 }
    	 catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	finally{
    		v();
    	}
    }
    
    /**
     * create table , you need point out table name with String 
     * type and row name with an String type array .
     * @param dbFile dbFile sqlite file name , end with .db .
     * @param table table of this sqlite file .
     * @param value an array , value of row
     */
    public void createTable(String dbFile , String table ,String[]value){
    	p();
    	int i;
    	String sql = "";
    	for(i=0;i<value.length-1;i++){
    		
    		sql = sql + value[i] + " TEXT,";
    	}
    	sql = "create table " + table + " (id INTEGER PRIMARY KEY, " + sql + 
    			value[value.length-1] + " TEXT);";
    	System.out.println("create table: " + sql);
    	try {
    		Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql); 
            conn.close();
            stat.close();
        }
    	 catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	finally{
    		v();
    	}
    }
    
    /**
     * insert data to dbFile , you need point out dbFile and table name with 
     * String type row name and value with a String type array .
     * @param dbFile sqlite file name , end with .db .
     * @param table table of this sqlite file .
     * @param row the row you want to insert data .
     * @param value an array , value of the row .
     * @param tableName table name of sqlite file .
     */
    public void insertData(String dbFile , String tableName,String[] row ,String[] value){  	
    	p();
    	int i;
    	String sql = "";
    	String values = "";
    	for(i = 0;i<row.length-1;i++){
    		sql = sql + row[i] + ", ";
    		values = values + "?" +",";
    	}
    	values = " values (" + values + "?" +")" ;
    	sql = "insert into " + tableName + " (" + sql + row[row.length-1] + ")" + values;
    	System.out.println("insert data: " + sql);
    	try {
             Class.forName("org.sqlite.JDBC");
             Connection conn = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
             PreparedStatement prep = conn.prepareStatement(sql);
             for(i = 0;i<value.length;i++){
            	 prep.setString(i+1, value[i]);
             }
             prep.executeUpdate();
             conn.setAutoCommit(false);
             prep.executeBatch();
             conn.setAutoCommit(true);
             conn.close();
    	 }
    	 catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	finally{
    		v();
    	}
    }
    
    /**
     * edit data , point out dbFile ,table , key and row , 
     * note: value[0] is new record and value[1] is value of key . 
    * @param dbFile sqlite file name , end with .db .
     * @param table table of this sqlite file .
     * @param key to find the line you want to edit .
     * @param row the row you want to update .
     * @param value is an array , value[0] is new record value and 
     * value[1] is value of key . 
     */
    public void editData(String dbFile , String table ,
    		String key , String row , String[] value){  
    	p();
    	String sql;
    	sql = "update " + table +"set " + row + " = ? where " + key + " = ?";
    	System.out.println("edit data: " + sql);
    	try {
             Class.forName("org.sqlite.JDBC");
             Connection conn = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
             PreparedStatement prep = conn.prepareStatement(sql);
             
             prep.setString(1, value[0]);
             prep.setString(2, value[1]);
             prep.executeUpdate();
             
             conn.setAutoCommit(false);
             prep.executeBatch();
             conn.setAutoCommit(true);
             conn.close();
    	 }
    	 catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	finally{
    		v();
    	}
    }
    
    /**
     * read dbFile line by line and then return an two-dimensional array
     * more information please read return .
     * @param dbFile sqlite file name , end with .db .
     * @param table table of this sqlite file .
     * @param row an array , it's value you want to return  .
     * @return a two-dimensional array , value[][1] is name and value[2] is phone
     * value[i][] is the record I .
     */
    public String[][] getValue(String dbFile ,String table ,String[] row){
    	String sql = "select * from " + table;
    	String[][] result;
    	result = new String[getTotal(dbFile,table)][row.length];
    	p();
    	try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            
            int i,j = 0;
            while (rs.next()) {
            	for(i =0;i<row.length;i++){
            		result[j][i] = rs.getString(row[i]);
            		System.out.println("("+i+","+j+")"+result[j][i]);
            	}
            	j++;
           } 
    	}
    	 catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	finally{
    		v();
    	}
    	return result;
    }
    
    /**
     * delete a record by key and it's value .
     * @param dbFile sqlite file name , end with .db .
     * @param table table of this sqlite file .
     * @param key the key to find delete line .
     * @param value value of the key .
     */
    public void deleteData(String dbFile , String table ,
    		String key , String value){  
    	p();
    	String sql;
    	sql = "delete from " + table + " where " + key + " = ?";
    	try {
             Class.forName("org.sqlite.JDBC");
             Connection conn = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
             PreparedStatement prep = conn.prepareStatement(sql);
             
             prep.setString(1, value);
             prep.executeUpdate();
             
             conn.setAutoCommit(false);
             prep.executeBatch();
             conn.setAutoCommit(true);
             conn.close();
    	 }
    	 catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	finally{
    		v();
    	}
    }
    
    /**
     * get total record number ,and then return , note: not support blank record .
     * @param dbFile  sqlite file name , end with .db .
     * @param table table of this sqlite file .
     * @return line number of a table ,but make sure not allow blank record .
     */
    private int getTotal(String dbFile ,String table){
    	p();
    	int i = 0;
    	String sql = "select * from " + table;
    	try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            
            while (rs.next()) {
            	i++;
           } 
    	}
    	 catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	finally{
    		v();
    	}
    	return i;
    }
    
    private void p(){
    	while(inUsing);
    	inUsing = true;
    }
    
    private void v(){
    	inUsing = false;
    }
} 
