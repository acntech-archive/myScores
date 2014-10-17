package myscores.mappers;

import myscores.constants.Props;
import myscores.domain.Gambler;
import myscores.domain.Party;
import myscores.relationships.ForGambler;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PartyMapper extends NodeMapper<Party> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyMapper.class);

    @Inject
    private GamblerMapper gamblerMapper;

    @Override
    public Party map(Node node) {
        if (node != null && hasLabel(node, createLabel())) {
            LOGGER.debug("Mapping node to domain object");
            Party party = new Party();
            party.setId(getIntProperty(node, Props.ID));
            party.setName(getStringProperty(node, Props.NAME));
            party.setGamblers(mapGamblers(node));
            return party;
        } else {
            LOGGER.debug("Node is null or has wrong label");
            return null;
        }
    }

    @Override
    public Node map(Node node, Party party) {
        if (node != null && party != null) {
            LOGGER.debug("Mapping domain object to node");
            node.addLabel(createLabel());
            node.setProperty(Props.ID, party.getId());
            node.setProperty(Props.NAME, party.getName());
            return node;
        } else {
            LOGGER.debug("Node or domain object is null");
            return null;
        }
    }

    @Override
    public String getDefaultKey() {
        return Props.ID;
    }

    @Override
    public Label createLabel() {
        return createLabel(Party.class);
    }

    private List<Gambler> mapGamblers(Node node) {
        List<Gambler> gamblers = new ArrayList<>();
        Iterable<Relationship> belongsTo = node.getRelationships(ForGambler.BELONGS_TO, Direction.INCOMING);
        for (Relationship rel : belongsTo) {
            for (Node gamblerNode : rel.getNodes()) {
                Gambler gambler = gamblerMapper.map(gamblerNode);
                if (gambler != null) {
                    gamblers.add(gambler);
                }
            }
        }
        return gamblers;
    }
}
