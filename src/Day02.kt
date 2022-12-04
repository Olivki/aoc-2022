import RoundResult.DRAW
import RoundResult.LOSS
import RoundResult.WIN
import RpsShape.PAPER
import RpsShape.ROCK
import RpsShape.SCISSORS

fun main() {
    val opponentShapeMap = mapOf("A" to ROCK, "B" to PAPER, "C" to SCISSORS)
    val desiredShapeMap = mapOf("X" to ROCK, "Y" to PAPER, "Z" to SCISSORS)
    val desiredResultMap = mapOf("X" to LOSS, "Y" to DRAW, "Z" to WIN)

    fun List<String>.splitInputs() = asSequence()
        .filter { it.isNotBlank() }
        .map { it.trim().split(' ') }

    fun part1(input: List<String>) = input
        .splitInputs()
        .map { (a, b) -> opponentShapeMap.getValue(a) to desiredShapeMap.getValue(b) }
        .map { (opponent, player) ->
            val result = when (opponent) {
                player.strongAgainst -> WIN
                player -> DRAW
                else -> LOSS
            }
            (player.ordinal + 1) + result.score
        }
        .toList()

    fun part2(input: List<String>) = input
        .splitInputs()
        .map { (a, b) -> opponentShapeMap.getValue(a) to desiredResultMap.getValue(b) }
        .map { (opponent, desiredResult) ->
            val shapeToPlay = when (desiredResult) {
                LOSS -> opponent.strongAgainst
                DRAW -> opponent
                WIN -> opponent.weakAgainst
            }
            (shapeToPlay.ordinal + 1) + desiredResult.score
        }
        .toList()

    val testInput = readTestInput("Day02")
    // p1
    with(part1(testInput)) {
        check(this[0] == 8)
        check(this[1] == 1)
        check(this[2] == 6)
        check(sum() == 15)
    }
    // p2
    with(part2(testInput)) {
        check(this[0] == 4)
        check(this[1] == 1)
        check(this[2] == 7)
        check(sum() == 12)
    }

    val input = readInput("Day02")
    println(part1(input).sum())
    println(part2(input).sum())
}

private enum class RoundResult(val score: Int) {
    LOSS(0),
    DRAW(3),
    WIN(6),
}

private enum class RpsShape(strongAgainst: String, weakAgainst: String) {
    ROCK("scissors", "paper"),
    PAPER("rock", "scissors"),
    SCISSORS("paper", "rock");

    val strongAgainst: RpsShape by lazy { RpsShape.valueOf(strongAgainst.uppercase()) }
    val weakAgainst: RpsShape by lazy { RpsShape.valueOf(weakAgainst.uppercase()) }
}