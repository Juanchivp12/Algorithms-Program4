import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;
import java.util.Arrays;

public class Prog4Test {
    
    public static void main(String[] args) {

	String filename = args[0];
	int p = 0, b = 0, k = 0;

        try (Scanner fin = new Scanner(new File(filename))) {

		p = fin.nextInt();
		b = fin.nextInt();
		k = fin.nextInt();
	        Candidate c[] = new Candidate[p*k];
			
		for (int i = 0; i < p * k; i++)
		{
		    int id = fin.nextInt();
		    int a = fin.nextInt();
		    float benefit = fin.nextFloat();
		    c[i] = new Candidate(id, a, benefit);
		}

		int [] res = Prog4.Prog4(c, b, p, k);
		Arrays.sort(res);
		float sum = (float)0.0;
		int cost = 0;
		System.out.print("Base case : ");
		for (int i = 0; i < res.length  ;  i++) {
			if ((res[i] < 0) ||(res[i] >= p*k)){
				System.out.println("Error " + " position " + i + " returned index " + res[i]);
				System.exit(0);
			}
			System.out.print(c[res[i]].Id());
			System.out.print(" ");
			sum = sum + c[i].Benefit();
			cost = cost + c[i].Cost();

		}
		System.out.println(" Cost : " + cost + "   Benefit : " + sum);

	 	int [] res2 = Prog4.Prog4_Extra(c, b, p, k);
		Arrays.sort(res2);
		sum = (float)0.0;
		cost = 0;
		System.out.print("Extra : ");
		for (int i = 0; i < res2.length  ;  i++) {
			if ((res2[i] < 0) ||(res2[i] >= p*k)){
				System.out.println("Error " + " position " + i + " returned index " + res2[i]);
				System.exit(0);
			}
			System.out.print(c[res2[i]].Id());
			System.out.print(" ");
			sum = sum + c[i].Benefit();
			cost = cost + c[i].Cost();

		}
		System.out.println(" Cost : " + cost + "   Benefit : " + sum);


	} catch (FileNotFoundException ex) {
		System.out.println("File not found");

	}

     }
}

