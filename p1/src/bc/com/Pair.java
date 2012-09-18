package bc.com;

import java.util.ArrayList;
import java.util.Collections;

public class Pair implements Comparable<Pair> {
	public final int key;
	public final int value;

	public Pair(int k, int v) {
		key = k;
		value = v;
	}

	public int compareTo(Pair that) {
		if (value == that.value)
			return 0;
		if (value < that.value)
			return -1;
		return 1;
	}

	public String toString() {
		return "[" + key + ":" + value + "]";
	}

	public static Pair valueOf(int k, int v) {
		return new Pair(k, v);
	}

	public static void main(final String[] args) {
		ArrayList<Pair> data = new ArrayList<Pair>();
		data.add(Pair.valueOf(1, 61));
		data.add(Pair.valueOf(2, 77));
		data.add(Pair.valueOf(3, 78));
		data.add(Pair.valueOf(4, 79));
		data.add(Pair.valueOf(5, 80));
		data.add(Pair.valueOf(6, 80));
		data.add(Pair.valueOf(1, 61));
		data.add(Pair.valueOf(1, 61));

		Collections.sort(data);

		ArrayList<Pair> newData = new ArrayList<Pair>();
		// 首先重写hashCode与equals，再用contains方法比较
		for (Pair pair : data) {
			if (newData.contains(pair))
				System.out.println("1:有相同");
			else {
				System.out.println("0：不同");
				newData.add(pair);
			}
		}
		data = newData;

		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).key);
		}

	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + key;
		result = PRIME * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Pair other = (Pair) obj;
		if (key != other.key)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
}