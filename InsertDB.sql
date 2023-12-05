--bản full
﻿----Update:2023-12-01--lúc 18g15p--okela
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
('NV04','TK04',N'Nguyễn Đình Minh Hiếu',1,N'Hà Nội','0334294653','037643953435','2023-07-15',0,'anh4.png'),
('NV05','TK05',N'Mai Thuỳ Linh',0,N'Hà Nội','0334294878','037643953267','2023-07-19',0,'anh5.png')
INSERT INTO KhachHang Values
('KH01',N'Đoàn Xuân Bằng','2004-11-03','0934832745','bangdx@gmail.com',1,N'Nam Định'),
('KH02',N'Vũ Mạnh Trường','2004-07-09','0934832324','truongvmx@gmail.com',1,N'Nam Định'),
('KH03',N'Hoàng Minh Tâm','2004-01-15','0934832234','tamhm@gmail.com',0,N'Nghệ An'),
('KH04',N'Nguyễn Tuấn Khanh','2004-08-22','0934832789','khanhnt@gmail.com',1,N'Ninh Bình'),
('KH05',N'Lê Tiến Dũng','2004-07-23','0934832987','dunglt@gmail.com',1,N'Ninh Bình'),
('KHNE',null,null,null,null,null,null)
INSERT INTO LoaiSanPham VALUES
('LSP01',N'Áo hoodie nam',1,N'Hàng đẹp'),
('LSP02',N'Áo sơ mi nam',1,N'Hàng đẹp'),
('LSP03',N'Áo khoác nam',1,N'Hàng đẹp'),
('LSP04',N'Áo len nam',1,N'Hàng đẹp'),
('LSP05',N'Áo jeans nam',1,N'Hàng đẹp')
INSERT INTO SanPham VALUES
('SP01',N'Nuker',1,'LSP05',N'Việt Nam'),
('SP02',N'GAP',1,'LSP05',N'Hàn Quốc'),
('SP03',N'LEVI',1,'LSP05',N'Thái Lan'),
('SP04',N'Basic Tee',1,'LSP01',N'Hàn Quốc'),
('SP05',N'Graphic Tee',1,'LSP01',N'Việt Nam'),
('SP06',N'Longline Tee',1,'LSP01',N'Campuchia'),
('SP07',N'Flannel Shirt',1,'LSP03',N'Việt Nam'),
('SP08',N'Inen Shirt',1,'LSP03',N'Việt Nam'),
('SP09',N'Western Shirt',1,'LSP03',N'Nhật Bản'),
('SP10',N'Denim Jacket',1,'LSP02',N'Việt Nam'),
('SP11',N'Down Jacket',1,'LSP02',N'Việt Nam'),
('SP12',N'Pea Coat ',1,'LSP02',N'Đài Loan')
INSERT INTO MauSac VALUES
('MS01',N'Đỏ',1),
('MS02',N'Đen',1),
('MS03',N'Trắng',1),
('MS04',N'Xanh',1),
('MS05',N'Vàng',1),
('MS06',N'Xám',1),
('MS07',N'Be',1),
('MS08',N'Hồng',1),
('MS09',N'Bạc',1),
('MS10',N'Tím',1)
INSERT INTO ChatLieu VALUES
('CL01',N'Vải Canvas',1),
('CL02',N'Vải Cotton',1),
('CL03',N'Vải Kate',1),
('CL04',N'Vải lụa',1),
('CL05',N'Vải Jean',1),
('CL06',N' Vải Modal',1)

INSERT INTO KichThuoc VALUES
('KT01','S',1),
('KT02','M',1),
('KT03','L',1),
('KT04','XL',1),
('KT05','XXL',1)
INSERT INTO Events VALUES
('EV01',N'Sinh nhật cửa hàng',1,'20000','2023-03-11','2023-03-15',N'Siêu giảm giá',1,1,'2000000'),
('EV02',N'Quốc tế Nam giới',1,'15000','2023-10-19','2023-10-21',N'Siêu giảm giá',1,0,''),
('EV03',N'Giáng sinh',0,'40%','2023-11-03','2023-11-12',N'Siêu giảm giá',1,1,'2500000'),
('EV04',N'Ngày nhà giáo Việt Nam 20/11',0,'40%','2023-11-12','2023-11-13',N'Siêu giảm giá',1,1,'7500000')

