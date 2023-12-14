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
        val digits = str.filter { it.isDigit() }.toList()
        return "${digits[0]}${digits[digits.size - 1]}".toInt()
    }

    override fun part2() {
        val numStrings = mapOf(
            0 to "zero",
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine"
        )

        val sum = inputList.map { line ->
            val digits = line.filter { it.isDigit() }.toList()

            var firstDigit : String? = null
            var firstDigitIdx = 300 // something high
            var lastDigit : String? = null
            var lastDigitIdx = -1

            if (digits.isNotEmpty()) {
                firstDigit = "${digits[0]}"
                firstDigitIdx = line.indexOf(firstDigit)
                lastDigit = "${digits[digits.size - 1]}"
                lastDigitIdx = line.lastIndexOf(lastDigit)
            }

            numStrings.forEach { (k, v) ->
                val foundFirst = line.indexOf(v)
                val foundLast = line.lastIndexOf(v)

                if (foundFirst in 0..<firstDigitIdx) {
                    firstDigitIdx = foundFirst
                    firstDigit = "$k"
                }
                if (foundLast > lastDigitIdx && foundLast < line.length) {
                    lastDigitIdx = foundLast
                    lastDigit = "$k"
                }
            }
            val result = "$firstDigit$lastDigit".toInt()
            result
        }.reduce { acc, str ->
            acc + str
        }

        println(sum)
    }
}