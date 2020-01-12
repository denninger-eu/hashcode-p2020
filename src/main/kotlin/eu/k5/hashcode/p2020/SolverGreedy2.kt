package eu.k5.hashcode.p2020

class SolverGreedy2(
    val problem: Problem
) : Solver {


    override fun solve(): Solution {
        val solution = Solution(problem)
        var score = 0L
        val used = ArrayList<IndexedValue<Int>>()
        val unused = ArrayList<IndexedValue<Int>>()
        for ((index, pizza) in problem.pizzas.withIndex().reversed()) {
            if (score + pizza < problem.persons) {
                score += pizza
                solution.add(index)
                used.add(IndexedValue(index, pizza))
            } else {
                unused.add(IndexedValue(index, pizza))
            }
        }


        var cleanup = 0
        while (true) {

            val openScore = problem.persons - score
            val smallestUnused = if (problem.name.startsWith("d")) {
                unused[unused.size - 101] // just some experiments
            } else {
                unused[unused.size - 1]
            }

            var gaines: Gain? = null
            for (unuse in unused) {
                for (use in used) {
                    val gain = unuse.value + smallestUnused.value - use.value
                    if (gain > 0 && gain <= openScore) {
                        if (gaines == null) {
                            gaines = Gain(smallestUnused, unuse, use, gain)
                        } else if (gaines!!.gain < gain) {
                            println("$gain found " + unuse + " " + smallestUnused + " " + use)
                            gaines = Gain(smallestUnused, unuse, use, gain)
                        }

                    }
                }
            }
            if (gaines != null) {
                println("cleanup $cleanup")
                solution.remove(gaines.used.index)
                used.removeIf { it.index == gaines.used.index }
                //  unused.add(gaines.used)
                solution.add(gaines.unused1.index)
                solution.add(gaines.unused2.index)

                unused.removeIf { it.index == gaines.unused2.index || it.index == gaines.unused1.index }
                used.add(gaines.unused1)
                used.add(gaines.unused2)
                score += gaines.gain
            } else {
                return solution
            }
            cleanup++
        }


        return solution
    }


    class Gain(
        val unused1: IndexedValue<Int>,
        val unused2: IndexedValue<Int>,
        val used: IndexedValue<Int>,
        val gain: Int
    )
}