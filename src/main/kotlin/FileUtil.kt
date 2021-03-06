import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader

fun loadInputAsLineList(filename: String, filepath: String) : List<String> {
    val inputFile: File
    when {
        File(filename).exists() -> inputFile = File(filename)
        File(filepath).exists() -> inputFile = File(filepath)
        else -> throw FileNotFoundException("$filename nor $filepath was found")
    }

    val fis = FileInputStream(inputFile).buffered()
    fis.use { fis ->
        val isr = InputStreamReader(fis)
        return isr.readLines()
    }
}

fun loadInputAsLines(filename: String, filepath: String) : String {
    val inputFile: File
    when {
        File(filename).exists() -> inputFile = File(filename)
        File(filepath).exists() -> inputFile = File(filepath)
        else -> throw FileNotFoundException("$filename nor $filepath was found")
    }

    val fis = FileInputStream(inputFile).buffered()
    var lines = ""

    fis.use { fis ->
        val isr = InputStreamReader(fis)
        isr.readLines().forEach { lines += it }
    }
    return lines
}
