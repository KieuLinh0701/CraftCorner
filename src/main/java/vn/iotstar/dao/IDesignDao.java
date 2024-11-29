package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Designs;

public interface IDesignDao {
	
	List<Designs> findAll();
}
