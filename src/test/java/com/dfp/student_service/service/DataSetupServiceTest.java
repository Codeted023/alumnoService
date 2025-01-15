package com.dfp.student_service.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;

import reactor.core.publisher.Mono;

class DataSetupServiceTest {
    @InjectMocks
    private DataSetupService dataSetupService;

    @Mock
    private R2dbcEntityTemplate entityTemplate;

    @Mock
    private Resource initSql;

    @Mock
    private DatabaseClient databaseClient;

    @Mock
    private DatabaseClient.GenericExecuteSpec executeSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityTemplate.getDatabaseClient()).thenReturn(databaseClient);
    }

    @Test
    void testRun() throws Exception {

        String sqlQuery = "CREATE TABLE alumno (id INT PRIMARY KEY, nombre VARCHAR(50));";
        when(initSql.getInputStream()).thenReturn(new ByteArrayInputStream(sqlQuery.getBytes(StandardCharsets.UTF_8)));
        when(databaseClient.sql(sqlQuery)).thenReturn(executeSpec);
        when(executeSpec.then()).thenReturn(Mono.empty());


        dataSetupService.run();


        verify(initSql, times(1)).getInputStream();
        verify(databaseClient, times(1)).sql(sqlQuery);
        verify(executeSpec, times(1)).then();
    }
}
