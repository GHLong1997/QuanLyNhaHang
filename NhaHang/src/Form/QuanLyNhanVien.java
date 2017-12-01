package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import TongHop.*;

public class QuanLyNhanVien extends JFrame {
	
	Connection conn;
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
	DefaultTableModel dtm;
	JTable tbl;
	JTextField txtMa,txtTen,txtSDT,txtNgay,txtDiaChi,txtChucvu,txtTim;
	JRadioButton chbNam,chbNu;
	Vector<NhanVien>dsNV;
	NhanVien nv;
	JButton btnThem,btnSua,btnXoa,btnTaoMoi,btnTim;

	public QuanLyNhanVien(String Title)
	{
		
		super(Title);
		SQLService co= new SQLService();
		conn= co.connect1();
		addControls();
		addEvents();
		
	}

	
	public void addControls()
	{
		Container con = getContentPane();
		
		JPanel pnTop= new JPanel();
		pnTop.setPreferredSize(new Dimension(0, 400));
		pnTop.setLayout(new GridLayout(1, 2));
		
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,pnTop,pnBottom);
		
		sp.setOneTouchExpandable(true);
		con.add(sp);
		
			JPanel pnLeftOfGrid= new JPanel();
		  
		    pnLeftOfGrid.setLayout(new BoxLayout(pnLeftOfGrid, BoxLayout.Y_AXIS));
		    JPanel pnMa = new JPanel();
		    pnTop.add(pnLeftOfGrid);
		    pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnLeftOfGrid.add(pnMa);
		    JLabel lblMa= new JLabel("Mã Nhân Viên:");
//		    lblMa.setForeground(Color.BLUE);
		    txtMa= new JTextField(20);
		    pnMa.add(lblMa);
		    pnMa.add(txtMa);


		    JPanel pnTen = new JPanel();
		    pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnLeftOfGrid.add(pnTen);
		    JLabel lblTen= new JLabel("Tên Nhân Viên:");
//		    lblTen.setForeground(Color.BLUE);
		    txtTen= new JTextField(20);
		    pnTen.add(lblTen);
		    pnTen.add(txtTen);
		    
		    JPanel pnSDT = new JPanel();
		    pnSDT.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnLeftOfGrid.add(pnSDT);
		    JLabel lblSDT= new JLabel("Số Điện Thoại:");
//		    lblSDT.setForeground(Color.BLUE);
		    txtSDT= new JTextField(20);
		    pnSDT.add(lblSDT);
		    pnSDT.add(txtSDT);
		    
		    JPanel pnButton = new JPanel();
		    pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnLeftOfGrid.add(pnButton);
		    btnTaoMoi = new JButton("Refresh");
		   
		    btnThem = new JButton("Thêm");
		   
		    btnSua = new JButton("Sửa");
		   
		    btnXoa = new JButton("Xóa");
		   
		    pnButton.add(btnTaoMoi);
		    pnButton.add(btnThem);
		    pnButton.add(btnSua);
		    pnButton.add(btnXoa);
//		    btnTaoMoi.setForeground(Color.BLUE);
//		    btnThem.setForeground(Color.BLUE);
//		    btnSua.setForeground(Color.BLUE);
//		    btnXoa.setForeground(Color.BLUE);
//		    
		    JPanel pnTim = new JPanel();
		    pnTim.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnLeftOfGrid.add(pnTim);
		    txtTim = new JTextField(17);
		    txtTim.setPreferredSize(new Dimension(150, 30));
		    btnTim = new JButton("Tìm Theo Tên");
		    btnTim.setIcon(new ImageIcon("img/13.png"));
//		    btnTim.setForeground(Color.BLUE);
		    pnTim.add(txtTim);
		    pnTim.add(btnTim);
		    JPanel pnRong = new JPanel();
		 
