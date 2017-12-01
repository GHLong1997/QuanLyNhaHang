package Form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.Environment;
import org.omg.IOP.Encoding;

import TongHop.Ban;
import TongHop.BanService;
import TongHop.DanhMuc;
import TongHop.DanhMucService;
import TongHop.SanPham;
import TongHop.SanPhamService;
import TongHop.NhanVien;
import TongHop.NhanVienService;
import TongHop.SQLService;

public class QuanLyBanHang extends JFrame {
	JPanel  pnLeftofBottom,pnRightofBottom,pnLeftofBot;
	JButton btnThem,btnThanhToan,btnDat,btnIn,btnLog,btnThemB,btnXoaB;
	JTextField txtSoluong,txtKhach,txtTong,txtPhatSinh,txtTongT,txtNgay,txtMaHD;
	Connection conn;
	Vector<Object>v= new Vector<Object>();
	Vector<Ban>dsBan;
	JComboBox<DanhMuc>cbDM;
	JComboBox<NhanVien>cbNV;
	DefaultTableModel dtm,dtm1;
	JTable tbl,tbl1;
	Vector<DanhMuc>dsDM;
	Vector<SanPham>dsHH;
	Vector<NhanVien>dsNV;
	JList<SanPham>listHH;
	DanhMuc dm;
	String temp;
	int nvTemp,thanhtoantemp=0;
	SanPham hhoa;
	NhanVien nv;
	Ban ba;
	String maHD="";
	int Status=0;
	int Status1=1;
	Calendar ca =Calendar.getInstance();
	Date t=ca.getTime();
	Double S=0.0;
	String st="";
	String NgayHD="";
	SimpleDateFormat spf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public QuanLyBanHang(String title)
	{
		super(title);
		SQLService co = new SQLService();
		conn =co.connect1();
		addControls();
		addEvents();
	}

