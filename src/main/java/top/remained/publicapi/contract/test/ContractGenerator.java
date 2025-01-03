package top.remained.publicapi.contract.test;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
/**
 * @author lx
 * @date 2025/1/2
 * @description
 */

public class ContractGenerator {

    public static void generateContract(String buyerName, String buyerPhone, String sellerName, String sellerPhone,
                                        String breed, String color, String gender, String birthDate,
                                        String price, String deposit, String deliveryDate, String transportMethod) {
        try {
            // PDF 文件输出路径
            String outputFilePath = "C:\\Users\\lx_yy\\Desktop\\pdf\\contract1.pdf";

            // 创建 PDF 文档
            PdfWriter writer = new PdfWriter(outputFilePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            PdfFont font = PdfFontFactory.createFont("C:\\soft\\download\\SimHei.ttf", PdfEncodings.IDENTITY_H);
            // 添加合同标题
            document.add(new Paragraph("购猫合同").setBold().setFontSize(16).setMarginBottom(20)).setFont(font);

            // 添加甲乙方信息
            document.add(new Paragraph("甲方（卖方）：" + sellerName + "        联系电话：" + sellerPhone)).setFont(font);
            document.add(new Paragraph("乙方（买方）：" + buyerName + "        联系电话：" + buyerPhone)).setFont(font);
            document.add(new Paragraph("\n甲乙双方本着保护动物、爱惜、尊重生命的人道主义精神，以诚信公平合理原则，经双方充分平等协商，订立本合同，双方共同遵守如下：\n")).setFont(font);

            // 添加交易信息
            document.add(new Paragraph("交易猫咪信息：")).setFont(font);
            document.add(new Paragraph("品种：" + breed)).setFont(font);
            document.add(new Paragraph("花色：" + color)).setFont(font);
            document.add(new Paragraph("性别：" + gender)).setFont(font);
            document.add(new Paragraph("出生日期：" + birthDate)).setFont(font);
            document.add(new Paragraph("\n一、甲方现将海山双布偶妹妹出售给乙方，出售金额为：" + price + "元整（包运输）。")).setFont(font);
            document.add(new Paragraph("二、" + deliveryDate + "乙方向甲方支付定金" + deposit + "元整，猫咪交付前一天乙方须向甲方支付尾款。")).setFont(font);
            document.add(new Paragraph("三、乙方接收猫咪日期为" + deliveryDate + "左右，运输方式为：" + transportMethod + "。\n")).setFont(font);

            // 添加售后条款
            document.add(new Paragraph("四、售后保障与义务：")).setFont(font);
            document.add(new Paragraph("1. 甲方在交付猫咪前应对猫咪完成3次妙三多疫苗接种和常规体内外驱虫，并提供疫苗本。")).setFont(font);
            document.add(new Paragraph("2. 猫咪到家30天内检测出猫瘟(FPV)，本合同活体猫在甲方指定宠物医院治疗，治疗费用甲方承担；若治疗无效死亡，甲方退还乙方尾款4800元。")).setFont(font);
            document.add(new Paragraph("...（其他条款根据需求填写）...")).setFont(font);

            // 添加甲乙双方签名
            document.add(new Paragraph("\n甲方签名：______________________        乙方签名：______________________"));
            document.add(new Paragraph("日期：" + deliveryDate + "                 日期：" + deliveryDate));

            // 关闭文档
            document.close();
            System.out.println("合同生成成功！文件路径：" + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

