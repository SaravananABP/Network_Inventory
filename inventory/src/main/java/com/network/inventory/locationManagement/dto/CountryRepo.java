package com.network.inventory.locationManagement.dto;

import com.network.inventory.locationManagement.pojo.Building;
import com.network.inventory.locationManagement.pojo.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

@EnableNeo4jRepositories
public interface CountryRepo extends Neo4jRepository<Country, Long> {
    @Query("Merge (c:Country{name:$countryName}) return c")
    Country findByName(String countryName);

    @Query("MATCH (b:Country) WHERE ID(b) = $id RETURN b")
    Optional<Country> findById(Long id);

    @Query("MATCH (c:Country {id: $id}) DETACH DELETE c")
    void deleteId(Long id);

}
