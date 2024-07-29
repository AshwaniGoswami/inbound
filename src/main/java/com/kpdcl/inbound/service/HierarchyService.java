//package com.kpdcl.inbound.service;
//
//import com.kpdcl.inbound.entity.hierarchy;
//import com.kpdcl.inbound.repository.hierarchyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;
//
//@Service
//public class HierarchyService {
//
//    private static final Logger logger = Logger.getLogger(HierarchyService.class.getName());
//
//    @Autowired
//    private hierarchyRepository hierarchyRepository;
//
//    public Map<String, Object> getHierarchyData(String sanctionedLoadStr) {
//        int sanctionedLoad;
//        try {
//            sanctionedLoad = Integer.parseInt(sanctionedLoadStr.trim());
//        } catch (NumberFormatException e) {
//            logger.severe("Invalid sanctioned load value: " + sanctionedLoadStr);
//            throw new IllegalArgumentException("Sanctioned load must be a valid number");
//        }
//
//        List<hierarchy> hierarchyData;
//        Map<String, Object> result = new HashMap<>();
//
//        if (sanctionedLoad >= 25 && sanctionedLoad < 50) {
//            hierarchyData = hierarchyRepository.findBySanctionedLoadRange(25, 50);
//            result.put("range", "25 to 50");
//        } else if (sanctionedLoad >= 50 && sanctionedLoad < 100) {
//            hierarchyData = hierarchyRepository.findBySanctionedLoadRange(50, 100);
//            result.put("range", "50 to 100");
//        } else if (sanctionedLoad >= 100 && sanctionedLoad < 500) {
//            hierarchyData = hierarchyRepository.findBySanctionedLoadRange(100, 500);
//            result.put("range", "100 to 500");
//        } else if (sanctionedLoad >= 500) {
//            hierarchyData = hierarchyRepository.findBySanctionedLoadGreaterThanEqual(500);
//            result.put("range", "greater than 500");
//        } else {
//            hierarchyData = List.of();
//            result.put("range", "below 25");
//        }
//
//        result.put("hierarchyData", hierarchyData);
//        return result;
//    }
//}

//package com.kpdcl.inbound.service;
//
//import com.kpdcl.inbound.entity.hierarchy;
//import com.kpdcl.inbound.repository.hierarchyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;
//
//@Service
//public class HierarchyService {
//
//    private static final Logger logger = Logger.getLogger(HierarchyService.class.getName());
//
//    @Autowired
//    private hierarchyRepository hierarchyRepository;
//
//    public Map<String, Object> getHierarchyData(String columnName, String code, String subDivision, String sanctionedLoadStr) {
//        int sanctionedLoad;
//        try {
//            sanctionedLoad = Integer.parseInt(sanctionedLoadStr.trim());
//        } catch (NumberFormatException e) {
//            logger.severe("Invalid sanctioned load value: " + sanctionedLoadStr);
//            throw new IllegalArgumentException("Sanctioned load must be a valid number");
//        }
////System.out.println(columnName);
////System.out.println(code);
////System.out.println(subDivision);
////System.out.println(columnName);
//        List<hierarchy> hierarchyData;
//        Map<String, Object> result = new HashMap<>();
//
//        switch (columnName.toUpperCase()) {
//            case "DIVISION_CODE":
//                hierarchyData = fetchHierarchyByDivisionCode(code,subDivision, sanctionedLoad);
//                break;
//            case "STD_CODE":
//                hierarchyData = fetchHierarchyByStdCode(code,subDivision, sanctionedLoad);
//                break;
//            case "WING_CODE":
//                hierarchyData = fetchHierarchyByWingCode(code,subDivision, sanctionedLoad);
//                break;
//            case "CIRCLE_CODE":
//                hierarchyData = fetchHierarchyByCircleCode(code,subDivision, sanctionedLoad);
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid column name");
//        }
//
//        result.put("columnName", columnName);
//        result.put("code", code);
//        result.put("sanctionedLoad", sanctionedLoad);
//        result.put("hierarchyData", hierarchyData);
//        return result;
//    }
//
//    private List<hierarchy> fetchHierarchyByDivisionCode(String divisionCode,String subDivision, int sanctionedLoad) {
//        if (sanctionedLoad >= 25 && sanctionedLoad < 50) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadRange(divisionCode,subDivision, 25, 50);
//        } else if (sanctionedLoad >= 50 && sanctionedLoad < 100) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadRange(divisionCode,subDivision, 50, 100);
//        } else if (sanctionedLoad >= 100 && sanctionedLoad < 500) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadRange(divisionCode,subDivision, 100, 500);
//        } else if (sanctionedLoad >= 500) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadGreaterThanEqual(divisionCode,subDivision, 500);
//        } else {
//            return List.of();
//        }
//    }

