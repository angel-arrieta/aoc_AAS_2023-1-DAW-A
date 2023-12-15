import java.io.File
import java.io.InputStream

fun main() {
    fun part1(lines: List<String>): MutableList<Int> {
        val valueslist: MutableList<Int> = mutableListOf()
        for (line in lines){
            val digitlist: MutableList<Char> = mutableListOf()
            for (character in line){
                val isLetter: Boolean = character.isLetter()
                if (!isLetter){
                    digitlist.add(character)
                    break
                }
            }
            val reverseLine = line.reversed()
            for (character in reverseLine){
                val isLetter: Boolean = character.isLetter()
                if (!isLetter){
                    digitlist.add(character)
                    break
                }
            }
            val stringDigits = digitlist.joinToString("")
            valueslist.add(stringDigits.toInt())
        }
        return valueslist
    }/*
    fun part2(input: List<String>): Int {
        return input.size
    }*/

    val inputStream: InputStream = File("src/Day01.txt").inputStream()
    val wholeFile: String = inputStream.bufferedReader().use { it.readText() }
    val linesList: List<String> = wholeFile.split("\n")
    val valuesList = part1(linesList)
    var calibrationSum = 0
    for (value in valuesList) {
        calibrationSum += value
    }
    println(calibrationSum)
}
