package org.yc.spring_cache.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yc.spring_cache.model.MUser;
import org.yc.spring_cache.model.TestBean;
import org.yc.spring_cache.service.MUserServiceI;
import org.yc.spring_cache.service.TestBeanService;

@Controller
@RequestMapping("/muser")
public class MUserController {

	@Autowired
	private MUserServiceI muserService;
	
	@Autowired
	private TestBeanService testBeanService;

	private Log log = LogFactory.getLog(MUserController.class);

	@RequestMapping(value = "/listUser")
	public String listUser(HttpServletRequest request) {
		List<MUser> list = muserService.getAll();
		request.setAttribute("userlist", list);
		return "user/listUser";
	}

	@RequestMapping(value = "/addUser")
	public String addUser(MUser muser) {
		// String id = UUID.randomUUID().toString();
		// muser.setId(id);
		try {
			muserService.insert(muser);
			testBeanService.insert(new TestBean(muser.getName()));
		} catch (Exception e) {
			log.error("�����쳣", e);
			e.printStackTrace();
		}
		return "redirect:/muser/listUser.do";
	}

	@RequestMapping(value = "/deleteUser")
	public String deleteUser(Integer id) {
		muserService.delete(id);
		return "redirect:/muser/listUser.do";
	}

	@RequestMapping(value = "/updateUserUI")
	public String updateUserUI(Integer id, HttpServletRequest request) {
		MUser muser = muserService.selectByPrimaryKey(id);
		request.setAttribute("user", muser);
		return "user/updateUser";
	}

	@RequestMapping(value = "/updateUser")
	public String updateUser(MUser muser) {
		muserService.update(muser);
		return "redirect:/muser/listUser.do";
	}
}
