using System;

namespace MikkyShopBackEnd.Models
{
    public class OrderM
    {
        public int UserId { get; set; }
        public DateTime? Date { get; set; }
        public string Status { get; set; }
        public double? TotalPrice { get; set; }
    }
}
