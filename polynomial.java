package poly;
import java.io.*;
import java.util.Scanner;

/**
 *implements a polynomial.
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from input (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials     
	 */
	public static Node add(Node poly1, Node poly2) {


		if(poly1 == null){
			return poly2;
		}else if (poly2 == null){
			return poly1;
		}
		 Node result = null;
		int size = 0;
		Node polyptr1 = poly1;
		Node polyptr2 = poly2;

		while(polyptr1 != null || polyptr2 != null)
		{
			if((polyptr1 != null && polyptr2 != null) && polyptr1.term.degree == polyptr2.term.degree )
			{
				result = new Node(polyptr1.term.coeff + polyptr2.term.coeff, polyptr1.term.degree, result);
				polyptr1 = polyptr1.next;
				polyptr2 = polyptr2.next;
				size++;
			}
			else
			{
				if( polyptr1 == null || (polyptr2 != null && polyptr1.term.degree > polyptr2.term.degree))
				{
					result = new Node(polyptr2.term.coeff, polyptr2.term.degree, result);
					polyptr2 = polyptr2.next;
					size++;
				
				}
				else
				{
					result = new Node(polyptr1.term.coeff, polyptr1.term.degree, result);
					polyptr1 = polyptr1.next;
					size ++;
					
				}

			}
			

		}
		Node resultptr = result;
		int temp = 0;
		while(resultptr != null)
		{	
			if(resultptr.term.coeff == 0){
				temp ++;
			}
			resultptr = resultptr.next;
		}
		if(temp == size){
			Node i = new Node (0,0,null);
			return  i;
		}
		return reverse(result);
	}
		 
		
	
		
	
	
	
	/**
	 * Returns the product of two polynomials
	 */
	public static Node multiply(Node poly1, Node poly2) {


		
		Node polyptr1 = poly1;
		Node mul = null;
		while(polyptr1 != null){
			Node result = null;
			Node polyptr2 = poly2;
			while(polyptr2 != null)
			{
				result = new Node (polyptr1.term.coeff * polyptr2.term.coeff, polyptr1.term.degree + polyptr2.term.degree, result);
				polyptr2 = polyptr2.next;
			}
			result = reverse(result);
			mul = add(result,  mul);
			polyptr1 = polyptr1.next;

		}
		
		
	    return mul;	
	}
	
	/**
	 * Evaluates a polynomial at a given value.
	 */
	public static float evaluate(Node poly, float x) {


		Node polyPtr = poly;
		int temp = 0;
		while(polyPtr != null){
			temp += (Math.pow(x, polyPtr.term.degree) * polyPtr.term.coeff);
			polyPtr = polyPtr.next;
		}
	    return temp;
	}
	
	/**
	 * Returns string representation of a polynomial
	
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
	
	private static Node reverse(Node p) {
		Node ret=null;
		while (p != null) {
			ret = new Node(p.term.coeff, p.term.degree, ret);
			p = p.next;
		}
		return ret;
	}
}