		    JLabel lbls = new JLabel(".");
		    lbls.setPreferredSize(new Dimension(0, 80));
		    lbls.setForeground(Color.WHITE);
		    pnRong.add(lbls);
		    pnLeftOfGrid.add(pnRong);
		   btnThem.setPreferredSize(btnTaoMoi.getPreferredSize());
		   btnSua.setPreferredSize(btnTaoMoi.getPreferredSize());
		   btnXoa.setPreferredSize(btnTaoMoi.getPreferredSize());
		    
		    
		    JPanel pnRightOfGrid = new JPanel();
		    pnRightOfGrid.setLayout(new BoxLayout(pnRightOfGrid, BoxLayout.Y_AXIS));
		    pnTop.add(pnRightOfGrid);
		    JPanel pnDiaChi = new JPanel();
		    pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnRightOfGrid.add(pnDiaChi);
		    JLabel lblDiaChi= new JLabel("Địa chỉ:");
//		    lblDiaChi.setForeground(Color.BLUE);
		    txtDiaChi= new JTextField(20);
		    pnDiaChi.add(lblDiaChi);
		    pnDiaChi.add(txtDiaChi);
  
		    JPanel pnNgay = new JPanel();
		    pnNgay.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnRightOfGrid.add(pnNgay);
		    JLabel lblNgay= new JLabel("Ngày Vào Làm:");
//		    lblNgay.setForeground(Color.BLUE);
		    txtNgay= new JTextField(20);
		    pnNgay.add(lblNgay);
		    pnNgay.add(txtNgay);
		    
		    JPanel pnChuc = new JPanel();
		    pnChuc.setLayout(new FlowLayout(FlowLayout.LEFT));
		    pnRightOfGrid.add(pnChuc);
		    JLabel lblChuc= new JLabel("Chức Vụ:");
//		    lblChuc.setForeground(Color.BLUE);
		    txtChucvu= new JTextField(20);
		    pnChuc.add(lblChuc);
		    pnChuc.add(txtChucvu);
		  

		   JPanel pnCheck = new JPanel();
		   pnCheck.setLayout(new FlowLayout(FlowLayout.LEFT));
		   pnRightOfGrid.add(pnCheck);
		   JLabel lblCheck = new JLabel("Giới tính:");
//		   lblCheck.setForeground(Color.BLUE);
		   chbNam = new JRadioButton("Nam");
		   chbNu = new JRadioButton("Nữ");
		   ButtonGroup group=new ButtonGroup();
		   group.add(chbNam);
		   group.add(chbNu);
		   pnCheck.add(lblCheck);
		   pnCheck.add(chbNam);
		   pnCheck.add(chbNu);
		   
		   JPanel pnTrong = new JPanel();
		   pnRightOfGrid.add(pnTrong);
		   
		  lblMa.setPreferredSize(lblTen.getPreferredSize());
		  lblSDT.setPreferredSize(lblTen.getPreferredSize());
		  lblDiaChi.setPreferredSize(lblNgay.getPreferredSize());
		  lblChuc.setPreferredSize(lblNgay.getPreferredSize());
		  lblCheck.setPreferredSize(lblNgay.getPreferredSize());
		  
