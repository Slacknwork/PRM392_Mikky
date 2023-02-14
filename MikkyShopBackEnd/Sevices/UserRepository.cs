using Microsoft.EntityFrameworkCore;
using MikkyLibrary.Models;
using MikkyShopBackEnd.Models;
using System.Collections.Generic;
using System.Linq;

namespace MikkyShopBackEnd.Sevices
{
    public class UserRepository : IRepository<UserVM,UserM>
    {
        private readonly MikkyContext _context;
        public UserRepository(MikkyContext context)
        {
            _context = context;
        }

        public UserVM Add(UserM y)
        {
            var user = new User
            {
                Username = y.Username,
                Password = y.Password,
                Address = y.Address,
                Phonenumber = y.Phonenumber,
                Role = y.Role,
            };
            _context.Users.Add(user);
            _context.SaveChanges();
            return new UserVM { 
                UserId = user.UserId,
                Username = user.Username,
                Password = user.Password,
                Address = user.Address,
                Phonenumber= user.Phonenumber,
                Role= user.Role,
            };
        }

        public void Delete(int id)
        {
            var user = UserExists(id);
            if(user != null)
            {
                _context.Users.Remove(user);
                _context.SaveChanges();
            }
        }

        public void Delete(int id1, int id2)
        {
            throw new System.NotImplementedException();
        }

        public List<UserVM> GetAll()
        {
            var luser = _context.Users.Select(user => new UserVM
            {
                UserId = user.UserId,
                Username = user.Username,
                Password= user.Password,
                Address = user.Address,
                Phonenumber= user.Phonenumber,
                Role= user.Role,
            });
            return luser.ToList();
        }

        public UserVM GetById(int id)
        {
            var user = UserExists(id);
            if (user != null)
            {
                return new UserVM
                {
                    UserId = user.UserId,
                    Username = user.Username,
                    Password = user.Password,
                    Address = user.Address,
                    Phonenumber = user.Phonenumber,
                    Role = user.Role,
                };
            }
            return null;
        }

        public List<UserVM> GetByNameList(string name)
        {
            var luser =_context.Users.Where(user => user.Username.Contains(name));
            if (luser != null && luser.Count() > 0)
            {
                var luserco = luser.Select(user => new UserVM
                {
                    UserId = user.UserId,
                    Username = user.Username,
                    Password = user.Password,
                    Address = user.Address,
                    Phonenumber = user.Phonenumber,
                    Role = user.Role,
                });
                return luserco.ToList();
            }
            return null;
        }

        public void Update(UserVM t)
        {
            var user = UserExists(t.UserId);
            if (user != null)
            {
                user.Username = t.Username;
                user.Password = t.Password;
                user.Address = t.Address;
                user.Phonenumber = t.Phonenumber;
                user.Role = t.Role;
                _context.Users.Update(user);
                _context.Entry(user).State = EntityState.Modified;
                _context.SaveChanges();
            }
        }
        private User UserExists(int id)
        {
            return _context.Users.SingleOrDefault(user => user.UserId == id);
        }
    }
}
