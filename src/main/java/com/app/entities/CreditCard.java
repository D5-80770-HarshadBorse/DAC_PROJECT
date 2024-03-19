package com.app.entities;





import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@Table(name="credit_card")
public class CreditCard extends BaseEntity{

        @Column(name = "card_number", unique = true, length = 16)
        private Long number;

        @Column(name = "expiry")
        private Integer expiry;

        @Column(name = "cvv")
        private Integer cvc;
        
        @Column(name = "card_holder_name")
        private String name;

}

