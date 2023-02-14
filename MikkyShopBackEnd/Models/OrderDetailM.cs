namespace MikkyShopBackEnd.Models
{
    public class OrderDetailM
    {
        public int OrderId { get; set; }
        public int DrinkId { get; set; }
        public int? Quantity { get; set; }
        public double? Price { get; set; }
    }
}
