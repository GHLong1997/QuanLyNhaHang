package Form;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThemBan extends JFrame{
	Connection conn;
	JTextField txtMa,txtBan;
	JButton btnThem;
	public ThemBan()
	{
	
		SQLService2();
		addControls();
		addEvents();
	
		
	}


	private void SQLService2() {
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


	private void addEvents() {
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					String sql = "Insert into Ban values(?,?)";
					PreparedStatement pre = conn.prepareStatement(sql);
				
					pre.setString(1, txtBan.getText());
					pre.setInt(2, 0);
					int i = pre.executeUpdate();
				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Thêm thất bại");
				}
				
			}
		});
		
	}


	private void addControls() {
		Container con = getContentPane();
		JPanel pnThem = new JPanel();
		pnThem.setLayout(new BoxLayout(pnThem, BoxLayout.Y_AXIS));
		con.add(pnThem);
		JPanel  pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMa = new JLabel("Mã Bàn");
		txtMa = new JTextField(15);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		
		JPanel  pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTen = new JLabel("Tên Bàn");
		txtBan = new JTextField(15);
		pnTen.add(lblTen);
		pnTen.add(txtBan);
		
		JPanel  pnBut = new JPanel();
		pnBut.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThem = new JButton("Thêm Bàn");
		pnBut.add(btnThem);
		
		pnThem.add(pnMa);
		pnThem.add(pnTen);
		pnThem.add(pnBut);
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		
		
	}


	public void showWindow() {
		this.setSize(250,150);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		 Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		    // get screen width, height
		    int w = this.getSize().width;
		    int h = this.getSize().height;
		    // position x, y
		    int x = (d.width - w)/2 -130;
		    int y = (d.height - h)/2 +170;
		    // set location for frame by position x, y
		    this.setLocation(x, y);
	}
}
