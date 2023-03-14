﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using MikkyLibrary.Models;

namespace MikkyLibrary.Migrations
{
    [DbContext(typeof(MikkyContext))]
    [Migration("20230314023641_DbReboot1")]
    partial class DbReboot1
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Relational:Collation", "SQL_Latin1_General_CP1_CI_AS")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.17")
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("MikkyLibrary.Models.Drink", b =>
                {
                    b.Property<int>("DrinkId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasColumnName("DrinkID")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Description")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int>("DrinkCateId")
                        .HasColumnType("int")
                        .HasColumnName("DrinkCateID");

                    b.Property<string>("DrinkImage")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Drinkname")
                        .IsRequired()
                        .HasMaxLength(50)
                        .HasColumnType("nvarchar(50)");

                    b.Property<double?>("Price")
                        .HasColumnType("float");

                    b.HasKey("DrinkId");

                    b.HasIndex("DrinkCateId");

                    b.ToTable("Drink");
                });

            modelBuilder.Entity("MikkyLibrary.Models.DrinkCategory", b =>
                {
                    b.Property<int>("DrinkCateId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasColumnName("DrinkCateID")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("DrinkCateName")
                        .IsRequired()
                        .HasMaxLength(50)
                        .HasColumnType("nvarchar(50)");

                    b.HasKey("DrinkCateId")
                        .HasName("PK__DrinkCat__1410FD874E18804D");

                    b.ToTable("DrinkCategory");
                });

            modelBuilder.Entity("MikkyLibrary.Models.Order", b =>
                {
                    b.Property<int>("OrderId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasColumnName("OrderID")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<DateTime?>("Date")
                        .HasColumnType("datetime");

                    b.Property<string>("Status")
                        .HasMaxLength(50)
                        .IsUnicode(false)
                        .HasColumnType("varchar(50)");

                    b.Property<double?>("TotalPrice")
                        .HasColumnType("float");

                    b.Property<int>("UserId")
                        .HasColumnType("int")
                        .HasColumnName("UserID");

                    b.HasKey("OrderId");

                    b.HasIndex("UserId");

                    b.ToTable("Order");
                });

            modelBuilder.Entity("MikkyLibrary.Models.OrderDetail", b =>
                {
                    b.Property<int>("OrderId")
                        .HasColumnType("int")
                        .HasColumnName("OrderID");

                    b.Property<int>("DrinkId")
                        .HasColumnType("int")
                        .HasColumnName("DrinkID");

                    b.Property<double?>("Price")
                        .HasColumnType("float");

                    b.Property<int?>("Quantity")
                        .HasColumnType("int");

                    b.HasKey("OrderId", "DrinkId")
                        .HasName("PK__OrderDet__5F991693E4016F11");

                    b.HasIndex("DrinkId");

                    b.ToTable("OrderDetail");
                });

            modelBuilder.Entity("MikkyLibrary.Models.User", b =>
                {
                    b.Property<int>("UserId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasColumnName("UserID")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Address")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Password")
                        .IsRequired()
                        .IsUnicode(false)
                        .HasColumnType("varchar(max)");

                    b.Property<int?>("Phonenumber")
                        .HasColumnType("int");

                    b.Property<int?>("Role")
                        .HasColumnType("int");

                    b.Property<string>("Username")
                        .IsRequired()
                        .HasMaxLength(50)
                        .HasColumnType("nvarchar(50)");

                    b.HasKey("UserId");

                    b.ToTable("User");
                });

            modelBuilder.Entity("MikkyLibrary.Models.Drink", b =>
                {
                    b.HasOne("MikkyLibrary.Models.DrinkCategory", "DrinkCate")
                        .WithMany("Drinks")
                        .HasForeignKey("DrinkCateId")
                        .HasConstraintName("FK__Drink__DrinkCate__60A75C0F")
                        .IsRequired();

                    b.Navigation("DrinkCate");
                });

            modelBuilder.Entity("MikkyLibrary.Models.Order", b =>
                {
                    b.HasOne("MikkyLibrary.Models.User", "User")
                        .WithMany("Orders")
                        .HasForeignKey("UserId")
                        .HasConstraintName("FK__Order__UserID__6383C8BA")
                        .IsRequired();

                    b.Navigation("User");
                });

            modelBuilder.Entity("MikkyLibrary.Models.OrderDetail", b =>
                {
                    b.HasOne("MikkyLibrary.Models.Drink", "Drink")
                        .WithMany("OrderDetails")
                        .HasForeignKey("DrinkId")
                        .HasConstraintName("FK__OrderDeta__Drink__66603565")
                        .IsRequired();

                    b.HasOne("MikkyLibrary.Models.Order", "Order")
                        .WithMany("OrderDetails")
                        .HasForeignKey("OrderId")
                        .HasConstraintName("FK__OrderDeta__Order__656C112C")
                        .IsRequired();

                    b.Navigation("Drink");

                    b.Navigation("Order");
                });

            modelBuilder.Entity("MikkyLibrary.Models.Drink", b =>
                {
                    b.Navigation("OrderDetails");
                });

            modelBuilder.Entity("MikkyLibrary.Models.DrinkCategory", b =>
                {
                    b.Navigation("Drinks");
                });

            modelBuilder.Entity("MikkyLibrary.Models.Order", b =>
                {
                    b.Navigation("OrderDetails");
                });

            modelBuilder.Entity("MikkyLibrary.Models.User", b =>
                {
                    b.Navigation("Orders");
                });
#pragma warning restore 612, 618
        }
    }
}
