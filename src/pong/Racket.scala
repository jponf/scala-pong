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
  val VELOCITY = 5
}

/**
 * Pong Racket
 */
class Racket(var x: Float, var y: Float, parent: Scene)
  extends SceneComponent(parent) {

  /**
   * Move this racket up
   */
  def moveUp(): Unit = {
    y = MathHelper.clamp(y - Racket.VELOCITY,
      0,
      parent.getHeight() - Racket.HEIGHT)
  }

  /**
   * Move this racket down
   */
  def moveDown(): Unit = {
    y = MathHelper.clamp(y + Racket.VELOCITY,
      0,
      parent.getHeight() - Racket.HEIGHT)
  }

  /**
   * Racket is updated when user presses a key
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