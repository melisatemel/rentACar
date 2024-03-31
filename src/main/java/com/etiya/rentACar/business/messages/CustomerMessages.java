    package com.etiya.rentACar.business.messages;

public class CustomerMessages {
    public static final String fullNameCannotBeEmpty = "fullName cannot be empty!";
    public static final String emailCannotBeEmpty = "email cannot be empty!";
    public static final String phoneCannotBeEmpty = "phone cannot be empty!";
    public static final String nationalNoCannotBeEmpty = "nationalNo cannot be empty!";
    public static final String birthDateCannotBeEmpty = "birthDate cannot be empty!";
    public static final String customerNationalIdCannotBeDuplicated = "Customer NationalId Exists";
    public static final String customerEmailCannotBeDuplicated = "Customer Email Exists";
    public static final String customerPhoneCannotBeDuplicated = "Customer Phone Exists";
    public static final String customerBirthDateCannotBeYoungerThan18 = "Customer must be older than 18 years old";
    public static final String customerNotFound = "Customer not found";
}
