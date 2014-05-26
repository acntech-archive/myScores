package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Gambler;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import java.util.List;

public class GamblerMapper extends Mapper<Gambler> {

    @Override
    public Gambler mapNode(Index<Node> index, int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Gambler> mapAllNodes(Index<Node> index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Gambler mapNode(Node node) {
        Gambler gambler = new Gambler();
        gambler.setId((Integer) node.getProperty(Props.ID));
        gambler.setName((String) node.getProperty(Props.NAME));
        gambler.setActive((Boolean) node.getProperty(Props.ACTIVE));
        return gambler;
    }

    @Override
    public Node mapNode(Node node, Gambler gambler) {
        node.setProperty(Props.ID, gambler.getId());
        node.setProperty(Props.NAME, gambler.getName());
        node.setProperty(Props.ACTIVE, gambler.isActive());
        return node;
    }
}
