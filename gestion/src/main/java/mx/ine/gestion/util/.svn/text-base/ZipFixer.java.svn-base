package mx.ine.gestion.util;
 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
 
public class ZipFixer {
 private final static int BUFFER_SIZE = 2048;
// private final static String ZIP_EXTENSION = ".zip";
  
 
	public static boolean zip( String path , /*List<ZipObject>*/HashMap<String,ZipObject> mapZipObj )
	{ 	 
		ZipOutputStream zos = null;
	    FileOutputStream fos;
	     
		try {
			fos = new FileOutputStream( path + ".new" );
			zos = new ZipOutputStream(fos);
			    		
			Iterator<Map.Entry<String,ZipObject>> entries = mapZipObj.entrySet().iterator();
			while (entries.hasNext()) 
			{
			  Map.Entry<String,ZipObject> entry = entries.next();
			  ZipObject value = entry.getValue();
			  
				ZipEntry ze= new ZipEntry( value.getEntry().toString() );
				zos.putNextEntry(ze);
	//			System.out.println("File opened : " + eachZipObj.getEntry().toString());
				zos.write(value.getData());
			
				zos.closeEntry();
			  // ...
			}
//			for( ZipObject eachZipObj : mapZipObj )
//			{
//				ZipEntry ze= new ZipEntry( eachZipObj.getEntry().toString() );
//				zos.putNextEntry(ze);
//	//			System.out.println("File opened : " + eachZipObj.getEntry().toString());
//				zos.write(eachZipObj.getData());
//			
//				zos.closeEntry();
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	 }
 
	public static /*List<ZipObject>*/HashMap<String,ZipObject> unzipFile( String path )
	{
		  File f = new File(path);
		  FileInputStream fis;
		  
		  BufferedInputStream bis = null;
		  BufferedOutputStream bos = null;
		  ByteArrayOutputStream baos = null;
		  
			try {
				  fis = new FileInputStream(f);
			
				  baos = new ByteArrayOutputStream();
				  bis = new BufferedInputStream(fis);
				  bos = new BufferedOutputStream(baos);
				  byte[] buffer = new byte[BUFFER_SIZE];
			  
				  while (bis.read(buffer, 0, BUFFER_SIZE) != -1) 
					  bos.write(buffer);
			  
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally{
				  try {
					bos.flush();
					bos.close();
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		  
		  return unzip(baos);
	}
 
	 private static /*List<ZipObject>*/HashMap<String,ZipObject> unzip( ByteArrayOutputStream zippedFileOS) 
	 {
		 try {
			 ZipInputStream inputStream = new ZipInputStream(
					 						new BufferedInputStream(new ByteArrayInputStream(
					 							zippedFileOS.toByteArray())));
			 ZipEntry entry;
			// List<ZipObject> result = new ArrayList<ZipObject>();
			 HashMap<String,ZipObject> result = new HashMap<String, ZipObject>();
 
			 
			   while ((entry = inputStream.getNextEntry()) != null) 
			   {
				    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				//    System.out.println("\tExtracting entry: " + entry);
				    int count;
				    byte data[] = new byte[BUFFER_SIZE];
				 
				    if (!entry.isDirectory()) 
				    {
					     BufferedOutputStream out = new BufferedOutputStream(
					       outputStream, BUFFER_SIZE);
					     
					     while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1) 
					    	 out.write(data, 0, count);
	
					     out.flush();
					     out.close();
	
					     result.put(entry.toString(), new ZipObject(entry,outputStream.toByteArray()) );
//					     result.add(new ZipObject(entry,outputStream.toByteArray()));
				    }
			   }
	   
			   inputStream.close();
			   return result;
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 		return null;
		 	}
	}
 
}