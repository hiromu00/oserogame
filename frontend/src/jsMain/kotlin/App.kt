import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.button
import csstype.*
import emotion.react.css
import kotlinx.coroutines.*

val App = FC<Props> {
    var gameState by useState<GameStateDto?>(null)
    val scope = MainScope()

    useEffectOnce {
        scope.launch {
            gameState = ApiClient.getGameState()
        }
    }

    val onSquareClick = { x: Int, y: Int ->
        scope.launch {
            gameState = ApiClient.makeMove(x, y)
        }
    }

    val onReset = {
        scope.launch {
            gameState = ApiClient.resetGame()
        }
    }

    div {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = AlignItems.center
            fontFamily = string("sans-serif")
            backgroundColor = Color("#2c3e50")
            color = Color("#ecf0f1")
            minHeight = 100.vh
            padding = 20.px
        }

        h1 { +"Othello Web (Kotlin + Java)" }

        gameState?.let { state ->
            p {
                +"Current Player: ${state.currentPlayer}"
            }

            if (state.gameOver) {
                div {
                    css {
                        fontSize = 2.rem
                        color = Color("#e74c3c")
                        margin = 10.px
                    }
                    +"Game Over! Winner: ${state.winner ?: "Draw"}"
                }
            }

            // Board
            div {
                css {
                    display = Display.grid
                    gridTemplateColumns = repeat(8, 50.px.fractional)
                    gridTemplateRows = repeat(8, 50.px.fractional)
                    gap = 2.px
                    backgroundColor = Color("#27ae60")
                    border = Border(4.px, LineStyle.solid, Color("#2ecc71"))
                    padding = 5.px
                }

                for (x in 0 until 8) {
                    for (y in 0 until 8) {
                        div {
                            key = "$x-$y"
                            css {
                                width = 50.px
                                height = 50.px
                                backgroundColor = Color("#2ecc71")
                                display = Display.flex
                                justifyContent = JustifyContent.center
                                alignItems = AlignItems.center
                                cursor = Cursor.pointer
                                hover {
                                    backgroundColor = Color("#58d68d")
                                }
                            }
                            onClick = { onSquareClick(x, y) }

                            val piece = state.board[x][y]
                            if (piece != "EMPTY") {
                                div {
                                    css {
                                        width = 40.px
                                        height = 40.px
                                        borderRadius = 50.pct
                                        backgroundColor = if (piece == "BLACK") Color("black") else Color("white")
                                        boxShadow = BoxShadow(2.px, 2.px, 5.px, Color("rgba(0,0,0,0.3)"))
                                    }
                                }
                            }
                        }
                    }
                }
            }

            button {
                css {
                    marginTop = 20.px
                    padding = Padding(10.px, 20.px)
                    fontSize = 1.1.rem
                    cursor = Cursor.pointer
                    borderRadius = 5.px
                    border = Border.none
                    backgroundColor = Color("#3498db")
                    color = Color("white")
                    hover {
                        backgroundColor = Color("#2980b9")
                    }
                }
                onClick = { onReset() }
                +"New Game"
            }
        } ?: div { +"Loading..." }
    }
}
