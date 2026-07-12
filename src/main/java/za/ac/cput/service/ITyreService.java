package za.ac.cput.service;

import za.ac.cput.domain.Tyre;

import java.util.List;

public interface ITyreService {

    Tyre create(Tyre tyre);

    Tyre read(String id);

    Tyre update(Tyre tyre);

    boolean delete(String id);

    List<Tyre> getAll();
}
