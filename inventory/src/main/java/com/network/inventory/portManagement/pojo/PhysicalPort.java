package com.network.inventory.portManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("PhysicalPort")
@Data
public class PhysicalPort {
//    physical_port_id; description; status; connection_type; speed; notes
    @Id
    @GeneratedValue
    private Long id;
    private String physical_port_id;
    private String description;
    private String status;
    private String connection_type;
    private String speed;
    private String notes;
}
