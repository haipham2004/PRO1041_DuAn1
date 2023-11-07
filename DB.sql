
----Update:2023-11-07--lúc 15g50p--
CREATE DATABASE AdamStores
GO
USE AdamStores
GO 
CREATE TABLE [TaiKhoan] (
  [MaTK] varchar(10),
  [UserName] varchar(20),
  [PassWord] varchar(20),
  [Role] varchar(10),
  [TrangThai] bit,
  PRIMARY KEY ([MaTK])
);
CREATE TABLE [NhanVien] (
  [MaNV] varchar(10),
  [MaTK] varchar(10) not null,
  CONSTRAINT FK_NV_TK foreign key(MaTK) references TaiKhoan(MaTK),
  [HoTen] nvarchar(50),
  [GioiTinh] bit,
  [DiaChi] nvarchar(100),
  [SoDienThoai] varchar(10),
  [CCCD] nvarchar(50),
  [NgayVaoLam] date,
  [TrangThai] bit,
  [Anh] varchar(MAX),
  PRIMARY KEY ([MaNV])
);

CREATE TABLE [KhachHang] (
  [MaKH] varchar(10),
  [HoTen] nvarchar(50),
  [NgaySinh] date,
  [SoDienThoai] varchar(10),
  [Email] varchar(100),
  [GioiTInh] bit,
  [DiaChi] nvarchar(100),
  [TrangThai] bit,
  PRIMARY KEY ([MaKH])
);

CREATE TABLE [HinhThucThanhToan] (
  [MaHTTT] varchar(10),
  [TenHTTT] nvarchar(50),
  [TrangThai] bit,
  PRIMARY KEY ([MaHTTT])
);
CREATE TABLE [Eventa] (
  [MaEV] varchar(10),
  [TenEV] nvarchar(50),
  [HinhThuc] nvarchar(100),
  [MucGiamGia] varchar(100),
  [ThoiGianBatDau] date,
  [ThoiGianKetThuc] date,
  [MoTa] nvarchar(100),
  [TrangThai] bit,
  PRIMARY KEY ([MaEV])
);

CREATE TABLE [MaVoucher] (
  [MaVoucher] varchar(10),
  [MaEV] varchar(10),
  CONSTRAINT FK_VC_EV foreign key(MaEV) references Eventa(MaEV),
  [SoLuong] int,
  [TrangThai] bit,
  PRIMARY KEY ([MaVoucher])
);

CREATE TABLE [HoaDon] (
  [MaHoaDon] varchar(10),
  [MaNV] varchar(10) not null,
  CONSTRAINT FK_HD_NV foreign key(MaNV) references NhanVien(MaNV),
  [MaKH] varchar(10) not null,
  CONSTRAINT FK_HD_KH foreign key(MaKH) references KhachHang(MaKH),
  [MaHTTT] varchar(10)not null,
  CONSTRAINT FK_HD_TT foreign key(MaHTTT) references HinhThucThanhToan(MaHTTT),
  [NgayTao] datetime,
  [TongTien] Money,
  [TongTienKM] Money,
  [TongTienSauKM] Money,
  [TrangThai] bit,
  [GhiChu] nvarchar(100),
  [MaVoucher] varchar(10) not null,
  CONSTRAINT FK_HD_VC foreign key(MaVoucher) references MaVoucher(MaVoucher),
  PRIMARY KEY ([MaHoaDon])
);

CREATE TABLE [DoiTra] (
  [MaDoiTra] varchar(10),
  [MaHoaDon] varchar(10) not null,
  CONSTRAINT FK_DT_HD foreign key(MaHoaDon) references HoaDon(MaHoaDon),
  [NgayDoiTra] date,
  [TrangThai] bit,
  PRIMARY KEY ([MaDoiTra])
);

CREATE TABLE [LoaiSanPham] (
  [MaLSP] varchar(10),
  [TenLSP] nvarchar(50),
  [TrangThai] bit,
  [Mota] nvarchar(100),
  PRIMARY KEY ([MaLSP])
);
CREATE TABLE [SanPham] (
  [MaSanPham] varchar(10),
  [TenSanPham] nvarchar(50),
  [TrangThai] bit,
  [MaLSP] varchar(10) not null,
  CONSTRAINT FK_SP_LSP foreign key(MaLSP) references LoaiSanPham(MaLSP),
  [XuatXu] nvarchar(50),
  PRIMARY KEY ([MaSanPham])
);

CREATE TABLE [MauSac] (
  [MaMauSac] varchar(10),
  [TenMauSac] nvarchar(50),
  [TrangThai] bit,
  PRIMARY KEY ([MaMauSac])
);

CREATE TABLE [ChatLieu] (
  [MaChatLieu] varchar(10),
  [TenChatLieu] nvarchar(50),
  [TrangThai] bit,
  PRIMARY KEY ([MaChatLieu])
);

CREATE TABLE [KichThuoc] (
  [MaKichThuoc] varchar(10),
  [TenKichThuoc] nvarchar(50),
  [TrangThai] bit,
PRIMARY KEY ([MaKichThuoc])
);

CREATE TABLE [ChiTietSanPham] (
  [MaCTSP] varchar(10),
  [MaSanPham] varchar(10) not null,
  CONSTRAINT FK_CTSP_SP foreign key(MaSanPham) references SanPham(MaSanPham),
  [MaMauSac] varchar(10) not null,
  CONSTRAINT FK_CTSP_MS foreign key(MaMauSac) references MauSac(MaMauSac),
  [MaChatLieu] varchar(10) not null,
   CONSTRAINT FK_CTSP_CL foreign key(MaChatLieu) references ChatLieu(MaChatLieu),
  [MaKichThuoc] varchar(10) not null,
   CONSTRAINT FK_CTSP_KT foreign key(MaKichThuoc) references KichThuoc(MaKichThuoc),
  [SoLuong] int,
  [Gia] money,
  [TrangThai] bit,
  PRIMARY KEY ([MaCTSP])
);


CREATE TABLE [HoaDonChiTiet] (
  [MaHoaDonChiTiet] varchar(10),
  [MaCTSP] varchar(10) not null,
   CONSTRAINT FK_HDCT_SPCT foreign key(MaCTSP) references ChiTietSanPham(MaCTSP),
   [MaHoaDon] varchar(10) not null,
   CONSTRAINT FK_HDCT_HD foreign key(MaHoaDon) references HoaDon(MaHoaDon),
  [SoLuong] int,
  [DonGia] money,
  [ThanhTien] money,
  [GhiChu] nvarchar(100),
  [TrangThai] bit,
  PRIMARY KEY ([MaHoaDonChiTiet])
);
--Update:2023-11-07--
