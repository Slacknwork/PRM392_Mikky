using System;
using System.Collections.Generic;

#nullable disable

namespace MikkyLibrary.Models
{
    public partial class User
    {
        public User()
        {
            Orders = new HashSet<Order>();
        }

        public int UserId { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public int? Phonenumber { get; set; }
        public string Address { get; set; }
        public int? Role { get; set; }

        public virtual ICollection<Order> Orders { get; set; }
    }
}
