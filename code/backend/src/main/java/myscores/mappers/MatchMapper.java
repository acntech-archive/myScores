package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Match;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import java.util.List;

public class MatchMapper extends Mapper<Match> {

    @Override
    public Match mapNode(Index<Node> index, int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Match> mapAllNodes(Index<Node> index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Match mapNode(Node node) {
        Match match = new Match();
        match.setId((Integer) node.getProperty(Props.ID));
        return match;
    }

    @Override
    public Node mapNode(Node node, Match match) {
        node.setProperty(Props.ID, match.getId());
        return node;
    }
}
