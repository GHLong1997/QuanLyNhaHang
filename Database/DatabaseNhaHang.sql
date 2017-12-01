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







