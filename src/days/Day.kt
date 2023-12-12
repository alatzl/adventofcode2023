package days

import util.InputReader

abstract class Day(dayNumber: Int) {
    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber) }
    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber) }
    protected val testInputList: List<String> by lazy { InputReader.getInputAsList(dayNumber, true) }
    protected val testInputString: String by lazy { InputReader.getInputAsString(dayNumber, true) }

    abstract fun part1(): Any

    abstract fun part2(): Any
}