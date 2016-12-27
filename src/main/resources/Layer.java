package nnet;
import java.io.Serializable;

/**
 * Интерфейс нейронного слоя
 *
 * Базовый интерфейс любого нейронного слоя.
 * Размер слоя - количество нейронов содержащихся в слое и, соотвественно,
 * размер выходного вектора слоя.
 */
public interface Layer extends Serializable {
    /**
     * Получает размер входного вектора
     * @return Размер входного вектора
     */
    int getInputSize();

    /**
     * Получает размер слоя
     * @return Размер слоя
     */
    int getSize();

    /**
     * Вычисляет отклик слоя
     * @param input Входной вектор
     * @return Выходной вектор
     */
    float[] computeOutput(float[] input);
}
