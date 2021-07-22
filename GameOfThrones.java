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
					"Enter a character name (or type \"all\" for all characters, \"family tree\" for a family tree of all houses,\n"
							+ "\"remove all\" to remove all characters, \"remove next\" to remove the next character,\n"
							+ "\"use sLinkedList\" to use the linked list heap, \"use MyArrayList\" to use the MyArrayList heap,\n"
							+ "\"LLTD\" to see which character is the least likely to die,\n"
							+ "or \"exit\" to exit):");
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


class House implements Comparable<House> {
	private String name;
	protected sLinkedList<Character> members;
	private Character patriarch;
	private Character matriarch;
	public FamilyTree familyTree;

	// constructor that instantiates a family tree to be later built
	// and a member linked list with all of the people with the same
	// allegiance.
	public House(String name) {
		this.name = name;
		this.members = new sLinkedList<Character>();
		this.familyTree = new FamilyTree();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public sLinkedList<Character> getMembers() {
		return members;
	}

	public Character getPatriarch() {
		return patriarch;
	}

	public void setPatriarch(Character patriarch) {
		this.patriarch = patriarch;
	}

	public Character getMatriarch() {
		return matriarch;
	}

	public void setMatriarch(Character matriarch) {
		this.matriarch = matriarch;
	}

	@Override
	public int compareTo(House o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return name + " -father: " + patriarch + " -mother: " + matriarch;
	}

}

class FamilyTree {
	private CharacterNode patriarch;
	private CharacterNode matriarch;
	
	public CharacterNode getPatriarch() {
		return patriarch;
	}

	public CharacterNode getMatriarch() {
		return matriarch;
	}

	// links all members of a house - this function
	// assigns matriarch and patriarch of the house and
	// uses them as the roots in the helper build function
	public void buildFamilyTree(House h) {
		sLinkedList<CharacterNode> charNodeList = new sLinkedList<CharacterNode>();
		for (int i = 0; i < h.getMembers().getSize(); i++) {
			CharacterNode chn = new CharacterNode(h.members.get(i));
			if (h.members.get(i).getFatherName().equals("PATRIARCH")) {
				this.patriarch = chn;
			}
			if (h.members.get(i).getMotherName().equals("MATRIARCH")) {
				this.matriarch = chn;
			}
			charNodeList.addLast(chn);
		}
		buildFamilyTree(charNodeList, patriarch);
		buildFamilyTree(charNodeList, matriarch);
	}

	// takes root character nodes and assigns children to them recursively
	// so the children of the children also get assigned
	private void buildFamilyTree(sLinkedList<CharacterNode> l, CharacterNode root) {
		if (root.children.getSize() != 0) {
			return;
		}
		for (int i = 0; i < l.getSize(); i++) {
			if (l.get(i).getCharacter().getFatherName().equals(root.getCharacter().getName())) {
				root.children.addLast(l.get(i));
				l.get(i).father = root;
				buildFamilyTree(l, l.get(i));
			}
			if (l.get(i).getCharacter().getMotherName().equals(root.getCharacter().getName())) {
				root.children.addLast(l.get(i));
				l.get(i).mother = root;
				buildFamilyTree(l, l.get(i));
			}
		}
	}
	
	public CharacterNode makeNode(Character c) {
		CharacterNode ch = new CharacterNode(c);
		return ch;
	}

	// function that prints a family tree given the root of
	// that family tree. - uses a helper function
	public void printTree(CharacterNode node) {
		printTree(node, 1);
		System.out.print('\n');
	}

	// helper function that takes a recursively changing root and
	// prints every child of that root. also takes in indent
	// counter to properly format it.
	private void printTree(CharacterNode node, int indent) {
		if (node.children == null) {
			return;
		}
		String tab = "";
		for (int i = 0; i < indent - 1; i++) {
			tab += "\t";
		}
		if (node.getCharacter().getFatherName().equals("PATRIARCH")
				|| node.getCharacter().getMotherName().equals("MATRIARCH")) {
			System.out.print(tab + node.getCharacter().getName() + '\n');
		} else {
			System.out.print(tab + node.getCharacter().getName() + " -father: " + node.getCharacter().getFatherName()
					+ " -mother: " + node.getCharacter().getMotherName() + '\n');
		}
		for (int i = 0; i < node.children.getSize(); i++) {
			printTree(node.children.get(i), indent + 1);
		}
	}

