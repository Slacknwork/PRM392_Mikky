using System;

namespace MikkyShopBackEnd.Models
{
    public class OrderVM
    {
        public int OrderId { get; set; }
        public int UserId { get; set; }
        public DateTime? Date { get; set; }
        public string Status { get; set; }
        public double? TotalPrice { get; set; }
    }
}
