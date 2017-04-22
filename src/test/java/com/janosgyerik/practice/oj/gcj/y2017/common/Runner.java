package com.janosgyerik.practice.oj.gcj.y2017.common;

import com.janosgyerik.practice.oj.gcj.y2017.qual.TidyNumbers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {
        String input = "B-large.in";
        Problem problem = new TidyNumbers();
        boolean print = true;

        String basedir = "tmp/submit";
        Inputs inputs = problem.inputs(new Scanner(Paths.get(basedir).resolve(input)));
        Solver solver = problem.solver(inputs);

        try (FileWriter writer = new FileWriter(Paths.get(basedir).resolve(input + ".out").toFile())) {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= inputs.count(); ++i) {
                Answer answer = solver.solve(inputs.get(i));
                sb.append("Case #").append(i).append(": ").append(answer.value()).append("\n");
                writer.write(sb.toString());
                if (print) System.out.print(sb);
                sb.setLength(0);
            }
        }
    }
}
