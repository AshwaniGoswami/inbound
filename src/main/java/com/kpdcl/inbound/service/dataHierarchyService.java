package com.kpdcl.inbound.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kpdcl.inbound.entity.dataHierarchy;
import com.kpdcl.inbound.repository.dataRepository;

@Service
public class dataHierarchyService {
	
	@Autowired
	private dataRepository dataRepo;
	
	
	public ResponseEntity<String> findByOfficeCode(String officeCode, String code, String officeName) {
        if (officeCode.length() != 8) {
            throw new IllegalArgumentException("Office code must be 8 digits long");
        }

        String wingCode = officeCode.substring(0, 2);
        String circleCode = officeCode.substring(2, 4);
        String divisionCode = officeCode.substring(4, 6);

        Optional<dataHierarchy> hierarchy = Optional.empty();
        
        String office_Name=officeName.toUpperCase();
        
        if (office_Name.equals("DIVISION_CODE")) {
            if (code.equals(divisionCode)) {
                return ResponseEntity.ok("Match found for DIVISION_CODE");
            }
        } else if (office_Name.equals("CIRCLE_CODE")) {
            if (code.equals(circleCode)) {
                return ResponseEntity.ok("Match found for CIRCLE_CODE");
            }
        } else if (office_Name.equals("WING_CODE")) {
            if (code.equals(wingCode)) {
                return ResponseEntity.ok("Match found for WING_CODE");
            }
        }
    
        return ResponseEntity.ok("not matched");
    
    }

}
