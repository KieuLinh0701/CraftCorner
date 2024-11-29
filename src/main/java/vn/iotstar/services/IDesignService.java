package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Designs;

public interface IDesignService {

	List<Designs> findAll();

	Designs findById(int id);

	void insert(Designs design);

	void update(Designs design);

	void delete(int id);
}
