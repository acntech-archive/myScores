package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Gambler;
import myscores.domain.Party;
import myscores.relationships.ForGambler;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
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
    public Party map(Index<Node> index, int id) {
        Node partyNode = index.get(Props.ID, id).getSingle();
        return mapFull(partyNode);
    }

    @Override
    public List<Party> mapAll(Index<Node> index) {
        List<Party> parties = new ArrayList<>();
        IndexHits<Node> partyNodes = index.query(Props.ID, Props.ALL);
        if (partyNodes != null) {
            LOGGER.debug("{} parties found", partyNodes.size());
            for (Node partyNode : partyNodes) {
                Party party = mapFull(partyNode);
                if (party != null) {
                    parties.add(party);
                }
            }
        } else {
            LOGGER.debug("No parties found");
        }
        return parties;
    }

    private Party mapFull(Node partyNode) {
        Party party = map(partyNode);
        if (party != null) {
            List<Gambler> gamblers = new ArrayList<>();
            Iterable<Relationship> belongsTo = partyNode.getRelationships(ForGambler.BELONGS_TO, Direction.INCOMING);
            for (Relationship rel : belongsTo) {
                Node[] gamblerNodes = rel.getNodes();
                LOGGER.debug("{} nodes found for party with id {}", gamblerNodes.length, party.getId());
                for (Node gamblerNode : gamblerNodes) {
                    Iterable<String> keys = gamblerNode.getPropertyKeys();
                    Iterable<Label> labels = gamblerNode.getLabels();
                    LOGGER.debug("Node with labels {} has keys {}", labels.toString(), keys.toString());
                    if (hasLabel(gamblerNode, gamblerMapper.createLabel(Gambler.class))) {
                        LOGGER.debug("Node has correct label");
                        Gambler gambler = gamblerMapper.map(gamblerNode);
                        if (gambler != null) {
                            LOGGER.debug("Adding gambler with id {} to party with id {}", gambler.getId(), party.getId());
                            gamblers.add(gambler);
                        } else {
                            LOGGER.debug("No gambler mapped");
                        }
                    } else {
                        LOGGER.debug("Node does not have correct label");
                    }
                }
            }
            party.setGamblers(gamblers);
        }
        return party;
    }

    @Override
    public Party map(Node node) {
        if (node != null) {
            Party party = new Party();
            party.setId((Integer) node.getProperty(Props.ID));
            party.setName((String) node.getProperty(Props.NAME));
            return party;
        } else {
            return null;
        }
    }

    @Override
    public Node map(Node node, Party party) {
        if (node != null && party != null) {
            node.addLabel(createLabel(Party.class));
            node.setProperty(Props.ID, party.getId());
            node.setProperty(Props.NAME, party.getName());
            return node;
        } else {
            return null;
        }
    }
}
