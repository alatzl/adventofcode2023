package days

class Day4: Day(4){
    private fun parseInput(input: List<String>): List<Pair<List<String>, List<String>>> {
        return input.map {
            val parts = it.split(": ")[1]
                .split(" | ")
                .map { p ->
                    p.split(" ")
                    .map { x -> x.trim() }
                    .filterNot { y -> y.isEmpty() }
                }
            Pair(parts[0], parts[1])
        }
    }
    override fun part1() {
        val parsed = parseInput(inputList)
        val result = parsed.map { card ->
            card.first.intersect(card.second.toSet()).map { it.toInt() }
            .fold(0) { acc, _ ->
                if (acc == 0) {
                    1
                } else {
                    acc + acc
                }
            }
        }.sum()
        println(result)
    }

    override fun part2() {
        TODO("Not yet implemented")
    }
}