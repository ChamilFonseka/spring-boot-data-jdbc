package dev.chafon.datajdbc.owner;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<OwnerView> findOwnerViewBy();

    Optional<OwnerView> findOwnerViewById(Long id);
}
