import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Jamie Wong
 *
 * The World class models a graph that holds the City and Road objects that exist in the World.
 * The World updates after each iteration of the simulation by removing the Destroyed City and Road objects.
 *
 */
public class World {
	
	/* A map that maps a key that is the City objects name to
	 * the City object.
	 */
	private Map<String, City> cities;
	/* A Set of all the Road objects.
	 */
	private Set<Road> roads;
	
	/**
	 * This constructs a World by reading the input from a specified file in a particular format.
	 * @param file The path directory of the specified file.
	 */
	
	public World(String file) {
		cities = new HashMap<String, City>();
		roads = new HashSet<Road>();
		readFileToConstructCities(file);
		readFileToConstructRoads(file);
	}
	
	/**
	 * This method updates the World by removing the City and Road object that have been destroyed.
	 * @param city The City object that needs to be removed.
	 */
	public void updateMap(City city) {
		cities.remove(city.getName());
		ArrayList<Road> destroyedRoads = new ArrayList<>();
		for(Road road : roads) {
			if(road.connectedCities.contains(city)) {
				destroyedRoads .add(road);
			}
		}
		for(int i=0; i < destroyedRoads.size(); i++) {
			roads.remove(destroyedRoads.get(i));
		}
	}
	
	/**
	 * This method prints the remaining City objects that have not been destroyed by the monsters.
	 */
	public void printMap() {
		for(Map.Entry<String, City> entry: cities.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
	}
	
	/**
	 * This method populates the World object with the City Objects from a specified file.
	 * @param file The path directory of the specified file.
	 */
	private void readFileToConstructCities(String file) {
		
		// create a Buffered Reader object instance with a FileReader
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(file));
					// Read the first line of the file
					String line = reader.readLine();
					while(line != null) {
						// Split the string to find the city names
						String[] splitted = line.split("\\s+");
						// Populate the world with cities
						City c = new City(splitted[0]);
						cities.put(splitted[0], c);
						line = reader.readLine();
					}
					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();}
				
	}
	
	/**
	 * This method populates the World object with the Road Objects from a specified file.
	 * @param file The path directory of the specified file.
	 */
	private void readFileToConstructRoads(String file) {
		// create a Buffered Reader object instance with a FileReader
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			// Read the first line of the file
			String line = reader.readLine();
			while(line!=null) {
				// Split the string to find the roads
				String[] splitted = line.split("\\s+");
				String city = splitted[0];
				for(int i=1; i<splitted.length; i++) {
					char firstLetterOfDirection = splitted[i].charAt(0);
					String neighbouringCity = splitted[i].substring(splitted[i].indexOf('=')+1);
					roads.add(new Road(cities.get(city), cities.get(neighbouringCity)));
					switch (firstLetterOfDirection) {
					case 'n':
						cities.get(city).setNorthCity(cities.get(neighbouringCity));
						break;
					case 'e':
						cities.get(city).setEastCity(cities.get(neighbouringCity));
						break;
					case's':
						cities.get(city).setSouthCity(cities.get(neighbouringCity));
						break;
					case'w':
						cities.get(city).setWestCity(cities.get(neighbouringCity));
						break;
					}
				}
				line = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();}
	}
	
	/**
	 * 
	 * Utility methods: Getter methods that return the cities and roads.
	 * 
	 */
	
	public Map<String, City> getCities(){
		return cities;
	}
	
	public HashSet<Road> getRoads(){
		return (HashSet<Road>) roads;
	}
}
