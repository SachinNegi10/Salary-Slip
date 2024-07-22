
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class salarySlip{
    static ResourceBundle rb;
    static void loadBundle(){
        rb = ResourceBundle.getBundle("message", locale);
    }


    static Locale locale;//(member of a class(class member))

    static String properCase(String Name){
        String fullName = "";
        
        String Names[] = Name.split(" ");
        for (int i = 0; i < Names.length; i++) {
            String n = String.valueOf(Names[i].charAt(0)).toUpperCase()+
             Names[i].substring(1).toLowerCase();
            // String val = Names[i];
            // char firstLatter = val.charAt(0);
            // String firstCapLetter = String.valueOf(firstLatter).toUpperCase();

            // String remainingName = Names[i].substring(1).toLowerCase();

            // String n = firstCapLetter+remainingName;
             fullName = fullName+n;

        }
        return fullName;

    }

    static String currencyFormat(double val){

        NumberFormat obj = NumberFormat.getCurrencyInstance(locale);
        return obj.format(val);

    }

    static String dateFormat(){
        //System date
        Date date = new Date();
       // System.out.println(date);
       DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
        return df.format(date);

            
        

    }
    static void input(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Press 1 for English ");
        System.out.println(" हिंदी के लिए 2 दबाएँ ");
        System.out.println("Press 3 for French ");
        
        int choice = scanner.nextInt();
        
        if (choice==1) {
            locale =  Locale.of("en", "US");
            
        }else if (choice ==2 ) {
            locale =  Locale.of("hi", "IN");
            
        }
        else if(choice ==3){
            locale = Locale.of("fr", "FR");

        }
        else{
            locale = Locale.of("en","US");
            System.out.println("Wrong choice... Taking English as default ");
        }
        loadBundle();
        System.out.println(rb.getString("id.msg"));
        int Id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(rb.getString("Name.msg"));
        String Name = scanner.nextLine();
        System.out.println(rb.getString("salary.msg"));
        double basicSalary = scanner.nextDouble();

        compute(Name,basicSalary);
        scanner.close();


    }
    static void compute( String Name,double basicSalary){
        double hra = basicSalary*0.50;
        double ta = basicSalary*0.40;
        double ma = basicSalary*0.25;
        double da = basicSalary*0.20;
        double pf = basicSalary*0.05;
        double gs = basicSalary+hra+ta+da+ma;
        double tax = computeTax(gs);
        double ns = gs-pf-tax;
        
        print(Name,gs,hra, ta, ma, da, pf,tax, ns);
        
    }
    static double computeTax(double gs){
        double tax = 0.0;
        double annual = gs*12;
        if(annual>500000 && annual<700000){
            return (annual*0.10/12);
        }
        else if(annual>700000 && annual<900000){
            return (annual*0.20)/12;
        }
        else if(annual>900000){
            return (annual*0.30)/12;
        }
        return 0.0;

    }
    static void print( String Name,double gs,double hra, double ta, double ma, double da, double pf,double tax, double ns){
        System.out.println( rb.getString("date.msg")+dateFormat());
        System.out.println("NAME: "+ properCase(Name));
        System.out.println(rb.getString("allowances.msg") +" \t Deduction");
        System.out.println("HRA "+currencyFormat(hra) +"\t \t PF "+ currencyFormat(pf));
        System.out.println("TA "+currencyFormat(ta)+"\t\t TAX "+ currencyFormat(tax));
        System.out.println("MA "+currencyFormat(ma));
        System.out.println("DA "+currencyFormat(da));
        System.out.println("GS "+currencyFormat(gs)); 
        //System.out.println("TAX "+tax);
        System.out.println("NS "+currencyFormat(ns));
        

    }
   
    public static void main(String[] args) {
        input();
        
        

    }
}