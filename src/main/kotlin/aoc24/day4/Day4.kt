package se.hjonas.aoc24.day4

import se.hjonas.aoc24.utils.readInputLines


fun main() {
    val rows = readInputLines("day4")
    var xmasCount = 0
    val directions = listOf(
        0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1
    )

    rows.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { charIndex, char ->
            if (char == 'X') {
                xmasCount += directions.map { (xDir, yDir) ->
                    buildString {
                        repeat(4) {
                            val x = charIndex + it * xDir
                            val y = rowIndex + it * yDir
                            append(rows.getOrElse(y) { "." }.getOrElse(x) { '.' })
                        }
                    }
                }.count { it == "XMAS" }
            }
        }
    }
    println("part1: $xmasCount")
    part2(rows)
}

fun part2(rows: List<String>) {
    val matches = listOf("MAS", "SAM")
    var xmasCount = 0

    rows.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { charIndex, char ->
            if (char == 'A') {
                val first = buildString {
                    listOf(-1 to 1, 0 to 0, 1 to -1).forEach { (x, y) ->
                        append(rows.getOrElse(rowIndex + y) { "." }.getOrElse(charIndex + x) { '.' })
                    }
                }
                val second = buildString {
                    listOf(1 to 1, 0 to 0, -1 to -1).forEach { (x, y) ->
                        append(rows.getOrElse(rowIndex + y) { "." }.getOrElse(charIndex + x) { '.' })
                    }
                }
                if (first in matches && second in matches) xmasCount++
            }
        }
    }
    println("part2: $xmasCount")
}
