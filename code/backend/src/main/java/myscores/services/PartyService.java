package myscores.services;

import myscores.domain.Party;
import myscores.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
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
        LOGGER.info("Register party {}", party.getName());
        try {
            repository.create(party);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering party " + party.getName(), e);
        }
    }

    @Override
    public void change(Party party) {
        LOGGER.info("Change party {} with id {}", party.getName(), party.getId());
        try {
            repository.update(party);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while changing party " + party.getName() + " with id " + party.getId(), e);
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete party with id {}", id);
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting party with id " + id, e);
        }
    }

    public void add(int id, List<Integer> gamblers) {
        LOGGER.info("Adding gamblers to party with id {}", id);
        if (gamblers == null) {
            throw new ServiceException("Gambler list to be added to party is null");
        } else {
            try {
                for (int gambler : gamblers) {
                    LOGGER.info("Adding gambler with id {} to party with id {}", gambler, id);
                    repository.add(id, gambler);
                }
            } catch (Exception e) {
                throw new ServiceException("An error occurred while adding gamblers to party with id " + id, e);
            }
        }
    }

    public void remove(int id, List<Integer> gamblers) {
        LOGGER.info("Removing gamblers from party with id {}", id);
        if (gamblers == null) {
            throw new ServiceException("Gambler list to be added to party is null");
        } else {
            try {
                for (int gambler : gamblers) {
                    LOGGER.info("Removing gambler with id {} from party with id {}", gambler, id);
                    repository.remove(id, gambler);
                }
            } catch (Exception e) {
                throw new ServiceException("An error occurred while removing gamblers from party with id " + id, e);
            }
        }
    }
}
