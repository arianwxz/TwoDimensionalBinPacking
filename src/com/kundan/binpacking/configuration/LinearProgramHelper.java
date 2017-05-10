package com.kundan.binpacking.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

import com.kundan.binpacking.core.Bin;
import com.kundan.binpacking.core.Item;
import com.kundan.binpacking.core.Shelf;

public class LinearProgramHelper {

	public double solve(List<Item> iDash, List<Config> configs) {
		double max = 0;
		double objective_array[] = new double[configs.size()];
		int ind = 0;
		for (Item item : iDash) {
			objective_array[ind++] = 1.0;
		}
		
		System.out.println(objective_array);

		// describe the optimization problem
		LinearObjectiveFunction f = new LinearObjectiveFunction(objective_array, 0);

		Collection constraints = new ArrayList();
		for (Item item : iDash) {
			double constraint_array[] = new double[configs.size()];
			int ind1 = 0;
			for(Config config: configs){
				if(configContains(item, config)){
					constraint_array[ind1++] = 1;
					System.out.print("1 ");
				}else{
					constraint_array[ind1++] = 0;
					System.out.print("0 ");
				}
			}
			System.out.println();
			constraints.add(new LinearConstraint(constraint_array,
					Relationship.GEQ, 1));
		}
		

		// create and run solver
		RealPointValuePair solution = null;
		try {
			solution = new SimplexSolver().optimize(f, constraints,
					GoalType.MINIMIZE, true);
		} catch (OptimizationException e) {
			e.printStackTrace();
		}

		if (solution != null) {
			// get solution
			max = solution.getValue();
			System.out.println("Opt: " + max);

			// print decision variables
			for (int i = 0; i < configs.size(); i++) {
				System.out.print(solution.getPoint()[i] + "\t");
			}
		}
		
		return max;
	}

	private boolean configContains(Item item, Config config) {
		Bin bin = config.mBin;
		for(Shelf shelf : bin.mShelves){
			if(shelf.mAssignedItems.contains(item)){
				return true;
			}
		}
		
		return false;
	}
	
	public double solve1(List<Item> iDash, List<Config> configs) {
		return 0;
	}
}
