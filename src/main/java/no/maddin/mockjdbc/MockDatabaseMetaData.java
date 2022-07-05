package no.maddin.mockjdbc;

import java.sql.*;

/**
 * @author Han at 2022/7/5.
 * email:   lynn.jiang@ximalaya.com
 */
public class MockDatabaseMetaData implements DatabaseMetaData {

    private String url;

    public MockDatabaseMetaData(String url) {
        this.url = url;
    }

    @Override
    public boolean allProceduresAreCallable() throws SQLException {
        return false;
    }

    @Override
    public boolean allTablesAreSelectable() throws SQLException {
        return true;
    }

    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public String getURL() throws SQLException {
        return url;
    }

    @Override
    public String getUserName() throws SQLException {
        return "";
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return true;
    }

    @Override
    public boolean nullsAreSortedHigh() throws SQLException {
        return false;
    }

    @Override
    public boolean nullsAreSortedLow() throws SQLException {
        return true;
    }

    @Override
    public boolean nullsAreSortedAtStart() throws SQLException {
        return true;
    }

    @Override
    public boolean nullsAreSortedAtEnd() throws SQLException {
        return false;
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        return "Mock Database";

    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return "0.1";
    }

    @Override
    public String getDriverName() throws SQLException {
        return "MockDriver";

    }

    @Override
    public String getDriverVersion() throws SQLException {
        return "0.1";

    }

    @Override
    public int getDriverMajorVersion() {
        return 1;

    }

    @Override
    public int getDriverMinorVersion() {
        return 0;
    }

    @Override
    public boolean usesLocalFiles() throws SQLException {
        return true;
    }

