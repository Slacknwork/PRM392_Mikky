USE [Mikky]
GO
/*INSERT INTO [dbo].[User](Username, [Password], Phonenumber, [Address],[Role])
VALUES  (N'ThanhDT','123456',0955158528,N'85 Hai Ba Trung',0),
		(N'NgaND','nga123',0762992922,N'45 Le Van Viet',0),
		(N'abc','123',null,null,1),
		(N'TranNT','yuso569',0848922629,N'68 Hoi An',0);*/
INSERT INTO [dbo].[DrinkCategory] (DrinkCateName)
VALUES	(N'MilkTea'),(N'Sinh to'),(N'Juice');
/*INSERT INTO [dbo].[Drink] (Drinkname, DrinkCateID, DrinkImage, Description, Price)
VALUES	(N'Trà sữa trân châu',1,N'https://pos.cafeongbau.com:4433/images/list/tra_sua_tran_chau.png',N'Trà sữa trân châu là sự kết hợp truyền thống giữa trân châu đen dai cùng vị trà sữa trà đen. Thức uống quen thuộc với mọi lứa tuổi.',30000),
		(N'Trà sữa bạc hà',1,N'https://product.hstatic.net/1000355252/product/2018-03-20-08_44_10_bacha_9fccbee2deaf475bb832b410b2cc76e9_grande.jpeg',N'Trà sữa bạc hà có bạc hà thanh mát, cay dịu vừa đủ, thêm vị sữa ngậy.',35000),
		(N'Sinh tố dâu',2,N'https://product.hstatic.net/200000388411/product/dau2_b2f4da868c4347fd942aa896c754c95c.png',N'Dâu tây ngoài ăn không còn có thể làm sinh tố rất ngon, sinh tố dâu tây đã quá phổ biến nhờ hương vị đặc trưng, béo mịn mà ai cũng thích.',25000),
		(N'Sinh tố xoài',2,N'http://bizweb.dktcdn.net/thumb/grande/100/438/465/products/22ab96083245f11ba854.jpg?v=1655371159900',N'Sinh tố xoài là đồ uống chứa nhiều vitamin C được rất nhiều người yêu thích, đặc biệt trong những ngày trời nóng oi bức.',25000),
		(N'Nước ép dua hấu',3,N'https://image.optcdn.me/8fa10de53b648664220e0b7f5e729a3e-nuoc-ep-dua-hau.jpg',N'Giữa những ngày hè nóng bức, một ly nước ép dưa hấu sẽ giúp bạn giải nhiệt và tiếp thêm nhiều năng lượng để làm việc và học tập hiệu quả.',20000),
		(N'Nước ép táo',3,N'https://bokhocomai.com/wp-content/uploads/2020/06/Nu%CC%9Bo%CC%9B%CC%81c-e%CC%81p-ta%CC%81o-1.png',N'Nước ép táo là một trong những lựa chọn hoàn hảo để giải nhiệt cho cơ thể vào những ngày thời tiết nắng nóng.',25000),
		(N'Nước ép cam',3,N'https://anhmuoifood.com/wp-content/uploads/2022/09/nuoc-ep-camhc.png',N'Nước Ép Cam chứa nhiều chất xơ và chất dinh dưỡng có thể hỗ trợ tăng cường sức khỏe tổng thể.',25000),
		(N'Sinh tố sapoche',2,N'https://product.hstatic.net/200000388411/product/sinhto-sapoche_014200e3709c4450a2989173d951d9d4.png',N'Sinh tố sapoche là món thức uống quen thuộc nhưng khi kết hợp vị ngọt lịm của sapoche với một chút đăng đắng của cà phê tạo nên thức uống mới lạ mà bạn không thể bỏ qua.',25000),
		(N'Trà sữa Chocolate',1,N'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-s%E1%BB%AFa-Chocolate-2.png',N'Hương vị Chocolate đậm đà hòa tan sâu trong vị sữa hảo hạng.',40000),
		(N'Trà sữa trân châu đường đen',1,N'https://pozaatea.vn/wp-content/uploads/2021/08/2-1.png',N'Sữa tươi trân châu đường đen đang là món hot trend trên thị trường, vị béo ngậy của sữa tươi thanh trùng, vị dai dai ngọt ngọt của trân châu đường đen khiến bạn uống mãi không dừng.',40000);
INSERT INTO [dbo].[Order] (UserID, Date,Status, TotalPrice)
VALUES	(1,'2022-12-23',N'Delivered',30000),
		(3,'2023-01-05',N'Delivered',60000),
		(1,'2023-01-16',N'Delivered',20000);
INSERT INTO [dbo].[OrderDetail] (OrderID, DrinkID, Quantity, Price)
VALUES	(1,1,1,30000),
		(2,2,1,35000),
		(2,3,1,25000),
		(3,5,1,20000);*/
SELECT * FROM  [dbo].[User];
SELECT * FROM  [dbo].[DrinkCategory];
SELECT * FROM  [dbo].[Drink];
SELECT * FROM  [dbo].[Order];
SELECT * FROM  [dbo].[OrderDetail];