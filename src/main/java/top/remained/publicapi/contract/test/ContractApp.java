//package top.remained.publicapi.contract.test;
//
//import top.remained.publicapi.contract.test.ContractGenerator;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
///**
// * @author lx
// * @date 2025/1/2
// * @description
// */
//
//
//public class ContractApp {
//    public static void main(String[] args) {
//        // 创建主窗口
//        JFrame frame = new JFrame("购猫合同生成器");
//        frame.setSize(500, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//
//        // 添加表单输入字段
//        JLabel lblSellerName = new JLabel("甲方（卖方）名称：");
//        lblSellerName.setBounds(20, 20, 150, 30);
//        frame.add(lblSellerName);
//        JTextField txtSellerName = new JTextField();
//        txtSellerName.setBounds(180, 20, 200, 30);
//        frame.add(txtSellerName);
//
//        JLabel lblSellerPhone = new JLabel("甲方电话：");
//        lblSellerPhone.setBounds(20, 60, 150, 30);
//        frame.add(lblSellerPhone);
//        JTextField txtSellerPhone = new JTextField();
//        txtSellerPhone.setBounds(180, 60, 200, 30);
//        frame.add(txtSellerPhone);
//
//        JLabel lblBuyerName = new JLabel("乙方（买方）名称：");
//        lblBuyerName.setBounds(20, 100, 150, 30);
//        frame.add(lblBuyerName);
//        JTextField txtBuyerName = new JTextField();
//        txtBuyerName.setBounds(180, 100, 200, 30);
//        frame.add(txtBuyerName);
//
//        JLabel lblBuyerPhone = new JLabel("乙方电话：");
//        lblBuyerPhone.setBounds(20, 140, 150, 30);
//        frame.add(lblBuyerPhone);
//        JTextField txtBuyerPhone = new JTextField();
//        txtBuyerPhone.setBounds(180, 140, 200, 30);
//        frame.add(txtBuyerPhone);
//
//        JLabel lblBreed = new JLabel("猫咪品种：");
//        lblBreed.setBounds(20, 180, 150, 30);
//        frame.add(lblBreed);
//        JTextField txtBreed = new JTextField();
//        txtBreed.setBounds(180, 180, 200, 30);
//        frame.add(txtBreed);
//
//        JLabel lblPrice = new JLabel("售价（元）：");
//        lblPrice.setBounds(20, 220, 150, 30);
//        frame.add(lblPrice);
//        JTextField txtPrice = new JTextField();
//        txtPrice.setBounds(180, 220, 200, 30);
//        frame.add(txtPrice);
//
//        // 提交按钮
//        JButton btnGenerate = new JButton("生成合同");
//        btnGenerate.setBounds(180, 500, 150, 40);
//        frame.add(btnGenerate);
//
//        // 按钮事件
//        btnGenerate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // 获取用户输入
//                String sellerName = txtSellerName.getText();
//                String sellerPhone = txtSellerPhone.getText();
//                String buyerName = txtBuyerName.getText();
//                String buyerPhone = txtBuyerPhone.getText();
//                String breed = txtBreed.getText();
//                String price = txtPrice.getText();
//
//                // 调用生成 PDF 方法
//                ContractGenerator.generateContract(buyerName, buyerPhone, sellerName, sellerPhone, breed, "蓝白", "母",
//                        "2023年12月01日", price, "2000", "2024年01月15日", "空运");
//
//                JOptionPane.showMessageDialog(frame, "合同生成成功！");
//            }
//        });
//
//        // 显示窗口
//        frame.setVisible(true);
//    }
//}
