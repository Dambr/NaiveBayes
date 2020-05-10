/**
 * Возможные состояния погоды: солнечно, пасмурно, дождливо
 */
enum Weather {
    Sunny("Sunny"),
    Overcast("Overcast"),
    Rainy("Rainy");

    private String string;

    private Weather(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}