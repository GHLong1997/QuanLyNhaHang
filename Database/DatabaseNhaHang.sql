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
(1,'B�n 01',0),
(2,'B�n 02',0),
(3,'B�n 03',0),
(4,'B�n 04',0),
(5,'B�n 05',0),
(6,'B�n 06',0),
(7,'B�n 07',0),
(8,'B�n 08',0),
(9,'B�n 09',0),
(10,'B�n 10',0),
(11,'B�n 11',0),
(12,'B�n 12',0),
(13,'B�n 13',0),
(14,'B�n 14',0)







