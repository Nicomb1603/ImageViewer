import control.Command;
import control.ImagePresenter;
import control.Presenter;
import view.SwingImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class MainFrame extends JFrame {
    private Dimension screenSize;
    private Presenter presenter;
    private Map<String, Command> commands;
    private JButton nextButton;
    private JButton previousButton;

    public static MainFrame createMainframe(){
        return new MainFrame();
    }

    private MainFrame() {

        initialize();

        this.setTitle("Image Viewer");
        this.setSize(screenSize);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add((SwingImageDisplay) presenter.imageDisplay());
        this.add(previousButton, BorderLayout.WEST);
        this.add(nextButton, BorderLayout.EAST);
    }

    private void initialize() {
        this.commands = new HashMap<>();
        setScreenSize();
        this.presenter = createPresenter();
        createButtons();
    }

    private ImagePresenter createPresenter() {
        return new ImagePresenter();
    }

    private void createButtons(){
        nextButton = new JButton(">");
        previousButton = new JButton("<");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {commands.get(">").execute();}});

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {commands.get("<").execute();}});

    }

    public MainFrame add(String name, Command command){
        this.commands.put(name, command);
        return this;
    }


    private void setScreenSize(){screenSize = Toolkit.getDefaultToolkit().getScreenSize();}


    public Presenter presenter() {
        return presenter;
    }
}
