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
public abstract class baseLevel extends JPanel {
    game gui;
    protected  JPanel leftPanel;
    protected  JLayeredPane rightPanel;
    protected  JButton addComandButton;
    protected  JButton removeComandButton;
    protected  JButton executeButton;
    protected  String[] comands = {"Вверх", "Вниз", "Влево", "Вправо","Поднять", "Положить"};
    protected  int currentIndexComand = 0;
    protected  JList<String> availableCommandsList;
    protected  DefaultListModel<Object> chosenModel; 
    protected  JList<Object> chosenCommandsList;
    protected  int colCellWight;
    protected  int colCellHeight;
    protected  static int cof = 1;
    protected  static int CELL_SIZE;
    protected  JPanel[][] cells;
    protected  int runningCommandIndex = 0;
    protected  java.util.List<Hero> heroesList = new java.util.ArrayList<>();;
    protected  java.util.List<blockData> blockList = new java.util.ArrayList<>();;
    protected  Timer commandExecutionTimer;
    protected  Timer stepAnimator;
    protected java.util.List<java.awt.Point> targetCells  = new java.util.ArrayList<>();;
    protected java.util.List<java.awt.Point> pitCells  = new java.util.ArrayList<>();;


    public baseLevel(game gui) {
        this.gui = gui;
        setBackground(gui.getCurrentColorLink());
        setLayout(new BorderLayout());
        
        setingLevel();
        
        leftPanel = settingLeftPanel();
        rightPanel = settingRightPanel();
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
    }
    public String getCurrentComand() {
        return comands[currentIndexComand];
    }
    protected  JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 50, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        return button;
    }
    protected  JComboBox createStyledComboBox(String[] text) {
        JComboBox<String> ComboBox = new JComboBox<>(text);
        ComboBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new javax.swing.plaf.basic.BasicArrowButton(
                    javax.swing.plaf.basic.BasicArrowButton.SOUTH, 
                    new Color(50, 50, 50), 
                    new Color(50, 50, 50),
                    Color.WHITE, 
                    new Color(50, 50, 50)
                );
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false); 
                button.setOpaque(false);
                button.setBackground(new Color(50, 50, 50));
                return button;
            }
        });
        ComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        ComboBox.setForeground(Color.WHITE);
        ComboBox.setBackground(new Color(50, 50, 50));
        ComboBox.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        ComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, ComboBox.getPreferredSize().height));    
        ComboBox.setRequestFocusEnabled(false);
        return ComboBox;
    }
    
    public JPanel settingLeftPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        availableCommandsList = new JList<>(comands);
        availableCommandsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollComandPane = new JScrollPane(availableCommandsList);
        scrollComandPane.setMaximumSize( new Dimension(260, 100));
        availableCommandsList.setBackground(gui.getCurrentColorLink());
        availableCommandsList.setFont(new Font("Arial", Font.BOLD, 12));
        availableCommandsList.setForeground(Color.WHITE);
        scrollComandPane.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.GREEN));
        
        chosenModel = new DefaultListModel<>();
        chosenCommandsList = new JList<>(chosenModel);
        chosenCommandsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollChosenComandPane = new JScrollPane(chosenCommandsList);
        chosenCommandsList.setBackground(gui.getCurrentColorLink());
        chosenCommandsList.setFont(new Font("Arial", Font.BOLD, 12));
        chosenCommandsList.setForeground(Color.WHITE);
        scrollChosenComandPane.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.GREEN));
        
        String[] directions = {"Слева-Сверху", "Сверху", "Справа-Сверху", 
                                "Слева",        "Центр",  "Справа", 
                                "Слева-Снизу",  "Снизу",  "Справа-Снизу"};
        JComboBox<String> directionBox = createStyledComboBox(directions);
        String[] operators = {"==", "!="};
        JComboBox<String> operatorBox = createStyledComboBox(operators);
        String[] targets = {"Герой","Блок", "Яма"};
        JComboBox<String> targetBox = createStyledComboBox(targets);
        
        addComandButton = createStyledButton("Добавить команду");
        removeComandButton = createStyledButton("Удалить команду");
        executeButton = createStyledButton("Запустить программу");
        JButton addIfButton = createStyledButton("Добавить условие");
        
        addIfButton.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.GREEN));
        executeButton.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.GREEN));
        
        scrollComandPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollChosenComandPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        addComandButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        removeComandButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        executeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        directionBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        operatorBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        targetBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        addIfButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(scrollComandPane);
        panel.add(addComandButton);
        panel.add(removeComandButton);
        panel.add(addIfButton);
        panel.add(directionBox);
        panel.add(operatorBox);
        panel.add(targetBox);
        panel.add(executeButton);
        panel.add(scrollChosenComandPane);
        
        addComandButton.addActionListener(e -> {
            String selected = availableCommandsList.getSelectedValue();
            if (selected != null) {
                int selectedIndex = chosenCommandsList.getSelectedIndex();
                if (selectedIndex == -1) {
                    selectedIndex = chosenModel.getSize() - 1;
                }
                boolean insideIf = false;
                int lastOpeningIfIndex = -1;
                for (int i = 0; i <= selectedIndex && i < chosenModel.getSize(); i++) {
                    Object item = chosenModel.getElementAt(i);
                    if (item instanceof ConditionalCommand) {
                        insideIf = true;
                        lastOpeningIfIndex = i;
                    }
                    if (item.toString().equals("}")) {
                        insideIf = false;
                    }
                }
                if (insideIf && lastOpeningIfIndex != -1) {
                    int closingBraceIndex = -1;
                    for (int i = lastOpeningIfIndex; i < chosenModel.getSize(); i++) {
                        if (chosenModel.getElementAt(i).toString().equals("}")) {
                            closingBraceIndex = i;
                            break;
                        }
                    }
                    if (closingBraceIndex != -1) {
                        chosenModel.add(closingBraceIndex, "    └ " + selected);
                        chosenCommandsList.setSelectedIndex(closingBraceIndex);
                    } else {
                        chosenModel.addElement("    └ " + selected);
                    }
                } else {
                    chosenModel.addElement(selected);
                    chosenCommandsList.setSelectedIndex(chosenModel.getSize() - 1);
                }
            }
        });
        
        removeComandButton.addActionListener(e -> {
            int selectedIndex = chosenCommandsList.getSelectedIndex();
            if (selectedIndex != -1) {
                chosenModel.remove(selectedIndex);
            }
        });
        addIfButton.addActionListener(e -> {
            if (commandExecutionTimer == null || !commandExecutionTimer.isRunning()) {
                ConditionalCommand ifCmd = new ConditionalCommand(
                    (String) directionBox.getSelectedItem(),
                    (String) operatorBox.getSelectedItem(),
                    (String) targetBox.getSelectedItem()
                );
                chosenModel.addElement(ifCmd);
                chosenModel.addElement("}");
            }
        });
        executeButton.addActionListener(e -> startExecutingCommands());
        return panel;
    }
    public JLayeredPane settingRightPanel(){
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(colCellHeight, colCellWight, 2, 2));
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, colCellWight * CELL_SIZE, colCellHeight * CELL_SIZE);
        
        for (int y = 0; y < colCellHeight; y++) {
            for (int x = 0; x < colCellWight; x++) {
                JPanel cell = new JPanel();
                if ((y + x) % 2 == 0){
                    cell.setBackground(Color.LIGHT_GRAY);
                }
                else{
                    cell.setBackground(Color.darkGray);
                }
                cells[y][x] = cell;
                panel.add(cell);
            }
        }
        
        for (Hero hero : heroesList) {
            layeredPane.add(hero.panel, JLayeredPane.PALETTE_LAYER);
        }
        for (blockData block : blockList) {
            layeredPane.add(block.panel, JLayeredPane.POPUP_LAYER);
        }
        for (java.awt.Point pit : pitCells) {
            cells[pit.y][pit.x].setBackground(Color.BLACK);
        }
        for (java.awt.Point target : targetCells) {
            cells[target.y][target.x].setBackground(Color.ORANGE);
        }
        layeredPane.add(panel);
        return layeredPane;
    }
    protected  void startExecutingCommands() {
        if (chosenModel.isEmpty() || (commandExecutionTimer != null && commandExecutionTimer.isRunning())) {
            return;
        }
        addComandButton.setEnabled(false);
        removeComandButton.setEnabled(false);
        executeButton.setEnabled(false);
        runningCommandIndex = 0;
        commandExecutionTimer = new Timer(800, e -> {
            if (runningCommandIndex >= chosenModel.getSize()){ 
                ((Timer) e.getSource()).stop();
                task();
                addComandButton.setEnabled(true);
                removeComandButton.setEnabled(true);
                executeButton.setEnabled(true);
                chosenCommandsList.clearSelection();
                for (Hero hero : heroesList) {
                    hero.returnBase();
                }
                for(blockData block : blockList){
                    block.returnBase();
                }
                return;
            }
            chosenCommandsList.setSelectedIndex(runningCommandIndex);
            chosenCommandsList.ensureIndexIsVisible(runningCommandIndex);
            execution();
            animateHeroToCell();
            checkPits();
            runningCommandIndex++;
        });
        commandExecutionTimer.start();
    }
    protected  void animateHeroToCell() {
        if (stepAnimator != null && stepAnimator.isRunning()) {
            stepAnimator.stop();
        }
        stepAnimator = new Timer(10, evt -> {
            int speed = 2;
            boolean allFinished = true;
            for (Hero hero : heroesList) {
                boolean isXFinished = false;
                boolean isYFinished = false;
                if (hero.currentX < hero.targetX) {
                    hero.currentX = Math.min(hero.currentX + speed, hero.targetX);
                } else if (hero.currentX > hero.targetX) {
                    hero.currentX = Math.max(hero.currentX - speed, hero.targetX);
                } else {
                    isXFinished = true;
                }
                if (hero.currentY < hero.targetY) {
                    hero.currentY = Math.min(hero.currentY + speed, hero.targetY);
                } else if (hero.currentY > hero.targetY) {
                    hero.currentY = Math.max(hero.currentY - speed, hero.targetY);
                } else {
                    isYFinished = true;
                }
                hero.panel.setLocation(hero.currentX, hero.currentY);
                if(hero.carrier != null){
                    hero.carrier.panel.setLocation(hero.currentX+4 * cof, hero.currentY+4 * cof);
                }
                if (!isXFinished || !isYFinished) {
                    allFinished = false;
                }
            }
            if (allFinished) {
                ((Timer) evt.getSource()).stop();
            }
        });
        stepAnimator.start();
    }
    public void execution(){
        Object currentCommand = chosenModel.getElementAt(runningCommandIndex);
        if(currentCommand instanceof ConditionalCommand){
            ConditionalCommand ifComand = (ConditionalCommand) currentCommand;
            for (Hero hero : heroesList) {
                if(hero.isAlive){
                    int checkX = hero.currentCellX;
                    int checkY = hero.currentCellY;
                    boolean hasOtherThing = false;
                    switch (ifComand.direction) {
                        case "Слева-Сверху":  checkX--; checkY--; break;
                        case "Сверху":        checkY--; break;
                        case "Справа-Сверху": checkX++; checkY--; break;
                        case "Слева":         checkX--; break;
                        case "Справа":        checkX++; break;
                        case "Слева-Снизу":   checkX--; checkY++; break;
                        case "Снизу":         checkY++; break;
                        case "Справа-Снизу":  checkX++; checkY++; break;
                    }
                    switch (ifComand.target) {
                        case "Герой":
                            for (Hero other : heroesList) {
                                if (other == hero) continue;
                                if (hero.currentCellX == checkX && hero.currentCellY == checkY) {
                                    hasOtherThing = true;
                                    break;
                                }
                            }
                        case "Блок":
                            for (blockData block : blockList) {
                                if (block.currentCellX == checkX && block.currentCellY == checkY) {
                                    hasOtherThing = true;
                                    break;
                                }
                            }
                        case "Яма":
                            for (Point pit : pitCells) {
                                if (pit.x == checkX && pit.y == checkY) {
                                    hasOtherThing = true;
                                    break;
                                }
                            }
                    }
                    switch (ifComand.operator) {
                        case "==":
                            hero.ifTrue = hasOtherThing;
                            break;
                        case "!=":
                            hero.ifTrue = !hasOtherThing;
                            break;
                    }
                }
            }
        }else{
            String stringComand = (String) currentCommand;
            moveComand(stringComand);
        }
    }
    public void moveComand(String stringComand){
         
        for (Hero hero : heroesList) {
            if(hero.isAlive){
                if(stringComand.contains("└")&& hero.ifTrue){
                    switch (stringComand) {
                    case "    └ Влево":
                        if (hero.targetX > CELL_SIZE / 2) { 
                            hero.targetX -= CELL_SIZE;
                            hero.currentCellX--;
                        }
                        break;
                    case "    └ Вправо":
                        if (hero.targetX < CELL_SIZE * colCellWight - CELL_SIZE) {
                            hero.targetX += CELL_SIZE;
                            hero.currentCellX++;
                        }
                        break;
                    case "    └ Вверх":
                        if (hero.targetY > CELL_SIZE / 2) { 
                            hero.targetY -= CELL_SIZE;
                            hero.currentCellY--;
                        }
                        break;
                    case "    └ Вниз":
                        if (hero.targetY < CELL_SIZE * colCellHeight - CELL_SIZE) {
                            hero.targetY += CELL_SIZE;
                            hero.currentCellY++;
                        }
                        break;
                    case "    └ Поднять":
                        for (blockData block : blockList) {
                            if(hero.currentCellX == block.currentCellX && hero.currentCellY == block.currentCellY){
                                block.carrier = hero;
                                hero.carrier = block;
                                break;
                            }
                        }
                        break;
                    case "    └ Положить":
                        if(hero.carrier != null){
                            hero.carrier.carrier = null;
                            hero.carrier = null;
                        }
                        break;
                    case "}":
                        hero.ifTrue = false;
                    default:
                        break;
                    }
                }
                switch (stringComand) {
                    case "Влево":
                        if (hero.targetX > CELL_SIZE / 2) { 
                            hero.targetX -= CELL_SIZE;
                            hero.currentCellX--;
                        }
                        break;
                    case "Вправо":
                        if (hero.targetX < CELL_SIZE * colCellWight - CELL_SIZE) {
                            hero.targetX += CELL_SIZE;
                            hero.currentCellX++;
                        }
                        break;
                    case "Вверх":
                        if (hero.targetY > CELL_SIZE / 2) { 
                            hero.targetY -= CELL_SIZE;
                            hero.currentCellY--;
                        }
                        break;
                    case "Вниз":
                        if (hero.targetY < CELL_SIZE * colCellHeight - CELL_SIZE) {
                            hero.targetY += CELL_SIZE;
                            hero.currentCellY++;
                        }
                        break;
                    case "Поднять":
                        for (blockData block : blockList) {
                            if(hero.currentCellX == block.currentCellX && hero.currentCellY == block.currentCellY){
                                block.carrier = hero;
                                hero.carrier = block;
                                break;
                            }
                        }
                        break;
                    case "Положить":
                        if(hero.carrier != null){
                            hero.carrier.carrier = null;
                            hero.carrier = null;
                        }
                        break;
                    default:
                        break;
                }
            }
            for (blockData block : blockList) {
                if(block.isAlive){
                    if (block.carrier == hero) {
                        block.currentCellX = hero.currentCellX;
                        block.currentCellY = hero.currentCellY;
                    }
                }
            }
        }
    }
    public void checkPits() {
        for (Hero hero : heroesList) {
            for (java.awt.Point pit : pitCells) {
                if (hero.currentCellX == pit.x && hero.currentCellY == pit.y) {
                    hero.isAlive = false;
                    hero.panel.setOpaque(false);
                    break;
                }
            }
        }
        for (blockData block : blockList) {
            for (java.awt.Point pit : pitCells) {
                if (block.currentCellX == pit.x && block.currentCellY == pit.y) {
                    block.isAlive = false;
                    block.panel.setOpaque(false);
                    break;
                }
            }
        }
        repaint();
    }

    
    
    protected  static class Hero {
        JPanel panel;
        int currentX, currentY;
        int targetX, targetY;
        int baseX, baseY;
        int currentCellX, currentCellY;
        int heroSizeX, heroSizeY;
        boolean ifTrue;
        boolean isAlive;
        blockData carrier;

        public Hero(int baseX, int baseY) {
            this.carrier = null;
            this.ifTrue = false;
            this.isAlive = true;
            this.heroSizeX = CELL_SIZE - 8 * cof;
            this.heroSizeY = CELL_SIZE - 8 * cof;
            this.baseX = baseX;
            this.baseY = baseY;
            this.panel = new JPanel();
            this.panel.setBackground(Color.CYAN);
            this.panel.setSize(heroSizeX, heroSizeY);
            this.panel.setOpaque(true);
            this.currentX = baseX;
            this.currentY = baseY;
            this.targetX = baseX;
            this.targetY = baseY;
            this.currentCellX = currentX/CELL_SIZE;
            this.currentCellY = currentY/CELL_SIZE;
            this.panel.setLocation(baseX, baseY);
        }
        public void returnBase(){
            this.carrier = null;
            panel.setLocation(baseX, baseY);
            this.ifTrue = false;
            this.isAlive = true;
            this.panel.setOpaque(true);
            this.currentX = baseX;
            this.currentY = baseY;
            this.targetX = baseX;
            this.targetY = baseY;
            this.currentCellX = currentX/CELL_SIZE;
            this.currentCellY = currentY/CELL_SIZE;
        }
    }
    protected  static class blockData {
        JPanel panel;
        int baseX, baseY;
        int currentCellX, currentCellY;
        int blockSizeX, blockSizeY;
        Hero carrier; 
        boolean isAlive;

        public blockData(int baseX, int baseY) {
            this.carrier = null;
            this.isAlive = true;
            this.blockSizeX = CELL_SIZE - 16 * cof;
            this.blockSizeY = CELL_SIZE - 16 * cof;
            this.baseX = baseX;
            this.baseY = baseY;
            this.panel = new JPanel();
            this.panel.setBackground(Color.RED);
            this.panel.setSize(blockSizeX, blockSizeY);
            this.panel.setOpaque(true);
            this.currentCellX = baseX/CELL_SIZE;
            this.currentCellY = baseY/CELL_SIZE;
            this.panel.setLocation(baseX, baseY);
        }
        public void returnBase(){
            panel.setLocation(baseX, baseY);
            this.carrier = null;
            this.isAlive = true;
            this.currentCellX = baseX/CELL_SIZE;
            this.panel.setOpaque(true);
            this.currentCellY = baseY/CELL_SIZE;
        }
    }
    protected static class ConditionalCommand {
        String direction;
        String operator;
        String target;
        public ConditionalCommand(String direction, String operator, String target) {
            this.direction = direction;
            this.operator = operator;
            this.target = target;
        }
        @Override
        public String toString() {
            return "ЕСЛИ (" + direction + " " + operator + " " + target + ") {";
        }
    }
    abstract public void setingLevel();
    abstract public void task();
}
            
