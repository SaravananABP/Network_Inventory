package com.network.inventory.rackManagement.dto;

import com.network.inventory.rackManagement.pojo.Rack;
import com.network.inventory.rackManagement.pojo.Shelf;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface ShelfRepo extends Neo4jRepository<Shelf,Long> {
    @Query("Merge (c:Rack{name:$rackName})-[r:to_shelf]-> (s:Shelf{name:$shelfName}) return s")
    Shelf createShelf (String rackName, String shelfName);
    @Query("Match (c:Rack{name:$rackName})-[r:to_shelf]-> (s:Shelf{name:$shelfName}) return s")
    Shelf findByName(String rackName,String shelfName);
    @Query ("merge (c:Rack{name:$rackName})-[:to_shelf]->(s:Shelf) return s")
    List<Shelf> findAllRackByShelf(String rackName);
    @Query("MATCH (b:Shelf) WHERE ID(b) = $id RETURN b")
    Optional<Shelf> findById(Long id);
}
