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
                xmasCount += directions.map { dir ->
                    buildString {
                        repeat(4) {
                            val x = charIndex + it * dir.first
                            val y = rowIndex + it * dir.second
                            append(rows.getOrElse(y) { "." }.getOrElse(x) { '.' })
                        }
                    }
                }.count { it == "XMAS" }
            }
        }
    }
    println("result: $xmasCount")
}
