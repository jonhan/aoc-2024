package se.hjonas.aoc24.day6

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

data class Point(val x: Int, val y: Int)
data class GuardPosition(val direction: Direction, val point: Point)

class MapGrid(rows: List<String>) {
    private var startPosition: GuardPosition? = null

    // true = obstacle exists
    private val cells: List<List<Boolean>> = List(rows.size) { rowIndex ->
        rows[rowIndex].mapIndexed { index, elem ->
            elem.toDirection()?.let {
                startPosition = GuardPosition(it, Point(index, rowIndex))
            }
            elem == '#'
        }
    }

    fun isObstacleAt(point: Point): Boolean? = cells.getOrNull(point.y)?.getOrNull(point.x)
    fun getStartPosition(): GuardPosition = requireNotNull(startPosition)
}

fun main() {
    val input = readInputLines("day6")
    val grid = MapGrid(input)
    var position = grid.getStartPosition()
    val visitedPoints = mutableSetOf(position.point)
    var canContinue = true

    while (canContinue) {
        var nextPoint = position.point
        while (grid.isObstacleAt(nextPoint) == false) {
            position = position.copy(point = nextPoint)
            visitedPoints.add(position.point)

            nextPoint = Point(position.point.x + position.direction.offsetX, position.point.y + position.direction.offsetY)
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
