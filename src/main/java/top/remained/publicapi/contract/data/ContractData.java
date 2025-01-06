package top.remained.publicapi.contract.data;

import lombok.Data;

/**
 * @author lx
 * @date 2025/1/2
 * @description
 */
@Data
public class ContractData {
        private String sellerName;            // 卖家
        private String buyerName;             // 买家
        private String sellerPhone;           // 卖家电话
        private String buyerPhone;            // 买家电话
        private String variety;               // 品种
        private String color;                 // 颜色
        private String gender;                // 性别
        private String birthDay;              // 出生日期
        private String salePrice;             // 出售金额
        private String bearTransport;         // 包运费/不包运费
        private String deposit;               // 定金
        private String frontBacked;               // 定金
        private String balance;               // 尾款
        private String predictDeliveryDate;   // 预计到达日期
        private String transport;             // 运输方式
        private String contractDate;          // 签订合同时间
}
