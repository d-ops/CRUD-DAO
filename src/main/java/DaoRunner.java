import dao.TicketDao;
import dto.TicketFilter;
import entity.Flight;
import entity.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {

        Optional<Ticket> ticket = TicketDao.getINSTANCE().findById(5L);
        System.out.println(ticket);



//        Ticket maybeTicket = saveTicket(new Ticket());
//        System.out.println(ticketDao.save(maybeTicket));

    }

    public static Ticket saveTicket(Ticket ticket) {
        ticket.setPassengerNo("1234567");
        ticket.setPassengerName("Test");
        //ticket.setFlight(3L);
        ticket.setSeatNo("B3");
        ticket.setCost(BigDecimal.TEN);
        return ticket;
    }

    private static void filterTicket() {
        TicketFilter ticketFilter = new TicketFilter(10, 2, null, null);
        List<Ticket> tickets = TicketDao.getINSTANCE().findAll(ticketFilter);
        System.out.println(tickets);
    }

    private static void findAll() {
        List<Ticket> tickets = TicketDao.getINSTANCE().findAll();
        System.out.println(tickets);
    }

    private static void findByIdAndUpdate() {
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
    }

    private static void deleteTicket() {
        TicketDao ticketDao = TicketDao.getINSTANCE();
        System.out.println(ticketDao.delete(56L));
    }
}
