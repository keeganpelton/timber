import java.util.ArrayList;
import java.util.List;

public class Timber {

    public static int T(int[] length, int i, int j) {

        if (i == j) {
            return length[i];
        }
        if (j == i+1) {
            return Math.max(length[i], length[j]);
        }

        int left = length[i] + Math.min(T(length,i+2, j), T(length,i+1, j-1));
        int right = length[j] + Math.min(T(length, i, j-2), T(length, i+1, j-1));

        return Math.max(left, right);

    }

    public static int TimberDP(int[] length) {

        int n = length.length;

        int[][] T = new int[n][n];
        for (int i = 0; i < n; i++) {
            T[i][i] = length[i];
        }

        for (int i = 0; i < n-1; i++) {
            T[i][i+1] = Math.max(length[i], length[i+1]);
        }

        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i + diff < n; i++) {
                int j = i + diff;
                int left = length[i] + Math.min(T[i+2][j], T[i+1][j-1]);
                int right = length[j] + Math.min(T[i][j-2], T[i+1][j-1]);
                T[i][j] = Math.max(left, right);
            }
        }

        return T[0][n-1];

    }

    public static List<Integer[]> TimberDPTraceback(int[] length) {

        int n = length.length;

        int[][][] T = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            T[i][i][0] = length[i];
        }

        for (int i = 0; i < n-1; i++) {
            T[i][i+1][0] = Math.max(length[i], length[i+1]);
        }

        Integer[] path = new Integer[Math.ceilDiv(n, 2)];

        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i + diff < n; i++) {
                int j = i + diff;
                int left = length[i] + Math.min(T[i+2][j][0], T[i+1][j-1][0]);
                int right = length[j] + Math.min(T[i][j-2][0], T[i+1][j-1][0]);

                if (left > right || left == right) {
                    T[i][j][0] = left;
                    T[i][j][1] = 0;
                }
                else {
                    T[i][j][0] = right;
                    T[i][j][1] = 1;
                }


                T[i][j][0] = Math.max(left, right);
            }
        }

        List<Integer[]> total_path = new ArrayList<>();
        total_path.add(new Integer[]{T[0][n-1]});

        return total_path;

    }

}