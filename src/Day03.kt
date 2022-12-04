fun main() {
    val charToPriority = (('a'..'z') + ('A'..'Z')).withIndex().associateBy({ it.value }, { it.index + 1 })

    data class Item(val char: Char, val priority: Int = charToPriority.getValue(char))

    data class Compartment(val text: String, val items: List<Item> = text.map(::Item))

    data class Rucksack(val first: Compartment, val second: Compartment) {
        val uniqueItems get() = (first.items + second.items).toSet()

        val intersection get() = first.items.intersect(second.items.toSet())

        val intersectionSum get() = intersection.sumOf { it.priority }
    }

    fun part1(input: List<String>) = input
        .filter { it.isNotBlank() }
        .map { line ->
            val middleIndex = (line.length / 2)
            Rucksack(
                first = Compartment(line.substring(0, middleIndex)),
                second = Compartment(line.substring(middleIndex, line.length)),
            )
        }

    fun part2(input: List<String>) = part1(input)
        .chunked(3)
        .map { it.fold(it.first().uniqueItems) { acc, rucksack -> acc.intersect(rucksack.uniqueItems) }.single() }

    val testInput = readTestInput("Day03")
    // p1
    with(part1(testInput)) {
        with(this[0]) {
            check(first.text == "vJrwpWtwJgWr")
            check(second.text == "hcsFMMfFFhFp")
            check(intersection == setOf(Item('p')))
            check(intersectionSum == 16)
        }

        with(this[1]) {
            check(first.text == "jqHRNqRjqzjGDLGL")
            check(second.text == "rsFMfFZSrLrFZsSL")
            check(intersection == setOf(Item('L')))
            check(intersectionSum == 38)
        }

        with(this[2]) {
            check(first.text == "PmmdzqPrV")
            check(second.text == "vPwwTWBwg")
            check(intersection == setOf(Item('P')))
            check(intersectionSum == 42)
        }

        with(this[3]) {
            check(intersection == setOf(Item('v')))
            check(intersectionSum == 22)
        }

        with(this[4]) {
            check(intersection == setOf(Item('t')))
            check(intersectionSum == 20)
        }

        with(this[5]) {
            check(intersection == setOf(Item('s')))
            check(intersectionSum == 19)
        }

        check(sumOf { it.intersectionSum } == 157)
    }
    // p2
    with(part2(testInput)) {
        with(this[0]) {
            check(this == Item('r'))
            check(priority == 18)
        }

        with(this[1]) {
            check(this == Item('Z'))
            check(priority == 52)
        }

        check(sumOf { it.priority } == 70)
    }

    val input = readInput("Day03")
    println(part1(input).sumOf { it.intersectionSum })
    println(part2(input).sumOf { it.priority })
}
