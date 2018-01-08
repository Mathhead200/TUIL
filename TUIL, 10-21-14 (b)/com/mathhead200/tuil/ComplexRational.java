package com.mathhead200.tuil;

import com.mathhead200.BigRational;


@SuppressWarnings("serial")
public class ComplexRational extends Number
{
	private BigRational a;
	private BigRational b;
	
	
	public static ComplexRational ZERO = new ComplexRational(BigRational.ZERO, BigRational.ZERO);
	public static ComplexRational ONE = new ComplexRational(BigRational.ONE, BigRational.ZERO);
	public static ComplexRational I = new ComplexRational(BigRational.ZERO, BigRational.ONE);
	
	
	public ComplexRational(BigRational a, BigRational b) {
		this.a = a;
		this.b = b;
	}
	
	
	public BigRational real() {
		return a;
	}
	
	public BigRational img() {
		return b;
	}
	
	public BigRational normSq() {
		return a.multiply(a).add( b.multiply(b) );
	}
	
	public double norm() {
		return Math.sqrt( normSq().doubleValue() );
	}
	
	public double argument() {
		return Math.atan2( img().doubleValue(), real().doubleValue() );
	}
	
	public ComplexRational add(ComplexRational z) {
		return new ComplexRational( a.add(z.a), b.add(z.b) );
	}
	
	public ComplexRational negate() {
		return new ComplexRational( a.negate(), b.negate() );
	}
	
	public ComplexRational subtract(ComplexRational z) {
		return new ComplexRational( a.subtract(z.a), b.subtract(z.b) );
	}
	
	public ComplexRational mutiply(BigRational x) {
		return new ComplexRational( a.multiply(x), b.multiply(x) );
	}
	
	public ComplexRational multiply(ComplexRational z) {
		return new ComplexRational( a.multiply(z.a).subtract(b.multiply(z.b)), a.multiply(z.b).add(b.multiply(z.a)) );
	}
	
	public ComplexRational conjugate() {
		return new ComplexRational( a, b.negate() );
	}
	
	public ComplexRational divide(BigRational x) {
		return new ComplexRational( a.divide(x), b.divide(x) );
	}
	
	public ComplexRational divide(ComplexRational z) {
		return multiply(z.conjugate()).divide(z.normSq());
	}
	
	public ComplexRational reciprocal() {
		return conjugate().divide(normSq());
	}
	
	public ComplexRational pow(int n) {
		if( n == 0 )
			return ComplexRational.ONE;
		if( n < 0 )
			return reciprocal().pow(-n);
		ComplexRational z = this;
		while( --n > 0 )
			z = z.multiply(this);
		return z;
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append( a.toPointString() );
		if( !b.equals(BigRational.ZERO) ) {
			str.append('+');
			str.append( b.toPointString() );
			str.append('i');
		}
		return str.toString();
	}
	
	
	public int intValue() {
		return ((Double) norm()).intValue();
	}

	public long longValue() {
		return ((Double) norm()).longValue();
	}

	public float floatValue() {
		return ((Double) norm()).floatValue();
	}

	public double doubleValue() {
		return norm();
	}
}
