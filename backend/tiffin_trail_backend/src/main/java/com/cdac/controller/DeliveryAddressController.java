package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.DeliveryAddress;
import com.cdac.entity.User;
import com.cdac.service.DeliveryAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/addresses")
@CrossOrigin
public class DeliveryAddressController {
	@Autowired
	private DeliveryAddressService addressService;
	
	@Autowired
 //  private UserService userService;

	public DeliveryAddressController(DeliveryAddressService addressService) {
		super();
		this.addressService = addressService;
	}
	
	// Create 
	@PostMapping("/createAddress/{userId}")
	public ResponseEntity<DeliveryAddress> createAddress(@Valid  @PathVariable Long userId, @RequestBody DeliveryAddress address ){		
		 User user = userService.getUserById(userId);
	        if (user == null) {
	            return ResponseEntity.notFound().build();
	        }
	        address.setUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(address));
		
	}
	
	// Get All addresses
	@GetMapping("/all")
	public ResponseEntity<List<DeliveryAddress>> getAllAddresses(){
		return ResponseEntity.ok(addressService.getAll());
	}
	
	
	 // Get all address by user 
	 @GetMapping("/user/{userId}")
	    public List<DeliveryAddress> getByUserId(@PathVariable Long userId) {
	        return addressService.getByUserId(userId);
	    }

	  // delete by address Id
	 @DeleteMapping("/{addressId}") 
	 public ResponseEntity<Void> delete (@PathVariable Long addressId){
		 boolean deleted = addressService.delete(addressId);
		 if(deleted) {
			 return ResponseEntity.noContent().build();
		 }
		 return ResponseEntity.notFound().build();
	 }
	 
	 //update by addressId
	 public ResponseEntity<?> updateByAddressId(@PathVariable Long addressId, @Valid @RequestBody DeliveryAddress updated){
		 DeliveryAddress address = addressService.update(addressId, updated);
		 return address != null?
				 ResponseEntity.ok(address) :
					 ResponseEntity.status(HttpStatus.NOT_FOUND).body("DeliveryAddress not found by this addressId");
	 }
}
