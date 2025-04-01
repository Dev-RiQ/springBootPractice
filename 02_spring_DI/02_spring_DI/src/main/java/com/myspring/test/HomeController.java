package com.myspring.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	_01Member test1 = new _01Member();
	
	// [1단계]
	@Autowired
	_01Member test2;		// _01Member test2 = member; 와 같은 문장이다.
	
	@Autowired
	_01Member test3;		// _01Member test3 = member; 와 같은 문장이다.
	
	// [2단계]
	@Autowired
	_02UserDAO userDAO;
	
	// [3단계]
	@Autowired
	_03ClientDAO clientDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		// [1단계] 호출
		System.out.println(">>> 1단계");
		System.out.println("test1의 주소 = " + test1);
		test1.add(100);
		test1.print();		// 100

		System.out.println("test2의 주소 = " + test2);
		System.out.println("test3의 주소 = " + test3);
		test2.add(100);
		test3.add(200);
		test2.print();		// 300
		test3.print();		// 300
		
		System.out.println("=====================");
		
		// [2단계] 호출
		System.out.println(">>> 2단계");
		userDAO.print();	// User
		
		System.out.println("=====================");
		
		// [3단계] 호출
		System.out.println(">>> 3단계");
		String companyName = clientDAO.getCompanyName();
		System.out.println("companyName = " + companyName);
		System.out.println("=====================");
		
		ArrayList<String> clientList = clientDAO.getClientList();
		for(String name : clientList) {
			System.out.println("clientList = " + name);
		}
		
		System.out.println("=====================");
		
		HashMap<String, String> clientMap1 = clientDAO.getClientMap1();
		for(String key : clientMap1.keySet()) {
			System.out.println("key = " + key + ", value = " + clientMap1.get(key));
		}
		
		System.out.println("=====================");
		
		HashMap<String, _03Client> clientMap2 = clientDAO.getClientMap2();
		for(String key : clientMap2.keySet()) {
			System.out.print("key = " + key + " ");
			clientMap2.get(key).printInfo();
		}
		System.out.println("=====================");
		Iterator<String> iter = clientMap2.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			System.out.print("key = " + key + " ");
			clientMap2.get(key).printInfo();
		}
		System.out.println("=====================");
		
		return "home";
	}
	
}







