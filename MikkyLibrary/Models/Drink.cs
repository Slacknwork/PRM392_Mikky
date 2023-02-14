using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

#nullable disable

namespace MikkyLibrary.Models
{
    public partial class Drink
    {
        public int DrinkId { get; set; }
        public string Drinkname { get; set; }
        public int DrinkCateId { get; set; }
        public string DrinkImage { get; set; }
        public string Description { get; set; }
        public double? Price { get; set; }

        public virtual DrinkCategory DrinkCate { get; set; }
    }
}
