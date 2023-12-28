package days

class Day3 : Day(3) {
    private val numRegex = """\d*""".toRegex()
    private val symbolRegex = """[^A-Za-z0-9\s.]""".toRegex()
    private val gearRegex = """\*""".toRegex()

    private fun isCharacterMatch(c: String, r: Regex) : Boolean {
        return r.find(c) != null
    }

    private fun getMatches(input: List<String>, pattern: Regex) : List<Triple<String, Int, IntRange>> {
        return input.mapIndexed { idx, line ->
            val matches = pattern.findAll(line).toList().map { match ->
                Triple(match.value, idx, match.range)
            }.filter {
                it.first.isNotEmpty()
            }
            matches
        }.flatten()
    }

    private fun getComparedMatches(input: List<String>, primaryRegex: Regex, secondaryRegex: Regex) : List<Pair<String, MutableList<Triple<String, Int, IntRange>>>> {
        return getMatches(input, primaryRegex).map {
            val (num, line, range) = it
            val secondaryMatches: MutableList<Triple<String, Int, IntRange>> = mutableListOf()
            val start = if (range.first > 0) range.first - 1 else range.first
            val end = if (range.last < input[line].length - 1) range.last + 1 else range.last

            if (line - 1 >= 0) {
                // check previous line
                for (i in start..end) {
                    if (isCharacterMatch("${input[line - 1][i]}", secondaryRegex)) {
                        secondaryMatches.add(Triple("${input[line - 1][i]}", line - 1, i..i))
                    }
                }
            }

            if (line + 1 <= input[line].length - 1) {
                for (i in start..end) {
                    if (isCharacterMatch("${input[line + 1][i]}", secondaryRegex)) {
                        secondaryMatches.add(Triple("${input[line + 1][i]}", line + 1, i..i))
                    }
                }
            }

            if (isCharacterMatch("${input[line][start]}", secondaryRegex)) {
                secondaryMatches.add(Triple("${input[line][start]}", line, start..start))
            }
            if (isCharacterMatch("${input[line][end]}", secondaryRegex)) {
                secondaryMatches.add(Triple("${input[line][end]}", line, end..end))
            }

            Pair(num, secondaryMatches)
        }
    }

    override fun part1() {
        val result = getComparedMatches(testInputList, numRegex, symbolRegex).filter {
            it.second.size > 0
        }.sumOf {
            it.first.toInt()
        }
        println(result)
    }
    override fun part2() {
        class Gear (var slot: Pair<Int, Int>, var partNos: MutableList<Int> = mutableListOf(), var ratio: Int)

        val gearList: MutableList<Gear> = mutableListOf()
        getMatches(inputList, gearRegex).map {
             Pair(it.second, it.third.first)
        }.forEach { g ->
            gearList.add(Gear(slot = g, ratio = 0))
        }

        getComparedMatches(inputList, numRegex, gearRegex).filter {
            it.second.size > 0
        }.forEach { (str, list) ->
            list.forEach {
                gearList.find { g ->
                    g.slot.first == it.second &&
                    g.slot.second == it.third.first
                }?.partNos?.add(str.toInt())
            }
        }
        val result = gearList.filter {
            it.partNos.size > 1
        }.map { g ->
            g.partNos.reduce { acc, num ->
                acc * num
            }
        }.sumOf {
            it
        }

         println(result)
    }

}