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
    public class ProductsController : ControllerBase
    {

        private readonly ProductRepository repository;
        public ProductsController(ProductRepository repository)
        {
            this.repository = repository;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Product>>> Get()
        {
            return await repository.GetAllProducts();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Product>> Get(int id)
        {
            return await repository.GetProductById(id);
        }

        [HttpPost]
        public async Task<ActionResult<Product>> Post(Product product)
        {
            await repository.CreateProduct(product);
            return CreatedAtAction("Get", new { id = product.Id }, product);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult<Product>> Delete(int id)
        {
            var product = await repository.DeleteProduct(id);
            if (product == null)
            {
                return NotFound();
            }
            return product;
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, Product product)
        {
            if (id != product.Id)
            {
                return BadRequest();
            }

            await repository.UpdateProduct(product);
            return NoContent();
        }
    }
}
