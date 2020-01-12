package eu.k5.hashcode.p2020

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths

class Problem(
    val name: String,
    val persons: Int,
    val pizzas: IntArray
) {


    companion object {
        fun parse(name: String): Problem {
            return Files.newBufferedReader(Paths.get("in", "$name.in")).use {
                parse(name, it!!)
            }
        }

        private fun parse(name: String, reader: BufferedReader): Problem {

            val header = reader.readLine();

            val headerParts = header.split(" ")


            val pizzaHeader = reader.readLine()

            return Problem(
                name,
                Integer.parseInt(headerParts[0]),
                pizzaHeader.split(" ").map { Integer.parseInt(it) }.toIntArray()
            )
        }
    }
}