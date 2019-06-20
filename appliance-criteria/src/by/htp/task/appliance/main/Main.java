package by.htp.task.appliance.main;

import static by.htp.task.appliance.entity.criteria.SearchCriteria.*;

import by.htp.task.appliance.entity.Appliance;
import by.htp.task.appliance.entity.criteria.Criteria;
import by.htp.task.appliance.service.ApplianceService;
import by.htp.task.appliance.service.LogicException;
import by.htp.task.appliance.service.ServiceFactory;

public class Main {

	public static void main(String[] args) {

		Appliance appliance = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		ApplianceService service = factory.getApplianceService();

		//////////////////////////////////////////////////////////////////

		Criteria criteriaOven = new Criteria(Oven.class.getSimpleName()); // "Oven"
		criteriaOven.add(Oven.CAPACITY.toString(), 32);

		try {
			appliance = service.find(criteriaOven);
		} catch (LogicException e) {
			System.err.println(e);
		}

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		criteriaOven = new Criteria(Oven.class.getSimpleName());
		criteriaOven.add(Oven.HEIGHT.toString(), 45.5);
		criteriaOven.add(Oven.DEPTH.toString(), 60);

		try {
			appliance = service.find(criteriaOven);
		} catch (LogicException e) {
			System.err.println(e);
		}

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria criteriaTabletPC = new Criteria(TabletPC.class.getSimpleName());
		criteriaTabletPC.add(TabletPC.COLOR.toString(), "blue");
		criteriaTabletPC.add(TabletPC.DISPLAY_INCHES.toString(), 14);
		criteriaTabletPC.add(TabletPC.MEMORY_ROM.toString(), 8000);

		try {
			appliance = service.find(criteriaTabletPC);
		} catch (LogicException e) {
			System.err.println(e);
		}

		PrintApplianceInfo.print(appliance);

	}

}
