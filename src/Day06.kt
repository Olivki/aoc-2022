fun main() {
    data class DataChunk(val chunk: String, val lastCharacter: Int)

    fun String.findUniqueDataChunk(chunkSize: Int): DataChunk = asSequence()
        .withIndex()
        .windowed(chunkSize) { chars ->
            val chunk = chars.joinToString(separator = "") { it.value.toString() }
            DataChunk(chunk, chars.last().index + 1)
        }
        .first { (chunk, _) -> chunk.toSet().size == chunk.length }

    fun part1(input: List<String>) = input.map { it.findUniqueDataChunk(4) }

    fun part2(input: List<String>) = input.map { it.findUniqueDataChunk(14) }

    val testInput = readTestInput("Day06")
    // p1
    with(part1(testInput)) {
        check(this[0].lastCharacter == 7)
        check(this[1].lastCharacter == 5)
        check(this[2].lastCharacter == 6)
        check(this[3].lastCharacter == 10)
        check(this[4].lastCharacter == 11)
    }
    // p2
    with(part2(testInput)) {
        check(this[0].lastCharacter == 19)
        check(this[1].lastCharacter == 23)
        check(this[2].lastCharacter == 23)
        check(this[3].lastCharacter == 29)
        check(this[4].lastCharacter == 26)
    }

    val input = readInput("Day06")
    println(part1(input).single().lastCharacter)
    println(part2(input).single().lastCharacter)
}
