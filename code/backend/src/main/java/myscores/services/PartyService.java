package myscores.services;

import myscores.domain.Gambler;
import myscores.domain.Party;
import myscores.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class PartyService extends Service<Party> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyService.class);

    @Inject
    private PartyRepository repository;

    @Override
    public Party get(int id) {
        LOGGER.info("Get party for id {}", id);
        try {
            return repository.read(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while getting party for id " + id, e);
        }
    }

    @Override
    public List<Party> find() {
        LOGGER.info("Find parties");
        try {
            return repository.find();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while finding parties", e);
        }
    }

    @Override
    public void register(Party party) {
        int id = repository.getNextId();
        LOGGER.info("Register party with id {}", id);
        try {
            repository.create(party);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering party with id " + id, e);
        }
    }

    public void add(Party party) {
        int id = party.getId();
        LOGGER.info("Adding gamblers to party with id {}", id);
        try {
            for (Gambler gambler : party.getGamblers()) {
                repository.add(id, gambler.getId());
            }
        } catch (Exception e) {
            throw new ServiceException("An error occurred while adding gamblers to party with id " + id, e);
        }
    }
}
