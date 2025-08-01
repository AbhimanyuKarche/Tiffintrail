package com.cdac.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seller_profiles")
public class SellerProfile {
	  @Id
	    private Long id;  // This will be same as the User ID


	    @OneToOne
	    @MapsId
	    @JoinColumn(name = "user_id")
	    private User user;

	    private String businessName;

	    private String description;

	    private String address;

	    private String city;

	    private String pincode;

	    private String phone;

	    // Constructors
	    public SellerProfile() {}

	    public SellerProfile(User user, String businessName, String description, String address, String city, String pincode, String phone) {
	        this.user = user;
	        this.businessName = businessName;
	        this.description = description;
	        this.address = address;
	        this.city = city;
	        this.pincode = pincode;
	        this.phone = phone;
	    }

	    // Getters and Setters
	    // (Or use Lombok @Data)
}