	// function that recursively searches 
	// the root's children and their children etc. until it finds
	// a match by comparing the root and the 
	// given character node. then it returns that match.
	public CharacterNode findNode(CharacterNode root, CharacterNode ch) {
	
		if (root.compareTo(ch) == 0) {
			return root;
		}
		for (int i = 0; i < root.children.getSize(); i++) {
			if (root.children.get(i).compareTo(ch) == 0) {
				ch = root.children.get(i);
			} else if (root.children.get(i).children.getSize() != 0) {
				ch = findNode(root.children.get(i), ch);
			}
		}
		return ch;
	}

	// function that traces the lineage of a given character.
	// takes in a character node (which is given through the findNode function when
	// the user inputs a character). first prints out the father's side
	// of the family then prints out the mother's side.
	public String lineageToString(CharacterNode c) {
		String s = '\n' + c.getCharacter().getName() + " -father: " + c.getCharacter().getFatherName() + " -mother: "
				+ c.getCharacter().getMotherName() + "\n" + ' ';
		s += "Father's Side:" + "\n" + ' ' + lineageToStringHelper(c.father) + '\n' + ' ';
		s += "Mother's Side:" + "\n" + ' ' + lineageToStringHelper(c.mother);
		return s + '\n';
	}

	// helper function that prints out the father and mother of the given
	// node and prints out the parents of the the node's parents recursively
	// until a patriarch or matriarch is found.
	public String lineageToStringHelper(CharacterNode c) {
		if (c == null) {
			return "";
		}
		String t = " " + c.getCharacter().getName() + " -father: " + c.getCharacter().getFatherName() + " -mother: "
				+ c.getCharacter().getMotherName() + "\n" + ' ';
		if (!c.getCharacter().getFatherName().equals("PATRIARCH")
				|| !c.getCharacter().getMotherName().equals("MATRIARCH")) {
			t += lineageToStringHelper(c.father);
			t += lineageToStringHelper(c.mother);
		}
		return t;
	}
	class CharacterNode implements Comparable<CharacterNode> {
		private Character character;
		private CharacterNode father;
		private CharacterNode mother;
		private sLinkedList<CharacterNode> children;

		// constructor that instantiates a children list to be later modified.
		public CharacterNode(Character character) {
			this.character = character;
			this.children = new sLinkedList<CharacterNode>();
		}

		// adds a child to the children linked list of the node
		public CharacterNode addChild(Character character) {
			CharacterNode charNode = new CharacterNode(character);
			charNode.father = this;
			this.children.addLast(charNode);
			return charNode;
		}

		public Character getCharacter() {
			return character;
		}

		@Override
		public int compareTo(CharacterNode o) {
			return (this.getCharacter().compareTo(o.getCharacter()));
		}
	}

}

class Character implements Comparable<Character> {
	String name;
	String allegiances;
	sLinkedList<Battle> battles;
	private String fatherName;
	private String motherName;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param allegiances
	 * @param battles
	 * @return Character object
	 */
	public Character(String name, String fatherName, String motherName, String allegiances,
			sLinkedList<Battle> battles) {
		this.name = name;
		this.allegiances = allegiances;
		this.battles = battles;
		this.fatherName = fatherName;
		this.motherName = motherName;
	}
	
