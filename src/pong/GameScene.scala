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
    
    handleKeyPressed(timespan)
    
    // Ball collisions
    if (collisionLeft) playerScore(scoreboard.increasePlayer2Score)
    else if (collisionRight) playerScore(scoreboard.increasePlayer1Score)
    
    
    if (collisionTop) {
      ball.setTop(0)
      ball.invertYVelocity()
    } else if (collisionBotom) {
      ball.setBottom(manager.height)
      ball.invertYVelocity()
    }
    
    if (collisionLeftRacket) { 
      ball.setLeft(p1racket.getRight())
      ball.invertXVelocity()
    } else if(collisionRightRacket) {
      ball.setRight(p2racket.getLeft())
      ball.invertXVelocity()
    }

  }

  /**
   * Handles user input
   */
  def handleKeyPressed(timespan: Float) = {

    if (manager.keyboardState.isNewKeyPress(Pong.PAUSE_GAME))
      manager.pushScene(new PauseScene(manager))
    if (manager.keyboardState.isNewKeyPress(Pong.QUIT_GAME))
      System.exit(0)

    if (manager.keyboardState.isKeyDown(Pong.PLAYER1_UP)) 
      p1racket.moveUp(timespan)
    if (manager.keyboardState.isKeyDown(Pong.PLAYER1_DOWN))
      p1racket.moveDown(timespan)
      
    if (manager.keyboardState.isKeyDown(Pong.PLAYER2_UP)) 
      p2racket.moveUp(timespan)
    if (manager.keyboardState.isKeyDown(Pong.PLAYER2_DOWN)) 
      p2racket.moveDown(timespan)
  }

  /**
   * Increase score and sets up a new ball
   */
  def playerScore(score_increase_function: () => Unit): Unit = {
    score_increase_function()
    setUpNewBall()
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
   * @return New ball centered on the game window with random velocity
   */
  private def getNewBall(): Ball =
    new Ball(
      (manager.width - Ball.SIZE) / 2f, (manager.height - Ball.SIZE) / 2f,
      Ball.getRandomBallVelocity(), Ball.getRandomBallVelocity(),
      this)

  // Ball collision checking  
  private def collisionTop = ball.intersects(0, -1, manager.width, 1)
  private def collisionLeft = ball.intersects(-1, 0, 1, manager.height)
  
  private def collisionBotom = 
    ball.intersects(0, manager.height, manager.width, 1)
  private def collisionRight = 
    ball.intersects(manager.width, 0, 1, manager.height)
  
  private def collisionLeftRacket =
    ball.intersects(p1racket.x, p1racket.y, Racket.WIDTH, Racket.HEIGHT)
  private def collisionRightRacket =
    ball.intersects(p2racket.x, p2racket.y, Racket.WIDTH, Racket.HEIGHT)
    
}
