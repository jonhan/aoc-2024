package se.hjonas.aoc24.day1

import se.hjonas.aoc24.utils.readInputLines
import kotlin.math.abs


fun main() {
    val rows = readInputLines("day1", "input")
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    rows.forEach { row ->
        val (left, right) = row.split("   ")
        leftList.add(left.toInt())
        rightList.add(right.toInt())
    }

    leftList.sort()
    rightList.sort()

    var result = 0
    leftList.forEachIndexed { index, nbr ->
        result += abs(leftList[index] - rightList[index])
    }

    val rightCounted = rightList.groupBy { it }

    val scores = leftList.map { nbr ->
        nbr * (rightCounted[nbr]?.size ?: 0)
    }

    print("scores: ${scores.sum()}")
}

