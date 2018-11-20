package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class TicketProcessing extends BaseEntity {
    @Id
    @GeneratedValue
    private Long ticketProcessingId;

    private Long ticketId;

    private String title;

    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketId", insertable = false, updatable = false)
    private SupportTicket supportTicket;

    public TicketProcessing() {
    }

    public Long getTicketProcessingId() {
        return ticketProcessingId;
    }

    public void setTicketProcessingId(Long ticketProcessingId) {
        this.ticketProcessingId = ticketProcessingId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SupportTicket getSupportTicket() {
        return supportTicket;
    }

    public void setSupportTicket(SupportTicket supportTicket) {
        if(supportTicket != null){
            setTicketId(supportTicket.getTickedId());
        }
        this.supportTicket = supportTicket;
    }
}
