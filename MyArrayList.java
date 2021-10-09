import java.util.*;

public class MyArrayList<E extends Comparable<E>> extends ArrayList<E> {

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
