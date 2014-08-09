package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Team;
import myscores.mappers.TeamMapper;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TeamRepository extends Repository<Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private TeamMapper mapper;

    @Override
    public Team read(int id) {
        LOGGER.info("Read team for id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Team team = mapper.map(index, id);
            if (team != null) {
                tx.success();
                return team;
            } else {
                tx.failure();
                throw new RepositoryException("Read failed. No team found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Read failed", e);
        }
    }

    @Override
    public List<Team> find() {
        LOGGER.info("Find teams");
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            List<Team> teams = mapper.mapAll(index);
            tx.success();
            return teams;
        } catch (Exception e) {
            throw new RepositoryException("Find failed", e);
        }
    }

    @Override
    public void create(Team team) {
        team.setId(nextId());
        LOGGER.info("Create team with id {}", team.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            if (!mapper.exists(index, team.getId())) {
                Node node = mapper.map(database.createNode(), team);
                index.add(node, Props.ID, team.getId());
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("Team with id " + team.getId() + " already exists");
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Create failed", e);
        }
    }

    @Override
    public void update(Team team) {
        LOGGER.info("Update team with id {}", team.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = mapper.getNode(index, team.getId());
            if (node != null) {
                node.setProperty(Props.NAME, team.getName());
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No team found for id " + team.getId());
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Update failed", e);
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete team with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = mapper.getNode(index, id);
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                node.delete();
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No team found for id " + id);
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
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            int id = mapper.getNodeCount(index) + 1;
            tx.success();
            return id;
        } catch (Exception e) {
            throw new RepositoryException("Getting next index failed", e);
        }
    }
}
