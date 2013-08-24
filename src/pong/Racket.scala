package pong

import scene.Scene
import scene.SceneComponent
import helpers.MathHelper

/**
 * Companion object
 */
object Racket {
  val WIDTH = 15
  val HEIGHT = 75
  val VELOCITY = 350
}

/**
 * Pong Racket
 */
class Racket(var x: Float, var y: Float, parent: Scene)
  extends SceneComponent(parent) {

  /**
   * @return Racket top edge Y coordinate
   */
  def getTop() = y
  
  /**
   * @return Racket Bottom edge Y coordinate
   */
  def getBottom() = y + Racket.HEIGHT
  
  /**
   * @return Racket left edge X coordinate
   */
  def getLeft() = x 
  
  /**
   * @return Racket right edge X coordinate
   */
  def getRight() = x + Racket.WIDTH 
  
  /**
   * Move this racket up
   */
  def moveUp(timespan: Float): Unit = {
    y = MathHelper.clamp(y - Racket.VELOCITY * timespan,
      0f,
      parent.getHeight() - Racket.HEIGHT)
  }

  /**
   * Move this racket down
   */
  def moveDown(timespan: Float): Unit = {
    y = MathHelper.clamp(y + Racket.VELOCITY * timespan,
      0f,
      parent.getHeight() - Racket.HEIGHT)
  }

  /**
   * Does nothing
   */
  def update(timespan: Float): Unit = {}

  /**
   * Draw implementation. Inherit from SceneComponent
   */
  def draw(g: java.awt.Graphics2D): Unit = {
    g.setColor(java.awt.Color.WHITE)
    g.fillRect(math.round(x), math.round(y), Racket.WIDTH, Racket.HEIGHT)
  }
}