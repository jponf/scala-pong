package pong

import scene.SceneComponent
import scene.Scene

class Field(parent: Scene) extends SceneComponent(parent) {

  val SEP_LINE_WIDTH = 10
  val SEP_LINE_XCOORD = math.round( (parent.getWidth - SEP_LINE_WIDTH) / 2f) 
  
  /**
   * Override Scene paint method. Draws the field and all the 
   * elements on it
   */
  override def draw(g: java.awt.Graphics2D): Unit = {
    g.setColor(java.awt.Color.WHITE)    
    g.fillRect(SEP_LINE_XCOORD, 0, SEP_LINE_WIDTH , parent.getHeight)    
  }
  
  /**
   * Inherit from abstract class SceneComponent
   */
  def update(timespan: Float): Unit = {}

}