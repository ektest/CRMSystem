package com.emrekoca.services.diary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emrekoca.dataaccess.ActionDao;
import com.emrekoca.domain.Action;

@Transactional
@Service
public class DiaryManagementServiceProductionImpl implements DiaryManagementService {

	@Autowired
	private ActionDao dao;

	public DiaryManagementServiceProductionImpl(ActionDao dao) {
		this.dao = dao;
	}

	@Override
	public void recordAction(Action action) {
		dao.create(action);
	}

	@Override
	public List<Action> getAllIncompleteActions(String requiredUser) {
		return dao.getIncompleteActions(requiredUser);
	}

}
