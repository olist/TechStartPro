using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using TechStartPro.Data;

namespace TechStartPro.Models
{
    public class Category : IEntity
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public ICollection<ProductCategory> ProductCategory { get; set; }

    }
}
