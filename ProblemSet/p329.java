import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int count;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int column = matrix[0].length;

        // /*
        //  * don't use minimum value as start point
        //  */
        // // System.out.println("n : " + n);
        // int min = n * n;
        // for (int x = 0; x < n; x++) {
        //     for (int y = 0; y < n; y++) {
        //         if (matrix[x][y] <= min) {
        //             min = matrix[x][y];
        //             // System.out.printf("find x:%d, y:%d, min:%d\n", x, y, min);
        //         }
        //     }
        // }
        // ArrayList<ArrayList<Point>> steps = new ArrayList<>();
        // for (int x = 0; x < n; x++) {
        //     for (int y = 0; y < n; y++) {
        //         if (matrix[x][y] == min) {
        //             // System.out.printf("match x:%d, y:%d, min:%d\n", x, y, min);
        //             ArrayList<Point> path = new ArrayList<>();
        //             path.add(new Point(x, y));
        //             // System.out.println("path : " + stringPath(path));
        //             steps.add(path);
        //         }
        //     }
        // }
        // // System.out.println("Initial " + stringSteps(steps));
        // this.count = 0;
        // steps.forEach(path -> {
        //     path.forEach(point -> {
        //         System.out.println("process " + point);
        //         ArrayList<ArrayList<Point>> result = nextSteps(n, matrix, point, null);
        //         System.out.println("result : " + stringSteps(result));
        //         result.forEach(p -> {
        //             // System.out.println("count : " + p.size());
        //             if (p.size() > count) {
        //                 count = p.size();
        //             }
        //         });
        //     });
        // });

        /*
         * use each position as start point
         */
        this.count = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                Point point = new Point(x, y);
                // System.out.println("process " + point);
                ArrayList<ArrayList<Point>> result = nextSteps(row, column, matrix, point, null);
                // System.out.println("result : " + stringSteps(result));
                if (result != null) {
                    result.forEach(p -> {
                        // System.out.println("count : " + p.size());
                        if (p.size() > this.count) {
                            this.count = p.size();
                        }
                    });
                }
            }
        }

        return this.count + 1;
    }

    public ArrayList<ArrayList<Point>> nextSteps(int row, int column, int[][] matrix, Point current, Point preview) {
        ArrayList<ArrayList<Point>> steps = new ArrayList<>();
        for (String dir : Arrays.asList("up", "down", "left", "right")) {
            int xx = current.x;
            int yy = current.y;
            switch (dir) {
            case "up":
                if (xx > 0) {
                    xx -= 1;
                }
                break;
            case "down":
                if (xx < row - 1) {
                    xx += 1;
                }
                break;
            case "left":
                if (yy > 0) {
                    yy -= 1;
                }
                break;
            case "right":
                if (yy < column - 1) {
                    yy += 1;
                }
                break;
            default:
                return null;
            }
            // System.out.printf("Move %s to %s from %s\n", dir, new Point(xx, yy), current);
            if (((xx == current.x) && (yy == current.y)) || (new Point(xx, yy)).equals(preview)) {
                // System.out.println("  wrong dir");
                continue;
            }
            // System.out.printf("Compare value %d on %s and %d on %s\n", matrix[xx][yy], new Point(xx, yy), matrix[current.x][current.y], current);
            if (matrix[current.x][current.y] < matrix[xx][yy]) {
                Point next = new Point(xx, yy);
                ArrayList<Point> p = new ArrayList<Point>();
                p.add(next);
                // System.out.println("add new point " + p);
                ArrayList<ArrayList<Point>> s = nextSteps(row, column, matrix, next, current);
                if (s != null) {
                    s.forEach(path -> {
                        ArrayList<Point> c = new ArrayList<Point>(p);
                        c.addAll(path);
                        // System.out.println("add new path " + p);
                        steps.add(c);
                    });
                } else {
                    steps.add(p);
                }
                // System.out.println("add new steps " + steps);
            } else {
                // System.out.println("  wrong value");
            }
        }
        if (steps.size() == 0) {
            return null;
        } else {
            return steps;
        }
    }

    public class Point implements Cloneable {
        public int x;
        public int y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + this.x + "," + this.y + ")";
        }

        public boolean equals(Point point) {
            if (point == null) {
                return false;
            }
            if (this.x == point.x && this.y == point.y) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected Point clone() {
            Point clone = new Point(this.x, this.y);
            return clone;
        }
    }

    public String stringPath(ArrayList<Point> path) {
        String str = "Path: ";
        for (int i = 0; i < path.size(); i++) {
            str += path.get(i) + " -> ";
        }
        str = str + "null";
        return str;
    }

    public String stringSteps(ArrayList<ArrayList<Point>> steps) {
        String str = "Steps:\n";
        if (steps != null) {
            for (int i = 0; i < steps.size(); i++) {
                str += stringPath(steps.get(i)) + ";\n";
            }
        }
        return str;
    }
}