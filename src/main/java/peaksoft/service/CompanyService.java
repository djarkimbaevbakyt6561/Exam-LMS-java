package peaksoft.service;

import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.dto.responses.SimpleResponse;

public interface CompanyService {
    SimpleResponse save(CompanyRequest companyRequest);

    CompanyResponse findById(Long companyId);

    SimpleResponse update(Long companyId, CompanyRequest companyRequest);

    SimpleResponse deleteById(Long companyId);
}
