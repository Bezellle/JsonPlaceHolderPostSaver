package FlieSaver

import java.io.{BufferedWriter, FileWriter}


class FileHandler {

  def saveFile(fileName: String, fileBody: String): Unit = {
    val fileWriter = new FileWriter(fileName)
    val bufferedWriter = new BufferedWriter(fileWriter)
    bufferedWriter.write(fileBody)
    bufferedWriter.close()
  }


}
