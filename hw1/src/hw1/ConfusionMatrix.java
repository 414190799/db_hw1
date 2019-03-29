package hw1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfusionMatrix {
	double[][] m;
	public ConfusionMatrix(HashMap<double[],Integer> l,Matrix k) {
		double[][] res=new double[3][3];
		for (int i=0; i<3; i++) {
			for (int j=0; j<8; j++) {
				res [i][l.get(k.a[8*i+j])-1]+=1;
			}
		}
		m=res;
	}
	
	public void print() {
		for (double[] a:m) {
			for (double n:a) {
				System.out.print(n+"   ");
			}
			System.out.println();
		}
	}
	public void adjust() {
		double[] t;
		t=m[0];
		m[0]=m[1];
		m[1]=m[2];
		m[2]=t;
	}
	public double precision() {
		double sum_t=0,sum_b=0;
		for (int i=0; i<3;i++) {
			for (int j=0; j<3; j++) {
				if (i>=j) {
					if (i==j) {sum_t+=m[i][j];}
					sum_b+=m[i][j];
				}
			}
		}
		System.out.println("Precision is: "+sum_t/sum_b);
		return sum_t/sum_b;
	}

}
