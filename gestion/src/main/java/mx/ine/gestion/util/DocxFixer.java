package mx.ine.gestion.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBElement;
//import org.docx4j.TraversalUtil.CallbackImpl;
//import org.docx4j.XmlUtils;
//import org.docx4j.wml.ContentAccessor;
//import org.jvnet.jaxb2_commons.ppp.Child;

public class DocxFixer {

//    private static final String TAG_BUG_1 =
//            "[^-]((<w:u w:color=\"\\w+\"\\/>))[^-]";
//    // private static final String TAG_BUG_2 =
//    // "[^-](?(<w:tabs).*?<\\/w:tabs>|(\\s.*?\\/>)|(\\/>))[^-]";
//    // private static final String TAG_BUG_2 =
//    // "[^-](?(<w:tab)(\\s.*?\\/>)|(\\/>))[^-]";
//    private static final String TAG_BUG_2 = "[^-]((<w:tab\\s.*?\\/>))[^-]";
//    private static final String TAG_BUG_3 = "[^-]((<w:tab\\/>))[^-]";
//    private static final String TAG_BUG_4 = "[^-]((<w:tabs.*?<\\/w:tabs>))[^-]";
//
//    // private static final String TAG_BUG_2 =
//    // "[^-]((<w:tab.*?\\/>) (<w:tabs.*?\\<\\/w:tabs>))[^-]";
//    // private static final String TAG_BUG_2 =
//    // "[^-]((<w:tab[^s].*?\\/>)||(<w:tabs.*?<\\/w:tabs>) )[^-]";
//
//    public static void fix(final String docxPath) {
//        DocxFixer.fixDocument(docxPath);
//
//    }
//
//    private static String replace(String xml, final String regex) {
//
//        final Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(xml);
//        while (matcher.find()) {
//            // System.out.println(matcher.groupCount());
//            if (matcher.groupCount() == 2) {
//                final String tagMatched = matcher.group(1);
//                xml = xml.replace(tagMatched, "<!--" + tagMatched + "-->");
//                matcher = pattern.matcher(xml);
//            }
//        }
//
//        return xml;
//    }
//
//    private static String remove(String xml, final String regex) {
//        final Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(xml);
//        while (matcher.find()) {
//            // System.out.println("- " + matcher.group() + "cero " +
//            // matcher.group(0) + " uno" + matcher.group(1) + " dos " +
//            // matcher.group(2) + " tres " +matcher.group(3) + " cuatro " +
//            // matcher.group(4) + " todo " + matcher.toString() );
//            if (matcher.groupCount() == 2) {
//                final String tagMatched = matcher.group(1);
//                // System.out.println("tagMatched " + tagMatched);
//                xml = xml.replace(tagMatched, "");
//                matcher = pattern.matcher(xml);
//            }
//        }
//
//        return xml;
//    }
//
//    public static boolean fixDocument(final String path) {
//        try {
//            final HashMap<String, ZipObject> mapZipObj =
//                    ZipFixer.unzipFile(path);
//
//            if (mapZipObj == null) {
//                return false;
//            }
//
//            ZipObject zoDocument = mapZipObj.get("word/document.xml");
//            if (zoDocument == null) {
//                return false;
//            }
//            String xmlDocument = new String(zoDocument.getData(), "UTF-8");
//
//            String xmlStyles = null;
//            final ZipObject zoStyles = mapZipObj.get("word/styles.xml");
//            if (zoStyles != null) {
//                xmlStyles = new String(zoStyles.getData(), "UTF-8");
//            }
//
//            xmlDocument = replace(xmlDocument, TAG_BUG_1);
//
//            zoDocument.setData(xmlDocument.getBytes("UTF8"));
//            if (zoStyles != null) {
//                xmlStyles = replace(xmlStyles, TAG_BUG_1);
//                zoStyles.setData(xmlStyles.getBytes("UTF8"));
//            }
//
//            ZipFixer.zip(path, mapZipObj);
//            new File(path).renameTo(new File(path + ".old"));
//            new File(path + ".new").renameTo(new File(path));
//            new File(path + ".old").delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }
//
//	// private static List<Object> getWmlObjects(
//	// final WordprocessingMLPackage wordMLPackage,
//	// String xhtmlDocumentAsString) throws Exception {
//	// try {
//	// XHTMLImporterImpl xhtmlImporterImpl =
//	// new XHTMLImporterImpl();
//	// return xhtmlImporterImpl.convert(xhtmlDocumentAsString, null);
//	//
//	// } catch (Exception e) {
//	// e.printStackTrace();
//	// throw e;
//	// }
//	// }
//
//    public static List<String> fixHeaders(String path) {
//        // System.out.println("fixHeaders");
//        List<String> headers = new ArrayList<String>();
//        try {
//            HashMap<String, ZipObject> mapZipObj = ZipFixer.unzipFile(path);
//
//            if (mapZipObj == null) {
//                return null;
//            }
//
//            Iterator iterator = mapZipObj.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, ZipObject> e =
//                        (Map.Entry<String, ZipObject>) iterator.next();
//                if (e.getKey().contains("header")) {
//                    String PATRON_ARCHIVO = "([^\\s]+(\\.(?i)(xml))$)";
//                    ;
//                    Pattern pattern = Pattern.compile(PATRON_ARCHIVO);
//                    Matcher matcher = pattern.matcher(e.getKey());
//                    while (matcher.find()) {
//                        ZipObject zipObject = mapZipObj.get(e.getKey());
//                        String xmlHeader =
//                                new String(zipObject.getData(), "UTF-8");
//                        xmlHeader = remove(xmlHeader, TAG_BUG_1);
//                        xmlHeader = remove(xmlHeader, TAG_BUG_4);
//                        xmlHeader = remove(xmlHeader, TAG_BUG_2);
//                        xmlHeader = remove(xmlHeader, TAG_BUG_3);
//                        zipObject.setData(xmlHeader.getBytes("UTF8"));
//                        // System.out.println(xmlHeader);
//                    }
//                }
//            }
//            ZipFixer.zip(path, mapZipObj);
//            new File(path).renameTo(new File(path + ".old"));
//            new File(path + ".new").renameTo(new File(path));
//            new File(path + ".old").delete();
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return headers;
//    }
//
//    public static List<String> fixFooter(String path) {
//        // System.out.println("fixHeaders");
//        List<String> headers = new ArrayList<String>();
//        try {
//            HashMap<String, ZipObject> mapZipObj = ZipFixer.unzipFile(path);
//
//            if (mapZipObj == null) {
//                return null;
//            }
//
//            Iterator<Entry<String, ZipObject>> iterator =
//                    mapZipObj.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, ZipObject> e =
//                        iterator.next();
//                if (e.getKey().contains("footer")) {
//                    String PATRON_ARCHIVO = "([^\\s]+(\\.(?i)(xml))$)";
//                    Pattern pattern = Pattern.compile(PATRON_ARCHIVO);
//                    Matcher matcher = pattern.matcher(e.getKey());
//                    while (matcher.find()) {
//                        ZipObject zipObject = mapZipObj.get(e.getKey());
//                        String xmlHeader =
//                                new String(zipObject.getData(), "UTF-8");
//                        xmlHeader = remove(xmlHeader, TAG_BUG_1);
//                        xmlHeader = remove(xmlHeader, TAG_BUG_4);
//                        xmlHeader = remove(xmlHeader, TAG_BUG_2);
//                        xmlHeader = remove(xmlHeader, TAG_BUG_3);
//                        zipObject.setData(xmlHeader.getBytes("UTF8"));
//                        // System.out.println(xmlHeader);
//                    }
//                }
//            }
//            ZipFixer.zip(path, mapZipObj);
//            new File(path).renameTo(new File(path + ".old"));
//            new File(path + ".new").renameTo(new File(path));
//            new File(path + ".old").delete();
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return headers;
//    }
//
//    static class Docx4jFinder extends CallbackImpl {
//
//        Class<?> classToSearch;
//        String localPartToSearch;
//
//        List<Child> elements = new ArrayList<Child>();
//
//        public Docx4jFinder(final Class<?> classToSearch,
//                final String localPartToSearch) {
//            super();
//            this.classToSearch = classToSearch;
//            this.localPartToSearch = localPartToSearch;
//        }
//
//        @Override
//        public List<Object> apply(Object o) {
//
//            if (o instanceof javax.xml.bind.JAXBElement
//                    && (((JAXBElement<?>) o).getName().getLocalPart()
//                            .equals(localPartToSearch))) {
//                // System.out.println(((JAXBElement<?>)o).getName().getLocalPart());
//                elements.add((Child) XmlUtils.unwrap(o));
//            } else if (classToSearch.isInstance(o)) {
//                // System.out.println("==>Name:"+o.getClass().getName());
//                elements.add((Child) o);
//            }
//            return null;
//        }
//
//        @Override
//        // to setParent
//                public
//                void walkJAXBElements(Object parent) {
//
//            List<?> children = getChildren(parent);
//            if (children != null) {
//
//                for ( Object o : children ) {
//
//                    if (o instanceof javax.xml.bind.JAXBElement
//                            && (((JAXBElement<?>) o).getName().getLocalPart()
//                                    .equals(localPartToSearch))) {
//
//                        ((Child) ((JAXBElement<?>) o).getValue())
//                                .setParent(XmlUtils.unwrap(parent));
//                    } else {
//                        o = XmlUtils.unwrap(o);
//                        if (o instanceof Child) {
//                            ((Child) o).setParent(XmlUtils.unwrap(parent));
//                        }
//                    }
//
//                    this.apply(o);
//
//                    if (this.shouldTraverse(o)) {
//                        walkJAXBElements(o);
//                    }
//
//                }
//            }
//        }
//    }
//
//    private static List<Object> getAllElementFromObject(Object obj,
//            Class<?> toSearch) {
//        List<Object> result = new ArrayList<Object>();
//        if (obj instanceof JAXBElement) {
//            obj = ((JAXBElement<?>) obj).getValue();
//        }
//
//        if (obj.getClass().equals(toSearch)) {
//            result.add(obj);
//        }
//        if (obj instanceof ContentAccessor) {
//            List<?> children = ((ContentAccessor) obj).getContent();
//            for ( Object child : children ) {
//                result.addAll(getAllElementFromObject(child, toSearch));
//            }
//        }
//
//        return result;
//    }
//
//    /* Convierte un documento de .doc a .docx usando POI mediante docx4j. */
//	// public static String convertirDocADocx(String path) throws
//	// FileNotFoundException, Exception {
//	//
//	// WordprocessingMLPackage out = Doc.convert(new FileInputStream(path));
//	// String outputfilepath = path.replace("doc", "docx");
//	// SaveToZipFile saver = new SaveToZipFile(out);
//	// if(saver.save(outputfilepath))
//	// return outputfilepath;
//	// else
//	// return "";
//	// }

}
