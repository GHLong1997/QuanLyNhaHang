package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import TongHop.SQLService;

public class ThongKe extends JFrame{
	DefaultTableModel dtm ;
	JTable tbl;
	Connection conn;
	JButton btnT;
	SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
	
	public ThongKe(String title)
	{
		super(title);
		SQLService co = new SQLService();
		conn =co.connect1();
	addControls();
	addEvent();
	}
	
	private void addEvent() {
		btnT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HienThiDoanhThu();
				
			}
		});
		
	}

	private void HienThiDoanhThu() {
		
		try{
			int i=1;
			String sql="Select HoaDon.MaHD,MaNV,TenBan,NgayHD,ThanhTien from ChiTietHoaDon,HoaDon,Ban where HoaDon.MaHD=ChiTietHoaDon.MaHD and HoaDon.MaBan=Ban.MaBan and HoaDon.status1=1 Order by NgayHD ASC";
			
			PreparedStatement prepare =conn.prepareStatement(sql);
			
			ResultSet rs= prepare.executeQuery();
			dtm.setRowCount(0);
			while(rs.next())
			{
				Vector<Object>vec = new Vector<Object>();
				vec.add(i);
				vec.add(rs.getString(1));
				vec.add(rs.getString(2));
				vec.add(rs.getString(3));
				
				vec.add(rs.getString(4));
				vec.add(rs.getInt(5));
				dtm.addRow(vec);
				i++;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 120));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,pnTop,pnBottom);
		sp.setOneTouchExpandable(true);
		con.add(sp,BorderLayout.CENTER);
		
		JPanel pnT = new JPanel();
		pnT.setPreferredSize(new Dimension(0, 40));
		pnTop.add(pnT,BorderLayout.NORTH);
		
		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setLayout(new FlowLayout());
		btnT = new JButton();
		btnT.setText("Thống kê");
		btnT.setBackground(Color.LIGHT_GRAY);
		Font fontTieuDe = new Font("arial",Font.PLAIN,20);
		btnT.setFont(fontTieuDe);
		pnTieuDe.add(btnT);
		pnTop.add(pnTieuDe,BorderLayout.CENTER);
		
		dtm =new DefaultTableModel();
		dtm.addColumn("STT");
		dtm.addColumn("Mã HĐ");
		dtm.addColumn("Mã Nhân Viên");
		dtm.addColumn("Bàn");
		dtm.addColumn("Thời gian");
		dtm.addColumn("Doanh thu");
		tbl = new JTable(dtm);
		JScrollPane sc = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottom.add(sc, BorderLayout.CENTER);
	
		
	}

	public void showWindow()
	{
		this.setSize(900,650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	this.setResizable(false);
	}
}
