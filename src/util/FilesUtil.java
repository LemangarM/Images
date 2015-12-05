package util;

import java.io.File;

public class FilesUtil {
	
	public boolean fileExit(String filepath,String filename){
		
		boolean flag = false;
		File f = new File(filepath);
		for(File file : f.listFiles()){
			if (filename.equals(file.getName()))
				flag = true;			
		}
		
		return flag;
	}
}
