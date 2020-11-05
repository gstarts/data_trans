package com.lhzn.soft.project.mapper;

import java.util.List;
import com.lhzn.soft.project.domain.ContaRecognition;

/**
 * 车辆通关箱号识别Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface ContaRecognitionMapper 
{
    /**
     * 查询车辆通关箱号识别
     * 
     * @param id 车辆通关箱号识别ID
     * @return 车辆通关箱号识别
     */
    public ContaRecognition selectContaRecognitionById (Long id);

    /**
     * 查询车辆通关箱号识别列表
     * 
     * @param sessionId 车辆通关箱号识别
     * @return 车辆通关箱号识别集合
     */
    public ContaRecognition selectContaRecognition (String sessionId);

    /**
     * 新增车辆通关箱号识别
     * 
     * @param contaRecognition 车辆通关箱号识别
     * @return 结果
     */
    public int insertContaRecognition (ContaRecognition contaRecognition);

    /**
     * 修改车辆通关箱号识别
     * 
     * @param contaRecognition 车辆通关箱号识别
     * @return 结果
     */
    public int updateContaRecognition (ContaRecognition contaRecognition);

    /**
     * 删除车辆通关箱号识别
     * 
     * @param id 车辆通关箱号识别ID
     * @return 结果
     */
    public int deleteContaRecognitionById (Long id);

    /**
     * 批量删除车辆通关箱号识别
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteContaRecognitionByIds (Long[] ids);
}
