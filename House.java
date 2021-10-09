public class House implements Comparable<House> {
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
