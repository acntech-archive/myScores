package myscores.neo4j;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.client.ClientBuilder.newClient;

public class Neo4jWrapper {

    private static final String FIND_NODE = "http://test:DX3uDiTDLv832RGfHpwu@test.sb02.stations.graphenedb.com:24789/db/data/node/";

    public String findNode(String id) {
        Client client = newClient();
        WebTarget nodeTarget = client.target(FIND_NODE + id);

        String s = nodeTarget.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        return s;

    }

}
