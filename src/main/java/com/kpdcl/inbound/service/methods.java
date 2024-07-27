package com.kpdcl.inbound.service;

import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.hierarchy;
import com.kpdcl.inbound.repository.approvalHierarchyRepository;
import com.kpdcl.inbound.repository.dataRepository;

@Service
public class methods {
	
	@Autowired
	private dataRepository dataRepo;
	
	@Autowired
	private approvalHierarchyRepository approvalHierarchyRepo;

//	public void updateExistingHierarchy(approval_hierarchy existingHierarchy, approval_hierarchy newHierarchy) {
//        // Update EE_URL, EE_status, and EE_Remark if provided
//        if (newHierarchy.getEE_URL() != null || newHierarchy.isEE_status() || newHierarchy.getEE_Remark() != null) {
//            existingHierarchy.setEE_URL(newHierarchy.getEE_URL());
//            existingHierarchy.setEE_status(newHierarchy.isEE_status());
//            existingHierarchy.setEE_Remark(newHierarchy.getEE_Remark());
//        }
//        // Update STD_URL, STD_status, and STD_Remark if provided
//        if (newHierarchy.getSTD_URL() != null && newHierarchy.isSTD_status() || newHierarchy.getSTD_Remark() != null) {
//            existingHierarchy.setSTD_URL(newHierarchy.getSTD_URL());
//            existingHierarchy.setSTD_status(newHierarchy.isSTD_status());
//            existingHierarchy.setSTD_Remark(newHierarchy.getSTD_Remark());
//        }
//        // Update SE_URL, SE_status, and SE_Remark if provided
//        if (newHierarchy.getSE_URL() != null && newHierarchy.isSE_status() || newHierarchy.getSE_Remark() != null) {
//            existingHierarchy.setSE_URL(newHierarchy.getSE_URL());
//    existingHierarchy.setSE_status(newHierarchy.isSE_status());
//            existingHierarchy.setSE_Remark(newHierarchy.getSE_Remark());
//        }
//        // Update CE_URL, CE_status, and CE_Remark if provided
//        if (newHierarchy.getCE_URL() != null || newHierarchy.isCE_status() || newHierarchy.getCE_Remark() != null) {
//            existingHierarchy.setCE_URL(newHierarchy.getCE_URL());
//            existingHierarchy.setCE_status(newHierarchy.isCE_status());
//            existingHierarchy.setCE_Remark(newHierarchy.getCE_Remark());
//        }
//        // Update KPTCL_URL, KPTCL_status, and KPTCL_Remark if provided
//        if (newHierarchy.getKPTCL_URL() != null || newHierarchy.isKPTCL_status() || newHierarchy.getKPTCL_Remark() != null) {
//            existingHierarchy.setKPTCL_URL(newHierarchy.getKPTCL_URL());
//            existingHierarchy.setKPTCL_status(newHierarchy.isKPTCL_status());
//            existingHierarchy.setKPTCL_Remark(newHierarchy.getKPTCL_Remark());
//        }
//        // Update MD_URL, MD_status, and MD_Remark if provided
//        if (newHierarchy.getMD_URL() != null || newHierarchy.isMD_status() || newHierarchy.getMD_Remark() != null) {
//            existingHierarchy.setMD_URL(newHierarchy.getMD_URL());
//            existingHierarchy.setMD_status(newHierarchy.isMD_status());
//            existingHierarchy.setMD_Remark(newHierarchy.getMD_Remark());
//        }
//    }
	
//	public void updateExistingHierarchy(approval_hierarchy existingHierarchy, approval_hierarchy newHierarchy) {
//        // Update EE_URL, EE_status, and EE_Remark if provided
//        if (newHierarchy.getEE_URL() != null  || newHierarchy.getEE_Remark() != null) {
//            existingHierarchy.setEE_URL(newHierarchy.getEE_URL());
//            //existingHierarchy.setEE_status(newHierarchy.isEE_status());
//            existingHierarchy.setEE_Remark(newHierarchy.getEE_Remark());
//        }
//        // Update STD_URL, STD_status, and STD_Remark if provided
//        if (newHierarchy.getSTD_URL() != null || newHierarchy.getSTD_status()!= null || newHierarchy.getSTD_Remark() != null) {
//            existingHierarchy.setSTD_URL(newHierarchy.getSTD_URL());
//           // existingHierarchy.setSTD_status(newHierarchy.isSTD_status());
//            existingHierarchy.setSTD_Remark(newHierarchy.getSTD_Remark());
//        }
//        // Update SE_URL, SE_status, and SE_Remark if provided
//        if (newHierarchy.getSE_URL() != null  || newHierarchy.getSE_Remark() != null) {
//            existingHierarchy.setSE_URL(newHierarchy.getSE_URL());
//   // existingHierarchy.setSE_status(newHierarchy.isSE_status());
//            existingHierarchy.setSE_Remark(newHierarchy.getSE_Remark());
//        }
//        // Update CE_URL, CE_status, and CE_Remark if provided
//        if (newHierarchy.getCE_URL() != null  || newHierarchy.getCE_Remark() != null) {
//            existingHierarchy.setCE_URL(newHierarchy.getCE_URL());
//            //existingHierarchy.setCE_status(newHierarchy.isCE_status());
//            existingHierarchy.setCE_Remark(newHierarchy.getCE_Remark());
//        }
//        // Update KPTCL_URL, KPTCL_status, and KPTCL_Remark if provided
//        if (newHierarchy.getKPTCL_URL() != null || newHierarchy.getKPTCL_status()!= null || newHierarchy.getKPTCL_Remark() != null) {
//            existingHierarchy.setKPTCL_URL(newHierarchy.getKPTCL_URL());
//            existingHierarchy.setKPTCL_status(newHierarchy.getKPTCL_status());
//            existingHierarchy.setKPTCL_Remark(newHierarchy.getKPTCL_Remark());
//        }
//        // Update MD_URL, MD_status, and MD_Remark if provided
//        if (newHierarchy.getMD_URL() != null  || newHierarchy.getMD_Remark() != null) {
//            existingHierarchy.setMD_URL(newHierarchy.getMD_URL());
//            //existingHierarchy.setMD_status(newHierarchy.isMD_status());
//            existingHierarchy.setMD_Remark(newHierarchy.getMD_Remark());
//        }
//    }
	
