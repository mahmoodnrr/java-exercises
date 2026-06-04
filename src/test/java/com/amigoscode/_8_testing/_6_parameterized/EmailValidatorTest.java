package com.amigoscode._8_testing._6_parameterized;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise: Parameterized Tests
 * <p>
 * Practice using JUnit 5 parameterized tests to test the EmailValidator
 * with multiple sets of input data.
 */
@DisplayName("Email Validator Parameterized Tests")
class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    // TODO: 1 - Use @ParameterizedTest with @ValueSource for valid emails.
    //  Create a parameterized test that receives a String parameter.
    //  Annotate with @ValueSource(strings = {"user@example.com", "test@domain.org",
    //      "name.surname@company.co.uk", "user123@test.com"}).
    //  Assert that emailValidator.isValid(email) returns true for each.
    @ParameterizedTest
    @ValueSource(strings = {"user@example.com", "test@domain.org", "name.surname@company.co.uk", "user123@test.com"})
    void canTestIsValidEmails_whenTrue(String input) {
        assertTrue(emailValidator.isValid(input));
    }

    // TODO: 2 - Use @ParameterizedTest with @ValueSource for invalid emails.
    //  Test invalid email formats: "plaintext", "@missing-local.com",
    //      "missing-domain@", "missing@dot", "user@@double.com".
    //  Assert that emailValidator.isValid(email) returns false for each.
    @ParameterizedTest
    @ValueSource(strings = {"missing-domain@", "missing@dot", "user@@double.com"})
    void canTestIsValidEmails_whenFalse(String input) {
        assertFalse(emailValidator.isValid(input));
    }

    // TODO: 3 - Use @NullAndEmptySource to test null and empty inputs.
    //  Create a parameterized test annotated with @ParameterizedTest
    //  and @NullAndEmptySource.
    //  Assert that emailValidator.isValid(email) returns false for null and empty strings.
    //  Hint: You can combine @NullAndEmptySource with @ValueSource by also adding
    //  @ValueSource(strings = {"  ", "\t", "\n"}) to test whitespace-only strings.
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void canTestIsNullAndEmpty_whenFalse(String input) {
        assertFalse(emailValidator.isValid(input));
    }

    // TODO: 4 - Use @CsvSource with email and expected result pairs.
    //  Annotate with @CsvSource({
    //      "user@example.com, true",
    //      "invalid-email, false",
    //      "test@domain.org, true",
    //      "@no-local.com, false",
    //      "no-domain@, false"
    //  }).
    //  The test method should take (String email, boolean expected) parameters.
    //  Assert that emailValidator.isValid(email) equals expected.
    @ParameterizedTest
    @CsvSource({
            "user@example.com, true",
            "invalid-email, false",
            "test@domain.org, true",
            "@no-local.com, false",
            "no-domain@, false"
    })
    void canTestIsValidEmail(String input, boolean expected) {
        assertEquals(expected, emailValidator.isValid(input));
    }

    // TODO: 5 - Use @MethodSource to provide test arguments from a static method.
    //  Create a static method named emailProvider() that returns Stream<Arguments>.
    //  Use Arguments.of("email", expectedBoolean) to provide test cases.
    //  Include at least 4 different test cases.
    //  Hint:
    //  static Stream<Arguments> emailProvider() {
    //      return Stream.of(
    //          Arguments.of("valid@test.com", true),
    //          Arguments.of("invalid", false),
    //          ...
    //      );
    //  }

    static Stream<Arguments> emailProvider() {
        return Stream.of(
                Arguments.of("valid@test.com", true),
                Arguments.of("invalid", false),
                Arguments.of("invalid@test", false),
                Arguments.of("invalid@@test", false)
                );
    }

    @ParameterizedTest
    @MethodSource("emailProvider")
    void shouldValidateEmailInput(String email, boolean expected) {
        assertEquals(expected, emailValidator.isValid(email));
    }

    // TODO: 6 - Use @EnumSource to test with enum values (if applicable).
    //  Create a simple enum inside this test class:
    //  enum EmailDomain { GMAIL("gmail.com"), YAHOO("yahoo.com"), OUTLOOK("outlook.com");
    //      final String domain; EmailDomain(String d) { this.domain = d; }
    //  }
    //  Write a parameterized test with @EnumSource(EmailDomain.class).
    //  For each domain, construct "test@" + domain.domain and assert it's valid.

    enum EmailDomain {
        GMAIL("gmail.com"),
        YAHOO("yahoo.com"),
        OUTLOOK("outlook.com");

        private final String domain;

        EmailDomain(String domain) {
            this.domain = domain;
        }
    }

    @ParameterizedTest()
    @EnumSource(EmailDomain.class)
    void shouldTestEmailDomains(EmailDomain domain) {
        assertTrue(emailValidator.isValid("test@" + domain.domain));
    }

}
