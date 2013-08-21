package pong

import java.awt.Font
import java.io.File
import scala.swing.event.Key

object Pong {
  // Game configurations
  val DEF_WIDTH = 800
  val DEF_HEIGHT = 600
  val TIME_BETWEEN_FRAMES = 1f / 60f * 1000f // milliseconds
  val FONT = Font.createFont(Font.TRUETYPE_FONT, new File("press_start_2p.ttf"))

  val PLAYER1_UP = Key.A
  val PLAYER1_DOWN = Key.Z
  val PLAYER2_UP = Key.J
  val PLAYER2_DOWN = Key.M
  
  val PAUSE_GAME = Key.P
  val QUIT_GAME = Key.Q
  
}