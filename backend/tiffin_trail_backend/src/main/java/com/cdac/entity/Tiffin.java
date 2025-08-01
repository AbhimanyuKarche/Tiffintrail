package com.cdac.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tiffins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tiffin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    private Double price;

    private boolean isVeg;

    private String imageUrl; // optional, for frontend to display image

    private boolean available = true; // mark as sold out or unavailable

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private SellerProfile seller;

    // Optional: quantity or type field can be added as needed
}
