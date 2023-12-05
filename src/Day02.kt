import java.io.File
import java.io.InputStream

fun countMatches(string: String, pattern: String): Int {
    return string.split(pattern)
        .dropLastWhile { it.isEmpty() }
        .toTypedArray().size - 1
}

fun divisorDePartidas(gamesList: List<String>): List<List<Map<String,Int>>> {
    val listedGames: MutableList<List<Map<String,Int>>>  = mutableListOf()
    for (game: String in gamesList) {
        val round: MutableList<( Map<String,Int> )> = mutableListOf()
        val plays = game.split(": ")
        val playsOfTheGame: String = plays[1]
        val playsList = playsOfTheGame.split("; ")
        for (movement in playsList) {
            val handTake: MutableMap<String, Int> = mutableMapOf()
            val commaAmount = countMatches(movement, ", ")
            if (commaAmount > 0) {
                val numbers_colors = movement.split(", ")
                for (number_color in numbers_colors) {
                    val numco = number_color.split(" ")
                    val number = numco[0].toInt()
                    val color = numco[1]
                    handTake.put(color, number)
                }
            } else {
                val numco = movement.split(" ")
                val number = numco[0].toInt()
                val color = numco[1]
                handTake.put(color, number)
            }
            round.add(handTake)
        }
        listedGames.add(round)
    }
    return listedGames
}

fun main(){

    val bag: Map<String,Int> = mapOf("red" to 12, "green" to 13, "blue" to 14)
    val inputStream: InputStream = File("src/Day02.txt").inputStream()
    val wholeFile: String = inputStream.bufferedReader().use { it.readText() }
    val gamesList = wholeFile.split("\n")
    val playableGamesList: List<List<Map<String,Int>>> = divisorDePartidas(gamesList)
    val idsPosibleGames: MutableSet<Int> = mutableSetOf()
    for (i in 1..playableGamesList.size) {
        for (movement in playableGamesList[i-1]){
            val out: MutableList<Int> = mutableListOf()
            for ((color,amount) in movement){
                val max = bag.get(color).toString().toInt()
                if (amount <= max){
                    idsPosibleGames += i
                } else {
                    idsPosibleGames -= i
                    out.add(1)
                    break
                } }
            if (out.size > 0) {
                break
            }
        }
    }
    var sumIDs = 0
    for (id in idsPosibleGames) {
        sumIDs += id
    }
    print(sumIDs)
}