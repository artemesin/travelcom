package common.solutions.repo.jdbc;

import java.sql.PreparedStatement;

public interface PreparedStatementIdentityFunc {
    PreparedStatement applyParamsAndGet(PreparedStatement ps) throws Exception;
}
