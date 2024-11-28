package com.network.inventory.rackManagement.dto;

import com.network.inventory.rackManagement.pojo.Card;
import com.network.inventory.rackManagement.pojo.CardSlot;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface CardSlotRepo extends Neo4jRepository<CardSlot,Long> {
    @Query("Merge (sl:Card{name:$cardName})-[r:to_card_slot]-> (s:CardSlot{name:$cardSlotName}) return s")
    CardSlot createCardSlot (Long cardName, String cardSlotName);
    @Query("Match (sl:Card{name:$cardName})-[r:to_card_slot]-> (c:CardSlot{name:$cardSlot})) return c")
    CardSlot findByName(Long cardName,String cardSlot);
    @Query ("merge (c:Card{name:$card_name})-[r:to_card_slot]->(s:CardSlot) return s")
    List<CardSlot> findAllCardByCardSlot(Long card_name);
    @Query("MATCH (b:Card) WHERE ID(b) = $id RETURN b")
    Optional<CardSlot> findById(Long id);
}
