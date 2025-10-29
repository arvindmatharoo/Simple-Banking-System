import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// --- BankAccount Logic ---
class BankAccount {
    private String accountHolderName;
    private double balance;

    public BankAccount(String name, double initialBalance) {
        accountHolderName = name;
        balance = initialBalance;
    }

    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// --- Next-Gen UI Class ---
public class NextGenBankingDashboard extends JFrame {
    private BankAccount account;
    private JTextField nameField, initBalanceField, depositField, withdrawField;
    private JLabel balanceLabel, nameLabel, statusLabel;
    private JButton createBtn, depositBtn, withdrawBtn, clearBtn;

    public NextGenBankingDashboard() {
        // Frame setup
        setTitle("🏦 Next-Gen Banking Dashboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Gradient background panel
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(46, 134, 193),
                        getWidth(), getHeight(), new Color(155, 89, 182));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout(15, 15));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header
        JLabel header = new JLabel("💰 Next-Gen Banking Dashboard", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.setForeground(Color.WHITE);
        backgroundPanel.add(header, BorderLayout.NORTH);

        // Content area
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Left: Account setup card
        JPanel accountCard = createCard("👤 Account Setup");
        nameField = new JTextField();
        initBalanceField = new JTextField();
        createBtn = createStyledButton("Create Account", new Color(41, 128, 185));

        accountCard.add(createLabeledField("Account Holder:", nameField));
        accountCard.add(createLabeledField("Initial Deposit (₹):", initBalanceField));
        accountCard.add(createBtn);

        // Middle: Transaction card
        JPanel transactionCard = createCard("💳 Transactions");
        depositField = new JTextField();
        withdrawField = new JTextField();
        depositBtn = createStyledButton("Deposit", new Color(39, 174, 96));
        withdrawBtn = createStyledButton("Withdraw", new Color(192, 57, 43));
        clearBtn = createStyledButton("Clear", new Color(149, 165, 166));

        transactionCard.add(createLabeledField("Deposit Amount (₹):", depositField));
        transactionCard.add(depositBtn);
        transactionCard.add(createLabeledField("Withdraw Amount (₹):", withdrawField));
        transactionCard.add(withdrawBtn);
        transactionCard.add(clearBtn);

        // Right: Info Card
        JPanel infoCard = createCard("📊 Account Info");
        nameLabel = createInfoLabel("Account: —");
        balanceLabel = createInfoLabel("Balance: ₹0.00");
        statusLabel = createInfoLabel("Status: No account created");

        infoCard.add(nameLabel);
        infoCard.add(balanceLabel);
        infoCard.add(statusLabel);

        // Add cards to grid
        gbc.gridx = 0; content.add(accountCard, gbc);
        gbc.gridx = 1; content.add(transactionCard, gbc);
        gbc.gridx = 2; content.add(infoCard, gbc);

        backgroundPanel.add(content, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel("© 2025 Arvind's Banking App — Powered by Java Swing",
                SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(Color.WHITE);
        backgroundPanel.add(footer, BorderLayout.SOUTH);

        add(backgroundPanel);

        // --- Actions ---
        createBtn.addActionListener(e -> createAccount());
        depositBtn.addActionListener(e -> depositMoney());
        withdrawBtn.addActionListener(e -> withdrawMoney());
        clearBtn.addActionListener(e -> clearFields());
    }

    private JPanel createCard(String titleText) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel title = new JLabel(titleText, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(52, 73, 94));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        return card;
    }

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lbl, BorderLayout.NORTH);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        field.setPreferredSize(new Dimension(100, 30));
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.brighter());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(new Color(44, 62, 80));
        return label;
    }

    private void createAccount() {
        try {
            String name = nameField.getText().trim();
            double init = Double.parseDouble(initBalanceField.getText());
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter account holder name!");
                return;
            }
            account = new BankAccount(name, init);
            nameLabel.setText("Account: " + name);
            balanceLabel.setText("Balance: ₹" + init);
            statusLabel.setText("✅ Account created successfully!");
            flashLabel(statusLabel, new Color(39, 174, 96));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount!");
        }
    }

    private void depositMoney() {
        if (account == null) {
            JOptionPane.showMessageDialog(this, "Create an account first!");
            return;
        }
        try {
            double amount = Double.parseDouble(depositField.getText());
            account.deposit(amount);
            balanceLabel.setText("Balance: ₹" + account.getBalance());
            statusLabel.setText("✅ Deposited ₹" + amount);
            flashLabel(statusLabel, new Color(39, 174, 96));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid deposit amount!");
        }
    }

    private void withdrawMoney() {
        if (account == null) {
            JOptionPane.showMessageDialog(this, "Create an account first!");
            return;
        }
        try {
            double amount = Double.parseDouble(withdrawField.getText());
            if (account.withdraw(amount)) {
                balanceLabel.setText("Balance: ₹" + account.getBalance());
                statusLabel.setText("✅ Withdrew ₹" + amount);
                flashLabel(statusLabel, new Color(230, 126, 34));
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid withdrawal amount!");
        }
    }

    private void clearFields() {
        nameField.setText("");
        initBalanceField.setText("");
        depositField.setText("");
        withdrawField.setText("");
        statusLabel.setText("Fields cleared!");
        flashLabel(statusLabel, new Color(149, 165, 166));
    }

    private void flashLabel(JLabel label, Color color) {
        new Thread(() -> {
            Color original = label.getForeground();
            label.setForeground(color);
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            label.setForeground(original);
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NextGenBankingDashboard().setVisible(true));
    }
}
