package myscores.services;

import myscores.domain.Gambler;
import myscores.repositories.GamblerRepository;
import myscores.repositories.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class GamblerService extends Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerService.class);

    @Inject
    private GamblerRepository repository;

    public Gambler get(int id) {
        LOGGER.info("Get gambler for id {}", id);
        return repository.read(id);
    }

    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        return repository.find();
    }

    public boolean register(Gambler gambler) {
        int id = repository.getNextId();
        LOGGER.info("Register gambler with id {}", id);
        gambler.setId(id);
        gambler.setActive(Boolean.FALSE);
        try {
            repository.create(gambler);
            return Boolean.TRUE;
        } catch (RepositoryException e) {
            LOGGER.error("Error while registering gambler with id " + id, e);
            return Boolean.FALSE;
        }
    }

    public boolean activate(int id) {
        Gambler gambler = repository.read(id);
        if (gambler == null) {
            LOGGER.warn("No gambler found for id {}", id);
            return Boolean.FALSE;
        } else if (gambler.isActive()) {
            LOGGER.warn("Gambler with id {} is already active", id);
            return Boolean.FALSE;
        } else {
            LOGGER.info("Activate gambler with id {}", id);
            gambler.setActive(Boolean.TRUE);
            repository.update(gambler);
            return Boolean.TRUE;
        }
    }
}
