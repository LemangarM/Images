package util;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Etity.PhotoData;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVUtil {
	
	
	public void writeRepresentives(List<PhotoData> photosData){
		
		String csv_path = "/Users/fooxlj/My Project/";
		String outputFile = csv_path + "representivesOfClusters0.csv";
		
		try{
			System.out.println ("===============    " + photosData.size());
			CSVWriter writer = new CSVWriter(new FileWriter(outputFile), ',');
			String[] headers = {"photo_id","latitude","longitude","cluster"};
			writer.writeNext(headers);
			
			for(int i = 0;i < photosData.size();i++){
			
				String[] params = photosData.get(i).toStringArray();
				writer.writeNext(params);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
