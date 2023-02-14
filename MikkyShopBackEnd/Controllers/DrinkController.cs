using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MikkyShopBackEnd.Models;
using MikkyShopBackEnd.Sevices;
using System;

namespace MikkyShopBackEnd.Controllers
{
    [Route("Mikky/[controller]")]
    [ApiController]
    public class DrinkController : ControllerBase
    {
        private readonly IRepository<DrinkVM, DrinkM> _dri;

        public DrinkController(IRepository<DrinkVM, DrinkM> dri)
        {
            _dri = dri;
        }
        [HttpGet]
        public IActionResult GetAll()
        {
            try
            {
                return Ok(_dri.GetAll());
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpGet("Drid={id}")]
        public IActionResult GetById(int id)
        {
            try
            {
                var data = _dri.GetById(id);
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
        [HttpGet("Drname={name}")]
        public IActionResult GetByNameList(string name)
        {
            try
            {
                var data = _dri.GetByNameList(name);
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
        public IActionResult Create(DrinkM driM)
        {
            try
            {
                return Ok(_dri.Add(driM));
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpPut("update/Drid={id}")]
        public IActionResult Edit(DrinkM driM, int id)
        {
            var dri = _dri.GetById(id);
            if (dri == null)
            {
                return BadRequest();
            }
            try
            {
                dri.Drinkname = driM.Drinkname;
                dri.DrinkImage = driM.DrinkImage;
                dri.Price = driM.Price;
                dri.Description = driM.Description;
                dri.DrinkCateId = driM.DrinkCateId;
                _dri.Update(dri);
                return Ok(dri);
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpDelete("delete/Drid={id}")]
        public IActionResult Delete(int id)
        {
            try
            {
                _dri.Delete(id);
                return Ok();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
    }
}
