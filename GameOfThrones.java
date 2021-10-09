import java.io.*;
import java.util.*;

/**
 * 
 * @author Thomas
 *
 */
public class GameOfThrones {
	public static void main(String[] args) throws Exception {
		// Stores the given csv's into different object types.
		ArrayList alist = characterFile("data/characters_lineage.csv");
		sLinkedList<Character> characters = (sLinkedList<Character>) alist.get(0);
		sLinkedList<String> houseNamesOnly = (sLinkedList<String>) alist.get(1);
		sLinkedList<House> houseList = (sLinkedList<House>) alist.get(2);
		FamilyTree ft = new FamilyTree();
		pqLinkedList<Prediction> pql = pqLinkedFile("data/predictions.csv");
		pqMyArrayList<Prediction> pqa = pqArrayFile("data/predictions.csv");

		// Stores names of every character in a singly linked list
		sLinkedList<String> charNamesOnly = new sLinkedList<String>();
		for (int d = 0; d < characters.getSize(); d++) {
			charNamesOnly.addLast(characters.get(d).getName().toLowerCase());
		}

		Scanner scan = new Scanner(System.in);
		String input = "";

		/*
		 * Takes user input and prints out characters with their associated battles
		 * until user enters 'exit' which breaks out of the while loop.
		 */
		while (true) {
			System.out.println(
					"\nEnter a character name, \"all\" for all characters, or \"family tree\" for a family tree of all houses.\n"
							+ "\nFor PLOD values, enter \"use sLinkedList\" to use the linked list heap or \"use MyArrayList\" to use the MyArrayList heap\n"
							+ "and enter \"LLTD\" to see which character is the least likely to die.\n"
							+ "Enter \"remove next\" to remove the front of the queue or \"remove all\" to remove all characters in the queue in order.\n"
							+ "\nTo exit the program type \"exit\": ");
			input = scan.nextLine().toLowerCase();

			// Checks to see if user input matches any names of characters. If yes,
			// prints character's name and battles they are involved in.
			if (charNamesOnly.contains(input)) {
				for (int a = 0; a < characters.getSize(); a++) {
					if (input.equals(charNamesOnly.get(a).toLowerCase())) {
						System.out.println(characters.get(a));
						for (int b = 0; b < characters.get(a).getBattles().getSize(); b++) {
							System.out.print(characters.get(a).getBattles().get(b).toString());
						}
						// Traverses the list of houses to find the character's House class then
						// prints their lineage by using lineageToString given the patriarch node
						// by comparing it to the character node made from
						// the user input.
						for (int c = 0; c < houseList.getSize(); c++) {
							if (houseList.get(c).getName().equals(characters.get(a).getAllegiances())) {
								ft = houseList.get(c).familyTree;
								FamilyTree ft1 = new FamilyTree();
								System.out.print(ft.lineageToString(
										ft.findNode(ft.getPatriarch(), ft1.makeNode(characters.get(a)))));
							}
						}
					}
				}

				// Prints all character names and battles they are involved in
			} else if (input.equals("all")) {
				for (int c = 0; c < characters.getSize(); c++) {
					System.out.println(characters.get(c).toString()); // Prints character's names first then battles
					for (int b = 0; b < characters.get(c).getBattles().getSize(); b++)
						System.out.print(characters.get(c).getBattles().get(b).toString());
				}
				System.out.print('\n');
				// Uses printTree to print the father's descendants and then
				// prints the mother's descendants.
			} else if (input.equals("family tree")) {
				for (int i = 0; i < houseList.getSize(); i++) {
					ft = houseList.get(i).familyTree;
					System.out.print(houseNamesOnly.get(i) + ':' + '\n');
					System.out.print("Patriarch: ");
					ft.printTree(ft.getPatriarch());
					System.out.print("Matriarch: ");
					ft.printTree(ft.getMatriarch());
				}
			
				/*
				 * Stays in sLinkedList mode until the user types "back".
				 * This is to prevent the beginning 5 line prompt after every
				 * single input. Also outputs a line if there are no more characters
				 * left. Includes straightforward commands of a priority queue linked list.
				 */
			} else if (input.equals("use slinkedlist")) {
				System.out.print("Now using sLinkedList! Type \"back\" if you would like to use other commands." + "\n");
				while (!input.equals("back")) {
					input = scan.nextLine().toLowerCase();
					if (input.equals("back")) {
						break;
					}
					// Calls pop() on the list (removes and returns first element).
					if (input.equals("remove next")) {
						if (pql.getSize() > 0) {
							System.out.print(pql.pop() + "\n");
						} else {
							System.out.print("There is noone left in this list. \n");
						}
					// Calls pop() on every single element in the list.
					} else if (input.equals("remove all")) {
						int s = pql.getSize();
						if (s == 0) {
							System.out.print("There is noone left in this list. \n");
						}
						for (int i = 0; i < s; i++) {
							System.out.print(i + " Removed: " + pql.pop() + "\n");
						}
					// Calls peek() on the list (returns but does not remove the first element).
					} else if (input.equals("lltd")) {
						if (pql.getSize() > 0) {
							System.out.print(pql.peek() + "\n");
						} else {
							System.out.print("There is noone left in this list. \n");
						}
					}
					else {
						System.out.print("Invalid command. Try again. \n");
					}
				}
				/*
				 * Stays in MyArrayList mode until the user types "back".
				 * This is to prevent the beginning 5 line prompt after every
				 * single input. Also outputs a line if there are no more characters
				 * left. Includes straightforward commands of a priority queue arraylist.
				 */
			} else if (input.equals("use myarraylist")) {
				System.out.print("Now using MyArrayList! Type \"back\" if you would like to use other commands." + "\n");
				while (!input.equals("back")) {
					input = scan.nextLine().toLowerCase();
					if (input.equals("back")) {
						break;
					}
					// Calls remove() on the list (Removes and returns first element of the list).
					if (input.equals("remove next")) {
						if (pqa.getSize() > 0) {
							System.out.print(pqa.remove() + "\n");
						} else {
							System.out.print("There is noone left in this list. \n");
						}
					// Calls remove() on every single element in the list.
					} else if (input.equals("remove all")) {
						int s = pqa.getSize();
						if (s == 0) {
							System.out.print("There is noone left in this list. \n");
						}
						for (int i = 0; i < s; i++) {
							System.out.print(i + " Removed: " + pqa.remove() + "\n");
						}
					// Calls peek() on the list (returns but does not remove the first element).
					} else if (input.equals("lltd")) {
						if (pqa.getSize() > 0) {
							System.out.print(pqa.peek() + "\n");
						} else {
							System.out.print("There is noone left in this list. \n");
						}
					}

					else {
						System.out.print("Invalid command. Try again. \n");
					}
				}

			} else if (input.equals("exit")) {
				break;

			} else {
				System.err.println("The character does not exist." + '\n');
			}

		}
		scan.close();
	}

