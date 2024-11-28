package vn.iotstar.dao;


import vn.iotstar.entity.Address;

public interface IAddressDao {
	
	Address findByAddressId(int id);
	
	void update(Address address);
	
	Address insert(Address address);

}
