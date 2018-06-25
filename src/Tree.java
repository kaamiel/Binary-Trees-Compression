import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {

    private int value;
    private Tree left, right;

    public Tree(int value, Tree left, Tree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    @Override
    public String toString() {
        if (this.left == null && this.right == null) {
            return "(" + this.value + ")";
        }

        String leftString = (this.left == null) ? "_" : this.left.toString();
        String rightString = (this.right == null) ? "_" : this.right.toString();

        return "(" + this.value + " " + leftString + " " + rightString + ")";
    }

    public static Tree createFromList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        if (list.get(0).equals("0")) {
            list.remove(0);
            return null;
        }

        Tree tree = new Tree(Integer.parseInt(list.remove(0)), null, null);

        tree.left = createFromList(list);
        tree.right = createFromList(list);

        return tree;
    }

    public void printAsList(FileWriter writer, HashMap<Tree, Integer> hashMap, AtomicInteger n) throws IOException {
        if (hashMap.containsKey(this)) {
            writer.write(hashMap.get(this) - n.getAndIncrement() + "\n");
        }
        else {
            writer.write(this.value + "\n");
            hashMap.put(this, n.getAndIncrement());

            if (this.left == null) {
                writer.write("0\n");
                n.incrementAndGet();
            } else {
                this.left.printAsList(writer, hashMap, n);
            }

            if (this.right == null) {
                writer.write("0\n");
                n.incrementAndGet();
            } else {
                this.right.printAsList(writer, hashMap, n);
            }
        }
    }

    public static Tree compress(Tree tree, HashMap<String, Tree> hashMap) {
        if (tree == null) {
            return null;
        }

        tree.left = compress(tree.left, hashMap);
        tree.right = compress(tree.right, hashMap);

        String key = tree.toString();
        if (hashMap.containsKey(key)) {
            return hashMap.get(key);
        } else {
            hashMap.put(key, tree);
            return tree;
        }
    }

}
