package peaksoft.dto.responses;

import lombok.*;

import java.util.List;


@Builder
public record CompanyResponse(
     Long id,
     String name,
     String country,
     String address,
     String phoneNumber,
     List<String> coursesNamesOfCompany,
     List<String> groupsNamesOfCompany,
     List<String> instructorsNamesOfCompany,
         Long countOfStudents
) {
}
