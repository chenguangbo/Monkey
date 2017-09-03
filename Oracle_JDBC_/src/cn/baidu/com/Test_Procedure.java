package cn.baidu.com;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import oracle.jdbc.OracleTypes;

public class Test_Procedure {

	/*
	 * CREATE OR REPLACE PROCEDURE p3(eno IN emp.empno%TYPE ,ysal OUT NUMBER) IS
	 * s emp.sal%TYPE; c emp.comm%TYPE; BEGIN SELECT e.sal,NVL(e.comm,0) INTO s
	 * ,c FROM emp e WHERE e.empno = eno; ysal:=s*12+c; END;
	 */
	@Test
	public void getEmp() {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.prepareCall("{call p3(?, ?)}"); // 调用方法不同
			stmt.setLong(1, 7369);
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.execute();
			System.out.println(stmt.getObject(2));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(rs, stmt, conn);
		}
	}
}
