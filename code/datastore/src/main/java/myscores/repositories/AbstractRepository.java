package myscores.repositories;

import myscores.constants.Key;
import myscores.constants.KeyValue;
import myscores.constants.NodeIndex;
import myscores.database.GraphDatabase;
import myscores.mappers.NodeMapper;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import java.util.List;

public abstract class AbstractRepository<T> implements Repository<T> {

    @Override
    public void add(Object parentId, Object childId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void remove(Object parentId, Object childId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    protected T read(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex, Object id) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(nodeIndex.getName());
            T object = mapper.map(index, id);
            if (object != null) {
                tx.success();
                return object;
            } else {
                tx.failure();
                throw new RepositoryException("No node found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Read failed", e);
        }
    }

    protected List<T> find(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(nodeIndex.getName());
            List<T> objects = mapper.mapAll(index);
            tx.success();
            return objects;
        } catch (Exception e) {
            throw new RepositoryException("Find failed", e);
        }
    }

    protected void create(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex, T object, Object id) {
        create(database, mapper, nodeIndex, object, id, null);
    }

    protected void create(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex, T object, Object id, KeyValue... keyValues) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(nodeIndex.getName());
            if (!mapper.exists(index, id)) {
                Node node = mapper.map(database.createNode(), object);
                index.add(node, Key.ID.getName(), id);
                if (keyValues != null) {
                    for (KeyValue keyValue : keyValues) {
                        index.add(node, keyValue.getKey().getName(), keyValue.getValue());
                    }
                }
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("Node with id " + id + " already exists");
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Create failed", e);
        }
    }

    protected void update(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex, Object id, KeyValue... keyValues) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(nodeIndex.getName());
            Node node = mapper.getNode(index, id);
            if (node != null) {
                for (KeyValue keyValue : keyValues) {
                    node.setProperty(keyValue.getKey().getName(), keyValue.getValue());
                }
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No node found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Update failed", e);
        }
    }

    protected void delete(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex, Object id) {
        delete(database, mapper, nodeIndex, id, null);
    }

    protected void delete(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex, Object id, Key... keys) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(nodeIndex.getName());
            Node node = mapper.getNode(index, id);
            if (node != null) {
                index.remove(node, Key.ID.getName(), node.getProperty(Key.ID.getName()));
                if (keys != null) {
                    for (Key key : keys) {
                        index.remove(node, key.getName(), node.getProperty(key.getName()));
                    }
                }
                node.delete();
                tx.success();
            } else {
                tx.failure();
                throw new RepositoryException("No node found for id " + id);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Delete failed", e);
        }
    }

    protected void add(GraphDatabase database, NodeMapper<T> mapper, NodeIndex parentNodeIndex, NodeIndex childNodeIndex, Object parentId, Object childId, RelationshipType type, KeyValue... keyValues) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> parentIndex = database.getNodeIndex(parentNodeIndex.getName());
            Node partyNode = mapper.getNode(parentIndex, parentId);
            if (partyNode != null) {
                Index<Node> gamblerIndex = database.getNodeIndex(childNodeIndex.getName());
                Node gamblerNode = mapper.getNode(gamblerIndex, childId);
                if (gamblerNode != null) {
                    Relationship relationship = gamblerNode.createRelationshipTo(partyNode, type);
                    if (keyValues != null) {
                        for (KeyValue keyValue : keyValues) {
                            relationship.setProperty(keyValue.getKey().getName(), keyValue.getValue());
                        }
                    }
                    tx.success();
                } else {
                    tx.failure();
                    throw new RepositoryException("No child node found for id " + childId);
                }
            } else {
                tx.failure();
                throw new RepositoryException("No parent node found for id " + parentId);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Add failed", e);
        }
    }

    public void remove(GraphDatabase database, NodeMapper<T> mapper, NodeIndex parentNodeIndex, NodeIndex childNodeIndex, Object parentId, Object childId, RelationshipType type, Direction dir) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> gamblerIndex = database.getNodeIndex(parentNodeIndex.getName());
            Node gamblerNode = mapper.getNode(gamblerIndex, childId);
            if (gamblerNode != null) {
                Index<Node> partyIndex = database.getNodeIndex(childNodeIndex.getName());
                Node partyNode = mapper.getNode(partyIndex, parentId);
                if (partyNode != null) {
                    Iterable<Relationship> belongsTo = gamblerNode.getRelationships(type, dir);
                    for (Relationship relationship : belongsTo) {
                        for (Node relationshipNode : relationship.getNodes()) {
                            if (relationshipNode != null && relationshipNode.hasLabel(mapper.createLabel()) && parentId == mapper.getIntProperty(relationshipNode, Key.ID)) {
                                relationship.delete();
                            }
                        }
                    }
                    tx.success();
                } else {
                    tx.failure();
                    throw new RepositoryException("No child node found for id " + childId);
                }
            } else {
                tx.failure();
                throw new RepositoryException("No parent node found for id " + parentId);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Add failed", e);
        }
    }

    protected int nextId(GraphDatabase database, NodeMapper<T> mapper, NodeIndex nodeIndex) {
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(nodeIndex.getName());
            int id = mapper.getNodeCount(index) + 1;
            tx.success();
            return id;
        } catch (Exception e) {
            throw new RepositoryException("Getting next index failed", e);
        }
    }
}
