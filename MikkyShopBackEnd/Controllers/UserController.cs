using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MikkyShopBackEnd.Models;
using MikkyShopBackEnd.Sevices;
using System;

namespace MikkyShopBackEnd.Controllers
{
    [Route("Mikky/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IRepository<UserVM, UserM> _usr;

        public UserController(IRepository<UserVM, UserM> usr)
        {
            _usr = usr;
        }
        [HttpGet]
        public IActionResult GetAll()
        {
            try
            {
                return Ok(_usr.GetAll());
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpGet("Userid={id}")]
        public IActionResult GetById(int id)
        {
            try
            {
                var data = _usr.GetById(id);
                if(data != null) 
                {
                    return Ok(data);
                }
                else
                {
                    return NotFound();
                }
            }
            catch(Exception e)
            {
                Console.WriteLine(e);
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpGet("Username={name}")]
        public IActionResult GetByNameList(string name)
        {
            try
            {
                var data = _usr.GetByNameList(name);
                if(data != null)
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
        public IActionResult Create (UserM userM)
        {
            try
            {
                return Ok(_usr.Add(userM));
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpPut("update/Userid={id}")]
        public IActionResult Edit(UserM userM, int id)
        {
            var usr = _usr.GetById(id);
            if(usr == null)
            {
                return BadRequest();
            }
            try
            {
                usr.Username = userM.Username;
                usr.Password = userM.Password;
                usr.Address = userM.Address;
                usr.Phonenumber = userM.Phonenumber;
                usr.Role = userM.Role;
                _usr.Update(usr);
                return Ok(usr);
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
        [HttpDelete("delete/Userid={id}")]
        public IActionResult Delete(int id)
        {
            try
            {
                _usr.Delete(id);
                return Ok();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }
    }
}
