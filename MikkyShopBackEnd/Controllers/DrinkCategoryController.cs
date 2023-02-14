using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MikkyShopBackEnd.Models;
using MikkyShopBackEnd.Sevices;
using System;

namespace MikkyShopBackEnd.Controllers
{
    [Route("Mikky/[controller]")]
    [ApiController]
    public class DrinkCategoryController : ControllerBase
    {
        private readonly IRepository<DrinkCategoryVM, DrinkCategoryM> _drcat;

        public DrinkCategoryController(IRepository<DrinkCategoryVM, DrinkCategoryM> drcat)
        {
            _drcat = drcat;
        }
        [HttpGet]
        public IActionResult GetAll()
        {
            try
            {
                return Ok(_drcat.GetAll());
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpGet("DCid={id}")]
        public IActionResult GetById(int id)
        {
            try
            {
                var data = _drcat.GetById(id);
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
        [HttpGet("DCname={name}")]
        public IActionResult GetByNameList(string name)
        {
            try
            {
                var data = _drcat.GetByNameList(name);
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
        public IActionResult Create(DrinkCategoryM drcatM)
        {
            try
            {
                return Ok(_drcat.Add(drcatM));
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpPut("update/DCid={id}")]
        public IActionResult Edit(DrinkCategoryM drcatM, int id)
        {
            var drcat = _drcat.GetById(id);
            if (drcat == null)
            {
                return BadRequest();
            }
            try
            {
                drcat.DrinkCateName = drcatM.DrinkCateName;
                _drcat.Update(drcat);
                return Ok(drcat);
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpDelete("delete/DCid={id}")]
        public IActionResult Delete(int id)
        {
            try
            {
                _drcat.Delete(id);
                return Ok();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
    }
}
