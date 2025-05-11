public class StringUtils {
    public static String reverse(String str){
        if(str == null){
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }
}
