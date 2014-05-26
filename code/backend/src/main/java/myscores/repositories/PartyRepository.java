package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Party;
import myscores.mappers.PartyMapper;
import myscores.relationships.Basic;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class PartyRepository extends Repository<Party> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private PartyMapper partyMapper;

    @Override
    public Party read(int id) {
        LOGGER.info("Read party for id {}", id);
        Party party;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            party = partyMapper.mapNode(index, id);
            if (party != null) {
                LOGGER.info("Read success for id {}", id);
            } else {
                LOGGER.warn("Read failed. No party found for id {}", id);
            }
            tx.success();
        }
        return party;
    }

    @Override
    public List<Party> find() {
        LOGGER.info("Find parties");
        List<Party> parties;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            parties = partyMapper.mapAllNodes(index);
            tx.success();
        }
        return parties;
    }

    @Override
    public void create(Party party) {
        LOGGER.info("Create team with id {}", party.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            if (index.get(Props.ID, party.getId()).getSingle() == null) {
                Node node = partyMapper.mapNode(database.createNode(), party);
                index.add(node, Props.ID, party.getId());
            } else {
                LOGGER.warn("Create failed. Party with id {} already exists", party.getId());
            }
            tx.success();
        }
    }

    @Override
    public void update(Party party) {
        LOGGER.info("Update team with id {}", party.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            Node node = index.get(Props.ID, party.getId()).getSingle();
            if (node != null) {
                node.setProperty(Props.NAME, party.getName());
            } else {
                LOGGER.warn("Update failed. No party found for id {}", party.getId());
            }
            tx.success();
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete party with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                node.delete();
            } else {
                LOGGER.warn("Delete failed. No party found for id {}", id);
            }
            tx.success();
        }
    }

    public void add(int partyId, int gamblerId) {
        LOGGER.info("Adding gambler with id {} to party with id {}", partyId);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> partyIndex = database.getNodeIndex(Props.PARTIES_INDEX);
            Node partyNode = partyIndex.get(Props.ID, partyId).getSingle();
            if (partyNode != null) {
                Index<Node> gamblerIndex = database.getNodeIndex(Props.GAMBLERS_INDEX);
                Node gamblerNode = gamblerIndex.get(Props.ID, gamblerId).getSingle();
                if (gamblerNode != null) {
                    gamblerNode.createRelationshipTo(partyNode, Basic.BELONGS_TO);
                } else {
                    LOGGER.warn("Add failed. No gambler found for id {}", partyId);
                }
            } else {
                LOGGER.warn("Add failed. No party found for id {}", partyId);
            }
            tx.success();
        }
    }
}
