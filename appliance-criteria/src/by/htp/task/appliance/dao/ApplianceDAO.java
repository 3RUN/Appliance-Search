package by.htp.task.appliance.dao;

import by.htp.task.appliance.entity.Appliance;
import by.htp.task.appliance.entity.criteria.Criteria;

public interface ApplianceDAO {

	Appliance find(Criteria criteria) throws DAOException;

}
