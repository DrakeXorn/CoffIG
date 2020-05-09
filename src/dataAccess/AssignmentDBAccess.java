package dataAccess;

import model.Assignment;
import model.Service;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;
import model.exceptions.TimeException;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class AssignmentDBAccess implements AssignmentDataAccess {
    @Override
    public ArrayList<Assignment> searchAssignments(String identity, GregorianCalendar startDate, GregorianCalendar endDate) throws AllDataException, ConnectionException, TimeException {
        ArrayList<Assignment> assignments = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();
            Hashtable<Integer, Service> services = new Hashtable<>();
            String sqlInstruction = "select * from service" +
                    " left join assignment a on service.service_id = a.service_id" +
                    " join employee e on a.employee_id = e.employee_id" +
                    " join user u on e.employee_id = u.user_id" +
                    " where u.first_name = ? and u.last_name = ?" +
                    " and a.date between ? and ?" +
                    " order by a.date";
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            ResultSet data;

            statement.setString(1, identity.split("\\s")[0]);
            statement.setString(2, identity.split("\\s")[1]);
            statement.setDate(3, new Date(startDate.getTimeInMillis()));
            statement.setDate(4, new Date(endDate.getTimeInMillis()));
            data = statement.executeQuery();

            while (data.next()) {
                int serviceId = data.getInt("service_id");
                GregorianCalendar date = new GregorianCalendar();
                Assignment assignment;

                if (!services.containsKey(serviceId))
                    services.put(serviceId, new Service(data.getTime("start_time").toLocalTime(), data.getTime("end_time").toLocalTime()));
                date.setTime(data.getDate("date"));
                assignments.add(new Assignment(services.get(serviceId), date));
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "services");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return assignments;
    }
}
