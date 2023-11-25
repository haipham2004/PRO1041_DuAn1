
﻿--bản full
﻿----Update:2023-11-21--lúc 15g22p--okela

INSERT INTO TaiKhoan VALUES
('TK01','hoilamgi1','khongnoidau1','Admin',1),
('TK02','hoilamgi2','khongnoidau2','Staff',1),
('TK03','hoilamgi3','khongnoidau3','Staff',1),
('TK04','hoilamgi4','khongnoidau4','Staff',1),
('TK05','hoilamgi5','khongnoidau5','Staff',0)
INSERT INTO NhanVien VALUES
('NV01','TK01',N'Phạm Ngọc Hải',1,N'Ninh Bình','0334294889','037643953721','2023-09-15',1,'anh1.png'),
('NV02','TK02',N'Trần Anh Quân',1,N'Hà Nội','0958655432','037637459321','2023-09-15',1,'anh2.png'),
('NV03','TK03',N'Nguyễn Khánh Li',0,N'Bắc Ninh','0394747324','0378475845','2023-08-13',1,'anh3.png'),
('NV04','TK04',N'Nguyễn Đình Minh Hiếu',1,N'Ninh Bình','0334294653','037643953435','2023-07-15',0,'anh4.png'),
('NV05','TK05',N'Mai Thuỳ Linh',0,N'Hà Nội','0334294878','037643953267','2023-07-19',0,'anh5.png')
INSERT INTO KhachHang Values
('KH01',N'Đoàn Xuân Bằng','2004-11-03','0934832745','bangdx@gmail.com',1,N'Nam Định'),
('KH02',N'Vũ Mạnh Trường','2004-07-09','0934832324','truongvmx@gmail.com',1,N'Nam Định'),
('KH03',N'Hoàng Minh Tâm','2004-01-15','0934832234','tamhm@gmail.com',0,N'Nghệ An')


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
('SP12',N'Pea Coat ',0,'LSP02',N'Đài Loan')
INSERT INTO MauSac VALUES
('MS01',N'Đỏ',1),
('MS02',N'Đen',1),
('MS03',N'Trắng',1),
('MS04',N'Xanh',1),
('MS05',N'Vàng',0)
INSERT INTO ChatLieu VALUES
('CL01',N'Vải cotton',1),
('CL02',N'Vải kaki',1),
('CL03',N'Vải Kate',1),
('CL04',N'Vải nỉ',1),
('CL05',N'Vải Jean',0)
INSERT INTO KichThuoc VALUES
('KT01','S',1),
('KT02','M',1),
('KT03','L',1),
('KT04','XL',1),
('KT05','XXL',0)
INSERT INTO Events VALUES
('EV01',N'Sinh nhật cửa hàng',1,'20000','2023-03-11','2023-03-15',N'Siêu giảm giá',1,1,'2000000'),
('EV02',N'Quốc tế Nam giới',1,'15000','2023-10-19','2023-10-21',N'Siêu giảm giá',1,0,''),
('EV03',N'Giáng sinh',0,'40%','2023-11-03','2023-11-12',N'Siêu giảm giá',1,1,'2500000'),
('EV04',N'Ngày nhà giáo Việt Nam 20/11',0,'40%','2023-11-12','2023-11-13',N'Siêu giảm giá',1,1,'7500000')

SELECT*FROM ChiTietSanPham
INSERT INTO ChiTietSanPham(MaSanPham,MaMauSac,MaChatLieu,MaKichThuoc,SoLuong,Gia,TrangThai,qrCode) VALUES
('SP02','MS05','CL03','KT04',50,400000,1,null),
('SP05','MS02','CL01','KT01',50,356000,0,null),
('SP04','MS03','CL02','KT03',50,200000,1,null),
('SP03','MS04','CL04','KT02',50,200000,1,null),
('SP01','MS01','CL05','KT05',50,200000,0,null),
('SP01','MS05','CL04','KT01',70,320000,1,null),
('SP03','MS05','CL05','KT02',80,650000,1,null),
('SP05','MS03','CL03','KT05',90,780000,1,null),
('SP01','MS04','CL04','KT04',100,210000,1,null)
SELECT*FROM HoaDon

INSERT INTO HoaDon VALUES
('HD01','NV05','KH03',getDate(),450000,50000,400000,1,N'Thành công','EV03'),
('HD02','NV03','KH02',getDate(),130000,50000,90000,1,N'Thất bại','EV02'),
('HD03','NV02','KH01',getDate(),750000,50000,700000,1,N'Thành công','EV01'),
('HD04','NV03','KH02',getDate(),130000,50000,90000,1,N'Thất bại','EV02'),
('HD05','NV02','KH01',getDate(),750000,50000,700000,1,N'Thành công','EV01'),
('HD06','NV03','KH02','2023-11-20',130000,50000,90000,1,N'Thất bại','EV02'),
('HD07','NV01','KH01','2023-11-1',750000,50000,700000,1,N'Thành công','EV01'),
('HD08','NV02','KH01','2023-11-25',750000,50000,700000,1,N'Thành công','EV01'),
('HD09','NV01','KH01','2023-11-25',750000,50000,700000,1,N'Thành công','EV01'),
('HD10','NV05','KH03','2023-11-1',450000,50000,400000,1,N'Thành công','EV03'),
('HD12','NV03','KH02','2023-11-1',130000,50000,90000,1,N'Thất bại','EV02'),
('HD11','NV05','KH01','2023-11-1',450000,50000,400000,1,N'Thành công','EV03'),
('HD13','NV02','KH01','2023-11-3',750000,50000,700000,1,N'Thành công','EV01'),
('HD14','NV03','KH02','2023-11-3',130000,50000,90000,1,N'Thất bại','EV02'),
('HD15','NV02','KH01','2023-11-3',750000,50000,700000,1,N'Thành công','EV01'),
('HD16','NV03','KH02','2023-11-5',130000,50000,90000,1,N'Thất bại','EV02'),
('HD17','NV02','KH01','2023-11-5',750000,50000,700000,1,N'Thành công','EV01'),
('HD18','NV01','KH01','2023-11-5',750000,50000,700000,1,N'Thành công','EV01'),
('HD19','NV02','KH01','2023-11-15',750000,50000,700000,1,N'Thành công','EV01'),
('HD20','NV03','KH01','2023-11-23',750000,50000,700000,1,N'Thành công','EV01'),
('HD21','NV05','KH01','2023-11-23',750000,50000,700000,1,N'Thành công','EV01')

