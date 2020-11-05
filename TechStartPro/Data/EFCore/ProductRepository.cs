using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Razor.Language;
using Microsoft.CodeAnalysis.FlowAnalysis;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading.Tasks;
using TechStartPro.Models;

namespace TechStartPro.Data.EFCore
{
    public class ProductRepository : EfCoreRepository<Product, DBContext>
    {
        private readonly DBContext context;
        public ProductRepository(DBContext context) : base(context)
        {
            this.context = context;
        }

        public async Task<Product> CreateProduct(Product product)
        {
            List<ProductCategory> listProductCategory = new List<ProductCategory>();

            product.Categories.ToList().ForEach(c => {
                listProductCategory.Add(new ProductCategory
                {
                    Product = product,
                    CategoryId = c.Id
                });
            });

            product.ProductCategory = listProductCategory;

            context.Product.Add(product);
            await context.SaveChangesAsync();

            return product;
        }

        public async Task<Product> DeleteProduct(int id)
        {
            var product = await context.Product.FindAsync(id);

            if (product == null)
            {
                return product;
            }

            context.Product.Remove(product);
            await context.SaveChangesAsync();

            return product;
        }

        public async Task<List<Product>> GetAllProducts()
        {
            var products = await context.Product.Include(p => p.ProductCategory).ThenInclude(op => op.Category).ToListAsync();

            return products;
        }

        public async Task<Product> GetProductById(int id)
        {
            var product = await context.Product.FindAsync(id);
            product.Categories = new List<Category>();

            var productCategories = context.ProductCategory.Include(c => c.Category).Where(p => p.ProductId == id);

            productCategories.ToList().ForEach(c =>
            {
                product.ProductCategory.Add(c);
                product.Categories.Add(c.Category);
            });

            return product;
        }

        public async Task<Product> UpdateProduct(Product product)
        {
            product.Categories = product.Categories ?? new List<Category>();
            product.ProductCategory = product.ProductCategory ?? new List<ProductCategory>();

            if (!product.ProductCategory.Equals(product.Categories)) {

                List<ProductCategory> listProductCategory = new List<ProductCategory>();

                product.Categories.ToList().ForEach(c => {
                    listProductCategory.Add(new ProductCategory
                    {
                        ProductId = product.Id,
                        CategoryId = c.Id
                    });
                });

                context.ProductCategory.RemoveRange(product.ProductCategory);
                context.ProductCategory.AddRange(listProductCategory);
                product.ProductCategory = listProductCategory;
            }
            
            context.Entry(product).State = EntityState.Modified;
            await context.SaveChangesAsync();
            return product;
        }

    }
}
