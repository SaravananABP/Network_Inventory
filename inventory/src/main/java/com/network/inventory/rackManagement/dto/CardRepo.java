package com.network.inventory.rackManagement.dto;

import com.network.inventory.rackManagement.pojo.Card;
import com.network.inventory.rackManagement.pojo.Slot;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

@EnableNeo4jRepositories
public interface CardRepo extends Neo4jRepository<Card,Long> {
    @Query("Merge (sl:Slot{name:$slotId})-[r:to_card]-> (s:Card{name:$cardName}) return s")
    Card createCard (Long slotId, Long cardName);
    @Query("Match (sl:Slot{name:$slotId})-[r:to_card]->(c:Card{name:$cardName})) return c")
    Card findByName(Long slot,String cardName);
    @Query ("merge (c:Slot{name:$slotId})-[r:to_card]->(s:Card) return s")
    List<Card> findAllSlotByCard(Long slotId);
    @Query("MATCH (b:Card) WHERE ID(b) = $id RETURN b")
    Optional<Card> findById(Long id);
}
