-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 22, 2023 at 06:03 AM
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
  `MaNV` int(11) NOT NULL,
  `TenCa` varchar(100) NOT NULL,
  `GioBatDau` time NOT NULL,
  `GioKetThuc` time NOT NULL,
  `LuongTheoCa` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Table structure for table `chitietquyen`
--

CREATE TABLE `chitietquyen` (
  `MaQuyen` int(11) NOT NULL,
  `MaCN` int(11) NOT NULL,
  `HanhDong` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chitietthanhtoan`
--

CREATE TABLE `chitietthanhtoan` (
  `MaHD` int(11) NOT NULL,
  `MaHTTT` int(11) NOT NULL,
  `SoTien` decimal(10,2) NOT NULL,
  `NgayThanhToan` datetime NOT NULL DEFAULT current_timestamp(),
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `MaCN` int(11) NOT NULL,
  `TenCN` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `donvi`
--

CREATE TABLE `donvi` (
  `MaDV` int(11) NOT NULL,
  `TenDV` varchar(50) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Table structure for table `hinhthucthanhtoan`
--

CREATE TABLE `hinhthucthanhtoan` (
  `MaHTTT` int(11) NOT NULL,
  `TenHTTT` varchar(100) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(11) NOT NULL,
  `MaKH` int(11) NOT NULL,
  `MaKM` int(11) NOT NULL,
  `MaNV` int(11) NOT NULL,
  `NgayLap` date NOT NULL DEFAULT current_timestamp(),
  `TongTien` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `TenKH` varchar(100) NOT NULL,
  `DiaChi` varchar(255) NOT NULL,
  `SDT` varchar(100) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `Luong` decimal(10,2) NOT NULL,
  `HinhAnh` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` int(11) NOT NULL,
  `MaNV` int(11) NOT NULL,
  `MaNCC` int(11) NOT NULL,
  `NgayNhap` date NOT NULL DEFAULT current_timestamp(),
  `TongTien` decimal(10,2) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `MaQuyen` int(11) NOT NULL,
  `TenQuyen` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` int(11) NOT NULL,
  `MaLoai` int(11) NOT NULL,
  `MaHang` int(11) NOT NULL,
  `MaDV` int(11) NOT NULL,
  `TenSP` varchar(255) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DungTich` int(11) NOT NULL,
  `NgaySanXuat` date NOT NULL,
  `HanSuDung` date NOT NULL,
  `HinhAnh` varchar(255) NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Indexes for dumped tables
--

--
-- Indexes for table `calamviec`
--
ALTER TABLE `calamviec`
  ADD PRIMARY KEY (`MaCa`),
  ADD KEY `calamviec_ibfk_1` (`MaNV`);

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
  ADD PRIMARY KEY (`MaQuyen`,`MaCN`,`HanhDong`);

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
  MODIFY `MaCa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `donvi`
--
ALTER TABLE `donvi`
  MODIFY `MaDV` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hangsanxuat`
--
ALTER TABLE `hangsanxuat`
  MODIFY `MaHang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hinhthucthanhtoan`
--
ALTER TABLE `hinhthucthanhtoan`
  MODIFY `MaHTTT` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `MaKM` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `MaLoai` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `MaNCC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MaPN` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quyen`
--
ALTER TABLE `quyen`
  MODIFY `MaQuyen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSP` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `calamviec`
--
ALTER TABLE `calamviec`
  ADD CONSTRAINT `calamviec_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
  ADD CONSTRAINT `chitietquyen_ibfk_1` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen` (`MaQuyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
