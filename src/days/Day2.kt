package days

class Day2 : Day(2) {
    val colors = listOf("red", "green", "blue")

    data class Game (
        val id: Int,
        val rounds: List<HashMap<String, Int>>
    )

    fun parseLine(line: String) : Game {
        val idRegex = """^(?:Game )(?<id>\d*)""".toRegex()
        val redRegex = """(?<num>\d*)( red)+""".toRegex()
        val greenRegex = """(?<num>\d*)( green)+""".toRegex()
        val blueRegex = """(?<num>\d*)( blue)+""".toRegex()

        val idResult = idRegex.find(line)
        val id = idResult?.groups?.get("id")?.value?.toInt() ?: 0

        val rounds = line.substring(line.indexOf(": ") + 2).split("; ")
        val formattedRounds = rounds.map { round ->
            val red = redRegex.find(round)?.groups?.get("num")?.value?.toInt() ?: 0
            val green = greenRegex.find(round)?.groups?.get("num")?.value?.toInt() ?: 0
            val blue = blueRegex.find(round)?.groups?.get("num")?.value?.toInt() ?: 0

            hashMapOf(
                "red" to red,
                "green" to green,
                "blue" to blue
            )
        }

        return Game(id = id, rounds = formattedRounds)
    }

    fun isGamePossible(game: Game, expected: HashMap<String, Int>) : Boolean {
        val returnable = game.rounds.map { round ->
            val results = colors.map { col ->
                (round[col] ?: 0) > (expected[col] ?: 0)
            }.filter { v -> v }

            results.isEmpty()
        }.reduce { acc, possible ->
            acc && possible
        }
        return returnable
    }

    fun getLeastCubesNecessary(game: Game): HashMap<String, Int> {
        val numPerColor = hashMapOf(
            "red" to 0,
            "green" to 0,
            "blue" to 0
        )

        game.rounds.forEach { round ->
            colors.forEach { col ->
                if ((round[col] ?: 0) > (numPerColor[col] ?: 0)) {
                    numPerColor[col] = (round[col] ?: 0)
                }
            }
        }

        return numPerColor
    }
    override fun part1() {
        val expectedCubes = hashMapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        val games = inputList.map { line ->
            parseLine(line)
        }

        val possible = games.map { game ->
            val gamePossible = isGamePossible(game, expectedCubes)
            if (gamePossible) game.id else 0
        }.reduce { acc, num ->
            acc + num
        }

        println(possible)
    }

    override fun part2() {
        val games = inputList.map { line ->
            parseLine(line)
        }

        val sumOfLeastCubes = games.map { game ->
            val cubes = getLeastCubesNecessary(game)
            cubes.map { (_, v) ->
                v
            }.reduce { acc, num ->
                acc * num
            }
        }.reduce { acc, num ->
            acc + num
        }

        println(sumOfLeastCubes)
    }
}