using System;
using System.Collections.Generic;

#nullable disable

namespace MikkyLibrary.Models
{
    public partial class OrderDetail
    {
        public int OrderId { get; set; }
        public int DrinkId { get; set; }
        public int? Quantity { get; set; }
        public double? Price { get; set; }

        public virtual Drink Drink { get; set; }
        public virtual Order Order { get; set; }
    }
}
