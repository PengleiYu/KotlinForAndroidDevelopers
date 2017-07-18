package com.example.weatherapp

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testSumOperator() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        //any
        assertTrue(list.any { it % 2 == 0 })
        assertFalse(list.any { it > 10 })
        //all
        assertTrue(list.all { it < 10 })
        assertFalse(list.all { it % 2 == 0 })
        //count
        assertEquals(3, list.count { it % 2 == 0 })
        //fold
        assertEquals(25, list.fold(4) { total, next -> total + next })
        assertEquals(25, list.foldRight(4) { total, next -> total + next })
        //foreach
        list.forEach { println(it) }
        list.forEachIndexed { index, i -> println("$index => $i") }
        //max
        assertEquals(6, list.max())
        assertEquals(1, list.maxBy { -it })
        //min
        assertEquals(1, list.min())
        assertEquals(6, list.minBy { -it })
        //none
        assertTrue(list.none { it % 7 == 0 })
        //reduce
        assertEquals(21, list.reduce { acc, i -> acc + i })
        assertEquals(21, list.reduceRight { acc, i -> acc + i })
        //sum
        assertEquals(21, list.sum())
        assertEquals(3, list.sumBy { it % 2 })
    }

    @Test
    fun testFilterOperator() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        //drop
        assertEquals(listOf(5, 6), list.drop(4))
        assertEquals(listOf(1, 2), list.dropLast(4))
        assertEquals(listOf(3, 4, 5, 6), list.dropWhile { it < 3 })
        assertEquals(listOf(1, 2, 3, 4), list.dropLastWhile { it > 4 })
        //filter
        assertEquals(listOf(2, 4, 6), list.filter { it % 2 == 0 })
        assertEquals(listOf(1, 3, 5), list.filterNot { it % 2 == 0 })
        val listWithNull = listOf(1, 2, null, null)
        assertEquals(listOf(1, 2), listWithNull.filterNotNull())
        //slice
        assertEquals(listOf(2, 3, 4), list.slice(1..3))
        //take
        assertEquals(listOf(1, 2), list.take(2))
        assertEquals(listOf(1, 2, 3), list.takeWhile { it < 4 })
    }

    @Test
    fun testMapOperator() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        //flatMap
        assertEquals(listOf(1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7), list.flatMap { listOf(it, it + 1) })
        //groupBy
        assertEquals(mapOf("odd" to listOf(1, 3, 5), "even" to listOf(2, 4, 6)),
                list.groupBy { if (it % 2 == 0) "even" else "odd" })
        //map
        assertEquals(listOf(2, 4, 6, 8, 10, 12), list.map { it * 2 })
        //mapIndexed
        assertEquals(listOf(0, 2, 6, 12, 20, 30), list.mapIndexed { index, i -> index * i })
        val listWithNull = listOf(1, 2, 3, null, null)
        assertEquals(listOf(2, 4, 6), listWithNull.mapNotNull { it?.times(2) })//注意：对结果过滤，而不是原始数据
    }

    @Test
    fun testElementOperator() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        //contains
        assertTrue(list.contains(2))
        //elementAt
        assertEquals(2, list.elementAt(1))
        assertEquals(30, list.elementAtOrElse(10) { it * 3 })
        assertNull(list.elementAtOrNull(10))
        //first,last
        assertEquals(2, list.first { it % 2 == 0 })
        assertNull(list.firstOrNull { it % 7 == 0 })
        assertEquals(6, list.last())
        //index
        assertEquals(3, list.indexOf(4))
        assertEquals(1, list.indexOfFirst { it % 2 == 0 })
        assertEquals(5, list.indexOfLast { it % 2 == 0 })
        //single
        assertEquals(5, list.single { it % 5 == 0 })
        assertNull(list.singleOrNull { it % 7 == 0 })
    }

    @Test
    fun TestProduceOperator() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        val listRepeated = listOf(2, 2, 3, 4, 5, 5, 6)
        //merge操作符作废了,使用zip代替
//        assertEquals(listOf(3,4,6,8,10,11),list.merge())
        assertEquals(listOf(3, 4, 6, 8, 10, 11),
                list.zip(listRepeated) { left, right -> left + right })
        //zip
        assertEquals(listOf(Pair(1, 7), Pair(2, 8)), list.zip(listOf(7, 8)))
        //unzip
        assertEquals(Pair(listOf(1, 2), listOf(7, 8)), listOf(Pair(1, 7), Pair(2, 8)).unzip())
        //plus,因为函数名称的原因重载了操作符+
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8), list + listOf(7, 8))
        //partition
        assertEquals(Pair(listOf(2, 4, 6), listOf(1, 3, 5)), list.partition { it % 2 == 0 })
    }

    @Test
    fun testSortOperator() {
        val unsortedList = listOf(3, 2, 7, 5)
        //reverse
        assertEquals(listOf(5, 7, 2, 3), unsortedList.reversed())
        //sort
        assertEquals(listOf(2, 3, 5, 7), unsortedList.sorted())
        assertEquals(listOf(3, 7, 2, 5), unsortedList.sortedBy { it % 3 })
        //sortDescend
        assertEquals(listOf(7, 5, 3, 2), unsortedList.sortedDescending())
        assertEquals(listOf(2, 5, 7, 3), unsortedList.sortedByDescending { it % 3 })
    }
}
