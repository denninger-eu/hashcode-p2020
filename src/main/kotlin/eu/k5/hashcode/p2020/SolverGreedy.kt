package eu.k5.hashcode.p2020

class SolverGreedy(
    val problem: Problem
) : Solver {


    override fun solve(): Solution {
        val solution = Solution(problem)
        var score = 0L
        for ((index, pizza) in problem.pizzas.withIndex().reversed()) {
            if (score + pizza < problem.persons) {
                score += pizza
                solution.add(index)
            }
        }


        return solution
    }
}