package git;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
class Bill {
    private String type; 
    private String date;
    private double amount;
    private String category;
    private String note;
    public Bill(String type, String date, double amount, String category, String note) {
        this.type = type;//收入或支出
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
        double amount =scanner.nextDouble();
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
        double amount =scanner.nextDouble();
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
    public void showMenu() {
        while (true) {
        	welcome();
            System.out.println("请选择操作：");
            System.out.println("1.记录收入");
            System.out.println("2.记录支出");
            System.out.println("3.查看所有账单");
            System.out.println("4.查询账单");
            System.out.println("5. 退出系统");
            System.out.print("请输入选项序号：");
            int choice =scanner.nextInt();
            switch (choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addExpense();
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
