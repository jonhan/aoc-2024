package se.hjonas.aoc24.utils


fun List<String>.toInts(): List<Int> = this.map { it.toInt() }
fun List<String>.toLongs(): List<Long> = this.map { it.toLong() }

data class Coordinate(val x: Int, val y: Int)
