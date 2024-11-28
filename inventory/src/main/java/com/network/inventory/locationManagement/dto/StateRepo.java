package com.network.inventory.locationManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.Country;
import com.network.inventory.locationManagement.pojo.State;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface StateRepo extends Neo4jRepository<State,Long> {
    @Query("Merge (c:Country{name:$CountryName})-[r:to_state]-> (s:State{name:$stateName}) return s")
    State createState (String CountryName, String stateName);
    @Query("Match (s:State{name:$stateName}) return s")
    State findByName(String stateName);
    @Query ("Merge (c:Country{name:$CountryName})-[:to_state]->(s:State) return s")
    List<State> findAllStateByCountry(String CountryName);
    @Query("MATCH (b:State) WHERE ID(b) = $id RETURN b")
    Optional<State> findById(Long id);
}
