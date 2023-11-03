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

alter tABLE KhachHang
ALTER COLUMN GioiTinh bit
SELECT*FROM KhachHang
DELETE FROM KhachHang