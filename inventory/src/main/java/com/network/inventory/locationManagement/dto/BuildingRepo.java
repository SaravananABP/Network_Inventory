package com.network.inventory.locationManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface BuildingRepo extends Neo4jRepository<Building,Long> {
    @Query("merge (c:City{name:$cityName})-[r:to_building]-> (s:Building{name:$stateName}) return s")
    Building createBuilding (String cityName, String stateName);
    @Query("Match (s:State{name:$stateName}) return s")
    Building findByName(String stateName);
    @Query ("merge (c:City{name:$cityName})-[:to_building]->(s:Building) return s")
    List<Building> findAllBuildingsByCity(String cityName);
    @Query("MATCH (b:Building) WHERE ID(b) = $id RETURN b")
    Optional<Building> findById(Long id);

}
