package scene

import scala.swing.event.Key


abstract class SceneComponent(val parent: Scene ){

  def update(timespan: Float): Unit
  
  def draw(g: scala.swing.Graphics2D): Unit
}