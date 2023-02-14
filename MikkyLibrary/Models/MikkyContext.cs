using System;
using System.IO;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.Extensions.Configuration;

#nullable disable

namespace MikkyLibrary.Models
{
    public partial class MikkyContext : DbContext
    {
        public MikkyContext()
        {
        }

        public MikkyContext(DbContextOptions<MikkyContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Drink> Drinks { get; set; }
        public virtual DbSet<DrinkCategory> DrinkCategories { get; set; }
        public virtual DbSet<Order> Orders { get; set; }
        public virtual DbSet<OrderDetail> OrderDetails { get; set; }
        public virtual DbSet<User> Users { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
                optionsBuilder.UseSqlServer(GetConnectionString());
            }
        }
        private string GetConnectionString()
        {
            IConfiguration config = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.json", true, true)
                .Build();
            var strConn = config["ConnectionStrings:Mikky"];
            return strConn;
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.HasAnnotation("Relational:Collation", "SQL_Latin1_General_CP1_CI_AS");

            modelBuilder.Entity<Drink>(entity =>
            {
                entity.ToTable("Drink");

                entity.Property(e => e.DrinkId).HasColumnName("DrinkID");

                entity.Property(e => e.DrinkCateId).HasColumnName("DrinkCateID");

                entity.Property(e => e.DrinkImage).HasColumnType("image");

                entity.Property(e => e.Drinkname)
                    .IsRequired()
                    .HasMaxLength(50);

                entity.HasOne(d => d.DrinkCate)
                    .WithMany(p => p.Drinks)
                    .HasForeignKey(d => d.DrinkCateId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Drink__DrinkCate__3B75D760");
            });

            modelBuilder.Entity<DrinkCategory>(entity =>
            {
                entity.HasKey(e => e.DrinkCateId)
                    .HasName("PK__DrinkCat__1410FD879E73D0C7");

                entity.ToTable("DrinkCategory");

                entity.Property(e => e.DrinkCateId).HasColumnName("DrinkCateID");

                entity.Property(e => e.DrinkCateName)
                    .IsRequired()
                    .HasMaxLength(50);
            });

            modelBuilder.Entity<Order>(entity =>
            {
                entity.ToTable("Order");

                entity.Property(e => e.OrderId).HasColumnName("OrderID");

                entity.Property(e => e.Date).HasColumnType("datetime");

                entity.Property(e => e.Status)
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Orders)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Order__UserID__3E52440B");
            });

            modelBuilder.Entity<OrderDetail>(entity =>
            {
                entity.HasNoKey();

                entity.ToTable("OrderDetail");

                entity.Property(e => e.DrinkId).HasColumnName("DrinkID");

                entity.Property(e => e.OrderId).HasColumnName("OrderID");

                entity.HasOne(d => d.Drink)
                    .WithMany()
                    .HasForeignKey(d => d.DrinkId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__OrderDeta__Drink__412EB0B6");

                entity.HasOne(d => d.Order)
                    .WithMany()
                    .HasForeignKey(d => d.OrderId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__OrderDeta__Order__403A8C7D");
            });

            modelBuilder.Entity<User>(entity =>
            {
                entity.ToTable("User");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.Property(e => e.Password)
                    .IsRequired()
                    .IsUnicode(false);

                entity.Property(e => e.Username)
                    .IsRequired()
                    .HasMaxLength(50);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
