package com.network.inventory.rackManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Card")
@Data
public class Card {
//    model, manufacturer, serial_number, status, description, notes
    @Id
    @GeneratedValue
    private Long id;
    private String model;
    private String manufacturer;
    private String serial_number;
    private String status;
    private String description;
    private String notes;
}
