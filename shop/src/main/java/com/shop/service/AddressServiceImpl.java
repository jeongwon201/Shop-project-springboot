package com.shop.service;

import org.springframework.stereotype.Service;

import com.shop.domain.Address;
import com.shop.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
	
	private final AddressRepository repository;

	@Override
	public long countAll() throws Exception {
		return repository.count();
	}

	@Override
	public void save(Address address) throws Exception {
		repository.save(address);
	}

	
	
}
