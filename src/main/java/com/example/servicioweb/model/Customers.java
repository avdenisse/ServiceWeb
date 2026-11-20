package com.example.servicioweb.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customers {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String country;
    private String city;
    private String address;
    private Double annualIncome;
    private int creditScore;
    private Boolean unpaidDebt;
    private Double unpaidDebtAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customers() {
    }

    public Customers(int id, String firstName, String lastName, String email, String phone, LocalDate dateOfBirth, String country, String city, String address, Double annualIncome, int creditScore, Boolean unpaidDebt, Double unpaidDebtAmount, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.city = city;
        this.address = address;
        this.annualIncome = annualIncome;
        this.creditScore = creditScore;
        this.unpaidDebt = unpaidDebt;
        this.unpaidDebtAmount = unpaidDebtAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public Double getUnpaidDebtAmount() {
        return unpaidDebtAmount;
    }

    public void setUnpaidDebtAmount(Double unpaidDebtAmount) {
        this.unpaidDebtAmount = unpaidDebtAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public Boolean getUnpaidDebt() {
        return unpaidDebt;
    }

    public void setUnpaidDebt(Boolean unpaidDebt) {
        this.unpaidDebt = unpaidDebt;
    }
}
