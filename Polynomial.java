package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
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
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
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
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node polyAns=new Node(0,0,null); 
		Node ptrPoly1=poly1; 
		Node ptrPoly2=poly2;
		Node polyAns2=polyAns;
		
		
		if ((ptrPoly1==null)&&(ptrPoly2==null)) {
			return null;
		}
		else if (ptrPoly1==null) {
			return ptrPoly2;
		}
		else if (ptrPoly2==null) {
			return ptrPoly1;
		}
		
		while (ptrPoly1 != null && ptrPoly2!=null) {
			if (ptrPoly1.term.degree==ptrPoly2.term.degree) {
				Node polyTemp=new Node(0,0,null);
				polyTemp.term.coeff=ptrPoly2.term.coeff+ptrPoly1.term.coeff;
				polyTemp.term.degree=ptrPoly1.term.degree;
				if (polyTemp.term.coeff==0) {
					ptrPoly1=ptrPoly1.next;
					ptrPoly2=ptrPoly2.next;
				}
				else {
					polyAns2.next=polyTemp;
					ptrPoly1=ptrPoly1.next;
					ptrPoly2=ptrPoly2.next;
					polyAns2=polyAns2.next;
				}
			}
			else if (ptrPoly2.term.degree<ptrPoly1.term.degree) {
				Node polyTemp=new Node(ptrPoly2.term.coeff,ptrPoly2.term.degree,null);
				polyAns2.next=polyTemp;
				ptrPoly2=ptrPoly2.next;
				polyAns2=polyAns2.next;
			}
			else if (ptrPoly1.term.degree<ptrPoly2.term.degree) {
				Node polyTemp=new Node(ptrPoly1.term.coeff,ptrPoly1.term.degree,null);
				polyAns2.next=polyTemp;
				ptrPoly1=ptrPoly1.next;
				polyAns2=polyAns2.next;
			}
		}
		
		while (ptrPoly1!=null) {
			Node polyTemp=new Node(ptrPoly1.term.coeff,ptrPoly1.term.degree,null);
			polyAns2.next=polyTemp;
			ptrPoly1=ptrPoly1.next;
			polyAns2=polyAns2.next;
		}
		
		while (ptrPoly2!=null) {
			Node polyTemp=new Node(ptrPoly2.term.coeff,ptrPoly2.term.degree,null);
			polyAns2.next=polyTemp;
			ptrPoly2=ptrPoly2.next;
			polyAns2=polyAns2.next;
		}
		
		polyAns=polyAns.next;
		return polyAns;	
	} 

	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node product = null; 
		Node product2= new Node(0,0,null);
		
		if (poly1==null || poly2==null) {
			return null;
		}
		
		for (Node ptrPoly1=poly1;ptrPoly1!=null; ptrPoly1=ptrPoly1.next) {
			Node frontNode=new Node(0,0,null);
			product2=frontNode;
			for (Node ptrPoly2=poly2;ptrPoly2!=null; ptrPoly2=ptrPoly2.next) {
				Node tempNode=new Node(0,0,null);
				tempNode.term.coeff=ptrPoly1.term.coeff*ptrPoly2.term.coeff;
				tempNode.term.degree=ptrPoly1.term.degree+ptrPoly2.term.degree;
				product2.next=tempNode;
				product2=product2.next;	
			}
			frontNode=frontNode.next;
			product=add(product,frontNode);
			System.out.println(toString(product));
		}
		return product;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float total=0;
		while (poly != null) {
			total=(float) (total+(poly.term.coeff*Math.pow(x, poly.term.degree)));
			poly=poly.next;
		}
		return total;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
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

}
