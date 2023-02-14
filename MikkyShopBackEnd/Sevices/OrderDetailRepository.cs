using Microsoft.EntityFrameworkCore;
using MikkyLibrary.Models;
using MikkyShopBackEnd.Models;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;

namespace MikkyShopBackEnd.Sevices
{
    public class OrderDetailRepository : IRepository<OrderDetailVM, OrderDetailM>
    {
        private readonly MikkyContext _context;
        public OrderDetailRepository(MikkyContext context)
        {
            _context = context;
        }

        public OrderDetailVM Add(OrderDetailM y)
        {
            var ordet = new OrderDetail
            {
                OrderId = y.OrderId,
                DrinkId = y.DrinkId,
                Price= y.Price,
                Quantity= y.Quantity,
            };
            _context.OrderDetails.Add(ordet);
            _context.SaveChanges();
            return new OrderDetailVM
            {
                OrderId = ordet.OrderId,
                DrinkId = ordet.DrinkId,
                Price = ordet.Price,
                Quantity = ordet.Quantity,
            };
        }

        public void DeleteAll(int id)
        {
            var lordet = OrderDetailsExists(id);
            if(lordet != null && lordet.Count > 0)
            {
                _context.OrderDetails.RemoveRange(lordet);
                _context.SaveChanges();
            }
        }
        public void Delete(int ordid, int driid)
        {
            var ordet = OrderDetailExists(ordid,driid);
            if (ordet != null)
            {
                _context.OrderDetails.Remove(ordet);
                _context.SaveChanges();
            }
        }

        public List<OrderDetailVM> GetAll()
        {
            var lordet = _context.OrderDetails.Select(ordet => new OrderDetailVM
            {
                OrderId= ordet.OrderId,
                DrinkId= ordet.DrinkId,
                Price= ordet.Price,
                Quantity= ordet.Quantity,
            });
            return lordet.ToList();
        }
        #region Not Implemented
        public void Delete(int id)
        {
            throw new System.NotImplementedException();
        }
        public OrderDetailVM GetById(int id)
        {
            throw new System.NotImplementedException();
        }

        public List<OrderDetailVM> GetByNameList(string name)
        {
            throw new System.NotImplementedException();
        }
        #endregion
        public OrderDetailVM GetById(int ordid, int driid)
        {
            var ordet = OrderDetailExists(ordid,driid);
            if(ordet != null)
            {
                return new OrderDetailVM
                {
                    OrderId = ordet.OrderId,
                    DrinkId = ordet.DrinkId,
                    Price = ordet.Price,
                    Quantity = ordet.Quantity,
                };
            }
            return null;
        }
        public List<OrderDetailVM> GetByIdListOrder(int ordid)
        {
            var lordet = _context.OrderDetails.Where(ord => ord.OrderId == ordid);
            if (lordet != null && lordet.Count() > 0)
            {
                var lordetvm = lordet.Select(ordet => new OrderDetailVM
                {
                    OrderId = ordet.OrderId,
                    DrinkId = ordet.DrinkId,
                    Price = ordet.Price,
                    Quantity = ordet.Quantity,
                });
                return lordetvm.ToList();
            }
            return null;
        }
        public List<OrderDetailVM> GetByIdListDrink(int driid)
        {
            var lordet = _context.OrderDetails.Where(ord => ord.DrinkId == driid);
            if (lordet != null && lordet.Count() > 0)
            {
                var lordetvm = lordet.Select(ordet => new OrderDetailVM
                {
                    OrderId = ordet.OrderId,
                    DrinkId = ordet.DrinkId,
                    Price = ordet.Price,
                    Quantity = ordet.Quantity,
                });
                return lordetvm.ToList();
            }
            return null;
        }
        public void Update(OrderDetailVM t)
        {
            var ordet = OrderDetailExists(t.OrderId, t.DrinkId);
            if(ordet != null)
            {
                ordet.Price = t.Price;
                ordet.Quantity = t.Quantity;
                _context.OrderDetails.Update(ordet);
                _context.Entry(ordet).State = EntityState.Modified;
                _context.SaveChanges();
            }
        }
        private List<OrderDetail> OrderDetailsExists(int orderid)
        {
            return _context.OrderDetails.Where(ordet => ordet.OrderId == orderid).ToList();
        }
        private OrderDetail OrderDetailExists(int orderid, int drinkid)
        {
            return _context.OrderDetails.SingleOrDefault(ordet => ordet.OrderId == orderid && ordet.DrinkId == drinkid);
        }

    }
}
