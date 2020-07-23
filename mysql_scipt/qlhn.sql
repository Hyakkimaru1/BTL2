/*
 Navicat Premium Data Transfer

 Source Server         : bookstay
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:8889
 Source Schema         : qlhn

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 23/07/2020 20:52:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for diadiem
-- ----------------------------
DROP TABLE IF EXISTS `diadiem`;
CREATE TABLE `diadiem`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `diachi` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `succhua` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diadiem
-- ----------------------------
INSERT INTO `diadiem` VALUES (1, 'Trung tâm Hội nghị Quốc gia Hà Nội', '57 đường Phạm Hùng, Hà Nội', 100);
INSERT INTO `diadiem` VALUES (8, 'Khách sạn JW. Marriott Hà Nội', 'số 8 Đỗ Đức Dục, Nam Từ Liêm, Hà Nội', 2);
INSERT INTO `diadiem` VALUES (9, 'Khách sạn Melia', 'trung tâm quận Hai Bà Trưng', 10);
INSERT INTO `diadiem` VALUES (10, 'Khách sạn Lotte Hà Nội', 'Tầng 6 của tòa nhà 65, Liễu Giai', 5);
INSERT INTO `diadiem` VALUES (11, 'TT Hội Nghị Thành Ủy', '272 Võ Thị Sáu, Quận 3, TP.HCM', 400);
INSERT INTO `diadiem` VALUES (12, 'Trung tâm TDTT', 'Quận Tân Bình, TP HCM', 40);
INSERT INTO `diadiem` VALUES (13, 'Trung tâm Thể dục thể thao', 'Quận 8, TP HCM', 15);

-- ----------------------------
-- Table structure for hinhanh
-- ----------------------------
DROP TABLE IF EXISTS `hinhanh`;
CREATE TABLE `hinhanh`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `hoinghi` int(0) NULL DEFAULT NULL,
  `hinhanh` varchar(20000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `hinhanhcuahoinghi`(`hoinghi`) USING BTREE,
  CONSTRAINT `hinhanhcuahoinghi` FOREIGN KEY (`hoinghi`) REFERENCES `hoinghi` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hinhanh
-- ----------------------------
INSERT INTO `hinhanh` VALUES (8, 15, 'src\\img\\hn\\15.png');
INSERT INTO `hinhanh` VALUES (9, 16, 'src\\img\\hn\\16.png');
INSERT INTO `hinhanh` VALUES (10, 17, 'src\\img\\hn\\17.png');
INSERT INTO `hinhanh` VALUES (11, 18, 'src\\img\\hn\\18.png');
INSERT INTO `hinhanh` VALUES (12, 19, 'src\\img\\hn\\19.png');
INSERT INTO `hinhanh` VALUES (15, 22, 'src\\img\\hn\\22.png');

-- ----------------------------
-- Table structure for hoinghi
-- ----------------------------
DROP TABLE IF EXISTS `hoinghi`;
CREATE TABLE `hoinghi`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `mota` varchar(5000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `motachitiet` varchar(10000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `thoigianbd` datetime(1) NULL DEFAULT NULL,
  `thoigiankt` datetime(1) NULL DEFAULT NULL,
  `diadiem` int(0) NULL DEFAULT NULL,
  `soluongnguoithamgia` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `diadiemtochuc`(`diadiem`) USING BTREE,
  CONSTRAINT `diadiemtochuc` FOREIGN KEY (`diadiem`) REFERENCES `diadiem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hoinghi
-- ----------------------------
INSERT INTO `hoinghi` VALUES (15, 'Hội chợ Triển lãm Quốc tế Hà Nội', '75 gian hàng thương hiệu hàng đầu của Thái Lan sẽ có mặt tại Trung tâm Hội chợ Triển lãm Quốc tế Hà Nội', 'Chương trình này do Cục Xúc Tiến Thương Mại Quốc Tế, Bộ Thương Mại Hoàng Gia Thái Lan – Thương Vụ Đại Sứ Quán Thái Lan tại Hà Nội, Việt Nam, phối hợp với công ty Vinexad, tổ chức.\n\nVới sự góp mặt của các nhà nhập khẩu và đại lý chính thức sản phẩm Thái Lan tại Việt Nam – với 75 gian hàng tiêu chuẩn, thương hiệu hàng đầu Thái Lan 2020 tại Hà Nội lần này sẽ trưng bày và bán các sản phẩm đa dạng từ thực phẩm và đồ uống, quả tươi, đồ dùng gia đình, dệt may, trang sức, sản phẩm chăm sóc sắc đẹp, đồ trang trí và lưu niệm và các sản phẩm khác được nhập khẩu từ Thái Lan.\n\nMột số hoạt động đặc biệt như nếm thử đồ ăn Thái Lan được chế biến bời một số nhà hàng trong hệ thống nhà hàng đạt danh hiệu Thai Select tại Việt Nam và các tiết mục múa Thái Lan cũng sẽ được tổ chức tại sự kiện để giới thiệu tới người tiêu dùng.\n\nTheo đại diện Ban tổ chức: thương hiệu hàng đầu Thái Lan 2020 tại Hà Nội lần này được tổ chức với mong muốn giúp tái khởi động các hoạt động giao thương giữa các nhà nhập khẩu và đại lý hàng Thái Lan tại Việt Nam và các nhà xuất khẩu Thái Lan, đóng góp một phần vào việc củng cố và tăng cường hơn nữa quan hệ thương mại giữa hai nước Việt Nam-Thái Lan cũng như sự hợp tác chặt chẽ trong phát triển kinh tế giữa các nước ASEAN thời kỳ sau đại dịch COVID-19.', '2020-07-12 07:00:00.0', '2020-07-21 18:00:00.0', 1, 100);
INSERT INTO `hoinghi` VALUES (16, 'Hội nghị thường niên 2020', 'HỘI NGHỊ THƯỜNG NIÊN LIÊN CHI HỘI HÔ HẤP TP HỒ CHÍ MINH 2020', '1. Đại dịch Coronavirus: từ cơ chế sinh học phân tử SARS-CoV-2\nđến bệnh lý học COVID-19\" ( Video 25p).\nGS TS Đinh Xuân Anh Tuấn . Hiệu trưởng ĐH Corse Pháp\n2. Cập nhật COVID-19 và xử trí bệnh hô hấp trên BN COVID19( 25p)\nPGS TS Trần Văn Ngọc . Chủ tịch Liên chi hội Hô hấp TPHCM\n3. PMI: Đánh giá tiềm năng giảm thiểu nguy cơ ung thư phổi của các\nsản phẩm chứa nicotin và thuốc lá thế hệ mới - Vấn đề của việc\nđáp ứng liều dùng (video 20p) .\nGS. David Khayat , Khoa Ung thư ĐH Pierre & Marie , Pháp\n4. GSK: Sự cần thiết của vaccine Bạch hầu – Ho gà – Uốn ván cho\nngười cao tuổi bị hen – COPD.( 20p).\nBS Gaurav Mathur . GĐYK vaccine GSK', '2020-08-18 07:00:00.0', '2020-08-19 19:00:00.0', 11, 300);
INSERT INTO `hoinghi` VALUES (17, 'Hội nghị khoa học thông tin và máy tính', 'Hội nghị khoa học thông tin và máy tính (NICS) là diễn đàn quốc tế dành cho các nhà nghiên cứu, triển khai và các nhà hoạch định chính sách', 'Hội nghị khoa học thông tin và máy tính (NICS) là diễn đàn quốc tế dành cho các nhà nghiên cứu, triển khai và các nhà hoạch định chính sách trình bày, thảo luận về những tiến bộ gần đây cũng như hướng đi trong tương lai trong việc giải quyết các thách thức trong lĩnh vực khoa học thông tin và máy tính. NICS được tổ chức hàng năm bởi Quỹ phát triển khoa học và công nghệ quốc gia (NAFOSTED)\n\nNICS 2020 sẽ được tổ chức tại TP Hồ Chí Minh dưới sự đăng cai của Trường Đại học Khoa học Tự nhiên (Đại học Quốc gia TP Hồ Chí Minh) và sự tài trợ của Hội kỹ sư điện và điện tử (IEEE), NAFOSTED, Đại học Quốc gia TP Hồ Chí Minh.\n\nHội nghị sẽ bao gồm các phiên sau:\n\n- Chương trình chính: Nghiên cứu trong lĩnh vực khoa học thông tin và máy tính.\n\n- Hội thảo nghiên cứu sinh: Hội thảo dành cho nghiên cứu sinh và học viên cao học trao đổi về chương trình nghiên cứu tiến sỹ.\n\n- Phiên trao đổi giữa các chuyên gia từ trường đại học và công ty công nghệ.\n\n- Hướng dẫn.\n\nNhững công bố tốt nhất sẽ được mời đăng trên Tạp chí Intelligent Data Analysis (thuộc danh mục ISI) và Tạp chí Journal of Information and Communications. \n\nCác mốc thời gian chính:\n\n* Ngày hội nghị: 26-27/11/2020\n\n* Hạn chót nhận bài: 3/8/2020\n\n* Thông báo chấp nhận bài: 28/9/2020\n\n* Hạn chót nộp bài hoàn chỉnh: 12/10/2020', '2020-08-25 07:00:00.0', '2020-08-28 07:00:00.0', 13, 15);
INSERT INTO `hoinghi` VALUES (18, 'Hội thảo khoa học quốc gia kỷ niệm 200 năm mất của đại thi hào Nguyễn Du', 'Hội thảo khoa học quốc gia kỷ niệm 200 năm năm mất của đại thi hào Nguyễn Du', 'Hướng tới  kỷ niệm 200 năm năm mất của đại thi hào Nguyễn Du, Viện Văn học (Viện Hàn lâm Khoa học Xã hội Việt Nam) sẽ tổ chức hội thảo khoa học quốc gia: Nguyễn Du - Truyện Kiều qua văn bản và các liên văn bản văn chương, nghệ thuật. Hội thảo nhằm kết nối những thành tựu nghiên cứu về đại thi hào Nguyễn Du và kiệt tác Truyện Kiều ở cả hai khía cạnh học thuật và hội nhập... Các chủ đề chính sẽ thảo luận tại hội thảo gồm: Những tìm tòi, phát hiện mới về văn bản, các thông tin/diễn giải mới về thân thế, cuộc đời, sự nghiệp của Nguyễn Du, vấn đề chuyển ngữ, tái tạo Truyện Kiều trong các ngôn ngữ khác; tái tạo Truyện Kiều trong các tác phẩm đương đại, hình ảnh/chân dung nhà văn và tác phẩm dưới các hình thức nghệ thuật (sân khấu, điện ảnh, âm nhạc, hội họa, điêu khắc…).', '2020-08-27 08:07:46.0', '2020-08-29 20:07:51.0', 9, 2);
INSERT INTO `hoinghi` VALUES (19, 'Hội nghị toàn quốc hệ thống Văn phòng Điều phối nông thôn mới', 'Văn phòng Điều phối nông thôn mới Trung ương trân trọng kính mời quý đại biểu tới dự Hội nghị toàn quốc Văn phòng Điều phối nông thôn mới các cấp', 'a) Đánh giá kết quả thực hiện Chương trình NTM năm 2018, trong đó kiểm điểm vai trò và chất lượng công tác tham mưu của hệ thống Văn phòng Điều phối nông thôn mới các cấp (Trung ương, tỉnh, huyện);\n\nb) Thảo luận và đề xuất các giải pháp, định hướng triển khai các nhiệm vụ trọng tâm trong năm 2019 theo chỉ đạo của Trưởng Ban Chỉ đạo Trung ương và Thường trực Chương trình (Chương trình OCOP; Môi trường nông thôn; Phát triển kinh tế du lịch nông thôn gắn với phát huy bản sắc văn hóa; Thôn bản xây dựng nông thôn mới; nâng cao chất lượng các tiêu chí sau đạt chuẩn, xây dựng nông thôn mới kiểu mẫu; khoa học công nghệ phục vụ xây dựng nông thôn mới; nâng cao chất lượng công tác truyền thông…) và kịp thời tháo gỡ, khắc phục một số khó khăn tồn tại trong quá trình thực hiện…\n\nc) Triển khai Kế hoạch Tổng kết 10 năm thực hiện Chương trình giai đoạn 2010-2020', '2020-08-25 19:55:50.9', '2020-08-28 19:55:57.6', 1, 78);
INSERT INTO `hoinghi` VALUES (22, 'Hội nghị thường niên khoa học', 'Hội nghị cho mọi người', 'Hội nghị cho mọi người', '2020-08-20 12:14:51.3', '2020-08-24 12:14:53.4', 13, 10);

-- ----------------------------
-- Table structure for nguoithamgiahoinghi
-- ----------------------------
DROP TABLE IF EXISTS `nguoithamgiahoinghi`;
CREATE TABLE `nguoithamgiahoinghi`  (
  `user` int(0) NOT NULL,
  `hoinghi` int(0) NOT NULL,
  `thoigianthamgia` datetime(1) NULL DEFAULT CURRENT_TIMESTAMP(1) ON UPDATE CURRENT_TIMESTAMP(1),
  `isallow` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`user`, `hoinghi`) USING BTREE,
  INDEX `hoinghitochuc`(`hoinghi`) USING BTREE,
  CONSTRAINT `hoinghitochuc` FOREIGN KEY (`hoinghi`) REFERENCES `hoinghi` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `nguoithamgiahoinghi` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nguoithamgiahoinghi
-- ----------------------------
INSERT INTO `nguoithamgiahoinghi` VALUES (13, 15, '2020-07-21 22:22:22.5', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (13, 17, '2020-07-23 12:11:16.6', 1);
INSERT INTO `nguoithamgiahoinghi` VALUES (13, 18, '2020-07-23 12:11:11.0', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (14, 16, '2020-07-11 20:13:16.3', 1);
INSERT INTO `nguoithamgiahoinghi` VALUES (14, 17, '2020-07-11 20:13:12.8', 1);
INSERT INTO `nguoithamgiahoinghi` VALUES (14, 18, '2020-07-11 20:13:08.7', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (17, 17, '2020-07-23 11:54:07.8', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (18, 17, '2020-07-23 10:29:44.1', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (19, 17, '2020-07-22 22:36:49.3', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (20, 17, '2020-07-22 22:37:09.2', 1);
INSERT INTO `nguoithamgiahoinghi` VALUES (21, 16, '2020-07-22 22:33:23.4', 1);
INSERT INTO `nguoithamgiahoinghi` VALUES (21, 17, '2020-07-23 10:29:26.8', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (22, 17, '2020-07-23 10:29:35.1', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (23, 17, '2020-07-23 11:51:46.4', 0);
INSERT INTO `nguoithamgiahoinghi` VALUES (24, 17, '2020-07-23 11:50:11.7', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `timeCreate` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `isDelete` bit(1) NULL DEFAULT b'0',
  `permission` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12, 'd', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-11 18:49:01', b'0', 1);
INSERT INTO `user` VALUES (13, 'dd', 'c4ca4238a0b923820dcc509a6f75849b', 'Huỳnh Duy Nè', 'duy81999@gmail.com', '2020-07-11 18:49:26', b'0', 0);
INSERT INTO `user` VALUES (14, 'duy', '5dc6da3adfe8ccf1287a98c0a8f74496', 'Duy', 'duy@gmail.com', '2020-07-11 18:49:36', b'0', 0);
INSERT INTO `user` VALUES (16, 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL, NULL, '2020-07-11 22:34:11', b'0', 1);
INSERT INTO `user` VALUES (17, 'd1', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-22 22:32:36', b'0', 0);
INSERT INTO `user` VALUES (18, 'd2', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-22 22:32:43', b'0', 0);
INSERT INTO `user` VALUES (19, 'd3', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-22 22:32:48', b'0', 0);
INSERT INTO `user` VALUES (20, 'd4', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-22 22:32:54', b'0', 0);
INSERT INTO `user` VALUES (21, 'd5', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-22 22:32:59', b'0', 0);
INSERT INTO `user` VALUES (22, 'd6', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-22 22:33:03', b'0', 0);
INSERT INTO `user` VALUES (23, 'd7', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-23 11:51:03', b'0', 0);
INSERT INTO `user` VALUES (24, 'd8', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL, '2020-07-22 22:33:11', b'0', 0);

SET FOREIGN_KEY_CHECKS = 1;
