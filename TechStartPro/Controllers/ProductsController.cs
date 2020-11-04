using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TechStartPro.Data.EFCore;
using TechStartPro.Models;

namespace TechStartPro.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductsController : BaseController <Product, ProductRepository>
    {
        public ProductsController(ProductRepository repository) : base(repository)
        {

        }
    }
}