	public approval_hierarchy updateExistingHierarchy(approval_hierarchy hierarchy1,String ColumnName) {
		switch (ColumnName) {
        case "STD":
            hierarchy1.setSTD_status("not approved");
            hierarchy1.setSTD_Remark(hierarchy1.getSTD_Remark());
            hierarchy1.setSTD_URL(hierarchy1.getSTD_URL()); // Use the appropriate URL
            break;
        case "KPTCL":
            hierarchy1.setKPTCL_URL(hierarchy1.getKPTCL_URL()); // Use the appropriate URL
            hierarchy1.setKPTCL_Remark(hierarchy1.getKPTCL_Remark());
            break;
        case "MD":
            hierarchy1.setMD_URL(hierarchy1.getMD_URL()); // Use the appropriate URL
            hierarchy1.setMD_Remark(hierarchy1.getMD_Remark());
            break;
        case "EE":
            hierarchy1.setEE_URL(hierarchy1.getEE_URL()); // Use the appropriate URL
            hierarchy1.setEE_Remark(hierarchy1.getEE_Remark());
            break;
        case "SE":
            hierarchy1.setSE_URL(hierarchy1.getSE_URL()); // Use the appropriate URL
            hierarchy1.setSE_Remark(hierarchy1.getSE_Remark());
            break;
        case "CE":
            hierarchy1.setCE_URL(hierarchy1.getCE_URL()); // Use the appropriate URL
            hierarchy1.setCE_Remark(hierarchy1.getCE_Remark());
            break;
        default:
              ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Invalid column name: " + ColumnName);
             
    }
		return hierarchy1;
	}
	 
	 
	 
	
	   // Method to check if circle code exists
    public boolean circleCodeExists(String circle_code) {
    	return dataRepo.existsBycircle_code(circle_code);
    }
    
