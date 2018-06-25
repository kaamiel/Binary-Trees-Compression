import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("No data file.");
            System.exit(-1);
        }

        Tree tree = read(args[0]);
        System.out.println(tree);
        tree.write(new HashMap<>(), new AtomicInteger(1));

        tree = Tree.compress(tree, new HashMap<>());

        System.out.println(tree);
        System.out.println("\n\n");
        tree.write(new HashMap<>(), new AtomicInteger(1));

    }

    private static Tree read(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(new File(path).toPath());
        } catch (IOException e) {
            System.err.println("Input error.");
            e.printStackTrace();
        }

        return Tree.createFromList(lines);
    }
}
