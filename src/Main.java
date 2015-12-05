import java.io.*;
import java.util.List;

import Etity.PhotoData;
import util.CSVUtil;
import util.RefineImage;
import util.RefineData;

public class Main {
      public static void main(String[] args) throws IOException {
    	
//    	 RefineImage myimage = new RefineImage();
//    	 RefineData imgdata = new RefineData();
    	 CSVUtil csvUtil = new CSVUtil();
    	  
//    	 String imgListStr = myimage.refineImg();
//   	     imgdata.refineData(imgListStr);
//    	 int numCluster = imgdata.geoCluster();
    	Representives representives = new Representives(4859);
    	 List<PhotoData> photosData = representives.getRepresentives();
    	 csvUtil.writeRepresentives(photosData);

    }
}