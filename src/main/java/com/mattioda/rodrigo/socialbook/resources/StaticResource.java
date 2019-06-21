package com.mattioda.rodrigo.socialbook.resources;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/index")
public class StaticResource {

	@GetMapping
	public ModelAndView showView(Model model){
		ModelAndView view= new ModelAndView("/index");
		
		return view;	
	}

	
}
