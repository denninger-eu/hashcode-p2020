package eu.k5.hashcode.p2020

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    var total = 0L
    total += solve("a_example") { SolverGreedy(it) }
    total += solve("b_small") { SolverGreedy2(it) }
    total += solve("c_medium") { SolverGreedy2(it) }
    total += solve("d_quite_big") { SolverGreedy2(it) }
    total += solve("e_also_big") { SolverGreedy2(it) }

    println("total " + total)
}


fun solve(name: String, solver: (Problem) -> Solver): Long {
    val problem = Problem.parse(name)

    val solver = solver(problem)
    val solution = solver.solve()

    val score = solution.score()
    println(name + " " + score)

    Files.newBufferedWriter(Paths.get("$name.out")).use {
        solution.write(it)
    }
    return score
}
