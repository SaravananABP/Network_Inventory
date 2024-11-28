package com.network.inventory.portManagement.pojo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Port")
@Data
public class Port {
//    port_id; type; status; speed; connector_type; description; notes
    @Id
    @GeneratedValue
    private Long id;
   private String port_id;
    private String type;
    private String status;
    private String  speed;
    private String  connector_type;
    private String description;
    private String notes;
}
