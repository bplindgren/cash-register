package register.register;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Register {
	private Map<Integer, Integer> bills;
	private List<Integer> denominations = Arrays.asList(20, 10, 5, 2, 1);

	public Register(Map<Integer, Integer> bills) {
		this.bills = bills;
	}
	
	public void addMoney(Integer bill, Integer quantity) {
		bills.put(bill, (bills.get(bill) + quantity));
	}
	
	public void removeMoney(Integer bill, Integer quantity) {
		bills.put(bill, (bills.get(bill) - quantity));
	}
	
	public Integer getTotal() {
		Integer total = 0;
		for (Integer key: bills.keySet()) 
			total += (key * bills.get(key));
		return total;
	}
	
	public TreeMap<Integer, Integer> makeChange(Integer amountOwed) throws Exception {
		TreeMap<Integer, Integer> change = new TreeMap<Integer, Integer>();
		
		// For each denomination		
		for (Integer denomination: denominations) {
			
			// If change can be made using that denomination
			Integer billsPossible = amountOwed/denomination;
			if (billsPossible > 0 && amountOwed > 0) {
				
				// Remove as many possible from the register
				Integer numInRegister = bills.get(denomination);
				
				// If the number in the register is greater than the number of bills possible
				if (numInRegister > billsPossible) {
					if ((amountOwed - (denomination * billsPossible) == 1) && (bills.get(1) == 0)) {
						// remove the number of bills possible
						removeMoney(denomination, billsPossible - 1);
						// update the amount owed
						amountOwed -= (denomination * (billsPossible - 1));
						// put the correct amount into the change TreeMap
						change.put(denomination, billsPossible - 1);
					} else {
						// remove the number of bills possible
						removeMoney(denomination, billsPossible);
						// update the amount owed
						amountOwed -= (denomination * billsPossible);
						// put the correct amount into the change TreeMap
						change.put(denomination, billsPossible);
					}
				}
				// If the number in the register is less than the number of bills possible
				else if (numInRegister > 0) {
					// remove all of the bills
					removeMoney(denomination, numInRegister);
					// update the amount owed
					amountOwed -= (denomination * numInRegister);
					// put the correct amount into the change TreeMap
					change.put(denomination, numInRegister);
				}
			}
		}
		
		// After going through each denomination, if you can't make change...
		if (amountOwed > 0) {
			throw new Exception("We don't have the proper bills to make change. Call the manager.");
		} 
		
		return change;
	}
	
	public TreeMap<Integer, Integer> makeTransaction(Integer cost, TreeMap<Integer, Integer> paid) throws Exception {
		// Find out how much customer paid
		Integer sum = 0;
		for (Integer key: paid.keySet())
			sum += (key * paid.get(key));
		
		// Initiate change TreeMap
		TreeMap<Integer, Integer> change = new TreeMap<Integer, Integer>();
		
		// If customer paid enough money, add money to register
		if (sum >= cost) {
			for (Integer key: paid.keySet())
				addMoney(key, paid.get(key));
		
			// Make change if necessary
			if (sum > cost) {
				// Make Change
				return makeChange(sum - cost);
			}
		} else {
			// If customer doesn't pay enough
			throw new Exception("You didn't pay enough money");
		}
		return new TreeMap<Integer, Integer>();
	}
	
	public String toString() {
		return "Bills in the register: \n twenties: " + bills.get(20) + "\n tens: " + 
				bills.get(10) + "\n fives: " + bills.get(5) + "\n twos: "
				+ bills.get(2) + "\n singles: " + bills.get(1);
	}
	


	public static void main(String[] args) throws Exception {
		Map<Integer, Integer> cash = new TreeMap<Integer, Integer>();
		cash.put(20, 2);
		cash.put(10, 4);
		cash.put(5, 6);
		cash.put(2, 4);
		cash.put(1, 10);
		
//		cash.put(20, 1);
//		cash.put(10, 1);
//		cash.put(5, 1);
//		cash.put(2, 4);
//		cash.put(1, 0);
		Register cr = new Register(cash);
		System.out.println(cr.getTotal());
		System.out.println(cr);
		
		cr.removeMoney(20, 1);
		cr.removeMoney(10, 4);
		cr.removeMoney(5, 3);
		cr.removeMoney(1, 10);
		
		System.out.println(cr.getTotal());
		System.out.println(cr);
		
		
		cr.makeChange(11);
//		cr.makeChange(12);
		System.out.println(cr.getTotal());
		System.out.println(cr);
		
		
		cr.makeChange(14);
		
		
//		TreeMap<Integer, Integer> customerPaid = new TreeMap<Integer, Integer>();
//		customerPaid.put(5, 1);
//		
//		System.out.println(cr.makeTransaction(1, customerPaid));
//		
//		System.out.println(cr.bills);

	}

}
