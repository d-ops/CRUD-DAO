import dao.TicketDao;
import dto.TicketFilter;
import entity.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
        TicketFilter ticketFilter = new TicketFilter(10, 2, null, null);
        List<Ticket> tickets = TicketDao.getINSTANCE().findAll(ticketFilter);
        System.out.println(tickets);
//        List<Ticket> tickets = TicketDao.getINSTANCE().findAll();
//        System.out.println(tickets);
        TicketDao ticketDao = TicketDao.getINSTANCE();
        Optional<Ticket> maybeTicket = ticketDao.findById(3L);
        System.out.println(maybeTicket);
        maybeTicket.ifPresent(
                ticket -> {
                    ticket.setCost(BigDecimal.valueOf(188.88));
                    ticketDao.update(ticket);
                }
        );
        System.out.println(ticketDao.findById(3L));
//        Ticket maybeTicket = saveTicket(new Ticket());
//        System.out.println(ticketDao.save(maybeTicket));

//        System.out.println(ticketDao.delete(56L));
    }

    public static Ticket saveTicket(Ticket ticket) {
        ticket.setPassengerNo("1234567");
        ticket.setPassengerName("Test");
        ticket.setFlightId(3L);
        ticket.setSeatNo("B3");
        ticket.setCost(BigDecimal.TEN);
        return ticket;
    }
}
