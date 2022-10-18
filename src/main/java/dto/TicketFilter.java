package dto;

public class TicketFilter {
    private Integer limit;
    private Integer offset;
    private String seatNo;
    private String passengerName;

    @Override
    public String toString() {
        return "TicketFilter{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", passengerNo='" + seatNo + '\'' +
                ", passengerName='" + passengerName + '\'' +
                '}';
    }

    public TicketFilter(int limit, int offset, String passengerNo, String passengerName) {
        this.limit = limit;
        this.offset = offset;
        this.seatNo = passengerNo;
        this.passengerName = passengerName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
}
