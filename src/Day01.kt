fun main() {
    fun part1(input: List<String>) = buildList {
        val inventory = hashSetOf<Int>()
        for (line in input) {
            if (line.isNotBlank()) {
                inventory += line.toInt()
            } else {
                add(inventory.sum())
                inventory.clear()
            }
        }
        if (inventory.isNotEmpty()) add(inventory.sum())
    }

    fun part2(input: List<String>) = part1(input)
        .sortedDescending()
        .take(3)

    val testInput = readInput("Day01_Test")
    // p1
    check(part1(testInput) == listOf(6_000, 4_000, 11_000, 24_000, 10_000))
    check(part1(testInput).max() == 24_000)
    // p2
    check(part2(testInput) == listOf(24_000, 11_000, 10_000))
    check(part2(testInput).sum() == 45_000)

    val input = readInput("Day01")
    println(part1(input).max())
    println(part2(input).sum())
}
