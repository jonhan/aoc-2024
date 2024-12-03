package se.hjonas.aoc24.day2

import se.hjonas.aoc24.utils.readInputLines
import se.hjonas.aoc24.utils.toInts
import kotlin.math.abs


fun main() {
    val rows = readInputLines("day2", "input")
    val reports = rows.map { it.split(" ").toInts() }
    val result = reports.map(::checkRow).count { it }

    println("Result 1: $result")

    part2(reports)
}

fun part2(reports: List<List<Int>>) {

    val result = reports.map { report ->
        checkRow(report) ||
            report.indices.any { idx ->
                checkRow(report.filterIndexed { index, _ -> idx != index })
            }
    }.count { it }

    println("result 2: $result")
}

fun checkRow(row: List<Int>): Boolean {
    val sorted = row.sorted()
    return (row == sorted || row == sorted.reversed()) && row.windowed(2).all { abs(it[0] - it[1]) in 1..3 }
}
