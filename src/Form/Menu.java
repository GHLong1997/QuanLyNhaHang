package Form;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Menu extends JFrame{
	Connection conn;
	JButton btnNV,btnHH,btnBan,btnThong,btnLog;
	
	public Menu(String title)
	{
		super(title);
		addControls();
		addEvents();
		
	}

	private void addEvents() {
		
		btnHH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				GiaoDienQLHH();
				
			}
			private void GiaoDienQLHH() {
				QuanLySanPham S=new QuanLySanPham("Quản lý hàng hóa");
				S.showWindow();
				
			}
			
		});
		
		btnNV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuanLyNhanVien S=new QuanLyNhanVien("Quản lý nhân viên");
				S.showWindow();
				
			}
		});
		btnBan.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		QuanLyBanHang S=new QuanLyBanHang("Quản lý bán hàng");
		S.showWindow();
		
	}
});
btnThong.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ThongKe S=new ThongKe("Thống kê");
		S.showWindow();
		
	}
});
btnLog.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logout();
		DangNhap1 S= new DangNhap1("Đăng nhập");
		S.showWindow();
		
	}
});

	}
	public void logout()
	{
		this.setVisible(false);
	}

	private void addControls() {
	Container con =getContentPane();
	con.setLayout(new BorderLayout());

	
	
	JPanel pnBottom = new JPanel();
	pnBottom.setLayout(new BorderLayout());
	
	JLabel lbl1 = new JLabel();
	ImageIcon img1 = new ImageIcon("img/4.png");
	

	lbl1.setIcon(img1);

	pnBottom.add(lbl1,BorderLayout.CENTER);
	JLabel lbl = new JLabel();
	ImageIcon img = new ImageIcon("img/v1.jpg");
	

	lbl.setIcon(img);

	pnBottom.add(lbl,BorderLayout.SOUTH);
	
	con.add(pnBottom,BorderLayout.SOUTH);
	JPanel pnBut= new JPanel();
	pnBut.setLayout(new FlowLayout(FlowLayout.LEFT));
	btnNV = new JButton("Nhân Viên");
	btnHH = new JButton("Sản phẩm");
	btnBan= new JButton("Bán Hàng");
	btnThong = new JButton("Thống kê");
	btnLog = new JButton();
	btnLog.setIcon(new ImageIcon("img/31.png"));
	pnBut.add(btnHH);
	btnHH.setIcon(new ImageIcon("img/112.png"));
	
	pnBut.add(btnNV);
	btnNV.setIcon(new ImageIcon("img/staff1.png"));
	pnBut.add(btnBan);
	btnBan.setIcon(new ImageIcon("img/ic1s1.png"));
	pnBut.add(btnThong);
	btnThong.setIcon(new ImageIcon("img/1.png"));
	pnBut.add(btnLog);
	pnBottom.add(pnBut, BorderLayout.NORTH);
		
	}
	public void showWindow()
	{
		this.setSize(600,500);
		this.setVisible(true);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
	}
}
