import java.util.List;

import mjtb49.hashreversals.ChunkRandomReverser;
import mjtb49.hashreversals.MultiChunkHelper;

public class Main {
	public static void findWorldSeeds(long s1, long s2, int n1, int n2) {
		s1 = (s1 ^ 25214903917L) - 60007;
		s2 = (s2 ^ 25214903917L) - 60007;
		ChunkRandomReverser crr = new ChunkRandomReverser();
		List<MultiChunkHelper.Result> results = crr.getWorldseedFromTwoChunkseeds(s1, s2, n1, n2);
		for (MultiChunkHelper.Result result : results) {
			System.out.println(Long.toString(result.getBitsOfSeed()) + " " + Integer.toString(result.getX()) + " 64 " + Integer.toString(result.getZ()));
		}
	}

	public static void findWorldSeeds(long s1, long s2) {
		CactusSimulation sim = new CactusSimulation(10, 63);
		sim.populate(s1);
		int pos = sim.currentHighestPos;
		int neighbor = (int) ((s2 >> 48) & ((1L << 10) - 1));
		s2 &= (1L << 48) - 1;
		if (neighbor - 16 == pos) findWorldSeeds(s1, s2, -1, 0);
		if (neighbor + 16 == pos) findWorldSeeds(s1, s2, 1, 0);
		if (neighbor - 512 == pos) findWorldSeeds(s1, s2, 0, -1);
		if (neighbor + 512 == pos) findWorldSeeds(s1, s2, 0, 1);
		if (neighbor - 16 - 512 == pos) findWorldSeeds(s1, s2, -1, -1);
		if (neighbor - 16 + 512 == pos) findWorldSeeds(s1, s2, -1, 1);
		if (neighbor + 16 - 512 == pos) findWorldSeeds(s1, s2, 1, -1);
		if (neighbor + 16 + 512 == pos) findWorldSeeds(s1, s2, 1, 1);
	}

	public static void main(String[] args) {
		findWorldSeeds(Long.parseLong(args[0]), Long.parseLong(args[1]));
	}
}