package Etity;

public class PhotoData {
	public String photo_id;
	public String latitude;
	public String longitude;
	public String cluster;
	public PhotoData(){};
	public PhotoData(String photo_id,String latitude,String longitude,String cluster){
		this.photo_id = photo_id;
		this.latitude = latitude;
		this.longitude =longitude;
	    this.cluster =cluster;
	}
	
	public String[] toStringArray(){
		String[] params = {this.photo_id,this.latitude,this.longitude,this.cluster};
		return params;
	}
}
