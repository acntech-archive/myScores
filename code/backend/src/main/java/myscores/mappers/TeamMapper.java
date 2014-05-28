package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Team;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper extends NodeMapper<Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamMapper.class);

    @Override
    public Team map(Index<Node> index, int id) {
        Node node = index.get(Props.ID, id).getSingle();
        return map(node);
    }

    @Override
    public List<Team> mapAll(Index<Node> index) {
        List<Team> teams = new ArrayList<>();
        IndexHits<Node> nodes = index.query(Props.ID, Props.ALL);
        if (nodes != null) {
            LOGGER.debug("{} teams found", nodes.size());
            for (Node node : nodes) {
                Team team = map(node);
                if (team != null) {
                    teams.add(team);
                }
            }
        }
        return teams;
    }

    @Override
    public Team map(Node node) {
        if (node != null) {
            Team team = new Team();
            team.setId((Integer) node.getProperty(Props.ID));
            return team;
        } else {
            return null;
        }
    }

    @Override
    public Node map(Node node, Team team) {
        if (node != null && team != null) {
            node.addLabel(createLabel(Team.class));
            node.setProperty(Props.ID, team.getId());
            return node;
        } else {
            return null;
        }
    }
}
