import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


public class RefineClusterdData {
	String csv_path = "/Users/fooxlj/My Project/";
	
	public String refineData() throws IOException
	{
		//centre_2014_09_1414-09-2015_All.csv
		String outputFile = csv_path+"refinedClusterdData.csv";
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(outputFile), ',');
			CSVReader reader = new CSVReader(new FileReader(csv_path + "representivesOfClusters.csv"));
			
			
			String[] headers = reader.readNext();
			writer.writeNext(headers);

			String [] nextLine;
			while((nextLine = reader.readNext()) != null){				
				if(nextLine[0].length()!=0&&nextLine[1].length()!=0&&nextLine[2].length()!=0&&nextLine[3].length()!=0){
					writer.writeNext(nextLine);
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
}