	public Character(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public String getName() {
		return name;
	}

	public String getAllegiances() {
		return allegiances;
	}

	public sLinkedList<Battle> getBattles() {
		return battles;
	}

	@Override
	public String toString() {
		return name + " with allegiance to " + allegiances;
	}

	@Override
	public int compareTo(Character a) {
		return this.getName().compareToIgnoreCase(a.getName());
	}

}

class Battle implements Comparable<Battle> {
	String name;
	String attackerKing;
	String defenderKing;
	String attackerOutcome;
	String battleType;
	String location;
	String region;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param attackerKing
	 * @param defenderKing
	 * @param attackerOutcome
	 * @param battleType
	 * @param location
	 * @param region
	 * @return A battle object
	 */
	public Battle(String name, String attackerKing, String defenderKing, String attackerOutcome, String battleType,
			String location, String region) {
		this.name = name;
		this.attackerKing = attackerKing;
		this.defenderKing = defenderKing;
		this.attackerOutcome = attackerOutcome;
		this.battleType = battleType;
		this.location = location;
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public String getAttackerKing() {
		return attackerKing;
	}

	public String getDefenderKing() {
		return defenderKing;
	}

	public String getAttackerOutcome() {
		return attackerOutcome;
	}

	public String getBattleType() {
		return battleType;
	}

	public String getLocation() {
		return location;
	}

	public String getRegion() {
		return region;
	}

	@Override
	public String toString() {
		return String.format(" - " + name + ", when " + attackerKing + " attacked " + defenderKing + ", resulting in a "
				+ attackerOutcome + ", through a " + battleType + ", at " + location + ", in the region of " + region
				+ "\n");
	}

	@Override
	public int compareTo(Battle a) {
		return this.getName().compareToIgnoreCase(a.getName());
	}
}


class MyArrayList<E extends Comparable<E>> extends ArrayList<E> {

	public void sort() {
		Collections.sort(this);
	}

	public boolean isSorted() {
		boolean sorted = true;
		for (int i = 1; i < this.size(); i++) {
			if (this.get(i - 1).compareTo(this.get(i)) > 0) {
				sorted = false;
				break;
			}
		}
		return sorted;
	}

	public boolean contains(E value) {
		if (isSorted()) {
			binarySearch(0, this.size() - 1, value);
		} else {
			super.contains(value);
		}
		return false;
	}

	public boolean binarySearch(int start, int end, E val) {
		int mid = (end - start / 2);
		while (end >= start) {
			if (this.get(mid).compareTo(val) == 0) {
				return true;
			} else if (this.get(mid).compareTo(val) > 0) {
				start = mid + 1;
			} else if (this.get(mid).compareTo(val) < 0) {
				end = mid - 1;
			}
		}
		return false;
	}
}

class pqLinkedList<E extends Comparable<E>> extends sLinkedList<E> {
	private sllNode<E> head;
	private int size = 0;
	
	// Constructor
	public pqLinkedList() {
		head = null;
	}
	
	// Simply returns the first node data which is always the min value.
	public E peek() {
		return head.data;
	}
	
	/*
	 * Returns the value of the first node and removes it by making the next node the head.
	 * When the list only has one element, it will return the head data and make the head null.
	 */
	public E pop() {
		sllNode<E> temp = head;
		if (head.next == null) {
			head = null;
			size -= 1;
			return temp.data;
		}
		size -= 1;
		head = head.next;
		return temp.data;
	}

	/*
	 * Pushes a data value into the list. If the list has 0 elements, the data value will be the first element.
	 * If not, then it checks to see if the head data value is greater than the the input data value in which case
	 * the head is pushed back and the input becomes the new head. If not that, then it compares the input
	 * to each data value until it finds one that is greater the input.
	 */
	public void push(E data) {
		sllNode<E> start = head;
		sllNode<E> temp = new sllNode<E>(data, head);

		if (size == 0) {
			head = temp;
			size += 1;
			return;
		}
		if (head.data.compareTo(data) > 0) {
			temp.next = head;
			head = temp;
			size += 1;
			return;
		} else {
			while (start.next != null && start.next.data.compareTo(data) < 0) {
				start = start.next;
			}
			temp.next = start.next;
			start.next = temp;
			size += 1;
		}
	}

	public int getSize() {
		return size;
	}

}

class pqMyArrayList<E extends Comparable<E>> {
	private MyArrayList<E> heap;
	private int size;
	
	// Constructor
	public pqMyArrayList() {
		this.heap = new MyArrayList<E>();
	}
	
