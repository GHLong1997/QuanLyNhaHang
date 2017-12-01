CREATE Database QuanLyNhaHang
CREATE TABLE User2
(
MaUser integer PRIMARY KEY,
TenUser nvarchar(100),
PassWord1  nvarchar(50),
Administrator nvarchar(100)

)

Insert into User2(MaUser,TenUser,PassWord1,Administrator) Values
(1,'Admin','Admin','Admin'),
(2,'NV','NV','NV')

CREATE TABLE DanhMuc
(
MaDM integer PRIMARY KEY,
TenDM nvarchar(100)
)

CREATE TABLE SanPham
(
MaSP integer PRIMARY KEY,
TenSP nvarchar(100),
DVT nvarchar(50),
DonGia integer,
MaDM integer
ConStraint FR_SP FOREIGN KEY(MaDM) REFERENCES DanhMuc(MaDM)
)

CREATE TABLE Ban
(
MaBan integer PRIMARY KEY,
TenBan nvarchar(100),
Status2 integer DEFAULT 0,
)


CREATE TABLE NhanVien
(
	MaNV varchar(20) PRIMARY KEY,
	TenNV nvarchar(100),
	GioiTinh nvarchar(20),
	DiaChi nvarchar(200),
	SDT nvarchar(20),
	NgayVaoLam nvarchar(20),
	CongViec nvarchar(100)
)

CREATE TABLE HoaDon
(
	MaHD integer PRIMARY KEY,
	MaBan integer,
	MaNV  varchar(20),
	NgayHD nvarchar(200),
	Status1 nvarchar(20),
	ConStraint FR_NV FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV),
	ConStraint FR_BAN FOREIGN KEY (MaBan) REFERENCES Ban(MaBan)
)

CREATE TABLE ChiTietHoaDon
(
	MaHD integer,
	MaSP integer,
	Soluong  integer,
	ThanhTien nvarchar(200)
	ConStraint PK PRIMARY KEY(MaHD,MaSP),
	ConStraint FR_HD FOREIGN KEY(MaHD) REFERENCES HoaDon(MaHD),
	ConStraint FR_SPP FOREIGN KEY(MaSP) REFERENCES SanPham(MaSP),

)

Insert into Ban(MaBan,TenBan,Status2) values
(1,'Bàn 01',0),
(2,'Bàn 02',0),
(3,'Bàn 03',0),
(4,'Bàn 04',0),
(5,'Bàn 05',0),
(6,'Bàn 06',0),
(7,'Bàn 07',0),
(8,'Bàn 08',0),
(9,'Bàn 09',0),
(10,'Bàn 10',0),
(11,'Bàn 11',0),
(12,'Bàn 12',0),
(13,'Bàn 13',0),
(14,'Bàn 14',0)







