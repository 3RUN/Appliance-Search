package by.htp.task.appliance.service.impl;

import by.htp.task.appliance.dao.ApplianceDAO;
import by.htp.task.appliance.dao.DAOException;
import by.htp.task.appliance.dao.DAOFactory;
import by.htp.task.appliance.entity.Appliance;
import by.htp.task.appliance.entity.criteria.Criteria;
import by.htp.task.appliance.service.ApplianceService;
import by.htp.task.appliance.service.LogicException;
import by.htp.task.appliance.service.validation.Validator;

public class ApplianceServiceImpl implements ApplianceService {

	@Override
	public Appliance find(Criteria criteria) throws LogicException {
		if (!Validator.isValid(criteria)) {
			System.err.println("Invalid criteria!");
			return null;
		}

		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();

		Appliance appliance = null;
		try {
			appliance = applianceDAO.find(criteria);
		} catch (DAOException e) {
			throw new LogicException(e);
		}

		return appliance;
	}

}
