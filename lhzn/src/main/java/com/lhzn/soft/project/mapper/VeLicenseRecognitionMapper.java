package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.VeLicenseRecognition;

import java.util.List;

/**
 * qweMapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface VeLicenseRecognitionMapper 
{
    /**
     * 查询qwe
     * 
     * @param id qweID
     * @return qwe
     */
    public VeLicenseRecognition selectVeLicenseRecognitionById (Long id);

    /**
     * 查询qwe列表
     * 
     * @param sessionId qwe
     * @return qwe集合
     */
    public VeLicenseRecognition selectVeLicenseRecognition(String sessionId);

    /**
     * 新增qwe
     * 
     * @param veLicenseRecognition qwe
     * @return 结果
     */
    public int insertVeLicenseRecognition (VeLicenseRecognition veLicenseRecognition);

    /**
     * 修改qwe
     * 
     * @param veLicenseRecognition qwe
     * @return 结果
     */
    public int updateVeLicenseRecognition (VeLicenseRecognition veLicenseRecognition);

    /**
     * 删除qwe
     * 
     * @param id qweID
     * @return 结果
     */
    public int deleteVeLicenseRecognitionById (Long id);

    /**
     * 批量删除qwe
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVeLicenseRecognitionByIds (Long[] ids);
}
