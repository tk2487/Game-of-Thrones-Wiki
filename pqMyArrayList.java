import java.util.*;

public class pqMyArrayList<E extends Comparable<E>> {
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
