using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MikkyShopBackEnd.Models;
using MikkyShopBackEnd.Sevices;
using System;

namespace MikkyShopBackEnd.Controllers
{
    [Route("Mikky/[controller]")]
    [ApiController]
    public class OrderDetailController : ControllerBase
    {
        private readonly IRepository<OrderDetailVM, OrderDetailM> _ordet;

        public OrderDetailController(IRepository<OrderDetailVM, OrderDetailM> ordet)
        {
            _ordet = ordet;
        }
        [HttpGet]
        public IActionResult GetAll()
        {
            try
            {
                return Ok(_ordet.GetAll());
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpGet("Ordetid={ordid}&Drid={drid}")]
        public IActionResult GetById(int ordid,int drid)
        {
            try
            {
                var data = _ordet.GetById(ordid,drid);
                if (data != null)
                {
                    return Ok(data);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpGet("Ordetid={ordid}")]
        public IActionResult GetByIdList(int ordid)
        {
            try
            {
                var data = _ordet.GetAll().FindAll(op => op.OrderId == ordid);
                if (data != null)
                {
                    return Ok(data);
                }
                else
                {
                    return NotFound();
                }
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPost("create")]
        public IActionResult Create(OrderDetailM orderdetailM)
        {
            try
            {
                return Ok(_ordet.Add(orderdetailM));
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpPut("update/Ordetid={id}")]
        public IActionResult Edit(OrderDetailM orderdetailM, int id)
        {
            var ordet = _ordet.GetById(id,orderdetailM.DrinkId);
            if (ordet == null)
            {
                return BadRequest();
            }
            try
            {
                ordet.Price = orderdetailM.Price;
                ordet.Quantity = orderdetailM.Quantity;
                _ordet.Update(ordet);
                return Ok(ordet);
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpDelete("delete/Ordid={ordid}&Drid={drid}")]
        public IActionResult Delete(int ordid, int drid)
        {
            try
            {
                _ordet.Delete(ordid,drid);
                return Ok();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
    }
}
