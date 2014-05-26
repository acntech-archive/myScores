package myscores.services;

import myscores.domain.Gambler;
import myscores.repositories.GamblerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class GamblerService extends Service<Gambler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerService.class);

    @Inject
    private GamblerRepository repository;

    @Override
    public Gambler get(int id) {
        LOGGER.info("Get gambler for id {}", id);
        try {
            return repository.read(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while getting gambler for id " + id, e);
        }
    }

    @Override
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        try {
            return repository.find();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while finding gamblers", e);
        }
    }

    @Override
    public void register(Gambler gambler) {
        int id = repository.getNextId();
        LOGGER.info("Register gambler with id {}", id);
        gambler.setId(id);
        gambler.setActive(Boolean.FALSE);
        try {
            repository.create(gambler);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering gambler with id " + id, e);
        }
    }

    public void activate(int id) {
        Gambler gambler = get(id);
        if (gambler == null) {
            throw new ServiceException("No gambler found for id " + id);
        } else if (gambler.isActive()) {
            throw new ServiceException("Gambler with id " + id + " is already active");
        } else {
            LOGGER.info("Activate gambler with id {}", id);
            gambler.setActive(Boolean.TRUE);
            try {
                repository.update(gambler);
            } catch (Exception e) {
                throw new ServiceException("An error occurred while activating gambler with id " + id, e);
            }
        }
    }
}