    // Method to check if division code exists
    public boolean divisionCodeExists(String division_code) {
    	return dataRepo.existsByDivision_code(division_code);
    }
    public double extractNumericValue(String str) {
    	String numericPart = str.replaceAll("[^\\d.]", "");
    	return Double.parseDouble(numericPart);
    }
    
//=============================================POST APPROVAL HIERARCHY ======================================	
    public approval_hierarchy updateDynamicFields(approval_hierarchy hierarchy, Map<String, Object> hierarchyData, String columnName) {
    	switch (columnName) {
    	case "STD":
    		hierarchy.setSTD_URL((String) hierarchyData.get("STD_URL"));
    		hierarchy.setSTD_status((String) hierarchyData.get("STD_status"));
    		hierarchy.setSTD_Remark((String) hierarchyData.get("STD_Remark"));
    		hierarchy.setSTD_DocType((String) hierarchyData.get("STD_DocType"));
    		break;
    	case "KPTCL":
    		hierarchy.setKPTCL_URL((String)hierarchyData.get("KPTCL_URL")); // Use the appropriate URL
    		hierarchy.setKPTCL_status((String) hierarchyData.get("KPTCL_status"));
    		hierarchy.setKPTCL_Remark((String)hierarchyData.get("KPTCL_Remark"));
    		hierarchy.setKPTCL_DocType((String)hierarchyData.get("KPTCL_DocType"));
    		break;
    	case "MD":
    		hierarchy.setMD_URL((String)hierarchyData.get("MD_URL")); // Use the appropriate URL
    		hierarchy.setMD_Remark((String)hierarchyData.get("MD_Remark"));
    		hierarchy.setMD_DocType((String)hierarchyData.get("MD_DocType"));
    		break;
    	case "EE":
    		hierarchy.setEE_URL((String)hierarchyData.get("EE_URL")); // Use the appropriate URL
    		hierarchy.setEE_Remark((String)hierarchyData.get("EE_Remark"));
    		hierarchy.setEE_DocType((String)hierarchyData.get("EE_DocType"));
    		break;
    	case "SE":
    		hierarchy.setSE_URL((String)hierarchyData.get("SE_URL")); // Use the appropriate URL
    		hierarchy.setSE_Remark((String)hierarchyData.get("SE_Remark"));
    		hierarchy.setSE_DocType((String)hierarchyData.get("SE_DocType"));
    		break;
    	case "CE":
    		hierarchy.setCE_URL((String)hierarchyData.get("CE_URL")); // Use the appropriate URL
    		hierarchy.setCE_Remark((String)hierarchyData.get("CE_Remark"));
    		hierarchy.setCE_DocType((String)hierarchyData.get("CE_DocType"));
    		break;
    	default:
    		throw new IllegalArgumentException("Unsupported column name: " + columnName);
    	}
    	return hierarchy;
    }
    
    
    
//    public ResponseEntity<String> updateApprovalHierarchy(approval_hierarchy existingApprovalHierarchy, double sanctionedLoadValue, String columnName, Map<String, Object> hierarchyData, String updatedBy, String status) {
//  if((status==null || !status.equals(columnName+" "+"rejected")) && !Arrays.asList("EE rejected", "SE rejected", "CE rejected", "MD rejected").contains(existingApprovalHierarchy.getStatus())) {
//        if (!canApprove(sanctionedLoadValue, columnName)) {
//            return ResponseEntity.status(HttpStatus.SC_CREATED).body("They are not able to approve this");
//        }
//        
//        
//            
//        	
//        
//     
//        String approvalHierarchyStatus = existingApprovalHierarchy.getStatus();
//        String newStatus = approvalHierarchyRepo.findNewStatusByCurrentStatus(approvalHierarchyStatus);
//        if (status != null && !status.equals(newStatus)) {
//            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                    .body("you cannot approve this until previous authority approves");
//        }
//        
//        
//
//        updateDynamicFields(existingApprovalHierarchy, hierarchyData, columnName);
//        existingApprovalHierarchy.setUPDATED_BY(updatedBy);
//        existingApprovalHierarchy.setUpdatedOn(new Date());
//
//        if (status != null && !"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
//            existingApprovalHierarchy.setStatus(status);
//        }
//
//        approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(existingApprovalHierarchy);
//        
//
//        if (isFinallyApproved(sanctionedLoadValue, savedApprovalHierarchy)) {
//        	existingApprovalHierarchy.setStatus("Finally Approved");
//        	approval_hierarchy savedApprovalHierarchyStatus = approvalHierarchyRepo.save(existingApprovalHierarchy);
//            return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//        }
//        
//
//        return ResponseEntity.ok("Approval Hierarchy updated successfully");
//        
//  }
//    else if(!Arrays.asList("EE rejected", "SE rejected", "CE rejected", "MD rejected").contains(existingApprovalHierarchy.getStatus())) {
//    	String rejected=columnName+" "+"rejected";
//    	existingApprovalHierarchy.setStatus(rejected);
//    	updateDynamicFields(existingApprovalHierarchy, hierarchyData, columnName);
//        existingApprovalHierarchy.setUPDATED_BY(updatedBy);
//        existingApprovalHierarchy.setUpdatedOn(new Date());
//        
//        if (status != null && !"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
//            existingApprovalHierarchy.setStatus(status);
//        }
//
//        approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(existingApprovalHierarchy);
//        return ResponseEntity.status(HttpStatus.SC_CREATED)
//                .body("updated successfully");
//    }
//  
//    else {
//    	return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                .body("You are not able to approve because previous authority rejected");
//    }
//}
//
//    public ResponseEntity<String> createApprovalHierarchy(hierarchy existingHierarchy, String sanctionedLoad, String category, String columnName, Map<String, Object> hierarchyData, String updatedBy, String status, double sanctionedLoadValue) {
//        approval_hierarchy newHierarchy = new approval_hierarchy();
//        newHierarchy.setHierarchy(existingHierarchy);
//        newHierarchy.setSanctionedLoad(sanctionedLoad);
//        newHierarchy.setCategory(category);
//
//        setDefaultValues(newHierarchy, sanctionedLoadValue);
//        newHierarchy.setUpdatedOn(new Date());
//        newHierarchy.setUPDATED_BY(updatedBy);
//
//        if (status != null && !status.equals("EE rejected") &&  !status.equals("EE approved")) {
//            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                    .body("you cannot approve this until previous authority approves");
//        }
//        
//        if("EE rejected".equals(status)) {
//        	newHierarchy.setStatus(status);
//        }
//        
//       
//
//        updateDynamicFields(newHierarchy, hierarchyData, columnName);
//
//        if (!"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
//            newHierarchy.setStatus(status);
//        }
//
//        approvalHierarchyRepo.save(newHierarchy);
//        return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
//    }
//
    public boolean canApprove(double sanctionedLoadValue, String columnName) {
        if (sanctionedLoadValue > 25.0 && sanctionedLoadValue <= 50.0) {
            return !("SE".equals(columnName) || "CE".equals(columnName) || "MD".equals(columnName) || "KPTCL".equals(columnName));
        } else if (sanctionedLoadValue > 50.0 && sanctionedLoadValue <= 100.0) {
            return !("CE".equals(columnName) || "MD".equals(columnName) || "KPTCL".equals(columnName));
        } else if (sanctionedLoadValue > 100.0 && sanctionedLoadValue <= 500.0) {
            return !("MD".equals(columnName) || "KPTCL".equals(columnName));
        } else {
            return true;
        }
    }
//
    public boolean isFinallyApproved(double sanctionedLoadValue, approval_hierarchy savedApprovalHierarchy) {
        if (sanctionedLoadValue > 25.0 && sanctionedLoadValue <= 50.0) {
            return "approved".equals(savedApprovalHierarchy.getSTD_status()) && "EE approved".equals(savedApprovalHierarchy.getStatus());
        } else if (sanctionedLoadValue > 50.0 && sanctionedLoadValue <= 100.0) {
            return "approved".equals(savedApprovalHierarchy.getSTD_status()) && "SE approved".equals(savedApprovalHierarchy.getStatus());
        } else if (sanctionedLoadValue > 100.0 && sanctionedLoadValue <= 500.0) {
            return "approved".equals(savedApprovalHierarchy.getSTD_status()) && "CE approved".equals(savedApprovalHierarchy.getStatus());
        } else if (sanctionedLoadValue > 500.0) {
            return "approved".equals(savedApprovalHierarchy.getSTD_status()) && "MD approved".equals(savedApprovalHierarchy.getStatus()) && "approved".equals(savedApprovalHierarchy.getKPTCL_status());
        }
        return false;
    }
//
    public void setDefaultValues(approval_hierarchy newHierarchy, double sanctionedLoadValue) {
        if (sanctionedLoadValue <= 500) {
            newHierarchy.setKPTCL_status("null");
        } else {
            newHierarchy.setKPTCL_status("not approved");
        }
        newHierarchy.setSTD_status("not approved");
        newHierarchy.setStatus("AEE approved");
    }
    
