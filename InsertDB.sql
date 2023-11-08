----Update:2023-11-08--lúc 18g00p--
SELECT*FROM Events
INSERT INTO TaiKhoan VALUES
('TK01','hoilamgi1','khongnoidau1','Admin',1),
('TK02','hoilamgi2','khongnoidau2','Staff',1)
INSERT INTO NhanVien VALUES
('NV01','TK02',N'Phạm Ngọc Hải',1,N'Ninh Bình','0334294889','037643953721','2023-09-15',1,'anh1.png'),
('NV02','TK02',N'Trần Anh Quân',1,N'Hà Nội','0958655432','037637459321','2023-09-15',1,'anh2.png'),
('NV03','TK02',N'Nguyễn Khánh Li',0,N'Bắc Ninh','0394747324','0378475845','2023-08-13',1,'anh3.png'),
('NV04','TK01',N'Nguyễn Đình Minh Hiếu',1,N'Ninh Bình','0334294653','037643953435','2023-07-15',0,'anh4.png'),
('NV05','TK02',N'Mai Thuỳ Linh',0,N'Hà Nội','0334294878','037643953267','2023-07-19',0,'anh5.png')
INSERT INTO KhachHang Values
('KH01',N'Đoàn Xuân Bằng','2004-11-03','0934832745','bangdx@gmail.com',1,N'Nam Định'),
('KH02',N'Vũ Mạnh Trường','2004-07-09','0934832324','truongvmx@gmail.com',1,N'Nam Định'),
('KH03',N'Hoàng Minh Tâm','2004-01-15','0934832234','tamhm@gmail.com',0,N'Nghệ An')

INSERT INTO HinhThucThanhToan Values
('HTTT01',N'Tiền mặt',1),
('HTTT02',N'Chuyển khoản',1)
INSERT INTO LoaiSanPham VALUES
('LSP01',N'Áo thun nam',1,N'Hàng đẹp'),
('LSP02',N'Áo sơ mi nam',1,N'Hàng đẹp'),
('LSP03',N'Áo khoác nam',1,N'Hàng đẹp'),
('LSP04',N'Áo polo Nam',0,N'Hàng đẹp'),
('LSP05',N'Áo jeans Nam',1,N'Hàng đẹp')
INSERT INTO SanPham VALUES
('SP01',N'OLD NAVY',1,'LSP05',N'Việt Nam'),
('SP02',N'GAP',1,'LSP05',N'Lào'),
('SP03',N'LEVI',1,'LSP05',N'Thái Lan'),
('SP04',N'Basic Tee',0,'LSP01',N'Hàn Quốc'),
('SP05',N'Graphic Tee',0,'LSP01',N'Việt Nam'),
('SP06',N'Longline Tee',1,'LSP01',N'Campuchia'),
('SP07',N'Flannel Shirt',1,'LSP03',N'Việt Nam'),
('SP08',N'inen Shirt',1,'LSP03',N'Việt Nam'),
('SP09',N'Western Shirt',1,'LSP03',N'Nhật Bản'),
('SP10',N'Denim Jacket',1,'LSP02',N'Việt Nam'),
('SP11',N'Down Jacket',1,'LSP02',N'Việt Nam'),
('SP12',N'Pea Coat ',0,'LSP02',N'Đài Loan'),
('SP13',N'Nuker',0,'LSP02',N'Quảng Châu')
INSERT INTO MauSac VALUES
('MS01',N'Đỏ',1),
('MS02',N'Đen',1),
('MS03',N'Trắng',1),
('MS04',N'Xanh',1),
('MS05',N'Vàng',1)
INSERT INTO ChatLieu VALUES
('CL01',N'Vải cotton',1),
('CL02',N'Vải kaki',1),
('CL03',N'Vải Kate',1),
('CL04',N'Vải nỉ',1),
('CL05',N'Vải Jean',1)
INSERT INTO KichThuoc VALUES
('KT01','S',1),
('KT02','M',1),
('KT03','L',1),
('KT04','XL',1),
('KT05','XXL',0)

INSERT INTO Events VALUES
('EV01',N'Sinh nhật cửa hàng',1,'20000','2023-03-11','2023-03-15',N'Siêu giảm giá',1,1,'2000000'),
('EV02',N'Quốc tế Nam giới',1,'15000','2023-10-19','2023-10-21',N'Siêu giảm giá',1,0,''),
('EV03',N'Giáng sinh',0,'40%','2023-11-03','2023-11-05',N'Siêu giảm giá',1,1,'2500000')

INSERT INTO MaVoucher VALUES
('VC01','EV01',10,1),
('VC02','EV03',15,1),
('VC03','EV02',2,0)
INSERT INTO ChiTietSanPham VALUES
('CTSP01','SP02','MS05','CL03','KT04',2,400000,1),
('CTSP02','SP05','MS02','CL01','KT01',1,356000,0),
('CTSP03','SP06','MS02','CL03','KT02',4,200000,1),
('CTSP04','SP03','MS01','CL05','KT03',12,567000,0),
('CTSP05','SP07','MS04','CL02','KT03',12,567000,0)

INSERT INTO HoaDon VALUES
('HD01','NV05','KH03','HTTT01',getDate(),450000,50000,400000,1,N'Thành công','VC03'),
('HD02','NV03','KH02','HTTT02',getDate(),130000,50000,90000,0,N'Thất bại','VC02'),
('HD03','NV02','KH01','HTTT01',getDate(),750000,50000,700000,1,N'Thành công','VC01')

INSERT INTO HoaDonChiTiet VALUES
('HDCT01','CTSP02','HD03',3,170000,510000,N'Ok',1),
('HDCT02','CTSP03','HD02',4,170000,680000,N'Ok',1),
('HDCT03','CTSP01','HD01',3,210000,630000,N'Not Ok',0)