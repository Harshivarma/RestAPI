package com.example.demo.services;

import java.util.List;

import com.example.demo.payload.userpayload;


public interface userservices {
	userpayload addusers(userpayload adu);
	userpayload updateusers(userpayload upu,int id);
	void deleteusers(int id);
	List<userpayload>getallusers();
	userpayload getuserstbyid(int id);
	
	
		
	}
	


