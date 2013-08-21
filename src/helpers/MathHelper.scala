package helpers


object MathHelper {

  /**
   * Restricts a value to be within a specified range
   * @return The clamped value
   * 	if value > high, high will be returned
   *    if value < log, low will be returned
   *    if min <= value <= max, value will be returned
   */  
  def clamp[T](value: T, low: T, high: T)(implicit num: Numeric[T]): T = {
    import num._
    if (value < low) low else if (value > high) high else value
  }
}
