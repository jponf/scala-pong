package scene

import scala.collection.mutable.Stack
import scala.swing.event.Key
import scala.swing.event.KeyPressed
import scala.swing.event.KeyReleased
import scene.input.KeyboardState

/**
 * Manage different scenes.
 *
 * Scenes are pushed into a stack and the top scene is the one running.
 * The others remain standby.
 */
class SceneManager(val width: Int, val height: Int) extends scala.swing.Panel {

  private val scenes = Stack[Scene]()
  val keyboardState = new KeyboardState()

  // Set up SceneManager to listen keyboard
  listenTo(keys)
  reactions += {
    case KeyPressed(s, k, m, l) => handleKeyPressed(k)
    case KeyReleased(s, k, m, l) => handleKeyReleased(k)
  }
  focusable = true
  requestFocus

  /**
   * Adds a new scene at the top of the stack
   */
  def pushScene(sc: Scene) = scenes.push(sc)

  /**
   * Pops the scene at the top and return it
   */
  def popScene(): Scene = scenes.pop()

  /**
   * Update keyboardState
   */
  def handleKeyPressed(key: Key.Value): Unit = {
    keyboardState.updateKeyPressed(key)
  }

  /**
   * Update keyboardState 
   */
  def handleKeyReleased(key: Key.Value): Unit = {
    keyboardState.updateKeyReleased(key)
  }
  
  /**
   * Updates the scene at the top of the stack
   */
  def update(timespan: Float) = {
    scenes.top.update(timespan)
    keyboardState.updateOldState()
  }

  /**
   * Draw the scene at the top of the stack
   */
  override def paint(g: java.awt.Graphics2D): Unit = {
    super.paint(g)

    val gclipbounds = g.getClipBounds()
    val xscale = gclipbounds.getWidth().toFloat / width
    val yscale = gclipbounds.getHeight().toFloat / height
    g.scale(xscale, yscale)

    scenes.top.draw(g)
  }

}