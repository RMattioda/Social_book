package com.mattioda.rodrigo.socialbook.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.services.HomePageService;

@RestController
@RequestMapping(value="/index")
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
}