	/*
	 * Adds the input data value to the list and then calls upHeap on it
	 * to get it to its correct spot.
	 */
	public void insert(E data) {
		heap.add(data);
		upHeap(heap.size() - 1);
	}

	// Returns the first element of the list which is the min value.
	public E peek() {
		return heap.get(0);
	}

	/*
	 * First stores the first element in a variable, temp. Then, swaps the 
	 * first element and the last element and removes the last element.
	 * Then, it calls downHeap on the new first element so that if it's not the
	 * min value it will move it to its correct position.
	 */
	public E remove() {
		E temp = heap.get(0);
		Collections.swap(heap, 0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		downHeap(0);
		return temp;
	}

	/*
	 * Moves the value up in the list if its value is less than its parent's value.
	 * Achieves this by comparing their values and swapping their places if it matches
	 * the requirements.
	 */
	public void upHeap(int index) {
		while (index > 0) {
			int parent = parent(index);
			if (heap.get(index).compareTo(heap.get(parent)) >= 0) {
				break;
			}
			Collections.swap(heap, index, parent);
			index = parent;
		}
	}

	/*
	 * Moves the value down by comparing it to its children's values.
	 * Achieves this by first checking if there is a left child and making it the
	 * small child then checking to see if there is a right child and if it is smaller
	 * than the left. Depending on whoever is the smaller child (left or right), it is 
	 * then compared to the input value and swapped if it meets the requirement.
	 */
	public void downHeap(int index) {
		while (hasLeft(index)) {
			int left = left(index);
			int small = left;
			if (hasRight(index)) {
				int right = right(index);
				if (heap.get(right).compareTo(heap.get(left)) < 0) {
					small = right;
				}
			}
			if (heap.get(small).compareTo(heap.get(index)) >= 0) {
				break;
			}
			Collections.swap(heap, index, small);
			index = small;
		}
	}

	// Returns parent index of every element.
	public int parent(int index) {
		return (index - 1) / 2;
	}

	// Returns left child of every element.
	public int left(int index) {
		return 2 * index + 1;
	}

	// Returns right child of every element.
	public int right(int index) {
		return index * 2 + 2;
	}

	/*
	 * Checks to see if the left child index is within the list size.
	 * Essentially checking if a left child exists.
	 */
	protected boolean hasLeft(int i) {
		return left(i) < heap.size();
	}

	/*
	 * Checks to see if the right child index is within the list size.
	 * Essentially checking if a right child exists.
	 */
	protected boolean hasRight(int i) {
		return right(i) < heap.size();
	}
	
	public int getSize() {
		return heap.size();
	}
}

class Prediction implements Comparable<Prediction> {

	private float plod;
	private Character character;

	/*
	 * Constructor for Prediction class that takes in the PLOD float number
	 * and the character object.
	 */
	public Prediction(float plod, Character character) {
		this.character = character;
		this.plod = plod;
	}

	public float getPlod() {
		return plod;
	}

	public Character getCharacter() {
		return character;
	}
	
	//Compares PLOD numbers of the two prediction objects.
	@Override
	public int compareTo(Prediction p) {
		return Float.valueOf(this.getPlod()).compareTo(Float.valueOf(p.getPlod()));
	}

	@Override
	public String toString() {
		return character.getName() + " with PLOD of " + plod;
	}

}

class sLinkedList<E extends Comparable<E>> {
	private sllNode<E> head;
	private int size;

	/**
	 * Constructor
	 */
	public sLinkedList() {
		head = null;
	}

	/**
	 * Checks to see if size of linked list is equal to 0
	 * 
	 * @return Boolean value indicating whether linked list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param Any
	 *            object type Adds the first element to an empty linked list
	 */
	public void addFirst(E o) {
		head = new sllNode<E>(o, head);
		size++;
	}

	public String toString() {
		int count; // index of Node we are looking at
		for (count = 0; count < this.getSize(); count++) {
			System.out.println(this.get(count).toString());
		}
		return "";
	}

