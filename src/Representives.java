import graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;




import Etity.PhotoData;
import au.com.bytecode.opencsv.CSVReader;






public class Representives {
    int numCluster;   
	Representives(int n){
		numCluster=n;
	}
	
	
	//return the name of representive image for each cluster
	public List<PhotoData> getRepresentives(){
		List<PhotoData> representivesOfClusters = new ArrayList<PhotoData>();
				
		for(int i = 0 ; i <= numCluster ; i++){
	//	int i=398;
		List<PhotoData> photosData = imagesInCluster(i);
		if(photosData!=null){
			if (photosData.size() == 1){
				representivesOfClusters.add(photosData.get(0));
			}
			else{
				PhotoData representive = findRepresentive(i);
				representivesOfClusters.add(representive);
			}
			System.out.println(i);
		}
		}
		return representivesOfClusters;
	}
	
	//get the names of images in one cluster given
	private List<PhotoData> imagesInCluster(int cluster){
		List<PhotoData> imagesInCluster= new ArrayList<PhotoData>();
		String csv_path = "/Users/fooxlj/My Project/clusterdData.csv";
		
		try {
			
			CSVReader reader = new CSVReader(new FileReader(csv_path));
			reader.readNext();
			String [] nextLine;
			while((nextLine = reader.readNext()) != null){
				
				String scluster = nextLine[3];
			   // System.out.println(scluster);
				if(cluster == Integer.parseInt(scluster)){
					PhotoData photoData = new PhotoData(nextLine[0],nextLine[1],nextLine[2],nextLine[3]);
					//System.out.println(id);
					imagesInCluster.add(photoData);
				//	
				//	System.out.println(cluster);
				}
				
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imagesInCluster;
	}
		
	//find the representive image from the cluster
	private PhotoData findRepresentive(int cluster){
		List<PhotoData> photosData = imagesInCluster(cluster);
		
		String img_path = "/Users/fooxlj/My Project/image decompresse/PanoCentre/";
		String representiveName = "";
		PhotoData representive = new PhotoData();
		Graph graph = new Graph();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		if(photosData!=null){
	    int count = photosData.size();
	    System.out.println(count);
	    String img_path1 = "";
	    String img_path2 = "";
	    if(count!=0){
		for(int i = 1; i < count-1 ; i++){
			for(int j =i+1 ; j < count; j++){
			img_path1 = img_path + photosData.get(i).photo_id+".jpg";	
			img_path2 = img_path +  photosData.get(j).photo_id+".jpg";				
				 if(compare(img_path1,img_path2)){
					 graph.addLink(photosData.get(i).photo_id,photosData.get(j).photo_id);
				 }		
			System.out.println(i+"  compare to    "+j);
			}
		}
		representiveName = graph.getTheMax();
		for(int k = 0; k < photosData.size() ; k++){
			if(representiveName == photosData.get(k).photo_id){
				representive = photosData.get(k);
			}
		}
		}
		}
		return representive;
	}
	

	
private boolean compare(String img_path1 , String img_path2){
	    Mat img1 = Imgcodecs.imread(img_path1);
	    Mat img2 = Imgcodecs.imread(img_path2);
		boolean iflink = false;

		MatOfKeyPoint keypoint1 = new MatOfKeyPoint();
		MatOfKeyPoint keypoint2 = new MatOfKeyPoint();
		Mat descriptor1 = new Mat();
		Mat descriptor2 = new Mat();
		try{
	//	System.out.println(image1.size()+"    "+image2.size());

		FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);//SURF algorithm to get keypoints
		DescriptorExtractor extractor = DescriptorExtractor.create(FeatureDetector.ORB);
	    	
		detector.detect(img1, keypoint1);
		detector.detect(img2, keypoint2);
		extractor.compute(img1, keypoint1, descriptor1);
		extractor.compute(img2, keypoint2, descriptor2);
		
		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT); // BRUTEFORCE_HAMMINGLU
		List<MatOfDMatch> matches = new ArrayList<MatOfDMatch>();
		
		matcher.knnMatch(descriptor1, descriptor2, matches, 2);
		// Ratio Test
		
		for (int matchIdx = 0; matchIdx < matches.size(); ++matchIdx) 
		{
			
		    final float ratio = (float) 0.8; // As in Lowe's paper (can be tuned)
		    DMatch[] matche = matches.get(matchIdx).toArray();
		    if(matche.length==2){
			   if (matche[0].distance < ratio *matche[1].distance)
			     {
			        iflink = true;
			        break;
			     }
		    }
		   
		}
		 img1.release(); 
		 img2.release();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
         return iflink;
	}

}


