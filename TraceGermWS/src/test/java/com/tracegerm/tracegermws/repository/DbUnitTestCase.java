/**
 * 
 */
package com.tracegerm.tracegermws.repository;

import java.io.FileInputStream;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author askos
 *
 */
@ActiveProfiles("test")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DbUnitTestCase extends DBTestCase{

	@Autowired
    private DataSource dataSource;
	
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder()
		.build(new FileInputStream("src/test/resources/dbUnit/DbUnitDataset.xml"));
	}

	@Before
    public void setUp() throws Exception {
        IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
    }

    @After
    public void restoreDB() {
        //DatabaseOperation
    }
}
