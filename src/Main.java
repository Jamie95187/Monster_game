import java.util.Scanner;

public class Main {
public static void main(String[] args) {
		
		// Create a scanner object to read the number of monsters the user has selected
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number of monsters");
		int numberOfMonsters = sc.nextInt();
		sc.close();
		
		String file = "/Users/jamie/eclipse-workspace/MonsterSolution/resource/world_map_medium.txt"; 
		GameSystem game = new GameSystem(file, numberOfMonsters);
		game.runSimulation();
	}
}
