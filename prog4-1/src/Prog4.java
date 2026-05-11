import java.util.ArrayList;

public class Prog4 {


   public static int[] Prog4(Candidate[] c, int budget, int pos, int k) {

      // dp table -> rows are positions, cols are remaining budget
      // basically knapsack
      float[][] dp = new float[pos + 1][budget + 1];
      int[][] choice = new int[pos + 1][budget + 1]; // tracks which candidate we picked

      for (int row = 1; row <= pos; row++) {
         // starting index for this positions candidates
         int base = (row - 1) * k;

         for (int column = 0; column <= budget; column++) {
            // dont pick anyone for this postiion
            dp[row][column] = dp[row - 1][column];
            choice[row][column] = -1;

            // try each candidate for this position
            for (int j = 0; j < k; j++) {
               int weight = c[base + j].Cost();

               if (weight <= column) {
                  float val = dp[row - 1][column - weight] + c[base + j].Benefit();

                  if (val > dp[row][column]) {
                     dp[row][column] = val;
                     choice[row][column] = base + j;
                  }
               }
            }
         }
      }

      // go back to find which candidates we picked
      ArrayList<Integer> selected = new ArrayList<>();
      int w = budget;

      for (int row = pos; row >= 1; row--) {
         if (choice[row][w] != -1) {
            selected.add(choice[row][w]);
            w -= c[choice[row][w]].Cost();
         }
      }

      int[] result = new int[selected.size()];

      for (int i = 0; i < selected.size(); i++)
         result[i] = selected.get(i);
      return result;
   }


   public static int[] Prog4_Extra(Candidate[] c, int budget, int pos, int k) {

      // added 3d  to track if we used our skip or not
      float[][][] dp = new float[pos + 1][budget + 1][2];
      int[][][] choice = new int[pos + 1][budget + 1][2];

      for (int row = 1; row <= pos; row++) {
         int base = (row - 1) * k;

         for (int column = 0; column <= budget; column++) {
            for (int s = 0; s <= 1; s++) {
               dp[row][column][s] = (s > 0) ?
                       Math.max(dp[row-1][column][s], dp[row-1][column][s-1]) :
                       dp[row-1][column][s];
               choice[row][column][s] = -1;

               for (int j = 0; j < k; j++) {
                  int weight = c[base + j].Cost();

                  if (weight <= column) {
                     float val = dp[row - 1][column - weight][s] + c[base + j].Benefit();

                     if (val > dp[row][column][s]) {
                        dp[row][column][s] = val;
                        choice[row][column][s] = base + j;
                     }
                  }
               }
            }
         }
      }

      // check if using the skip gave us a better answer
      int bestS = (dp[pos][budget][1] >= dp[pos][budget][0]) ? 1 : 0;

      ArrayList<Integer> selected = new ArrayList<>();
      int w = budget;
      int s = bestS;

      for (int row = pos; row >= 1; row--) {
         if (choice[row][w][s] != -1) {
            selected.add(choice[row][w][s]);
            w -= c[choice[row][w][s]].Cost();
         } else if (s > 0) {
            s--;
         }
      }

      int[] result = new int[selected.size()];

      for (int i = 0; i < selected.size(); i++)
         result[i] = selected.get(i);
      return result;
   }

}