package peaksoft.dto.requests;

import peaksoft.entites.Company;

public record CompanyRequest(
        String name,
        String country,
        String address,
        String phoneNumber
) {
    public Company build() {
        Company newCompany = new Company();
        newCompany.setName(this.name);
        newCompany.setCountry(this.country);
        newCompany.setAddress(this.address);
        newCompany.setPhoneNumber(this.phoneNumber);
        return newCompany;
    }
}
