package cn.baidu.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDao {
	
	public static void getEmp(Long deptno){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.prepareStatement("select * from emp where deptno=? ");
			stmt.setLong(1, deptno);
			rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getLong("empno")+":"+rs.getString("ename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(rs, stmt, conn);
		}
	}
	
	public static void main(String[] args) {
		getEmp(10L);
	}
}
