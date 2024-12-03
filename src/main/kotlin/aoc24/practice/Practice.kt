package se.hjonas.aoc24.practice

import se.hjonas.aoc24.utils.readInputLines

fun main() {
    val rows = readInputLines("exercise")

    val numbers = rows.map(::rowToNumber)
    println("result: ${numbers.sum()}")
}

fun rowToNumber(row: String): Int {
    val digitsInRow = mutableListOf<Int>()
    row.forEachIndexed { index, char ->
        if (char.isDigit()) {
            digitsInRow.add(char.digitToInt())
        } else {
            Digit
                .entries
                .map { it.name }
                .firstOrNull { nbr ->
                    nbr.regionMatches(0, row, index, nbr.length)
                }?.let {
                    DigitValueOfOrNull(it)?.ordinal?.plus(1)
                }?.let {
                    digitsInRow.add(it)
                }
        }
    }

    return if (digitsInRow.size == 1) {
        "${digitsInRow.first()}${digitsInRow.first()}".toInt()
    } else {
        "${digitsInRow.first()}${digitsInRow.last()}".toInt()
    }
}

enum class Digit {
    one, two, three, four, five, six, seven, eight, nine;
}

fun DigitValueOfOrNull(value: String) = try {
    Digit.valueOf(value)
} catch (e: Exception) {
    null
}

fun Digit.toNormalizedString() = (this.ordinal + 1).toString()
