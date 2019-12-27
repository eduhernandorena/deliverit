package br.com.dit.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "seqInvoice", sequenceName = "INVOICE_SEQ", allocationSize = 1)
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqInvoice")
    private Long id;
    @Column
    @NotBlank(message = "O campo nome é obrigatório!")
    private String name;
    @Column
    @NotNull(message = "O campo valor é obrigatório!")
    @DecimalMin(value = "0.1", message = "O campo valor deve ser maior que zero!")
    private Double value;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "O campo data de vencimento é obrigatório!")
    private Date dueDate;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "O campo data de pagamento é obrigatório!")
    private Date paymentDate;
    @Column
    @NotNull
    @DecimalMin(value = "0.1")
    private Double correctedValue;
    @Column
    @NotNull
    private Integer daysLate;
    @Column
    @NotNull
    @DecimalMin(value = "0.0")
    private Double penalty;
    @Column
    @NotNull
    @DecimalMin(value = "0.0")
    private Double interest;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (getDueDate().before(getPaymentDate())) {
            double penalty = .0;
            double interest = .0;
            LocalDate ldDue = getDueDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate ldPayment = getPaymentDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int diffDays = ldPayment.getDayOfYear() - ldDue.getDayOfYear();
            if (diffDays > 0 && diffDays <= 3) {
                penalty = 2.0;
                interest = diffDays * 0.1;
            } else if (diffDays > 3 && diffDays <= 5) {
                penalty = 3.0;
                interest = diffDays * 0.2;
            } else if (diffDays > 5) {
                penalty = 5.0;
                interest = diffDays * 0.3;
            }
            if (penalty > 0 && interest > 0) {
                setPenalty(getValue() * (penalty / 100));
                setInterest(getValue() * (interest / 100));

                setDaysLate(diffDays);
                setCorrectedValue(getValue() + getInterest() + getPenalty());
            } else {
                setPenalty(0.0);
                setInterest(0.0);

                setDaysLate(0);
                setCorrectedValue(getValue());
            }
        } else {
            setPenalty(0.0);
            setInterest(0.0);

            setDaysLate(0);
            setCorrectedValue(getValue());
        }
    }
}
