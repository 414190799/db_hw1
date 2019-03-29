package hw1;
import java.util.*;
public class run {
	public static void main(String[] args) {
		List<Map<String,Integer>> k=new ArrayList<Map<String,Integer>>();
		HashMap<double[],Integer> map=new HashMap<double[], Integer>();
		process data=new process();
		System.out.println("Topics(by frequence):");
		try {
			k=data.pro();
		}catch (Exception e) {
			System.out.println(e);
		}
		double[][] t= {{1.11}};
		Matrix m=new Matrix(t);
		m.buildMatrix(k);//m is the document-term matrix
		Tfidf tfTool=new Tfidf();
		m=tfTool.transform(m);//new m is the transformed matrix.
		PCA pca=new PCA();
		m=pca.redu(m);//recduction
		Kmeans x=new Kmeans(m);
		map=x.cluster("e");//change e to cos we use cosine similarity
		System.out.println("Confusion Matrix:");
		ConfusionMatrix con=new ConfusionMatrix(map,m);//Confusion Matrix
		con.print();
		System.out.println("Now we can adjust the order of columns to make the matrix look better");
		con.adjust();
		con.print();
		con.precision();
		
		
		/*for (double[] key: map.keySet()) {
			System.out.print(key[0]+"  "+key[1]);
			System.out.print("   "+map.get(key));
			System.out.println();
		}
		/*int count=0;
		for (int i=0; i<2000; i++) {
			if (m.a[23][i]!=0) {
				count+=1;
			}
		}
		System.out.println(count);*/
	}
	
}

