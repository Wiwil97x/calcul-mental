import kotlin.random.Random
import kotlin.system.exitProcess

fun main() {
    var score = 0
    var bonnesReponses = 0
    var niveau = 1
    var vies = 3

    println("=== Bienvenue au jeu de calcul mental ===")
    println("Règle : 3 vies. Tu montes de niveau après 2 puis 4 bonnes réponses.\n")

    while (vies > 0) {
        val (question, bonneReponse) = genererOperation(niveau)

        println("Niveau $niveau | Score : $score | Vies restantes : $vies")
        print("$question = ")

        val input = readLine()
        val reponse = input?.toIntOrNull()

        if (reponse == null) {
            println("Entrée invalide. Tu dois entrer un nombre entier.\n")
            continue
        }

        if (reponse == bonneReponse) {
            println("Bonne réponse !\n")
            bonnesReponses++
            score += when (niveau) {
                1 -> 1
                2 -> 2
                else -> 3
            }

            // Passage de niveau
            if (bonnesReponses == 2) niveau = 2
            if (bonnesReponses == 4) niveau = 3

            println("Score actuel : $score\n")
        } else {
            vies--
            println("❌ Mauvaise réponse. La bonne était $bonneReponse.")
            println("Vies restantes : $vies\n")
            if (vies == 0) break
            // Réaffiche la même question
            continue
        }
    }

    println("💀 Game Over. Ton score final est : $score")
    println("Merci d’avoir joué !")
    exitProcess(0)
}

fun genererOperation(niveau: Int): Pair<String, Int> {
    return when (niveau) {
        1 -> {
            val a = Random.nextInt(1, 100)
            val b = Random.nextInt(1, 100)
            val question = "$a + $b"
            val resultat = a + b
            question to resultat
        }
        2 -> {
            val a = Random.nextInt(100, 1000)
            val b = Random.nextInt(1, 100)
            val c = Random.nextInt(1, 100)
            val question = "$a - $b + $c"
            val resultat = a - b + c
            question to resultat
        }
        else -> {
            val a = Random.nextInt(1, 10)
            val b = Random.nextInt(10, 100)
            val question = "$a * $b"
            val resultat = a * b
            question to resultat
        }
    }
}
