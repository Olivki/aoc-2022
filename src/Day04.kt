fun main() {
    fun parseRange(text: String): List<Int> {
        val (a, b) = text.split('-')
        return (a.toInt()..b.toInt()).toList()
    }

    fun parseInput(input: List<String>) = input
        .asSequence()
        .filter { it.isNotBlank() }
        .map { it.split(',') }
        .map { (a, b) -> parseRange(a) to parseRange(b) }

    fun part1(input: List<String>) = parseInput(input)
        .count { (a, b) -> a.containsAll(b) || b.containsAll(a) }

    fun part2(input: List<String>) = parseInput(input)
        .count { (a, b) -> a.any { it in b } || b.any { it in a } }

    val testInput = readTestInput("Day04")
    // p1
    check(part1(testInput) == 2)
    // p2
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
