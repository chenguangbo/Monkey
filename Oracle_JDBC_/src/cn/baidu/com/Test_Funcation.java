package cn.baidu.com;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import oracle.jdbc.OracleTypes;

public class Test_Funcation {
	/*
	 * CREATE OR REPLACE FUNCTION F1(ENO EMP.EMPNO%TYPE) RETURN NUMBER IS YEAL
	 * NUMBER(10); BEGIN SELECT E.SAL * 12 + NVL(E.COMM, 0) INTO YEAL FROM EMP E
	 * WHERE E.EMPNO = ENO; DBMS_OUTPUT.PUT_LINE(YEAL); RETURN YEAL; END;
	 */
	@Test
	public void getEmp() {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.prepareCall("{? = call f1(?)}"); // 调用方法不同
			stmt.setLong(2, 7369);
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.execute();
			System.out.println(stmt.getObject(1));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(rs, stmt, conn);
		}
	}
}