    public ResponseEntity<String> updateApprovalHierarchy(approval_hierarchy existingApprovalHierarchy, double sanctionedLoadValue, String columnName, Map<String, Object> hierarchyData, String updatedBy, String status) {
        // Check if the current column is already approved
//        String columnStatusField = getColumnStatusField(columnName);
//        String currentStatus = getStatusField(existingApprovalHierarchy, columnStatusField);
//
//        if ((columnName + " approved").equals(currentStatus) || (columnName + " rejected").equals(currentStatus)) {
//            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                    .body("This approval has already been completed by " + columnName);
//        }

        if ((status == null || !status.equals(columnName + " rejected")) && !isRejected(existingApprovalHierarchy)) {
            if (!canApprove(sanctionedLoadValue, columnName)) {
                return ResponseEntity.status(HttpStatus.SC_CREATED).body("They are not able to approve this");
            }

            String stdStatus=(String) hierarchyData.get("STD_status");
	        String kptclStatus=(String)hierarchyData.get("KPTCL_status");
            String approvalHierarchyStatus = existingApprovalHierarchy.getStatus();
            String newStatus = approvalHierarchyRepo.findNewStatusByCurrentStatus(approvalHierarchyStatus);
            if (status != null && !status.equals(newStatus)) {
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                        .body("You cannot approve this until previous authority approves");
            }

            updateDynamicFields(existingApprovalHierarchy, hierarchyData, columnName);
            existingApprovalHierarchy.setUPDATED_BY(updatedBy);
            existingApprovalHierarchy.setUpdatedOn(new Date());
            existingApprovalHierarchy.setStatus(approvalHierarchyStatus);
            
//            if ("STD".equals(columnName) || "KPTCL".equals(columnName)) {
//                existingApprovalHierarchy.setStatus(status);
//            }
            System.out.println(status);

            if ("STD".equals(columnName)) {
                existingApprovalHierarchy.setSTD_status(stdStatus);
            } else if ("KPTCL".equals(columnName)) {
                existingApprovalHierarchy.setKPTCL_status(kptclStatus);
            } else {
                existingApprovalHierarchy.setStatus(status);
            }

            approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(existingApprovalHierarchy);
            System.out.println(savedApprovalHierarchy);
//            if (isFinallyApproved(sanctionedLoadValue, savedApprovalHierarchy)) {
//                existingApprovalHierarchy.setStatus("Finally Approved");
//                approvalHierarchyRepo.save(existingApprovalHierarchy);
//                return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//            }
            return ResponseEntity.ok("Approval Hierarchy updated successfully");
            //return ResponseEntity.ok("Approval Hierarchy updated successfully");
        } else if (!isRejected(existingApprovalHierarchy)) {
            String rejected = columnName + " rejected";
            existingApprovalHierarchy.setStatus(rejected);
            updateDynamicFields(existingApprovalHierarchy, hierarchyData, columnName);
            existingApprovalHierarchy.setUPDATED_BY(updatedBy);
            existingApprovalHierarchy.setUpdatedOn(new Date());

            if ("STD".equals(columnName) || "KPTCL".equals(columnName)) {
                existingApprovalHierarchy.setStatus(status);
            }
            if (status != null && !"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
                existingApprovalHierarchy.setStatus(status);
            } 
            else if ("STD".equals(columnName)) {
            	existingApprovalHierarchy.setSTD_status(status);
            } else if ("KPTCL".equals(columnName)) {
            	existingApprovalHierarchy.setKPTCL_status(status);
            }

            approvalHierarchyRepo.save(existingApprovalHierarchy);
            return ResponseEntity.status(HttpStatus.SC_CREATED)
                    .body("updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                    .body("You are not able to approve because previous authority rejected");
        }
    }
    
//    public ResponseEntity<String> updateApprovalHierarchy(approval_hierarchy existingApprovalHierarchy, double sanctionedLoadValue, String columnName, Map<String, Object> hierarchyData, String updatedBy, String status) {
//        // Check if the current column is already approved
//        String columnStatusField = getColumnStatusField(columnName);
//        String currentStatus = getStatusField(existingApprovalHierarchy, columnStatusField);
//
//        if ((columnName+ " "+"approved").equals(currentStatus) || (columnName+ " "+"rejected").equals(currentStatus)) {
//            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                    .body("This approval has already been completed by " + columnName);
//        }
//
//        if ((status == null || !status.equals(columnName + " rejected")) && !isRejected(existingApprovalHierarchy)) {
//            if (!canApprove(sanctionedLoadValue, columnName)) {
//                return ResponseEntity.status(HttpStatus.SC_CREATED).body("They are not able to approve this");
//            }
//
//            String approvalHierarchyStatus = existingApprovalHierarchy.getStatus();
//            String newStatus = approvalHierarchyRepo.findNewStatusByCurrentStatus(approvalHierarchyStatus);
//            if (status != null && !status.equals(newStatus)) {
//                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                        .body("You cannot approve this until previous authority approves");
//            }
//
//            updateDynamicFields(existingApprovalHierarchy, hierarchyData, columnName);
//            existingApprovalHierarchy.setUPDATED_BY(updatedBy);
//            existingApprovalHierarchy.setUpdatedOn(new Date());
//
//            if (status != null && !"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
//                existingApprovalHierarchy.setStatus(status);
//            }
//
//            approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(existingApprovalHierarchy);
//
//            if (isFinallyApproved(sanctionedLoadValue, savedApprovalHierarchy)) {
//                existingApprovalHierarchy.setStatus("Finally Approved");
//                 approvalHierarchyRepo.save(existingApprovalHierarchy);
//                return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//            }
//
//            return ResponseEntity.ok("Approval Hierarchy updated successfully");
//        } else if (!isRejected(existingApprovalHierarchy)) {
//            String rejected = columnName + " rejected";
//            existingApprovalHierarchy.setStatus(rejected);
//            updateDynamicFields(existingApprovalHierarchy, hierarchyData, columnName);
//            existingApprovalHierarchy.setUPDATED_BY(updatedBy);
//            existingApprovalHierarchy.setUpdatedOn(new Date());
//
//            if (status != null && !"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
//                existingApprovalHierarchy.setStatus(status);
//            }
//
//            approvalHierarchyRepo.save(existingApprovalHierarchy);
//            return ResponseEntity.status(HttpStatus.SC_CREATED)
//                    .body("updated successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                    .body("You are not able to approve because previous authority rejected");
//        }
//    }
    public String getColumnStatusField(String columnName) {
        switch (columnName.toUpperCase()) {
            case "EE": return "status";
            case "SE": return "status";
            case "CE": return "status";
            case "MD": return "status";
            case "KPTCL": return "KPTCL_status";
            case "STD": return "STD_status";
            default: throw new IllegalArgumentException("Unsupported column name: " + columnName);
        }
    }

    public String getStatusField(approval_hierarchy approvalHierarchy, String fieldName) {
        try {
            Field field = approvalHierarchy.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            System.out.println(field.get(approvalHierarchy));
            return (String) field.get(approvalHierarchy);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error accessing status field: " + fieldName, e);
        }
    }

    private boolean isRejected(approval_hierarchy approvalHierarchy) {
        String status = approvalHierarchy.getStatus();
        return Arrays.asList("EE rejected", "SE rejected", "CE rejected", "MD rejected").contains(status);
    }
    
    
 public ResponseEntity<String> createApprovalHierarchy(hierarchy existingHierarchy, String sanctionedLoad, String category, String columnName, Map<String, Object> hierarchyData, String updatedBy, String status, double sanctionedLoadValue) {
        approval_hierarchy newHierarchy = new approval_hierarchy();
        newHierarchy.setHierarchy(existingHierarchy);
        newHierarchy.setSanctionedLoad(sanctionedLoad);
        newHierarchy.setCategory(category);
        newHierarchy.setStatus("AEE approved");

        setDefaultValues(newHierarchy, sanctionedLoadValue);
        newHierarchy.setUpdatedOn(new Date());
        newHierarchy.setUPDATED_BY(updatedBy);
        

        if (status != null && !status.equals("EE rejected") && !status.equals("EE approved")) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                    .body("You cannot approve this until previous authority approves");
        }

        if ("EE rejected".equals(status)) {
            newHierarchy.setStatus(status);
        }

        if (!"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
        	newHierarchy.setStatus(status);
        }
        else if ("STD".equals(columnName)) {
        	newHierarchy.setSTD_status(status);
        } else if ("KPTCL".equals(columnName)) {
        	newHierarchy.setKPTCL_status(status);
        }
        updateDynamicFields(newHierarchy, hierarchyData, columnName);

        

        approvalHierarchyRepo.save(newHierarchy);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
    }

//    private String getPreviousColumn(String columnName) {
//        switch (columnName.toUpperCase()) {
//        case "EE": return "EE";
//        case "STD": return "STD";
//            case "SE": return "EE";
//            case "CE": return "SE";
//            case "MD": return "CE";
//            case "KPTCL": return "MD";
//            default: return null; // EE and STD have no previous authority
//        }
//    }

//    public boolean isMandotaryEngineerApproved(double sanctionedLoadValue, approval_hierarchy savedApprovalHierarchy) {
//        if ((sanctionedLoadValue >= 25.0 && sanctionedLoadValue < 50.0) || (sanctionedLoadValue >= 50.0 && sanctionedLoadValue < 100.0) || (sanctionedLoadValue >= 100.0 && sanctionedLoadValue < 500.0)) {
//            return "approved".equals(savedApprovalHierarchy.getSTD_status());
//       } else if (sanctionedLoadValue >= 500.0) {
//            return "approved".equals(savedApprovalHierarchy.getSTD_status()) && "MD approved".equals(savedApprovalHierarchy.getStatus()) && "approved".equals(savedApprovalHierarchy.getKPTCL_status());
//        }
//        return false;
//    }
    //======================GET APPROVAL HIERARCHY========================================
    public Map<String, Object> buildResponseMap(approval_hierarchy approvalHierarchy) {
        Map<String, Object> response = new HashMap<>();
        response.put("caseId", approvalHierarchy.getHierarchy().getCase_id());
        response.put("sanctionedLoad", approvalHierarchy.getSanctionedLoad());
        response.put("category", approvalHierarchy.getCategory());
        response.put("updatedBy", approvalHierarchy.getUPDATED_BY());
        response.put("createDate", DateUtil.formatDate(approvalHierarchy.getHierarchy().getCreateDate().getTime()));
        response.put("updatedOn", DateUtil.formatDate(approvalHierarchy.getUpdatedOn().getTime()));
        return response;
    }

