package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Gambler;
import myscores.mappers.GamblerMapper;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class GamblerRepository extends Repository<Gambler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private GamblerMapper mapper;

    @Override
    public Gambler read(int id) {
        LOGGER.info("Read gambler for id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Gambler gambler = mapper.map(index, id);
            if (gambler != null) {
                tx.success();
                return gambler;
            } else {
                tx.failure();
                throw new RepositoryException("No gambler found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Read failed", e);
        }
    }

    @Override
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            List<Gambler> gamblers = mapper.mapAll(index);
            tx.success();
            return gamblers;
        } catch (Exception e) {
            throw new RepositoryException("Find failed", e);
        }
    }

    @Override
    public void create(Gambler gambler) {
        gambler.setId(nextId());
        LOGGER.info("Create gambler with id {}", gambler.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            if (!mapper.exists(index, gambler.getId())) {
                Node node = mapper.map(database.createNode(), gambler);
                index.add(node, Props.ID, gambler.getId());
                index.add(node, Props.NAME, gambler.getName());
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("Gambler with id " + gambler.getId() + " already exists");
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Create failed", e);
        }
    }

    @Override
    public void update(Gambler gambler) {
        LOGGER.info("Update gambler with id {}", gambler.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = mapper.getNodeById(index, gambler.getId());
            if (node != null) {
                node.setProperty(Props.NAME, gambler.getName());
                node.setProperty(Props.ACTIVE, gambler.isActive());
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No gambler found for id " + gambler.getId());
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Update failed", e);
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete gambler with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = mapper.getNodeById(index, id);
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                index.remove(node, Props.NAME, node.getProperty(Props.NAME));
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

    @Override
    protected int nextId() {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            int id = mapper.getNodeCount(index) + 1;
            tx.success();
            return id;
        } catch (Exception e) {
            throw new RepositoryException("Getting next index failed", e);
        }
    }
}
