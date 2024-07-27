//package com.kpdcl.inbound.service;
//
//import com.kpdcl.inbound.entity.approval_hierarchy;
//import com.kpdcl.inbound.repository.approvalHierarchyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
////import com.kpdcl.inbound.service.DateUtil;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;
//
//@Service
//public class ApprovalHierarchyService {
//
//    private static final Logger logger = Logger.getLogger(ApprovalHierarchyService.class.getName());
//
//    @Autowired
//    private approvalHierarchyRepository approvalHierarchyRepo;
//
//    public ResponseEntity<?> getApprovalHierarchy(String sanctionedLoadStr, String columnName) {
//        double sanctionedLoad;
//        
//        // Check if the table is empty
//        List<approval_hierarchy> allData = approvalHierarchyRepo.findAll();
//        if (allData.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Table is empty");
//        }
//
//        try {
//            //sanctionedLoad = Integer.parseInt(sanctionedLoadStr.trim());
//            sanctionedLoad = Double.parseDouble(sanctionedLoadStr.trim());
//        } catch (NumberFormatException e) {
//            logger.severe("Invalid sanctioned load value: " + sanctionedLoadStr);
//            return ResponseEntity.badRequest().body("Invalid sanctioned load value: " + sanctionedLoadStr);
//        }
//
//        // Ensure columnName is not null or empty
//        if (columnName == null || columnName.trim().isEmpty()) {
//            logger.severe("Column name is null or empty");
//            return ResponseEntity.badRequest().body("Column name is required");
//        }
//
//        try {
//            // Implement logic to fetch approval hierarchy based on sanctionedLoad and columnName
//            List<approval_hierarchy> approvalHierarchyList = fetchApprovalHierarchy(sanctionedLoad, columnName);
//            if (approvalHierarchyList.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body("Approval Hierarchy not found for sanctionedLoad: " + sanctionedLoad +
//                                " and columnName: " + columnName);
//            }
//
//            // Build response map
//            Map<String, Object> response = buildResponseMap(approvalHierarchyList, columnName, sanctionedLoad);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            logger.severe("Error occurred while fetching approval hierarchy: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error occurred: " + e.getMessage());
//        }
//    }
//
//    private List<approval_hierarchy> fetchApprovalHierarchy(double sanctionedLoad, String columnName) {
//        if (columnName == null) {
//            throw new IllegalArgumentException("columnName must not be null");
//        }
//
//        switch (columnName.toUpperCase()) {
//            case "EE":
//                if (sanctionedLoad > 25) {
//                    return approvalHierarchyRepo.findBySanctionedLoadAndEE(25);
//                }
//                break;
//            case "STD":
//                if (sanctionedLoad > 25) {
//                    return approvalHierarchyRepo.findBySanctionedLoadAndSTD(25);
//                }
//                break;
//            case "SE":
//                if (sanctionedLoad > 50) {
//                    return approvalHierarchyRepo.findBySanctionedLoadAndSE(50);
//                }
//                break;
//            case "CE":
//                if (sanctionedLoad > 100) {
//                    return approvalHierarchyRepo.findBySanctionedLoadAndCE(100);
//                }
//                break;
//            case "MD":
//                if (sanctionedLoad > 500) {
//                    return approvalHierarchyRepo.findBySanctionedLoadAndMD(500);
//                }
//                break;
//            case "KPTCL":
//                if (sanctionedLoad > 500) {
//                    return approvalHierarchyRepo.findBySanctionedLoadAndKPTCL(500);
//                }
//                break;
//            default:
//                logger.warning("Unsupported column name: " + columnName);
//                break;
//        }
//        return new ArrayList<>(); // Return an empty list if no matching conditions found
//    }
//
//    private Map<String, Object> buildResponseMap(List<approval_hierarchy> approvalHierarchyList, String columnName, double sanctionedLoad) {
//        List<Map<String, Object>> hierarchyDetails = new ArrayList<>();
//
//        for (approval_hierarchy approvalHierarchy : approvalHierarchyList) {
//            Map<String, Object> response = new HashMap<>();
//            if (approvalHierarchy != null) {
//                switch (columnName.toUpperCase()) {
//                    case "EE":
//                        response.put("EE_URL", approvalHierarchy.getEE_URL());
//                        response.put("EE_Remark", approvalHierarchy.getEE_Remark());
//                        response.put("Status", approvalHierarchy.getStatus());
//                        break;
//                    case "STD":
//                        response.put("STD_URL", approvalHierarchy.getSTD_URL());
//                        response.put("STD_status", approvalHierarchy.getSTD_status());
//                        response.put("STD_Remark", approvalHierarchy.getSTD_Remark());
//                        break;
//                    case "SE":
//                        response.put("SE_URL", approvalHierarchy.getSE_URL());
//                        response.put("SE_Remark", approvalHierarchy.getSE_Remark());
//                        response.put("Status", approvalHierarchy.getStatus());
//                        break;
//                    case "CE":
//                        response.put("CE_URL", approvalHierarchy.getCE_URL());
//                        response.put("CE_Remark", approvalHierarchy.getCE_Remark());
//                        response.put("Status", approvalHierarchy.getStatus());
//                        break;
//                    case "MD":
//                        response.put("MD_URL", approvalHierarchy.getMD_URL());
//                        response.put("MD_Remark", approvalHierarchy.getMD_Remark());
//                        response.put("Status", approvalHierarchy.getStatus());
//                        break;
//                    case "KPTCL":
//                        response.put("KPTCL_URL", approvalHierarchy.getKPTCL_URL());
//                        response.put("KPTCL_status", approvalHierarchy.getKPTCL_status());
//                        response.put("KPTCL_Remark", approvalHierarchy.getKPTCL_Remark());
//                        break;
//                    default:
//                        logger.warning("Unsupported column name: " + columnName);
//                        break;
//                }
//
//                // Add common fields
//                response.put("sanctionedLoad", approvalHierarchy.getSanctionedLoad());
//                response.put("category", approvalHierarchy.getCategory());
//                response.put("UPDATED_BY", approvalHierarchy.getUPDATED_BY());
//                response.put("UPDATED_ON", DateUtil.formatDate(approvalHierarchy.getUpdatedOn().getTime()));
//                response.put("caseId", approvalHierarchy.getHierarchy().getCase_id());
//
//                hierarchyDetails.add(response);
//            }
//        }
//
//        Map<String, Object> finalResponse = new HashMap<>();
//        if (hierarchyDetails.isEmpty()) {
//            finalResponse.put("message", "Approval Hierarchy table is empty");
//        } else {
//            finalResponse.put("approvalHierarchyDetails", hierarchyDetails);
//        }
//        return finalResponse;
//    }
//
//}




