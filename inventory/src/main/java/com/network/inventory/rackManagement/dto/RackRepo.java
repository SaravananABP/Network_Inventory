package com.network.inventory.rackManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.City;
import com.network.inventory.rackManagement.pojo.Rack;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface RackRepo extends Neo4jRepository<Rack,Long> {
    @Query("Merge (c:Building{name:$building_name})-[r:to_rack]-> (s:Rack{name:$rackName}) return s")
    Rack createRack (String building_name, String rackName);
    @Query("Match (c:Building{name:$building_name})-[r:to_rack]->s:Rack{name:$rackName}) return s")
    Rack findByName(String building_name, String rackName);
    @Query ("merge (c:Building{name:$building_name})-[:to_rack]->(s:Rack) return s")
    List<Rack> findAllBuildingByRack(String building_name);
    @Query("MATCH (b:Rack) WHERE ID(b) = $id RETURN b")
    Optional<Rack> findById(Long id);
}
