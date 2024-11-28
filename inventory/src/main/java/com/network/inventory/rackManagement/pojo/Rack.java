package com.network.inventory.rackManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Rack")
@Data
public class Rack {
//    rack_id, description, rack_size, location, status, capacity, notes
    @Id
    @GeneratedValue
    private Long id;
    private String rack_id;
    private String description;
    private String rack_size;
    private String location;
    private String status;
    private String capacity;
    private String notes;
}
