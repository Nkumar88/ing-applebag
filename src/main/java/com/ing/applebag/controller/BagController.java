package com.ing.applebag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ing.applebag.model.Bag;
import com.ing.applebag.service.BagService;

@RestController
public class BagController{
	
	@Autowired
	private BagService service;	
		
	
	@PostMapping(value="/addBag", produces = "application/json")
	public String addBag(@RequestBody Bag bag)
	{	
		String respMessage = service.addBag(bag);
		if(!respMessage.equals("Bag Added."))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, respMessage);
	    
	    return respMessage;
	}
	
	@GetMapping(value="/getBags", produces = "application/json")
	public List<Bag> getBags()
	{	
		if(service.getBags().isEmpty())
			throw new ResponseStatusException(HttpStatus.PARTIAL_CONTENT, "Please add atleast 3 bags.");
		return service.getBags();
	}
	
	@GetMapping(value="/getBags/{limit}", produces = "application/json")
	public List<Bag> getBags(@PathVariable String limit)
	{
		int intLimit;
		try
		{
		 intLimit = Integer.parseInt(limit);
		}catch(NumberFormatException nfe)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested number of bags should be a valid integer.");
		}
		
		if(intLimit < 1)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested number of bags should be more than 0.");
		List<Bag> bagList = service.getBags(Integer.parseInt(limit));
		if(bagList.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Bag found.");
		return service.getBags(Integer.parseInt(limit));
	}
}
