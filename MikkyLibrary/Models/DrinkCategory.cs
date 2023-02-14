using System;
using System.Collections.Generic;

#nullable disable

namespace MikkyLibrary.Models
{
    public partial class DrinkCategory
    {
        public DrinkCategory()
        {
            Drinks = new HashSet<Drink>();
        }

        public int DrinkCateId { get; set; }
        public string DrinkCateName { get; set; }

        public virtual ICollection<Drink> Drinks { get; set; }
    }
}
