package vn.iotstar.services.implement;

import vn.iotstar.dao.IAddressDao;
import vn.iotstar.dao.implement.AddressDao;
import vn.iotstar.entity.Address;
import vn.iotstar.services.IAddressService;

public class AddressService implements IAddressService{
	
	IAddressDao addressDao = new AddressDao();

	@Override
	public Address findByAddressId(int id) {
		return addressDao.findByAddressId(id);
	}

	@Override
	public void update(Address address) {
		addressDao.update(address);
	}

	@Override
	public Address insert(Address address) {
		return addressDao.insert(address);
	}
	

}
