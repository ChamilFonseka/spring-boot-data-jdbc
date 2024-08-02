package dev.chafon.datajdbc;

import net.datafaker.Faker;
import dev.chafon.datajdbc.owner.Owner;
import dev.chafon.datajdbc.owner.OwnerDto;

public class TestUtil {

    private static final Faker faker = new Faker();

    public static Owner generateFakeOwner() {
        return Owner.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .street(faker.address().streetAddress())
                .city(faker.address().city())
                .state(faker.address().stateAbbr())
                .zipCode(faker.address().zipCode())
                .build();
    }

    public static OwnerDto generateFakeOwnerDto() {
        return new OwnerDto(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().phoneNumber(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().stateAbbr(),
                faker.address().zipCode());
    }
}
