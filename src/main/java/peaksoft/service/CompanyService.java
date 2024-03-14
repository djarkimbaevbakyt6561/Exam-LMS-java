package peaksoft.service;

import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCompanyResponse;

public interface CompanyService {
    SimpleResponse save(CompanyRequest companyRequest);

    UnionCompanyResponse findById(Long companyId);

    SimpleResponse update(Long companyId, CompanyRequest companyRequest);

    SimpleResponse deleteById(Long companyId);
}
