using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TechStartPro.Data;

namespace TechStartPro.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public abstract class BaseController<TEntity, TRepository> : ControllerBase
        where TEntity : class, IEntity
        where TRepository : IRepository<TEntity>
    {
        private readonly TRepository repository;

        public BaseController(TRepository repository)
        {
            this.repository = repository;
        }

        /// <summary>
        /// Endpoint for get all data.
        /// </summary>
        /// <returns>
        /// List of all objects
        /// </returns>
        [HttpGet]
        public async Task<ActionResult<IEnumerable<TEntity>>> Get()
        {
            return await repository.GetAll();
        }

        /// <summary>
        /// Endpoint for get data by id.
        /// </summary>
        /// <param name="id">Data id</param>
        /// <returns>Object</returns>
        [HttpGet("{id}")]
        public async Task<ActionResult<TEntity>> Get(int id)
        {
            var element = await repository.Get(id);
            if (element == null)
            {
                return NotFound();
            }
            return element;
        }

        // PUT: api/[controller]/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, TEntity element)
        {
            if (id != element.Id)
            {
                return BadRequest();
            }
            await repository.Update(element);
            return NoContent();
        }

        // POST: api/[controller]
        [HttpPost]
        public async Task<ActionResult<TEntity>> Post(TEntity element)
        {
            await repository.Add(element);
            return CreatedAtAction("Get", new { id = element.Id }, element);
        }

        // DELETE: api/[controller]/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<TEntity>> Delete(int id)
        {
            var element = await repository.Delete(id);
            if (element == null)
            {
                return NotFound();
            }
            return element;
        }

    }
}
