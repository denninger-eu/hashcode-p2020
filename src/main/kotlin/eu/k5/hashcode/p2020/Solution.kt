package eu.k5.hashcode.p2020

import java.time.chrono.JapaneseEra.values
import java.io.IOException
import java.io.Writer


class Solution(
    val problem: Problem
) {

    private val orderPizza = ArrayList<Int>()


    fun score(): Long {
        val used = HashSet<Int>()
        var score = 0L
        for (order in orderPizza) {
            if (used.contains(order)) {
                throw IllegalArgumentException("already ordered $order")
            }

            score += problem.pizzas[order]
            used.add(order)
        }
        if (score > problem.persons) {
            throw IllegalArgumentException("wasted pizza")
        }
        return problem.persons - score
    }

    fun add(pizza: Int) {
        if (orderPizza.contains(pizza)) {
            throw IllegalArgumentException("already ordered $pizza")
        }
        orderPizza.add(pizza)
    }

    @Throws(IOException::class)
    fun write(writer: Writer) {

        writer.write("" + orderPizza.size)
        writer.write("\n")
        for (cache in orderPizza) {
            writer.write("$cache ")
        }
        writer.write("\n")
    }

    fun remove(pizza: Int) {
        orderPizza.remove(pizza)
    }

}