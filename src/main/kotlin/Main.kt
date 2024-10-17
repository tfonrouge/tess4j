package com.fonrouge

import net.sourceforge.tess4j.Tesseract
import java.io.File
import java.net.URL

fun main() {
    Tesseract().apply {
        setDatapath("/usr/share/tesseract-ocr/5/tessdata")
        setLanguage("spa")
    }.run {
        this::class.java.getResource("/OrdenCompraDirectos24101210911321.pdf")?.let { url ->
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
