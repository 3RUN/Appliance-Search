package by.htp.task.appliance.entity;

import java.util.HashMap;
import java.util.Map;

public class Appliance {

	private String name;
	private Map<String, Object> params = new HashMap<String, Object>();

	public Appliance() {
	}

	public Appliance(String name, Map<String, Object> params) {
		this.name = name;
		this.params = params;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getName() {
		return this.name;
	}

	public Map<String, Object> getParams() {
		return this.params;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appliance other = (Appliance) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appliance [name=" + this.getName() + ", params=" + this.getParams() + "]";
	}

}
