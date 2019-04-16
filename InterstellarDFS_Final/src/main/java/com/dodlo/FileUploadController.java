package com.dodlo;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodlo.domain.Planets;
import com.dodlo.domain.Routes;
import com.dodlo.service.FileUploadService;

@Controller
public class FileUploadController {

	private FileUploadService fileUploadService;

	@Autowired
	public FileUploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		List<Routes> routes = fileUploadService.storeRouteRepos();

		System.out.println("List of Routes>>>" + routes.size());

		model.addAttribute("routes", routes);

		return "redirect:/loadRoutes";
	}

	
	@GetMapping("/loadRoutes")
	public String getRoutes(RedirectAttributes redirectAttributes, Model model)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<Routes> routes = fileUploadService.getRoutes();
		System.out.println("list of routes size" + routes.size());
		model.addAttribute("routes", routes);
		List<Planets> planets = fileUploadService.storePlanetRepos();
		System.out.println("List Of Planetss Size >>> " + planets.size());
		model.addAttribute("planets", planets);
		model.addAttribute("planet", new Planets());
		return "showAllRoutes";
	}

	@GetMapping("/showAddForm")
	public String showAddForm(Model model) {
		model.addAttribute("route", new Routes());
		return "routesForm";
	}

	@PostMapping("/addRoute")
	public String addRoutes(@Valid Routes route, Model model) {

		fileUploadService.addRoute(route);
		List<Routes> routesList = fileUploadService.getRoutes();
		model.addAttribute("routes", routesList);
		List<Planets> planetsList= fileUploadService.addNewVertex(route);
		model.addAttribute("planets",planetsList);
		return "showAllRoutes";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Routes Route = fileUploadService.findById(id);
		model.addAttribute("route", Route);
		return "updateRoutesForm";
	}

	@GetMapping("/deleteRoute/{id}")
	public String deleteRoutes(@PathVariable("id") long id, Model model) {
		System.out.println(id);
		// long id=Long.parseLong(id);
		fileUploadService.deleteRoute(id);
		List<Routes> routesList = fileUploadService.getRoutes();
		model.addAttribute("routes", routesList);
		return "showAllRoutes";
	}

	@PostMapping("/updateRoute/{id}")
	public String updateRoute(@PathVariable("id") long id, @Valid Routes route, Model model) {
		fileUploadService.addRoute(route);
		List<Routes> routesList = fileUploadService.getRoutes();
		System.out.println("list of routes size" + routesList.size());
		model.addAttribute("routes", routesList);
		return "showAllRoutes";
	}
	
	
	@PostMapping("/shorteshPath")
	public String shortestPath(Model model,@Valid Planets planet ) {
		System.out.println("Planets "+planet.getPlanetNode());
		
		String destination = planet.getPlanetNode();
				String path = fileUploadService.getShortestPath(destination);		
				String[] splitString = path.split(planet.getPlanetNode());
				String distance = splitString[1];
				
				model.addAttribute("path", path);
				model.addAttribute("distance", distance);
				
				return "showChartForm";
	}

}
