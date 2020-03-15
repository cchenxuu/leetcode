class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int v = 0;
        int[][][] grid3d = new int[n][n][50];
        int total = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                total += grid[x][y];
                if (grid[x][y] > v) {
                    v = grid[x][y];
                }
                for (int z = 0; z < 50; z++) {
                    if (z < grid[x][y]) {
                        grid3d[x][y][z] = 1;
                    } else {
                        grid3d[x][y][z] = 0;
                    }
                }
            }
        }
        // System.out.println("total : " + total);
        int overlay = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int z = 0; z < v; z++) {
                    if ((x != 0) && (grid3d[x-1][y][z] * grid3d[x][y][z] > 0)) {
                        overlay++;
                    }
                    // System.out.printf("x %d, y %d, z %d : %d\n", x, y, z, grid3d[x][y][z]);
                }
            }
        }
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                for (int z = 0; z < v; z++) {
                    if ((y != 0) && (grid3d[x][y-1][z] * grid3d[x][y][z] > 0)) {
                        overlay++;
                    }
                }
            }
        }
        for (int z = 0; z < v; z++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if ((z != 0) && (grid3d[x][y][z-1] * grid3d[x][y][z] > 0)) {
                        overlay++;
                    }
                }
            }
        }
        // System.out.println("overlay : " + overlay);
        return 6 * total - 2 * overlay;
    }
}