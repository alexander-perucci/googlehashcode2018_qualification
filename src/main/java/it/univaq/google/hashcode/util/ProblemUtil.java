/*
 * googlehashcode2018_qualification - Copyright (C) 2018 404 Solution Not Found team's
 *
 * googlehashcode2018_qualification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *   
 * googlehashcode2018_qualification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *   
 * You should have received a copy of the GNU General Public License
 * along with googlehashcode2018_qualification.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.univaq.google.hashcode.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProblemUtil {

	public static long calculateScore() {
		return 0L;
	}

	public static String getActualTime() {
		return new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
	}

	public static String getActualTime(String separator) {
		return new SimpleDateFormat("HH" + separator + "mm" + separator + "ss.SSS").format(new Date());
	}
	
		public static <K, V extends Comparable<? super V>> Map<K, V> reverseSortByValue(Map<K, V> map, Map<K, V> map2) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				if ((o1.getValue()).compareTo(o2.getValue()) == 0){
					/*if (map2.get(o1.getKey()).compareTo(map2.get(o2.getKey())) == 0){
						return new Integer((((CacheServer)o2.getKey()).getSize()-((CacheServer)o2.getKey()).getUsedSpace())).compareTo(new Integer((((CacheServer)o1.getKey()).getSize()-((CacheServer)o1.getKey()).getUsedSpace())));
					}else{
						return map2.get(o1.getKey()).compareTo(map2.get(o2.getKey()));
					}*/
					return map2.get(o1.getKey()).compareTo(map2.get(o2.getKey()));
				}else{
					return (o2.getValue()).compareTo(o1.getValue());
				}
			}
		});
		
		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
