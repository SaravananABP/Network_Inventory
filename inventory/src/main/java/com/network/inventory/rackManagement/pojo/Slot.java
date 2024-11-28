package com.network.inventory.rackManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Slot")
@Data
public class Slot {
//    slot_id, description, slot_type, status, occupied, notes
    @Id
    @GeneratedValue
    private Long id;
    private String slot_id;
    private String description;
    private String slot_type;
    private String status;
    private String occupied;
    private String notes;
}
