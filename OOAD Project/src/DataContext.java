import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class DataContext {
	private Connection connection;
	private Statement statement;
	
	public DataContext()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apartmentsociety", "root", "root");
			this.statement = this.connection.createStatement();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
		
	public Vector<String> getFlatId() {
		try
		{
			String sql = "SELECT FlatId FROM flatinfo";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<String> FlatIdlist = new Vector<String>();
			while(rs.next())
			{
				FlatIdlist.add(rs.getString("FlatId"));
			}
			return FlatIdlist;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
    }
	
	public Vector<String> getBillId() {
		try
		{
			String sql = "SELECT BillId FROM billsinfo";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<String> BillIdList = new Vector<String>();
			while(rs.next())
			{
				BillIdList.add(rs.getString("BillId"));
			}
			return BillIdList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
    }

	public Vector<String> getFamilyId() {
		try
		{
			String sql = "SELECT FamilyId FROM familyinfo";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<String> FamilyIdList = new Vector<String>();
			while(rs.next())
			{
				FamilyIdList.add(rs.getString("FamilyId"));
			}
			return FamilyIdList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
    }
	
	public boolean SaveFlatInfo(Flat flat)
	{
		try
		{
			String sql = "INSERT INTO flatinfo VALUES ('" + flat.getFlatId() +  "', '" + flat.getOwnerName() +  "', '" + flat.getDescription() +  "','" + flat.getFlatLocation() +  "','" + flat.getIntercomeNumber() + "','" + flat.getRentCost() +  "')";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean SaveFamilyInfo(Family family)
	{
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String joinDate = formatter.format(family.getStayOfTime());
			String sql = "INSERT INTO familyinfo VALUES (null, '" + family.getHeadOfFamily() +  "', '" + family.getNumberOfmembers() +  "','" + joinDate +  "','" + family.getFlatId() + "')";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}

	public boolean SaveMemberInfo(Member member)
	{
		try
		{
			String sql = "INSERT INTO membersinfo VALUES (null, '" + member.getName() +  "', '" + member.getEmail() +  "','" + member.getPermanentAddress() +  "','" + member.getPhone() + "','" + member.getOccupation() + "','" + member.getFamilyId() + "')";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean SaveBillInfo(Bill bill)
	{
		try
		{
			String sql = "INSERT INTO billsinfo VALUES (null, '" + bill.getElectricBill() +  "', '" + bill.getWaterBill() +  "','" + bill.getTelephoneBill() +  "','" + bill.getOthersBill() + "','" + bill.getMonth() + "','" + bill.getYear() + "','" + bill.getFlatId() + "')";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			return false;
		}		
	}
		
	public boolean SaveAdminInfo(String UName, String UPassword)
	{
		try
		{
			String sql = "INSERT INTO adminlogin VALUES ('" + UName +  "', '" + UPassword + "')";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "This username already exists on the system.Please try again.","ERROR", JOptionPane.ERROR_MESSAGE);
			//ex.printStackTrace();
			return false;
		}		
	}
	
	public Vector<Vector<String>> getLoginInfo(String username, String password)
	{
		try
		{
			String sql = ("SELECT * FROM AdminLogin WHERE UserName LIKE '" + username + "' AND Password LIKE '" + password + "'");
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> loginList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> log = new Vector<String>();
				log.add(rs.getString("UserName"));
				log.add(rs.getString("Password"));
				loginList.add(log);
			}
			return loginList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getBillListAsString()
	{
		try
		{
			String sql = "SELECT * FROM billsinfo ORDER BY BillId ASC";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> BillList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("BillId"));
				cont.add(rs.getString("ElectricBill"));
				cont.add(rs.getString("WaterBill"));
				cont.add(rs.getString("TelephoneBill"));
				cont.add(rs.getString("OthersBill"));
				cont.add(rs.getString("Month"));
				cont.add(rs.getString("Year"));
				cont.add(rs.getString("FlatId"));
				BillList.add(cont);
			}
			return BillList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getMemberListAsString()
	{
		try
		{
			String sql = "SELECT * FROM membersinfo ORDER BY MemberId ASC";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> MemberList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("MemberId"));
				cont.add(rs.getString("Name"));
				cont.add(rs.getString("Email"));
				cont.add(rs.getString("PermanentAddress"));
				cont.add(rs.getString("Phone"));
				cont.add(rs.getString("Occupation"));
				cont.add(rs.getString("FamilyId"));
				MemberList.add(cont);
			}
			return MemberList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getFlatListAsString()
	{
		try
		{
			String sql = "SELECT * FROM FlatInfo ORDER BY FlatId ASC";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> FlatList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("FlatId"));
				cont.add(rs.getString("OwnerName"));
				cont.add(rs.getString("Description"));
				cont.add(rs.getString("FlatLocation"));
				cont.add(rs.getString("IntercomeNumber"));
				cont.add(rs.getString("RentCost"));
				FlatList.add(cont);
			}
			return FlatList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getFamilyListAsString()
	{
		try
		{
			String sql = "SELECT * FROM familyinfo ORDER BY FamilyId ASC";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> familyList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("FamilyId"));
				cont.add(rs.getString("HeadofFamily"));
				cont.add(rs.getString("NumberofMembers"));
				cont.add(rs.getString("StayofTime"));
				cont.add(rs.getString("FlatId"));
				familyList.add(cont);
			}
			return familyList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getAnyFlatBillAsString()
	{
		try
		{
			String sql = "SELECT * FROM flatallbills ORDER BY FBillId ASC";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> FlatALLBillList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("FBillId"));
				cont.add(rs.getString("FElectricBill"));
				cont.add(rs.getString("FWaterBill"));
				cont.add(rs.getString("FTelephoneBill"));
				cont.add(rs.getString("FOthersBill"));
				cont.add(rs.getString("BillMonth"));
				FlatALLBillList.add(cont);
			}
			return FlatALLBillList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public boolean Updateflatinfo(Flat flat,String UflatId,String UownerName,String Udescription,String UflatLocation,String UintercomeNumber,String UrentCost)
	{
		try
		{
			String sql = ("UPDATE flatinfo SET FlatId = '" + UflatId + "', OwnerName = '" + UownerName + "', Description = '" + Udescription + "', FlatLocation = '" + UflatLocation + "', IntercomeNumber = '" + UintercomeNumber + "', RentCost = '" + UrentCost + "' where FlatId LIKE '" + flat.getFlatId() + "' AND OwnerName LIKE '" + flat.getOwnerName() + "' AND Description like '" +flat.getDescription() + "' and FlatLocation LIKE '" + flat.getFlatLocation() + "' and IntercomeNumber LIKE '" + flat.getIntercomeNumber() + "' and RentCost LIKE '" + flat.getRentCost() + "'");
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean DeleteFlat(String flatId,String ownerName,String description,String flatLocation,String intercomeNumber,String rentCost)
	{
		try
		{
			String sql =  "DELETE FROM flatinfo where FlatId like '" + flatId + "' AND OwnerName like '" + ownerName + "' AND Description like '" + description + "' AND FlatLocation like '" + flatLocation + "' AND IntercomeNumber like '" + intercomeNumber + "' AND RentCost like '" + rentCost + "'";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean UpdateFamilyInfo(Family family,String UheadOfFamily,String UnumberOfmembers,String UflatId)
	{
		try
		{
			String sql = ("UPDATE familyinfo SET HeadofFamily = '" + UheadOfFamily + "', NumberofMembers = '" + UnumberOfmembers + "',FlatId = '" + UflatId + "' where HeadofFamily LIKE '" + family.getHeadOfFamily() + "' AND NumberofMembers LIKE '" + family.getNumberOfmembers() + "' AND FlatId LIKE '" + family.getFlatId() + "'");
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean DeleteFamily(String familyId, String headOfFamily,String numberOfMembers,String stayOfTime,String flatId)
	{
		try
		{
			String sql =  "DELETE FROM familyinfo where FamilyId like '" + familyId + "' AND HeadofFamily like '" + headOfFamily + "' AND NumberofMembers like '" + numberOfMembers + "' AND StayofTime like '" + stayOfTime + "' AND FlatId like '" + flatId + "'";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean UpdateBillInfo(Bill bill, String UelectricBill,String UwaterBill,String UtelephoneBill,String UothersBill,String Umonth,String Uyear,String UflatId)
	{
		try
		{
			String sql = ("UPDATE billsinfo SET ElectricBill = '" + UelectricBill + "', WaterBill = '" + UwaterBill + "', TelephoneBill = '" + UtelephoneBill + "', OthersBill = '" + UothersBill + "', Month = '" + Umonth + "', Year = '" + Uyear + "', FlatId = '" + UflatId + "' where ElectricBill like '" + bill.getElectricBill() + "' AND WaterBill like '" + bill.getWaterBill() + "' AND TelephoneBill like '" + bill.getTelephoneBill() + "' AND OthersBill like '" + bill.getOthersBill() + "' AND Month like '" + bill.getMonth() + "' AND Year like '" + bill.getYear() + "' AND FlatId like '" + bill.getFlatId() + "'");
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean UpdateMemberInfo(Member member,String Uname,String Uemail,String UpermanentAddress,String Uphone,String Uoccupation,String UfamilyId)
	{
		try
		{
			String sql = ("UPDATE membersinfo SET Name = '" + Uname + "', Email = '" + Uemail + "', PermanentAddress = '" + UpermanentAddress + "', Phone = '" + Uphone + "', Occupation = '" + Uoccupation + "', FamilyId = '" + UfamilyId + "' where Name like '" + member.getName() + "' AND Email like '" + member.getEmail() + "' AND PermanentAddress like '" + member.getPermanentAddress() + "' AND Phone like '" + member.getPhone() + "' AND Occupation like '" + member.getOccupation() + "' AND FamilyId like '" + member.getFamilyId() + "'");
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean DeleteBill(String billId,String electricBill,String waterBill,String telephoneBill,String othersBill,String month,String year,String flatId)
	{
		try
		{
			String sql =  "DELETE FROM billsinfo where BillId like '" + billId + "' AND ElectricBill like '" + electricBill + "' AND WaterBill like '" + waterBill + "' AND TelephoneBill like '" + telephoneBill + "' AND OthersBill like '" + othersBill + "' AND Month like '" + month + "' AND Year like '" + year + "' AND FlatId like '" + flatId + "'";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	public boolean DeleteMember(String memberId,String name,String email,String permanentAddress,String phone,String occupation,String familyId)
	{
		try
		{
			String sql =  "DELETE FROM membersinfo where MemberId like '" + memberId + "' AND Name like '" + name + "' AND Email like '" + email + "' AND PermanentAddress like '" + permanentAddress + "' AND Phone like '" + phone + "' AND Occupation like '" + occupation + "' AND FamilyId like '" + familyId + "'";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}		
	}
	
	public Vector<Vector<String>> getSearchBillAsString(String flatId,String month,String year)
	{
		try
		{
			String sql = "SELECT *,ElectricBill+WaterBill+TelephoneBill+OthersBill AS TotalMonthlyBill FROM billsinfo WHERE  Month='" + month + "' AND Year='" + year + "' AND FlatId='" + flatId + "'";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> BillList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("BillId"));
				cont.add(rs.getString("ElectricBill"));
				cont.add(rs.getString("WaterBill"));
				cont.add(rs.getString("TelephoneBill"));
				cont.add(rs.getString("OthersBill"));
				cont.add(rs.getString("Month"));
				cont.add(rs.getString("Year"));
				cont.add(rs.getString("FlatId"));
				cont.add(rs.getString("TotalMonthlyBill"));
				BillList.add(cont);
			}
			return BillList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean CreateFlatVeiw(String sflatId,String syear)
	{
		try
		{
			String sql = "CREATE OR REPLACE ALGORITHM = UNDEFINED VIEW FlatAllBills (FBillId,FElectricBill,FWaterBill,FTelephoneBill,FOthersBill,BillMonth) AS SELECT BillId,ElectricBill , WaterBill , TelephoneBill , OthersBill , Month FROM billsinfo WHERE FlatId LIKE '" + sflatId + "' AND Year LIKE '" + syear + "' ORDER BY BillId ASC";
			this.statement.execute(sql);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	
	public Vector<Vector<String>> getSearchMemberAsString(String sName,String sOccupation)
	{
		try
		{
			String sql = "SELECT Name,Email,Phone,Occupation,FlatId FROM membersinfo mem join familyinfo fam on(mem.FamilyId=fam.FamilyId) WHERE  Name like '%" + sName + "%' OR Occupation like '%" + sOccupation + "%'";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> MemberList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("Name"));
				cont.add(rs.getString("Email"));
				cont.add(rs.getString("Phone"));
				cont.add(rs.getString("Occupation"));
				cont.add(rs.getString("FlatId"));
				MemberList.add(cont);
			}
			return MemberList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	public Vector<Vector<String>> getDetailsMemberInfoAsString()
	{
		try
		{
			String sql = "SELECT MemberId,Name,Email,PermanentAddress,Phone,Occupation,FlatId FROM membersinfo mem join familyinfo fam on(mem.FamilyId=fam.FamilyId) ORDER BY MemberId ASC";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> DetailsMemberList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("MemberId"));
				cont.add(rs.getString("Name"));
				cont.add(rs.getString("Email"));
				cont.add(rs.getString("PermanentAddress"));
				cont.add(rs.getString("Phone"));
				cont.add(rs.getString("Occupation"));
				cont.add(rs.getString("FlatId"));
				DetailsMemberList.add(cont);
			}
			return DetailsMemberList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getSearchFlatAsString(String sflatId)
	{
		try
		{
			String sql = "SELECT * FROM FlatInfo WHERE  FlatId='" + sflatId + "'";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> FlatList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("FlatId"));
				cont.add(rs.getString("OwnerName"));
				cont.add(rs.getString("Description"));
				cont.add(rs.getString("FlatLocation"));
				cont.add(rs.getString("IntercomeNumber"));
				cont.add(rs.getString("RentCost"));
				FlatList.add(cont);
			}
			return FlatList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getSearchMemberByName(String sName)
	{
		try
		{
			String sql = "SELECT Name,Email,Phone,Occupation,FlatId FROM membersinfo mem join familyinfo fam on(mem.FamilyId=fam.FamilyId) WHERE  Name like '%" + sName + "%'";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> MemberList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("Name"));
				cont.add(rs.getString("Email"));
				cont.add(rs.getString("Phone"));
				cont.add(rs.getString("Occupation"));
				cont.add(rs.getString("FlatId"));
				MemberList.add(cont);
			}
			return MemberList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	public Vector<Vector<String>> getSearchMemberByOccupation(String sOccupation)
	{
		try
		{
			String sql = "SELECT Name,Email,Phone,Occupation,FlatId FROM membersinfo mem join familyinfo fam on(mem.FamilyId=fam.FamilyId) WHERE Occupation like '%" + sOccupation + "%'";
			ResultSet rs = this.statement.executeQuery(sql);
			Vector<Vector<String>> MemberList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> cont = new Vector<String>();
				cont.add(rs.getString("Name"));
				cont.add(rs.getString("Email"));
				cont.add(rs.getString("Phone"));
				cont.add(rs.getString("Occupation"));
				cont.add(rs.getString("FlatId"));
				MemberList.add(cont);
			}
			return MemberList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}
