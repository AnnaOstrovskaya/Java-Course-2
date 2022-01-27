import java.util.*;
import java.util.stream.Collectors;

public class HW4t1 {

    public static void main(String[] args) {

        ArrayList allItems = new ArrayList<String>();

        int size = allItems.size();

        allItems.add("Screen");
        allItems.add("Laptop");
        allItems.add("Ornament");
        allItems.add("Coaster");
        allItems.add("Candle");
        allItems.add("Mask");
        allItems.add("Planner");
        allItems.add("Coaster");
        allItems.add("Phone");
        allItems.add("Mouse");
        allItems.add("Tokens");

        Set<Object> checkDuplicates = new HashSet<>();

        for (int i = 0; i < allItems.size(); i++) {
            Object items = allItems.get(i);
            if (!checkDuplicates.add(items)) {
                System.out.println(allItems = (ArrayList) allItems.stream().distinct().collect(Collectors.toList()));
            }
        }

        System.out.println(allItems.size());

    }
}
