package com.network.inventory.locationManagement.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Data
@Node("Country")
public class Country {
//    name, code, description, region, contact_info, notes
    @Id
    @GeneratedValue // Generates a unique ID
    private Long ids;
    private String name;
    private String description;
    
}
