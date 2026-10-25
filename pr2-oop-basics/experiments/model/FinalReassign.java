package model;

/** Дослід 5: спроби перезаписати final-поле після конструктора та final-змінну методу. */
class FinalReassign extends Player {
    private final int id = 5;

    void tryToChange() {
        id = 6;                         // final-поле
        final int bonus = 10;
        bonus = 20;                     // final-змінна методу
    }
}
