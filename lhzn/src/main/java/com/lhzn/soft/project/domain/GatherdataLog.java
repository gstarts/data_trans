package com.lhzn.soft.project.domain;

import com.lhzn.soft.framework.aspectj.lang.annotation.NotNull;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * qwe对象 g_gatherdata_log
 *
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class GatherdataLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 报文序列号
     */
    @NotNull(fileName = "会话编号")
    private String sessionId;
    /**
     * 场站代码
     */
    @NotNull(fileName = "监管场所代码")
    private String areaId;
    /**
     * 卡口代码
     */
    @NotNull(fileName = "通道号")
    private String chnlNo;
    /**
     * 进出标志
     */
    @NotNull(fileName = "进出卡口标志")
    private String iEFlag;
    /**
     * 地磅称重
     */
    @NotNull(fileName = "地磅称重")
    private String grossWt;
    /**
     * 当前关区号
     */
    @NotNull(fileName = "当前关区号")
    private String cusCode;
    /**
     * 卡口标识类型
     */
    @NotNull(fileName = "卡口类型标识")
    private String rpbType;
    /**
     * 采集标志
     */
    private String getherMode;

    /**
     * 驾驶员编号
     */

    private String drCustomsNo;

    /**
     * 车辆海关编号
     */

    private String veCustomsNo;

    /**
     * 车牌识别车牌号
     */

    private String veLicenseNo;

    /**
     * 箱数
     */

    private String contaId;

    /**
     * 电子关锁号(安全智能锁号)
     */

    private String esealId;


    /**
     * 操作关员
     */

    private String operatorId;

    /**
     * 操作时间
     */

    private String operateTime;

    /**
     * 验放列表类型
     */

    private String rellistType;

    /**
     * 验放列表id类型
     */

    private String rellistIdType;

    /**
     * 验放列表id
     */
    private String rellistId;

    /**
     * 后台核放时间
     */

    private String feedbackTime;

    /**
     * 最终处理结果：y为放行，n为报警，m为待人工确认
     */

    private String checkResult;

    /**
     * 后台对前端的敏感设备操作
     */

    private String instruction;

    /**
     * $column.columnComment
     */

    private String photo;

    /**
     * 海关业务方面对返回结果代码，返回给关员
     */

    private String procErrorCode;

    /**
     * 业务逻辑错误解释
     */

    private String procErrorDescription;

    /**
     * 技术错误码
     */

    private String techErrorCode;

    /**
     * 技术错误解释
     */

    private String techErrorDescription;

    /**
     * 铅封号
     */

    private String sealId;

    /**
     * 单证总件数
     */

    private String packNo;

    /**
     * 舱单总件数
     */

    private String declPack;

    /**
     * 舱单货物总重量
     */

    private String declGoodsWeight;

    /**
     * 核放结果详细信息（布控、地磅等)
     */

    private String opHint;

    /**
     * led操作提示
     */

    private String ledHint;

    /**
     * 扩展内容
     */

    private String extendedContent;

    /**
     * 81指令xml
     */

    private String x81Command;

    /**
     * 82指令xml
     */

    private String x82Command;

    /**
     * 进出时间
     */

    private Date inoutTime;

    /**
     * 1：采集系统，2：验放系统，3：通讯系统，10：采集系统数据采集完成，等待验放，11：采集系统处理完成，本次过卡完成，20：验放系统处理完成，等待采集系统执行，21：验放系统场站验放完成，等待海关验放，30：通讯平台发送81完成，31：通讯平台接收82完成。海关验放完成，等待验放平台处理。
     */
    private String gatheralldataFlg;

    @Override
    public String toString () {
        return new ToStringBuilder (this, ToStringStyle.MULTI_LINE_STYLE)
                .append ("sessionId", getSessionId ())
                .append ("areaId", getAreaId ())
                .append ("chnlNo", getChnlNo ())
                .append ("iEFlag", getIEFlag ())
                .append ("getherMode", getGetherMode ())
                .append ("drCustomsNo", getDrCustomsNo ())
                .append ("veCustomsNo", getVeCustomsNo ())
                .append ("veLicenseNo", getVeLicenseNo ())
                .append ("contaId", getContaId ())
                .append ("esealId", getEsealId ())
                .append ("grossWt", getGrossWt ())
                .append ("operatorId", getOperatorId ())
                .append ("operateTime", getOperateTime ())
                .append ("rellistType", getRellistType ())
                .append ("rellistIdType", getRellistIdType ())
                .append ("rellistId", getRellistId ())
                .append ("feedbackTime", getFeedbackTime ())
                .append ("checkResult", getCheckResult ())
                .append ("instruction", getInstruction ())
                .append ("photo", getPhoto ())
                .append ("procErrorCode", getProcErrorCode ())
                .append ("procErrorDescription", getProcErrorDescription ())
                .append ("techErrorCode", getTechErrorCode ())
                .append ("techErrorDescription", getTechErrorDescription ())
                .append ("sealId", getSealId ())
                .append ("packNo", getPackNo ())
                .append ("declPack", getDeclPack ())
                .append ("declGoodsWeight", getDeclGoodsWeight ())
                .append ("opHint", getOpHint ())
                .append ("ledHint", getLedHint ())
                .append ("extendedContent", getExtendedContent ())
                .append ("inoutTime", getInoutTime ())
                .append ("gatheralldataFlg", getGatheralldataFlg ())
                .toString ();
    }
}
