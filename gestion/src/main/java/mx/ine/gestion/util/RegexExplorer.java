package mx.ine.gestion.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExplorer {

    public List<String> findElements(final String regex,
            final String textToMatch) {
        // System.out.println("--------------------- COINCIDENCIASS --------------------------");
        final List<String> matches = new ArrayList<String>();
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(textToMatch);
        // System.out.println("textToMatch " + textToMatch);
        while (m.find()) {
            // System.out.println("matches " + m.group(1));
            matches.add(m.group(1));
        }
        return matches;
    }

    public String replaceWith(String textToReplace, final List<String> oldText,
            final List<String> replaceWith) {
        for ( int i = 0; i < oldText.size(); i++ ) {
            textToReplace =
                    textToReplace.replace(oldText.get(i), replaceWith.get(i));
        }
        return textToReplace;
    }

    public boolean isMatched(final String regex, final String textToMatch) {
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(textToMatch);
        return m.matches();
    }
}
