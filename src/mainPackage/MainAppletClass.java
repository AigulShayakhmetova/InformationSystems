package mainPackage;

import javax.swing.JApplet;

import panelsPackage.PanelsManager;

public class MainAppletClass extends JApplet {
    public PanelsManager panelsManager;

    public MainAppletClass() {
        System.out.println("MainAppletClass::MainAppletClass(); -- ");
    }

    @Override
    public void init() {
        System.out.println("MainAppletClass::init(); -- Start");
        super.init();
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    invalidate();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("MainAppletClass::init(); -- End");
    }

    public void initComponents() {
        this.panelsManager = new PanelsManager(this, getContentPane());
//        this.setSize(panelsManager.container.getSize());
    }

    @Override
    public void start() {
        System.out.println("MainAppletClass::start(); -- ");
    }

    @Override
    public void stop() {
        System.out.println("MainAppletClass::stop(); -- ");
    }

    @Override
    public void destroy() {
        System.out.println("MainAppletClass::destroy(); -- ");
    }

//    @Override
//    public void paint(Graphics g) {
////       g.drawString("Hello, world!",70,50);
//       System.out.println("MainAppletClass::paint(" + g + "); -- ");
////       this.setSize(getContentPane().getPreferredSize());
//    }
}
