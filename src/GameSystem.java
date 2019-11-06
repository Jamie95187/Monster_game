import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Jamie Wong
 *
 * The GameSystem incorporates all the aspects of a World where Monster exists and
 * creates scenarios where fights can occur and the destruction of cities and roads. 
 *
 */
public class GameSystem {
	World world;
	Map<Integer, Monster> monsters;
	Map<City, ArrayList<Monster>> monsterPos;
	
	/**
	 * This constructs the GameSystem object where the user inputs the number of Monster
	 * objects to be deployed.
	 * @param file The file used to construct the World object.
	 * @param numberOfMonsters The user chooses the number of Monster objects to be deployed into
	 * the World.
	 */
	public GameSystem(String file, int numberOfMonsters) {
		world = new World(file);
		monsters = new HashMap<Integer, Monster>();
		monsterPos = new HashMap<City, ArrayList<Monster>>();
		populateWorldWithMonsters(numberOfMonsters);
	}
	
	/**
	 * This method updates the World after each iteration.
	 */
	public void updateMaster() {
		updateMonsterLocations();
		checkForDestroyedCities();
	}
	
	/**
	 * This method populates the World object by placing the Monsters at a
	 * random City within this World.
	 * @param numberOfMonsters The user inputs the number of Monsters to be deployed.
	 */
	private void populateWorldWithMonsters(int numberOfMonsters) {
		for(String city : world.getCities().keySet()) {
			ArrayList<Monster> monsterList = new ArrayList<Monster>();
			monsterPos.put(world.getCities().get(city), monsterList);
		}
		for(int i=0; i<numberOfMonsters; i++) {
			City randomCity = getRandomCity();
			Monster m = new Monster(i, randomCity);
			monsters.put(i, m);
			monsterPos.get(randomCity).add(monsters.get(i));
		}
	}
	
	/**
	 * This method checks which Cities have been destroyed by the fighting Monsters, and
	 * updates the City and Road data structures in the World object.
	 */
	public void checkForDestroyedCities() {
		ArrayList<City> destroyedCities = new ArrayList<>();
		for(Map.Entry<City, ArrayList<Monster>> entry : monsterPos.entrySet()) {
			if(entry.getValue().size() > 1) {
				System.out.print(entry.getKey().getName() + " has been destroyed by monsters : ");
				for(int i=0; i < entry.getValue().size(); i++) {
					System.out.print(entry.getValue().get(i).getId());
					monsters.remove(entry.getValue().get(i).getId());
					if(i != entry.getValue().size() - 1) {
						System.out.print( " & ");
					}
					else {
						System.out.print("\n");
					}
				}
				world.updateMap(entry.getKey());
				destroyedCities.add(entry.getKey());
			}
		}
		for(int i=0; i<destroyedCities.size(); i++) {
			monsterPos.remove(destroyedCities.get(i));
		}
	}
	

	/**
	 * This method updates the Monster objects location after it has moved (one iteration).
	 * We assume that a Monster must move if given the chance to.
	 */
	public void updateMonsterLocations() {
		// Loop through all the Monsters that are left in this World.
		for(Integer key : monsters.keySet()) {
			City monsterStartLocation = monsters.get(key).getCurrentCity();
			Monster m = monsters.get(key);
			m.move();
			monsterPos.get(monsterStartLocation).remove(m);
			City newMonsterLocation = m.getCurrentCity();
			if(!monsterPos.containsKey(newMonsterLocation)) {
				ArrayList<Monster> monsterList = new ArrayList<Monster>();
				monsterList.add(m);
				monsterPos.put(newMonsterLocation, monsterList);
			}
			else {
				monsterPos.get(newMonsterLocation).add(m);
			}
		}
	}
	

	/**
	 * This helper method generates a random City that has not been destroyed by
	 * the Monsters. 
	 * @return Returns a random City.
	 */
	public City getRandomCity() {
		Random rand = new Random();
		ArrayList<City> arrayListOfCities = new ArrayList<City>();
		for(Map.Entry<String, City> entry : world.getCities().entrySet()) {
			arrayListOfCities.add(entry.getValue());
		}
		City randomCity = arrayListOfCities.get(rand.nextInt(world.getCities().size()));
		return randomCity;
	}
	
	
	/**
	 * This method runs the simulation and prints the information that has occurred at each iteration.
	 * This simulation will still run even if there are Monsters that are trapped within their own smaller graph.
	 */
	public void runSimulation() {
		// This is assuming that the cities can be destroyed immediately after
		// (before the first iteration) the monsters have been deployed
		System.out.println("\nThese cities have been destroyed before any iterations have occured: \n"
				+ "-------------------------------------------- \n");
		checkForDestroyedCities();
		System.out.println("--------------------------------------------");
		for(int i=1; i<10001; i++) {
			System.out.println("\nIteration \n " + i);
			System.out.println("Monsters Alive at start of iteration: " + monsters.size());
			if(monsters.size() < 2) {
				if(monsters.size() == 1) {
					System.out.println("There is only one monster left, no more cities can be destroyed.");
				}
				if(monsters.size() == 0) {
					System.out.println("All monsters are dead.");
				}
				break;
			}
			else {
				updateMaster();
			}
		}
		System.out.println("============================");
		System.out.println("THE SIMULATION HAS ENDED!");
		// Prints the Cities are left after the simulation has ended. 
		world.printMap();
	}
}
