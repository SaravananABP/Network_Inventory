package com.network.inventory.rackManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Shelf")
@Data
public class Shelf {
//    shelf_id, description, location_in_rack, capacity, status, notes
    @Id
    @GeneratedValue
    private Long id;
    private String shelf_id;
    private String description;
    private String location_in_rack;
    private String capacity;
    private String status;
    private String notes;
}
