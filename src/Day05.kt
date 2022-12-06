import java.util.LinkedList

fun main() {
    val excessiveWhitespace = """\s+""".toRegex()

    data class CraneData(val cranes: List<LinkedList<Char>>, val instructions: List<List<Int>>)

    fun parseInput(input: List<String>): CraneData {
        val separatorIndex = input.indexOfFirst { it.isBlank() }
        val cranes = run {
            val craneCount = input[separatorIndex - 1]
                .trim()
                .splitToSequence(excessiveWhitespace)
                .count()
            val cranes = MutableList<LinkedList<Char>>(craneCount) { LinkedList() }
            input
                .take(separatorIndex - 1)
                .map { line ->
                    line
                        .windowed(size = 3, step = 4)
                        .map { char -> char[1].takeIf { it != ' ' } }
                }
                .forEach { chars ->
                    chars.forEachIndexed { i, crate ->
                        if (crate != null) cranes[i].addLast(crate)
                    }
                }
            cranes
        }
        val instructions = input
            .drop(separatorIndex + 1)
            .takeWhile { it.isNotBlank() }
            .map { line ->
                line
                    .split(' ')
                    .filterIndexed { i, _ -> i % 2 != 0 }
                    .map(String::toInt)
            }
        return CraneData(cranes, instructions)
    }

    fun part1(input: List<String>): String {
        val (cranes, instructions) = parseInput(input)
        for ((amountToMove, from, to) in instructions) {
            val fromCrane = cranes[from - 1]
            val toCrane = cranes[to - 1]
            repeat(amountToMove) {
                toCrane.push(fromCrane.pop())
            }
        }
        return cranes.joinToString(separator = "") { it.first().toString() }
    }

    fun part2(input: List<String>): String {
        val (cranes, instructions) = parseInput(input)
        for ((amountToMove, from, to) in instructions) {
            val fromCrane = cranes[from - 1]
            val toCrane = cranes[to - 1]
            val cratesToMove = fromCrane.subList(0, amountToMove)
            toCrane.addAll(0, cratesToMove)
            repeat(amountToMove) {
                fromCrane.pop()
            }
        }
        return cranes.joinToString(separator = "") { it.first().toString() }
    }

    val testInput = readTestInput("Day05")
    // p1
    check(part1(testInput) == "CMZ")
    // p2
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
