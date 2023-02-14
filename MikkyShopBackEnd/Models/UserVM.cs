namespace MikkyShopBackEnd.Models
{
    public class UserVM
    {
        public int UserId { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public int? Phonenumber { get; set; }
        public string Address { get; set; }
        public int? Role { get; set; }
    }
}
