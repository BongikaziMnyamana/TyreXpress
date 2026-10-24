package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Tyre;
import za.ac.cput.repository.ITyreRepository;
//import za.ac.cput.repository.TyreRepositoryImpl;

import java.util.List;

@Service
public class TyreServiceImpl implements ITyreService {

    private ITyreRepository repository;

    public TyreServiceImpl(ITyreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tyre create(Tyre tyre) {
        return repository.save(tyre);
    }

    @Override
    public Tyre read(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Tyre update(Tyre tyre) {
        if (tyre.getProductID() == null ||
                !repository.existsById(tyre.getProductID())) {
            throw new IllegalArgumentException(
                    "Tyre not found. Cannot update product."
            );
        }

        return repository.save(tyre);
    }

    @Override
    public boolean delete(Long tyreId) {
        if (repository.existsById(tyreId)) {
            repository.deleteById(tyreId);
            return true;
        }
        return false;
    }

    @Override
    public List<Tyre> getAll() {
        return repository.findAll();
    }

 }
