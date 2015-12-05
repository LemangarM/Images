package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.regex.Pattern;

import com.csvreader.CsvWriter;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class RefineData {
	
	String csv_path = "/Users/fooxlj/My Project/";
	
	public String refineData(String imgListStr) throws IOException
	{
		//centre_2014_09_1414-09-2015_All.csv
	//	System.out.print(imgListStr.length());
		String outputFile = csv_path+"refinedData.csv";
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(outputFile), ',');
			CSVReader reader = new CSVReader(new FileReader(csv_path + "centre_2014_09_1414-09-2015_All.csv"));
			
			String[] headers ={"photo_id","latitude","longitude"};
			writer.writeNext(headers);

			String [] nextLine;
			while((nextLine = reader.readNext()) != null){				
				String imgId = nextLine[0];
               // System.out.println(nextLine[1]);
              //  System.out.println(ifDouble(nextLine[1]));
				if(imgListStr.contains(imgId) && ifDouble(nextLine[1]) && ifDouble(nextLine[2])){
					String[] newLine=  {nextLine[0],nextLine[1],nextLine[2]};
					writer.writeNext(newLine);
				}
			}
			reader.close();
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return "refinedData.csv";
	}
	
	
	
	
	
	 public int geoCluster(){
		 
		 double lat1, lng1, lat2, lng2;
		 int cluster = 0;
		
		 try {
			 
			    CsvWriter writer = new CsvWriter(csv_path +"clusterdData.csv");
				CSVReader reader = new CSVReader(new FileReader(csv_path + "refinedData.csv"));
			    
				reader.readNext();
				String[] newHeaders ={"photo_id","latitude","longitude","cluster"};
				writer.writeRecord(newHeaders);
				//get the first info of photos
				String[] nextLine = reader.readNext();
				lat1 = Double.parseDouble(nextLine[1]);
				lng1 = Double.parseDouble(nextLine[2]);
				
				String[] newRow1 =  {nextLine[0],nextLine[1],nextLine[2],Integer.toString(cluster)};
				writer.writeRecord(newRow1);
				int i =0;
				while ((nextLine = reader.readNext()) != null)
				{
					
					//get lat,lng of the next photo
					lat2 = Double.parseDouble(nextLine[1]);
					lng2 = Double.parseDouble(nextLine[2]);
					i++;
					if(GeoUtil.getDistance(lat1, lng1, lat2, lng2)>= 75 || i>4){
						cluster++;
						i=0;
					}
					String[] newRow = {nextLine[0],nextLine[1],nextLine[2],Integer.toString(cluster)};
					//System.out.println(newRow.length);
					writer.writeRecord(newRow);
					
					lat1 = lat2;
					lng1 = lng2;
				}
		
				reader.close();
				writer.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 return cluster;
	 }
	 
	 private boolean ifDouble(String str){
		 return str.matches("-?\\d+(\\.\\d+)?");
	 }
	 
}
