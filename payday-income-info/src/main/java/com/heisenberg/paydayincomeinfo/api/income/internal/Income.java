package com.heisenberg.paydayincomeinfo.api.income.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income implements Serializable {
    private static final long serialVersionUID = 1231244353343L;

    private BigDecimal monthlyIncome;
    private LocalDate incomeDate;
}
