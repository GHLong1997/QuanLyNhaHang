package TongHop;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SQLService {
	public static Connection conn;
	
	public  SQLService(){
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl="jdbc:sqlserver://DESKTOP-M75UEQH:1433;databaseName=QuanLyNhaHang;integratedSecurity=true;";
		conn= DriverManager.getConnection(connectionUrl);
	    
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "kết nối cơ sở dữ liệu thất bại");
			e.printStackTrace();
		}
	}
	public void connect(){
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl="jdbc:sqlserver://DESKTOP-M75UEQH:1433;databaseName=QuanLyNhaHang;integratedSecurity=true;";
		conn= DriverManager.getConnection(connectionUrl);
	    
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "kết nối cơ sở dữ liệu thất bại");
			e.printStackTrace();
		}
	}
	public Connection connect1(){
		Connection con;
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl="jdbc:sqlserver://DESKTOP-M75UEQH:1433;databaseName=QuanLyNhaHang;integratedSecurity=true;";
		con= DriverManager.getConnection(connectionUrl);
		return con;
	    
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "kết nối cơ sở dữ liệu thất bại");
			e.printStackTrace();
		}
		return null;
	}
	
}
