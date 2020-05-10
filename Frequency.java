/**
 * Класс хранит информацию
 * Какое количество раз при заданном состоянии погоды
 * Была или не была сыграна игра
 */
class Frequency {
    private String weather;
    private int No;
    private int Yes;

    public Frequency(String weather, int No, int Yes) {
        this.weather = weather;
        this.No = No;
        this.Yes = Yes;
    }
}