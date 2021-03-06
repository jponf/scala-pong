package pong

import scene.Scene
import scene.SceneComponent

/**
 * Companion object
 */
object Ball {
  val SIZE = 20
  
  private val MIN_BALL_VEL = 150
  private val MAX_BALL_VEL = 300

  /**
   * Gets random ball speed between MIN_BALL_VEL and MAX_BALL_VEL
   */
  def getRandomBallVelocity(): Float = {
    val vel = MIN_BALL_VEL + math.random.toFloat * (MAX_BALL_VEL - MIN_BALL_VEL) 
    
    if (math.random < 0.5) -vel    	
    else vel
  }
}

/**
 * Pong ball
 */
class Ball(var x: Float, var y: Float, var xvelocity: Float,
  var yvelocity: Float, parent: Scene) extends SceneComponent(parent) {

  /**
   * Sets ball y coordinate, to have its top at the specified coordinate
   */
  def setTop(y: Float) = this.y = y
  
  /**
   * Sets ball x coordinate, to have its left at the specified coordinate
   */
  def setLeft(x: Float) = this.x = x
  
  /**
   * Sets ball y coordinate, to have its bottom at the specified coordinate
   */
  def setBottom(y: Float) = this.y = y - Ball.SIZE
  
  /**
   * Sets ball x coordinate, to have its right at the specified coordinate
   */
  def setRight(x: Float) = this.x = x - Ball.SIZE
  
  /**
   * Inverts Y velocity
   */
  def invertYVelocity(): Unit = yvelocity = -yvelocity
  
  /**
   * Inverts X velocity
   */
  def invertXVelocity(): Unit = xvelocity = -xvelocity

  /**
   * Update ball position
   */
  def update(tspan: Float): Unit = {
    x = x + xvelocity * tspan
    y = y + yvelocity * tspan
  }
 
  /**
   * Draw implementation. Inherit from SceneComponent
   */
  def draw(g: java.awt.Graphics2D): Unit = {
    g.setColor(java.awt.Color.WHITE)
    g.fillRect(math.round(x), math.round(y), Ball.SIZE, Ball.SIZE)
  }

  /**
   *  Checks if the ball intersects the specified rectangle
   */
  def intersects(x: Float, y: Float, width: Float, height: Float): Boolean = {
    !(this.x > x + width || this.x + Ball.SIZE < x ||
      this.y > y + height || this.y + Ball.SIZE < y)
  }

  override def toString: String =
    "Pong.Ball [x: " + x + ", y:" + y + " | xspeed: " + xvelocity + ", yspeed: " + yvelocity + " ]"
}