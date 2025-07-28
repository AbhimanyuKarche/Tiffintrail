package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.DeliveryAddressDao;
import com.cdac.entity.DeliveryAddress;

@Service
public class DeliveryAddressServImpl implements DeliveryAddressService{

	@Autowired
	private DeliveryAddressDao deliveryAddressdao ;
	
	public DeliveryAddressServImpl(DeliveryAddressDao deliveryAddressdao) {
		super();
		this.deliveryAddressdao = deliveryAddressdao;
	}

	@Override
	public DeliveryAddress create(DeliveryAddress address) {		
		return deliveryAddressdao.save(address) ;
	}

	@Override
	public List<DeliveryAddress> getAll() {
		return deliveryAddressdao.findAll();
	}

//	@Override
//	public DeliveryAddress getById(Long addressId) {		
//		return deliveryAddressdao.findById(addressId).orElse(null);
//	}
	
	@Override
	public List<DeliveryAddress> getByUserId(Long userId) {
		
		return deliveryAddressdao.findByUserUserId(userId);
	}
	
	
	@Override
	public DeliveryAddress update(Long addressId, DeliveryAddress updated) {
		Optional<DeliveryAddress> existing = deliveryAddressdao.findById(addressId);
		if (existing.isPresent()) {
			DeliveryAddress addr = existing.get();
			addr.setLine1(updated.getLine1());
			addr.setLine2(updated.getLine2());
			addr.setCity(updated.getCity());
			addr.setPincode(updated.getPincode());
			addr.setState(updated.getState());
			addr.setUser(updated.getUser());
			return deliveryAddressdao.save(addr);
		}
		return null;
	}

	@Override
	 public boolean delete(Long addressId) {
		if (deliveryAddressdao.existsById(addressId)) {
			deliveryAddressdao.deleteById(addressId);
			return true;
		}
		return false;
	}

	
	
	

}
