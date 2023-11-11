-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2023 at 04:01 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlysieuthimini`
--

-- --------------------------------------------------------

--
-- Table structure for table `calamviec`
--

CREATE TABLE `calamviec` (
  `MaCa` int(11) NOT NULL,
  `TenCa` varchar(100) NOT NULL,
  `GioBatDau` time NOT NULL,
  `GioKetThuc` time NOT NULL,
  `LuongTheoCa` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `calamviec`
--

INSERT INTO `calamviec` (`MaCa`, `TenCa`, `GioBatDau`, `GioKetThuc`, `LuongTheoCa`, `TrangThai`) VALUES
(1, 'Sáng', '06:00:00', '11:30:00', 22.00, 1),
(2, 'Chiều', '11:30:00', '17:00:00', 22.00, 1),
(3, 'Tối', '17:00:00', '23:00:00', 25.00, 1),
(4, 'Khuya', '23:00:00', '06:00:00', 30.00, 1),
(5, 'Sáng', '12:00:00', '13:00:00', 12.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `ThanhTien` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `DonGia`, `SoLuong`, `ThanhTien`, `TrangThai`) VALUES
(3, 1, 10000.00, 14, 140000.00, 1),
(3, 2, 20.00, 1, 20.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPN` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `ThanhTien` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `SoLuong`, `DonGia`, `ThanhTien`, `TrangThai`) VALUES
(1, 1, 98, 10000.00, 980000.00, 1),
(1, 2, 100, 20.00, 2000.00, 1),
(24, 1, 12, 10000.00, 120000.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `chitietquyen`
--

CREATE TABLE `chitietquyen` (
  `MaQuyen` int(11) NOT NULL,
  `MaCN` varchar(100) NOT NULL,
  `HanhDong` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietquyen`
--

INSERT INTO `chitietquyen` (`MaQuyen`, `MaCN`, `HanhDong`, `TrangThai`) VALUES
(1, 'banhang', 'create', 1),
(1, 'banhang', 'delete', 1),
(1, 'banhang', 'update', 1),
(1, 'banhang', 'view', 1),
(1, 'khachhang', 'create', 1),
(1, 'khachhang', 'delete', 1),
(1, 'khachhang', 'update', 1),
(1, 'khachhang', 'view', 1),
(1, 'khuyenmai', 'create', 1),
(1, 'khuyenmai', 'delete', 1),
(1, 'khuyenmai', 'update', 1),
(1, 'khuyenmai', 'view', 1),
(1, 'nhacungcap', 'create', 1),
(1, 'nhacungcap', 'delete', 1),
(1, 'nhacungcap', 'update', 1),
(1, 'nhacungcap', 'view', 1),
(1, 'nhanvien', 'create', 1),
(1, 'nhanvien', 'delete', 1),
(1, 'nhanvien', 'update', 1),
(1, 'nhanvien', 'view', 1),
(1, 'nhaphang', 'create', 1),
(1, 'nhaphang', 'delete', 1),
(1, 'nhaphang', 'update', 1),
(1, 'nhaphang', 'view', 1),
(1, 'phancongca', 'create', 1),
(1, 'phancongca', 'delete', 1),
(1, 'phancongca', 'update', 1),
(1, 'phancongca', 'view', 1),
(1, 'phanquyen', 'create', 1),
(1, 'phanquyen', 'delete', 1),
(1, 'phanquyen', 'update', 1),
(1, 'phanquyen', 'view', 1),
(1, 'sanpham', 'create', 1),
(1, 'sanpham', 'delete', 1),
(1, 'sanpham', 'update', 1),
(1, 'sanpham', 'view', 1),
(1, 'taikhoan', 'create', 1),
(1, 'taikhoan', 'delete', 1),
(1, 'taikhoan', 'update', 1),
(1, 'taikhoan', 'view', 1),
(1, 'thanhphan', 'create', 1),
(1, 'thanhphan', 'delete', 1),
(1, 'thanhphan', 'update', 1),
(1, 'thanhphan', 'view', 1),
(1, 'thongke', 'create', 1),
(1, 'thongke', 'delete', 1),
(1, 'thongke', 'update', 1),
(1, 'thongke', 'view', 1);

-- --------------------------------------------------------

--
-- Table structure for table `chitietthanhtoan`
--

CREATE TABLE `chitietthanhtoan` (
  `MaHD` int(11) NOT NULL,
  `MaHTTT` int(11) NOT NULL,
  `SoTien` decimal(10,2) NOT NULL,
  `NgayThanhToan` timestamp NOT NULL DEFAULT current_timestamp(),
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietthanhtoan`
--

INSERT INTO `chitietthanhtoan` (`MaHD`, `MaHTTT`, `SoTien`, `NgayThanhToan`, `TrangThai`) VALUES
(3, 1, 140020.00, '2023-11-10 02:58:30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `MaCN` varchar(100) NOT NULL,
  `TenCN` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`MaCN`, `TenCN`, `TrangThai`) VALUES
('banhang', 'Bán hàng', 1),
('khachhang', 'Quản lý khách hàng', 1),
('khuyenmai', 'Quản lý khuyến mãi', 1),
('nhacungcap', 'Quản lý nhà cung cấp', 1),
('nhanvien', 'Quản lý nhân viên', 1),
('nhaphang', 'Nhập hàng', 1),
('phancongca', 'Phân công ca', 1),
('phanquyen', 'Phân quyền người dùng', 1),
('sanpham', 'Quản lý sản phẩm', 1),
('taikhoan', 'Quản lý tài khoản', 1),
('thanhphan', 'Quản lý thành phần sản phẩm', 1),
('thongke', 'Báo cáo thống kê', 1);

-- --------------------------------------------------------

--
-- Table structure for table `donvi`
--

CREATE TABLE `donvi` (
  `MaDV` int(11) NOT NULL,
  `TenDV` varchar(50) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donvi`
--

INSERT INTO `donvi` (`MaDV`, `TenDV`, `TrangThai`) VALUES
(1, 'kg', 0),
(2, 'bichX', 0),
(3, 'bich4', 0),
(4, 'kg', 1),
(5, 'bịch', 1),
(6, 'ml', 1),
(7, 'lít', 1),
(8, 'gam', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hangsanxuat`
--

CREATE TABLE `hangsanxuat` (
  `MaHang` int(11) NOT NULL,
  `TenHang` varchar(100) NOT NULL,
  `TruSo` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hangsanxuat`
--

INSERT INTO `hangsanxuat` (`MaHang`, `TenHang`, `TruSo`, `TrangThai`) VALUES
(7, 'Nuti food', 'TpHCM', 1),
(8, 'Fanta', 'Tiền Giang', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hinhthucthanhtoan`
--

CREATE TABLE `hinhthucthanhtoan` (
  `MaHTTT` int(11) NOT NULL,
  `TenHTTT` varchar(100) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hinhthucthanhtoan`
--

INSERT INTO `hinhthucthanhtoan` (`MaHTTT`, `TenHTTT`, `TrangThai`) VALUES
(1, 'Tiền mặt', 1),
(2, 'Momo', 1),
(3, 'MB Bank', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(11) NOT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `MaKM` int(11) DEFAULT NULL,
  `MaNV` int(11) NOT NULL,
  `NgayLap` timestamp NOT NULL DEFAULT current_timestamp(),
  `TongTien` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaKH`, `MaKM`, `MaNV`, `NgayLap`, `TongTien`, `TrangThai`) VALUES
(3, 1, 3, 1, '2023-11-10 02:58:30', 140020.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `TenKH` varchar(100) NOT NULL,
  `DiaChi` varchar(255) NOT NULL,
  `SDT` varchar(100) NOT NULL,
  `DiemTichLuy` int(11) NOT NULL,
  `ChietKhauTheoDiem` double NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `DiaChi`, `SDT`, `DiemTichLuy`, `ChietKhauTheoDiem`, `TrangThai`) VALUES
(1, 'Quân Ca', 'hahahhaa', '123432123', 2, 0.005, 1);

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` int(11) NOT NULL,
  `TenKM` varchar(100) NOT NULL,
  `DieuKienKM` float NOT NULL,
  `PhanTramKM` float NOT NULL,
  `NgayBatDau` date NOT NULL,
  `NgayKetThuc` date NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `DieuKienKM`, `PhanTramKM`, `NgayBatDau`, `NgayKetThuc`, `TrangThai`) VALUES
(1, 'Khuyến mãi 20/11', 2000, 0.3, '2023-11-10', '2023-11-22', 1),
(2, 'Halloween', 1000, 0.15, '2023-11-01', '2023-11-12', 1),
(3, 'Tết vui xuân', 3000, 0.4, '2023-01-01', '2023-02-28', 1);

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `MaLoai` int(11) NOT NULL,
  `TenLoai` varchar(100) NOT NULL,
  `CachBaoQuan` varchar(100) NOT NULL,
  `MoTa` varchar(255) NOT NULL,
  `TrangThai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`MaLoai`, `TenLoai`, `CachBaoQuan`, `MoTa`, `TrangThai`) VALUES
(1, 'ga', 'ga', 'ga', 0),
(2, 'eqeq', 'ewq', 'qwe', 0),
(3, 'qwe', 'rewr', 'wer', 0),
(4, 'asd', 'sda', 'asd', 0),
(5, 'sdf', 'asda', 'sada', 0),
(6, 'Đồ uống', 'Để ngăn mát', 'Các loại nước uống dạng chai hoặc hộp', 1),
(7, 'Đồ đông lạnh', 'Ngăn đông, nhiệt độ < -10 C', 'Các loại đồ sống, kem, ...', 1),
(8, 'hahaha', 'ád', 'fff', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` int(11) NOT NULL,
  `TenNCC` varchar(100) NOT NULL,
  `DiaChi` varchar(255) NOT NULL,
  `SDT` varchar(50) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChi`, `SDT`, `Email`, `TrangThai`) VALUES
(1, 'haha', 'haha', '123123', 'dominhquan15623@gmail.com', 1),
(2, 'Công ty hữu hạn 1 thành viên', '123 Tân Bình lục bảo', '12312312312', 'thanhvien@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(11) NOT NULL,
  `TenNV` varchar(100) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SDT` varchar(50) NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` tinyint(1) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Luong` decimal(10,2) DEFAULT NULL,
  `HinhAnh` varchar(255) DEFAULT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `DiaChi`, `SDT`, `NgaySinh`, `GioiTinh`, `Email`, `Luong`, `HinhAnh`, `TrangThai`) VALUES
(1, 'Đỗ Minh Quân', 'TpHCM', '0778715603', '2003-06-15', 1, 'dominhquan15623@gmail.com', 0.00, '', 1),
(2, 'Koong Chấn Phong', 'TpHCM', '0778715603', '2003-06-15', 1, 'dominhquan15623@gmail.com', 0.00, '', 1),
(3, 'Lê Ngọc Giàu', 'TpHCM', '0778715603', '2003-06-15', 1, 'dominhquan15623@gmail.com', 0.00, '', 1),
(4, 'Nguyễn Khánh Nam', 'TpHCM', '0778715603', '2003-06-15', 1, 'dominhquan15623@gmail.com', 0.00, '', 1),
(5, 'Nguyễn Nhật Khải', 'TpHCM', '0778715603', '2003-06-15', 1, 'dominhquan15623@gmail.com', 0.00, '', 1),
(6, 'Trần Tiến Phát', 'TpHCM', '0778715603', '2003-06-15', 1, 'dominhquan15623@gmail.com', 0.00, '', 1),
(7, 'haha', 'haha', '12312353465', '2023-11-17', 1, 'adsasdsd', 5.40, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phancongca`
--

CREATE TABLE `phancongca` (
  `MaCa` int(11) NOT NULL,
  `MaNV` int(11) NOT NULL,
  `Thu` varchar(50) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieuchi`
--

CREATE TABLE `phieuchi` (
  `MaPC` int(11) NOT NULL,
  `MaPN` int(11) NOT NULL,
  `MaNV` int(11) NOT NULL,
  `TenNguoiChi` varchar(255) NOT NULL,
  `NgayChi` timestamp NOT NULL DEFAULT current_timestamp(),
  `LyDoChi` varchar(255) NOT NULL,
  `SoTienChi` decimal(10,2) NOT NULL,
  `GhiChu` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieuchi`
--

INSERT INTO `phieuchi` (`MaPC`, `MaPN`, `MaNV`, `TenNguoiChi`, `NgayChi`, `LyDoChi`, `SoTienChi`, `GhiChu`, `TrangThai`) VALUES
(1, 24, 1, 'Đỗ Minh Quân', '2023-11-09 02:04:11', 'Nhập hàng', 120000.00, '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` int(11) NOT NULL,
  `MaNV` int(11) NOT NULL,
  `MaNCC` int(11) NOT NULL,
  `NgayNhap` timestamp NOT NULL DEFAULT current_timestamp(),
  `TongTien` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`MaPN`, `MaNV`, `MaNCC`, `NgayNhap`, `TongTien`, `TrangThai`) VALUES
(1, 1, 1, '2023-11-08 08:17:29', 982000.00, 0),
(24, 1, 1, '2023-11-07 08:44:20', 120000.00, 2);

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `MaQuyen` int(11) NOT NULL,
  `TenQuyen` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`MaQuyen`, `TenQuyen`, `TrangThai`) VALUES
(1, 'Quản lý', 1),
(2, 'Nhân viên bán hàng', 1),
(3, 'Nhân viên nhập hàng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` int(11) NOT NULL,
  `MaLoai` int(11) NOT NULL,
  `MaHang` int(11) NOT NULL,
  `MaDV` int(11) NOT NULL,
  `MaVach` varchar(255) NOT NULL,
  `TenSP` varchar(255) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DungTich` int(11) NOT NULL,
  `NgaySanXuat` date NOT NULL,
  `HanSuDung` date NOT NULL,
  `HinhAnh` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `MaLoai`, `MaHang`, `MaDV`, `MaVach`, `TenSP`, `DonGia`, `SoLuong`, `DungTich`, `NgaySanXuat`, `HanSuDung`, `HinhAnh`, `TrangThai`) VALUES
(1, 6, 7, 6, '123123345345', 'Nước cam', 10000.00, 45, 450, '2023-11-01', '2024-11-15', '', 1),
(2, 7, 8, 5, '123123123', 'yaout đá vị cam', 20.00, 98, 1, '2023-11-01', '2023-11-30', '9HK1_2023-2024-Tuan_1.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaNV` int(11) NOT NULL,
  `MaQuyen` int(11) NOT NULL,
  `TenTK` varchar(100) NOT NULL,
  `MatKhau` varchar(50) NOT NULL,
  `OTP` varchar(50) DEFAULT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MaNV`, `MaQuyen`, `TenTK`, `MatKhau`, `OTP`, `TrangThai`) VALUES
(1, 1, 'admin', 'admin', 'null', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `calamviec`
--
ALTER TABLE `calamviec`
  ADD PRIMARY KEY (`MaCa`);

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaSP`),
  ADD KEY `MaSP` (`MaSP`),
  ADD KEY `MaSP_2` (`MaSP`),
  ADD KEY `MaHD` (`MaHD`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MaPN`,`MaSP`),
  ADD KEY `MaPN` (`MaPN`,`MaSP`),
  ADD KEY `ctpn_ibfk_2` (`MaSP`),
  ADD KEY `MaPN_2` (`MaPN`,`MaSP`);

--
-- Indexes for table `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD PRIMARY KEY (`MaQuyen`,`MaCN`,`HanhDong`),
  ADD KEY `MaQuyen` (`MaQuyen`,`MaCN`),
  ADD KEY `chitietquyen_ibfk_2` (`MaCN`);

--
-- Indexes for table `chitietthanhtoan`
--
ALTER TABLE `chitietthanhtoan`
  ADD PRIMARY KEY (`MaHD`,`MaHTTT`),
  ADD KEY `cttt_ibfk_2` (`MaHTTT`),
  ADD KEY `MaHD` (`MaHD`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`MaCN`);

--
-- Indexes for table `donvi`
--
ALTER TABLE `donvi`
  ADD PRIMARY KEY (`MaDV`);

--
-- Indexes for table `hangsanxuat`
--
ALTER TABLE `hangsanxuat`
  ADD PRIMARY KEY (`MaHang`);

--
-- Indexes for table `hinhthucthanhtoan`
--
ALTER TABLE `hinhthucthanhtoan`
  ADD PRIMARY KEY (`MaHTTT`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`,`MaKM`,`MaNV`),
  ADD KEY `hoadon_ibfk_2` (`MaKM`),
  ADD KEY `hoadon_ibfk_3` (`MaNV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `phancongca`
--
ALTER TABLE `phancongca`
  ADD PRIMARY KEY (`MaCa`,`MaNV`,`Thu`),
  ADD KEY `phancongca_ibfk_2` (`MaNV`),
  ADD KEY `MaCa` (`MaCa`);

--
-- Indexes for table `phieuchi`
--
ALTER TABLE `phieuchi`
  ADD PRIMARY KEY (`MaPC`),
  ADD KEY `MaPN` (`MaPN`,`MaNV`),
  ADD KEY `phieuchi_ibfk_1` (`MaNV`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaNV` (`MaNV`,`MaNCC`),
  ADD KEY `phieunhap_ibfk_1` (`MaNCC`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `MaLoai` (`MaLoai`,`MaHang`,`MaDV`),
  ADD KEY `sanpham_ibfk_1` (`MaDV`),
  ADD KEY `sanpham_ibfk_2` (`MaHang`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaNV`),
  ADD KEY `maquyen` (`MaQuyen`,`TenTK`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `calamviec`
--
ALTER TABLE `calamviec`
  MODIFY `MaCa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `donvi`
--
ALTER TABLE `donvi`
  MODIFY `MaDV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `hangsanxuat`
--
ALTER TABLE `hangsanxuat`
  MODIFY `MaHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `hinhthucthanhtoan`
--
ALTER TABLE `hinhthucthanhtoan`
  MODIFY `MaHTTT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `MaKM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `MaLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `MaNCC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `phieuchi`
--
ALTER TABLE `phieuchi`
  MODIFY `MaPC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MaPN` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `quyen`
--
ALTER TABLE `quyen`
  MODIFY `MaQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `cthd_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `cthd_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `ctpn_ibfk_1` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ctpn_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD CONSTRAINT `chitietquyen_ibfk_1` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen` (`MaQuyen`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `chitietquyen_ibfk_2` FOREIGN KEY (`MaCN`) REFERENCES `chucnang` (`MaCN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `chitietthanhtoan`
--
ALTER TABLE `chitietthanhtoan`
  ADD CONSTRAINT `cttt_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `cttt_ibfk_2` FOREIGN KEY (`MaHTTT`) REFERENCES `hinhthucthanhtoan` (`MaHTTT`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phancongca`
--
ALTER TABLE `phancongca`
  ADD CONSTRAINT `phancongca_ibfk_1` FOREIGN KEY (`MaCa`) REFERENCES `calamviec` (`MaCa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `phancongca_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieuchi`
--
ALTER TABLE `phieuchi`
  ADD CONSTRAINT `phieuchi_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `phieuchi_ibfk_2` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaDV`) REFERENCES `donvi` (`MaDV`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`MaHang`) REFERENCES `hangsanxuat` (`MaHang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `sanpham_ibfk_3` FOREIGN KEY (`MaLoai`) REFERENCES `loaisanpham` (`MaLoai`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen` (`MaQuyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
