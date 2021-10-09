public class pqLinkedList<E extends Comparable<E>> extends sLinkedList<E> {
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
