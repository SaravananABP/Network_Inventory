package com.network.inventory.locationManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.City;
import com.network.inventory.locationManagement.pojo.State;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface CityRepo extends Neo4jRepository<City,Long> {
    @Query("Merge (c:Country{name:$cityName})-[r:to_city]-> (s:State{name:$stateName}) return s")
    City createCity (String cityName, String stateName);
    @Query("Match (s:State{name:$stateName}) return s")
    City findByName(String stateName);
    @Query ("merge (c:Country{name:$cityName})-[:to_city]->(s:City) return s")
    List<City> findAllCitiesByState(String CountryName);
    @Query("MATCH (b:City) WHERE ID(b) = $id RETURN b")
    Optional<City> findById(Long id);
}
