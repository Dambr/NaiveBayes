/**
 * Возможные исходы того, что игра состоится
 * Всего 2 исхода: Да и Нет
 */
enum Play {
    Yes("Yes"),
    No("No");

    private String string;

    private Play(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}