/**
 * Класс, в который пишется исходные данные
 * Исходными данными является серия наблюдений
 * Своего рода статистическая выборка
 * Описывает состояние погоды и тот факт, была ли в такую погоду игра
 */
class WeatherPlay {
    Weather weather;
    Play play;

    public WeatherPlay(Weather weather, Play play) {
        this.weather = weather;
        this.play = play;
    }

    @Override
    public String toString() {
        return "{weather: " + this.weather + ", play:" + this.play + "}";
    }
}