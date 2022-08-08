package validator

import java.io.File

class DirectoryValidator {

  def isValidDirectory(directoryPath: String): Boolean = {
    val directory = new File(directoryPath)
    directory.isDirectory
  }
}
