package com.mattioda.rodrigo.socialbook.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.dto.UserDto;
import com.mattioda.rodrigo.socialbook.services.HomePageService;

@RestController
@RequestMapping(value="/")
public class HomePageResource {

	@Autowired
	private HomePageService homePageService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<User>> findPage(
			@RequestParam(name="page", defaultValue="0")Integer page,
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(name="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(name="direction", defaultValue="DESC") String direction
			){
		Page<User> list= homePageService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDto userDto){
		User user = homePageService.fromDto(userDto);
		user= homePageService.insert(user);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registrar(Model model){
		ModelAndView mv = new ModelAndView("/register");
		return mv;
	}
}
