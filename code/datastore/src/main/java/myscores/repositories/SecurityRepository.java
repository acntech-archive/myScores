package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.AuthGroup;
import myscores.domain.AuthUser;
import myscores.mappers.AuthGroupMapper;
import myscores.mappers.AuthUserMapper;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SecurityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private AuthUserMapper authUserMapper;

    @Inject
    private AuthGroupMapper authGroupMapper;


    public AuthUser readAuthUser(String name) {
        LOGGER.info("Read auth user {}", name);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.AUTH_USERS_INDEX);
            AuthUser authUser = authUserMapper.map(index, name);
            if (authUser != null) {
                tx.success();
                return authUser;
            } else {
                tx.failure();
                throw new RepositoryException("No auth user found for with name " + name);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Read failed", e);
        }
    }

    public AuthGroup readAuthGroup(String name) {
        LOGGER.info("Read auth group {}", name);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.AUTH_GROUPS_INDEX);
            AuthGroup authGroup = authGroupMapper.map(index, name);
            if (authGroup != null) {
                tx.success();
                return authGroup;
            } else {
                tx.failure();
                throw new RepositoryException("No auth group found for with name " + name);
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Read failed", e);
        }
    }
}
