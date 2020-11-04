using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TechStartPro.Models;

namespace TechStartPro.Data.EFCore
{
    public class CategoryRepository : EfCoreRepository<Category, DBContext>
    {
        public CategoryRepository(DBContext context) : base(context)
        {

        }
    }
}
