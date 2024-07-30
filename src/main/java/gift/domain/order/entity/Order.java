package gift.domain.order.entity;

import gift.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();;

    @Column
    private String recipientMessage;

    @Column(nullable = false)
    private int totalPrice;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime orderDateTime;

    protected Order() {
    }

    public Order(Long id, User user, String recipientMessage, int totalPrice) {
        this.id = id;
        this.user = user;
        this.recipientMessage = recipientMessage;
        this.totalPrice = totalPrice;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getRecipientMessage() {
        return recipientMessage;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }
}
