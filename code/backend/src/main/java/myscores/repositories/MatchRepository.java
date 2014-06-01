package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Match;
import myscores.mappers.MatchMapper;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class MatchRepository extends Repository<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private MatchMapper mapper;

    @Override
    public Match read(int id) {
        LOGGER.info("Read match for id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            Match match = mapper.map(index, id);
            if (match != null) {
                tx.success();
                return match;
            } else {
                tx.failure();
                throw new RepositoryException("No match found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Read failed", e);
        }
    }

    @Override
    public List<Match> find() {
        LOGGER.info("Find matches");
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            List<Match> matches = mapper.mapAll(index);
            tx.success();
            return matches;
        } catch (Exception e) {
            throw new RepositoryException("Find failed", e);
        }
    }

    @Override
    public void create(Match match) {
        match.setId(nextId());
        LOGGER.info("Create match with id {}", match.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            if (!mapper.exists(index, match.getId())) {
                Node node = mapper.map(database.createNode(), match);
                index.add(node, Props.ID, match.getId());
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("Match with id " + match.getId() + " already exists");
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Create failed", e);
        }
    }

    @Override
    public void update(Match match) {
        LOGGER.info("Update match with id {}", match.getId());
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete match with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            Node node = mapper.getNodeById(index, id);
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                node.delete();
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No match found for id " + id);
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
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            int id = mapper.getNodeCount(index) + 1;
            tx.success();
            return id;
        } catch (Exception e) {
            throw new RepositoryException("Getting next index failed", e);
        }
    }
}