INSERT INTO HoaDonChiTiet VALUES
('HDCT01','CTSP02','HD03',3,170000,N'Ok',1),
('HDCT02','CTSP03','HD02',4,170000,N'Ok',1),
('HDCT03','CTSP01','HD01',3,210000,N'Not Ok',0),
('HDCT04','CTSP03','HD02',4,170000,N'Ok',1),
('HDCT06','CTSP01','HD04',3,210000,N'Not Ok',0),
('HDCT07','CTSP03','HD05',4,170000,N'Ok',1),
('HDCT08','CTSP01','HD06',3,210000,N'Not Ok',0),
('HDCT09','CTSP01','HD08',3,210000,N'Not Ok',0),
('HDCT10','CTSP01','HD09',3,210000,N'Not Ok',0),
('HDCT11','CTSP01','HD09',3,210000,N'Not Ok',0),
('HDCT12','CTSP01','HD06',3,210000,N'Not Ok',0),
('HDCT13','CTSP01','HD08',3,210000,N'Not Ok',0),
('HDCT14','CTSP03','HD07',4,170000,N'Ok',1),
('HDCT15','CTSP01','HD10',1,400000,N'Ok',1),
('HDCT16','CTSP02','HD10',2,356000,N'Ok',1),
('HDCT17','CTSP03','HD10',3,200000,N'Ok',1),
('HDCT18','CTSP04','HD11',4,200000,N'Ok',1),
('HDCT19','CTSP05','HD11',5,200000,N'Ok',1),
('HDCT20','CTSP06','HD11',5,200000,N'Ok',1),
('HDCT21','CTSP01','HD12',4,400000,N'Ok',1),
('HDCT22','CTSP02','HD12',3,356000,N'Ok',1),
('HDCT23','CTSP03','HD12',2,200000,N'Ok',1),
('HDCT24','CTSP04','HD13',1,200000,N'Ok',1),
('HDCT25','CTSP05','HD13',1,200000,N'Ok',1),
('HDCT26','CTSP06','HD13',2,200000,N'Ok',1),
('HDCT27','CTSP05','HD14',4,200000,N'Ok',1),
('HDCT28','CTSP06','HD14',3,320000,N'Ok',1),
('HDCT29','CTSP07','HD14',2,650000,N'Ok',1),
('HDCT30','CTSP08','HD15',1,780000,N'Ok',1),
('HDCT31','CTSP09','HD15',1,210000,N'Ok',1),
('HDCT32','CTSP04','HD15',2,200000,N'Ok',1),
('HDCT33','CTSP01','HD16',1,400000,N'Ok',1),
('HDCT34','CTSP02','HD16',2,356000,N'Ok',1),
('HDCT35','CTSP03','HD16',3,200000,N'Ok',1),
('HDCT36','CTSP04','HD17',4,200000,N'Ok',1),
('HDCT37','CTSP05','HD17',5,200000,N'Ok',1),
('HDCT38','CTSP06','HD17',5,200000,N'Ok',1),
('HDCT39','CTSP01','HD18',4,400000,N'Ok',1),
('HDCT40','CTSP02','HD18',3,356000,N'Ok',1),
('HDCT41','CTSP03','HD18',2,200000,N'Ok',1),
('HDCT42','CTSP04','HD19',1,200000,N'Ok',1),
('HDCT43','CTSP05','HD19',1,200000,N'Ok',1),
('HDCT44','CTSP06','HD19',2,200000,N'Ok',1),
('HDCT45','CTSP05','HD20',4,200000,N'Ok',1),
('HDCT46','CTSP06','HD20',3,320000,N'Ok',1),
('HDCT47','CTSP07','HD20',2,650000,N'Ok',1),
('HDCT48','CTSP08','HD21',1,780000,N'Ok',1),
('HDCT49','CTSP09','HD21',1,210000,N'Ok',1),
('HDCT50','CTSP04','HD21',2,200000,N'Ok',1)


INSERT INTO DoiHang VALUES
('DH01','NV02','HD03','2023-11-07',1),
('DH02','NV04','HD06','2023-11-14',1),
('DH03','NV05','HD08','2023-11-19',1)

INSERT INTO DoiHangChiTiet VALUES
('DHCT01','CTSP02','DH03',N'Áo bị chật',1),
('DHCT02','CTSP02','DH01',N'Áo bị rộng',1),
('DHCT03','CTSP03','DH02',N'Áo bị chật',1),
('DHCT04','CTSP01','DH02',N'Áo bị rộng',1),
('DHCT05','CTSP03','DH03',N'Áo bị chật',1)

