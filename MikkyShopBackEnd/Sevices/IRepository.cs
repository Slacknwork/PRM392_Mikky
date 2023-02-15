using System.Collections.Generic;

namespace MikkyShopBackEnd.Sevices
{
    public interface IRepository<T, Y> where T : class where Y : class 
    {
        List<T> GetAll();
        T GetById(int id);
        T GetById(int id1, int id2);
        List<T> GetByNameList(string name);
        T Add(Y y);
        void Update(T t);
        void Delete(int id);
        void Delete(int id1, int id2);
        void DeleteAll(int id);
    }
}
