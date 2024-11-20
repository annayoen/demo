package git;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
class Bill {
    private String type;
    private String date;
    private double amount;
    private String category;
    private String note;
    public Bill(String type, String date, double amount, String category, String note) {
        this.type = type; // 收入或支出
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.note = note;
    }
    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
    public double getAmount() {
        return amount;
    }
    public String getCategory() {
        return category;
    }
    public String getNote() {
        return note;
    }
    @Override
    public String toString() {
        return String.format("%s: %s | %.2f | %s | %s", type, date, amount, category, note);
    }
}
public class BillManager {
    private ArrayList<Bill> bills;
    private Scanner scanner;

    public BillManager() {
        bills = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void welcome() {
        System.out.println("=================================");
        System.out.println("欢迎使用个人账单管理系统");
        System.out.println("=================================");
    }
    public void addIncome() {
        welcome();
        System.out.println("请输入收入信息：");
        System.out.print("日期(yyyy-MM-dd): ");
        String date = scanner.next();
        System.out.print("金额:");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("金额必须大于0!");
            return;
        }
        System.out.print("类别（如工资、奖金等）:");
        String category = scanner.next();
        System.out.print("备注:");
        String note = scanner.next();

        bills.add(new Bill("Income", date, amount, category, note));
        System.out.println("收入已成功记录！");
    }
    public void addExpense() {
        welcome();
        System.out.println("请输入支出信息：");
        System.out.print("日期(yyyy-MM-dd): ");
        String date = scanner.next();
        System.out.print("金额:");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("金额必须大于0!");
            return;
        }
        System.out.print("类别（如餐饮、交通、购物等）:");
        String category = scanner.next();
        System.out.print("备注:");
        String note = scanner.next();

        bills.add(new Bill("支出", date, amount, category, note));
        System.out.println("支出已成功记录！");
    }
    public void showAllBills() {
        welcome();
        System.out.println("所有账单记录：");
        System.out.println("收入：");
        for (Bill bill : bills) {
            if (bill.getType().equals("Income")) {
                System.out.println(bill);
            }
        }
        System.out.println("支出：");
        for (Bill bill : bills) {
            if (bill.getType().equals("支出")) {
                System.out.println(bill);
            }
        }
    }
    public void queryBills() {
        welcome();
        System.out.println("请选择查询方式：");
        System.out.println("1. 按日期查询");
        System.out.println("2. 按日期范围查询");
        System.out.println("3. 按类别查询");
        System.out.print("请输入选项序号：");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                queryByDate();
                break;
            case 2:
                queryByDateRange();
                break;
            case 3:
                queryByCategory();
                break;
            default:
                System.out.println("无效选项！请重试。");
        }
    }
    private void queryByDate() {
        System.out.print("请输入查询日期(yyyy-MM-dd): ");
        String date = scanner.next();

        System.out.println("查询结果：");
        System.out.println("收入：");
        for (Bill bill : bills) {
            if (bill.getType().equals("Income") && bill.getDate().equals(date)) {
                System.out.println(bill);
            }
        }
        System.out.println("支出：");
        for (Bill bill : bills) {
            if (bill.getType().equals("支出") && bill.getDate().equals(date)) {
                System.out.println(bill);
            }
        }
    }

    private void queryByDateRange() {
        System.out.print("请输入起始日期(yyyy-MM-dd): ");
        String startDate = scanner.next();
        System.out.print("请输入结束日期(yyyy-MM-dd): ");
        String endDate = scanner.next();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = (Date) sdf.parse(startDate);
            Date end = (Date) sdf.parse(endDate);

            System.out.println("查询结果：");
            System.out.println("收入：");
            for (Bill bill : bills) {
                Date billDate = (Date) sdf.parse(bill.getDate());
                if (bill.getType().equals("Income") && billDate.compareTo(start) >= 0 && billDate.compareTo(end) <= 0) {
                    System.out.println(bill);
                }
            }
            System.out.println("支出：");
            for (Bill bill : bills) {
                Date billDate = (Date) sdf.parse(bill.getDate());
                if (bill.getType().equals("支出") && billDate.compareTo(start) >= 0 && billDate.compareTo(end) <= 0) {
                    System.out.println(bill);
                }
            }
        } catch (ParseException e) {
            System.out.println("日期格式错误！");
        }
    }
    private void queryByCategory() {
        System.out.print("请输入查询类别: ");
        String category = scanner.next();

        System.out.println("查询结果：");
        System.out.println("收入：");
        for (Bill bill : bills) {
            if (bill.getType().equals("Income") && bill.getCategory().equals(category)) {
                System.out.println(bill);
            }
        }
        System.out.println("支出：");
        for (Bill bill : bills) {
            if (bill.getType().equals("支出") && bill.getCategory().equals(category)) {
                System.out.println(bill);
            }
        }
    }
    public void showMenu() {
        while (true) {
            welcome();
            System.out.println("请选择操作：");
            System.out.println("1. 记录收入");
            System.out.println("2. 记录支出");
            System.out.println("3. 查看所有账单");
            System.out.println("4. 查询账单");
            System.out.println("5. 退出系统");
            System.out.print("请输入选项序号：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addExpense();
                    break;
                case 3:
                    showAllBills();
                    break;
                case 4:
                    queryBills();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BillManager manager = new BillManager();
        manager.showMenu();
    }
}