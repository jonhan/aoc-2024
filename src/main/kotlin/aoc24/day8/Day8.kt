package aoc24.day8

import se.hjonas.aoc24.utils.Coordinate
import se.hjonas.aoc24.utils.readInputLines

private data class Antenna(val freq: Char, val coordinate: Coordinate)

val grid = readInputLines("day8")

fun Coordinate.isWithinGrid(): Boolean {
    return grid.getOrNull(y)?.getOrNull(x) != null
}

fun mapAntennas(): Set<Pair<Coordinate, Coordinate>> {
    val antennas = buildList {
        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, c ->
                if (c != '.') add(Antenna(c, Coordinate(colIndex, rowIndex)))
            }
        }
    }.groupBy(
        keySelector = { it.freq },
        valueTransform = { it.coordinate }
    )

    return buildSet {
        antennas.forEach { (_, points) ->
            points.subList(0, points.lastIndex).forEachIndexed { i, coord1 ->
                points.subList(i + 1, points.size).forEach { coord2 ->
                    add(coord1 to coord2)
                }
            }
        }
    }
}

fun part1() {
    val antiNodes = mutableSetOf<Coordinate>()

    mapAntennas().forEach { (coord1, coord2) ->
        val xDiff = coord2.x - coord1.x
        val yDiff = coord2.y - coord1.y
        var node1 = Coordinate(coord1.x - xDiff, coord1.y - yDiff)
        var node2 = Coordinate(coord2.x + xDiff, coord2.y + yDiff)

        if (node1.isWithinGrid()) antiNodes.add(node1)
        if (node2.isWithinGrid()) antiNodes.add(node2)
    }

    println("part1: ${antiNodes.size}")
}

fun part2() {
    val antiNodes = mutableSetOf<Coordinate>()

    mapAntennas().forEach { (coord1, coord2) ->
        antiNodes.add(coord1)
        antiNodes.add(coord2)
//                println("For coords $coord1, $coord2")
        val xDiff = coord2.x - coord1.x
        val yDiff = coord2.y - coord1.y
        var c1 = 1
        var c2 = 1
        var node1 = Coordinate(coord1.x - xDiff, coord1.y - yDiff)
        var node2 = Coordinate(coord2.x + xDiff, coord2.y + yDiff)

        while (node1.isWithinGrid()) {
            antiNodes.add(node1)
            c1++
            node1 = Coordinate(coord1.x - xDiff * c1, coord1.y - yDiff * c1)
        }

        while (node2.isWithinGrid()) {
            antiNodes.add(node2)
            c2++
            node2 = Coordinate(coord2.x + xDiff * c2, coord2.y + yDiff * c2)
        }
    }

    println("part2: ${antiNodes.size}")
}

fun main() {
    part1()
    part2()
}

private val test = """
    ............
    ........0...
    .....0......
    .......0....
    ....0.......
    ......A.....
    ............
    ............
    ........A...
    .........A..
    ............
    ............
""".trimIndent().lines()
