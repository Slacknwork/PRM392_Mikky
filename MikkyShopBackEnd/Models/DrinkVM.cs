namespace MikkyShopBackEnd.Models
{
    public class DrinkVM
    {
        public int DrinkId { get; set; }
        public string Drinkname { get; set; }
        public int DrinkCateId { get; set; }
        public string DrinkImage { get; set; }
        public string Description { get; set; }
        public double? Price { get; set; }
    }
}