	/*
	 * Parses a file (battles.csv) and splits each line into strings with comma
	 * delimiters and makes battle object to store into a singly linked list. Then,
	 * it sorts the battles in alphabetical order.
	 */
	public static sLinkedList<Battle> battleFile(String filename) throws IOException {
		sLinkedList<Battle> battles = new sLinkedList<Battle>();
		String x = "";
		Scanner inFile = new Scanner(new File(filename));
		while (inFile.hasNext()) {
			x = inFile.nextLine();
			String[] x1 = x.split(",");
			String i1 = x1[0];
			String i2 = x1[1];
			String i3 = x1[2];
			String i4 = x1[3];
			String i5 = x1[4];
			String i6 = x1[5];
			String i7 = x1[6];
			battles.addLast(makeBattle(i1, i2, i3, i4, i5, i6, i7));
		}
		inFile.close();
		battles.sort();
		return battles;
	}

	/*
	 * Battle object constructor using the string elements from parsing the battle
	 * file.
	 */
	public static Battle makeBattle(String i1, String i2, String i3, String i4, String i5, String i6, String i7) {
		return new Battle(i1, i2, i3, i4, i5, i6, i7);
	}

	/*
	 * Parses a file (characters.csv) and splits each line into strings with comma
	 * delimiters and makes character objects to store in a singly linked list with
	 * their associated battles. Then, it is sorted by alphabetical order.
	 */
	public static ArrayList characterFile(String filename) throws IOException {
		ArrayList alist = new ArrayList(3);
		sLinkedList<Character> characters = new sLinkedList<Character>();
		sLinkedList<Battle> battles = battleFile("data/battles.csv");
		sLinkedList<House> houseList = new sLinkedList<House>();
		sLinkedList<String> houseNamesOnly = new sLinkedList<String>();

		String x = "";
		Scanner inFile = new Scanner(new File(filename));
		x = inFile.nextLine();
		while (inFile.hasNext()) {
			x = inFile.nextLine();
			String[] x1 = x.split(",");
			String i1 = x1[0];
			String i2 = x1[1];
			String i3 = x1[2];
			String i4 = x1[3];
			sLinkedList<Battle> i5 = indexofall(battles, i1);
			characters.addLast(makeCharacter(i1, i2, i3, i4, i5));
			// Stores the house names in a list of strings and adds a house object in
			// another list of house objects.
			if (!houseNamesOnly.contains(i4)) {
				houseNamesOnly.addLast(i4);
				House o = new House(i4);
				houseList.addLast(o);
			}
		}
		// adds members of each house to proper house object
		for (int i = 0; i < characters.getSize(); i++) {
			for (int j = 0; j < houseList.getSize(); j++) {
				if (houseList.get(j).getName().equals(characters.get(i).getAllegiances())) {
					houseList.get(j).members.addLast(characters.get(i));
				}
			}
		}
		// builds family trees for each house object in house list
		for (int i = 0; i < houseList.getSize(); i++) {
			houseList.get(i).familyTree.buildFamilyTree(houseList.get(i));
		}
		inFile.close();
		characters.sort();
		alist.add(characters);
		alist.add(houseNamesOnly);
		alist.add(houseList);
		return alist;
	}

