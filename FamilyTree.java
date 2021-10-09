public class FamilyTree {
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
