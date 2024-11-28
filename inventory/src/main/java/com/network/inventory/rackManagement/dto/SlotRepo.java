package com.network.inventory.rackManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.rackManagement.pojo.Shelf;
import com.network.inventory.rackManagement.pojo.Slot;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface SlotRepo extends Neo4jRepository<Slot,Long> {
    @Query("Merge (sh:Shelf{name:$shelfName})-[r:to_slot]-> (s:Slot{name:$slotName}) return s")
    Slot createSlot (String shelfName, String slotName);
    @Query("Match (s:Shelf{name:$shelfName}) return s")
    Slot findByName(String shelfName);
    @Query ("merge (c:Shelf{name:$shelfName})-[r:to_slot]->(s:Slot) return s")
    List<Slot> findAllShelfBySlot(String shelfName);
    @Query("MATCH (b:Slot) WHERE ID(b) = $id RETURN b")
    Optional<Slot> findById(Long id);
}
