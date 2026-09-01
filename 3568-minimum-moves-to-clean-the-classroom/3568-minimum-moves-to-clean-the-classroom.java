import java.util.*;

class Solution {
    static class State {
        int x, y, energy, mask, steps;

        State(int x, int y, int energy, int mask, int steps) {
            this.x = x;
            this.y = y;
            this.energy = energy;
            this.mask = mask;
            this.steps = steps;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        char[][] grid = new char[m][n];

        int startX = 0, startY = 0;

        // Assign index to each litter
        Map<Integer, Integer> litterIndex = new HashMap<>();
        int idx = 0;

        for (int i = 0; i < m; i++) {
            grid[i] = classroom[i].toCharArray();

            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }

                if (grid[i][j] == 'L') {
                    litterIndex.put(i * n + j, idx++);
                }
            }
        }

        int totalMask = (1 << idx) - 1;

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << idx];

        Queue<State> q = new LinkedList<>();

        q.offer(new State(startX, startY, energy, 0, 0));
        visited[startX][startY][energy][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.mask == totalMask) {
                return cur.steps;
            }

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                    continue;

                if (grid[nx][ny] == 'X')
                    continue;

                if (cur.energy == 0)
                    continue;

                int newEnergy = cur.energy - 1;
                int newMask = cur.mask;

                // collect litter
                if (grid[nx][ny] == 'L') {
                    int bit = litterIndex.get(nx * n + ny);
                    newMask |= (1 << bit);
                }

                // reset energy
                if (grid[nx][ny] == 'R') {
                    newEnergy = energy;
                }

                if (!visited[nx][ny][newEnergy][newMask]) {
                    visited[nx][ny][newEnergy][newMask] = true;
                    q.offer(new State(
                            nx, ny,
                            newEnergy,
                            newMask,
                            cur.steps + 1
                    ));
                }
            }
        }

        return -1;
    }
}