	private void addEvents() {
		
		HienThiDanhSachBan();
		LayDanhSachDanhMuc1() ;
		HienThiDanhSachNV();
		cbNV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbNV.getSelectedIndex()==-1)return;
				nv=(NhanVien) cbNV.getSelectedItem();
				
			}
		});
		cbDM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(cbDM.getSelectedIndex()==-1)return;
				 dm = (DanhMuc) cbDM.getSelectedItem();
			SanPhamService hhSv = new SanPhamService();
			dsHH =hhSv.DanhSachHangHoa(dm.getMaDM());
			listHH.setListData(dsHH);
			
				
			}
		});
	
		listHH.addMouseListener(new MouseListener() {
			
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
				hhoa=listHH.getSelectedValue();
				
			}
				
			
		});
		btnThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thanh toán hóa đơn cho '"+ba.getTenBan()+ "'","Thông báo",JOptionPane.YES_NO_OPTION);
				if(ret ==JOptionPane.YES_OPTION)
					{
					NgayHD =spf.format(t=ca.getTime());
		
							try
							{
							String sqll ="Update HoaDon set HoaDon.status1=1,NgayHD=? where HoaDon.MaBan=?";//status=1 tức là hóa đơn đã được thanh toán
							PreparedStatement ppp= conn.prepareStatement(sqll);
							ppp.setString(1, spf.format(t=ca.getTime()));
							ppp.setString(2, ba.getMaBan());
							int i =ppp.executeUpdate();
								if(i>0)
								{
									
									JOptionPane.showMessageDialog(null, "Thanh toán thành công");
									ba.setStatus(0);
									String S="Update Ban set Ban.Status2=0 where Ban.MaBan=?";//Status2=0 bàn trống ,hết người dùng
									PreparedStatement p= conn.prepareStatement(S);
									p.setString(1, ba.getMaBan());
									p.executeUpdate();
									HienThiThongTin();
									Status1=0;
									temp=ba.getMaBan();
									thanhtoantemp=1;//mục in hóa đơn(đã thanh toán)
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
								}
								
							}catch(Exception ex)
							{
								ex.printStackTrace();
							}
						
					
					}
				else
					return ;
			}
		});
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					int m=1;
					ResultSet result;
					thanhtoantemp=0;//mục in hóa đơn(chưa thanh toán)
					try{
						String s ="Select * from HoaDon where HoaDon.MaBan=? and HoaDon.status1=0";
						PreparedStatement P =conn.prepareStatement(s);
						P.setString(1, ba.getMaBan());
						result =P.executeQuery();
						if(result.next())//bàn này đã có người dùng
						{
							if(m==1)//Tức là khi chọn món ăn thì chưa có món ăn này
							{
								try{
									String sqe="Insert into ChiTietHoaDon values(?,?,?,?)";
									PreparedStatement prepe = conn.prepareStatement(sqe);
									prepe.setString(1,result.getString(1));
									prepe.setString(2,hhoa.getMaSP());
									prepe.setInt(3, Integer.parseInt(txtSoluong.getText()));
									prepe.setInt(4, hhoa.getDonGia()*Integer.parseInt(txtSoluong.getText()));
									int i2 =prepe.executeUpdate();
									HienThiThongTin();
								}catch(Exception ex)
								{
									
									m--;//neu khong them duoc thi trong chi tiết hóa đơn món ăn này đã tồn tại.
									
								}
							}
							if(m==0)//Món ăn đã tồn tại nên chỉ việc + thêm số lượng thay vì thêm mới.
							{
								try{
									m=1;//tra m ve lai gia tri ban dau
									String a="Update ChiTietHoaDon set Soluong=Soluong+? , ThanhTien=ThanhTien+? where MaSP=? and MaHD=?";
									PreparedStatement aa = conn.prepareStatement(a);
									aa.setInt(1, Integer.parseInt(txtSoluong.getText()));
									aa.setInt(2, hhoa.getDonGia()*Integer.parseInt(txtSoluong.getText()));
									aa.setString(3, hhoa.getMaSP());
									aa.setString(4, result.getString(1));
									
								
									
									int i=aa.executeUpdate();
									HienThiThongTin();
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
							}
							
						
							}else//bàn này chưa có người dùng, tức là chưa có hóa đơn
							{
								try{
									
									String sq="Insert into HoaDon values(?,?,NULL,?,?)";//Thêm mới hóa đơn
									
									PreparedStatement prep3 = conn.prepareStatement(sq);
									prep3.setString(1, txtMaHD.getText());
									prep3.setString(2,ba.getMaBan());
									//prep3.setString(3, nv.getMaNV());
									prep3.setString(3, spf.format(t));
									prep3.setInt(4, 0);
								
									int i11 =prep3.executeUpdate();
									
									maHD=txtMaHD.getText();//mục in hóa đơn
									
									String Ss="Update Ban set Ban.Status2=1 where Ban.MaBan=?";//Cập nhật lại bàn đã có người dùng
									PreparedStatement p3= conn.prepareStatement(Ss);
									p3.setString(1, ba.getMaBan());
									int ii =p3.executeUpdate();
									Status=1;//Đã ttoonf tại hóa đơn
									temp=ba.getMaBan();//mục đich chủ yếu là khi bàn đã có người dùng thì khi kich vào lại button bàn thì chuyển sang icon khac
								}catch(Exception ex)
								{
									JOptionPane.showMessageDialog(null, "Đã tồn tại mã hóa đơn này");
								}
								
								
								
								try{
									String sqe="Insert into ChiTietHoaDon values(?,?,?,?)";
									PreparedStatement prepe = conn.prepareStatement(sqe);
									prepe.setString(1,txtMaHD.getText() );
									prepe.setString(2,hhoa.getMaSP());
									prepe.setInt(3, Integer.parseInt(txtSoluong.getText()));
									prepe.setInt(4, hhoa.getDonGia()*Integer.parseInt(txtSoluong.getText()));
									int i2 =prepe.executeUpdate();
									HienThiThongTin();
								}catch(Exception ex)
								{
									
									ex.printStackTrace();
								}
							
						}
					}catch(Exception ex)
					{
						ex.printStackTrace();
				
					}}
		});
		
	
		btnThemB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ThemBan t = new ThemBan();
				t.showWindow();
				
				
			}
		});
		btnXoaB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null,"Bạn thật sư muốn xóa bàn này","Thông báo",JOptionPane.YES_NO_OPTION);
				if(ret==JOptionPane.YES_OPTION)
				{
				try{
					String sql ="Delete from Ban where Ban.MaBan=?";
					PreparedStatement pr = conn.prepareStatement(sql);
					pr.setString(1, ba.getMaBan());
					int i =pr.executeUpdate();
					if(i>0)
					{
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					}else
					{
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
			}
		});
