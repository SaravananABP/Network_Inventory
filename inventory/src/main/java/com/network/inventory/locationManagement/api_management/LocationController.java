package com.network.inventory.locationManagement.api_management;

import com.network.inventory.locationManagement.dto.BuildingRepo;
import com.network.inventory.locationManagement.dto.CityRepo;
import com.network.inventory.locationManagement.dto.CountryRepo;
import com.network.inventory.locationManagement.dto.StateRepo;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.City;
import com.network.inventory.locationManagement.pojo.Country;
import com.network.inventory.locationManagement.pojo.State;
import jakarta.websocket.server.PathParam;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class LocationController {
    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private BuildingRepo buildingRepo;

    // Country CRUD operations

    @PostMapping("/create/country")
    private JSONObject createCountry(@RequestBody Country country) {
        JSONObject response = new JSONObject();
        String countryName = formatName(country.getName());

        if (countryRepo.findByName(countryName)==null) {
            country.setName(countryName);
            countryRepo.save(country);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "Country already exists");
        }
        return response;
    }

    @GetMapping("/get/allcountries")
    private JSONObject getAllCountryDetails() {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", countryRepo.findAll());
        return response;
    }

    @PutMapping("/edit/country/{id}")
    private JSONObject editCountry(@PathVariable Long id, @RequestBody Country updatedCountry) {
        JSONObject response = new JSONObject();
        Optional<Country> optionalCountry = countryRepo.findById(id);

        if (optionalCountry.isPresent()) {
            updatedCountry.setIds(id);
            updatedCountry.setName(formatName(updatedCountry.getName()));
            countryRepo.save(updatedCountry);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/country/{id}")
    private JSONObject dropCountry(@PathVariable Long id) {
        JSONObject response = new JSONObject();
        if (countryRepo.existsById(id)) {
            countryRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    // State  operations

    @PostMapping("/create/state/{countryName}")
    private JSONObject createState(@RequestBody State state, @PathVariable String countryName) {
        JSONObject response = new JSONObject();
        String stateName = formatName(state.getName());
        if (stateRepo.findByName(stateName) == null) {
            stateRepo.save(state);
            stateRepo.createState(countryName, stateName);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "State already exists");
        }
        return response;
    }

    @GetMapping("/get/{countryName}/allstates")
    private JSONObject getAllStatesByCountry(@PathVariable String countryName) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", stateRepo.findAllStateByCountry(countryName));
        return response;
    }

    @PutMapping("/edit/state/{id}")
    private JSONObject editState(@PathVariable Long id, @RequestBody State updatedState) {
        JSONObject response = new JSONObject();
        Optional<State> optionalState = stateRepo.findById(id);

        if (optionalState.isPresent()) {
            updatedState.setId(id);
            updatedState.setName(formatName(updatedState.getName()));
            stateRepo.save(updatedState);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/state/{id}")
    private JSONObject dropState(@PathVariable Long id) {
        JSONObject response = new JSONObject();
        if (stateRepo.existsById(id)) {
            stateRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    // City CRUD operations

    @PostMapping("/create/city/{stateName}")
    private JSONObject createCity(@RequestBody City city, @PathVariable String stateName) {
        JSONObject response = new JSONObject();
        String cityName = formatName(city.getName());

        if (cityRepo.findByName(cityName) == null) {
            city.setName(cityName);
            cityRepo.save(city);
            cityRepo.createCity(stateName, cityName);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "City already exists");
        }
        return response;
    }

    @GetMapping("/get/{stateName}/allcities")
    private JSONObject getAllCitiesByState(@PathVariable String stateName) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", cityRepo.findAllCitiesByState(stateName));
        return response;
    }

    @PutMapping("/edit/city/{id}")
    private JSONObject editCity(@PathVariable Long id, @RequestBody City updatedCity) {
        JSONObject response = new JSONObject();
        Optional<City> optionalCity = cityRepo.findById(id);

        if (optionalCity.isPresent()) {
            updatedCity.setId(id);
            updatedCity.setName(formatName(updatedCity.getName()));
            cityRepo.save(updatedCity);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/city/{id}")
    private JSONObject dropCity(@PathVariable Long id) {
        JSONObject response = new JSONObject();
        if (cityRepo.existsById(id)) {
            cityRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    // Building CRUD operations

    @PostMapping("/create/building/{cityName}")
    private JSONObject createBuilding(@RequestBody Building building, @PathVariable String cityName) {
        JSONObject response = new JSONObject();
        String buildingName = formatName(building.getName());

        if (buildingRepo.findByName(buildingName) == null) {
            building.setName(buildingName);
            buildingRepo.save(building);
            buildingRepo.createBuilding(cityName, buildingName);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "Building already exists");
        }
        return response;
    }

    @GetMapping("/get/{cityName}/allbuildings")
    private JSONObject getAllBuildingsByCity(@PathVariable String cityName) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", buildingRepo.findAllBuildingsByCity(cityName));
        return response;
    }

    @PutMapping("/edit/building/{id}")
    private JSONObject editBuilding(@PathVariable Long id, @RequestBody Building updatedBuilding) {
        JSONObject response = new JSONObject();
        Optional<Building> optionalBuilding = buildingRepo.findById(id);

        if (optionalBuilding.isPresent()) {
            updatedBuilding.setId(id);
            updatedBuilding.setName(formatName(updatedBuilding.getName()));
            buildingRepo.save(updatedBuilding);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/building/{id}")
    private JSONObject dropBuilding(@PathVariable Long id) {
        JSONObject response = new JSONObject();
        if (buildingRepo.existsById(id)) {
            buildingRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    /**
     * Utility method to format names to a unique, consistent format by removing spaces, special characters, and setting to camel case.
     * @param name Original name to format.
     * @return Formatted name.
     */
    private String formatName(String name) {
        if (name == null) return "";
        return name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().substring(0, 1).toUpperCase()
                + name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().substring(1);
    }

}
