namespace MikkyShopBackEnd.Models
{
    public class OrderDetailVM
    {
        public int OrderId { get; set; }
        public int DrinkId { get; set; }
        public int? Quantity { get; set; }
        public double? Price { get; set; }
    }
}