    public void addColumnSpecificData(Map<String, Object> response, approval_hierarchy approvalHierarchy, String columnName) {
        switch (columnName) {
            case "STD":
                response.put("STD_status", approvalHierarchy.getSTD_status());
                response.put("STD_URL", approvalHierarchy.getSTD_URL());
                break;
            case "KPTCL":
                response.put("KPTCL_status", approvalHierarchy.getKPTCL_status());
                response.put("KPTCL_URL", approvalHierarchy.getKPTCL_URL());
                break;
            case "MD":
                response.put("MD_URL", approvalHierarchy.getMD_URL());
                break;
            case "EE":
                response.put("EE_URL", approvalHierarchy.getEE_URL());
                response.put("EE_Remark", approvalHierarchy.getEE_Remark());
                break;
            case "SE":
                response.put("SE_URL", approvalHierarchy.getSE_URL());
                response.put("SE_Remark", approvalHierarchy.getSE_Remark());
                break;
            case "CE":
                response.put("CE_URL", approvalHierarchy.getCE_URL());
                response.put("CE_Remark", approvalHierarchy.getCE_Remark());
                break;
            default:
                throw new IllegalArgumentException("Invalid column name: " + columnName);
        }

        if (!"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
            response.put("status", approvalHierarchy.getStatus());
        }
    }
}
