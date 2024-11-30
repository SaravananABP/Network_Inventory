package com.network.inventory.locationManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface BuildingRepo extends Neo4jRepository<Building,Long> {
    @Query("merge (c:City{name:$cityName})-[r:to_building]-> (s:Building{name:$stateName}) return s")
    Building createBuilding (@Param("cityName") String cityName,
                             @Param("stateName") String stateName,
                             @Param("Address") String Address,
                             @Param("floor") String floor,
                             @Param("description") String description);
    @Query("Match (c:City{name:$cityName})-[:to_building]->(b:Building{name:$buildingName}) return count(b) = 0")
    boolean verifyExitingName(@Param("cityName") String cityName,
                              @Param("buildingName") String buildingName);
    @Query ("merge (c:City{name:$cityName})-[:to_building]->(s:Building) return s")
    List<Building> findAllBuildingsByCity(@Param("cityName") String cityName);
    @Query("MATCH (b:Building) WHERE ID(b) = $id RETURN b")
    Optional<Building> findById(@Param("id") Long id);
    @Query("match (b:Building{id:$id}) detach delete b")
    Void deleteBuilding (@Param("id")Long id);

}
