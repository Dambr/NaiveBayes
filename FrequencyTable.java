import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Класс описывает все состояния погоды
 * И каждому состоянию он сопоставляет то количество раз
 * Была или не была при такой погоде сыграна игра
 */
class FrequencyTable {
    /**
     * Счечик общего количества состояний "No"
     */
    private int No;

    /**
     * Счетчик общего количества состояний "Yes"
     */
    private int Yes;

    /**
     * Коллекция Set нужна для избежания дублирования состояний погоды
     */
    private Set weather = new HashSet();

    /**
     * В свойство table записывается состояние погоды и массив из двух элементов:
     * Сколько раз при такой погоде игра не была сыграна (значение No)
     * И сколько раз игра состоялась (значение Yes)
     */
    Map table = new HashMap<>();

    /**
     * Конструктор класса. Срабатывает в момент создания экземпляра класса
     *
     * @param weatherPlay - статистическая выборка наблюдений,
     *                    была ли сыграна игра при том или ином
     *                    состоянии погоды
     */
    public FrequencyTable(WeatherPlay[] weatherPlay) {
        for (int i = 0; i < weatherPlay.length; i++) {
            // Локальные значения "No" и "Yes"
            // Описывают сколько раз была или не была сыграна игра
            // При заданном состоянии погоды
            int No = 0;
            int Yes = 0;

            // В цикле перебираем все состояния погоды и ищем одинаковые
            for (int j = 0; j < weatherPlay.length; j++) {
                // Если состояния погоды совпадают, то увеличиваем соответствующий счетчик
                if (weatherPlay[i].weather.equals(weatherPlay[j].weather)) {
                    if (weatherPlay[j].play.equals(Play.No)) {
                        No++;
                    } else {
                        Yes++;
                    }
                }
            }
            // Проверяем, было ли учтено ранее текущее состояние погоды,
            // Чтобы избежать дублирования данных
            // Если такое состояние погоды встретилось в первый раз,
            // То увеличиваем общиее количество состояний "No" и "Yes"
            // А так же запоминаем, сколько всего "No" и "Yes" у текущего
            // Состояния погоды из стататистической выборки
            if (weather.add(weatherPlay[i].weather.toString())) {
                this.No += No;
                this.Yes += Yes;
                int[] arr = {No, Yes};
                table.put(weatherPlay[i].weather.toString(), arr);
            }
        }
        // В конце сохраняем общее число состояний "No" и "Yes"
        // По каждому из состояний погоды
        // Получить эти данные можно по ключу "GrandTotal"
        int[] arr = {this.No, this.Yes};
        table.put("GrandTotal", arr);
    }
}