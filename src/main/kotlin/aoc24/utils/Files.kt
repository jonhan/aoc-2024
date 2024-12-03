package se.hjonas.aoc24.utils

import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.readText

fun getInputFile(packageName: String, inputFileName: String = "input"): File {
    return File("src/main/kotlin/aoc24/$packageName/$inputFileName").absoluteFile
}

fun readInputText(packageName: String, inputFileName: String = "input"): String {
    return File("src/main/kotlin/aoc24/$packageName/$inputFileName").absoluteFile.readText()
}

fun readInputLines(packageName: String, inputFileName: String = "input") =
    Path("src/main/kotlin/aoc24/$packageName/$inputFileName").readText().trim().lines()