    @Override
    public boolean usesLocalFilePerTable() throws SQLException {
        return true;
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        return false;

    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws SQLException {
        throw new LogUnsupportedOperationException("storesUpperCaseIdentifiers");

    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws SQLException {
        throw new LogUnsupportedOperationException("storesLowerCaseIdentifiers");

    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws SQLException {
        throw new LogUnsupportedOperationException("storesMixedCaseIdentifiers");

    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        throw new LogUnsupportedOperationException("supportsMixedCaseQuotedIdentifiers");

    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        throw new LogUnsupportedOperationException("storesUpperCaseQuotedIdentifiers");

    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        throw new LogUnsupportedOperationException("storesLowerCaseQuotedIdentifiers");

    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        throw new LogUnsupportedOperationException("storesMixedCaseQuotedIdentifiers");

    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {
        throw new LogUnsupportedOperationException("getIdentifierQuoteString");

    }

    @Override
    public String getSQLKeywords() throws SQLException {
        throw new LogUnsupportedOperationException("getSQLKeywords");

    }

    @Override
    public String getNumericFunctions() throws SQLException {
        throw new LogUnsupportedOperationException("getNumericFunctions");

    }

    @Override
    public String getStringFunctions() throws SQLException {
        throw new LogUnsupportedOperationException("getStringFunctions");

    }

    @Override
    public String getSystemFunctions() throws SQLException {
        throw new LogUnsupportedOperationException("getSystemFunctions");

    }

    @Override
    public String getTimeDateFunctions() throws SQLException {
        throw new LogUnsupportedOperationException("getTimeDateFunctions");

    }

    @Override
    public String getSearchStringEscape() throws SQLException {
        throw new LogUnsupportedOperationException("getSearchStringEscape");

    }

    @Override
    public String getExtraNameCharacters() throws SQLException {
        throw new LogUnsupportedOperationException("getExtraNameCharacters");

    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        throw new LogUnsupportedOperationException("supportsAlterTableWithAddColumn");

    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        throw new LogUnsupportedOperationException("supportsAlterTableWithDropColumn");

    }

    @Override
    public boolean supportsColumnAliasing() throws SQLException {
        throw new LogUnsupportedOperationException("supportsColumnAliasing");

    }

    @Override
    public boolean nullPlusNonNullIsNull() throws SQLException {
        throw new LogUnsupportedOperationException("nullPlusNonNullIsNull");

    }

    @Override
    public boolean supportsConvert() throws SQLException {
        throw new LogUnsupportedOperationException("supportsConvert");

    }

    @Override
    public boolean supportsConvert(int fromType, int toType) throws SQLException {
        throw new LogUnsupportedOperationException("supportsConvert");

    }

    @Override
    public boolean supportsTableCorrelationNames() throws SQLException {
        throw new LogUnsupportedOperationException("supportsTableCorrelationNames");

    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        throw new LogUnsupportedOperationException("supportsDifferentTableCorrelationNames");

    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws SQLException {
        throw new LogUnsupportedOperationException("supportsExpressionsInOrderBy");

    }

    @Override
    public boolean supportsOrderByUnrelated() throws SQLException {
        throw new LogUnsupportedOperationException("supportsOrderByUnrelated");

    }

    @Override
    public boolean supportsGroupBy() throws SQLException {
        throw new LogUnsupportedOperationException("supportsGroupBy");

    }

    @Override
    public boolean supportsGroupByUnrelated() throws SQLException {
        throw new LogUnsupportedOperationException("supportsGroupByUnrelated");

    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws SQLException {
        throw new LogUnsupportedOperationException("supportsGroupByBeyondSelect");

    }

    @Override
    public boolean supportsLikeEscapeClause() throws SQLException {
        throw new LogUnsupportedOperationException("supportsLikeEscapeClause");

    }

    @Override
    public boolean supportsMultipleResultSets() throws SQLException {
        throw new LogUnsupportedOperationException("supportsMultipleResultSets");

    }

    @Override
    public boolean supportsMultipleTransactions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsMultipleTransactions");

    }

    @Override
    public boolean supportsNonNullableColumns() throws SQLException {
        throw new LogUnsupportedOperationException("supportsNonNullableColumns");

    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws SQLException {
        throw new LogUnsupportedOperationException("supportsMinimumSQLGrammar");

    }

    @Override
    public boolean supportsCoreSQLGrammar() throws SQLException {
        throw new LogUnsupportedOperationException("supportsCoreSQLGrammar");

    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws SQLException {
        throw new LogUnsupportedOperationException("supportsExtendedSQLGrammar");

    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        throw new LogUnsupportedOperationException("supportsANSI92EntryLevelSQL");

    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        throw new LogUnsupportedOperationException("supportsANSI92IntermediateSQL");

    }

    @Override
    public boolean supportsANSI92FullSQL() throws SQLException {
        throw new LogUnsupportedOperationException("supportsANSI92FullSQL");

    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        throw new LogUnsupportedOperationException("supportsIntegrityEnhancementFacility");

    }

    @Override
    public boolean supportsOuterJoins() throws SQLException {
        throw new LogUnsupportedOperationException("supportsOuterJoins");

    }

    @Override
    public boolean supportsFullOuterJoins() throws SQLException {
        throw new LogUnsupportedOperationException("supportsFullOuterJoins");

    }

    @Override
    public boolean supportsLimitedOuterJoins() throws SQLException {
        throw new LogUnsupportedOperationException("supportsLimitedOuterJoins");

    }

    @Override
    public String getSchemaTerm() throws SQLException {
        throw new LogUnsupportedOperationException("getSchemaTerm");

    }

    @Override
    public String getProcedureTerm() throws SQLException {
        throw new LogUnsupportedOperationException("getProcedureTerm");

    }

    @Override
    public String getCatalogTerm() throws SQLException {
        throw new LogUnsupportedOperationException("getCatalogTerm");

    }

    @Override
    public boolean isCatalogAtStart() throws SQLException {
        throw new LogUnsupportedOperationException("isCatalogAtStart");

    }

    @Override
    public String getCatalogSeparator() throws SQLException {
        throw new LogUnsupportedOperationException("getCatalogSeparator");

    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSchemasInDataManipulation");

    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSchemasInProcedureCalls");

    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSchemasInTableDefinitions");

    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSchemasInIndexDefinitions");

    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSchemasInPrivilegeDefinitions");

    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        throw new LogUnsupportedOperationException("supportsCatalogsInDataManipulation");

    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        throw new LogUnsupportedOperationException("supportsCatalogsInProcedureCalls");

    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsCatalogsInTableDefinitions");

    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsCatalogsInIndexDefinitions");

    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsCatalogsInPrivilegeDefinitions");

    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {
        throw new LogUnsupportedOperationException("supportsPositionedDelete");

    }

    @Override
    public boolean supportsPositionedUpdate() throws SQLException {
        throw new LogUnsupportedOperationException("supportsPositionedUpdate");

    }

    @Override
    public boolean supportsSelectForUpdate() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSelectForUpdate");

    }

    @Override
    public boolean supportsStoredProcedures() throws SQLException {
        throw new LogUnsupportedOperationException("supportsStoredProcedures");

    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSubqueriesInComparisons");

    }

    @Override
    public boolean supportsSubqueriesInExists() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSubqueriesInExists");

    }

    @Override
    public boolean supportsSubqueriesInIns() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSubqueriesInIns");

    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSubqueriesInQuantifieds");

    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws SQLException {
        throw new LogUnsupportedOperationException("supportsCorrelatedSubqueries");

    }

    @Override
    public boolean supportsUnion() throws SQLException {
        throw new LogUnsupportedOperationException("supportsUnion");

    }

    @Override
    public boolean supportsUnionAll() throws SQLException {
        throw new LogUnsupportedOperationException("supportsUnionAll");

    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        throw new LogUnsupportedOperationException("supportsOpenCursorsAcrossCommit");

    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        throw new LogUnsupportedOperationException("supportsOpenCursorsAcrossRollback");

    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        throw new LogUnsupportedOperationException("supportsOpenStatementsAcrossCommit");

    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        throw new LogUnsupportedOperationException("supportsOpenStatementsAcrossRollback");

    }

    @Override
    public int getMaxBinaryLiteralLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxBinaryLiteralLength");

    }

    @Override
    public int getMaxCharLiteralLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxCharLiteralLength");

    }

    @Override
    public int getMaxColumnNameLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxColumnNameLength");

    }

    @Override
    public int getMaxColumnsInGroupBy() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxColumnsInGroupBy");

    }

    @Override
    public int getMaxColumnsInIndex() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxColumnsInIndex");

    }

    @Override
    public int getMaxColumnsInOrderBy() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxColumnsInOrderBy");

    }

