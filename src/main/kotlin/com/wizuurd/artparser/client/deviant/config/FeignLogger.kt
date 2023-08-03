package com.wizuurd.artparser.client.deviant.config

import feign.Logger
import org.slf4j.LoggerFactory

class FeignLogger: Logger() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private var log = StringBuilder()
    private var isLogging = false

    override fun log(configKey: String, format: String, vararg args: Any) {

        if (format.startsWith("--->") && !format.startsWith("---> END")) {
            isLogging = true
            log.appendLine()
        }

        if (format.contains("--->") || format.contains("<---")) {
            log.append("$configKey ")
        }

        if (isLogging) {
            log.append("$format\n".format(*args))
        }

        if (format.startsWith("<--- END")) {
            isLogging = false

            logger.info(log.toString())
            log.clear()
        }
    }
}