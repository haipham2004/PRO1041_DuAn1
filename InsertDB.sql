INSERT INTO TaiKhoan VALUES
('TK01','hoilamgi1','khongnoidau1','Admin',1),
('TK02','hoilamgi2','khongnoidau2','Staff',1),
('TK03','hoilamgi3','khongnoidau3','Staff',1)
INSERT INTO NhanVien VALUES
('NV01','TK02',N'Phạm Ngọc Hải',1,N'Ninh Bình','0334294889','037643953721','2023-09-15',1,'anh1.png'),
('NV02','TK02',N'Trần Anh Quân',1,N'Hà Nội','0958655432','037637459321','2023-09-15',1,'anh2.png'),
('NV03','TK02',N'Nguyễn Khánh Li',0,N'Bắc Ninh','0394747324','0378475845','2023-08-13',1,'anh3.png'),
('NV04','TK01',N'Nguyễn Đình Minh Hiếu',1,N'Ninh Bình','0334294653','037643953435','2023-07-15',0,'anh4.png'),
('NV05','TK02',N'Mai Thuỳ Linh',0,N'Hà Nội','0334294878','037643953267','2023-07-19',0,'anh5.png')
INSERT INTO KhachHang Values
('KH01',N'Đoàn Xuân Bằng','2004-11-03','0934832745','bangdx@gmail.com',1,N'Nam Định',1),
('KH02',N'Vũ Mạnh Trường','2004-07-09','0934832324','truongvmx@gmail.com',1,N'Nam Định',1),
('KH03',N'Hoàng Minh Tâm','2004-01-15','0934832234','tamhm@gmail.com',0,N'Nghệ An',0)
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
('SP02',N'GAP',1,'LSP05',N'Việt Nam'),
('SP03',N'LEVI',1,'LSP05',N'Việt Nam'),
('SP04',N'Basic Tee',1,'LSP01',N'Việt Nam'),
('SP05',N'Graphic Tee',1,'LSP01',N'Việt Nam'),
('SP06',N'Longline Tee',1,'LSP01',N'Việt Nam'),
('SP07',N'Flannel Shirt',1,'LSP03',N'Việt Nam'),
('SP08',N'inen Shirt',1,'LSP03',N'Việt Nam'),
('SP09',N'Western Shirt',1,'LSP03',N'Việt Nam'),
('SP10',N'Denim Jacket',1,'LSP02',N'Việt Nam'),
('SP11',N'Down Jacket',1,'LSP02',N'Việt Nam'),
('SP12',N'Pea Coat ',1,'LSP02',N'Việt Nam')
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
('KT04','XL',1)

INSERT INTO Eventa VALUES
('EV01',N'Sinh nhật cửa hàng',N'Giảm giá','20%','2023-03-11','2023-03-15',N'Siêu giảm giá',1),
('EV02',N'Quốc tế Nam giới',N'Giảm giá','50%','2023-10-19','2023-10-21',N'Siêu giảm giá',1),
('EV03',N'Giáng sinh',N'Giảm giá','40%','2023-11-03','2023-11-05',N'Siêu giảm giá',1)
INSERT INTO ChiTietSanPham VALUES
('CTSP01','SP02','MS05','CL03','KT04',2,500000,1),
('CTSP02','SP05','MS02','CL01','KT01',1,356000,1),
('CTSP03','SP06','MS02','CL03','KT02',4,297000,1)
SELECT*FROM KhachHang
DELETE FROM KhachHang

SELECT*FROM NhanVien
