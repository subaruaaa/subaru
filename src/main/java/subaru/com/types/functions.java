package subaru.com.types;

import static java.lang.String.*;

import java.io.PrintStream;
import java.util.*;

import static java.lang.Math.*;

public class functions {
	/* Tuples */
	public static Tuple0 o() {
		return new Tuple0();
	}

	public static <T1> Tuple1<T1> o(T1 o1) {
		return new Tuple1<T1>(o1);
	}

	public static <T1, T2> Tuple2<T1, T2> o(T1 o1, T2 o2) {
		return new Tuple2<T1, T2>(o1, o2);
	}

	public static <T1, T2, T3> Tuple3<T1, T2, T3> o(T1 o1, T2 o2, T3 o3) {
		return new Tuple3<T1, T2, T3>(o1, o2, o3);
	}

	public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> o(T1 o1, T2 o2, T3 o3, T4 o4) {
		return new Tuple4<T1, T2, T3, T4>(o1, o2, o3, o4);
	}

	public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> o(T1 o1, T2 o2, T3 o3, T4 o4, T5 o5) {
		return new Tuple5<T1, T2, T3, T4, T5>(o1, o2, o3, o4, o5);
	}

	public static <T, S extends Tuple1<T>> List<T> get1stElements(List<S> tuples) {
		List<T> list = list();
		for (S t : tuples) {
			list.add(t._1);
		}
		return list;
	}

	public static <T, S extends Tuple2<?, T>> List<T> get2ndElements(List<S> tuples) {
		List<T> list = list();
		for (S t : tuples) {
			list.add(t._2);
		}
		return list;
	}

	public static <T, S extends Tuple3<?, ?, T>> List<T> get3rdElements(List<S> tuples) {
		List<T> list = list();
		for (S t : tuples) {
			list.add(t._3);
		}
		return list;
	}

	public static <K, V> List<Tuple2<K, V>> entries2TupleList(Iterable<Map.Entry<K, V>> entries) {
		List<Tuple2<K, V>> tuples = list();
		for (Map.Entry<K, V> e : entries) {
			tuples.add(o(e.getKey(), e.getValue()));
		}
		return tuples;
	}

	public static <K, V> Map<K, V> tupleList2Map(Iterable<Tuple2<K, V>> tuples) {
		Map<K, V> map = new HashMap<K, V>();
		for (Tuple2<K, V> tuple : tuples) {
			map.put(tuple._1, tuple._2);
		}
		return map;
	}

	/* Map */
	public static <K, V> Map<K, V> m(Tuple2<K, V>... entries) {
		Map<K, V> map = new HashMap<K, V>();

		for (Tuple2<K, V> entry : entries) {
			map.put(entry._1, entry._2);
		}

		return map;
	}

	public static <K, V> Map<K, V> map(K k, V v, Object... kvs) {
		if (kvs.length % 2 != 0) {
			throw new IllegalArgumentException("number of arguments should be even");
		}

		Map<K, V> map = new HashMap<K, V>();
		map.put(k, v);
		for (int i = 0; i < kvs.length; i += 2) {
			map.put((K) kvs[i], (V) kvs[i + 1]);
		}
		return map;
	}

	public static <T, T1, T2> List<Map<T, Object>> make_title(Tuple2<T, T> title, List<Tuple2<T1, T2>> values) {
		List<Map<T, Object>> list = list();
		for (Tuple2 v : values) {
			list.add(map(title._1, v._1, title._2, v._2));
		}
		return list;
	}

	public static <T, T1, T2, T3> List<Map<T, Object>> make_title(Tuple3<T, T, T> title, List<Tuple3<T1, T2, T3>> values) {
		List<Map<T, Object>> list = list();
		for (Tuple3 v : values) {
			list.add(map(title._1, v._1, title._2, v._2, title._3, v._3));
		}
		return list;
	}

	public static <T, T1, T2, T3, T4> List<Map<T, Object>> make_title(Tuple4<T, T, T, T> title,
			List<Tuple4<T1, T2, T3, T4>> values) {
		List<Map<T, Object>> list = list();
		for (Tuple4 v : values) {
			list.add(map(title._1, v._1, title._2, v._2, title._3, v._3, title._4, v._4));
		}
		return list;
	}

	public static <K1, K2, V> Map<K1, Map<K2, V>> mergeMaps(Map<K1, Map<K2, V>> map0, Map<K1, Map<K2, V>>... maps) {
		// TODO
		for (Map<K1, Map<K2, V>> map : maps) {
			map0 = mergeMap(map0, map);
		}
		return map0;
	}

