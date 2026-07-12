package za.ac.cput.service;

import za.ac.cput.domain.Rim;
import za.ac.cput.repository.RimRepositoryImpl;

import java.util.List;

public class RimServiceImpl implements IRimService {

    private static RimServiceImpl service;

    private RimRepositoryImpl repository;

    private RimServiceImpl() {
        repository = RimRepositoryImpl.getRepository();
    }

    public static RimServiceImpl getService() {

        if (service == null) {
            service = new RimServiceImpl();
        }

        return service;
    }

    @Override
    public Rim create(Rim rim) {
        return repository.create(rim);
    }

    @Override
    public Rim read(String id) {
        return repository.read(id);
    }

    @Override
    public Rim update(Rim rim) {
        return repository.update(rim);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public List<Rim> getAll() {
        return repository.getAll();
    }
}