package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyAPI {

    private final CompanyService companyService;

    @GetMapping("/{companyId}")
    public CompanyResponse getById(@PathVariable Long companyId) {
        return companyService.findById(companyId);
    }

    @PostMapping("/save")
    public SimpleResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.save(companyRequest);
    }

    @PutMapping("/{companyId}")
    public SimpleResponse update(@PathVariable Long companyId, @RequestBody CompanyRequest companyRequest) {
        return companyService.update(companyId, companyRequest);
    }
    @DeleteMapping("/{companyId}")
    public SimpleResponse delete(@PathVariable Long companyId){
        return companyService.deleteById(companyId);
    }
}
