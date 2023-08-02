package com.example.demo.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception_handling.ResourceNotFoundException;
import com.example.demo.models.usermodels;
import com.example.demo.payload.userpayload;
import com.example.demo.repository.userrepository;
import com.example.demo.services.userservices;
@Service
public class userservices_imp implements userservices {
	
	@Autowired
	userrepository repo;
	@Autowired
	ModelMapper modelmapper;

	@Override
	public userpayload addusers(userpayload adu) {
		usermodels u = this.dto_users(adu);
		usermodels savesusermodels = this.repo.save(u);
		return this.users_dto(savesusermodels);
	}

	@Override
	public userpayload updateusers(userpayload adu, int id) {
		usermodels u = this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("usermodels","id",id));
		u.setName(adu.getName());
		u.setEmail(adu.getEmail());
		u.setPassword(adu.getPassword());
		usermodels u1 = this.repo.save(u);
		userpayload up = this.users_dto(u1);
		return up;
	}
	public void deleteusers(int id) {
		usermodels u = this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("usermodels","id",id));
		this.repo.delete(u);
	}

	@Override
	public List<userpayload> getallusers() {
		List<usermodels> u=(List<usermodels>)this.repo.findAll();
		List<userpayload> adu = u.stream().map(user ->this.users_dto(user)).collect(Collectors.toList());
		return adu;
	
		
	}
	
	public usermodels dto_users(userpayload up) {
		usermodels u = this.modelmapper.map(up,usermodels.class);
				return u;
	}
	public userpayload users_dto(usermodels u) {
		userpayload useradu = this.modelmapper.map(u, userpayload.class);
				return useradu;
	
	}

	@Override
	public userpayload getuserstbyid(int id) {
			usermodels u =this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("usermodels","id",id));
			
			return this.users_dto(u) ;
	}
	
}	
