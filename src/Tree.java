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

    public static Tree createFromList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        if (list.get(0).equals("0")) {
            list.remove(0);
            return null;
        }

        Tree tree = new Tree(Integer.parseInt(list.get(0)), null, null);
        list.remove(0);

        tree.left = createFromList(list);
        tree.right = createFromList(list);

        return tree;
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


    public void write(HashMap<Tree, Integer> hashMap, AtomicInteger n) {
        if (hashMap.containsKey(this)) {
            System.out.println(hashMap.get(this) - n.get());
            n.incrementAndGet();
        }
        else {
            System.out.println(this.value);
            hashMap.put(this, n.get());
            n.incrementAndGet();

            if (this.left == null) {
                System.out.println("0");
                n.incrementAndGet();
            } else {
                this.left.write(hashMap, n);
            }

            if (this.right == null) {
                System.out.println("0");
                n.incrementAndGet();
            } else {
                this.right.write(hashMap, n);
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
