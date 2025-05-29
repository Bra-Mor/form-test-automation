package utils;

import model.FormModel;
import net.datafaker.Faker;

public class FormModelFactory {

    private static final Faker faker = new Faker();

    public FormModel createFakeFormModel() {
        return new FormModel(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.numerify("##########"),
                faker.address().fullAddress());
    }

    public String createFakeEmail() {
        return faker.internet().emailAddress();
    }

    public String createFakeAddress() {
        return faker.address().fullAddress();
    }

}