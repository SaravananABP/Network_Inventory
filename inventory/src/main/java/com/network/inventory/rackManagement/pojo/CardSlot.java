package com.network.inventory.rackManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("CardSlot")
@Data
public class CardSlot {
//    card_slot_id, description, card_type, serial_number, status, notes
    @Id
    @GeneratedValue
    private Long id;
    private String card_slot_id;
    private String card_type;
    private String serial_number;
    private String status;
    private String notes;
}
