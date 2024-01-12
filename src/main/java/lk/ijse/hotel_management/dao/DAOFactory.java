package lk.ijse.hotel_management.dao;

import lk.ijse.hotel_management.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory ;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory =new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        BOOKING,CUSTOMER,EMPLOYEE,PAYMENT,RESERVATION,ROOMCATEGORY,ROOM,USER;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case BOOKING:
                return new BookingDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case ROOMCATEGORY:
                return new RoomCategoryDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }


}
