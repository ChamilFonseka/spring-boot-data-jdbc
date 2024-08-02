package dev.chafon.datajdbc.owner;

import dev.chafon.datajdbc.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "owners")
public class Owner extends BaseEntity {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String street;

    private String city;

    private String state;

    private String zipCode;

    public static Owner of(OwnerDto ownerDto) {
        return Owner.builder()
                .firstName(ownerDto.firstName())
                .lastName(ownerDto.lastName())
                .phoneNumber(ownerDto.phoneNumber())
                .street(ownerDto.street())
                .city(ownerDto.city())
                .state(ownerDto.state())
                .zipCode(ownerDto.zipCode())
                .build();
    }

    public void update(OwnerDto ownerDto) {
        this.firstName = ownerDto.firstName();
        this.lastName = ownerDto.lastName();
        this.phoneNumber = ownerDto.phoneNumber();
        this.street = ownerDto.street();
        this.city = ownerDto.city();
        this.state = ownerDto.state();
        this.zipCode = ownerDto.zipCode();
    }

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
