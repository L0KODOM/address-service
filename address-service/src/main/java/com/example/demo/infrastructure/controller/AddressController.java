package com.example.demo.infrastructure.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.response.AddressResponse;
import com.example.demo.application.service.AddressService;
import com.example.demo.domain.entity.Address;
import com.example.demo.infrastructure.request.AddressRequest;

@RestController
public class AddressController {
	

	@Autowired
    private AddressService service;
	
	@GetMapping("/address/{id}")
    public ResponseEntity<AddressResponse> getClient(@PathVariable("id") Long id) {
        Optional<AddressResponse> address = service.getById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok(address.get());  
        } else {
            return ResponseEntity.notFound().build();  
        }
    }
	
	@PostMapping("/address/register")
    public ResponseEntity<Object> register(@RequestBody Address address) {
        try {
            Address savedAddress = service.registerAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);  
        } catch (IllegalStateException e) {
        	
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String("Error: " + e.getMessage()));
        } catch (Exception e) {
        	
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new String("Unexpected error occurred " + e.getMessage()));
        }
    }
	
	@DeleteMapping("/address/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id){
		try {
        	service.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Borrado con éxito");  
        } catch (IllegalStateException e) {
        	
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String("Error: " + e.getMessage()));
        } catch (Exception e) {
        	
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new String("Unexpected error occurred " + e.getMessage()));
        }
	}

	
	@PutMapping("/address/update/{id}")
	public ResponseEntity<Object> update( @PathVariable("id") Long id, @RequestBody AddressRequest request) {
      try {
    	  Address savedAddress = service.update( id,request );
          return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
      } catch (IllegalStateException e) {
      	
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String("Error: " + e.getMessage()));
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
      }
	}

}
