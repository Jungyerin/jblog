package com.jx372.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jx372.jblog.service.UserService;
import com.jx372.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping( value="/join",method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo){
		return "user/join";
	}
	
	@RequestMapping( value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model){
		
		if(result.hasErrors()){
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error: list){
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	
	@RequestMapping( value="/joinsuccess")
	public String joinsuccess(){
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "user/login";		
	}

}
