package dev.chafon.datajdbc.owner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;
import java.util.Optional;

import static dev.chafon.datajdbc.TestUtil.generateFakeOwner;
import static dev.chafon.datajdbc.TestUtil.generateFakeOwnerDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import({TestcontainersConfiguration.class, OwnerService.class})
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void shouldReturnAllOwners() {
        Owner owner1 = ownerRepository.save(generateFakeOwner());

        Owner owner2 = ownerRepository.save(generateFakeOwner());

        List<OwnerView> allOwners = ownerService.getOwners();
        assertThat(allOwners).isNotNull();
        assertThat(allOwners).hasSize(2);

        assertThat(allOwners).extracting(OwnerView::getId).contains(owner1.getId(), owner2.getId());

        assertThat(allOwners)
                .extracting(OwnerView::getFirstName)
                .contains(owner1.getFirstName(), owner2.getFirstName());

        assertThat(allOwners).extracting(OwnerView::getLastName).contains(owner1.getLastName(), owner2.getLastName());

        assertThat(allOwners)
                .extracting(OwnerView::getPhoneNumber)
                .contains(owner1.getPhoneNumber(), owner2.getPhoneNumber());

        assertThat(allOwners)
                .extracting(OwnerView::getStreet)
                .contains(owner1.getStreet(), owner2.getStreet());

        assertThat(allOwners)
                .extracting(OwnerView::getCity)
                .contains(owner1.getCity(), owner2.getCity());

        assertThat(allOwners)
                .extracting(OwnerView::getState)
                .contains(owner1.getState(), owner2.getState());

        assertThat(allOwners)
                .extracting(OwnerView::getZipCode)
                .contains(owner1.getZipCode(), owner2.getZipCode());
    }

    @Test
    void shouldReturnOwnerById() {
        Owner owner = ownerRepository.save(generateFakeOwner());

        OwnerView fetchedOwner = ownerService.getOwner(owner.getId());
        assertThat(fetchedOwner.getId()).isEqualTo(owner.getId());
        assertThat(fetchedOwner.getFirstName()).isEqualTo(owner.getFirstName());
        assertThat(fetchedOwner.getLastName()).isEqualTo(owner.getLastName());
        assertThat(fetchedOwner.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
        assertThat(fetchedOwner.getStreet()).isEqualTo(owner.getStreet());
        assertThat(fetchedOwner.getCity()).isEqualTo(owner.getCity());
        assertThat(fetchedOwner.getState()).isEqualTo(owner.getState());
        assertThat(fetchedOwner.getZipCode()).isEqualTo(owner.getZipCode());
    }

    @Test
    void shouldThrowOwnerNotFoundException() {
        long id = 99L;
        assertThatThrownBy(() -> ownerService.getOwner(id))
                .isInstanceOf(OwnerNotFoundException.class)
                .hasMessageContaining("Owner with id " + id + " not found");
    }

    @Test
    void shouldCreateOwner() {
        OwnerDto ownerDto = generateFakeOwnerDto();
        Long id = ownerService.createOwner(ownerDto);

        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        assertThat(optionalOwner).isPresent();

        Owner owner = optionalOwner.get();
        assertThat(owner.getFirstName()).isEqualTo(ownerDto.firstName());
        assertThat(owner.getLastName()).isEqualTo(ownerDto.lastName());
        assertThat(owner.getPhoneNumber()).isEqualTo(ownerDto.phoneNumber());
        assertThat(owner.getStreet()).isEqualTo(ownerDto.street());
        assertThat(owner.getCity()).isEqualTo(ownerDto.city());
        assertThat(owner.getState()).isEqualTo(ownerDto.state());
        assertThat(owner.getZipCode()).isEqualTo(ownerDto.zipCode());
    }

    @Test
    void shouldUpdateOwner() {
        Owner owner = ownerRepository.save(generateFakeOwner());
        OwnerDto ownerDto = generateFakeOwnerDto();

        ownerService.updateOwner(owner.getId(), ownerDto);

        Optional<Owner> optionalOwner = ownerRepository.findById(owner.getId());
        assertThat(optionalOwner).isPresent();

        Owner updatedOwner = optionalOwner.get();
        assertThat(updatedOwner.getFirstName()).isEqualTo(ownerDto.firstName());
        assertThat(updatedOwner.getLastName()).isEqualTo(ownerDto.lastName());
        assertThat(updatedOwner.getPhoneNumber()).isEqualTo(ownerDto.phoneNumber());
        assertThat(updatedOwner.getStreet()).isEqualTo(ownerDto.street());
        assertThat(updatedOwner.getCity()).isEqualTo(ownerDto.city());
        assertThat(updatedOwner.getState()).isEqualTo(ownerDto.state());
        assertThat(updatedOwner.getZipCode()).isEqualTo(ownerDto.zipCode());
    }

    @Test
    void shouldThrowOwnerNotFoundExceptionOnUpdate() {
        long id = 99L;
        OwnerDto ownerDto = generateFakeOwnerDto();
        assertThatThrownBy(() -> ownerService.updateOwner(id, ownerDto))
                .isInstanceOf(OwnerNotFoundException.class)
                .hasMessageContaining("Owner with id " + id + " not found");
    }

    @Test
    void shouldDeleteOwner() {
        Owner owner = ownerRepository.save(generateFakeOwner());

        ownerService.deleteOwner(owner.getId());
        Optional<Owner> optionalOwner = ownerRepository.findById(owner.getId());
        assertThat(optionalOwner).isEmpty();
    }
}