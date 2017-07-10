/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package banking;

import java.sql.Date;

public class Account {

	private int accNo;
	private String name;
	private float cashDeposit;
	private float balance;
    private String city;
    private String address;
    private int mobilenumber;
 private String state;
    private int Accno;
    private Float withdrawCashDeposit;
    private String email;
    private int pincode;
    private int AccNum;
    private float CashWithdraw;
    private Date date;
    float getcashwithdraw;


 public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
     public int getAccNum() {
		return AccNum;
	}

	public void setAccNum(int AccNum) {
		this.AccNum = AccNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(float cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getAddrees() {
		return address;
	}
    	public String getAddress() {
		return address;
	}

    float getCashWithdraw() {
       return CashWithdraw;
    }
    public void setCashWithdraw(float CashWithdraw) {
		this.CashWithdraw = CashWithdraw;
    }

    Date getDate() {
         return date;
    }

    float getcashwithdraw() {
        return CashWithdraw;
    }

    int getpincode() {
       return pincode;
    }

   

    void setAddress(String address) {
       this.address=address;
    }
	public String getemail() {
		return email;
	}



    void setemail(String email  ) {
       this.email =email ;
    }

	public String getcity() {
		return city;
	}

    void setcashDespositAccNo(int Accno) {
        this.Accno=Accno;
    }
    	public int getcashDespositAccNo() {
		return Accno;
	}

    void setcashWithdrawAccNo(int AccNo) {
        this.Accno=AccNo;
    }
    public int getcashWithdrawAccNo() {
		return Accno;
	}
       void setcashWithdraw(Float withdrawCashDeposit) {
        this.withdrawCashDeposit=withdrawCashDeposit;
    }
    public Float gettcashWithdraw() {
		return withdrawCashDeposit;
	}
    void setcity(String city) {
        this.city=city;

    }
public int getmobilenumber() {
		return mobilenumber;
	}

    void setmobilenumber(int mobilenumber) {
        this.mobilenumber=mobilenumber;
    }
	public String getstate() {
		return state;
	}

    void setpinCode(int pincode) {
        this.pincode=pincode;
    }
    void setstate(String state) {
        this.state=state;
    }

}
