package com.empl.mgr.dao;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeAddress;

@Repository
public class AddressDao extends AbstractDao<TeAddress> {

	@Override
	public Class<TeAddress> getEntityClass() {
		// TODO Auto-generated method stub
		return TeAddress.class;
	}

}
