import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class floydwarshall {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int line = Integer.parseInt(br.readLine());
		int map[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = 1000000;
			}
		}

		for (int i = 0; i < line; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			if (map[s][e] > m) {
				map[s][e] = m;
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					map[i][j] = 0;
				}
				if (map[i][j] == 1000000) {
					map[i][j] = 0;
				}
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}