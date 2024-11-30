package com.network.inventory.locationManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.City;
import com.network.inventory.locationManagement.pojo.State;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface CityRepo extends Neo4jRepository<City,Long> {
    @Query("Merge (ss:State{name:$stateName})-[r:to_city]-> (c:City{name:$cityName}) set c.description=$description return c")
    City createCity (@Param("stateName") String stateName,
                     @Param("cityName") String cityName,
                     @Param("description") String description);
    @Query("Match (ss:State{name:$stateName})-[:to_city]->(c:City{name:$cityName}) return count(c) =0")
    boolean verifyExitingCity(@Param("stateName") String stateName,
                              @Param("cityName") String cityName);
    @Query ("merge (ss:State{name:$stateName})-[:to_city]->(c:City) return c")
    List<City> findAllCitiesByState(@Param("stateName") String stateName);
    @Query("MATCH (b:City) WHERE ID(b) = $id RETURN b")
    Optional<City> findById(@Param("id") Long id);
}
