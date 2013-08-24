package pong

import java.awt.Font
import java.io.File
import scene.SceneComponent
import scene.Scene

class ScoreBoard(var p1score: Int, var p2score: Int, font: Font,
    parent: Scene) extends SceneComponent(parent) {

  private val FONT_SIZE = 75f
  private val FONT = font.deriveFont(FONT_SIZE)
		  					
  private val YCOORD = 100f
   
  /**
   * Builds a new ScoreBoard initializing both scores to 0
   */
  def this(font: Font, parent: Scene) = this(0, 0, font, parent)
  
  /**
   * Increase player1 score
   */
  def increasePlayer1Score() = p1score += 1
  
  /**
   * Increase player2 score
   */
  def increasePlayer2Score() = p2score += 1
  
  /**
   * Draw players' score
   */
  def draw(g: java.awt.Graphics2D): Unit = {
    g.setColor(java.awt.Color.WHITE)    
    g.setFont(FONT)
    
    val p1string = p1score.toString
    val p2string = p2score.toString
    
    val p1xcoord = parent.getWidth / 4f - p1string.length / 2f * FONT_SIZE
    val p2xcoord = parent.getWidth * 3 / 4f - p2string.length / 2f * FONT_SIZE
    			
    			
    g.drawString(p1string, p1xcoord, YCOORD)
    g.drawString(p2string, p2xcoord, YCOORD)
    
  } 
  
  /**
   * Does nothing
   */
  def update(timespan: Float): Unit = {}
  
  /**
   * Override toString
   */
  override def toString = 
    "[pong.ScoreBoard] player1: " + p1score + " | player2: " + p2score
}


