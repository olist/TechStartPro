using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using TechStartPro.Data;

namespace TechStartPro.Models
{
    public class Product : IEntity
    {
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(60)]
        public string Name { get; set; }

        [Required]
        [StringLength(60)]
        public string Description { get; set; }

        [Required]
        [DisplayFormat(DataFormatString = "{0:C2}")]
        public double Price { get; set; }

        public virtual ICollection<Category> Categories { get; set; }

    }
}
