package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCompanyResponse;
import peaksoft.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyAPI {
    private final CompanyService companyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{companyId}")
    public UnionCompanyResponse getById(@PathVariable Long companyId) {
        return companyService.findById(companyId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.save(companyRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{companyId}")
    public SimpleResponse update(@PathVariable Long companyId, @RequestBody CompanyRequest companyRequest) {
        return companyService.update(companyId, companyRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{companyId}")
    public SimpleResponse delete(@PathVariable Long companyId){
        return companyService.deleteById(companyId);
    }
}
