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
import top.remained.publicapi.contract.data.ContractData;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
    @Value("${contract.file.path}")
    private String contractFilePath;

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
        String tempFilePath = contractFilePath+
                LocalDateTime.now().toString().replaceAll(":","-")
                        .replaceAll("\\.","-")+".pdf"; // 临时文件路径
        PdfWriter writer = new PdfWriter(tempFilePath); // 创建用于保存 PDF 文件的 PdfWriter
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // 设置字体
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

        // 按段落生成 PDF 内容
        for (String line : contractContent.split("\n")) {
            document.add(new Paragraph(line).setFont(font).setFontSize(px));
        }

        // 关闭文档
        document.close();

        // 设置响应头，让浏览器下载文件
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=contract.pdf");

        // 读取本地临时文件并写入到响应流
        try (InputStream fileInputStream = new FileInputStream(tempFilePath);
             OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
//    @PostMapping("/fix-pdf1")
    public void fixPdf1(@RequestBody ContractData contractData, HttpServletResponse response) throws IOException {
        // 合同内容拼接
        String contractContent = generateContractContent(
                contractData.getSellerName(), contractData.getBuyerName(), contractData.getSellerPhone(),
                contractData.getBuyerPhone(), contractData.getVariety(), contractData.getColor(),
                contractData.getGender(), contractData.getBirthDay(), contractData.getSalePrice(),
                contractData.getBearTransport(), contractData.getDeposit(),contractData.getFrontBacked(),
                contractData.getBalance(), contractData.getPredictDeliveryDate(), contractData.getTransport(),
                contractData.getContractDate()
        );

        // 设置响应头
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=contract.pdf");

        // 使用 iText7 创建 PDF
        PdfWriter responseWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocForResponse = new PdfDocument(responseWriter);
        Document documentForResponse = new Document(pdfDocForResponse);

        // 设置字体
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
        // 保存到系统中
        Document documentForFile =  saveSystem(contractContent,font);
        // 按段落生成 PDF
        for (String line : contractContent.split("\n")) {
            documentForResponse.add(new Paragraph(line).setFont(font).setFontSize(8));
            documentForFile.add(new Paragraph(line).setFont(font).setFontSize(8));
        }

        // 关闭文档
        documentForFile.close();
        documentForResponse.close();
    }
    // 保存到系统中
    public Document saveSystem(String contractContent, PdfFont font) throws FileNotFoundException {
        String filePath = contractFilePath+
                LocalDateTime.now().toString().replaceAll(":","-")
                        .replaceAll("\\.","-")+".pdf";
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdfDocForFile = new PdfDocument(writer);
                                                    // 指定文件保存路径
        return new Document(pdfDocForFile);
    }
    @PostMapping("/fix-pdf")
    public void fixPdf(@RequestBody ContractData contractData, HttpServletResponse response) throws IOException {
        // 合同内容拼接
        String contractContent = generateContractContent(
                contractData.getSellerName(), contractData.getBuyerName(), contractData.getSellerPhone(),
                contractData.getBuyerPhone(), contractData.getVariety(), contractData.getColor(),
                contractData.getGender(), contractData.getBirthDay(), contractData.getSalePrice(),
                contractData.getBearTransport(), contractData.getDeposit(), contractData.getFrontBacked(),
                contractData.getBalance(), contractData.getPredictDeliveryDate(), contractData.getTransport(),
                contractData.getContractDate()
        );

        // 设置本地保存路径
        String tempFilePath = contractFilePath+
                LocalDateTime.now().toString().replaceAll(":","-")
                        .replaceAll("\\.","-")+".pdf"; // 临时文件路径
        PdfWriter writer = new PdfWriter(tempFilePath); // 创建用于保存 PDF 文件的 PdfWriter
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // 设置字体
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

        // 按段落生成 PDF 内容
        for (String line : contractContent.split("\n")) {
            document.add(new Paragraph(line).setFont(font).setFontSize(8));
        }

        // 关闭文档
        document.close();

        // 设置响应头，让浏览器下载文件
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=contract.pdf");

        // 读取本地临时文件并写入到响应流
        try (InputStream fileInputStream = new FileInputStream(tempFilePath);
             OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // 下载完成后删除临时文件
//        new File(tempFilePath).delete();
    }


    /**
     *
     * @param sellerName 卖家
     * @param buyerName 买家
     * @param sellerPhone 卖家电话
     * @param buyerPhone 买家电话
     * @param variety 品种
     * @param color 颜色
     * @param gender 性别
     * @param birthDay 出生日期
     * @param salePrice 出售金额
     * @param bearTransport 包运费/不包运费
     * @param deposit 定金
     * @param frontBacked 前、后
     * @param balance 尾款
     * @param predictDeliveryDate 预计到达日期
     * @param transport 运输方式
     * @param contractDate 签订合同时间
     * @return 合同模版
     */
    private String generateContractContent(String sellerName, String buyerName, String sellerPhone, String buyerPhone,
                                           String variety, String color,String gender,String birthDay,
                                           String salePrice,String bearTransport,String deposit, String frontBacked,String balance,
                                           String predictDeliveryDate,String transport,String contractDate) {
        return String.format(
                "甲方（卖方）：%s\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        " 乙方（买方）：%s\n" +
                        "联系电话：%s\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "联系电话：%s\n" +
                        "甲乙双方本着保护动物、爱惜、尊重生命的人道主义精神，以诚信公平合理原则，经双方充分平等协商，订立本合同，双方共同遵守如下：：\n" +
                        "交易猫咪信息\n" +
                        "品种：%s\n" +
                        "花色：%s\n" +
                        "性别：%s\n" +
                        "出生日期：%s" +
                        "一、甲方现将海山双布偶妹妹出售给乙方，出售金额为：%s元整%s\n" +
                        "二、乙方向甲方支付定金%s元整，猫咪交付%s乙方须向甲方支付尾款%s元整。\n" +
                        "三、乙方接收猫咪日期为%s左右，运输方式为：%s\n\n" +
                        "四、售后保障与义务：\n" +
                        "1. 甲方在交付猫咪前应对猫咪完成3次疫苗接种和常规体内外驱虫，并提供疫苗本。\n" +
                        "2. 猫咪到家30天内检测出猫瘟(FPV)，本合同活体猫在甲方指定宠物医院治疗，治疗费用甲方承担；若治疗无效死亡，甲方更换同等价位活体猫一只，如价格不等乙方需补差价，并重新签署合同。\n" +
                        "3. 猫咪到家30天内检测出猫传腹(FIP）本合同活体猫在甲方指定宠物医院治疗，所产生费用由甲方承担，最高报销至本合同活体猫实付金额；或本合同活体猫退还甲方，更换同等价位活体猫一只，如价格不等乙方需补差价，并重新签署合同。\n" +
                        "4. 遗传病包终身（肥厚心肌病、渐性视网膜萎缩）本猫舍所有种猫均通过基因检测，均不携带以上两种致病基因，也不会遗传给小猫。\n" +
                        "5. 若30天内出现常见疾病（感冒类，皮肤疾病类，肠胃疾病类），甲方免费提供药物指导治疗。\n" +
                        "6. 猫咪保证没有呼吸道、猫藓等疾病，否则退猫退款，出发前视频确认眼鼻干净没有耳螨。\n" +
                        "7. 乙方有按甲方告知的注意事项科学养护所购宠物的义务并细心看管，如果因饲养不当或他人引起的疾病甚至死亡甲方不负任何责任。\n\n" +
                        "五、运输风险\n" +
                        "甲方将在确保小猫身体健康、状态良好的前提下运送小猫前往新家，并为乙方提供发猫视频。甲方须保障猫咪安全到达乙方手中。\n" +
                        "由甲方承担小猫在运输中的一切风险。\n" +
                        "由乙方承担小猫落地后，因自身原因未能及时接猫产生的一系列风险。\n" +
                        "如小猫在运输过程中发生意外，将由甲方补偿乙方一只同等价位猫咪，且重新签署购猫合同，乙方将享受相同的售后保障。\n\n" +
                        "六、其他\n" +
                        "1.对本合同之任何变更及增加，仅在以书面经双方签宇后，方为有效，任何一方在未取得对方书面同意前，无权将本合同规定之权利及义务转让给第三者。\n" +
                        "2.本合同经双方签署后生效，合同一式两份，具有同等法律效益，由甲方乙方双方各执一份。\n" +
                        "3.本合同一经签订，双方须道守承诺，未尽事宜双方协商解决，如若协商不成，任何一方可向甲方所在地人民法院提起诉讼。\n\n" +
                        "甲方：%s\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t乙方：%s\n%s\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  %s",
                 sellerName, buyerName, sellerPhone, buyerPhone,
                 variety, color, gender,getChinesDay(birthDay),
                 salePrice, bearTransport, deposit, frontBacked,balance,
                getChinesDay(predictDeliveryDate), transport,sellerName,
                buyerName,getChinesDay(contractDate) , getChinesDay(contractDate)
        );
    }
    // yyyy-MM-dd ->yyyy年MM月dd日
    public String getChinesDay(String date) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
        if (date == null) {
            date = format1.format(new Date());
        }

        Date parse = null;
        try {
            parse = format1.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }

      return format2.format(parse);
    }
}

