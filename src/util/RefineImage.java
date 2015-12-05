package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Size;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;











public class RefineImage {
	
	String img_path = "/Users/fooxlj/My Project/image decompresse/";
	
	public String refineImg() throws IOException{
	    boolean refined = false ; 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		File f = new File(img_path+"PanoCentre/");
//	    if(!f.exists()){
//	    	refined = true;
//	    	f = new File(img_path + "PanoCentreRefined/");
//	    }
		int i =0;
		String imgList = "";
		for (File file : f.listFiles()) {
		        if (file.isFile()) {
		               String name = file.getName();
		        	   img_path =img_path + name;
		        	   imgList = imgList + "," +name.substring(0, name.length() - 4);
		        	   try{
		        	   Mat img = Imgcodecs.imread(img_path);
		        	   //if the images haven't been refined
    	        	   if(!refined){
    	        		 //rotate the image 
        	        	   Size s_img = img.size();
        	       	    	if(s_img.width > s_img.height)
        	       		    {
        	       			   Core.transpose(img, img);
        	       			   Core.flip(img, img, 1);
        	       		    }
        	       	    	System.out.println(i);
        	       	    	System.out.println(file.getName());
        	       	    	//resize the image after rotate
    		        	   Size sz = new Size(1000,1501);
    		        	   Imgproc.resize(img,img,sz);
    		        	   Imgcodecs.imwrite(img_path,img);
    		        	   
    		        	   img.release();
    	        	   }
		        	   renameDir();
		        	   }catch(Exception e){
		        		   System.err.println(e);
		        		  // Path path = FileSystems.getDefault().getPath(img_path);
		        		  // Files.delete(path);
		        	   }
		        	  
		        } 
		        i++;
		       }
//		if(!refined){
//			renameDir();
//		}		
		return imgList;
	   }
	
	
	//After resized the image ,rename the dir , so that next time when get the file the, don't need to refine anymore; 
	private void renameDir(){
		
		     File oldName = new File("C://Users//fooxlj//My Project//image decompresse//PanoCentre");
		   
		    //create destination File object
		     File newName = new File("C://Users//fooxlj//My Project//image decompresse//PanoCentreRefined");
		   
		    /*
		     * To rename a file or directory, use
		     * boolean renameTo(File destination) method of Java File class.
		     *
		     * This method returns true if the file was renamed successfully, false
		     * otherwise.
		     */
		     oldName.renameTo(newName);
		     boolean isFileRenamed = oldName.renameTo(newName);
		   
		     if(isFileRenamed)
		       System.out.println("File has been renamed");
		     else
		       System.out.println("Error renaming the file");
	}
	
	
	};
	
	
	