//package com.kpdcl.inbound.service;
//
//import com.kpdcl.inbound.entity.hierarchy;
//import com.kpdcl.inbound.repository.hierarchyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
////import java.text.SimpleDateFormat;
////import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Service
//public class HierarchyService {
//
//    private static final Logger logger = Logger.getLogger(HierarchyService.class.getName());
//
//    @Autowired
//    private hierarchyRepository hierarchyRepository;
//
//    public Map<String, Object> getHierarchyData(String columnName, String code, String subDivision, String sanctionedLoadStr) {
//        int sanctionedLoad;
//        try {
//            sanctionedLoad = Integer.parseInt(sanctionedLoadStr.trim());
//        } catch (NumberFormatException e) {
//            logger.severe("Invalid sanctioned load value: " + sanctionedLoadStr);
//            throw new IllegalArgumentException("Sanctioned load must be a valid number");
//        }
//
//        List<hierarchy> hierarchyData;
//        Map<String, Object> result = new HashMap<>();
//
//        switch (columnName.toUpperCase()) {
//            case "DIVISION_CODE":
//                hierarchyData = fetchHierarchyByDivisionCode(code, subDivision, sanctionedLoad);
//                break;
//            case "STD_CODE":
//                hierarchyData = fetchHierarchyByStdCode(code, subDivision, sanctionedLoad);
//                break;
//            case "WING_CODE":
//                hierarchyData = fetchHierarchyByWingCode(code, subDivision, sanctionedLoad);
//                break;
//            case "CIRCLE_CODE":
//                hierarchyData = fetchHierarchyByCircleCode(code, subDivision, sanctionedLoad);
//                break;
//            case "MD":
//                hierarchyData = fetchHierarchyByMD(subDivision, sanctionedLoad);
//                break;
//            case "KPTCL":
//                hierarchyData = fetchHierarchyByKPTCL(subDivision, sanctionedLoad);
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid column name");
//        }
//
//        List<Map<String, Object>> formattedData = hierarchyData.stream()
//                .map(this::formatHierarchy)
//                .collect(Collectors.toList());
//
//        result.put("hierarchy", formattedData);
//        return result;
//    }
//
//    private Map<String, Object> formatHierarchy(hierarchy hierarchy) {
//        Map<String, Object> formatted = new HashMap<>();
//        formatted.put("case_id", hierarchy.getCase_id());
//        formatted.put("user1", hierarchy.getUser1());
//        formatted.put("sanctionedLoad", hierarchy.getSanctionedLoad());
//        formatted.put("category", hierarchy.getCategory());
//        formatted.put("subDivision", hierarchy.getSubDivision());
//        formatted.put("createDate", DateUtil.formatDate(hierarchy.getCreateDate().getTime()));  // Convert Date to long
//        return formatted;
//    }
//
//    private List<hierarchy> fetchHierarchyByDivisionCode(String divisionCode, String subDivision, int sanctionedLoad) {
//        if (sanctionedLoad > 25 && sanctionedLoad <= 50) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadRange(divisionCode, subDivision, 25, 50);
//        } else if (sanctionedLoad > 50 && sanctionedLoad <=100) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadRange(divisionCode, subDivision, 50, 100);
//        } else if (sanctionedLoad > 100 && sanctionedLoad <= 500) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadRange(divisionCode, subDivision, 100, 500);
//        } else if (sanctionedLoad > 500) {
//            return hierarchyRepository.findByDivisionCodeAndSanctionedLoadGreaterThanEqual(divisionCode, subDivision, 500);
//        } else {
//            return List.of();
//        }
//    }
//
//    private List<hierarchy> fetchHierarchyByStdCode(String stdCode,String subDivision, int sanctionedLoad) {
//        // Implement similar logic as fetchHierarchyByDivisionCode for STD_CODE
//    	 if (sanctionedLoad > 25 && sanctionedLoad <= 50) {
//             return hierarchyRepository.findBySTDCodeAndSanctionedLoadRange(stdCode,subDivision, 25, 50);
//         } else if (sanctionedLoad > 50 && sanctionedLoad <= 100) {
//             return hierarchyRepository.findBySTDCodeAndSanctionedLoadRange(stdCode,subDivision, 50, 100);
//         } else if (sanctionedLoad > 100 && sanctionedLoad <= 500) {
//             return hierarchyRepository.findBySTDCodeAndSanctionedLoadRange(stdCode,subDivision, 100, 500);
//         } else if (sanctionedLoad > 500) {
//             return hierarchyRepository.findBySTDCodeAndSanctionedLoadGreaterThanEqual(stdCode,subDivision, 500);
//         }
//         else {
//        return List.of();
//         }
//    }
//
//    private List<hierarchy> fetchHierarchyByWingCode(String wingCode,String subDivision, int sanctionedLoad) {
//        // Implement similar logic as fetchHierarchyByDivisionCode for WING_CODE
//   	 if (sanctionedLoad > 25 && sanctionedLoad <= 50) {
//         return hierarchyRepository.findByWingCodeAndSanctionedLoadRange(wingCode,subDivision, 25, 50);
//     } else if (sanctionedLoad > 50 && sanctionedLoad <= 100) {
//         return hierarchyRepository.findByWingCodeAndSanctionedLoadRange(wingCode,subDivision, 50, 100);
//     } else if (sanctionedLoad > 100 && sanctionedLoad <= 500) {
//         return hierarchyRepository.findByWingCodeAndSanctionedLoadRange(wingCode,subDivision, 100, 500);
//     } else if (sanctionedLoad > 500) {
//         return hierarchyRepository.findByWingCodeAndSanctionedLoadGreaterThanEqual(wingCode,subDivision, 500);
//     }
//     else {
//    return List.of();
//     }
//    }
//
//    
//    private List<hierarchy> fetchHierarchyByCircleCode(String circleCode,String subDivision, int sanctionedLoad) {
//        // Implement similar logic as fetchHierarchyByDivisionCode for CIRCLE_CODE
//     	 if (sanctionedLoad > 25 && sanctionedLoad <= 50) {
//             return hierarchyRepository.findBycircleCodeAndSanctionedLoadRange(circleCode,subDivision, 25, 50);
//         } else if (sanctionedLoad > 50 && sanctionedLoad <= 100) {
//             return hierarchyRepository.findBycircleCodeAndSanctionedLoadRange(circleCode,subDivision, 50, 100);
//         } else if (sanctionedLoad > 100 && sanctionedLoad <= 500) {
//             return hierarchyRepository.findBycircleCodeAndSanctionedLoadRange(circleCode,subDivision, 100, 500);
//         } else if (sanctionedLoad > 500) {
//             return hierarchyRepository.findBycirlceCodeAndSanctionedLoadGreaterThanEqual(circleCode,subDivision, 500);
//         }
//         else {
//        return List.of();
//         }
//    }
//    
//    private List<hierarchy> fetchHierarchyByMD(String subDivision, int sanctionedLoad) {
//        // Implement similar logic as fetchHierarchyByDivisionCode for CIRCLE_CODE
//     	
//        if (sanctionedLoad > 500) {
//             return hierarchyRepository.findByMDAndKPTCLANDSanctionedLoadGreaterThanEqual(subDivision, 500);
//         }
//         else {
//        return List.of();
//         }
//    }
//    
//    private List<hierarchy> fetchHierarchyByKPTCL(String subDivision, int sanctionedLoad) {
//        // Implement similar logic as fetchHierarchyByDivisionCode for CIRCLE_CODE
//     	
//        if (sanctionedLoad > 500) {
//             return hierarchyRepository.findByMDAndKPTCLANDSanctionedLoadGreaterThanEqual(subDivision, 500);
//         }
//         else {
//        return List.of();
//         }
//    }
//}
//============================================================================
//    private String convertTimestampToDate(long timestamp) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(timestamp);
//        return sdf.format(date);
//    }
//}
//



