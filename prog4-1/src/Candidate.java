import java.util.Scanner;

public class Candidate {
    
    int id, cost;
    float benefit;
    

    public Candidate(int x, int y, float z) {
	   id = x;
	   cost = y;
	   benefit = z;
	}

    public Candidate() {
	   id = 0;
	   cost = 0;
	   benefit = (float)0.0;
    }

    public void Update(int x, int y, float z) {
	   id = x;
	   cost = y;
	   benefit = z;
	}

    int Id() {
	return id;
    }	

    int Cost() {
	   return cost;
	}

    float Benefit() {
	   return benefit;
	}


    public void print() {
	System.out.print("(" + id + " " + cost + " " + benefit + ")");

    }
}

