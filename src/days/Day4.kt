package days

class Day4: Day(4) {
    class Card(
        val cardNo: Int,
        var wins: List<Int>,
        var have: List<Int>,
        var matches: MutableList<Int>? = mutableListOf<Int>()
    )

    private fun parseInput(input: List<String>): List<Card> {
        return input.map {
            val parts = it.split(": ")[1]
                .split(" | ")
                .map { p ->
                    p.split(" ")
                    .map { x -> x.trim() }
                    .filterNot { y -> y.isEmpty() }
                        .map { z -> z.toInt() }
                }
            val cardNum = it.split(": ")[0].split(" ").map { x -> x.trim() }.filterNot { y -> y.isEmpty() }[1]
//            println(cardNum)
            Card(cardNum.toInt(), parts[0], parts[1])
        }
    }

    override fun part1() {
        val parsed = parseInput(inputList)
        val result = parsed.map { card ->
            card.wins.intersect(card.have.toSet())
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
        val parsed = parseInput(testInputList)
        var result = parsed.map { card ->
            card.matches = card.wins.intersect(card.have.toSet()).toMutableList()
            card
//        }.filter { c ->
//            c.matches?.isNotEmpty() == true
//        }.map { idx, wins ->
//            for ()
        }.toMutableList()

        var additionalCards: MutableList<Card> = mutableListOf()
        for (i in 0..result.size) {
            val size = result[i].matches?.size ?: 0
            if (size > 0) {
                for (j in 1..size) {
                    result.add(result[i + j])
                }
            }
        }

//        println(additionalCards)
        println(result)

    }
}