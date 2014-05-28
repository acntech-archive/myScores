package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Match;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MatchMapper extends NodeMapper<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchMapper.class);

    @Override
    public Match map(Index<Node> index, int id) {
        Node node = index.get(Props.ID, id).getSingle();
        return map(node);
    }

    @Override
    public List<Match> mapAll(Index<Node> index) {
        List<Match> matches = new ArrayList<>();
        IndexHits<Node> nodes = index.query(Props.ID, Props.ALL);
        if (nodes != null) {
            LOGGER.debug("{} matches found", nodes.size());
            for (Node node : nodes) {
                Match match = map(node);
                if (match != null) {
                    matches.add(match);
                }
            }
        }
        return matches;
    }

    @Override
    public Match map(Node node) {
        if (node != null) {
            Match match = new Match();
            match.setId((Integer) node.getProperty(Props.ID));
            return match;
        } else {
            return null;
        }
    }

    @Override
    public Node map(Node node, Match match) {
        if (node != null && match != null) {
            node.addLabel(createLabel(Match.class));
            node.setProperty(Props.ID, match.getId());
            return node;
        } else {
            return null;
        }
    }
}
