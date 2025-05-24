import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    public static List<String> nonEmpty(List<String> list){
        return list.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toList());
    }
}
