package dev.chafon.datajdbc.owner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;

import static dev.chafon.datajdbc.TestUtil.generateFakeOwner;
import static org.assertj.core.api.Assertions.assertThat;

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
}