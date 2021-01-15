package com.lhzn.soft.project.services.impl;


import com.lhzn.soft.framework.aspectj.lang.MyAnnotation;
import com.lhzn.soft.framework.aspectj.lang.ValidateResult;
import com.lhzn.soft.framework.exception.CustomException;
import com.lhzn.soft.project.domain.*;
import com.lhzn.soft.project.mapper.*;
import com.lhzn.soft.project.services.BusinessService;
import com.lhzn.soft.project.services.BusinessSystemService;
import com.lhzn.soft.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 业务处理
 *
 * @author qishuai
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private GatherdataLogMapper glMapper;
    @Resource
    private ContaRecognitionMapper contaRecognitionMapper;
    @Resource
    private DrRfidMapper drRfidMapper;
    @Resource
    private ExtendMapper extendMapper;
    @Resource
    private FormInfoMapper formInfoMapper;
    @Resource
    private IcInfoMapper icInfoMapper;
    @Resource
    private PhotoMapper photoMapper;
    @Resource
    private VeLicenseRecognitionMapper veLicenseRecognitionMapper;
    @Resource
    private GveRfidMapper gVeRfidMapper;

    @Resource(name  = "COLLECT")
    private BusinessSystemService systemService;
    @Resource(name  = "HDFZ")
    private BusinessSystemService hdfzSystemService;
    @Resource(name  = "ZS")
    private BusinessSystemService zsSystemService;
    @Resource(name  = "YBTKJ")
    private BusinessSystemService ybtSystemService;

    @Resource
    private StationManagementMapper stationManagement;
    @Async(value = "threadPoolTaskExecutor")
    @Override
    public void handle(Map map, String ipAddress) {
        GatherdataLog gl = (GatherdataLog) map.get("gl");
        // 更新数据库 使用的key
        String sessionId = gl.getSessionId();
        // 通过通道号获取对接的系统列表
        String dockingSystem = stationManagement.getDockingSystem(gl.getChnlNo());
        List<String> dockingList = StringUtils.str2List(dockingSystem, ",", true, true);
        boolean isNotRelease;
        for (String docking : dockingList) {
            switch (docking) {
                case "ZS":
                    BusinessContext context = new BusinessContext(zsSystemService);
                     isNotRelease = context.businessProcessing(map);
                    break;
                case "HDFZ":
                    BusinessContext hdContext = new BusinessContext(hdfzSystemService);
                     isNotRelease = hdContext.businessProcessing(map);
                    break;
                case "YBTKJ":
                    BusinessContext ybtContext = new BusinessContext(ybtSystemService);
                     isNotRelease = ybtContext.businessProcessing(map);
                    break;
                default:
                    continue;
            }
            if (isNotRelease) {
                break;
            }
        }
        BusinessContext collectContext = new BusinessContext(systemService);
        collectContext.collectBusinessProcessing(sessionId, ipAddress);
    }

    @Override
    public void isCorrect(Map<String, ?> map) throws CustomException {

        StringBuilder str = new StringBuilder();
        GatherdataLog gl = (GatherdataLog) map.get("gl");
        List<ValidateResult> validate = MyAnnotation.validate(gl);
        for (ValidateResult va : validate) {
            if (va.isValid()) {
                str.append(va.getMessage()).append("\n");
            }
        }
        try {
            Double.valueOf(gl.getGrossWt());
        } catch (Exception e) {
            logger.error(e.getMessage());
            str.append("地磅称重的值不是数字");
        } finally {
            if (StringUtils.isNotEmpty(str.toString())) {
                throw new CustomException(str.toString());
            }

        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void xmlToJavaBenSave(Map map) {

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        GatherdataLog gatherdataLog = (GatherdataLog) map.get("gl");
        String sessionId = gatherdataLog.getSessionId();
        try {
            for (Map.Entry<String, Object> entrty : entries) {
                if (entrty.getKey().contains("cr")) {
                    ContaRecognition contaRecognition = (ContaRecognition) entrty.getValue();
                    contaRecognition.setSessionId(sessionId);
                    contaRecognitionMapper.insertContaRecognition(contaRecognition);

                } else if (entrty.getKey().contains("gl")) {
                    glMapper.insertGatherdataLog(gatherdataLog);

                } else if (entrty.getKey().contains("dr")) {
                    DrRfid drRfid = (DrRfid) entrty.getValue();
                    drRfid.setSessionId(sessionId);
                    drRfidMapper.insertDrRfid(drRfid);

                } else if (entrty.getKey().contains("extend")) {
                    Extend extend = (Extend) entrty.getValue();
                    extend.setSessionId(sessionId);
                    extendMapper.insertExtend(extend);

                } else if (entrty.getKey().contains("fi")) {
                    FormInfo formInfo = (FormInfo) entrty.getValue();
                    formInfo.setSessionId(sessionId);
                    formInfoMapper.insertFormInfo(formInfo);

                } else if (entrty.getKey().contains("ic")) {
                    IcInfo icInfo = (IcInfo) entrty.getValue();
                    icInfo.setSessionId(sessionId);
                    icInfoMapper.insertIcInfo(icInfo);

                } else if (entrty.getKey().contains("photo")) {
                    Photo photo = (Photo) entrty.getValue();
                    photo.setSessionId(sessionId);
                    photoMapper.insertPhoto(photo);

                } else if (entrty.getKey().contains("vr")) {
                    VeLicenseRecognition veLicenseRecognition = (VeLicenseRecognition) entrty.getValue();
                    veLicenseRecognition.setSessionId(sessionId);
                    veLicenseRecognitionMapper.insertVeLicenseRecognition(veLicenseRecognition);
                } else if (entrty.getKey().contains("ve")) {
                    VeRfid veRfid = (VeRfid) entrty.getValue();
                    veRfid.setSessionId(sessionId);
                    gVeRfidMapper.insertGveRfid(veRfid);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    @Override
    public void genXml(String xml, String resJson, Map map) {
        if (null != map) {
            GatherdataLog gl = (GatherdataLog) map.get("gl");
            systemService.saveInteractiveXml(xml, resJson, gl.getSessionId());
        }

    }


}





