import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class ControlPanel extends JFrame {
    private ThreadManager threadManager;
    private JLabel feedbackLabel;
    private List<String> feedbackMessages = new ArrayList<>();

    public ControlPanel(ThreadManager threadManager) {
        this.threadManager = threadManager;
        setTitle("Particle Controls");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750, 400); 

        feedbackLabel = new JLabel("<html></html>");
        feedbackLabel.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane feedbackScrollPane = new JScrollPane(feedbackLabel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        feedbackScrollPane.setPreferredSize(new Dimension(700, 150));
        feedbackScrollPane.setBorder(null);

        JLabel feedbackTitle = new JLabel("Output");
        feedbackTitle.setHorizontalAlignment(JLabel.CENTER);
        feedbackTitle.setBorder(new EmptyBorder(10, 0, 10, 0)); 
        feedbackTitle.setOpaque(true); 
        feedbackTitle.setBackground(Color.WHITE);

        JPanel feedbackPanel = new JPanel(new BorderLayout());
        feedbackPanel.add(feedbackTitle, BorderLayout.NORTH);
        feedbackPanel.add(feedbackScrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(createParticleInputPanel());
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.NORTH);
        getContentPane().add(feedbackPanel, BorderLayout.CENTER); 
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createParticleInputPanel() {
        JPanel particlePanel = new JPanel(new BorderLayout());
        JPanel particleInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel particleButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel particleLabel = new JLabel("Add particles");
        particleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JTextField numberInput = new JTextField("1", 5);
        JTextField xInput = new JTextField(5);
        JTextField yInput = new JTextField(5);
        JTextField angleInput = new JTextField(5);
        JTextField velocityInput = new JTextField(5);
        JButton addButton = new JButton("Add");

        particleInputPanel.add(new JLabel("No. of Particles:"));
        particleInputPanel.add(numberInput);
        particleInputPanel.add(new JLabel("X:"));
        particleInputPanel.add(xInput);
        particleInputPanel.add(new JLabel("Y:"));
        particleInputPanel.add(yInput);
        particleInputPanel.add(new JLabel("Angle:"));
        particleInputPanel.add(angleInput);
        particleInputPanel.add(new JLabel("Velocity:"));
        particleInputPanel.add(velocityInput);

        particleButtonPanel.add(addButton);

        particlePanel.add(particleLabel, BorderLayout.NORTH);
        particlePanel.add(particleInputPanel, BorderLayout.CENTER);
        particlePanel.add(particleButtonPanel, BorderLayout.EAST);

        addButton.addActionListener(e -> handleParticleAddition(e, numberInput, xInput, yInput, angleInput, velocityInput));
        return particlePanel;
    }

    private void handleParticleAddition(ActionEvent e, JTextField numberInput, JTextField xInput, JTextField yInput, JTextField angleInput, JTextField velocityInput) {
        try {
            int x = Integer.parseInt(xInput.getText());
            int y = Integer.parseInt(yInput.getText());
            double angle = Math.toRadians(Double.parseDouble(angleInput.getText()));
            double velocity = Double.parseDouble(velocityInput.getText());
            int number = Integer.parseInt(numberInput.getText());
            for (int i = 0; i < number; i++) {
                threadManager.addParticle(new Particle(x, y, Math.cos(angle) * velocity, Math.sin(angle) * velocity));
            }
            String feedback = "Added " + number + " particles at (" + x + ", " + y + ") with angle " + Math.toDegrees(angle) + "° and velocity " + velocity;
            updateFeedbackDisplay(feedback);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFeedbackDisplay(String feedback) {
        feedbackMessages.add(feedback);
        StringBuilder feedbackHtml = new StringBuilder("<html>");
        for (int i = feedbackMessages.size() - 1; i >= 0; i--) {
            feedbackHtml.append(feedbackMessages.get(i)).append("<br>");
        }
        feedbackHtml.append("</html>");
        feedbackLabel.setText(feedbackHtml.toString());
    }
}
