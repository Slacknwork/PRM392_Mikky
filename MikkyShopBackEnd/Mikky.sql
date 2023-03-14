
DROP DATABASE IF EXISTS [Mikky]
CREATE DATABASE [Mikky];
GO
USE [Mikky]
GO
DROP TABLE IF EXISTS [Admin]
DROP TABLE IF EXISTS [OrderDetail]
DROP TABLE IF EXISTS [Order]
DROP TABLE IF EXISTS [Drink]
DROP TABLE IF EXISTS [DrinkCategory]
DROP TABLE IF EXISTS [Customer]
GO
/*CREATE TABLE [dbo].[Admin](
	Id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	Username nvarchar(50) NOT NULL,
	[Password] nvarchar(max) NOT NULL,
)*/
CREATE TABLE [dbo].[User](
	UserID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	Username nvarchar(50) NOT NULL,
	[Password] varchar(max) NOT NULL,
	Phonenumber int,
	[Address] nvarchar(max),
	[Role] int,
)
CREATE TABLE [dbo].[DrinkCategory](
	DrinkCateID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	DrinkCateName nvarchar(50) NOT NULL,
)
CREATE TABLE [dbo].[Drink](
	DrinkID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	Drinkname nvarchar(50) NOT NULL,
	DrinkCateID int NOT NULL FOREIGN KEY REFERENCES DrinkCategory(DrinkCateID),
	DrinkImage nvarchar(max),
	[Description] nvarchar(max),
	Price float,
)
CREATE TABLE [dbo].[Order](
	OrderID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	UserID int NOT NULL FOREIGN KEY REFERENCES [User](UserID),
	[Date] datetime,
	[Status] varchar(50),
	TotalPrice float,
)
CREATE TABLE [dbo].[OrderDetail](
	OrderID int NOT NULL FOREIGN KEY REFERENCES [Order](OrderID),
	DrinkID int NOT NULL FOREIGN KEY REFERENCES Drink(DrinkID),
	Quantity int,
	Price float,
	PRIMARY KEY (OrderID,DrinkID)
)

