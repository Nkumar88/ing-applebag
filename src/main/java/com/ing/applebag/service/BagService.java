package com.ing.applebag.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ing.applebag.dao.BagRepository;
import com.ing.applebag.model.Bag;

@Service
public class BagService {
	@Autowired
	private BagRepository bagRepository;
	private List<String> supplierList = Arrays.asList("Royal Gala","Pink Lady", "Kanzi Apple","Elstar Apples");
	
	public String addBag(Bag bag)
	{
		//If numOfApples are out of expected range
		if(bag.getNumOfApples() < 1 || bag.getNumOfApples() > 100) 
		{
		    return "numOfApples should be between 1 and 100";
		}
	    if(!supplierList.contains(bag.getSupplier()))
		    return "Supplier must be one of 'Royal Gala','Pink Lady', 'Kanzi Apple','Elstar Apples'.";
	    if(bag.getPrice()< 1 || bag.getPrice() > 50)
		    return "Price should be between 1 and 50";	
		
	    bagRepository.save(bag);
		return "Bag Added.";
	}
	
	public List<Bag> getBags()
	{
		/*Note: We can also, always return 3 bags whether or not user added them by themselves 
		 * by un-commenting below block 
		 * if(bagRepository.count() == 0) //If there are 0 bags, add 3 bags and return {
		 * addBag(new Bag(1,"Royal Gala",10f)); addBag(new Bag(2,"Pink Lady",15f));
		 * addBag(new Bag(3,"Kanzi Apple",20f)); } if(bagRepository.count() == 1) //If
		 * there is 1 bag, add 2 bags and return { addBag(new Bag(1,"Royal Gala",10f));
		 * addBag(new Bag(2,"Pink Lady",15f)); } if(bagRepository.count() == 2) //If
		 * there are 2 bags, add 1 bag and return { addBag(new Bag(1,"Royal Gala",10f));
		 * }
		 */
		List<Bag> bags;
		if(bagRepository.count() < 3)
		{
			bags = Arrays.asList();
			return bags;
		}
		
		Pageable top3 = PageRequest.of(0, 3);
		bags = bagRepository.findByOrderByIdDesc(top3); 
		return bags;
	}
	
	public List<Bag> getBags(int limit)
	{
		Pageable topx = PageRequest.of(0, limit);
		List<Bag> bags = bagRepository.findByOrderByIdDesc(topx); 
		return bags;
	}

}
