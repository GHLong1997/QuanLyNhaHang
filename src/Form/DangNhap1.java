package Form;
import TongHop.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.omg.CORBA.portable.ApplicationException;
public class DangNhap1 extends JFrame {
	 Connection conn;
	public JTextField txtUser;
	public JPasswordField txtPass;
	public JButton btnDang,btnThoat;
	
	public DangNhap1(String title)
	{
		super(title);
		SQLService co = new SQLService();
		conn =co.connect1();
		addControls();
		addEvents();
	
		
	}


	public void showWindow() {
		this.setSize(440,284);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}


	public void addEvents() {
		
		
		txtPass.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {//nut enter
				// TODO Auto-generated method stub
				//System.out.print(arg0.getKeyCode()); 
		        if(arg0.getKeyChar()=='\n'){
		        	kiemtradangnhap();
		        } 
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnDang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kiemtradangnhap();
				
				
			}
		});
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		   System.exit(0);
				
			}
		});
	
	}
	
	public void kiemtradangnhap() {
		if((txtUser.getText().length()==0) ||(String.valueOf(txtPass.getPassword()).length()==0))
			{
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin và mật khẩu");
		
		}else
			{
		try{
				 String sql= "Select * from User2 where TenUser=? and PassWord1=?";
				  PreparedStatement prepare = conn.prepareStatement(sql);
				  prepare.setString(1, txtUser.getText());
				  prepare.setString(2, String.valueOf(txtPass.getPassword()));
				ResultSet rs=prepare.executeQuery();
				
				if(rs.next())
				{
					if(rs.getString(4).contains("Admin"))
						{
						this.hide();
						Menu ui = new Menu("Quản lý nhà hàng");
						ui.showWindow();
						}
					else
					{
						this.hide();
						Menu1 ui = new Menu1("Quản lý nhà hàng");
						ui.showWindow();
					}
				}else
				{
					JOptionPane.showMessageDialog(null,"Sai tên tài khoản hoặc mật khẩu");
				}
	     	}catch(Exception ex)
			{
	     		ex.printStackTrace();
			}
			}
	}
		


	private void addControls() {
		Container con =getContentPane();
		
		JLabel lbl = new JLabel();
	
		lbl.setIcon(new ImageIcon("img/form_4.jpg"));
		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		JPanel pnDangNhap = new JPanel();
		
		pnDangNhap.setLayout(new FlowLayout());
		
		pnMain.add(pnDangNhap);
		JLabel lblDangnhap = new JLabel("Đăng nhập");
		pnDangNhap.setBackground(Color.BLUE);
		lblDangnhap.setForeground(Color.WHITE);
		Font font = new Font("arial",Font.BOLD,20);
		lblDangnhap.setFont(font);
		pnDangNhap.add(lblDangnhap);
		
		
		JPanel pnUser = new JPanel();
		pnUser.setLayout(new FlowLayout());
		pnMain.add(pnUser);
		pnUser.setBackground(Color.BLUE);
		JLabel lblUser = new JLabel("Tài khoản");
		

		lblUser.setForeground(Color.WHITE);
		txtUser = new JTextField(18);
		pnUser.add(lblUser);
		pnUser.add(txtUser);
		
		JPanel pnPass = new JPanel();
		pnPass.setLayout(new FlowLayout());
		pnMain.add(pnPass);
		pnPass.setBackground(Color.BLUE);
		JLabel lblPass = new JLabel("Mật khẩu");
		lblPass.setForeground(Color.WHITE);
		txtPass = new JPasswordField(18);
		pnPass.add(lblPass);
		pnPass.add(txtPass);
		
		JPanel pnBut = new JPanel();
		pnBut.setLayout(new FlowLayout());
		pnMain.add(pnBut);
		JPanel pn = new JPanel();
		pn.setPreferredSize(new Dimension(34, 0));
		pnBut.add(pn);
		pnBut.setBackground(Color.BLUE);
		btnDang = new JButton("Đăng nhập");
	
		btnThoat = new JButton("Thoát");
		pnBut.add(btnDang);
		pnBut.add(btnThoat);
		
		btnThoat.setPreferredSize(btnDang.getPreferredSize());
		lblPass.setPreferredSize(lblUser.getPreferredSize());
		
	}
}
