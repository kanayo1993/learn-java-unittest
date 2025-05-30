import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    public static List<String> nonEmpty(List<String> list){
        return list.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toList());
    }

    public static int parsePositiveInt(String s){
        if (!isNumeric(s)){
            throw new IllegalArgumentException("value must be numeric");
        }
        int v = Integer.parseInt(s);
        if (v <= 0) throw new IllegalArgumentException("value must be positive; " + s);
        return v;
    }

    private static boolean isNumeric(String s){
        return s != null && s.matches("-?\\d+");
    }
}
