package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Match;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchMapper extends NodeMapper<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchMapper.class);

    @Override
    public Match map(Node node) {
        if (node != null && hasLabel(node, createLabel())) {
            Match match = new Match();
            match.setId(getIdProperty(node));
            return match;
        } else {
            return null;
        }
    }

    @Override
    public Node map(Node node, Match match) {
        if (node != null && match != null) {
            node.addLabel(createLabel());
            node.setProperty(Props.ID, match.getId());
            return node;
        } else {
            return null;
        }
    }

    @Override
    public Label createLabel() {
        return createLabel(Match.class);
    }
}