package com.kpdcl.inbound.service;

import com.kpdcl.inbound.entity.hierarchy;
import com.kpdcl.inbound.repository.hierarchyRepository;
//import com.kpdcl.inbound.service.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class HierarchyService {

    private static final Logger logger = Logger.getLogger(HierarchyService.class.getName());

    @Autowired
    private hierarchyRepository hierarchyRepository;

    public Map<String, Object> getHierarchyData(String columnName, String code, String subDivision, String sanctionedLoadStr) {
        double sanctionedLoad;
        try {
            sanctionedLoad = Double.parseDouble(sanctionedLoadStr.trim());
        } catch (NumberFormatException e) {
            logger.severe("Invalid sanctioned load value: " + sanctionedLoadStr);
            throw new IllegalArgumentException("Sanctioned load must be a valid number");
        }

        List<hierarchy> hierarchyData = fetchHierarchy(columnName, code, subDivision, sanctionedLoad);

        List<Map<String, Object>> formattedData = hierarchyData.stream()
                .map(this::formatHierarchy)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("hierarchy", formattedData);
        return result;
    }

    private List<hierarchy> fetchHierarchy(String columnName, String code, String subDivision, double sanctionedLoad) {
        List<hierarchy> hierarchyData = new ArrayList<>();

        switch (columnName.toUpperCase()) {
            case "DIVISION_CODE":
                if (sanctionedLoad > 25) {
                    hierarchyData.addAll(hierarchyRepository.findByDivisionCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 25));
                }
                break;
            case "STD_CODE":
                if (sanctionedLoad > 25) {
                    hierarchyData.addAll(hierarchyRepository.findBySTDCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 25));
                }
                break;
            case "CIRCLE_CODE":
                if (sanctionedLoad > 50) {
                    hierarchyData.addAll(hierarchyRepository.findByCircleCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 50));
                }
                break;
            case "WING_CODE":
                if (sanctionedLoad > 100) {
                    hierarchyData.addAll(hierarchyRepository.findByWingCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 100));
                }
                break;
            case "MD_CODE":
            case "KPTCL_CODE":
                if (sanctionedLoad > 500) {
                    hierarchyData.addAll(hierarchyRepository.findByMDAndKPTCLANDSanctionedLoadGreaterThanEqual(subDivision, 500));
                }
                break;
            default:
                logger.warning("Invalid column name: " + columnName);
                break;
        }

        return hierarchyData;
    }

    private Map<String, Object> formatHierarchy(hierarchy hierarchy) {
        Map<String, Object> formatted = new HashMap<>();
        formatted.put("case_id", hierarchy.getCase_id());
        formatted.put("user1", hierarchy.getUser1());
        formatted.put("sanctionedLoad", hierarchy.getSanctionedLoad());
        formatted.put("category", hierarchy.getCategory());
        formatted.put("subDivision", hierarchy.getSubDivision());
        formatted.put("createDate", DateUtil.formatDate(hierarchy.getCreateDate().getTime()));
        return formatted;
    }
}

