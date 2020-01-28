
public class DisjointSet {
	static int[] parents;
	private static void union(int a, int b) {
		int RootA = find(a);
		int RootB = find(b);
		if(RootA!=RootB) {
			parents[RootB] = RootA;
		}
	}

	private static int find(int a) {
		if(parents[a]<0) return a;
		return parents[a] = find(parents[a]);
	}
}
