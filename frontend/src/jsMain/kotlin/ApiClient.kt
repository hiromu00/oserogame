import web.http.fetch
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.coroutines.*
import org.w3c.fetch.RequestInit
import kotlin.js.json

@Serializable
data class GameStateDto(
    val board: List<List<String>>,
    val currentPlayer: String,
    val gameOver: Boolean,
    val winner: String?
)

@Serializable
data class MoveRequestDto(val x: Int, val y: Int)

object ApiClient {
    private const val BASE_URL = "http://localhost:8080/api/game"

    suspend fun getGameState(): GameStateDto {
        val response = fetch("$BASE_URL/state").await()
        return Json.decodeFromString(response.text().await())
    }

    suspend fun makeMove(x: Int, y: Int): GameStateDto {
        val body = Json.encodeToString(MoveRequestDto(x, y))
        val response = fetch("$BASE_URL/move", RequestInit(
            method = "POST",
            headers = json("Content-Type" to "application/json"),
            body = body
        )).await()
        return Json.decodeFromString(response.text().await())
    }

    suspend fun resetGame(): GameStateDto {
        val response = fetch("$BASE_URL/reset", RequestInit(method = "POST")).await()
        return Json.decodeFromString(response.text().await())
    }
}