		  TitledBorder titleBorder=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Thông tin chi tiết",TitledBorder.CENTER, 0);
		    titleBorder.setTitleColor(Color.BLUE);
		   pnTop.setBorder(titleBorder);
		  
		
		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Nhân Viên");
		dtm.addColumn("Tên Nhân Viên");
		dtm.addColumn("Giới Tính");
		dtm.addColumn("Địa Chỉ");
		dtm.addColumn("SĐT");
		dtm.addColumn("Ngày Vào Làm");
		dtm.addColumn("Chức vụ");
		tbl = new JTable(dtm);
	    JScrollPane sc1=new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    pnBottom.add(sc1,BorderLayout.CENTER);
//		tbl.setForeground(Color.BLUE);
//	
	}
	public void addEvents()
	{
		HienThiDanhSachNhanVien();
		tbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row=tbl.getSelectedRow();
				if(row==-1)return;
				 nv =dsNV.get(row);
				txtMa.setText(nv.getMaNV());
				txtTen.setText(nv.getTenNV());
				if(nv.getGioiTinh().contains("Nam"))
				{
					chbNam.doClick();
				}else
				{
					chbNu.doClick();
				}
				txtDiaChi.setText(nv.getDiaChi());
				txtSDT.setText(nv.getSDT());
				txtNgay.setText(nv.getNgayVao());
				txtChucvu.setText(nv.getCongviec());
			}
		});
		btnTaoMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				txtMa.setText("");
				txtTen.setText("");

				txtDiaChi.setText("");
				txtSDT.setText("");
				txtNgay.setText("");
				txtChucvu.setText("");
				HienThiDanhSachNhanVien();
			}
		});
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					
				NhanVien nv = new NhanVien();
				nv.setMaNV(txtMa.getText());
				nv.setTenNV(txtTen.getText());
				if(chbNam.isSelected()==true)
				{
					nv.setGioiTinh(chbNam.getText());
				}
				else
				{
					nv.setGioiTinh(chbNu.getText());
				}
				nv.setDiaChi(txtDiaChi.getText());
				nv.setSDT(txtSDT.getText());
				nv.setNgayVao(txtNgay.getText());
				nv.setCongviec(txtChucvu.getText());
				NhanVienService nvSV= new NhanVienService();
				if(nvSV.ThemNhanVien(nv)>0)
				{
					JOptionPane.showMessageDialog(null, "Đã thêm mới");
					HienThiDanhSachNhanVien();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Thêm thất bại");
				}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					
				String sql="Delete from NhanVien where MaNV=?";
				
				PreparedStatement prepare = conn.prepareStatement(sql);
				
				prepare.setString(1,nv.getMaNV() );
				int i =prepare.executeUpdate();
				
				if(i>0)
				{
					JOptionPane.showMessageDialog(null, "Xóa thành công");
					HienThiDanhSachNhanVien();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Xóa thất bại");
				}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null,"Bạn muốn cập nhật lại thay đổi","Thông báo",JOptionPane.YES_NO_OPTION);
				if(ret==JOptionPane.YES_OPTION)
				{
				try{
					String gt="";
					if(chbNam.isSelected()==true)
					{gt=gt+chbNam.getText();}
					else
					{gt=gt+chbNu.getText();}
					String sql="Update NhanVien set TenNV=?,GioiTinh=?,DiaChi=?,SDT=?,NgayVaoLam=?,CongViec=? where MaNV=?";
					PreparedStatement prepare=conn.prepareStatement(sql);
					prepare.setString(1, txtTen.getText());
					prepare.setString(2, gt);
					prepare.setString(3, txtDiaChi.getText());
					prepare.setString(4, txtSDT.getText());
					prepare.setString(5, txtNgay.getText());
					prepare.setString(6, txtChucvu.getText());
					prepare.setString(7,txtMa.getText());
					int i=prepare.executeUpdate();
					if(i>0)
					{
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						HienThiDanhSachNhanVien();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				}
				
			}
		});
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				Vector<Object>vc = new Vector<Object>();
					String sql ="select * from NhanVien where TenNV like ?";
					PreparedStatement prepare =conn.prepareStatement(sql);
					prepare.setString(1, txtTim.getText());
					ResultSet result=prepare.executeQuery();
					dtm.setRowCount(0);
					while(result.next())
					{	
						vc.add(result.getString(1));
						vc.add(result.getString(2));
						vc.add(result.getString(3));
						vc.add(result.getString(4));
						vc.add(result.getString(5));
						vc.add(result.getString(6));
						vc.add(result.getString(7));
						
						dtm.addRow(vc);
					}
					
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
	}
	private void HienThiDanhSachNhanVien() {
		NhanVienService nvSv= new NhanVienService();
		dtm.setRowCount(0);
		dsNV=nvSv.HienThiDanhSachNhanVien();
		for (NhanVien nhanVien : dsNV) {
			Vector<Object>vec = new Vector<Object>();
			vec.add(nhanVien.getMaNV());
			vec.add(nhanVien.getTenNV());
			vec.add(nhanVien.getGioiTinh());
			vec.add(nhanVien.getDiaChi());
			vec.add(nhanVien.getSDT());
			vec.add(nhanVien.getNgayVao());
			vec.add(nhanVien.getCongviec());
			dtm.addRow(vec);
		}
		
		
		
	}
	public void showWindow()
	{
		this.setSize(900,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
