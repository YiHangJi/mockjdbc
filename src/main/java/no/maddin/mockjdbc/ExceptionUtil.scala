package no.maddin.mockjdbc

import java.io.{ByteArrayOutputStream, PrintStream}

object ExceptionUtil {

  def handleException[T](body: => T): T = {
    try {
      body
    } catch {
      case e: Exception => {
        val io = new ByteArrayOutputStream
        e.printStackTrace(new PrintStream(io))
        LogUtil.append(io.toString("UTF-8"))
        throw e;
      }
    }
  }
}