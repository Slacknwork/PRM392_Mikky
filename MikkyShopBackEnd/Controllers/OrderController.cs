using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MikkyShopBackEnd.Models;
using MikkyShopBackEnd.Sevices;
using System;

namespace MikkyShopBackEnd.Controllers
{
    [Route("Mikky/[controller]")]
    [ApiController]
    public class OrderController : ControllerBase
    {
        private readonly IRepository<OrderVM, OrderM> _ord;

        public OrderController(IRepository<OrderVM, OrderM> ord)
        {
            _ord = ord;
        }
        [HttpGet]
        public IActionResult GetAll()
        {
            try
            {
                return Ok(_ord.GetAll());
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpGet("Ordid={id}")]
        public IActionResult GetById(int id)
        {
            try
            {
                var data = _ord.GetById(id);
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
        [HttpGet("Ordid={ordid}&Userid={usrid}")]
        public IActionResult GetById(int ordid, int usrid)
        {
            try
            {
                var data = _ord.GetById(ordid,usrid);
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

        [HttpGet("Userid={usrid}")]
        public IActionResult GetByUserIdList(int usrid)
        {
            try
            {
                var data = _ord.GetAll().FindAll(op => op.UserId == usrid);
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
        public IActionResult Create(OrderM orderM)
        {
            try
            {
                return Ok(_ord.Add(orderM));
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpPut("update/Ordid={id}")]
        public IActionResult Edit(OrderM orderM, int id)
        {
            var ord = _ord.GetById(id);
            if (ord == null)
            {
                return BadRequest();
            }
            try
            {
                ord.Status = orderM.Status;
                ord.Date = orderM.Date;
                ord.TotalPrice = orderM.TotalPrice;
                ord.UserId = orderM.UserId;
                _ord.Update(ord);
                return Ok(ord);
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpDelete("delete/Ordid={id}")]
        public IActionResult Delete(int id)
        {
            try
            {
                _ord.Delete(id);
                return Ok();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
    }
}
