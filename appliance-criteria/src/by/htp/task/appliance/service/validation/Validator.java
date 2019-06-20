package by.htp.task.appliance.service.validation;

import static by.htp.task.appliance.entity.criteria.SearchCriteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.htp.task.appliance.entity.criteria.Criteria;

public class Validator {

	public static boolean isDouble(Object obj) {
		return obj instanceof Double;
	}

	public static boolean isInteger(Object obj) {
		return obj instanceof Integer;
	}

	public static boolean isString(Object obj) {
		return obj instanceof String;
	}

	public static boolean isNumber(Object obj) {

		if (isString(obj)) {
			return false;
		} else {
			return true;
		}

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

	public static boolean isSizeCorrect(String group, String key, double d) {

		// Oven
		if (group.equals(Oven.class.getSimpleName())) {

			if (key.equals(Oven.DEPTH.toString()) && d > 100) {
				return false;
			}

			if (key.equals(Oven.HEIGHT.toString()) && d > 100) {
				return false;
			}

			if (key.equals(Oven.WIDTH.toString()) && d > 100) {
				return false;
			}

		}

		// Refrigerator ?
		if (group.equals(Refrigerator.class.getSimpleName())) {

			if (key.equals(Refrigerator.HEIGHT.toString()) && d > 400) {
				return false;
			}

			if (key.equals(Refrigerator.WIDTH.toString()) && d > 100) {
				return false;
			}

		}

		return true;
	}

	public static boolean isValidValue(String group, String key, Object value) {

		String str = null;
		double d = 0;

		if (isNumber(value)) {
			d = objToDouble(value);
			if (d <= 0) {
				return false;
			}
		} else {
			str = (String) value;
		}

		// Oven
		if (group.equals(Oven.class.getSimpleName())) {

			// oven can't have NaN values
			if (!isNumber(value)) {
				return false;
			} else {

				if (key.equals(Oven.POWER_CONSUMPTION.toString()) && d <= 3000) {
					return true;
				} else if (key.equals(Oven.WEIGHT.toString()) && d <= 100) {
					return true;
				} else if (key.equals(Oven.CAPACITY.toString()) && d <= 1000) {
					return true;
				} else if (isSizeCorrect(group, key, d)) {
					return true;
				} else {
					return false;
				}

			}

		}

		// Laptop
		if (group.equals(Laptop.class.getSimpleName())) {

			// laptop can have string only in it's OS parameter
			if (!isNumber(value)) {

				if (key.equals(Laptop.OS.toString()) && str.equals("Windows")) {
					return true;
				} else if (key.equals(Laptop.OS.toString()) && str.equals("Linux")) {
					return true;
				} else {
					return false;
				}

			} else {

				if (key.equals(Laptop.BATTERY_CAPACITY.toString()) && d <= 3000) {
					return true;
				} else if (key.equals(Laptop.MEMORY_ROM.toString()) && d <= 10000) {
					return true;
				} else if (key.equals(Laptop.SYSTEM_MEMORY.toString()) && d <= 5000) {
					return true;
				} else if (key.equals(Laptop.CPU.toString()) && d <= 10) {
					return true;
				} else if (key.equals(Laptop.DISPLAY_INCHS.toString()) && d <= 30) {
					return true;
				} else {
					return false;
				}

			}

		}

		// Refrigerator ?
		if (group.equals(Refrigerator.class.getSimpleName())) {

			if (key.equals(Refrigerator.POWER_CONSUMPTION.toString()) && d <= 1000) {
				return true;
			} else if (key.equals(Refrigerator.WEIGHT.toString()) && d <= 500) {
				return true;
			} else if (key.equals(Refrigerator.FREEZER_CAPACITY.toString()) && d <= 500) {
				return true;
			} else if (key.equals(Refrigerator.OVERALL_CAPACITY.toString()) && d <= 2000) {
				return true;
			} else if (isSizeCorrect(group, key, d)) {
				return true;
			} else {
				return false;
			}

		}

		// VacuumCleaner
		if (group.equals(VacuumCleaner.class.getSimpleName())) {

			// VacuumCleaner can have string only in it's FILTER_TYPE, BAG_TYPE or WAND_TYPE
			// parameters
			if (!isNumber(value)) {

				if (key.equals(VacuumCleaner.FILTER_TYPE.toString()) && str.equals("A")) {
					return true;
				} else if (key.equals(VacuumCleaner.FILTER_TYPE.toString()) && str.equals("B")) {
					return true;
				} else if (key.equals(VacuumCleaner.FILTER_TYPE.toString()) && str.equals("C")) {
					return true;
				} else if (key.equals(VacuumCleaner.BAG_TYPE.toString()) && str.equals("A2")) {
					return true;
				} else if (key.equals(VacuumCleaner.BAG_TYPE.toString()) && str.equals("AA-89")) {
					return true;
				} else if (key.equals(VacuumCleaner.BAG_TYPE.toString()) && str.equals("XX00")) {
					return true;
				} else if (key.equals(VacuumCleaner.WAND_TYPE.toString()) && str.equals("all-in-one")) {
					return true;
				} else {
					return false;
				}

			} else {

				if (key.equals(VacuumCleaner.POWER_CONSUMPTION.toString()) && d <= 500) {
					return true;
				} else if (key.equals(VacuumCleaner.MOTOR_SPEED_REGULATION.toString()) && d <= 10000) {
					return true;
				} else if (key.equals(VacuumCleaner.CLEANING_WIDTH.toString()) && d <= 500) {
					return true;
				} else {
					return false;
				}

			}

		}

		// TabletPC ?
		if (group.equals(TabletPC.class.getSimpleName())) {

			// string ?
			if (!isNumber(value)) {

				// only 3 options available for COLOR
				if (key.equals(TabletPC.COLOR.toString()) && str.equals("blue")) {
					return true;
				} else if (key.equals(TabletPC.COLOR.toString()) && str.equals("red")) {
					return true;
				} else if (key.equals(TabletPC.COLOR.toString()) && str.equals("green")) {
					return true;
				} else {
					return false;
				}

			} else {

				if (key.equals(TabletPC.BATTERY_CAPACITY.toString()) && d <= 10) {
					return true;
				} else if (key.equals(TabletPC.DISPLAY_INCHES.toString()) && d <= 20) {
					return true;
				} else if (key.equals(TabletPC.MEMORY_ROM.toString()) && d <= 32000) {
					return true;
				} else if (key.equals(TabletPC.FLASH_MEMORY_CAPACITY.toString()) && d <= 10) {
					return true;
				} else {
					return false;
				}

			}

		}

		// Speakers ?
		if (group.equals(Speakers.class.getSimpleName())) {

			// Speakers can have string only in it's POWER_CONSUMPTION parameter
			if (!isNumber(value)) {

				if (key.equals(Speakers.POWER_CONSUMPTION.toString()) && str.equals("2-4")) {
					return true;
				} else if (key.equals(Speakers.POWER_CONSUMPTION.toString()) && str.equals("3-4")) {
					return true;
				} else if (key.equals(Speakers.POWER_CONSUMPTION.toString()) && str.equals("2-3.5")) {
					return true;
				} else {
					return false;
				}

			} else {

				if (key.equals(Speakers.POWER_CONSUMPTION.toString()) && d <= 100) {
					return true;
				} else if (key.equals(Speakers.NUMBER_OF_SPEAKERS.toString()) && d <= 10) {
					return true;
				} else if (key.equals(Speakers.CORD_LENGTH.toString()) && d <= 20) {
					return true;
				} else {
					return false;
				}

			}

		}

		return true;

	}

	public static boolean isCriteriaCorrect(Criteria criteria) {

		// get group we are searching for
		String groupName = criteria.getGroupSearchName();

		// criteria HASHMAP and it's keys
		Map<String, Object> tempCriteria = criteria.getCriteria();
		List<String> keyList = new ArrayList<String>(criteria.getCriteria().keySet());

		int i = 0;
		int matchCounter = 0;
		int criteriaCounter = 0;
		criteriaCounter = keyList.size();

		for (i = 0; i < keyList.size(); i++) {

			// Oven ?
			if (groupName.equals(Oven.class.getSimpleName())) {

				for (Oven search : Oven.values()) {

					if (keyList.get(i).equals(search.toString())) {

						Object obj = tempCriteria.get(keyList.get(i));
						if (isValidValue(groupName, keyList.get(i), obj)) {
							matchCounter++;
						}

					}

				}

			}

			// Laptop ?
			if (groupName.equals(Laptop.class.getSimpleName())) {

				for (Laptop search : Laptop.values()) {

					if (keyList.get(i).equals(search.toString())) {

						Object obj = tempCriteria.get(keyList.get(i));
						if (isValidValue(groupName, keyList.get(i), obj)) {
							matchCounter++;
						}

					}

				}

			}

			// Refrigerator ?
			if (groupName.equals(Refrigerator.class.getSimpleName())) {

				for (Refrigerator search : Refrigerator.values()) {

					if (keyList.get(i).equals(search.toString())) {

						Object obj = tempCriteria.get(keyList.get(i));
						if (isValidValue(groupName, keyList.get(i), obj)) {
							matchCounter++;
						}

					}

				}

			}

			// VacuumCleaner ?
			if (groupName.equals(VacuumCleaner.class.getSimpleName())) {

				for (VacuumCleaner search : VacuumCleaner.values()) {

					if (keyList.get(i).equals(search.toString())) {

						Object obj = tempCriteria.get(keyList.get(i));
						if (isValidValue(groupName, keyList.get(i), obj)) {
							matchCounter++;
						}

					}

				}

			}

			// TabletPC ?
			if (groupName.equals(TabletPC.class.getSimpleName())) {

				for (TabletPC search : TabletPC.values()) {

					if (keyList.get(i).equals(search.toString())) {

						Object obj = tempCriteria.get(keyList.get(i));
						if (isValidValue(groupName, keyList.get(i), obj)) {
							matchCounter++;
						}

					}

				}

			}

			// Speakers ?
			if (groupName.equals(Speakers.class.getSimpleName())) {

				for (Speakers search : Speakers.values()) {

					if (keyList.get(i).equals(search.toString())) {

						Object obj = tempCriteria.get(keyList.get(i));
						if (isValidValue(groupName, keyList.get(i), obj)) {
							matchCounter++;
						}

					}

				}

			}

		}

		if (matchCounter < criteriaCounter) {
			return false;
		}

		return true;
	}

	public static boolean isValid(Criteria criteria) {
		if (criteria == null) {
			return false;
		}

		if (!isCriteriaCorrect(criteria)) {
			return false;
		}

		return true;
	}

}