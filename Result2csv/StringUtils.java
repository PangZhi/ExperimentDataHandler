import java.util.List;

public class StringUtils {
	/**
	 * @brief Concat the string list with glue.
	 * @param sList String list.
	 * @param glue Glue.
	 * @return The new String by concatting the string list with the glue.
	 */
	public static String join(List<String> sList, String glue) {
		if (sList.isEmpty()) {
			return null;
		}
		StringBuilder ret = new StringBuilder();
		ret.append(sList.get(0));
		for (int i = 1; i < sList.size(); ++i) {
			ret.append(glue).append(sList.get(i));
		}
		return ret.toString();	
	}
}
