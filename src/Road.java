import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamie Wong
 * 
 * The Road class models a link between two City objects.
 * 
 */
public class Road {
	
	Set<City> connectedCities;
	
	/**
	 * This constructs a Road with a specified City on either side,
	 * @param cityOne A City at one end of the Road.
	 * @param cityTwo The other City that is at the end of the Road.
	 */
	
	public Road(City cityOne, City cityTwo) {
		connectedCities = new HashSet<City>();
		connectedCities.add(cityOne);
		connectedCities.add(cityTwo);
	}
	
	/**
	 * 
	 * Utility methods: overrode equals and hashCode methods to identify duplicate objects
	 * in set data structures.
	 * 
	 */
	
	@Override
	  public int hashCode() {
	    int result = 17;
	    result = 31 * result + connectedCities.hashCode();
	    return result;
	  }
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		else if(o instanceof Road) {
			if(((Road)o).connectedCities.containsAll(connectedCities)==false) {
				return false;
			}
			if(((Road)o).connectedCities.size() != connectedCities.size()) {
				return false;
			}
			return true;
		}
		return false;
	}
}
