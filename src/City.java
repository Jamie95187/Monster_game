

/**
 * @author Jamie Wong
 * 
 * The City class models a City where Monsters can battle.
 * If there are Monsters fighting the City will be destroyed.
 *  
 */

public class City {
	private String cityName;
	City northCity;
	City eastCity;
	City southCity;
	City westCity;
	
	/**
	 * This constructs a City with a specified String as the name of it.
	 * @param cityName The name of the city.
	 */
	
	public City(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 
	 * Utility methods: Getter and setter methods. Overrode the toString() method to
	 * print out the remaining City and Road objects that are left.
	 * 
	 */
	
	
	public void setNorthCity(City city) {
		northCity = city;
	}
	
	public void setEastCity(City city) {
		eastCity = city;
	}
	
	public void setSouthCity(City city) {
		southCity = city;
	}
	
	public void setWestCity(City city) {
		westCity = city;
	}
		
	public String getName() {
		return cityName;
	}
	
	@Override
	public String toString() {
		String cityInformation = cityName;
		if(northCity!=null) {
			cityInformation+= " north=" + northCity.getName();
		}
		if(eastCity!=null) {
			cityInformation+= " east=" + eastCity.getName();
		}
		if(southCity!=null) {
			cityInformation+= " south=" + southCity.getName();
		}
		if(westCity!=null) {
			cityInformation+=" west=" + westCity.getName();
		}
		return cityInformation;
	}
		
}
