import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        /*
        1. Find the depth of all nodes and store in an array depth[]
        2. Return the max value for the height of the tree.

        Determine the depth of a node at index i:
        1. If it is the root, depth is 1.
        2. If the depth of parent[i] is evaluated, depth[i] is depth[parent[i]]+1
        3. Otherwise, recur for parent and do like above.

        * */

        private void fillDepth(int[] parent, int i, int[] depth) {
            // Base case: if depth[i] is already filled
            if (depth[i] != 0) {
                return;
            }

            if (parent[i] == -1) {
                depth[i] = 1;
                return;
            }

            // If depth of parent is not evaluated before, then evaluate the depth of the parent first
            if (depth[parent[i]] == 0) {
                fillDepth(parent, parent[i], depth);
            }

            // Depth of a node is depth of parent + 1
            depth[i] = depth[parent[i]] + 1;
        }

        int computeHeight() {

            // Create a list to store depth of all elements
            int[] depth = new int[n];
            Arrays.fill(depth, 0);

            // Do for all nodes of the tree
            for (int i = 0; i < n; i++) {
                fillDepth(parent, i, depth);
            }

            int maxHeight = depth[0];
            for (int depthEle : depth) {
                if (depthEle > maxHeight) {
                    maxHeight = depthEle;
                }
            }
            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
