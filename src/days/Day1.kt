package days

class Day1 : Day(1) {
    override fun part1() {
        val sum = inputList.map {
            val digits = it.filter { it.isDigit() }.toList()
            "${digits[0]}${digits[digits.size - 1]}".toInt()
        }.reduce { acc, str ->
            acc + str
        }

        println(sum)
    }

    override fun part2() {

    }
}