
package banking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;



public class Bank extends BankUtilities implements Transaction {



Account account = new Account();
ResultSet rs = null;
    Statement stmt = null;
  
   private float bal;

int accountnumber;
Float cashdeposit;
String address;
String city;
int pincode;
String state;
   

    private String sql;
    private float cashwithdraw;
    private float cashdepositamount;
    private Date date;
    private float Balance;

  void existingaccount() {

 try {
            System.out.println("Enter your accont number");
            account.setAccNum(in.nextInt());
            Connection conn1 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
            System.out.println("Creating statement...");
            conn1.setAutoCommit(false);
            sql = ("SELECT * FROM logbook where accountnumber = ? ");
            PreparedStatement preparedSelect = (PreparedStatement) conn1.prepareStatement(sql);
            preparedSelect.setInt(1, account.getAccNum());
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {
                int accountnumber1 = rs1.getInt("accountnumber");
                String name1 = rs1.getString("name");
                String address1 = rs1.getString("address");
                String state1 = rs1.getString("state");
                String city1 = rs1.getString("city");
                int pincode1 = rs1.getInt("pincode");
                System.out.format("%s, %s, %s, %s, %s, %s\n", accountnumber1, name1, address1, state1, city1, pincode1);
            }
            {
                System.out.println("\n\n\tmenu option");
                try {
                    while (true) {
                        System.out.println("1.CashDepost\t2.CashWithdraw\t3.checkbalance\t4.transactionDetails.");
                        int ch = in.nextInt();
                        switch (ch) {
                            case 1:
                                deposit();
                                break;
                            case 2:
                                withdraw();
                                break;
                            case 3:
                                checkBalance();
                                break;
                         case 4:
                                lasttransaction();
                                break;

                        }
                    }
                } catch (Exception ex) {
                    System.out.println("SELF THROWN EXCEPTION IS--->" );
                } finally {
                    in.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
                }





	void getInfo() {
		try {



			System.out.print("Enter your full Name: ");
			 account.setName(in.nextLine() ) ;

			System.out.print("Enter account number: ");
			account.setAccNo(in.nextInt());

			System.out.print("Enter your  address: ");
			 account.setAddress(in.next());

			System.out.println("Enter your city");
			account.setcity(in.next());

			System.out.println("Enter your state");
			account.setstate(in.next());

			System.out.println("Enter your pin code");
			account.setpinCode(in.nextInt());

			System.out.println("Mention account type:\n1.Savings (7% intr )\t2.Current (5% intr) ");
			acctype = in.nextInt();
			switch (acctype) {
			case 1:
				System.out.println("Enter the initial amount of deposit:");
				Cashdeposit = in.nextFloat();
				if (Cashdeposit < 0) {
					System.out.println("Invalid Amount\nTry again:\n");
					System.out.println("Enter the initial amount of deposit:");
					Cashdeposit = in.nextFloat();
				}
				account.setCashDeposit(Cashdeposit);
				System.out.println("Enter no of years:");
				year = in.nextInt();
				if (year <= 0) {
					System.out.println("Invalid year\n Try again.");
					System.out.println("Enter no of years:");
					year = in.nextInt();
				}

				break;

			case 2:
				System.out.println("Enter the initial amount of deposit:");
				Cashdeposit = in.nextFloat();
				if (Cashdeposit < 0) {
					System.out.println("Invalid Amount\nTry again:\n");
					System.out.println("Enter the initial amount of deposit:");
					Cashdeposit = in.nextFloat();
				}
				break;

			default:
				System.out.println("Invalid Option");
			}
			createAccountInDB( account );

		} catch (Exception e) {
			System.out.println("Inbuilt Exception --> " + e);
			System.exit(0);
		}

	}

	public void createAcc() {
		try {
			getInfo();
			System.out.println("\nAccount Successfully Created!");

			System.out.println("Hello " + account.getName() + " your account no is " + account.getAccNo() + ".\n");
		} catch (Exception e) {
			System.out.println("Fatal Error");
		}
	}



    private void checkBalance() {
     try {
            System.out.println("Enter your accont number");
            account.setAccNum(in.nextInt());
            Connection conn1 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
            System.out.println("Creating statement...");
            conn1.setAutoCommit(false);
     sql = ("SELECT balance FROM banking.logbook where accountnumber = ? ");
PreparedStatement preparedSelect = (PreparedStatement) conn1.prepareStatement(sql);
            preparedSelect.setInt(1, account.getAccNum());
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {

                int Balance1 = rs1.getInt("balance");
System.out.println("your current balance is");
               System.out.format("%s, %s, %s, %s, %s,\n" ,Balance1);
            }
        } catch (Exception e) {
                                 }

    }

	private void createAccountInDB( Account account ){
	 try {
           Connection conn1 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
            String insertTableSQL = "INSERT INTO logbook" + "(accountnumber, name,address,state,city,pincode, cashdeposit,cashwithdraw, balance) VALUES" + "(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn1.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, account.getAccNo());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setString(3, account.getAddrees());
            preparedStatement.setString(4, account.getstate());
            preparedStatement.setString(5, account.getcity());
            preparedStatement.setInt(6, account.getpincode());
            preparedStatement.setFloat(7, account.getCashDeposit());
            preparedStatement.setFloat(8, account.getcashwithdraw());
            preparedStatement.setFloat(9, account.getCashDeposit());
            preparedStatement.executeUpdate();

                Statement stmt1 = (Statement) conn1.createStatement();
            String sqlQuery="select*from logbook";
            System.out.println("accountnumber\t\tname\t\taddress\t\tstate\t\tcity \t\tpincode\t\tcashdeposit\t\tcashwithdraw\t\tbalance");
             System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ResultSet rs1 = stmt1.executeQuery(sqlQuery);

             while (rs1.next()) {
                 int accountnumber1 = rs1.getInt("accountnumber");
                  String name1 = rs1.getString("name");
                  String address1 = rs1.getString("address");
                  String state1 = rs1.getString("state");
                    String city1 = rs1.getString("city");
                    int pincode1 = rs1.getInt("pincode");
                    int cashdeposit1=rs1.getInt("cashdeposit");
                      int cashwithdraw1=rs1.getInt("cashwithdraw");
                    int Balance2=rs1.getInt("balance");
          System.out.format("%s, %s, %s, %s, %s, %s\n", accountnumber1, name1,address1,state1,city1,pincode1,cashdeposit1,cashwithdraw1,Balance2 );
    }
             
            Connection conn2 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
             String insertTransacationTable = "INSERT INTO banking.transactiondetails" + "(AccNum,cahdeposit,cashwithdraw, balance,Date1) VALUES" + "(?,?,?,?,())";
            PreparedStatement preparedStatement1 = conn2.prepareStatement(insertTransacationTable);
            preparedStatement1.setInt(1, account.getAccNo());
            preparedStatement1.setFloat(2,account.getCashDeposit() );
            preparedStatement1.setFloat(3,account.getcashwithdraw() );
            preparedStatement1.setFloat(4,  account.getCashDeposit());
preparedStatement1.setDate(5, account.getDate());
            preparedStatement1.executeUpdate(insertTransacationTable);
        
             } catch (SQLException ex) {

        }

}


	public void deposit() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("enter your account number :");
            int id = Integer.parseInt(br.readLine());
            System.out.println("enter your ammount to deposit::");
            float cashdepositamount1 = Float.parseFloat(br.readLine());
              {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                        } catch (ClassNotFoundException e) {
                            System.out.println("Where is your MySQL JDBC Driver?");
                            e.printStackTrace();
                            return;
                        }

                        try {
                            Connection conn1 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
                            conn1.setAutoCommit(false);
                            System.out.println("Creating statement...");

            sql = ("SELECT balance FROM banking.logbook where accountnumber = ? ");
            PreparedStatement preparedSelect = (PreparedStatement) conn1.prepareStatement(sql);
            preparedSelect.setInt(1, id);
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {
                int Balance5 = rs1.getInt("balance");


          
                            PreparedStatement ps = conn1.prepareStatement("update banking.logbook set cashdeposit= ?,balance=? where accountnumber=?");
                            while (true) {
                                try {
                                    if (cashdepositamount1 > 0) {
                                        bal += cashdepositamount1;
                                    } else {
                                        throw new MyException("INVALID AMOUNT EXCEPTION");
                                    }
                                } catch (MyException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("TRANSACTION FAILURE");
                                }
                                ps.setFloat(1, cashdepositamount1);
                                ps.setFloat(2, cashdepositamount1+Balance5);
                                ps.setInt(3, id);
                                int i = ps.executeUpdate();
                                System.out.println(i + " records affected");
                                System.out.println("commit/rollback");
                                String answer = br.readLine();
                                if (answer.equals("commit")) {
                                    conn1.commit();
                                }
                                if (answer.equals("rollback")) {
                                    conn1.rollback();
                                }
                                System.out.println("Want to add more records y/n");
                                String ans = br.readLine();
                                if (ans.equals("n")) {
                                    break;
                                }
                            }
                            conn1.commit();
                            System.out.println("record successfully saved");
                            conn1.close();

                                 sql = ("SELECT balance FROM banking.logbook where accountnumber = ? ");

                Connection conn2 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
                String insertTransacationTable = "INSERT INTO banking.transactiondetails" + "(AccNum,  cahdeposit,cashwithdraw, balance,Date1) VALUES" + "(?,?,?,?,curdate())";
                PreparedStatement preparedStatement1 = conn2.prepareStatement(insertTransacationTable);
                preparedStatement1.setInt(1, id);
                preparedStatement1.setFloat(2, cashdepositamount1);
                preparedStatement1.setFloat(3, cashwithdraw);
                preparedStatement1.setFloat(4, cashdepositamount1+Balance5);
                preparedStatement1.executeUpdate();
                }
                        } catch (SQLException ex) {
                     System.out.println("thankyou");
                } catch (IOException ex) {
                             System.out.println("thankyou");
                        }

                    
                    }  
            }
        catch (IOException ex) {
            System.out.println("thankyou");
        }    }

public void withdraw() {
     try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("enter your account number :");
            int id = Integer.parseInt(br.readLine());
            System.out.println("Enter the amount to be withdrawn :");
            float cashwithdraw1 = Float.parseFloat(br.readLine());
          
              try {
                            Class.forName("com.mysql.jdbc.Driver");
                        } catch (ClassNotFoundException e) {
                            System.out.println("Where is your MySQL JDBC Driver?");
                            e.printStackTrace();
                            return;
                        }
              
                    Connection conn2 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
                    conn2.setAutoCommit(false);
                   Connection conn1 = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
                    System.out.println("Creating statement...");
                    conn1.setAutoCommit(false);
                    sql = ("SELECT balance FROM banking.logbook where accountnumber = ? ");
                    PreparedStatement preparedSelect = (PreparedStatement) conn1.prepareStatement(sql);
                    preparedSelect.setInt(1, id);
                    ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
                    while (rs1.next()) {
                    int Balance6= rs1.getInt("balance");
                    PreparedStatement ps = conn2.prepareStatement("update logbook set cashwithdraw = ?, balance = cashwithdraw-balance where accountnumber = ?");
                    while (true) {
                    if (cashwithdraw1 <= 0) {
                    System.out.println("Invalid Amount Error.");
                    System.exit(0);
                    }
                
                    ps.setFloat(1, cashwithdraw1);
                    ps.setInt(2, id);
                    int i = ps.executeUpdate();
                    System.out.println(i + " records affected");
                    System.out.println("commit/rollback");
                    String answer = br.readLine();
                    if (answer.equals("commit")) {
                    conn2.commit();
                    }
                    if (answer.equals("rollback")) {
                    conn2.rollback();
                    }
                    System.out.println("Want to add more records y/n");
                    String ans = br.readLine();
                    if (ans.equals("n")) {
                    break;
                    }
                    }
                    conn2.commit();
                    System.out.println("record successfully saved");
                    conn2.close();


                String insertTransacationTable = "INSERT INTO banking.transactiondetails" + "(AccNum,  cahdeposit,cashwithdraw, balance,Date1) VALUES" + "(?,?,?,?,curdate())";
                PreparedStatement preparedStatement1 = conn2.prepareStatement(insertTransacationTable);
                preparedStatement1.setInt(1, id);
                preparedStatement1.setFloat(2, cashdepositamount);
                preparedStatement1.setFloat(3, cashwithdraw1);
                preparedStatement1.setFloat(4,cashwithdraw1 - Balance6);
                preparedStatement1.executeUpdate();
                    }
            }
        catch (SQLException ex) {
            System.out.println("thankyou");
        }        catch (IOException ex) {
            System.out.println("thankyou");
        }
                        }


    private void lasttransaction() {
        try {
             System.out.println("Enter your accont number");
            account.setAccNum(in.nextInt());
            Connection conn1 = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Banking?useSSL=false","root", "viki");
            System.out.println("Creating statement...");
            conn1.setAutoCommit(false);
            sql = ("select * from banking.transactiondetails where AccNum = ? order by AccNum limit 10");
            PreparedStatement preparedSelect = (PreparedStatement) conn1.prepareStatement(sql);
            preparedSelect.setInt(1, account.getAccNum());
            ResultSet rs1 = (ResultSet) preparedSelect.executeQuery();
            while (rs1.next()) {

                int accountnumber1 = rs1.getInt("AccNum");
                int cashdeposit1=rs1.getInt("cahdeposit");
                int cashwithdraw1=rs1.getInt("cashwithdraw");
                int balance1=rs1.getInt("balance");
                Date date1=rs1.getDate("Date1");


                System.out.format("%s, %s, %s, %s, %s, %s\n", accountnumber1, cashdeposit1, cashwithdraw1, balance1, date1);
            }


        } catch (SQLException ex) {
        System.out.println("sql error");
        }




        }

}