package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Party;
import myscores.mappers.PartyMapper;
import myscores.relationships.ForGambler;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
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
    private PartyMapper mapper;

    @Override
    public Party read(int id) {
        LOGGER.info("Read party for id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            Party party = mapper.map(index, id);
            if (party != null) {
                tx.success();
                return party;
            } else {
                tx.failure();
                throw new RepositoryException("No party found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Read failed", e);
        }
    }

    @Override
    public List<Party> find() {
        LOGGER.info("Find parties");
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            List<Party> parties = mapper.mapAll(index);
            tx.success();
            return parties;
        } catch (Exception e) {
            throw new RepositoryException("Find failed", e);
        }
    }

    @Override
    public void create(Party party) {
        party.setId(nextId());
        LOGGER.info("Create team with id {}", party.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            if (!mapper.exists(index, party.getId())) {
                Node node = mapper.map(database.createNode(), party);
                index.add(node, Props.ID, party.getId());
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("Party with id " + party.getId() + " already exists");
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Create failed", e);
        }
    }

    @Override
    public void update(Party party) {
        LOGGER.info("Update team with id {}", party.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            Node node = mapper.getNodeById(index, party.getId());
            if (node != null) {
                node.setProperty(Props.NAME, party.getName());
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No gambler found for id " + party.getId());
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Update failed", e);
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete party with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            Node node = mapper.getNodeById(index, id);
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                node.delete();
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No gambler found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Delete failed", e);
        }
    }

    public void add(int partyId, int gamblerId) {
        LOGGER.info("Adding gambler with id {} to party with id {}", partyId);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> partyIndex = database.getNodeIndex(Props.PARTIES_INDEX);
            Node partyNode = mapper.getNodeById(partyIndex, partyId);
            if (partyNode != null) {
                Index<Node> gamblerIndex = database.getNodeIndex(Props.GAMBLERS_INDEX);
                Node gamblerNode = mapper.getNodeById(gamblerIndex, gamblerId);
                if (gamblerNode != null) {
                    Relationship belongsTo = gamblerNode.createRelationshipTo(partyNode, ForGambler.BELONGS_TO);
                    belongsTo.setProperty(Props.SINCE, getCurrentTime());
                    tx.success();
                } else {
                    tx.failure();
                    throw new RepositoryException("No gambler found for id " + gamblerId);
                }
            } else {
                tx.failure();
                throw new RepositoryException("No gambler found for id " + partyId);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Add failed", e);
        }
    }

    public void remove(int partyId, int gamblerId) {
        LOGGER.info("Remove gambler with id {} from party with id {}", partyId);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> gamblerIndex = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node gamblerNode = mapper.getNodeById(gamblerIndex, gamblerId);
            if (gamblerNode != null) {
                Index<Node> partyIndex = database.getNodeIndex(Props.PARTIES_INDEX);
                Node partyNode = mapper.getNodeById(partyIndex, partyId);
                if (partyNode != null) {
                    Iterable<Relationship> belongsTo = gamblerNode.getRelationships(ForGambler.BELONGS_TO, Direction.OUTGOING);
                    for (Relationship relationship : belongsTo) {
                        for (Node relationshipNode : relationship.getNodes()) {
                            if (relationshipNode != null && relationshipNode.hasLabel(mapper.createLabel()) && partyId == mapper.getIdProperty(relationshipNode)) {
                                relationship.delete();
                            }
                        }
                    }
                    tx.success();
                } else {
                    tx.failure();
                    throw new RepositoryException("No gambler found for id " + gamblerId);
                }
            } else {
                tx.failure();
                throw new RepositoryException("No gambler found for id " + partyId);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Add failed", e);
        }
    }

    @Override
    protected int nextId() {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.PARTIES_INDEX);
            int id = mapper.getNodeCount(index) + 1;
            tx.success();
            return id;
        } catch (Exception e) {
            throw new RepositoryException("Getting next index failed", e);
        }
    }
}
