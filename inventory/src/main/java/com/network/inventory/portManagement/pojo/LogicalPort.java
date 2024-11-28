package com.network.inventory.portManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("LogicalPort")
@Data
public class LogicalPort {
    @Id
    @GeneratedValue
    private Long id;
//    logical_port_id; description; status; capacity; assigned_to; notes
    private String logical_port_id;
    private String  description;
    private String status;
    private String capacity;
    private String assigned_to;
    private String notes;
}
