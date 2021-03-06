package com.janosgyerik.practice.oj.codejam.codejam2017.common;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Runner {

    private final Problem problem;

    private Runner(Problem problem) {
        this.problem = problem;
    }

    public static Runner create(Problem problem) {
        return new Runner(problem);
    }

    public void run(String input) throws IOException {
        run(input, true);
    }

    public void run(String filename, boolean print) throws IOException {
        String basedir = "tmp/submit";
        Inputs inputs = problem.inputs(new Scanner(Paths.get(basedir).resolve(filename)));
        Solver solver = problem.solver(inputs);

        try (FileWriter writer = new FileWriter(Paths.get(basedir).resolve(filename + ".out").toFile())) {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= inputs.count(); ++i) {
                Input input = inputs.get(i);
                Answer answer = solver.solve(input);
                sb.append("Case #").append(i).append(": ").append(answer.value()).append("\n");
                problem.validate(input, answer);
                writer.write(sb.toString());
                if (print) System.out.print(sb);
                sb.setLength(0);
            }
        }
    }
}
