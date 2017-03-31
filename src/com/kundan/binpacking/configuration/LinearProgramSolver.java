package com.kundan.binpacking.configuration;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

@SuppressWarnings("deprecation")
public class LinearProgramSolver {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// describe the optimization problem
		LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] {
				3, 5, 7 }, 0);

		Collection constraints = new ArrayList();
		constraints.add(new LinearConstraint(new double[] { 2, 8, 3 },
				Relationship.LEQ, 13));
		constraints.add(new LinearConstraint(new double[] { 5, -1, 3 },
				Relationship.LEQ, 11));

		constraints.add(new LinearConstraint(new double[] { 1, 0, 2 },
				Relationship.GEQ, 0));
		constraints.add(new LinearConstraint(new double[] { 0, 1, 1 },
				Relationship.GEQ, 0));

		// create and run solver
		RealPointValuePair solution = null;
		try {
			solution = new SimplexSolver().optimize(f, constraints,
					GoalType.MAXIMIZE, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (solution != null) {
			// get solution
			double max = solution.getValue();
			System.out.println("Opt: " + max);

			// print decision variables
			for (int i = 0; i < 2; i++) {
				System.out.print(solution.getPoint()[i] + "\t");
			}
		}
	}
}
