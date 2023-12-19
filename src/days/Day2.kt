package days

class Day2 : Day(2) {

    private fun parseGames(input: List<String>) : List<List<Map<String, Int>>> {
        return input.map { line ->
            line.substringAfter(": ").split("; ").map { round ->
                round.split(", ").associate { str ->
                    val (num, color) = str.split(" ")
                    color to num.toInt()
                }
            }
        }
    }
    override fun part1() {
        val games = parseGames(inputList)

        val possible = games.withIndex().filter { (_, game) ->
            game.all { round ->
                (round["red"] ?: 0) <= 12 &&
                        (round["green"] ?: 0) <= 13 &&
                        (round["blue"] ?: 0) <= 14
            }
        }.sumOf { (i)-> i + 1 }

        println(possible)
    }

    override fun part2() {
        val games = parseGames(inputList)

        val result = games.sumOf { game ->
            listOf("red", "green", "blue").map { color ->
                game.maxOf { round ->
                    round[color] ?: 0 }.toLong()
            }.reduce { acc, num ->
                acc * num
            }
        }

        println(result)
    }
}