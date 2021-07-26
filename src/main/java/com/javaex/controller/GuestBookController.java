package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	//addList
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("[GuestbookController.addList]");
		
		//db
		guestbookDao.getList();
		
		//model
		model.addAttribute("guestbookList", guestbookDao);
		
		return "/addList";
	}

	//add
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo gbVo) {
		System.out.println(gbVo);
		
		//DB
		guestbookDao.insert(gbVo);
		
		return "redirct:/addList";
	}
	
	//deleteForm
	@RequestMapping(value="/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @RequestParam("no") int no) {
		System.out.println(no);
		
		//model
		model.addAttribute("no", no);
		
		return "/deleteForm";
	}
	
	//delete
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo gbVo) {
		
		//DB
		guestbookDao.delete(gbVo);
		
		return "redirect:/addList";
	}
	
}

