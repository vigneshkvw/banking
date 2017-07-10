/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package banking;



import java.sql.SQLException;
import java.util.Scanner;


public class BankTransaction {
	
	public static void main(String arg[]) throws MyException, SQLException {
		Scanner in = new Scanner(System.in);
		Bank b = new Bank();

		System.out.println("\n\n\t----Bank Simulator----");
		try {
			while (true) {
				System.out.println("1.Create Account\t2.existing account\t3Exit.");
				int ch = in.nextInt();
				switch (ch) {

                      case 1:
                          b.createAcc();
                          break;

               case 2:
					b.existingaccount();
					break;

			
				case 3:
					System.exit(0);
					break;

				default:
					System.out.println("Invalid Option");
				}
			}
		} catch (Exception e) {
			System.out.println("SELF THROWN EXCEPTION IS--->" + e);
		} finally {
			in.close();
		}
	}

}