package com.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.AllCountDAO;
import com.ui.model.AllCount;

@RestController
public class AllCountController {
	@Autowired
	AllCountDAO allCountDao;
	
	AllCount allCount;
	
private static final Logger logger = LoggerFactory.getLogger(AllCountController.class);
	
	@RequestMapping(value="/getAllCounts", method= RequestMethod.GET, produces = "application/json")
	public AllCount getAllCounts(HttpServletRequest request) {
		logger.info("***** GET ALL COUNTS *****");		
		AllCount allCount = allCountDao.getAllCounts();		
		return allCount;
	}
}
