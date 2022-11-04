import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Equations {
	
	/*
	 * Find the mean of the given an ArrayList<Double> arr.
	 */
	public double mean(ArrayList<Double> arr) {
		double sum = 0.0;
		int size = arr.size();
		for (int i = 0; i < size; i++) {
			sum += arr.get(i);
		}
		return sum / size;
	}
	
	/*
	 * Find the median of a given an ArrayList<Double> arr.
	 */
	public double median(ArrayList<Double> arr) {
		int size = arr.size();
		int mid = size / 2;
		if (size % 2 == 1) {
			return arr.get(mid);
		} else {
			return (arr.get(mid) + arr.get(mid - 1)) / 2.0;
		}
	}
	
	/*
	 * Find the mode of a given an ArrayList<Double> arr.
	 */
	public double mode(ArrayList<Double> arr) {
		ArrayList<Double> nums = new ArrayList<Double>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		for (int i = 0; i < arr.size(); i++) {
			if (!nums.contains(arr.get(i))) {
				nums.add(arr.get(i));
				count.add(1);
			} else {
				int pos = nums.indexOf(arr.get(i));
				count.set(pos, count.get(pos) + 1);
			}
		}
		int max = 0;
		int maxIndex = -1;
		for (int i = 0; i < count.size(); i++) {
			if (count.get(i) > max) {
				max = count.get(i);
				maxIndex = i;
			}
		}
		return nums.get(maxIndex);
	}
	
	/*
	 * Find the deviation given a double value and double mean.
	 */
	public double deviation(double value, double mean) {
		return value-mean;
	}
	
	/*
	 * Finds the variance given an ArrayList<Double> arr.
	 * 
	 * Uses deviation and mean methods.
	 */
	public double variance(ArrayList<Double> arr) {
		double mean = this.mean(arr);
		double iniSum = 0.0;
		for(int i = 0; i < arr.size(); i++) {
			iniSum += Math.pow(this.deviation(mean, arr.get(i)),2);
		}
		return (iniSum / (arr.size()-1));
	}
	
	/*
	 * Find the Standard Deviation given an ArrayList<Double> arr.
	 * 
	 * Uses the variance class.
	 */
	public double standardDeviation(ArrayList<Double> arr){
		double var = this.variance(arr);
		return Math.sqrt(var);
	}
	
	/*
	 * Finds the union between ArrayList<Integer> a and ArrayList<Integer> b.
	 */
	public ArrayList<Integer> union(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			result.add(a.get(i));
		}
		for (int i = 0; i < b.size(); i++) {
			if (!result.contains(b.get(i))) {
				result.add(b.get(i));
			}
		}
		return result;
	}
	
	/*
	 * Finds the intersection between between ArrayList<Integer> a and ArrayList<Integer> b.
	 */
	public ArrayList<Integer> intersection(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			if (b.contains(a.get(i))) {
				result.add(a.get(i));
			}
		}
		return result;
	}
	
	/*
	 * Finds the complement of ArrayList<Integer> a from ArrayList<Integer> source.
	 * 
	 * If source is not bigger than a, then the system will output an empty ArrayList<Integer>.
	 */
	public ArrayList<Integer> compliment(ArrayList<Integer> a, ArrayList<Integer> source) {
		ArrayList<Integer> result = new ArrayList<>();
		if(source.size() < a.size()) {					// source needs to be bigger
			return result;
		}
		for (int i = 0; i < source.size(); i++) {
			if (!a.contains(source.get(i))) {
				result.add(source.get(i));
			}
		}
		return result;
	}
	
	/*
	 * Finds the factorial of int num.
	 */
	public BigInteger factorial(int num) {
		BigInteger answer = BigInteger.valueOf(1);
		while(num > 0) {
			answer.multiply(BigInteger.valueOf(num));
			num--;
		}
		return answer;
	}
	
	/*
	 * Finds the permutation of int n objects into a group of int r size.
	 * 
	 * Uses the factorial method.
	 */
	public double permutation(int n, int r) {
		return new BigDecimal(factorial(n)).divide(new BigDecimal(factorial(n-r)), RoundingMode.HALF_UP).doubleValue();
	}
	
	/*
	 * Finds the partition of int n objects into group sizes determines by
	 * ArrayList<Integer> groups.
	 * 
	 * If group is empty or n is less than the sum of all of the groups, then the method
	 * will output -1.0.
	 * 
	 * Uses the factorial method.
	 */
	public double partition(int n, ArrayList<Integer> groups) {
		if(groups.isEmpty()) {
			return -1.0;
		}
		int sum = 0;
		for(int i = 0; i < groups.size(); i++) {	// makes sure everyone is distributed
			sum += groups.get(i);
		}
		if(sum < n) {								// if total > sum, then add new group
			groups.add(n-sum);
		} else if(sum > n) {						// sum cannot be greater than total
			return -1.0;
		}
		BigInteger denominator = BigInteger.valueOf(1);
		for(int i = 0; i < groups.size(); i++) {
			denominator.multiply(factorial(groups.get(i)));
		}
		return new BigDecimal(factorial(n)).divide(new BigDecimal(denominator), RoundingMode.HALF_UP).doubleValue();
	}
	
	/*
	 * Finds the combination of int n objects into a group of int r size.
	 * 
	 * Uses the factorial method.
	 */
	public double combination(int n, int r) {
		BigInteger den = factorial(r).multiply(factorial(n-r));
		return new BigDecimal(factorial(n)).divide(new BigDecimal(den), RoundingMode.HALF_UP).doubleValue();
	}
	
	/*
	 * Finds the conditional probability of A if B. Input is double AorB, which is the
	 * probability of A or B occurring, and double B, which is the probability of B occurring.
	 */
	public double conditionalProbability(double AorB, double B) {
		return AorB / B;
	}
	
	/*
	 * Find if two events are independent of each other. Inputs are double A, the probability
	 * of A occurring, double B, the probability of B occurring, double AorB, the probability of
	 * A or B occurring, and double AandB, the probability of A and B occurring.
	 * 
	 * Uses conditionalProbability as a helper method.
	 */
	public boolean independentProbability(double A, double B, double AorB, double AandB) {
		if(conditionalProbability(AorB, B) == A){
			return true;
		} else if (conditionalProbability(AorB, A) == B){
			return true;
		} else if(A*B == AandB) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Finds the conditional probability of B_j if A given an ArrayList<Double> AorB that
	 * describes the probability of A and each value of B_i, ArrayList<Double> B that gives the
	 * probability of each value of B_i, and int j, which is the location in B that contains
	 * B_j.
	 * 
	 * If j is not less than AorB's size or if AorB's size is not equal to B's size,
	 * then the method will output -1.0.
	 * 
	 * Uses conditionalProbability as a helper method.
	 */
	public double bayes(ArrayList<Double> AorB, ArrayList<Double> B, int j) {
		if(j >= AorB.size() || AorB.size() != B.size()) {	// if input bad return -1.0
			return -1.0;
		}
		double denominator = 0.0;
		for(int i = 0; i < AorB.size(); i++) {
			denominator += conditionalProbability(AorB.get(i), B.get(i));
		}
		return (conditionalProbability(AorB.get(j), B.get(j)) * B.get(j)) / denominator;
	}
	
	/*
	 * Returns the random variable expectation/mean given an ArrayList<Integer> Y, which
	 * signifies the random variable Y, and ArrayList<Double> py, which signifies the
	 * probabilities of each y value. Y and py are mapped via their indexes, so the second
	 * element in py is the probability of the second element of Y occurring.
	 * 
	 * If the sizes of Y and py are not equal, the method will output -1.0.
	 */
	public double RVexpectation(ArrayList<Double> Y, ArrayList<Double> py) {
		double sum = 0;
		if(Y.size() != py.size()) {					// sizes need to be equal
			return -1.0;
		}
		for(int i = 0; i < Y.size(); i++) {
			sum += Y.get(i) * py.get(i);
		}
		return sum;
	}
	
	/*
	 * Returns the random variable variance given an ArrayList<Integer> Y, which
	 * signifies the random variable Y, and ArrayList<Double> py, which signifies the
	 * probabilities of each y value. Y and py are mapped via their indexes, so the second
	 * element in py is the probability of the second element of Y occurring.
	 * 
	 * If the sizes of Y and py are not equal, the method will output -1.0.
	 * 
	 * Uses the RVexpectation as a helper method.
	 */
	public double RVvariance(ArrayList<Double> Y, ArrayList<Double> py) {
		if(Y.size() != py.size()) {
			return -1.0;
		}
		double expected = RVexpectation(Y, py);
		ArrayList<Double> Yu = new ArrayList<Double>();		// Yu = Y - expected
		for(int i = 0; i < Y.size(); i++) {
			Yu.add(Y.get(i)-expected);
		}
		return Math.pow(RVexpectation(Yu, py), 2);
	}
	
	/*
	 * Returns the random variable standard deviation given an ArrayList<Integer> Y, which
	 * signifies the random variable Y, and ArrayList<Double> py, which signifies the
	 * probabilities of each y value. Y and py are mapped via their indexes, so the second
	 * element in py is the probability of the second element of Y occurring.
	 * 
	 * Uses the RVvariance as a helper method.
	 */
	public double RVstandardDeviation(ArrayList<Double> Y, ArrayList<Double> py) {
		return Math.sqrt(RVvariance(Y, py));
	}
	
	/*
	 * Returns the binomial distribution given double p, the probability of success,
	 * int n, the number of trials, and int y, the number of successful trials.
	 */
	public double binomialDistribution(double p, int n, int y) {
		return combination(n, y)*Math.pow(p, y)*Math.pow(1-y, n-y);
	}
	
	/*
	 * Returns the geometric distribution given double p, the probability of success,
	 * int n, the number of trials, and int y, the number of successful trials. The last
	 * input, int symbol, determines what type of geometric distribution is being used.
	 * See blow:
	 * 
	 * symbol == 1: P(Y<=y)
	 * symbol == 2: P(Y<y)
	 * symbol == 3: P(Y>=y)
	 * symbol == 4: P(Y>y)
	 * any other input will default to: P(Y=y)
	 */
	public double geometricDistrubution(double p, int n, int y, int symbol) {
		if(symbol == 1) {					// P(Y<=y)
			return 1-Math.pow(1-p, y);
		} else if(symbol == 2) {			// P(Y<y)
			return 1-Math.pow(1-p, y-1);
		} else if(symbol == 3) {			// P(Y>=y)
			return Math.pow(1-p, y-1);
		} else if(symbol == 4) {			// P(Y>y)
			return Math.pow(1-p, y);
		} else {							// P(Y=y)
			return Math.pow(1-p, y-1)*p;
		}
		
	}
	
	/*
	 * Returns the expectation/mean of a geometric distribution given double p, the probability
	 * of success.
	 */
	public double GDexpected(double p) {
		return 1/p;
	}
	
	/*
	 * Returns the variance of a geometric distribution given double p, the probability
	 * of success.
	 */
	public double GDvariance(double p) {
		return (1-p)/(p*p);
	}
	
	/*
	 * Returns the standard deviation of a geometric distribution given double p, the
	 * probability of success.
	 * 
	 * Uses GDvariance as a helper method.
	 */
	public double GDstandardDeviation(double p) {
		return Math.sqrt(GDvariance(p));
	}
	
	/*
	 * Determines the hypergeometric distribution from int N, the total number of items,
	 * int r, the number of type 1 items, and int n, the number of items being selected, and
	 * int y, the number of type 1 items we wish to know the probability of selecting.
	 * 
	 * If combination(N, n) turns out to be 0, then the method will output -1.0.
	 * 
	 * Uses combination as a helper method.
	 */
	public double hypergeometricDistribution(int N, int r, int n, int y) {
		if(combination(N, n) != 0.0) {			// cannot / by 0
			return -1.0;
		}
		return (combination(r, y) * combination(N-r, n-y)) / (combination(N, n));
	}
	
	/*
	 * Finds the expected of a hypergeometric distribution given int N, the total number of
	 * items, int r, the number of type 1 items, and int n, the number of items being selected.
	 * 
	 * If N = 0, then the method will output -1.0.
	 */
	public double HGDexpected(int N, int r, int n) {
		if(N == 0) {
			return -1.0;
		}
		return (n*r) / (N + 0.0);
	}
	
	/*
	 * Finds the variance of a hypergeometric distribution given int N, the total number of
	 * items, int r, the number of type 1 items, and int n, the number of items being selected.
	 * 
	 * If N == 0 or N == 1, then the method will output -1.0.
	 */
	public double HGDvariance(int N, int r, int n) {
		if(N <= 1) {
			return -1.0;
		}
		return n*((r+0.0)/N)*((N-r+0.0)/N)*((N-n+0.0)/(N-1));
	}
	
	/*
	 * Finds the standard deviation of a hypergeometric distribution given int N, the total
	 * number of items, int r, the number of type 1 items, and int n, the number of items
	 * being selected.
	 * 
	 * Uses HGDvariance as a helper method.
	 */
	public double HGDstandardDeviation(int N, int r, int n) {
		return Math.sqrt(HGDvariance(N, r, n));
	}
	
	/*
	 * A recursive method to find the poisson distribution given a double k, the number of
	 * incidents, double n, the length of time, int y, the number of successes to calculate the 
	 * probability of, and int sign, the sign that is being used, as described below:
	 * 		1: P(Y < y)
	 * 		1: P(Y <= y)
	 * 		1: P(Y > y)
	 * 		1: P(Y >= y)
	 * 		Else: P(Y = y)
	 * 
	 * Uses the factorial method as a helper method.
	 * 
	 * This variety of the method converts k and n into lamda and then uses the version
	 * of the method that includes lamda.
	 * 
	 * If n = 0, then the method will output -1.0;
	 */
	public double poissonDistribution(double k, double n, int y, int sign) {
		if(n == 0) {
			return -1.0;
		}
		return poissonDistribution(k/n, y, sign);
	}
	
	/*
	 * A recursive method to find the poisson distribution given double lamda, the rate of
	 * incidents per time unit, int y, the number of successes to calculate the 
	 * probability of, and int sign, the sign that is being used, as described below:
	 * 		1: P(Y < y)
	 * 		1: P(Y <= y)
	 * 		1: P(Y > y)
	 * 		1: P(Y >= y)
	 * 		Else: P(Y = y)
	 * 
	 * Uses the factorial method as a helper method.
	 */
	public double poissonDistribution(double lamda, int y, int sign) {
		if(sign == 1) {										// Y < y
			return poissonDistribution(lamda, y-1, 2);
		} else if(sign == 2) {								// Y <= y
			if(y == 0) {
				return poissonDistribution(lamda, y, 0);
			} else {
				return poissonDistribution(lamda, y, 0) + poissonDistribution(lamda, y-1, 2);
			}
		} else if(sign == 3) {								// Y > y
			return 1 - poissonDistribution(lamda, y, 2);
		} else if(sign == 4) {								// Y >= y
			return 1 - poissonDistribution(lamda, y-1, 2);
		} else {											// Y = y,  sign != 1,2,3,4 becomes =
			return BigDecimal.valueOf(Math.exp(lamda*-1)*Math.pow(lamda, y)).divide(new BigDecimal(factorial(y)), RoundingMode.HALF_UP).doubleValue();
		}
	}
	
	/*
	 * Find the expected / variance for a poisson distribution. Since both are the same, this
	 * method serves both purposes. Uses input double k, the number of incidents, and double
	 * n, the length of time.
	 */
	public double PDEV(double k, double n) {
		return k/n;
	}
	
	/*
	 * Find the standard deviation for a poisson distribution. Uses input double k, the
	 * number of incidents, and double n, the length of time.
	 */
	public double PDSD(double k, double n) {
		return Math.sqrt(k/n);
	}
	
	/*
	 * Find the standard deviation for a poisson distribution. Uses input double lamda, the
	 * lamda of the distribution.
	 */
	public double PDSD(double lamda) {
		return Math.sqrt(lamda);
	}
	
	/*
	 * Finds the proportion of total observations that fall within a range using
	 * Tchebysheff’s / Chebyshev’s theorem. Uses double range, the range out from the mean
	 * (ie, if using 3-9 the range is 3 with the mean being 6), the double SD, the standard
	 * deviation, and boolean isGreaterThan, which determines what part of Chebyshev's
	 * theorem to use. If isGreaterThan is true, then it will use the equation that deals
	 * with P() >=. If it is false, it will instead use the equation that deals with P() <=.
	 */
	public double chebyshevTheorem(double range, double SD, boolean isGreaterThan) {
		if(isGreaterThan) {
			return 1.0 - (1.0 / Math.pow(range * SD, 2));
		} else {
			return 1.0 / Math.pow(range * SD, 2);
		}
	}
	
}
