package com.empl.mgr.dao;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeChooseEducation;

@Repository
public class ChooseEducationDao extends AbstractDao<TeChooseEducation> {

	@Override
	public Class<TeChooseEducation> getEntityClass() {
		// TODO Auto-generated method stub
		return TeChooseEducation.class;
	}

}