//===========================================================================================================================
//package com.kpdcl.inbound.service;
//
//import com.kpdcl.inbound.entity.approval_hierarchy;
//import com.kpdcl.inbound.entity.hierarchy;
//import com.kpdcl.inbound.repository.hierarchyRepository;
//import com.kpdcl.inbound.service.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
////import java.security.Timestamp;
//import java.sql.Timestamp;
//import java.util.*;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Service
//public class HierarchyService {
//
//    private static final Logger logger = Logger.getLogger(HierarchyService.class.getName());
//
//    @Autowired
//    private hierarchyRepository hierarchyRepository;
//
//    public Map<String, Object> getHierarchyData(String columnName, String code, String subDivision, String sanctionedLoadStr) {
//        int sanctionedLoad;
//        try {
//            sanctionedLoad = Integer.parseInt(sanctionedLoadStr.trim());
//        } catch (NumberFormatException e) {
//            logger.severe("Invalid sanctioned load value: " + sanctionedLoadStr);
//            throw new IllegalArgumentException("Sanctioned load must be a valid number");
//        }
//
//        List<hierarchy> hierarchyData = fetchHierarchy(columnName, code, subDivision, sanctionedLoad);
//        List<Long> caseIds = hierarchyData.stream().map(h -> h.getCase_id()).collect(Collectors.toList());
//
//        List<Object[]> approvalHierarchiesData = hierarchyRepository.findApprovalHierarchyByCaseIds(caseIds);
//        List<approval_hierarchy> approvalHierarchies = approvalHierarchiesData.stream()
//                .map(this::convertToApprovalHierarchy)
//                .collect(Collectors.toList());
//
//        List<Map<String, Object>> formattedData = hierarchyData.stream()
//                .map(h -> formatHierarchy(h, approvalHierarchies, columnName))
//                .collect(Collectors.toList());
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("hierarchy", formattedData);
//        return result;
//    }
//
//    private approval_hierarchy convertToApprovalHierarchy(Object[] data) {
//        approval_hierarchy approvalHierarchy = new approval_hierarchy();
//        approvalHierarchy.setId(((BigDecimal) data[0]).longValue());
//        approvalHierarchy.setEE_URL((String) data[1]);
//        approvalHierarchy.setEE_Remark((String) data[2]);
//        approvalHierarchy.setSTD_URL((String) data[3]);
//        approvalHierarchy.setSTD_status((String) data[4]);
//        approvalHierarchy.setSTD_Remark((String) data[5]);
//        approvalHierarchy.setSE_URL((String) data[6]);
//        approvalHierarchy.setSE_Remark((String) data[7]);
//        approvalHierarchy.setCE_URL((String) data[8]);
//        approvalHierarchy.setCE_Remark((String) data[9]);
//        approvalHierarchy.setKPTCL_URL((String) data[10]);
//        approvalHierarchy.setKPTCL_status((String) data[11]);
//        approvalHierarchy.setKPTCL_Remark((String) data[12]);
//        approvalHierarchy.setMD_URL((String) data[13]);
//        approvalHierarchy.setMD_Remark((String) data[14]);
//        approvalHierarchy.setCategory((String) data[15]);
//        approvalHierarchy.setSanctionedLoad((String) data[16]);
//        approvalHierarchy.setUPDATED_BY((String) data[17]);
//        approvalHierarchy.setStatus((String) data[18]);
//
//        // Handle Timestamp conversion properly
//        //Object timestampObject = data[19];
//        //System.out.println("Timestamp object class: " + timestampObject.getClass());
////        if (timestampObject instanceof Timestamp) {
////            Timestamp timestamp = (Timestamp) timestampObject;
////            approvalHierarchy.setUpdatedOn(new Date(timestamp.getTime()));
////        } else {
////            // Handle unexpected case or log an error
////            System.err.println("Unexpected type for updatedOnTimestamp: " + timestampObject.getClass());
////        }
//
//        hierarchy hierarchyEntity = new hierarchy();
//        hierarchyEntity.setCase_id(((BigDecimal) data[19]).longValue());
//        approvalHierarchy.setHierarchy(hierarchyEntity);
//
//        return approvalHierarchy;
//    }
//
//
//
//
//    private List<hierarchy> fetchHierarchy(String columnName, String code, String subDivision, int sanctionedLoad) {
//        List<hierarchy> hierarchyData = new ArrayList<>();
//
//        switch (columnName.toUpperCase()) {
//            case "DIVISION_CODE":
//                if (sanctionedLoad > 25) {
//                    hierarchyData.addAll(hierarchyRepository.findByDivisionCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 25));
//                }
//                break;
//            case "STD_CODE":
//                if (sanctionedLoad > 25) {
//                    hierarchyData.addAll(hierarchyRepository.findBySTDCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 25));
//                }
//                break;
//            case "CIRCLE_CODE":
//                if (sanctionedLoad > 50) {
//                    hierarchyData.addAll(hierarchyRepository.findByCircleCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 50));
//                }
//                break;
//            case "WING_CODE":
//                if (sanctionedLoad > 100) {
//                    hierarchyData.addAll(hierarchyRepository.findByWingCodeAndSanctionedLoadGreaterThanEqual(code, subDivision, 100));
//                }
//                break;
//            case "MD":
//            case "KPTCL":
//                if (sanctionedLoad > 500) {
//                    hierarchyData.addAll(hierarchyRepository.findByMDAndKPTCLANDSanctionedLoadGreaterThanEqual(subDivision, 500));
//                }
//                break;
//            default:
//                logger.warning("Invalid column name: " + columnName);
//                break;
//        }
//
//        return hierarchyData;
//    }
//
//    private Map<String, Object> formatHierarchy(hierarchy hierarchy, List<approval_hierarchy> approvalHierarchies, String columnName) {
//        Map<String, Object> formatted = new HashMap<>();
//        formatted.put("case_id", hierarchy.getCase_id());
//        formatted.put("user1", hierarchy.getUser1());
//        formatted.put("sanctionedLoad", hierarchy.getSanctionedLoad());
//        formatted.put("category", hierarchy.getCategory());
//        formatted.put("subDivision", hierarchy.getSubDivision());
//        formatted.put("createDate", DateUtil.formatDate(hierarchy.getCreateDate().getTime()));
//
//        Optional<approval_hierarchy> approvalHierarchyOpt = approvalHierarchies.stream()
//                .filter(ah -> ah.getHierarchy().getCase_id().equals(hierarchy.getCase_id()))
//                .findFirst();
//
//        if (approvalHierarchyOpt.isPresent()) {
//            approval_hierarchy approvalHierarchy = approvalHierarchyOpt.get();
//            switch (columnName.toUpperCase()) {
//                case "EE":
//                    formatted.put("url", approvalHierarchy.getEE_URL());
//                    formatted.put("remark", approvalHierarchy.getEE_Remark());
//                    formatted.put("status", approvalHierarchy.getStatus());
//                    break;
//                case "SE":
//                    formatted.put("url", approvalHierarchy.getSE_URL());
//                    formatted.put("remark", approvalHierarchy.getSE_Remark());
//                    formatted.put("status", approvalHierarchy.getStatus());
//                    break;
//                case "CE":
//                    formatted.put("url", approvalHierarchy.getCE_URL());
//                    formatted.put("remark", approvalHierarchy.getCE_Remark());
//                    formatted.put("status", approvalHierarchy.getStatus());
//                    break;
//                case "MD":
//                    formatted.put("url", approvalHierarchy.getMD_URL());
//                    formatted.put("remark", approvalHierarchy.getMD_Remark());
//                    formatted.put("status", approvalHierarchy.getStatus());
//                    break;
//                case "STD":
//                    formatted.put("url", approvalHierarchy.getSTD_URL());
//                    formatted.put("remark", approvalHierarchy.getSTD_Remark());
//                    formatted.put("status", approvalHierarchy.getSTD_status());
//                    break;
//                case "KPTCL":
//                    formatted.put("url", approvalHierarchy.getKPTCL_URL());
//                    formatted.put("remark", approvalHierarchy.getKPTCL_Remark());
//                    formatted.put("status", approvalHierarchy.getKPTCL_status());
//                    break;
//            }
//        }
//
//        return formatted;
//    }
//}
