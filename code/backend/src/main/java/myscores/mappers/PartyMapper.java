package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Gambler;
import myscores.domain.Party;
import myscores.relationships.Basic;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PartyMapper extends Mapper<Party> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyMapper.class);

    @Inject
    private GamblerMapper gamblerMapper;

    public Party mapNode(Index<Node> index, int id) {
        Party party = null;
        Node partyNode = index.get(Props.ID, id).getSingle();
        if (partyNode != null) {
            party = mapNode(partyNode);
            List<Gambler> gamblers = new ArrayList<>();
            Iterable<Relationship> belongsTo = partyNode.getRelationships(Basic.BELONGS_TO, Direction.INCOMING);
            for (Relationship rel : belongsTo) {
                for (Node gamblerNode : rel.getNodes()) {
                    gamblers.add(gamblerMapper.mapNode(gamblerNode));
                }
            }
            party.setGamblers(gamblers);
        }
        return party;
    }

    @Override
    public List<Party> mapAllNodes(Index<Node> index) {
        List<Party> parties = new ArrayList<>();
        IndexHits<Node> partyNodes = index.query(Props.ID, Props.ALL);
        if (partyNodes != null) {
            LOGGER.debug("{} party nodes found", partyNodes.size());
            for (Node partyNode : partyNodes) {
                Party party = mapNode(partyNode);
                List<Gambler> gamblers = new ArrayList<>();
                Iterable<Relationship> belongsTo = partyNode.getRelationships(Basic.BELONGS_TO, Direction.INCOMING);
                LOGGER.debug("Iterable is {}", belongsTo.getClass().getName());
                for (Relationship rel : belongsTo) {
                    Node[] gamblerNodes = rel.getNodes();
                    LOGGER.debug("{} gambler nodes found for party with id {}", gamblerNodes.length, party.getId());
                    for (Node gamblerNode : gamblerNodes) {
                        Iterable<String> keys = gamblerNode.getPropertyKeys();
                        LOGGER.debug("Gambler node has keys {}", keys.toString());
                        Gambler gambler = gamblerMapper.mapNode(gamblerNode);
                        LOGGER.debug("Adding gambler with id {} to party with id {}", gambler.getId(), party.getId());
                        gamblers.add(gambler);
                    }
                }
                party.setGamblers(gamblers);
                parties.add(party);
            }
        }
        return parties;
    }

    @Override
    public Party mapNode(Node node) {
        Party party = new Party();
        party.setId((Integer) node.getProperty(Props.ID));
        return party;
    }

    @Override
    public Node mapNode(Node node, Party party) {
        node.setProperty(Props.ID, party.getId());
        node.setProperty(Props.NAME, party.getName());
        return node;
    }
}
