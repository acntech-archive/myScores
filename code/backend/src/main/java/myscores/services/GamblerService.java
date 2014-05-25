package myscores.services;

import myscores.domain.Gambler;
import myscores.repositories.GamblerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class GamblerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerService.class);

    @Inject
    private GamblerRepository repository;

    public Gambler get(String name) {
        LOGGER.info("Get gambler for name {}", name);
        return repository.read(name);
    }

    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        return repository.find();
    }

    public boolean register(Gambler gambler) {
        String id = "" + gambler.getId();
        LOGGER.info("Register gambler with id {}", id);
        repository.create(id, gambler);
        return Boolean.TRUE;
    }

    public boolean activate(String name) {
        Gambler gambler = repository.read(name);
        if (gambler == null) {
            LOGGER.info("No gambler found for name {}", name);
            return Boolean.FALSE;
        } else if (gambler.isActive()) {
            LOGGER.info("Gambler with name {} is already active", name);
            return Boolean.FALSE;
        } else {
            String id = "" + gambler.getId();
            LOGGER.info("Activate gambler with name {}", name);
            gambler.setActive(Boolean.TRUE);
            repository.update(id, gambler);
            return Boolean.TRUE;
        }
    }
}
