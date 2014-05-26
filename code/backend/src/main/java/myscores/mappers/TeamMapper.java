package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Team;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import java.util.List;

public class TeamMapper extends Mapper<Team> {

    @Override
    public Team mapNode(Index<Node> index, int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Team> mapAllNodes(Index<Node> index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Team mapNode(Node node) {
        Team team = new Team();
        team.setId((Integer) node.getProperty(Props.ID));
        return team;
    }

    @Override
    public Node mapNode(Node node, Team team) {
        node.setProperty(Props.ID, team.getId());
        return node;
    }
}
