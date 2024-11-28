package vn.iotstar.services;


import vn.iotstar.entity.Address;

public interface IAddressService {

	Address findByAddressId(int id);
	
	void update(Address address);
	
	Address insert(Address address);
	
}
