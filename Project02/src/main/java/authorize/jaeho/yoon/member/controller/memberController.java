package authorize.jaeho.yoon.member.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import authorize.jaeho.yoon.member.dao.MemberDAO;
import authorize.jaeho.yoon.member.vo.Member;




@Controller
public class memberController {
	
	@Autowired
	MemberDAO dao;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login1(String employee_id, String password, HttpSession session){
		Member login = dao.selectOne(employee_id);
		if (login != null && login.getPassword().equals(password)) {
			session.setAttribute("id", login.getEmployee_ID());
			session.setAttribute("name", login.getEmployee_name());
			return "redirect:/main";
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String login2(){
		return "main";
	}
	
	@RequestMapping(value ="logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}
