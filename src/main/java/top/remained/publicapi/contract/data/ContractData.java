package top.remained.publicapi.contract.data;

import lombok.Data;

/**
 * @author lx
 * @date 2025/1/2
 * @description
 */
@Data
public class ContractData {
        private String partyA;
        private String partyB;
        private String contactA;
        private String contactB;
        private String breed;
        private String color;
        private String gender;
        private String birthDate;
        private double amount;
        private String depositDate;
        private String finalPaymentDate;
        private String deliveryDate;
        private String deliveryMethod;
        private String afterSales;
        private String transportRisk;
}
