package com.shop.service;

import com.shop.domain.Address;

public interface AddressService {
	
	public long countAll() throws Exception;
	
	public void save(Address address) throws Exception;
	
	
}
