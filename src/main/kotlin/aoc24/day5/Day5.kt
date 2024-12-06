package se.hjonas.aoc24.day5

import se.hjonas.aoc24.utils.readInputLines
import se.hjonas.aoc24.utils.toInts

fun main() {
    val rules = readInputLines("day5", "rules")
    val updates = readInputLines("day5", "updates")

    val rulesMap = rules.groupBy {
        it.substringBefore("|")
    }.map { (key, value) ->
        key.toInt() to value.map { it.substringAfter("|").toInt() }
    }.toMap()

    fun checkLines(line: List<Int>): Boolean {
        return line.mapIndexed { index, item ->
            rulesMap[item].orEmpty().containsAll(line.takeLast(line.lastIndex - index))
        }.all { it }
    }

    val updatesAsLists = updates.asSequence()
        .map { it.split(",").toInts() }

    val correctUpdates = updatesAsLists
        .filter(::checkLines)

    //Part1
    val result1 = correctUpdates.sumOf {
        it[it.size / 2]
    }
    println("part1: $result1")

    //Part2
    val comparator = Comparator<Int> { left, right ->
        val rules = rulesMap[left].orEmpty()
        if (rules.contains(right)) 0 else -1
    }

    val incorrectUpdates = updatesAsLists.filter {
        it !in correctUpdates
    }.toList()

    val fixedUpdates = incorrectUpdates.map { line ->
        line.sortedWith(comparator).reversed()
    }

    println("part2: ${fixedUpdates.sumOf { it[it.size / 2] }}")
}
