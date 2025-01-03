package top.remained.publicapi.contract.api;


import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author lx
 * @date 2025/1/2
 * @description
 */

@RestController
@RequestMapping("contract")
@CrossOrigin
public class ContractController {
    @Value("${font.path}")
    private String fontPath;

//    @PostMapping
//    public void generateContract(@RequestBody ContractData contractData, HttpServletResponse response) throws IOException {
//        // 设置响应头，告诉浏览器返回的是 PDF 文件
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=contract.pdf");
//        // 创建 PdfWriter 和 PdfDocument
//        PdfWriter writer = new PdfWriter(response.getOutputStream());
//        PdfDocument pdfDoc = new PdfDocument(writer);
//        Document document = new Document(pdfDoc);
//        // 设置字体  simhei.ttf黑体  SimSun宋体
//        PdfFont font = PdfFontFactory.createFont("C:\\soft\\download\\SimHei.ttf", PdfEncodings.IDENTITY_H);
//        // 生成合同内容
//        document.add(new Paragraph("甲方（卖方）：" + contractData.getPartyA() + "\t\t\t乙方（买方）：" + contractData.getPartyB())).setFont(font);
//        document.add(new Paragraph("联系电话：" + contractData.getContactA() + "\t\t联系电话：" + contractData.getContactB())).setFont(font);
//        document.add(new Paragraph("甲乙双方本着保护动物、爱惜、尊重生命的人道主义精神，以诚信公平合理原则，经双方充分平等协商，订立本合同，双方共同遵守如下：")).setFont(font);
//
//        // 交易猫咪信息
//        document.add(new Paragraph("交易猫咪信息")).setFont(font);
//        document.add(new Paragraph("品种：" + contractData.getBreed())).setFont(font);
//        document.add(new Paragraph("花色：" + contractData.getColor())).setFont(font);
//        document.add(new Paragraph("性别：" + contractData.getGender())).setFont(font);
//        document.add(new Paragraph("出生日期：" + contractData.getBirthDate())).setFont(font);
//
//        // 第一部分内容
//        document.add(new Paragraph("一、甲方现将海山双布偶妹妹出售给乙方，出售金额为：" + contractData.getAmount() + "元整（包运输）")).setFont(font);
//        document.add(new Paragraph("二、" + contractData.getDepositDate() + "乙方向甲方支付定金XX元整，猫咪交付前一天乙方须向甲方支付尾款XX元整")).setFont(font).setFont(font);
//        document.add(new Paragraph("三、乙方接收猫咪日期为" + contractData.getDeliveryDate() + "左右，运输方式为：" + contractData.getDeliveryMethod())).setFont(font);
//
//        // 售后保障与义务
//        document.add(new Paragraph("四、售后保障与义务：")).setFont(font);
//        document.add(new Paragraph(contractData.getAfterSales())).setFont(font);
//
//        // 运输风险
//        document.add(new Paragraph("五、运输风险")).setFont(font);
//        document.add(new Paragraph(contractData.getTransportRisk())).setFont(font);
//
//        // 其他条款
//        document.add(new Paragraph("六、其他")).setFont(font);
//        document.add(new Paragraph("1. 对本合同之任何变更及增加，仅在以书面经双方签宇后，方为有效，任何一方在未取得对方书面同意前，无权将本合同规定之权利及义务转让给第三者。")).setFont(font);
//        document.add(new Paragraph("2. 本合同经双方签署后生效，合同一式两份，具有同等法律效益，由甲方乙方双方各执一份。")).setFont(font);
//        document.add(new Paragraph("3. 本合同一经签订，双方须道守承诺，未尽事宜双方协商解决，如若协商不成，任何一方可向甲方所在地人民法院提起诉讼。")).setFont(font);
//
//        // 甲方乙方签字日期
//        document.add(new Paragraph("甲方： \t\t\t乙方：")).setFont(font);
//        document.add(new Paragraph(contractData.getFinalPaymentDate() + " \t\t\t" + contractData.getFinalPaymentDate())).setFont(font);
//
//        // 关闭文档
//        document.close();
//    }
    @PostMapping("/generate-pdf")
    public void generateContract(@RequestBody Map<String, String> payload, HttpServletResponse response) throws IOException {
        String fontSize = payload.get("fontSize");
        Integer px = Integer.valueOf(fontSize.replace("px", ""));
        String contractContent = payload.get("content");
// 去除多余的换行符和空格
//        contractContent = contractContent.replaceAll("[\\r\\n]+", "\n").trim();
        // 设置响应头
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=contract.pdf");

        // 使用 iText7 创建 PDF
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // 设置字体
        PdfFont font = PdfFontFactory.createFont("C:\\soft\\download\\SimHei.ttf", PdfEncodings.IDENTITY_H);

        // 按段落生成 PDF
        for (String line : contractContent.split("\n")) {
            // 去除每行前后的空格
//            line = line.trim();
//            if (!line.isEmpty()) {  // 只添加非空行
                document.add(new Paragraph(line).setFont(font).setFontSize(px));
//            }
        }

        // 关闭文档
        document.close();
    }

}

