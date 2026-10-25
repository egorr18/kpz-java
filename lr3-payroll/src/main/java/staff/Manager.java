package staff;

import java.math.BigDecimal;

/** Менеджер: оклад + 5 % окладу за кожного підлеглого (але не більше 50 %). */
public class Manager extends Employee {

    private static final BigDecimal PER_SUBORDINATE = new BigDecimal("0.05");
    private static final BigDecimal MAX_BONUS_SHARE = new BigDecimal("0.50");

    private final int teamSize;

    public Manager(String name, BigDecimal baseSalary, int teamSize) {
        super(name, baseSalary);
        if (teamSize < 0) {
            throw new IllegalArgumentException("розмір команди не може бути від'ємним");
        }
        this.teamSize = teamSize;
    }

    @Override
    public String position() {
        return "Менеджер";
    }

    private BigDecimal teamBonus() {
        BigDecimal share = PER_SUBORDINATE.multiply(BigDecimal.valueOf(teamSize)).min(MAX_BONUS_SHARE);
        return money(getBaseSalary().multiply(share));
    }

    @Override
    public BigDecimal monthlyPay() {
        return money(super.monthlyPay().add(teamBonus()));
    }

    @Override
    protected String payDetails() {
        return super.payDetails() + " + бонус за команду з " + teamSize + " осіб " + teamBonus();
    }
}
