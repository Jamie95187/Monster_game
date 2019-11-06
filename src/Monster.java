import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Jamie Wong
 * 
 * The Monster class models a Monster with a unique ID and its current
 * location in this World.
 *
 */
public class Monster {
	private int ID;
	City location;
	static ArrayList<String> directions = new ArrayList<>(
			Arrays.asList("north", "west", "south", "east"));
	
	/**
	 * This constructs a Monster object which has a unique ID and the
	 * current location of this Monster.
	 * @param id The unique ID of this monster.
	 * @param currentCity The current City this Monster is located at.
	 */
	public Monster(int id, City currentCity) {
		this.ID = id;
		this.location = currentCity;
	}
	
	public void move() {
		City destination = location;
		// Randomise the direction it will move
		Collections.shuffle(directions);
		for(int i=0; i<4; i++) {
			if(directions.get(i)=="north") {
				if(location.northCity != null) {
					destination = location.northCity;
					break;
				}
				else if(directions.get(i)=="east") {
					if(location.eastCity != null) {
						destination = location.eastCity;
						break;
					}
				}
				else if(directions.get(i)=="south") {
					if(location.southCity!=null) {
						destination = location.southCity;
						break;
					}
				}
				else if(directions.get(i)=="west") {
					if(location.westCity!=null) {
						destination = location.westCity;
						break;
					}
				}
			}
		}
		location = destination;
	}

	/**
	 * 
	 * Utility methods: Getter methods that return the unique ID of this Monster and
	 * its current location.
	 * 
	 */
	
	public int getId() {
		return ID;
	}
	
	public City getCurrentCity(){
		return location;
	}
	
}
