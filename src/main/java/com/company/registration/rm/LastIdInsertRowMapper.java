package com.company.registration.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
public class LastIdInsertRowMapper implements  RowMapper < String > {


	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		//LastInsertId lastInsertId = new LastInsertId();
		String lastInsertedRow = rs.getString("LAST_INSERT_ID()");
		//lastInsertId.setLastInsert(rs.getString("LAST_INSERT_ID()"));     
        return lastInsertedRow;
	}
}