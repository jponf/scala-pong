package pong

import java.awt.Font

import scala.swing.event.Key

import scene.Scene
import scene.SceneManager

/**
 * PauseScene companion object
 */
object PauseScene {
  val FONT_SIZE = 25f
  val MARGIN_BETWEEN_LINES = 10f
  val FONT = Pong.FONT.deriveFont(FONT_SIZE)

  val PAUSE_MSG: String =
    "PAUSE\n" +
      "-----\n" +
      "Player1:\n'" + Pong.PLAYER1_UP.toString + "' - UP, '" +
      Pong.PLAYER1_DOWN.toString + "' - DOWN\n" +
      "Player2:\n'" + Pong.PLAYER2_UP.toString + "' - UP, '" +
      Pong.PLAYER2_DOWN.toString + "' - DOWN\n" +
      "------------------------------\n" +
      "Toggle pause: '" + Pong.PAUSE_GAME.toString + "'\n" +
      "Quit: '" + Pong.QUIT_GAME.toString + "'"
}

/**
 * Pause screen
 */
class PauseScene(manager: SceneManager) extends Scene(manager) {
 
  override def update(timespan: Float): Unit = {
    super.update(timespan)
    handleKeyPressed()
  }
  
  /**
   * Handles user input
   */
  def handleKeyPressed() = {
    if (manager.keyboardState.isNewKeyPress(Pong.PAUSE_GAME)) manager.popScene()
    if (manager.keyboardState.isNewKeyPress(Pong.QUIT_GAME)) System.exit(0)
  }

  /**
   * Draw
   */
  override def draw(g: java.awt.Graphics2D): Unit = {
    super.draw(g)

    g.setFont(PauseScene.FONT)
    g.setColor(java.awt.Color.WHITE)

    val numlines = PauseScene.PAUSE_MSG.lines.length
    var y = (manager.height - numlines * PauseScene.FONT_SIZE) / 2f

    for (l <- PauseScene.PAUSE_MSG.lines) {
      val x = (manager.width - l.length() * PauseScene.FONT_SIZE) / 2f
      g.drawString(l, x, y)
      y = y + PauseScene.FONT_SIZE + PauseScene.MARGIN_BETWEEN_LINES
    }

  }

}