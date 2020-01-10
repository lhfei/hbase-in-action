package cn.lhfei.hbase.client

object SparkLoader {
  
  def salt(key: String, modulus: Int): String = {
    val saltAsInt = Math.abs(key.hashCode) % modulus

    // left pad with 0's (for readability of keys)
    val charsInSalt = digitsRequired(modulus)
    ("%0" + charsInSalt + "d").format(saltAsInt) + ":" + key
  }

  // number of characters required to encode the modulus in chars (01,02.. etc)
  def digitsRequired(modulus: Int): Int = {
    (Math.log10(modulus - 1) + 1).asInstanceOf[Int]
  }

  def main(args: Array[String]) {
//    val spark = SparkSession.builder()
//      .appName("Spark Dataframe example by Scala.")
//      .getOrCreate();

    val key = "species:797:1:1:1"
    val salt = Math.abs(key.hashCode % 45)

    val saltedKey = "%02d".format(salt) + ":" + key

    println(saltedKey)

  }
}