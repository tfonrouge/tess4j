package com.fonrouge

import net.sourceforge.tess4j.Tesseract
import java.io.File

fun main(vararg args: String) {
    Tesseract().apply {
        when (args.getOrNull(0)) {
            "linux" -> {
                setDatapath("/usr/share/tesseract-ocr/5/tessdata")
            }

            "macOsX" -> {
                System.setProperty("jna.library.path", "/usr/local/lib")
                setDatapath("/usr/local/Cellar/tesseract/5.4.1_1/share/tessdata")
            }
            else -> return
        }
        setLanguage("spa")
    }.run {
        this::class.java.getResource("/OrdenCompra24101510919065.pdf")?.let { url ->
            val file = File(url.path)
            println("Starting ...")
            if (file.exists()) {
                val millis = System.currentTimeMillis()
                doOCR(file)?.let {
                    println(it)
                }
                println("took millis: ${System.currentTimeMillis() - millis}")
            } else {
                println("$file not found")
            }
        }
    }
}