btnIn.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			if(thanhtoantemp==0)
			{
				JOptionPane.showMessageDialog(null, "Bạn chưa thanh toán");
				return;
			}
		else if(nv==null)
			{
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên để thu ngân");
				return;
			}else
			{
				if(nvTemp==0&&maHD!="")//nvTemp=0 tức la chưa có nhân viên thanh toán hóa đơn này, maHD !"" tức là đã tồn tại hóa đơn này
				{
				String sql="update HoaDon set MaNV=? where MaHD=?";//Cập nhật lại nhân viên nào đã thanh toán hóa đơn này
				PreparedStatement pr= conn.prepareStatement(sql);
				pr.setString(1, nv.getMaNV());
				pr.setString(2,maHD);
				pr.executeUpdate();
				nvTemp=1;//Đã có nhân viên thanh toán hóa đơn này
			
			
			
				}
				
			}
			InHoaDon U = new InHoaDon("Phiếu thanh toán",ba,NgayHD,maHD);
			U.showWindow();
		
			}catch(Exception ex)
			{
				ex.printStackTrace();
			} 

	
	}
});

	}
		

		
		

	 private void LayDanhSachDanhMuc1() {
			DanhMucService dm = new DanhMucService();
		   dsDM = dm.DanhSachDanhMuc();
			for (DanhMuc d : dsDM) {
				
				cbDM.addItem(d);
			}
		
		}

	 private void HienThiDanhSachNV() {
			NhanVienService nvSv = new NhanVienService();
		   dsNV = nvSv.HienThiDanhSachNhanVien();
			for (NhanVien nv : dsNV) {
				
				cbNV.addItem(nv);
			}
		
		}
	public void HienThiThongTin()
	{
		S=0.0;
		txtTong.setText("");
		try{
			
			dtm.setRowCount(0);
			
			String Sql="select SanPham.TenSP, Soluong,DonGia,DVT, DonGia*Soluong,Ban.TenBan,Ban.MaBan,HoaDon.MaHD from SanPham,HoaDon,ChiTietHoaDon,Ban where SanPham.MaSP=ChiTietHoaDon.MaSP and HoaDon.MaHD=ChiTietHoaDon.MaHD and Ban.MaBan=HoaDon.MaBan and Ban.MaBan=?  and HoaDon.status1=0";
			PreparedStatement prepare = conn.prepareStatement(Sql);
			prepare.setString(1, ba.getMaBan());
			ResultSet result =prepare.executeQuery();
			
			while(result.next())
			{
				Vector<Object>vec = new Vector<Object>();
				vec.add(result.getString(1));
				vec.add(result.getInt(2));
				vec.add(result.getInt(3));
				vec.add(result.getString(4));
				vec.add(result.getInt(5));
				
				S=S+result.getInt(5);//Thành tiền= số lượng * đơn giá
				dtm.addRow(vec);
			}
			
			txtTong.setText(S.toString());//Hiển thị tổng tiền của món ăn
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public void HienThiDanhSachBan() {
	
		BanService dm = new BanService();
		dsBan = dm.DanhSachBan();
		  for (Ban ban : dsBan) {
			  JPanel pn = new JPanel();
			  pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
			  JPanel p= new JPanel();
			  pn.add(p);
			JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(75, 75));
			btn.setIcon(new ImageIcon("img/n.jpg"));
			JLabel lbl= new JLabel(ban.getTenBan());
			lbl.setAlignmentX(CENTER_ALIGNMENT);
			p.add(btn);
			pn.add(lbl);
			ba =ban;
		
			 switch(ban.getStatus())
	  			{
	  			case 0://Bàn chưa có người dùng
	  				btn.setBackground(Color.CYAN);
	  				btn.setIcon(new ImageIcon("img/n.jpg"));
	  				break;
	  			default://Bàn đã có người dùng
	  				btn.setIcon(new ImageIcon("img/u.jpg"));
	  				btn.setBackground(Color.GREEN);
	  				break;
	  			}
			pnLeftofBot.add(pn);
              btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					S=0.0;//mục thanh toán
					txtTong.setText("");//mục thanh toán
					txtMaHD.setText("");//mục thanh toán
					try{
						
						dtm.setRowCount(0);
						
						String Sql="select SanPham.TenSP, Soluong,DonGia,DVT, DonGia*Soluong,Ban.TenBan,Ban.MaBan,HoaDon.MaHD from SanPham,HoaDon,ChiTietHoaDon,Ban where SanPham.MaSP=ChiTietHoaDon.MaSP and HoaDon.MaHD=ChiTietHoaDon.MaHD and Ban.MaBan=HoaDon.MaBan and Ban.MaBan=? and HoaDon.status1=0";
						PreparedStatement prepare = conn.prepareStatement(Sql);
						prepare.setString(1, ban.getMaBan());
						ResultSet result =prepare.executeQuery();
						ba = ban;
						
						while(result.next())
						{
							Vector<Object>vec = new Vector<Object>();
							vec.add(result.getString(1));
							vec.add(result.getInt(2));
							vec.add(result.getInt(3));
							vec.add(result.getString(4));
							vec.add(result.getInt(5));
							maHD=result.getString(8);
							S=S+result.getInt(5);//Thành tiền= số lượng * đơn giá
							dtm.addRow(vec);
							int i=tbl.getRowCount();
							txtMaHD.setText(maHD);//hiển thị mã HD theo bàn
							
						}	
						 
						
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					txtTong.setText(S.toString());
					if(Status==1&&ba.getMaBan()==temp)//Status==1 :đã có hóa đơn, ba.getMaBan()==temp:tức là chuyển đôi icon button theo mã
					{
						btn.setIcon(new ImageIcon("img/u.jpg"));
		  				btn.setBackground(Color.GREEN);
		  				Status=0;
		  				temp="";
					}
					if(Status1==0&&ba.getMaBan()==temp)
					{
						btn.setBackground(Color.CYAN);
		  				btn.setIcon(new ImageIcon("img/n.jpg"));
		  				Status1=1;
		  				temp="";
					}
					//Khi click vào 1 button(1 bàn ) thì nvTemp=0,nv=null;
					nvTemp=0;//mục in hóa đơn(thiết lập chưa có nhân vvieen  thanh toán hóa đơn này)
					nv=null;//mục in hóa đơn(thiết lập chưa chọn nhân viên thanh toán hóa đơn)
					
				}
				
			});
              
            
			
		}
	}

	
	private void addControls() {
		
		Container con = getContentPane();
		
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 100));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new  BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,pnTop,pnBottom);
		sp.setOneTouchExpandable(true);
		con.add(sp,BorderLayout.CENTER);
		
		JPanel pnT = new JPanel();
		pnT.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl = new JLabel("Quản lý bán hàng");
		Font ff= new Font("arial",Font.BOLD,23);
		lbl.setFont(ff);
		lbl.setForeground(Color.BLUE);
		pnT.add(lbl);
		pnTop.add(pnT,BorderLayout.CENTER);
		
		pnLeftofBottom = new JPanel();
		pnLeftofBottom.setPreferredSize(new Dimension(280,0));
		pnLeftofBottom.setLayout(new BorderLayout());
	pnLeftofBot = new JPanel();
		
		pnLeftofBot.setLayout(new GridLayout(6,3));
	 pnRightofBottom = new JPanel();
		pnRightofBottom.setLayout(new BorderLayout());
	

		JScrollPane sc1=new JScrollPane(pnLeftofBot,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnLeftofBottom.add(sc1, BorderLayout.CENTER);
		pnBottom.add(pnLeftofBottom,BorderLayout.WEST);
		pnBottom.add(pnRightofBottom, BorderLayout.CENTER);
		
		JPanel pnTopOfRightOfBottom = new JPanel();
		pnTopOfRightOfBottom.setLayout(new BorderLayout());
		pnRightofBottom.add(pnTopOfRightOfBottom,BorderLayout.NORTH);
		pnTopOfRightOfBottom.setPreferredSize(new Dimension(0, 130));

	
		
		JPanel pnDanhMuc = new JPanel();
		pnDanhMuc.setLayout(new BorderLayout());
		cbDM =new JComboBox<DanhMuc>();
		cbDM.setPreferredSize(new Dimension(220, 25));
		listHH = new JList<SanPham>();
		JScrollPane sc12=new JScrollPane(listHH,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnDanhMuc.add(sc12,BorderLayout.CENTER);
		pnDanhMuc.add(cbDM,BorderLayout.NORTH);
		pnTopOfRightOfBottom.add(pnDanhMuc,BorderLayout.EAST);
		
		JPanel pnThemMoi = new JPanel();
		pnThemMoi.setLayout(new BoxLayout(pnThemMoi, BoxLayout.Y_AXIS));
		pnTopOfRightOfBottom.add(pnThemMoi,BorderLayout.CENTER);

		JPanel pnMaHD = new JPanel();
		pnMaHD.setLayout(new BoxLayout(pnMaHD, BoxLayout.X_AXIS));
		pnThemMoi.add(pnMaHD);
		JPanel pnM = new JPanel();
		pnMaHD.add(pnM);
		pnM.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMa= new JLabel("Mã HĐ :");
		txtMaHD = new JTextField(5);
		pnM.add(lblMa);
		pnM.add(txtMaHD);
		txtMaHD.setPreferredSize(new Dimension(40, 20));
		lblMa.setForeground(Color.BLUE);
		
		JPanel pnS = new JPanel();
		pnS.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnMaHD.add(pnS);
		JLabel lblSo = new JLabel("Số lượng:");
		txtSoluong = new JTextField(3);
		pnS.add(lblSo);
		pnS.add(txtSoluong);
		txtSoluong.setPreferredSize(new Dimension(25, 25));
		
		JPanel pnNgayLap = new JPanel();
		pnNgayLap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgay = new JLabel("Ngày Lập HĐ:");
		lblNgay.setForeground(Color.BLUE);
		txtNgay = new JTextField();
		pnNgayLap.add(lblNgay);
		pnNgayLap.add(txtNgay);
	//	pnThemMoi.add(pnNgayLap);
	txtNgay.setPreferredSize(new Dimension(175, 20));
		

		JPanel pnNhan = new JPanel();
		pnNhan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNhan = new JLabel("Nhân viên:");
		lblNhan.setForeground(Color.BLUE);
		cbNV = new JComboBox<NhanVien>();
		cbNV.setPreferredSize(new Dimension(175, 22));
		pnNhan.add(lblNhan);
		pnNhan.add(cbNV);
		pnThemMoi.add(pnNhan);
		
		JPanel pnThem = new JPanel();
		pnThem.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel pn = new JPanel();
		pn.setPreferredSize(new Dimension(87, 0));
		pnThem.add(pn);
		btnThem = new JButton("Gọi món");
		btnDat= new JButton("Đặt bàn");
		pnThem.add(btnThem);
		pnThem.add(btnDat);
		btnThem.setPreferredSize(new Dimension(80, 30));
		btnDat.setPreferredSize(new Dimension(80, 30));
		pnThemMoi.add(pnThem);
		
		lblNhan.setPreferredSize(lblNgay.getPreferredSize());
		lblMa.setPreferredSize(lblNgay.getPreferredSize());
		lblSo.setForeground(Color.BLUE);
		
		JPanel pnThemBan = new JPanel();
		pnThemBan.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThemB = new JButton("Thêm");
		btnXoaB = new JButton("Xóa");
		pnThemBan.add(btnThemB);
		pnThemBan.add(btnXoaB);
		pnLeftofBottom.add(pnThemBan, BorderLayout.SOUTH);
		btnThemB.setPreferredSize(btnThem.getPreferredSize());
		btnXoaB.setPreferredSize(btnThem.getPreferredSize());
	
		
	
		
		dtm = new DefaultTableModel();
		dtm.addColumn("Tên Hàng Hóa");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Đơn giá");
		dtm.addColumn("ĐVT");
		dtm.addColumn("Thành tiền");
		tbl = new JTable(dtm);
	    JScrollPane sc11=new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    pnRightofBottom.add(sc11,BorderLayout.CENTER);
		
	    JPanel pnBottomOfRightOfBottom = new JPanel();
	    pnBottomOfRightOfBottom.setLayout(new BorderLayout());
	    pnBottomOfRightOfBottom.setPreferredSize(new Dimension(0, 45));
	    pnRightofBottom.add(pnBottomOfRightOfBottom,BorderLayout.SOUTH);
	    JPanel pnChiTiet =new JPanel();
	   
	    pnBottomOfRightOfBottom.add(pnChiTiet,BorderLayout.CENTER);
	    
	    JPanel pnTongTien = new JPanel();
	    pnTongTien.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel lblTong = new JLabel("Tổng tiển:");
	    lblTong.setForeground(Color.RED);
	    txtTong = new JTextField(20);
	    txtTong.setPreferredSize(new Dimension(300, 30));
	    txtTong.setForeground(Color.RED);
	    pnTongTien.add(lblTong);
	    pnTongTien.add(txtTong);
	    pnChiTiet.add(pnTongTien);
	    
	    Font font = new Font("arial", Font.BOLD, 15);
	    lblTong.setFont(font);
	    

	    
	    
	    JPanel pnThanhToan = new JPanel();
	    pnThanhToan.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    pnBottomOfRightOfBottom.add(pnThanhToan,BorderLayout.EAST);
	    btnIn = new JButton();
	    btnIn.setIcon(new ImageIcon("img/412.png"));
	    btnThanhToan =new JButton("Thanh toán");
	    btnThanhToan.setForeground(Color.BLUE);
	    pnThanhToan.add(btnThanhToan);
	    pnThanhToan.add(btnIn);
	    btnThanhToan.setPreferredSize(new Dimension(100,35));
	    btnIn.setPreferredSize(btnThanhToan.getPreferredSize());
	    TitledBorder title= new TitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Danh Mục Bàn",TitledBorder.CENTER,0);
	    title.setTitleColor(Color.BLUE);
	    pnLeftofBottom.setBorder(title);
	   txtNgay.setEnabled(false);
	   txtTong.setEditable(false);
	}
	public void showWindow()
	{
		this.setSize(1000,650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	this.setResizable(false);
	}
	public void offWindow()
	{
		this.setVisible(false);
	}
}










 











