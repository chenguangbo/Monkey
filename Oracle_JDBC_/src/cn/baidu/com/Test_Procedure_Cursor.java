package cn.baidu.com;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleCallableStatement;
import org.junit.Test;

import oracle.jdbc.OracleTypes;
public class Test_Procedure_Cursor {

	@Test
	public void Procedure_Cursor(){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.prepareCall("{call p4(?, ?)}");
			stmt.setObject(1, 10);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			rs = ((OracleCallableStatement)stmt).getCursor(2);
			while(rs.next()){
				System.out.println(rs.getObject(2)+":"+rs.getObject(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(rs, stmt, conn);
		}
	}
}
