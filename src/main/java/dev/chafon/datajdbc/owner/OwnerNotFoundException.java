package dev.chafon.datajdbc.owner;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(Long id) {
        super("Owner with id " + id + " not found");
    }
}