	/*
	 * Character constructor that also takes in a linked list of battles associated
	 * with the character.
	 */
	public static Character makeCharacter(String i1, String i2, String i3, String i4, sLinkedList<Battle> i5) {
		return new Character(i1, i2, i3, i4, i5);
	}

	/*
	 * Takes in the singly linked list of battles and a character's name and returns
	 * another singly linked list containing all battles associated with the
	 * character name given.
	 */
	public static sLinkedList<Battle> indexofall(sLinkedList<Battle> battles, String name) {
		sLinkedList<Battle> charbattles = new sLinkedList<Battle>();
		for (int i = 0; i < battles.getSize(); i++)
			if (name.equals(battles.get(i).getAttackerKing()) || name.equals(battles.get(i).getDefenderKing())) {
				charbattles.addLast(battles.get(i));
			}
		return charbattles;
	}
	
	/*
	 * Parses the Prediction csv file and makes Prediction objects out of the character name and PLOD
	 * value. Then, it pushes that Prediction object into the priority queue linked list.
	 */
	public static pqLinkedList<Prediction> pqLinkedFile(String filename) throws IOException {
		pqLinkedList<Prediction> pql = new pqLinkedList<Prediction>();

		String x = "";
		Scanner inFile = new Scanner(new File(filename));
		x = inFile.nextLine();
		while (inFile.hasNext()) {
			x = inFile.nextLine();
			String[] x1 = x.split(",");
			float i1 = Float.parseFloat(x1[0]);
			Character i2 = new Character(x1[1]);
			Prediction prediction = new Prediction(i1, i2);
			pql.push(prediction);
		}
		return pql;
	}
	
	/*
	 * Parses the Prediction csv file and makes Prediction objects out of the character name and PLOD
	 * value. Then, it pushes that Prediction object into the priority queue arraylist.
	 */
	public static pqMyArrayList<Prediction> pqArrayFile(String filename) throws IOException {
		pqMyArrayList<Prediction> pqa = new pqMyArrayList<Prediction>();

		String x = "";
		Scanner inFile = new Scanner(new File(filename));
		x = inFile.nextLine();
		while (inFile.hasNext()) {
			x = inFile.nextLine();
			String[] x1 = x.split(",");
			float i1 = Float.parseFloat(x1[0]);
			Character i2 = new Character(x1[1]);
			Prediction prediction = new Prediction(i1, i2);
			pqa.insert(prediction);
		}
		return pqa;
	}
}
