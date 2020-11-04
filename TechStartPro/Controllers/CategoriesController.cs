using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TechStartPro.Data.EFCore;
using TechStartPro.Models;

namespace TechStartPro.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoriesController : BaseController<Category, CategoryRepository>
    {
        public CategoriesController(CategoryRepository repository) : base(repository)
        {

        }
    }
}
