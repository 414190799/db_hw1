package hw1;


public class Tfidf {
	public Matrix transform(Matrix k){
		double[][] m=k.a;
		int c=0,depth = m.length,
		len=m[0].length;
		int[] count=new int[depth];
		for (int i=0; i<depth; i++) {
			for (int j=0; j<len; j++) {
				if (m[i][j]>=1) {
					c+=m[i][j];
				}
			}
			count[i]=c;
			c=0;
		}
		
		for (int j=0; j<len; j++) {
			for (int i=0; i<depth; i++) {
				if (m[i][j]>=1) {
					c+=1;
				}
			}
			for (int i=0; i<depth; i++) {
				if (m[i][j]>=1) {
					m[i][j]=(m[i][j]/count[i])*Math.log(depth/c);
				}
			}
			c=0;
		}
		System.out.println("TFIDF Applied");
		Matrix result=new Matrix(m);
		return result;
	}
}
