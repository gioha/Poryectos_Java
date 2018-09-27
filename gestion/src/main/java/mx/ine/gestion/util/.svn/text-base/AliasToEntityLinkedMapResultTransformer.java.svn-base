package mx.ine.gestion.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.transform.BasicTransformerAdapter;

/**
 * Clase de utileria para la consulta a la BD
 * 
 * @autor INE
 * @copy MAVO
 * @since 12/07/2016
 */
public class AliasToEntityLinkedMapResultTransformer extends BasicTransformerAdapter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6963211463969471422L;
	
	public static final AliasToEntityLinkedMapResultTransformer INSTANCE = new AliasToEntityLinkedMapResultTransformer();

    private AliasToEntityLinkedMapResultTransformer() {

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new LinkedHashMap(tuple.length);
        for (int i = 0; i < tuple.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }

        return result;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public boolean equals(Object other) {
        return other != null && AliasToEntityLinkedMapResultTransformer.class.isInstance(other);
    }

    public int hashCode() {
        return getClass().getName().hashCode();
    }
}