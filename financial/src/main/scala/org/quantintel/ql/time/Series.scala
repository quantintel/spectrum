/*
 * Copyright (c) 2014  Paul Bernard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Spectrum Finance is based in part on:
 *        QuantLib. http://quantlib.org/
 *
 */

package org.quantintel.ql.time

import java.util
import java.util.Map.Entry
import java.util.{NavigableMap, _}
import java.util.function.{BiConsumer, BiFunction, Function}


/**
 * @author Paul Bernard
 */
class Series[K, V](classK: Class[K], classV: Class[V]) extends NavigableMap[K, V] {

  val delegate : util.NavigableMap[K, V] = new TreeMap[K, V]()


  override def equals(o: Any) : Boolean = delegate.equals(o)

  override def hashCode : Int = delegate.hashCode()


  override def lowerEntry(key: K): Entry[K, V] = delegate.lowerEntry(key)

  override def descendingMap(): NavigableMap[K, V] = delegate.descendingMap()

  override def firstEntry(): Entry[K, V] = delegate.firstEntry()

  override def navigableKeySet(): NavigableSet[K] = delegate.navigableKeySet()

  override def subMap(fromKey: K, fromInclusive: Boolean, toKey: K, toInclusive: Boolean): NavigableMap[K, V] =
    delegate.subMap(fromKey, fromInclusive, toKey, toInclusive)

  override def subMap(fromKey: K, toKey: K): util.SortedMap[K, V] = delegate.subMap(fromKey, toKey)

  override def headMap(toKey: K, inclusive: Boolean): NavigableMap[K, V] = delegate.headMap(toKey, inclusive)

  override def headMap(toKey: K): util.SortedMap[K, V] = delegate.headMap(toKey)

  override def ceilingKey(key: K): K = delegate.ceilingKey(key)

  override def higherEntry(key: K): Entry[K, V] = delegate.higherEntry(key)

  override def ceilingEntry(key: K): Entry[K, V] = delegate.ceilingEntry(key)

  override def pollFirstEntry(): Entry[K, V] = delegate.pollFirstEntry()

  override def floorKey(key: K): K = delegate.floorKey(key)

  override def floorEntry(key: K): Entry[K, V] = delegate.floorEntry(key)

  override def pollLastEntry(): Entry[K, V] = delegate.pollLastEntry()

  override def descendingKeySet(): NavigableSet[K] = ???

  override def lastEntry(): Entry[K, V] = delegate.lastEntry()

  override def tailMap(fromKey: K, inclusive: Boolean): NavigableMap[K, V] = delegate.tailMap(fromKey, inclusive)

  override def tailMap(fromKey: K): util.SortedMap[K, V] = delegate.tailMap(fromKey)

  override def lowerKey(key: K): K = delegate.lowerKey(key)

  override def higherKey(key: K): K = delegate.higherKey(key)

  override def firstKey(): K = delegate.firstKey()

  override def comparator(): Comparator[_ >: K] = delegate.comparator()

  override def lastKey(): K = delegate.lastKey()

  override def values(): Collection[V] = delegate.values()

  override def entrySet(): Set[Entry[K, V]] = delegate.entrySet()

  override def keySet(): Set[K] = delegate.keySet()

  override def computeIfPresent(key: K, remappingFunction: BiFunction[_ >: K, _ >: V, _ <: V]): V
      = delegate.computeIfPresent(key, remappingFunction)

  override def forEach(action: BiConsumer[_ >: K, _ >: V]): Unit
    = delegate.forEach(action)

  override def replaceAll(function: BiFunction[_ >: K, _ >: V, _ <: V]): Unit
    = delegate.replaceAll(function)

  override def get(key: scala.Any): V = delegate.get(key)

  override def replace(key: K, oldValue: V, newValue: V): Boolean
    = delegate.replace(key, oldValue, newValue)

  override def replace(key: K, value: V): V
    = delegate.replace(key, value)

  override def put(key: K, value: V): V = delegate.put(key, value)

  override def merge(key: K, value: V, remappingFunction: BiFunction[_ >: V, _ >: V, _ <: V]): V
      = delegate.merge(key, value, remappingFunction)

  override def clear() = delegate.clear

  override def getOrDefault(key: scala.Any, defaultValue: V): V
      = delegate.getOrDefault(key, defaultValue)

  override def size(): Int = delegate.size()

  override def remove(key: scala.Any): V = delegate.remove(key)

  override def remove(key: scala.Any, value: scala.Any): Boolean
      = delegate.remove(key, value)

  override def putIfAbsent(key: K, value: V): V
      = delegate.putIfAbsent(key, value)

  override def containsKey(key: scala.Any): Boolean = delegate.containsKey(key)

  override def containsValue(value: scala.Any): Boolean = delegate.containsValue(value)

  override def isEmpty: Boolean = delegate.isEmpty()

  override def compute(key: K, remappingFunction: BiFunction[_ >: K, _ >: V, _ <: V]): V
        = delegate.compute(key, remappingFunction)

  override def putAll(m: Map[_ <: K, _ <: V]): Unit = delegate.putAll(m)

  override def computeIfAbsent(key: K, mappingFunction: Function[_ >: K, _ <: V]): V
      = delegate.computeIfAbsent(key, mappingFunction)
}
