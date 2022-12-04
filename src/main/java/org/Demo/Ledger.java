package org.Demo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    public Boolean evaluateLedger(){
        boolean value = false;
        try{
            Path path = Paths.get("src\\main\\resources\\test.txt");
            Scanner scanner = new Scanner(path);
            List<String[]> splited = new ArrayList();
            List<String> loanList = new ArrayList<>();
            List<String> paymentList = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] stringSplit = line.split("\\s+");
                splited.add(stringSplit);
            }
            scanner.close();

            for(int i=0;i< splited.size();i++){
                String[] a =splited.get(i);
                //Loan List
                if(a[0].equalsIgnoreCase("LOAN")) {
                    loanList.add(a[1]); //Bank Name
                    loanList.add(a[2]); // Borrower Name
                    Double interest = (Double.parseDouble(a[3]) * Double.parseDouble(a[4]) * Double.parseDouble(a[5]))/100;
                    Double Amount = Double.parseDouble(a[3])+interest;
                    Double Emi=Math.ceil(Amount/(Double.parseDouble(a[4])*12));
                    loanList.add(a[4]); // year
                    loanList.add(Double.toString(Emi)); // Monthly Emi
                    loanList.add(Double.toString(Amount)); //Total Amount
                }

                //Payment List
                if(a[0].equalsIgnoreCase("PAYMENT")) {
                    paymentList.add(a[1]); //Bank Name
                    paymentList.add(a[2]); //Borrower Name
                    paymentList.add(a[3]); //lumpsum
                    paymentList.add(a[4]); //EMi Num
                }
            }

            for(int i=0;i< splited.size();i++) {
                String[] b =splited.get(i);

                if(b[0].equalsIgnoreCase("BALANCE")) {
                    Double amountPaid= 0.0;
                    Double emisLeft=0.0;
                    int lumpsum = 0;

                    for(int j=0;j<paymentList.size();j++){
                        //bankname match with paymentList bank name
                        if(b[1].equalsIgnoreCase(paymentList.get(j)) && (Integer.parseInt(b[3]) <
                                Integer.parseInt(paymentList.get(j+3)))) {
                            lumpsum = 0;
                        } else if (b[1].equalsIgnoreCase(paymentList.get(j)) && (Integer.parseInt(b[3])>=Integer.parseInt(paymentList.get(j+3)))) {
                            lumpsum = Integer.parseInt(paymentList.get(j+2));
                        }
                    }


                    for(int j=0;j<loanList.size();j++){
                        if(b[1].equalsIgnoreCase(loanList.get(j))) {
                            String MonthlyEmi = loanList.get(j+3); // monthly emi
                            amountPaid = (Double.parseDouble(b[3])* Double.parseDouble(MonthlyEmi))+lumpsum;
                            emisLeft = (Double.parseDouble(loanList.get(j+4))-amountPaid)/Double.parseDouble(MonthlyEmi);
                        }
                    }
                    System.out.println(b[1]+" "+b[2]+" "+amountPaid+" "+Math.ceil(emisLeft));
                }
            }
            value = true;
        } catch (Exception e){
            System.out.println("exception"+e.getStackTrace());
        }
       return value;
    }
}
