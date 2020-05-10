import java.util.concurrent.ThreadLocalRandom;

/**
 * Задача: определить вероятность того, что игра состоится при каждом из возможных состояний погоды
 * Класс, реализующий решение такой задачи методом Navie Bayes
 */
public class NaiveBayes {
    /**
     * Главная функция
     * С нее начинается работа всего проекта
     *
     * @param args - параметр, указывающийся при запуске программы,
     *             в раках выполнения текущей работы он не указывается
     */
    public static void main(String[] args) {
        // Получение списка всех возможных состояний погоды
        Weather[] weather = Weather.values();

        // Получение списка всех возможных состояний, была или не была сыграна игра
        Play[] play = Play.values();

        // Генерация статистических данных
        WeatherPlay[] weatherPlay = new WeatherPlay[20];
        for (int i = 0; i < weatherPlay.length; i++) {
            // В каждому элементу статистических данных
            // Присваивается случайное состояние погоды из возможных вариантов
            // А так же указывается, была или не была сыграна игра
            weatherPlay[i] = new WeatherPlay(
                    weather[ThreadLocalRandom.current().nextInt(0, weather.length)],
                    play[ThreadLocalRandom.current().nextInt(0, play.length)]
            );
        }

        // Вывод в консоль сгенерированной статистистики
        System.out.println("Statistic");
        for (var i : weatherPlay) {
            System.out.println(i);
        }
        System.out.println();

        // Создание таблицы данных
        // Сколько раз была или не была сыграна игра
        // При каждом из возможных состояний погоды
        FrequencyTable frequencyTable = new FrequencyTable(weatherPlay);

        // Создание таблицы данных
        // Сколько раз то или иное состояние погоды встречается
        // В статистической выборке
        LikelihoodTable likelihoodTable = new LikelihoodTable(frequencyTable, weather);

        // Вывод данных из двух таблиц в обобщенном виде
        System.out.println("Given table");
        for (int i = 0; i < weather.length; i++) {
            int[] arr = (int[]) frequencyTable.table.get(weather[i].toString());

            // Указание отступа для выравнивания элеметов и красивого вывода данных в консоль
            System.out.printf("%10s", weather[i]);
            System.out.print("{No:" + arr[0] + ", Yes:" + arr[1] + "}; Sum = " +
                    likelihoodTable.table.get(weather[i].toString()));
            System.out.println();
        }

        // Дополнительный вывод информации по общему количеству сыгранных и не сыгранных игр
        System.out.println("GrandTotal{No:" + ((int[]) frequencyTable.table.get("GrandTotal"))[0] +
                ", Yes:" + ((int[]) frequencyTable.table.get("GrandTotal"))[1] + "}"
        );
        System.out.println();


        // Перебор каждого сотояния погоды
        for (int i = 0; i < weather.length; i++) {
            // Перебор каждого варианта исхода
            for (int j = 0; j < play.length; j++) {
                // Вероятность определенного состояния погоды при определенном результате
                // Какое состояние погоды, если игра (не) состоялась?
                double P_Weather_Play = (double) ((int[]) frequencyTable.table.get(weather[i].toString()))[j] /
                        (double) ((int[]) frequencyTable.table.get("GrandTotal"))[j];

                // Вероятность определенного состояния погоды
                // Отношение количества определенного состояния погоды к общеему числу наблюдений
                double P_Weather = (double) ((int) likelihoodTable.table.get(weather[i].toString())) /
                        (double) weatherPlay.length;

                // Вероятность определенного результата
                // Отношение текущего варианта исхода к общему числу наблюдений
                double P_Play = (double) ((int[]) frequencyTable.table.get("GrandTotal"))[j] /
                        (double) weatherPlay.length;

                // Вероятность определенного результата при определенной погоде
                // Состоится ли игра, если у погоды заданное состояние? - Ответ на вопрос задачи
                double P_Play_Weather = P_Weather_Play * P_Play / P_Weather;

                // Вывод результата расчета
                System.out.println((play[j].equals(Play.Yes)) ?
                        "The likelihood that the game will take place in the weather '" +
                                weather[i] + "' = " + P_Play_Weather :
                        "The likelihood that the game will not take place in the weather '" +
                                weather[i] + "' = " + P_Play_Weather);
            }
            System.out.println();
        }
    }
}






