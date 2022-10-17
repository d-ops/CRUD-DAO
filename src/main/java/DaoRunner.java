import dao.TicketDao;
import entity.Ticket;

import java.math.BigDecimal;

public class DaoRunner {
    public static void main(String[] args) {
        TicketDao ticketDao = TicketDao.getINSTANCE();
//        Ticket ticket = saveTicket(new Ticket());
//        System.out.println(ticketDao.save(ticket));
        System.out.println(ticketDao.delete(56L));
    }

    public static Ticket saveTicket(Ticket ticket){
        ticket.setPassengerNo("1234567");
        ticket.setPassengerName("Test");
        ticket.setFlightId(3L);
        ticket.setSeatNo("B3");
        ticket.setCost(BigDecimal.TEN);
        return ticket;
    }
}
