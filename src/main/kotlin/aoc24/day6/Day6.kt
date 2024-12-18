package se.hjonas.aoc24.day6

import se.hjonas.aoc24.utils.Coordinate
import se.hjonas.aoc24.utils.readInputLines

enum class Direction(val offsetX: Int, val offsetY: Int) {
    Up(0, -1),
    Right(1, 0),
    Down(0, 1),
    Left(-1, 0)
}

fun Direction.moveRight() = Direction.entries[(ordinal + 1) % 4]

fun Char.toDirection(): Direction? = when (this) {
    '>' -> Direction.Right
    '<' -> Direction.Left
    '^' -> Direction.Up
    'v' -> Direction.Down
    else -> null
}

data class GuardPosition(val direction: Direction, val coordinate: Coordinate)

class MapGrid(rows: List<String>) {
    private var startPosition: GuardPosition? = null

    // true = obstacle exists
    private val cells: List<List<Boolean>> = List(rows.size) { rowIndex ->
        rows[rowIndex].mapIndexed { index, elem ->
            elem.toDirection()?.let {
                startPosition = GuardPosition(it, Coordinate(index, rowIndex))
            }
            elem == '#'
        }
    }

    fun isObstacleAt(coordinate: Coordinate): Boolean? = cells.getOrNull(coordinate.y)?.getOrNull(coordinate.x)
    fun getStartPosition(): GuardPosition = requireNotNull(startPosition)
}

fun main() {
    val input = readInputLines("day6")
    val grid = MapGrid(input)
    var position = grid.getStartPosition()
    val visitedPoints = mutableSetOf(position.coordinate)
    var canContinue = true

    while (canContinue) {
        var nextPoint = position.coordinate
        while (grid.isObstacleAt(nextPoint) == false) {
            position = position.copy(coordinate = nextPoint)
            visitedPoints.add(position.coordinate)

            nextPoint = Coordinate(position.coordinate.x + position.direction.offsetX, position.coordinate.y + position.direction.offsetY)
        }
        canContinue = grid.isObstacleAt(nextPoint) != null
        position = position.copy(direction = position.direction.moveRight())
    }

    println("result: ${visitedPoints.size}")
}

val testGrid = """
    ....#.....
    .........#
    ..........
    ..#.......
    .......#..
    ..........
    .#..^.....
    ........#.
    #.........
    ......#...
""".trimIndent().lines()
