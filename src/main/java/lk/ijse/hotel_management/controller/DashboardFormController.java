package lk.ijse.hotel_management.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import lk.ijse.hotel_management.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hotel_management.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.hotel_management.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.dto.EmployeeDto;
import lk.ijse.hotel_management.dto.RoomsDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    @FXML
    private Label txtAllCustomers;

    @FXML
    private Label txtBookRppms;

    @FXML
    private Label txtTotaRooms;

    @FXML
    private Label txtTotalRooms;

    @FXML
    private Label txtEmployee;

    @FXML
    private BarChart<String, Integer> barchart;

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    RoomDAOImpl roomDAO = new RoomDAOImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<CustomerDto> allCustomer = customerDAO.getAll();
            txtAllCustomers.setText(String.valueOf(allCustomer.size()));

            List<EmployeeDto> allEmployees = employeeDAO.getAll();
            txtEmployee.setText(String.valueOf(allEmployees.size()));

            RoomDAOImpl roomModel = new RoomDAOImpl();

            ArrayList<RoomsDto> allRooms = roomDAO.getAll();
            txtTotaRooms.setText(String.valueOf(allRooms.size()));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void reservationReportOnAction(ActionEvent actionEvent) {
    }

    public void employeesReportsOnAction(ActionEvent actionEvent) throws SQLException, JRException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/emoployee.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnetion.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
    }

    public void customerReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/customer_report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnetion.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
    }


}
