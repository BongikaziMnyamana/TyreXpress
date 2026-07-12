package za.ac.cput.service;

import za.ac.cput.domain.Rim;

import java.util.List ;

public interface IRimService {

    Rim create(Rim rim);

    Rim read(String id);

    Rim update(Rim rim);

    boolean delete(String id);

    List<Rim> getAll();
}