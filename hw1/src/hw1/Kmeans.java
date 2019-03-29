package hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kmeans {
	int k=3;
	Matrix m;
	public Kmeans(Matrix k) {
		m=k;
	}
	
	public HashMap cluster(String n) {
		HashMap<double[],Integer> map=new HashMap<double[], Integer>(), last=new HashMap<double[], Integer>();
		boolean c=false;
		int len=m.a.length;
		double[] a1= {0,1},a2= {1,0},a3= {1,1};
		double c1=0,c2=0,c3=0;
		while(c==false) {
			c=true;
			for (int i=0; i<len; i++) {
				if (n=="e") {
				if (euclidean(a1,m.a[i])<euclidean(a2,m.a[i])) {
					if (euclidean(a1,m.a[i])<euclidean(a3,m.a[i])) {
						map.put(m.a[i],1);
					}else {
						map.put(m.a[i],3);
					}
				}else {
					if (euclidean(a2,m.a[i])<euclidean(a3,m.a[i])) {
						map.put(m.a[i],2);
					}else {
						map.put(m.a[i],3);
					}
				}
				}
				if (n=="cos") {
					if (cosine(a1,m.a[i])<cosine(a2,m.a[i])) {
						if (cosine(a1,m.a[i])<cosine(a3,m.a[i])) {
							map.put(m.a[i],1);
						}else {
							map.put(m.a[i],3);
						}
					}else {
						if (cosine(a2,m.a[i])<cosine(a3,m.a[i])) {
							map.put(m.a[i],2);
						}else {
							map.put(m.a[i],3);
						}
					}
					}
			}
			for (double[] key: map.keySet()) {
				switch(map.get(key)) {
				case 1:
					a1[0]=a1[0]+key[0];
					a1[1]=a1[1]+key[1];
					c1+=1;
					break;
				case 2:
					a2[0]=a2[0]+key[0];
					a2[1]=a2[1]+key[1];
					c2+=1;
					break;
				case 3:
					a3[0]=a3[0]+key[0];
					a3[1]=a3[1]+key[1];
					c3+=1;
					break;
				
				}
				if (map.get(key)!=last.get(key)) {
					c=false;
					last.put(key,map.get(key));
				}				
			}
			a1[0]=a1[0]/c1;
			a1[1]=a1[1]/c1;
			a2[0]=a2[0]/c2;
			a2[1]=a2[1]/c2;
			a3[0]=a3[0]/c3;
			a3[1]=a3[1]/c3;
			c1=0;
			c2=0;
			c3=0;
			map.clear();
		}
		return last;
	}
	
	public double euclidean(double[] x, double[] y) {
		return Math.sqrt(Math.pow(x[0]-y[0], 2)+Math.pow(x[1]-y[1], 2));
	}
	
	public double cosine(double[] x, double[] y) {
		return (x[0]*y[0]+x[1]*y[1])/(Math.sqrt((x[0]*x[0]+x[1]*x[1]))*Math.sqrt((y[0]*y[0]+y[1]*y[1])));
	}

}
