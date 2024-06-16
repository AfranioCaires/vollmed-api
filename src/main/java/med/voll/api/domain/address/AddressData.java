package med.voll.api.domain.address;

public record AddressData(String street,
                          String neighbour,
                          String zipcode,
                          String city,
                          String state,
                          String complement,
                          String number) {
}