    @Override
    public int getMaxColumnsInSelect() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxColumnsInSelect");

    }

    @Override
    public int getMaxColumnsInTable() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxColumnsInTable");

    }

    @Override
    public int getMaxConnections() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxConnections");

    }

    @Override
    public int getMaxCursorNameLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxCursorNameLength");

    }

    @Override
    public int getMaxIndexLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxIndexLength");

    }

    @Override
    public int getMaxSchemaNameLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxSchemaNameLength");

    }

    @Override
    public int getMaxProcedureNameLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxProcedureNameLength");

    }

    @Override
    public int getMaxCatalogNameLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxCatalogNameLength");

    }

    @Override
    public int getMaxRowSize() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxRowSize");

    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        throw new LogUnsupportedOperationException("doesMaxRowSizeIncludeBlobs");

    }

    @Override
    public int getMaxStatementLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxStatementLength");

    }

    @Override
    public int getMaxStatements() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxStatements");

    }

    @Override
    public int getMaxTableNameLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxTableNameLength");

    }

    @Override
    public int getMaxTablesInSelect() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxTablesInSelect");

    }

    @Override
    public int getMaxUserNameLength() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxUserNameLength");

    }

    @Override
    public int getDefaultTransactionIsolation() throws SQLException {
        throw new LogUnsupportedOperationException("getDefaultTransactionIsolation");

    }

    @Override
    public boolean supportsTransactions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsTransactions");

    }

    @Override
    public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
        throw new LogUnsupportedOperationException("supportsTransactionIsolationLevel");

    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        throw new LogUnsupportedOperationException("supportsDataDefinitionAndDataManipulationTransactions");

    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        throw new LogUnsupportedOperationException("supportsDataManipulationTransactionsOnly");

    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        throw new LogUnsupportedOperationException("dataDefinitionCausesTransactionCommit");

    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        throw new LogUnsupportedOperationException("dataDefinitionIgnoredInTransactions");

    }

    @Override
    public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getProcedures");

    }

    @Override
    public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getProcedureColumns");

    }

    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
        throw new LogUnsupportedOperationException("getTables");

    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        throw new LogUnsupportedOperationException("getSchemas");

    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        throw new LogUnsupportedOperationException("getCatalogs");

    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        throw new LogUnsupportedOperationException("getTableTypes");

    }

    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getColumns");

    }

    @Override
    public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getColumnPrivileges");

    }

    @Override
    public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getTablePrivileges");

    }

    @Override
    public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
        throw new LogUnsupportedOperationException("getBestRowIdentifier");

    }

    @Override
    public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
        throw new LogUnsupportedOperationException("getVersionColumns");

    }

    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        throw new LogUnsupportedOperationException("getPrimaryKeys");

    }

    @Override
    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
        throw new LogUnsupportedOperationException("getImportedKeys");

    }

    @Override
    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        throw new LogUnsupportedOperationException("getExportedKeys");

    }

    @Override
    public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
        throw new LogUnsupportedOperationException("getCrossReference");

    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        throw new LogUnsupportedOperationException("getTypeInfo");

    }

    @Override
    public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
        throw new LogUnsupportedOperationException("getIndexInfo");

    }

    @Override
    public boolean supportsResultSetType(int type) throws SQLException {
        throw new LogUnsupportedOperationException("supportsResultSetType");

    }

    @Override
    public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
        throw new LogUnsupportedOperationException("supportsResultSetConcurrency");

    }

    @Override
    public boolean ownUpdatesAreVisible(int type) throws SQLException {
        throw new LogUnsupportedOperationException("ownUpdatesAreVisible");

    }

    @Override
    public boolean ownDeletesAreVisible(int type) throws SQLException {
        throw new LogUnsupportedOperationException("ownDeletesAreVisible");

    }

    @Override
    public boolean ownInsertsAreVisible(int type) throws SQLException {
        throw new LogUnsupportedOperationException("ownInsertsAreVisible");

    }

    @Override
    public boolean othersUpdatesAreVisible(int type) throws SQLException {
        throw new LogUnsupportedOperationException("othersUpdatesAreVisible");

    }

    @Override
    public boolean othersDeletesAreVisible(int type) throws SQLException {
        throw new LogUnsupportedOperationException("othersDeletesAreVisible");

    }

    @Override
    public boolean othersInsertsAreVisible(int type) throws SQLException {
        throw new LogUnsupportedOperationException("othersInsertsAreVisible");

    }

    @Override
    public boolean updatesAreDetected(int type) throws SQLException {
        throw new LogUnsupportedOperationException("updatesAreDetected");

    }

    @Override
    public boolean deletesAreDetected(int type) throws SQLException {
        throw new LogUnsupportedOperationException("deletesAreDetected");

    }

    @Override
    public boolean insertsAreDetected(int type) throws SQLException {
        throw new LogUnsupportedOperationException("insertsAreDetected");

    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {
        throw new LogUnsupportedOperationException("supportsBatchUpdates");

    }

    @Override
    public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
        throw new LogUnsupportedOperationException("getUDTs");

    }

    @Override
    public Connection getConnection() throws SQLException {
        throw new LogUnsupportedOperationException("getConnection");

    }

    @Override
    public boolean supportsSavepoints() throws SQLException {
        throw new LogUnsupportedOperationException("supportsSavepoints");

    }

    @Override
    public boolean supportsNamedParameters() throws SQLException {
        throw new LogUnsupportedOperationException("supportsNamedParameters");

    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {
        throw new LogUnsupportedOperationException("supportsMultipleOpenResults");

    }

    @Override
    public boolean supportsGetGeneratedKeys() throws SQLException {
        throw new LogUnsupportedOperationException("supportsGetGeneratedKeys");

    }

    @Override
    public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getSuperTypes");

    }

    @Override
    public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getSuperTables");

    }

    @Override
    public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getAttributes");

    }

    @Override
    public boolean supportsResultSetHoldability(int holdability) throws SQLException {
        throw new LogUnsupportedOperationException("supportsResultSetHoldability");

    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        throw new LogUnsupportedOperationException("getResultSetHoldability");

    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        throw new LogUnsupportedOperationException("getDatabaseMajorVersion");

    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        throw new LogUnsupportedOperationException("getDatabaseMinorVersion");

    }

    @Override
    public int getJDBCMajorVersion() throws SQLException {
        throw new LogUnsupportedOperationException("getJDBCMajorVersion");

    }

    @Override
    public int getJDBCMinorVersion() throws SQLException {
        throw new LogUnsupportedOperationException("getJDBCMinorVersion");

    }

    @Override
    public int getSQLStateType() throws SQLException {
        throw new LogUnsupportedOperationException("getSQLStateType");

    }

    @Override
    public boolean locatorsUpdateCopy() throws SQLException {
        throw new LogUnsupportedOperationException("locatorsUpdateCopy");

    }

    @Override
    public boolean supportsStatementPooling() throws SQLException {
        throw new LogUnsupportedOperationException("supportsStatementPooling");

    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        throw new LogUnsupportedOperationException("getRowIdLifetime");

    }

    @Override
    public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
        throw new LogUnsupportedOperationException("getSchemas");

    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        throw new LogUnsupportedOperationException("supportsStoredFunctionsUsingCallSyntax");

    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        throw new LogUnsupportedOperationException("autoCommitFailureClosesAllResultSets");

    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        throw new LogUnsupportedOperationException("getClientInfoProperties");

    }

    @Override
    public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getFunctions");

    }

    @Override
    public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getFunctionColumns");

    }

    @Override
    public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        throw new LogUnsupportedOperationException("getPseudoColumns");

    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        throw new LogUnsupportedOperationException("generatedKeyAlwaysReturned");

    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new LogUnsupportedOperationException("unwrap");

    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new LogUnsupportedOperationException("isWrapperFor");

    }
}
