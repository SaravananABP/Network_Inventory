package com.network.inventory.locationManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.Country;
import com.network.inventory.locationManagement.pojo.State;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface StateRepo extends Neo4jRepository<State,Long> {
    @Query("Merge (c:Country{name:$countryName})-[r:to_state]-> (s:State{name:$stateName}) set s.description=$stateDescription return s")
    State createState (@Param("countryName")String countryName,
                       @Param("stateName") String stateName,
                       @Param("stateDescription") String stateDescription);
    @Query("Match (c:Country{name:$countryName})-[r:to_state]->(s:State{name:$stateName}) return count(s) = 0")
    boolean verifyExitingName(@Param("countryName")String countryName,
                              @Param("stateName") String stateName);
    @Query ("Match (c:Country{name:$countryName})-[:to_state]->(s:State) return s")
    List<State> findAllStateByCountry(@Param("countryName") String countryName);
    @Query("MATCH (b:State) WHERE ID(b) = $id RETURN b")
    Optional<State> findById(@Param("id") Long id);
}
