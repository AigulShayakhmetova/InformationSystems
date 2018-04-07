package mainPackage;/** пакет ,в котором содержится папка  */

import javax.swing.JApplet;/** класс, который используется */
import javax.swing.JFrame;/** класс,который используется */

public class MainFrameClass /** публичный класс*/
{
    public static void main(String[] args) /** функция,принимающая массив аргументов*/
    {
        JFrame frame = new JFrame();/** создание объекта класса*/
        JApplet app = new MainAppletClass();/** создание объекта класса*/
        frame.getContentPane().add(app);/** передаем компонент в контейнер*/
        frame.setSize(400, 400);/** размер*/
        app.init();/** инициализация*/
        app.start();
        frame.setVisible(true);/** видимость */
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);/**операция для закрытия формы*/
    }
}
