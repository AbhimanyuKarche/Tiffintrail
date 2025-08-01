package com.cdac.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_profiles")
@Getter
@Setter
public class CustomerProfile {
	    @Id
	    private Long id;  // This will be same as the User ID
	    @OneToOne
	    @MapsId
	    @JoinColumn(name = "user_id")
	    private User user;

	    private String address;

	    private String city;

	    private String pincode;

	    private String phone;

	    // Constructors
	    public CustomerProfile() {}

	    public CustomerProfile(User user, String address, String city, String pincode, String phone) {
	        this.user = user;
	        this.address = address;
	        this.city = city;
	        this.pincode = pincode;
	        this.phone = phone;
	    }

	    // (Use Lombok @Data if you prefer)
	
}
