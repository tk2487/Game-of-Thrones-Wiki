import java.util.*;

/**
 * 
 * @author Thomas
 *
 * @param <E>
 */
public class sLinkedList<E extends Comparable<E>> {
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
