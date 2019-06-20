package by.htp.task.appliance.service;

import by.htp.task.appliance.entity.Appliance;
import by.htp.task.appliance.entity.criteria.Criteria;

public interface ApplianceService {

	Appliance find(Criteria criteria) throws LogicException;

}
