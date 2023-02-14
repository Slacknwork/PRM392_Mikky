using Microsoft.EntityFrameworkCore;
using MikkyLibrary.Models;
using MikkyShopBackEnd.Models;
using System.Collections.Generic;
using System.Linq;

namespace MikkyShopBackEnd.Sevices
{
    public class DrinkCategoryRepository : IRepository<DrinkCategoryVM,DrinkCategoryM>
    {
        private readonly MikkyContext _context;
        public DrinkCategoryRepository(MikkyContext context)
        {
            _context = context;
        }

        public DrinkCategoryVM Add(DrinkCategoryM y)
        {
            var drcat = new DrinkCategory
            {
                DrinkCateName = y.DrinkCateName,
            };
            _context.DrinkCategories.Add(drcat);
            _context.SaveChanges();
            return new DrinkCategoryVM
            {
                DrinkCateName = drcat.DrinkCateName
            };
        }

        public void Delete(int id)
        {
            var drcat = DrCatExists(id);
            if(drcat != null)
            {
                _context.DrinkCategories.Remove(drcat);
                _context.SaveChanges();
            }
        }

        public void Delete(int id1, int id2)
        {
            throw new System.NotImplementedException();
        }

        public List<DrinkCategoryVM> GetAll()
        {
            var lidrcat = _context.DrinkCategories.Select(drcat => new DrinkCategoryVM
            {
                DrinkCateId = drcat.DrinkCateId,
                DrinkCateName = drcat.DrinkCateName
            });
            return lidrcat.ToList();
        }

        public DrinkCategoryVM GetById(int id)
        {
            var drcat = DrCatExists(id);
            if(drcat != null)
            {
                return new DrinkCategoryVM
                {
                    DrinkCateId = drcat.DrinkCateId,
                    DrinkCateName = drcat.DrinkCateName
                };
            }
            return null;
        }

        public List<DrinkCategoryVM> GetByNameList(string name)
        {
            var lidrcat = _context.DrinkCategories.Where(drcat => drcat.DrinkCateName.Contains(name));
            if(lidrcat !=null && lidrcat.Count() > 0)
            {
                var lidrcatvm = lidrcat.Select(drcat => new DrinkCategoryVM
                {
                    DrinkCateId = drcat.DrinkCateId,
                    DrinkCateName = drcat.DrinkCateName,
                });
                return lidrcatvm.ToList();
            }
            return null;
        }

        public void Update(DrinkCategoryVM t)
        {
            var drcat = DrCatExists(t.DrinkCateId);
            if(drcat !=null)
            {
                drcat.DrinkCateName = t.DrinkCateName;
                _context.DrinkCategories.Update(drcat);
                _context.Entry(drcat).State = EntityState.Modified;
                _context.SaveChanges();
            }
        }
        private DrinkCategory DrCatExists(int id)
        {
            return _context.DrinkCategories.SingleOrDefault(drcat => drcat.DrinkCateId == id);
        }
    }
}
