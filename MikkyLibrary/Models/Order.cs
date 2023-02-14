﻿using System;
using System.Collections.Generic;

#nullable disable

namespace MikkyLibrary.Models
{
    public partial class Order
    {
        public int OrderId { get; set; }
        public int UserId { get; set; }
        public DateTime? Date { get; set; }
        public string Status { get; set; }
        public double? TotalPrice { get; set; }

        public virtual User User { get; set; }
    }
}
