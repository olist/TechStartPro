using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TechStartPro.Models;

namespace TechStartPro.Data.EFCore
{
    public class ProductRepository : EfCoreRepository<Product, DBContext>
    {
        public ProductRepository(DBContext context) : base(context)
        {

        }
    }
}