	public static <K1, K2, V> Map<K1, Map<K2, V>> mergeMap(Map<K1, Map<K2, V>> map, Map<K1, Map<K2, V>> supplement) {
		for (K1 k : map.keySet()) {
			if (supplement.containsKey(k)) {
				map.get(k).putAll(supplement.get(k));
			}
		}
		return map;
	}

	/* List */
	public static <T> List<T> list(T... elements) {
		List<T> list = new ArrayList<T>();

		for (T element : elements) {
			list.add(element);
		}

		return list;
	}

	public static <T> List<T> listNonNull(T... elements) {
		List<T> list = new ArrayList<T>();

		for (T e : elements) {
			if (e == null)
				continue;
			list.add(e);
		}

		return list;
	}

	public static <T> List<T> toList(PriorityQueue<T> queue) {
		List<T> list = list();
		while (!queue.isEmpty()) {
			list.add(queue.poll());
		}
		return list;
	}

	public static <T> List<T> subList(List<T> l, int fromIndex, int toIndex) {
		fromIndex = min(l.size(), max(0, fromIndex));
		toIndex = min(l.size(), max(0, toIndex));
		return l.subList(fromIndex, toIndex);
	}

	/**
	 * 
	 * @param map
	 * @param list
	 * @return a map which key in list
	 */
	public static <K, V> Map<K, V> filterMap(Map<K, V> map, List<K> list) {
		Map<K, V> m = new HashMap<K, V>();
		for (K l : list) {
			if (map.containsKey(l)) {
				m.put(l, map.get(l));
			}
		}

		return m;
	}

	public static String subString(String str, int width) {
		StringBuffer sb = new StringBuffer();
		int w = 0;
		int i = 0;
		for (; i < str.length() && w < width; ++i) {
			w += str.codePointAt(i) < 128 ? 1 : 2;
			sb.append(str.charAt(i));
		}
		if (i <= str.length())
			sb.append("…");
		return sb.toString();
	}

	/**
	 * subtract sets
	 * 
	 * @param l
	 * @param c
	 * @param <T>
	 * @return a list of elements in l not in c
	 */
	public static <T> List<T> subtract(Collection<T> l, Collection<T> c) {
		List<T> list = list();
		for (T e : l) {
			if (c.contains(e))
				continue;
			list.add(e);
		}
		return list;
	}

	public static <T> String join(String delimiter, List<T> list) {
		String str = "";
		int i = 0;
		for (T e : list) {
			if (i > 0)
				str += delimiter;
			str += e.toString();
			++i;
		}
		return str;
	}

	public static <T> List<T> unique(List<T> list) {
		List<T> newList = list();
		for (T e : list) {
			if (newList.contains(e))
				continue;
			newList.add(e);
		}
		return newList;
	}

	public static <T> List<T> uniqueBy(List<T> list, Comparator<? super T> comparator) {
		Set<T> set = new TreeSet<T>(comparator);
		set.addAll(list);
		return list((T[]) set.toArray());
	}

	public static List<Integer> parseInts(String str, String regex) {
		List<Integer> ints = list();
		String[] strs = str.split(regex);
		for (String s : strs) {
			try {
				ints.add(Integer.parseInt(s));
			} catch (Exception e) {
			}
		}
		return ints;
	}

	/* print */
	public static <T> void print(T x) {
		System.out.print(x);
	}

	public static <T> void println(T x) {
		System.out.println(x);
	}

	public static void println() {
		System.out.println();
	}

	public static PrintStream printf(String format, Object... args) {
		return System.out.printf(format, args);
	}

	public static PrintStream printf(Locale l, String format, Object... args) {
		return System.out.printf(l, format, args);
	}

	/**
	 * <a href=
	 * "http://en.wikipedia.org/wiki/Normal_distribution#Generating_values_from_normal_distribution"
	 * > Normal Distribution </a> <a
	 * href="http://en.wikipedia.org/wiki/Box_Muller_transform"> Box Muller
	 * transform </a>
	 * 
	 * @param mu
	 *            the average of the distribution
	 * @param sigma
	 *            the standard deviation
	 * @return a random number from normal distribution
	 */
	public static double randGaussian(double mu, double sigma) {
		double x = random();
		double y = random();
		return mu + sqrt(-2 * log(x)) * cos(2 * PI * y) * sigma;
	}

}
