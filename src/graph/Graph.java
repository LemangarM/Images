package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	List<String> names  = new ArrayList<String>();
	List<Integer> weights  = new ArrayList<Integer>();
	//List<String> links = new ArrayList<String>();
	
	public void addLink(String photoId1, String photoId2){
	
		if(names.indexOf(photoId1)==-1 && names.indexOf(photoId2)==-1)
		{
			names.add(photoId1);
			weights.add(1);
			names.add(photoId2);
			weights.add(1);
		
		}else if(names.indexOf(photoId1)==-1 && names.indexOf(photoId2)!=-1){
			names.add(photoId1);
			weights.add(1);
			int pos = names.indexOf(photoId2);
			int weight = weights.get(pos)+1;
			weights.set(pos, weight);
		}else if(names.indexOf(photoId1)!=-1 && names.indexOf(photoId2)==-1){
			names.add(photoId2);
			weights.add(1);
			int pos = names.indexOf(photoId1);
			int weight = weights.get(pos)+1;
			weights.set(pos, weight);
		}else if(names.indexOf(photoId1)!=-1 && names.indexOf(photoId2)!=-1){
			int pos1 = names.indexOf(photoId1);
			int pos2 = names.indexOf(photoId2);	
			int weight1 = weights.get(pos1)+1;
			int weight2 = weights.get(pos2)+1;
			weights.set(pos1, weight1);
			weights.set(pos2, weight2);
		}
		
	}
		
//		if(posStart == -1){
//			Node node = new Node(start);
//			vertexs.add(node);
//			posStart = vertexs.size();
//		}
//		if(posEnd == -1){
//			Node node = new Node(end);
//			vertexs.add(node);
//			posEnd = vertexs.size();
//		}
//		
//		Edge e = new Edge(end);
//		Edge s = new Edge(start);
//		List<String> nodesContainsEdge = getNodesContainEdge(s);
//		if(nodesContainsEdge.size()>0){
//			for(int i=0;i<nodesContainsEdge.size();i++){
//				int pos = getNodePosition(nodesContainsEdge.get(i));
//				vertexs.get(pos).listEdges.add(e);
//			}
//		}else{
//			vertexs.get(posStart).listEdges.add(e);
//		}
//		
//	}
	
//	
//	private void linkPhotos(String start,String end){
//		
//		for(int i=0;i<names.size();i++){
//			String slinks = links.get(i);
//			int weight ;
//			if(slinks.contains(start+",")&&!slinks.contains(end+",")){
//				slinks = slinks+end+",";
//				links.set(i, slinks);
//				weight = weights.get(i)+1;
//				weights.set(i, weight);
//			}
//		}
//	}
	public String getTheMax(){
		String name="";
		int max = -1;
		int pos = -1;
		System.out.println(names.size());
		for(int i =0 ; i < names.size() ; i++){
		
			if(max < weights.get(i)){
				max = weights.get(i);
			}
		}
		pos = weights.indexOf(max);
		if(max != -1){
			name = names.get(pos);
		}
		return name;
	}	
//	public String getDeepestDegree(){
//		String deepestDegree="" ;
//		int max = 0 ;
//		for(int i = 0; i < vertexs.size() ; i++){
//			String name = vertexs.get(i).name;
//			int degree = getDegree(name);
//			if(max < degree){
//				max = degree;
//				deepestDegree = name;
//			}
//		}
//		return deepestDegree;
//	}
//	
//	
//	private int getDegree(String name){
//		
//		int i =getNodePosition(name);
//		Node node = vertexs.get(i);
//		int degree = node.listEdges.size();
//		return degree;
//	}
//	
//	private List<String> getNodesContainEdge(Edge e){
//		List<String> nodesContainEdge = new ArrayList<String>();
//		for(int i=0;i<vertexs.size();i++){
//			if(vertexs.get(i).listEdges.indexOf(e)>=0){
//				nodesContainEdge.add(vertexs.get(i).name);
//			}
//		}
//		return nodesContainEdge;		
//	}
//	
//	// if the vertex name as "name" exist ,return the position in the list
//	private int getNodePosition(String name){
//		int pos = -1;
//		for(int i=0 ; i < vertexs.size() ; i++){
//			if(vertexs.get(i).name == name){
//				pos = i;
//				break;
//			}
//		}
//	//	System.out.println("_________________  "+pos);
//		return pos;
//	}
}
