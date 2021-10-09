public class Character implements Comparable<Character> {
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
