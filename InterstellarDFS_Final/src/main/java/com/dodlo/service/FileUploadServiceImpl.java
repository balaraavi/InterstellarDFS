/**
 * 
 */
package com.dodlo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodlo.domain.Planets;
import com.dodlo.domain.Routes;
import com.dodlo.graph.Graph;
import com.dodlo.repository.PlanetRepository;
import com.dodlo.repository.RoutesRepository;

/**
 * @author rbalabharghav
 *
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private RoutesRepository routesRepository;

	@Autowired
	private PlanetRepository planetRepository;

	private static long idCounter = 0;

	public static final String SAMPLE_XLSX_FILE_PATH = "D:\\DumbStuff\\excel.xlsx";
	private String START = "A";

	@Override
	public List<Routes> storeRouteRepos() {
		List<Routes> routes = new ArrayList<>();

		try {
			Workbook offices = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
			Sheet sheet = offices.getSheetAt(1);
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				Routes route = new Routes();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {

						route.setId((long) ce.getNumericCellValue());
					}
					if (j == 1) {
						route.setPlanetOrigin(ce.getStringCellValue());
					}
					if (j == 2) {
						route.setPlanetDestination(ce.getStringCellValue());
					}
					if (j == 3) {
						route.setDistance(ce.getNumericCellValue());
					}
				}
				routes.add(route);

			}

			for (Routes route : routes) {
				routesRepository.save(route);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return routes;

	}

	@Override
	public List<Routes> getRoutes() {
		List<Routes> routes = new ArrayList<>();
		routesRepository.findAll().forEach(routes::add);
		return routes;

	}

	@Override
	public void addRoute(Routes routes) {

		routesRepository.save(routes);

	}

	@Override
	public void deleteRoute(long id) {
		Routes routes = routesRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		routesRepository.delete(routes);

	}

	@Override
	public Routes findById(long id) {
		// TODO Auto-generated method stub
		return routesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}

	@Override
	public List<Planets> storePlanetRepos() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Workbook offices = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

		Sheet sheet = offices.getSheetAt(0);
		System.out.println("sheet >>> " + sheet.getSheetName());
		List<Planets> planetsList = new ArrayList<>();
		System.out.println("getfirstRowNumber" + sheet.getFirstRowNum() + "sheet.getLastRow" + sheet.getLastRowNum());
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Planets planets = new Planets();
			Row ro = sheet.getRow(i);
			idCounter = idCounter + 1;
			System.out.println(
					"row first sell number " + ro.getFirstCellNum() + "last cell number " + ro.getLastCellNum());
			for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum() - 1; j++) {

				Cell ce = ro.getCell(j);
				if (null != ce.getStringCellValue()) {
					if (j == 0) {
						planets.setId(idCounter);
						planets.setPlanetNode(ce.getStringCellValue());
					}
					if (j == 1) {
						planets.setPlanetName(ce.getStringCellValue() + "-" + planets.getPlanetNode());
					}
					if (j == 2) {
						System.out.println("cell value " + ce.getStringCellValue());
					}
					System.out.println("cell values >>> " + ce.getStringCellValue());
					planetRepository.save(planets);
				}

			}
			planetsList.add(planets);

		}
		System.out.println("Planets List Size >>>>>>> File Uplaod " + planetsList.size());
		/*
		 * for (Planets planets : planetsList) { planetRepository.save(planets); }
		 */
		return planetsList;
	}

	@Override
	public Graph getGrph() {

		// Iterable<Routes> routes = routesRepository.findAll();
		List<Routes> routeList = storeRouteRepos();
		Graph.Edge[] GRAPH = new Graph.Edge[routeList.size()];
		Routes route = new Routes();

		for (int i = 0; i <= routeList.size() - 1; i++) {
			route = routeList.get(i);
			if ((route.getPlanetOrigin() != null) && (route.getPlanetDestination() != null)) {
				GRAPH[i] = new Graph.Edge(route.getPlanetOrigin(), route.getPlanetDestination(), route.getDistance());
			}
		}

		Graph g = new Graph(GRAPH);
		// g.dijkstra(START);
		return g;

	}

	@Override
	public String getShortestPath(String destination) {
		Graph g = getGrph();
		String END = destination;
		g.dijkstra(START);

		return g.printPath(END);
	}

	@Override
	public List<Planets> addNewVertex(Routes routes) {
		List<Planets> planetList = new ArrayList<Planets>();
		Iterable<Planets> planets = planetRepository.findAll();
	//	List<Routes> existingroutes = getRoutes();
		for (Planets planet : planets) {
			if (!(planet.getPlanetName().equalsIgnoreCase(routes.getPlanetOrigin()))) {
				if (!(planet.getPlanetName().equalsIgnoreCase(routes.getPlanetDestination()))) {
					planetList.add(planet);
					planetRepository.save(planet);
				}
			}
			
		}
		return planetList;

	}

}
