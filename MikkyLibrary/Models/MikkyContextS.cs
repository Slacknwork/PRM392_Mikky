using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MikkyLibrary.Models
{
    public partial class MikkyContext : DbContext
    {
        public MikkyContext(string ConnectionString)
        {
            this.Database.SetConnectionString(ConnectionString);
        }
    }
}