	/**
	 * @param Any
	 *            object type Adds an element to the end of a linked list. If empty,
	 *            calls addFirst.
	 */
	public void addLast(E o) {
		if (head == null) {
			addFirst(o);
		} else {
			sllNode<E> tmp = head;
			while (tmp.next != null) {
				tmp = tmp.next;
			}
			tmp.next = new sllNode<E>(o, null);
			size++;
		}
	}

	/**
	 * Calls mergeSort to sort a linked list.
	 */
	public void sort() {
		sllNode<E> o = head;
		head = mergeSort(o);
	}

	/**
	 * @param Singly
	 *            linked list node a
	 * @param Singly
	 *            linked list node b
	 * @return Singly linked list node that is sorted recursively
	 */
	public sllNode<E> sortedMerge(sllNode<E> a, sllNode<E> b) {
		sllNode<E> res = null;
		// Base cases
		if (a == null)
			return b;
		if (b == null)
			return a;

		// Takes lower value linked list using compareTo and calls sortedMerge again.
		if (a.compareTo(b) <= 0) {
			res = a;
			res.next = sortedMerge(a.next, b);
		} else {
			res = b;
			res.next = sortedMerge(a, b.next);
		}
		return res;

	}

	/**
	 * Splits singly linked list nodes into two halves recursively and sorted by
	 * calling sortedMerge
	 * 
	 * @param Singly
	 *            linked list node
	 * @return Sorted singly linked list node
	 */
	public sllNode<E> mergeSort(sllNode<E> n) {
		// Base case - if head is null
		if (n == null || n.next == null) {
			return n;
		}

		// Gets middle of list using getMid
		sllNode<E> mid = getMid(n);
		sllNode<E> midNext = mid.next;

		// Sets middle.next to null
		mid.next = null;

		// Calls mergeSort on left list
		sllNode<E> lt = mergeSort(n);

		// Calls mergeSort on right list
		sllNode<E> rt = mergeSort(midNext);

		// Merge left and right lists
		sllNode<E> sortList = sortedMerge(lt, rt);
		return sortList;
	}

	// Gets middle of list
	public sllNode<E> getMid(sllNode<E> h) {
		// Base case
		if (h == null)
			return h;
		sllNode<E> fr = h.next;
		sllNode<E> sr = h;

		// Moves fr and sr where sr points to middle node
		while (fr != null) {
			fr = fr.next;
			if (fr != null) {
				sr = sr.next;
				fr = fr.next;
			}
		}
		return sr;
	}

	/**
	 * Checks to see if nodes are sorted by data value
	 * 
	 * @return A boolean value depending on result
	 */
	public boolean isSorted() {
		for (int i = 0; i < this.getSize(); i++) {
			if (this.get(i - 1).compareTo(this.get(i)) > 0) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

	/**
	 * Checks to see if object is contained in singly linked list
	 * 
	 * @param Any
	 *            object type
	 * @return A boolean value depending on result
	 */
	public boolean contains(E o) {
		sllNode<E> cd = head;
		while (cd != null) {
			E tmp = cd.data;
			if (tmp.equals(o)) {
				return true;
			}
			cd = cd.next;
		}
		return false;
	}

	public int getSize() {
		return size;
	}

	/**
	 * @param index
	 * @return Any object type in a singly linked list with position 'index'
	 */
	public E get(int index) {
		sllNode<E> cd = head;
		int count; // index of Node we are looking at
		if (head == null) {
			return null;
		}
		for (count = 0; count < index; count++) {
			cd = cd.next;
		}
		return cd.getData();
	}

	class sllNode<E extends Comparable<E>> {
		sllNode<E> next;
		E data;

		/**
		 * Node constructor
		 */
		public sllNode(E data, sllNode<E> next) {
			this.next = next;
			this.data = data;
		}

		/**
		 * 
		 * @param next
		 *            node of current node we are looking at
		 * @return int value indicating comparison between two data values
		 */
		public int compareTo(sllNode<E> next) {
			return this.getData().compareTo(next.getData());
		}

		public E getData() {
			return data;
		}

	}

}

