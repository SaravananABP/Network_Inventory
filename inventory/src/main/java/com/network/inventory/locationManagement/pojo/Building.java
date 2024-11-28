package com.network.inventory.locationManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Building")
@Data
public class Building {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String Address;
    private String floor;
    private String description;
}
