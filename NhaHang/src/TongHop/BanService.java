package TongHop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class BanService extends SQLService {
	public Vector<Ban> DanhSachBan()
	{
		Vector<Ban>dsBan= new Vector<Ban>();
		try{
			String sql=" select * from Ban";
			PreparedStatement prepare = conn.prepareStatement(sql);
			ResultSet result=prepare.executeQuery();
			while(result.next())
			{
				Ban A =new Ban();
				A.setMaBan(result.getString(1));
				A.setTenBan(result.getString(2));
				A.setStatus(result.getInt(3));
				dsBan.add(A);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsBan;
	}
}
