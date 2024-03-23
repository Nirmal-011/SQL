package com.Employee;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import  java.sql.PreparedStatement;
public class Employee {

	public static void main(String[] args)throws SQLException {
		String driver="com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/GL?useSSL=false", "root", "Nirmal_11_09_2001");
		//Create a table employee in MYSQL database “GL”
		createtable(con);
		//a. Insert 5 records.
		insertelement(con);
	    //b. Modify Email_Id column to varchar(30) NOT NULL.
	    Modify(con);
	    //c.Insert 2 records and check.
	    insertelement(con);
	    //d. Update the name of employee Id 3 to Karthik and phone number to 1234567890.
	    UpdateTable(con);
	    //e. Delete employee records 3 and 4.
	    DeleteRecord(con);
	    //f. Remove all records from the table employee.
	    removeall(con);
	    
		
	}
	public static void createtable(Connection con) {
		try {
			String query="create table employee (id int primary key ,emp_name varchar(50)  not null, email_id  varchar(50) not null, phn_no varchar(15))";
			Statement st=con.createStatement();
			st.execute(query);
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	public static void insertelement(Connection connection) {
		try {
			String query="insert into employee ( id, emp_name, email_Id, Phn_no) values(?,?,?,?)";
			PreparedStatement ps= connection.prepareStatement(query);
			for(int i=1;i<=5;i++) {
				ps.setInt(1, i);
				ps.setString(2, "Name "+i);
				ps.setString(3, "email"+i+"@gmail.com");
				ps.setString(4, "1254234512"+i);
			}
		    System.out.println("Inserted Successfully");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void  Modify(Connection con){
		try {
			String query ="Alter Table employee Modify column email_Id varchar(30) NOT NULL";
			PreparedStatement ps=con.prepareStatement(query);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void UpdateTable(Connection con) {
		try {
			String query="Update employee set emp_name=? ,phn_no=? where id=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, "Karthick");
			st.setString(2, "1234567890");
			st.setInt(3, 3);
			st.executeUpdate();
			System.out.println("Updated Successfully");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void DeleteRecord(Connection con) {
		try {
			String query="delete from employee where id=? or id=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,3);
			ps.setInt(2, 4);
			ps.executeUpdate();
			System.out.println("Successfully deleted");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void removeall(Connection con) {
		
		try {
			String query="truncate table employee";
			PreparedStatement ps=con.prepareStatement(query);
			ps.executeUpdate();
			System.out.println("Truncated");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
