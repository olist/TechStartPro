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

        private readonly ProductRepository repository;
        public ProductsController(ProductRepository repository) : base(repository)
        {
            this.repository = repository;
        }

        [HttpGet("getall")]
        public new async Task<ActionResult<IEnumerable<Product>>> Get()
        {
            return await repository.GetAllProducts();
        }

        [HttpPost("postproduct")]
        public new async Task<ActionResult<Product>> Post(Product product)
        {
            await repository.CreateProduct(product);
            return CreatedAtAction("Get", new { id = product.Id }, product);
        }

        [HttpDelete("deleteproduct/{id}")]
        public new async Task<ActionResult<Product>> Delete(int id)
        {
            var product = await repository.DeleteProduct(id);
            if (product == null)
            {
                return NotFound();
            }
            return product;
        }
    }
}
