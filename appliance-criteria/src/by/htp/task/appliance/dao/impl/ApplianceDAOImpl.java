package by.htp.task.appliance.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.task.appliance.dao.ApplianceDAO;
import by.htp.task.appliance.dao.DAOException;
import by.htp.task.appliance.entity.Appliance;
import by.htp.task.appliance.entity.criteria.Criteria;

public class ApplianceDAOImpl implements ApplianceDAO {

	private static final String fileName = "./resources/appliances_db.txt";

	public List<Appliance> take(Criteria criteria) throws DAOException {

		int i = 0;
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		String[] tempLine = null;
		String groupName = criteria.getGroupSearchName();
		List<Appliance> appliance = new ArrayList<Appliance>();

		try {

			String line = null;
			reader = new FileReader(fileName);
			bufferedReader = new BufferedReader(reader);

			while ((line = bufferedReader.readLine()) != null) {
				tempLine = line.split("\\s+");

				// take the name from the line
				String name = tempLine[0];

				// it matches the group we are searching for?
				if (name.equals(groupName)) {

					// create map
					Map<String, Object> params = new HashMap<String, Object>();

					// and add parameters from this line into them
					for (i = 1; i < tempLine.length; i++) {

						// ignore : in the line
						if (!tempLine[i].equals(":")) {

							// add parameters and values into the list and map
							String tempParam = tempLine[i].split("=")[0];
							Object tempValue = tempLine[i].split("=")[1].replace(",", "").replace(";", "");
							params.put(tempParam, tempValue);

						}

					}

					// add this appliance into our list
					Appliance tempAppliance = new Appliance(tempLine[0], params);
					appliance.add(tempAppliance);

				}
			}

		} catch (IOException e) {
			throw new DAOException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new DAOException(e);
				}
			}
		}

		return appliance;
	}

	@Override
	public Appliance find(Criteria criteria) throws DAOException {

		Appliance result = null;
		List<Appliance> applianceList = new ArrayList<Appliance>();

		try {
			applianceList = take(criteria);
		} catch (DAOException e) {
			throw new DAOException(e);
		}

		int i = 0;
		boolean matched = false;

		// cycle through appliances
		for (i = 0; i < applianceList.size(); i++) {

			// search within current appliance
			matched = findInAppliance(applianceList.get(i), criteria);

			// found one? no need to search more
			if (matched) {
				result = applianceList.get(i);
				break;
			}

		}

		return result;
	}

	public boolean findInAppliance(Appliance appliance, Criteria criteria) {

		int i = 0;
		int j = 0;
		int matchedCounter = 0;
		int criteriaCounter = 0;

		// criteria HASHMAP keys and values
		List<String> tempCKey = new ArrayList<String>(criteria.getCriteria().keySet());
		List<Object> tempCValue = new ArrayList<Object>(criteria.getCriteria().values());

		// amount of all passed criteria
		criteriaCounter = tempCKey.size();

		boolean matched = false;

		// appliance HASHMAP keys and values
		List<String> tempAKey = new ArrayList<String>(appliance.getParams().keySet());
		List<Object> tempAValue = new ArrayList<Object>(appliance.getParams().values());

		// cycle though appliance's parameters
		for (i = 0; i < tempAKey.size(); i++) {

			// cycle though given criteria
			// seeking for a match
			for (j = 0; j < tempCKey.size(); j++) {

				// found same key?
				if (tempAKey.get(i).equals(tempCKey.get(j))) {

					// check if criteria's value is a type of string
					// we check only criteria, because keys matched and
					// values should be of the same type
					if (isString(tempCValue.get(j))) {

						// get string values from the current appliance and criteria
						String applianceStr = (String) tempAValue.get(i);
						String criteriaStr = (String) tempCValue.get(j);

						// with the same values?
						if (criteriaStr.equals(applianceStr)) {

							// plus one criteria match was found !
							matchedCounter++;

						}

					} else {

						// get double value out of object
						// we check if it's string, integer or double
						// and then convert it from obj to double
						double applianceValue = objToDouble(tempAValue.get(i));
						double criteriaValue = objToDouble(tempCValue.get(j));

						// with the same values?
						if (criteriaValue == applianceValue) {

							// plus one criteria match was found !
							matchedCounter++;

						}

					}

					// matched enough criteria?
					// then this is the appliance we are looking for
					if (matchedCounter >= criteriaCounter) {
						matched = true;
					}

				}

			}

		}

		// just a helper
		// to show how many matches we've got
		// System.out.println(matchedCounter + " out of " + criteriaCounter + "
		// matches!");
		return matched;
	}

	public static boolean isDouble(Object obj) {
		return obj instanceof Double;
	}

	public static boolean isInteger(Object obj) {
		return obj instanceof Integer;
	}

	public static boolean isString(Object obj) {
		return obj instanceof String;
	}

	public static double objToDouble(Object obj) {

		if (isString(obj)) {

			return Double.valueOf((String) obj);

		} else if (isInteger(obj)) {

			return Double.valueOf((Integer) obj);

		} else if (isDouble(obj)) {

			return (Double) obj;

		} else {
			return 0;
		}

	}

}
