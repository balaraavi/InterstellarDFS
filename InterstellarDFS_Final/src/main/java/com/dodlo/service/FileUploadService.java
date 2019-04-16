package com.dodlo.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.dodlo.domain.Planets;
import com.dodlo.domain.Routes;
import com.dodlo.graph.Graph;

/**
 * @author rbalabharghav
 *
 */
public interface FileUploadService {
	
	List<Planets> storePlanetRepos() throws EncryptedDocumentException, InvalidFormatException, IOException;
	
	
	List<Routes> storeRouteRepos();
	
	List<Routes> getRoutes();
	
	void addRoute(Routes routes);
	
	List<Planets> addNewVertex(Routes routes);
	
	void deleteRoute(long id);
	
	Routes findById(long id);
	
	public Graph getGrph() ;  // this is for implementing shortest Path  
	
	public String getShortestPath(String destination);
	
	//public String getTotalDistannce();
	
	
}
