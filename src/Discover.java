import javax.swing.*;
import java.awt.*;

/**
 * Created by fcm2009 on 3/22/14.
 */
public class Discover extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu discover = new JMenu("Discover");
    private JMenu collect = new JMenu("Collect");
    private JMenu trace = new JMenu("Trace");

    private JPanel ctrl = new JPanel();
    private JPanel img = new JPanel();
    private JPanel info = new JPanel();

    public Discover(int w, int h) {
        super("TvTime - Discover");
        setSize(w, h);
        setResizable(false);
        setLayout(null);
        add(ctrl);
        add(img);
        add(info);

        ctrl.setBackground(Color.black);
        ctrl.setBounds(0, 0, 960, 1080);
        ctrl.setLayout(new FlowLayout());

        img.setBackground(Color.yellow);
        img.setBounds(960, 0, 1920, 540);
        img.setLayout(new FlowLayout());

        info.setBackground(new Color(198, 6, 3));
        info.setBounds(960, 540, 1920, 1080);
        info.setLayout(new FlowLayout());

        setJMenuBar(menuBar);
        menuBar.add(discover);
        menuBar.add(collect);
        menuBar.add(trace);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Discover(1920, 1080);
    }
}
