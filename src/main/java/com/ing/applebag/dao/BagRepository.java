package com.ing.applebag.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.applebag.model.Bag;

public interface BagRepository extends JpaRepository<Bag, String>{
	List<Bag> findByOrderByIdDesc(Pageable pageable);

}
