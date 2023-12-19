package days

class Day1 : Day(1) {
    override fun part1() {
        val sum = inputList.map {
            getNumberFromString(it)
        }.reduce { acc, str ->
            acc + str
        }

        println(sum)
    }

    private fun getNumberFromString(str: String) : Int {
        val firstDigit = str.first { it.isDigit() }
        val lastDigit = str.last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    override fun part2() {
        val words = hashMapOf(
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        val digits = List(10) { "$it"}

        val sum = inputList.map { line ->
            val (firstWordIdx, firstWord) = line.findAnyOf(words.map { (k, _) -> k }) ?: (Int.MAX_VALUE to "not found")
            val (lastWordIdx, lastWord) = line.findLastAnyOf(words.map { (k, _) -> k }) ?: (Int.MIN_VALUE to "not found")
            val (firstDigitIdx, firstDigit) = line.findAnyOf(digits) ?: (Int.MAX_VALUE to "not found")
            val (lastDigitIdx, lastDigit) = line.findLastAnyOf(digits) ?: (Int.MIN_VALUE to "not found")

            var result = ""
            result += if (firstDigitIdx < firstWordIdx) firstDigit else words[firstWord]
            result += if (lastDigitIdx > lastWordIdx) lastDigit else words[lastWord]

            result.toInt()
        }.reduce { acc, str ->
            acc + str
        }

        println(sum)
    }
}