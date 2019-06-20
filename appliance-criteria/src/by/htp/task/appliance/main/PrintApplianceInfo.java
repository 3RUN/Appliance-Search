package by.htp.task.appliance.main;

import java.util.ArrayList;
import java.util.List;

import by.htp.task.appliance.entity.Appliance;

public class PrintApplianceInfo {

	public static void print(Appliance appliance) {

		if (appliance == null) {
			System.out.println("Appliance within the given criteria wasn't found!");
		} else {
			System.out.println("Appliance was found!");
			System.out.println(appliance.getName() + " with following parameters: ");

			int i = 0;
			List<String> applianceKey = new ArrayList<String>(appliance.getParams().keySet());
			List<Object> applianceValue = new ArrayList<Object>(appliance.getParams().values());

			for (i = 0; i < applianceKey.size(); i++) {

				System.out.print(applianceKey.get(i) + "=" + applianceValue.get(i) + " ");

			}
			System.out.println("");
		}
		System.out.println("");

	}

}
