package scene

import scala.swing.event.Key

/**
 * Represents a game scene. Contains minimal implementation.
 */
abstract class Scene(val manager: SceneManager) {

  private var components = List[SceneComponent]()

  /**
   * Adds the specified component to this scene
   */
  def addComponent(c: SceneComponent): Unit =
    components = components :+ c

  /**
   * Remove the specified component. If the component isn't part of the scene
   * then nothing happens
   */
  def removeComponent(c: SceneComponent): Unit =
    components = components.filter(x => x != c)

  /**
   * Gets the scene width
   */
  def getWidth() = manager.width

  /**
   * Gets the scene height
   */
  def getHeight() = manager.height
 
  /**
   * Update all the scene components.
   *
   * To add extra logic to the scene override this method and add a call to
   * super.update(timespan) where it is necessary within the new logic
   *
   * @param timespan Time between update calls in seconds
   */
  def update(timespan: Float): Unit = {
    for (ue <- components)
      ue.update(timespan)
  }

  /**
   * Set graphics and call draw method of all the components
   */
  def draw(g: java.awt.Graphics2D): Unit = {

    for (c <- components)
      c.draw(g)
  }
}