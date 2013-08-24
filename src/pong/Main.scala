package pong

import scene.SceneManager

/**
 * Contains the canvas and handles time operations
 */
object Main extends scala.swing.SimpleSwingApplication {

  val scene_manager = new SceneManager(Pong.DEF_WIDTH, Pong.DEF_HEIGHT)

  // Game Window
  def top = new scala.swing.MainFrame {

    // Set up scene manager
    scene_manager.background = java.awt.Color.BLACK
    scene_manager.pushScene(new GameScene(scene_manager))
    scene_manager.pushScene(new PauseScene(scene_manager))
    contents = scene_manager

    // Game loop
    val gameLoop = new scala.actors.Actor {

      def act() {
        var st = System.nanoTime()
        var et = System.nanoTime()

        // Game Loop
        loop {
          val tspan = (et - st) / 1000000f // Time in milliseconds for sleep

          scene_manager.update(tspan / 1000f) // Runs game logic
          repaint()

          Thread.sleep(math.max(0f, Pong.TIME_BETWEEN_FRAMES - tspan).toLong)

          st = et
          et = System.nanoTime()
        }
      }
    }
    gameLoop.start()

    // Set up some window properties
    title = "Pong"
    size = new scala.swing.Dimension(Pong.DEF_WIDTH, Pong.DEF_HEIGHT)
  }
}
