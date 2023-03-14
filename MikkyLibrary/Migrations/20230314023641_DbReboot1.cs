using Microsoft.EntityFrameworkCore.Migrations;

namespace MikkyLibrary.Migrations
{
    public partial class DbReboot1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK__Drink__DrinkCate__3B75D760",
                table: "Drink");

            migrationBuilder.DropForeignKey(
                name: "FK__Order__UserID__3E52440B",
                table: "Order");

            migrationBuilder.DropForeignKey(
                name: "FK__OrderDeta__Drink__412EB0B6",
                table: "OrderDetail");

            migrationBuilder.DropForeignKey(
                name: "FK__OrderDeta__Order__403A8C7D",
                table: "OrderDetail");

            migrationBuilder.DropIndex(
                name: "IX_OrderDetail_OrderID",
                table: "OrderDetail");

            migrationBuilder.DropPrimaryKey(
                name: "PK__DrinkCat__1410FD8718663686",
                table: "DrinkCategory");

            migrationBuilder.AddPrimaryKey(
                name: "PK__OrderDet__5F991693E4016F11",
                table: "OrderDetail",
                columns: new[] { "OrderID", "DrinkID" });

            migrationBuilder.AddPrimaryKey(
                name: "PK__DrinkCat__1410FD874E18804D",
                table: "DrinkCategory",
                column: "DrinkCateID");

            migrationBuilder.AddForeignKey(
                name: "FK__Drink__DrinkCate__60A75C0F",
                table: "Drink",
                column: "DrinkCateID",
                principalTable: "DrinkCategory",
                principalColumn: "DrinkCateID",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK__Order__UserID__6383C8BA",
                table: "Order",
                column: "UserID",
                principalTable: "User",
                principalColumn: "UserID",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK__OrderDeta__Drink__66603565",
                table: "OrderDetail",
                column: "DrinkID",
                principalTable: "Drink",
                principalColumn: "DrinkID",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK__OrderDeta__Order__656C112C",
                table: "OrderDetail",
                column: "OrderID",
                principalTable: "Order",
                principalColumn: "OrderID",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK__Drink__DrinkCate__60A75C0F",
                table: "Drink");

            migrationBuilder.DropForeignKey(
                name: "FK__Order__UserID__6383C8BA",
                table: "Order");

            migrationBuilder.DropForeignKey(
                name: "FK__OrderDeta__Drink__66603565",
                table: "OrderDetail");

            migrationBuilder.DropForeignKey(
                name: "FK__OrderDeta__Order__656C112C",
                table: "OrderDetail");

            migrationBuilder.DropPrimaryKey(
                name: "PK__OrderDet__5F991693E4016F11",
                table: "OrderDetail");

            migrationBuilder.DropPrimaryKey(
                name: "PK__DrinkCat__1410FD874E18804D",
                table: "DrinkCategory");

            migrationBuilder.AddPrimaryKey(
                name: "PK__DrinkCat__1410FD8718663686",
                table: "DrinkCategory",
                column: "DrinkCateID");

            migrationBuilder.CreateIndex(
                name: "IX_OrderDetail_OrderID",
                table: "OrderDetail",
                column: "OrderID");

            migrationBuilder.AddForeignKey(
                name: "FK__Drink__DrinkCate__3B75D760",
                table: "Drink",
                column: "DrinkCateID",
                principalTable: "DrinkCategory",
                principalColumn: "DrinkCateID",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK__Order__UserID__3E52440B",
                table: "Order",
                column: "UserID",
                principalTable: "User",
                principalColumn: "UserID",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK__OrderDeta__Drink__412EB0B6",
                table: "OrderDetail",
                column: "DrinkID",
                principalTable: "Drink",
                principalColumn: "DrinkID",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK__OrderDeta__Order__403A8C7D",
                table: "OrderDetail",
                column: "OrderID",
                principalTable: "Order",
                principalColumn: "OrderID",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