package com.kpdcl.inbound.service;

import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.repository.approvalHierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class ApprovalHierarchyService {

    private static final Logger logger = Logger.getLogger(ApprovalHierarchyService.class.getName());

    @Autowired
    private approvalHierarchyRepository approvalHierarchyRepo;

    public ResponseEntity<?> getApprovalHierarchy(String sanctionedLoadStr, String columnName) {
        double sanctionedLoad;

        // Check if the table is empty
        List<approval_hierarchy> allData = approvalHierarchyRepo.findAll();
        if (allData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Table is empty");
        }

        try {
            sanctionedLoad = Double.parseDouble(sanctionedLoadStr.trim());
        } catch (NumberFormatException e) {
            //logger.severe("Invalid sanctioned load value: " + sanctionedLoadStr);
            return ResponseEntity.badRequest().body("Invalid sanctioned load value: " + sanctionedLoadStr);
        }
       
        // Ensure columnName is not null or empty
        if (columnName == null || columnName.trim().isEmpty()) {
            logger.severe("Column name is null or empty");
            return ResponseEntity.badRequest().body("Column name is required");
        }

        try {
            // Implement logic to fetch approval hierarchy based on sanctionedLoad and columnName
            List<approval_hierarchy> approvalHierarchyList = fetchApprovalHierarchy(sanctionedLoad, columnName);
            if (approvalHierarchyList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Approval Hierarchy not found for sanctionedLoad: " + sanctionedLoad +
                                " and columnName: " + columnName);
            }

            // Build response map
            Map<String, Object> response = buildResponseMap(approvalHierarchyList, columnName, sanctionedLoad);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error occurred while fetching approval hierarchy: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }

    private List<approval_hierarchy> fetchApprovalHierarchy(double sanctionedLoad, String columnName) {
        if (columnName == null) {
            throw new IllegalArgumentException("columnName must not be null");
        }

        switch (columnName.toUpperCase()) {
            case "EE":
                if (sanctionedLoad > 25) {
                    return approvalHierarchyRepo.findBySanctionedLoadAndEE(25);
                }
                break;
            case "STD":
                if (sanctionedLoad > 25) {
                    return approvalHierarchyRepo.findBySanctionedLoadAndSTD(25);
                }
                break;
            case "SE":
                if (sanctionedLoad > 50) {
                    return approvalHierarchyRepo.findBySanctionedLoadAndSE(50);
                }
                break;
            case "CE":
                if (sanctionedLoad > 100) {
                    return approvalHierarchyRepo.findBySanctionedLoadAndCE(100);
                }
                break;
            case "MD":
                if (sanctionedLoad > 500) {
                    return approvalHierarchyRepo.findBySanctionedLoadAndMD(500);
                }
                break;
            case "KPTCL":
                if (sanctionedLoad > 500) {
                    return approvalHierarchyRepo.findBySanctionedLoadAndKPTCL(500);
                }
                break;
            default:
                logger.warning("Unsupported column name: " + columnName);
                break;
        }
        return new ArrayList<>(); // Return an empty list if no matching conditions found
    }

    private Map<String, Object> buildResponseMap(List<approval_hierarchy> approvalHierarchyList, String columnName, double sanctionedLoad) {
        List<Map<String, Object>> hierarchyDetails = new ArrayList<>();

        for (approval_hierarchy approvalHierarchy : approvalHierarchyList) {
            Map<String, Object> response = new HashMap<>();
            if (approvalHierarchy != null) {
                switch (columnName.toUpperCase()) {
                    case "EE":
                        response.put("EE_URL", approvalHierarchy.getEE_URL());
                        response.put("EE_Remark", approvalHierarchy.getEE_Remark());
                        response.put("EE_DocType", approvalHierarchy.getEE_DocType());
                        response.put("Status", approvalHierarchy.getStatus());
                        break;
                    case "STD":
                        response.put("STD_URL", approvalHierarchy.getSTD_URL());
                        response.put("STD_status", approvalHierarchy.getSTD_status());
                        response.put("STD_DocType", approvalHierarchy.getSTD_DocType());
                        response.put("STD_Remark", approvalHierarchy.getSTD_Remark());
                        break;
                    case "SE":
                        response.put("SE_URL", approvalHierarchy.getSE_URL());
                        response.put("SE_Remark", approvalHierarchy.getSE_Remark());
                        response.put("SE_DocType", approvalHierarchy.getSE_DocType());
                        response.put("Status", approvalHierarchy.getStatus());
                        break;
                    case "CE":
                        response.put("CE_URL", approvalHierarchy.getCE_URL());
                        response.put("CE_Remark", approvalHierarchy.getCE_Remark());
                        response.put("CE_DocType", approvalHierarchy.getCE_DocType());
                        response.put("Status", approvalHierarchy.getStatus());
                        break;
                    case "MD":
                        response.put("MD_URL", approvalHierarchy.getMD_URL());
                        response.put("MD_Remark", approvalHierarchy.getMD_Remark());
                        response.put("MD_DocType", approvalHierarchy.getMD_DocType());
                        response.put("Status", approvalHierarchy.getStatus());
                        break;
                    case "KPTCL":
                        response.put("KPTCL_URL", approvalHierarchy.getKPTCL_URL());
                        response.put("KPTCL_status", approvalHierarchy.getKPTCL_status());
                        response.put("KPTCL_Remark", approvalHierarchy.getKPTCL_Remark());
                        response.put("KPTCL_DocType", approvalHierarchy.getKPTCL_DocType());
                        break;
                    default:
                        logger.warning("Unsupported column name: " + columnName);
                        break;
                }

                // Add common fields
                response.put("sanctionedLoad", approvalHierarchy.getSanctionedLoad());
                response.put("category", approvalHierarchy.getCategory());
                response.put("UPDATED_BY", approvalHierarchy.getUPDATED_BY());
                response.put("UPDATED_ON", DateUtil.formatDate(approvalHierarchy.getUpdatedOn().getTime()));
                response.put("caseId", approvalHierarchy.getHierarchy().getCase_id());

                hierarchyDetails.add(response);
            }
        }

        Map<String, Object> finalResponse = new HashMap<>();
        if (hierarchyDetails.isEmpty()) {
            finalResponse.put("message", "Approval Hierarchy table is empty");
        } else {
            finalResponse.put("approvalHierarchyDetails", hierarchyDetails);
        }
        return finalResponse;
    }
}
