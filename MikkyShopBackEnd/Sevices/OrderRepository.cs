using Microsoft.EntityFrameworkCore;
using MikkyLibrary.Models;
using MikkyShopBackEnd.Models;
using System.Collections.Generic;
using System.Linq;

namespace MikkyShopBackEnd.Sevices
{
    public class OrderRepository : IRepository<OrderVM, OrderM>
    {
        private readonly MikkyContext _context;
        public OrderRepository(MikkyContext context)
        {
            _context = context;
        }

        public OrderVM Add(OrderM y)
        {
            var order = new Order
            {
                UserId= y.UserId,
                Date= y.Date,
                Status= y.Status,
                TotalPrice = y.TotalPrice
            };
            _context.Orders.Add(order);
            _context.SaveChanges();
            return new OrderVM
            {
                OrderId = order.OrderId,
                UserId = order.UserId,
                Date= order.Date,
                Status= order.Status,
                TotalPrice = order.TotalPrice
            };
        }

        public void Delete(int id)
        {
            var ord = OrderExists(id);
            if(ord != null)
            {
                _context.Orders.Remove(ord);
                _context.SaveChanges();
            }
        }

        public void Delete(int id1, int id2)
        {
            throw new System.NotImplementedException();
        }

        public void DeleteAll(int id)
        {
            throw new System.NotImplementedException();
        }

        public List<OrderVM> GetAll()
        {
            var lord = _context.Orders.Select(ord => new OrderVM
            {
                OrderId = ord.OrderId,
                UserId = ord.UserId,
                Date = ord.Date,
                Status = ord.Status,
                TotalPrice = ord.TotalPrice
            });
            return lord.ToList();
        }

        public OrderVM GetById(int id)
        {
            var ord = OrderExists(id);
            if(ord != null)
            {
                return new OrderVM
                {
                    OrderId = ord.OrderId,
                    UserId = ord.UserId,
                    Date = ord.Date,
                    Status = ord.Status,
                    TotalPrice = ord.TotalPrice
                };
            }
            return null;
        }

        public OrderVM GetById(int id1, int id2)
        {
            throw new System.NotImplementedException();
        }
        #region Not Implemented
        public List<OrderVM> GetByNameList(string name)
        {
            throw new System.NotImplementedException();
        }
        #endregion
        public List<OrderVM> GetByUseridList(int userId)
        {
            var lord = _context.Orders.Where(ord => ord.UserId == userId);
            if(lord != null && lord.Count() > 0)
            {
                var lordvm = lord.Select(ord => new OrderVM
                {
                    OrderId = ord.OrderId,
                    UserId = ord.UserId,
                    Date = ord.Date,
                    Status = ord.Status,
                    TotalPrice = ord.TotalPrice
                });
                return lordvm.ToList();
            }
            return null;
        }

        public void Update(OrderVM t)
        {
            var ord = OrderExists(t.OrderId);
            if(ord != null)
            {
                ord.UserId = t.UserId;
                ord.Date = t.Date;
                ord.Status = t.Status;
                ord.TotalPrice = t.TotalPrice;
                _context.Orders.Update(ord);
                _context.Entry(ord).State = EntityState.Modified;
                _context.SaveChanges();
            }

        }
        private Order OrderExists(int id)
        {
            return _context.Orders.SingleOrDefault(ord => ord.OrderId == id);
        }
    }
}
