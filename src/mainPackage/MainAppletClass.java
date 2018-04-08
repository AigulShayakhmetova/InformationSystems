package mainPackage;

import javax.swing.JApplet;/** импортируем  */

import panelsPackage.PanelsManager;/**импортируем */

public class MainAppletClass extends JApplet /** класс,использующийся для расширения другого класса*/
{
    public PanelsManager panelsManager;/** создание объекта  */

    public MainAppletClass() /**конструктор этого класса*/
    {
        System.out.println("MainAppletClass::MainAppletClass(); -- ");/**пишется , что функция вызвалась*/ 
    }

    @Override
    public void init()/** переопределени функции*/
    {
        System.out.println("MainAppletClass::init(); -- Start");/** пишется, что функция вызвалась */
        super.init();/** вызов функции*/
        try /**для проверки */
        {
            java.awt.EventQueue.invokeAndWait(new Runnable()/**создание отдельного  потока*/
                 {
                public void run() ;
               
                {
                    initComponents();
                    invalidate();
                }
            });
        } catch (Exception ex) /**если что-то пошло не так ,бросается ex*/
        {
            ex.printStackTrace();
        }
        System.out.println("MainAppletClass::init(); -- End");/** конец функции*/
    }

    public void initComponents() /** функция в другом потоке*/
    {
        this.panelsManager = new PanelsManager(this, getContentPane());/** динамическое создание объекта*/

    }

    @Override
    public void start() /** создаются объекты потоков и потоки запускаются на выполнение*/
    {
        System.out.println("MainAppletClass::start(); -- ");
    }

    @Override
    public void stop()/** остановка работающего потока*/
    {
        System.out.println("MainAppletClass::stop(); -- ");
    }

    @Override
    public void destroy() /**уничтожение*/
    {
        System.out.println("MainAppletClass::destroy(); -- ");
    }
}
