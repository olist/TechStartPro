using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;
using TechStartPro.Data;

namespace TechStartPro.Models
{
    public class ProductCategory
    {
        [ForeignKey("ProductForeignKey")]
        public int ProductId { get; set; }

        [ForeignKey("CategoryForeignKey")]
        public int CategoryId { get; set; }
    }
}
