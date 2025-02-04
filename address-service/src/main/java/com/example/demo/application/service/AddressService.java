package com.example.demo.application.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.response.AddressResponse;
import com.example.demo.domain.entity.Address;
import com.example.demo.infrastructure.repository.AddressRepository;
import com.example.demo.infrastructure.request.AddressRequest;

import jakarta.transaction.Transactional;

@Service
public class AddressService {

	@Autowired
    private AddressRepository repository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public Optional<AddressResponse> getById(Long id){

        Optional<Address> address = repository.findById(id);
        
        Optional<AddressResponse> aResponse = address.map(addr ->{
        	return modelMapper.map(addr, AddressResponse.class);
        });
        return aResponse;
    }
	
	public Address registerAddress(Address address) {
		try {
			repository.save(address);
		}catch (Exception e ) {
			System.out.println(e.getMessage());
		}
		return address;
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public List<Address> getAll() {
		return repository.findAll();
	}
	
	@Transactional
    public Address update(Long id, AddressRequest request) {
    	
		Address address = repository.findById(id)
    			.orElseThrow(()-> new IllegalStateException("El cliente no existe"));
		
		address.setId(id);
		
		if (request.getPostalCode() != null) address.setPostalCode(request.getPostalCode());
		
		if (request.getStreetAndNumber() != null) address.setStreetAndNumber(request.getStreetAndNumber());
		
		if (request.getCountry() != null) address.setCountry(request.getCountry());
		
		if (request.getDoor() != null) address.setDoor(request.getDoor());
    	
    	return address;
    }
}