SELECT*FROM ChiTietSanPham
INSERT INTO ChiTietSanPham(MaSanPham,MaMauSac,MaChatLieu,MaKichThuoc,SoLuong,Gia,TrangThai,qrCode) VALUES
('SP02','MS05','CL03','KT04',200,400000,1,null),
('SP05','MS02','CL01','KT01',210,750000,0,null),
('SP04','MS03','CL02','KT03',230,200000,1,null),
('SP03','MS04','CL04','KT02',250,600000,1,null),
('SP01','MS01','CL05','KT05',210,200000,0,null),
('SP02','MS05','CL04','KT01',150,380000,1,null),
('SP03','MS05','CL05','KT02',110,650000,1,null),
('SP05','MS03','CL03','KT05',120,780000,1,null),
('SP01','MS04','CL04','KT04',123,220000,1,null),
('SP06','MS06','CL02','KT03',178,390000,1,null),
('SP07','MS07','CL01','KT05',189,470000,1,null),
('SP09','MS09','CL05','KT04',100,980000,1,null),
('SP10','MS10','CL02','KT03',100,980000,1,null)
SELECT*FROM HoaDonChiTiet

INSERT INTO HoaDon VALUES
('HD01','NV05','KH03',getDate(),450000,50000,400000,N'Đã thanh toán',N'Thành công','EV03'),
('HD02','NV03','KH02',getDate(),130000,50000,90000,N'Đã thanh toán',N'Thất bại','EV02'),
('HD03','NV02','KH01',getDate(),750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD04','NV03','KH02',getDate(),130000,50000,90000,N'Đã thanh toán',N'Thất bại','EV02'),
('HD05','NV02','KH01',getDate(),750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD06','NV03','KH02','2023-11-20',130000,50000,90000,N'Đã thanh toán',N'Thất bại','EV02'),
('HD07','NV01','KH01','2023-11-1',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD08','NV02','KH01','2023-11-25',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD09','NV01','KH01','2023-11-25',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD10','NV05','KH03','2023-11-1',450000,50000,400000,N'Đã thanh toán',N'Thành công','EV03'),
('HD12','NV03','KH02','2023-11-1',130000,50000,90000,N'Đã thanh toán',N'Thất bại','EV02'),
('HD11','NV05','KH01','2023-11-1',450000,50000,400000,N'Đã thanh toán',N'Thành công','EV03'),
('HD13','NV02','KH01','2023-11-3',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD14','NV03','KH02','2023-11-3',130000,50000,90000,N'Đã thanh toán',N'Thất bại','EV02'),
('HD15','NV02','KH01','2023-11-3',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD16','NV03','KH02','2023-11-5',130000,50000,90000,N'Đã thanh toán',N'Thất bại','EV02'),
('HD17','NV02','KH01','2023-11-5',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD18','NV01','KH01','2023-11-5',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD19','NV02','KH01','2023-11-15',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD20','NV03','KH01','2023-11-23',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD21','NV05','KH01','2023-11-23',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01')
 
INSERT INTO HoaDonChiTiet VALUES
<<<<<<< HEAD
('HDCT01','CTSP01A','HD03',3,170000,N'Ok',1),
('HDCT02','CTSP022','HD02',4,170000,N'Ok',1),
('HDCT03','CTSP01A','HD01',3,210000,N'Not Ok',0),
('HDCT04','CTSP0AD','HD02',4,170000,N'Ok',1),
('HDCT06','CTSP022','HD04',3,210000,N'Not Ok',0),
('HDCT07','CTSP01A','HD05',4,170000,N'Ok',1),
('HDCT08','CTSP10F','HD06',3,210000,N'Not Ok',0),
('HDCT09','CTSP0AD','HD08',3,210000,N'Not Ok',0),
('HDCT10','CTSP01A','HD09',3,210000,N'Not Ok',0),
('HDCT11','CTSP310','HD09',3,210000,N'Not Ok',0),
('HDCT12','CTSP454','HD06',3,210000,N'Not Ok',0),
('HDCT13','CTSP310','HD08',3,210000,N'Not Ok',0),
('HDCT14','CTSP454','HD07',4,170000,N'Ok',1),
('HDCT15','CTSP0AD','HD10',1,400000,N'Ok',1),
('HDCT16','CTSP10F','HD10',2,356000,N'Ok',1),
('HDCT17','CTSP01A','HD10',3,200000,N'Ok',1),
('HDCT18','CTSP10F','HD11',4,200000,N'Ok',1),
('HDCT19','CTSP485','HD11',5,200000,N'Ok',1),
('HDCT20','CTSP495','HD11',5,200000,N'Ok',1),
('HDCT21','CTSP0AD','HD12',4,400000,N'Ok',1),
('HDCT22','CTSP310','HD12',3,356000,N'Ok',1),
('HDCT23','CTSP454','HD12',2,200000,N'Ok',1),
('HDCT24','CTSP022','HD13',1,200000,N'Ok',1),
('HDCT25','CTSP0AD','HD13',1,200000,N'Ok',1),
('HDCT26','CTSP10F','HD13',2,200000,N'Ok',1),
('HDCT27','CTSP01A','HD14',4,200000,N'Ok',1),
('HDCT28','CTSP0AD','HD14',3,320000,N'Ok',1),
('HDCT29','CTSP022','HD14',2,650000,N'Ok',1),
('HDCT30','CTSP495','HD15',1,780000,N'Ok',1),
('HDCT31','CTSP0AD','HD15',1,210000,N'Ok',1),
('HDCT32','CTSP10F','HD15',2,200000,N'Ok',1),
('HDCT33','CTSP022','HD16',1,400000,N'Ok',1),
('HDCT34','CTSP0AD','HD16',2,356000,N'Ok',1),
('HDCT35','CTSP454','HD16',3,200000,N'Ok',1),
('HDCT36','CTSP022','HD17',4,200000,N'Ok',1),
('HDCT37','CTSP10F','HD17',5,200000,N'Ok',1),
('HDCT38','CTSP0AD','HD17',5,200000,N'Ok',1),
('HDCT39','CTSP10F','HD18',4,400000,N'Ok',1),
('HDCT40','CTSP022','HD18',3,356000,N'Ok',1),
('HDCT41','CTSP10F','HD18',2,200000,N'Ok',1),
('HDCT42','CTSP0AD','HD19',1,200000,N'Ok',1),
('HDCT43','CTSP454','HD19',1,200000,N'Ok',1),
('HDCT44','CTSP310','HD19',2,200000,N'Ok',1),
('HDCT45','CTSP454','HD20',4,200000,N'Ok',1),
('HDCT46','CTSP310','HD20',3,320000,N'Ok',1),
('HDCT47','CTSP0AD','HD20',2,650000,N'Ok',1),
('HDCT48','CTSP0AD','HD21',1,780000,N'Ok',1),
('HDCT49','CTSP495','HD21',1,210000,N'Ok',1),
('HDCT50','CTSP485','HD21',2,200000,N'Ok',1)
=======
('HDCT01','CTSP05B','HD03',3,170000,N'Ok',1),
('HDCT02','CTSP1D7','HD02',4,170000,N'Ok',1),
('HDCT03','CTSP05B','HD01',3,210000,N'Not Ok',0),
('HDCT04','CTSP232','HD02',4,170000,N'Ok',1),
('HDCT06','CTSP1D7','HD04',3,210000,N'Not Ok',0),
('HDCT07','CTSP05B','HD05',4,170000,N'Ok',1),
('HDCT08','CTSP3A7','HD06',3,210000,N'Not Ok',0),
('HDCT09','CTSP232','HD08',3,210000,N'Not Ok',0),
('HDCT10','CTSP05B','HD09',3,210000,N'Not Ok',0),
('HDCT11','CTSP556','HD09',3,210000,N'Not Ok',0),
('HDCT12','CTSP752','HD06',3,210000,N'Not Ok',0),
('HDCT13','CTSP556','HD08',3,210000,N'Not Ok',0),
('HDCT14','CTSP752','HD07',4,170000,N'Ok',1),
('HDCT15','CTSP232','HD10',1,400000,N'Ok',1),
('HDCT16','CTSP3A7','HD10',2,356000,N'Ok',1),
('HDCT17','CTSP05B','HD10',3,200000,N'Ok',1),
('HDCT18','CTSP3A7','HD11',4,200000,N'Ok',1),
('HDCT19','CTSP904','HD11',5,200000,N'Ok',1),
('HDCT20','CTSP97A','HD11',5,200000,N'Ok',1),
('HDCT21','CTSP232','HD12',4,400000,N'Ok',1),
('HDCT22','CTSP556','HD12',3,356000,N'Ok',1),
('HDCT23','CTSP752','HD12',2,200000,N'Ok',1),
('HDCT24','CTSP1D7','HD13',1,200000,N'Ok',1),
('HDCT25','CTSP9CF','HD13',1,200000,N'Ok',1),
('HDCT26','CTSP3A7','HD13',2,200000,N'Ok',1),
('HDCT27','CTSP05B','HD14',4,200000,N'Ok',1),
('HDCT28','CTSP232','HD14',3,320000,N'Ok',1),
('HDCT29','CTSP1D7','HD14',2,650000,N'Ok',1),
('HDCT30','CTSP97A','HD15',1,780000,N'Ok',1),
('HDCT31','CTSP9CF','HD15',1,210000,N'Ok',1),
('HDCT32','CTSP3A7','HD15',2,200000,N'Ok',1),
('HDCT33','CTSP1D7','HD16',1,400000,N'Ok',1),
('HDCT34','CTSP9CF','HD16',2,356000,N'Ok',1),
('HDCT35','CTSP752','HD16',3,200000,N'Ok',1),
('HDCT36','CTSP1D7','HD17',4,200000,N'Ok',1),
('HDCT37','CTSP3A7','HD17',5,200000,N'Ok',1),
('HDCT38','CTSP9CF','HD17',5,200000,N'Ok',1),
('HDCT39','CTSP3A7','HD18',4,400000,N'Ok',1),
('HDCT40','CTSP1D7','HD18',3,356000,N'Ok',1),
('HDCT41','CTSP3A7','HD18',2,200000,N'Ok',1),
('HDCT42','CTSP9CF','HD19',1,200000,N'Ok',1),
('HDCT43','CTSP752','HD19',1,200000,N'Ok',1),
('HDCT44','CTSP556','HD19',2,200000,N'Ok',1),
('HDCT45','CTSP752','HD20',4,200000,N'Ok',1),
('HDCT46','CTSP556','HD20',3,320000,N'Ok',1),
('HDCT47','CTSP232','HD20',2,650000,N'Ok',1),
('HDCT48','CTSP232','HD21',1,780000,N'Ok',1),
('HDCT49','CTSP97A','HD21',1,210000,N'Ok',1),
('HDCT50','CTSP904','HD21',2,200000,N'Ok',1)
>>>>>>> 7f88496ed86b000af7eeac2ad3ff89e1f28aa842

INSERT INTO DoiHang VALUES
('DH01','NV02','HD03','2023-11-07',N'Đổi thành công'),
('DH02','NV04','HD06','2023-11-14',N'Đổi thành công'),
('DH03','NV05','HD08','2023-11-19',N'Đổi thành công'),
('DH04','NV03','HD10','2023-11-29',N'Đổi thành công')

INSERT INTO DoiHangChiTiet VALUES
<<<<<<< HEAD
('DHCT01','CTSP454','DH03','HDCT09',2,N'Áo bị chật',1),
('DHCT02','CTSP310','DH01','HDCT01',1,N'Áo bị rộng',1),
('DHCT03','CTSP495','DH02','HDCT08',1,N'Áo bị chật',1),
('DHCT04','CTSP01A','DH03','HDCT13',1,N'Áo bị chật',1),
('DHCT05','CTSP495','DH04','HDCT16',1,N'Áo bị rộng',1)
=======
('DHCT01','CTSP752','DH03','HDCT09',2,N'Áo bị chật',1),
('DHCT02','CTSP556','DH01','HDCT01',1,N'Áo bị rộng',1),
('DHCT03','CTSP97A','DH02','HDCT08',1,N'Áo bị chật',1),
('DHCT04','CTSP05B','DH03','HDCT13',1,N'Áo bị chật',1),
('DHCT05','CTSP97A','DH04','HDCT16',1,N'Áo bị rộng',1)
>>>>>>> 7f88496ed86b000af7eeac2ad3ff89e1f28aa842

