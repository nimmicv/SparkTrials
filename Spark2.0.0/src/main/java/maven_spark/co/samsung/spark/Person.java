package maven_spark.co.samsung.spark;

import java.io.Serializable;

public class Person implements Serializable {
	
	private int id;
	private String name;
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public Person() {
		super();
	}

	public Person(int id, String city, String name) {
		this.id = id;
		this.city = city;
		this.name = name;
	}


}
