import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Compressor {

    public static void main(String[] args) { // args: input file path, output file path

        if (args.length < 2) {
            System.err.println("Too few arguments.");
            System.exit(-1);
        }

        try (FileWriter writer = new FileWriter(args[1])) {

            Tree tree = readFromFile(args[0]);

            if (tree == null) {
                writer.write("0");
                writer.close();
                System.exit(0);
            }

            tree = Tree.compress(tree, new HashMap<>());

            tree.printAsList(writer, new HashMap<>(), new AtomicInteger(1));

        } catch (IOException e) {
            System.err.println("File write error.");
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static Tree readFromFile(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(new File(path).toPath());
        } catch (IOException e) {
            System.err.println("File read error.");
            e.printStackTrace();
            System.exit(1);
        }

        return Tree.createFromList(lines);
    }
}
