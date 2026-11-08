/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GamePackage;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ALEKSEY
 */
public class gameSetting extends JPanel {
    JPanel colorChooserPanel;
    JPanel buttonPanel;
    JLabel colorValue;
    game gui;
    public gameSetting(game gui) {
        this.gui = gui;
        setBackground(gui.getCurrentColorLink());
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Настройки", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        add(titleLabel, BorderLayout.NORTH);
         
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 20)); 
        buttonPanel.setBackground(gui.getCurrentColorLink());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 400, 200, 400));
        
        JLabel themeLabel = createStyledLebel("Цвет интерфейса:");
        themeLabel.setHorizontalAlignment(JLabel.RIGHT);
        colorValue = createStyledLebel(gui.getCurrentColor());
        colorValue.setHorizontalAlignment(JLabel.CENTER);
        colorChooserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        colorChooserPanel.setBackground(gui.getCurrentColorLink());

        JButton leftArrow = createStyledButton("<");
        JButton rightArrow = createStyledButton(">");
        JButton exitButton = createStyledButton("Назад");
        
        colorChooserPanel.add(themeLabel);
        colorChooserPanel.add(leftArrow);
        colorChooserPanel.add(colorValue);
        colorChooserPanel.add(rightArrow);
        buttonPanel.add(colorChooserPanel);
        buttonPanel.add(exitButton);
        
        leftArrow.addActionListener(e ->{
            gui.previous();
            colorUpdate();
        });
        rightArrow.addActionListener(e ->{
            gui.next();
            colorUpdate();
        }); 
        exitButton.addActionListener(e -> gui.openMenu()); 
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 50, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        button.setPreferredSize(new Dimension(20,20));
        return button;
    }
    
    public JLabel  createStyledLebel(String text) {
        JLabel lebel = new JLabel (text);
        lebel.setFont(new Font("Arial", Font.BOLD, 18));
        lebel.setForeground(Color.WHITE);
        lebel.setOpaque(true);
        lebel.setBackground(new Color(50, 50, 50));
        lebel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        lebel.setPreferredSize(new Dimension(200,50));
        return lebel;
    }
    public void thisColorUpdate(){
        colorValue.setText(gui.getCurrentColor());
        this.setBackground(gui.getCurrentColorLink());
        buttonPanel.setBackground(gui.getCurrentColorLink());
        colorChooserPanel.setBackground(gui.getCurrentColorLink());
    }
    public void colorUpdate(){
        thisColorUpdate();
        gui.menuPanel.thisColorUpdate();
    }
}
