import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> mergedList = new ArrayList<>();
        Set<Integer> commonSet = new HashSet<>();

        try {
            // Read integers from input1.txt
            List<String> lines1 = Files.readAllLines(Paths.get("input1.txt"));
            for (String line : lines1) {
                try {
                    int num = Integer.parseInt(line.trim());
                    list1.add(num);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number in input1.txt: " + line);
                }
            }

            // Read integers from input2.txt
            List<String> lines2 = Files.readAllLines(Paths.get("input2.txt"));
            for (String line : lines2) {
                try {
                    int num = Integer.parseInt(line.trim());
                    list2.add(num);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number in input2.txt: " + line);
                }
            }

            // Alternate merging the contents of list1 and list2
            int maxSize = Math.max(list1.size(), list2.size());
            for (int i = 0; i < maxSize; i++) {
                if (i < list1.size()) {
                    mergedList.add(list1.get(i));
                }
                if (i < list2.size()) {
                    mergedList.add(list2.get(i));
                }
            }

            // Identify common integers
            Set<Integer> set1 = new HashSet<>(list1);
            Set<Integer> set2 = new HashSet<>(list2);
            for (int num : set1) {
                if (set2.contains(num)) {
                    commonSet.add(num);
                }
            }

            // Write the merged list to merged.txt
            List<String> mergedStrings = new ArrayList<>();
            for (int num : mergedList) {
                mergedStrings.add(String.valueOf(num));
            }
            Files.write(Paths.get("merged.txt"), mergedStrings, StandardOpenOption.CREATE);

            // Write the common integers to common.txt
            List<String> commonStrings = new ArrayList<>();
            for (int num : commonSet) {
                commonStrings.add(String.valueOf(num));
            }
            Files.write(Paths.get("common.txt"), commonStrings, StandardOpenOption.CREATE);

        } catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
        }
    }
}
