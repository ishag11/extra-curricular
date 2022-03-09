package com.project.extracurricularservice.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SessionImplementor;

import java.io.Serializable;
import java.sql.*;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class ExtracurricularIdGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {

        String prefix = "EXT";
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(extracurricular_id) as Id from tbl_extracurricular");

            if(rs.next())
            {
                int id=rs.getInt(1)+101;
                String generatedId = prefix + new Long(id).toString();
                return generatedId;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
