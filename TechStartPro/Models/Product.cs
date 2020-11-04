using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;
using TechStartPro.Data;

namespace TechStartPro.Models
{
    public class Product : IEntity
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Description { get; set; }

        public double Price { get; set; }

        public ICollection<ProductCategory> ProductCategory { get; set; }

        [NotMapped]
        public virtual ICollection<Category> Categories { get; set; }

    }

}
