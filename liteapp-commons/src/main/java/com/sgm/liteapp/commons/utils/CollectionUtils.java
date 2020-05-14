package com.sgm.liteapp.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

public class CollectionUtils {

	public static Collection<?> complement(Collection<?> full, Collection<?> part) {
		if (full == null) {
			return null;
		}
		if (part == null || part.size() == 0) {
			return full;
		}
		Iterator<?> it = full.iterator();
		while (it.hasNext()) {
			if (part.contains(it.next())) {
				it.remove();
			}
		}
		return full;
	}

	public static List<String> toList(String content, String regex) {
		if (content == null) {
			return null;
		}
		String[] strs = content.split(regex);
		List<String> list = new ArrayList<String>();
		for (String str : strs) {
			list.add(StringUtils.trim(str));
		}
		return list;
	}

	public static <T> List<T> toList(Collection<T> collection) {
		List<T> list = new ArrayList<T>();
		if (collection != null) {
			list.addAll(collection);
		}
		return list;
	}

	@SafeVarargs
	public static <T> List<T> arrayToList(T... obj) {
		if (obj == null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (T t : obj) {
			list.add(t);
		}
		return list;
	}

	@SafeVarargs
	public static <T> Set<T> arrayToSet(T... obj) {
		if (obj == null) {
			return null;
		}
		Set<T> set = new LinkedHashSet<T>();
		for (T t : obj) {
			if (t != null) {
				set.add(t);
			}
		}
		return set;
	}

	public static String connectBy(String[] array, String split) {
		if (array == null) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				str.append(split);
			}
			str.append(array[i]);
		}
		return str.toString();
	}

	public static String connectBy(String[] array, String split, int limit) {
		if (array == null) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < array.length && i < limit - 1; i++) {
			if (i > 0) {
				str.append(split);
			}
			str.append(array[i]);
		}
		if (array.length > limit) {
			str.append(",...");
		}
		return str.toString();
	}

	public static String connectBy(List<?> collection, String split) {
		if (collection == null) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < collection.size(); i++) {
			if (i > 0) {
				str.append(split);
			}
			str.append(collection.get(i));
		}
		return str.toString();
	}

	public static String[] distinctArray(String[] t) {
		if (t == null) {
			return null;
		}
		Set<String> set = new LinkedHashSet<String>();
		for (String e : t) {
			set.add(e);
		}
		return set.toArray(new String[0]);
	}

	@SuppressWarnings("serial")
	public static <T> List<List<T>> split(final List<T> collection, int bufferSize) {
		if (collection == null || collection.size() == 0) {
			return new ArrayList<List<T>>();
		}
		if (bufferSize > 0) {
			List<List<T>> list = new ArrayList<List<T>>();
			List<T> temp = null;
			for (int i = 0; i < collection.size(); i++) {
				if (i % bufferSize == 0) {
					temp = new ArrayList<T>();
					list.add(temp);
				}
				if (temp != null) {
					temp.add(collection.get(i));
				}
			}
			return list;
		} else {
			return new ArrayList<List<T>>() {
				{
					add(collection);
				}
			};
		}
	}

	public static <T> T getFirst(Collection<T> collection) {
		if (collection != null && collection.size() > 0) {
			return collection.iterator().next();
		}
		return null;
	}

	public static <T> T getLast(List<T> collection) {
		if (collection != null && collection.size() > 0) {
			return collection.get(collection.size() - 1);
		}
		return null;
	}

	public static <T> T getFirst(Map<String, T> map) {
		if (map != null && map.size() > 0) {
			return getFirst(map.values());
		}
		return null;
	}

	public static <T> int size(Collection<T> collection) {
		if (collection != null) {
			return collection.size();
		}
		return 0;

	}

	public static <T> void addAll(Collection<T> c1, Collection<T> c2) {
		if (c1 != null && c2 != null) {
			c1.addAll(c2);
		}
	}

	public static <T> T find(Collection<T> c, T t) {
		for (T ele : c) {
			if (ele.equals(t)) {
				return ele;
			}
		}
		return null;
	}

	public static Map<String, Object> singletonMap(String key, Object value) {
		return Collections.singletonMap(key, value);
	}

	public static <T> T valueOfMaxKey(Map<Double, T> map) {
		if (map == null || map.size() < 1) {
			return null;
		}
		double d = Double.MIN_VALUE;
		for (double k : map.keySet()) {
			if (k > d) {
				d = k;
			}
		}
		return map.get(d);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection coll) {
		return coll == null || coll.size() < 1;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection coll) {
		return coll != null && coll.size() > 0;
	}

}
