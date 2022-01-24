package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
