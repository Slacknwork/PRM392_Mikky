using Microsoft.EntityFrameworkCore;
using MikkyLibrary.Models;
using MikkyShopBackEnd.Models;
using System.Collections.Generic;
using System.Linq;

namespace MikkyShopBackEnd.Sevices
{
    public class DrinkRepository : IRepository<DrinkVM,DrinkM>
    {
        private readonly MikkyContext _context;
        public DrinkRepository(MikkyContext context)
        {
            _context = context;
        }

        public DrinkVM Add(DrinkM y)
        {
            var dri = new Drink
            {
                Drinkname = y.Drinkname,
                DrinkImage = y.DrinkImage,
                Description = y.Description,
                Price = y.Price,
                DrinkCateId = y.DrinkCateId,

            };
            _context.Drinks.Add(dri);
            _context.SaveChanges();
            return new DrinkVM
            {
                DrinkId = dri.DrinkId,
                Drinkname = dri.Drinkname,
                DrinkImage = dri.DrinkImage,
                Description = dri.Description,
                Price = dri.Price,
                DrinkCateId = dri.DrinkCateId,
            };
        }

        public void Delete(int id)
        {
            var dri = DrinkExists(id);
            if(dri !=null)
            {
                _context.Drinks.Remove(dri);
                _context.SaveChanges();
            };
        }

        public void Delete(int id1, int id2)
        {
            throw new System.NotImplementedException();
        }

        public List<DrinkVM> GetAll()
        {
            var ldri = _context.Drinks.Select(dri => new DrinkVM
            {
                DrinkId = dri.DrinkId,
                Drinkname = dri.Drinkname,
                Price = dri.Price,
                Description = dri.Description,
                DrinkCateId = dri.DrinkCateId,
                DrinkImage = dri.DrinkImage,
            });
            return ldri.ToList();
        }

        public DrinkVM GetById(int id)
        {
            var dri = DrinkExists(id);
            if (dri != null)
            {
                return new DrinkVM
                {
                    DrinkId = dri.DrinkId,
                    Drinkname = dri.Drinkname,
                    Price = dri.Price,
                    Description = dri.Description,
                    DrinkImage = dri.DrinkImage,
                    DrinkCateId = dri.DrinkCateId,
                };
            }
            return null;
        }

        public List<DrinkVM> GetByNameList(string name)
        {
            var ldri = _context.Drinks.Where(dri => dri.Drinkname.Contains(name));
            if (ldri != null && ldri.Count() > 0)
            {
                var ldrivm = ldri.Select(dri => new DrinkVM
                {
                    DrinkId= dri.DrinkId,
                    Drinkname= dri.Drinkname,
                    Price = dri.Price,
                    Description = dri.Description,
                    DrinkImage= dri.DrinkImage,
                    DrinkCateId = dri.DrinkCateId,
                });
                return ldrivm.ToList();
            }
            return null;
        }

        public void Update(DrinkVM t)
        {
            var dri = DrinkExists(t.DrinkId);
            if (dri != null)
            {
                dri.Drinkname = t.Drinkname;
                dri.DrinkImage = t.DrinkImage;
                dri.Price = t.Price;
                dri.Description = t.Description;
                dri.DrinkCateId= t.DrinkCateId;
                _context.Drinks.Update(dri);
                _context.Entry(dri).State = EntityState.Modified;
                _context.SaveChanges();
            }
        }
        private Drink DrinkExists(int id)
        {
            return _context.Drinks.SingleOrDefault(dri => dri.DrinkId == id);
        }
    }
}
