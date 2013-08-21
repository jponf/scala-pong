package pong

import scene.Scene
import scene.SceneManager
import scala.swing.event.Key

/**
 * GameScene companion object
 */
object GameScene {
  val RACKET_EDGE_MARGIN = 10
}

/**
 * Pong main game routine
 */
class GameScene(manager: SceneManager) extends Scene(manager) {

  // Add game scene components
  var ball = getNewBall()
  val scoreboard = new ScoreBoard(0, 0, Pong.FONT, this)
  val p1racket = new Racket(
    GameScene.RACKET_EDGE_MARGIN, (manager.height - Racket.HEIGHT) / 2f, this)
  val p2racket = new Racket(
    manager.width - Racket.WIDTH - GameScene.RACKET_EDGE_MARGIN,
    (manager.height - Racket.HEIGHT) / 2f, this)

  addComponent(new Field(this))
  addComponent(scoreboard)
  addComponent(p1racket)
  addComponent(p2racket)
  addComponent(ball)

  /**
   * Scene logic
   */
  override def update(timespan: Float): Unit = {
    super.update(timespan)
    
    handleKeyPressed()
    
    // Ball collisions
    if (collisionLeft) {
      setUpNewBall()
      scoreboard.increasePlayer2Score
    } else if (collisionRight) {
      setUpNewBall()
      scoreboard.increasePlayer1Score
    }
    
    if (collisionTop) {
      setBallAfterCollision(ball.x, 0, ball.xvelocity, -ball.yvelocity)
    } else if (collisionBotom) {
      setBallAfterCollision(
        ball.x, manager.height - Ball.SIZE, ball.xvelocity, -ball.yvelocity)
    }
    
    if (collisionLeftRacket) { 
      setBallAfterCollision(
          p1racket.x + Racket.WIDTH, ball.y, 
          -ball.xvelocity, ball.yvelocity)
    } else if(collisionRightRacket) {
      setBallAfterCollision(
          p2racket.x - Ball.SIZE, ball.y, 
          -ball.xvelocity, ball.yvelocity)
    }

  }

  /**
   * Handles user input
   */
  def handleKeyPressed() = {

    if (manager.keyboardState.isNewKeyPress(Pong.PAUSE_GAME))
      manager.pushScene(new PauseScene(manager))
    if (manager.keyboardState.isNewKeyPress(Pong.QUIT_GAME))
      System.exit(0)

    if (manager.keyboardState.isKeyDown(Pong.PLAYER1_UP)) p1racket.moveUp
    if (manager.keyboardState.isKeyDown(Pong.PLAYER1_DOWN)) p1racket.moveDown
    if (manager.keyboardState.isKeyDown(Pong.PLAYER2_UP)) p2racket.moveUp
    if (manager.keyboardState.isKeyDown(Pong.PLAYER2_DOWN)) p2racket.moveDown
  }

  /**
   * Sets up a new ball
   */
  private def setUpNewBall(): Unit = {
    removeComponent(ball)
    ball = getNewBall()
    addComponent(ball)
  }

  /**
   * Get a new ball centered on the game window with random velocity
   */
  private def getNewBall(): Ball =
    new Ball(
      (manager.width - Ball.SIZE) / 2f, (manager.height - Ball.SIZE) / 2f,
      Ball.getRandomBallVelocity(), Ball.getRandomBallVelocity(),
      this)

  /**
   * Sets ball properties after a collision with top or bottom edges
   */
  private def setBallAfterCollision(x: Float, y: Float, xvelocity: Float,
    yvelocity: Float) = {
    ball.x = x
    ball.y = y
    ball.xvelocity = xvelocity
    ball.yvelocity = yvelocity
  }

  // Ball collision checking  
  private def collisionTop = ball.intersects(0, -1, manager.width, 1)
  private def collisionBotom = ball.intersects(0, manager.height, manager.width, 1)
  private def collisionLeft = ball.intersects(-1, 0, 1, manager.height)
  private def collisionRight = ball.intersects(manager.width, 0, 1, manager.height)
  
  private def collisionLeftRacket =
    ball.intersects(p1racket.x, p1racket.y, Racket.WIDTH, Racket.HEIGHT)
  private def collisionRightRacket =
    ball.intersects(p2racket.x, p2racket.y, Racket.WIDTH, Racket.HEIGHT)
    
}