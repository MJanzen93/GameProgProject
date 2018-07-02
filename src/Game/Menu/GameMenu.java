package Game.Menu;

import ch.aplu.xboxcontroller.XboxControllerListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameMenu extends JPanel implements MouseListener, KeyListener
{
    private GraphicsConfiguration graphicsConf =
            GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().getDefaultConfiguration();
    private BufferedImage imageBuffer;
    private Graphics      graphics;
    private Image 	  	  imageBG;
    private BufferedImage imageMenuBar;
    private BufferedImage imageMenuBarBig;
    private BufferedImage imageSign;
    private BufferedImage imageSignLeft;
    private BufferedImage imageSignRight;
    private BufferedImage imageTimeOut;
    
    private String currentPlayer = "";
    private int currentLevel = 0;
    private int currentScore = 0;
    
    // DUMMY Componentes for the Game
    JLabel game = new JLabel("THE GAME");
    JLabel gameLabelPlayer = new JLabel();
    JLabel gameLabelScoreTitle = new JLabel("Score");
    JLabel gameLabelLevelTitle = new JLabel("Level");
    JLabel gameLabelScore = new JLabel();
    JLabel gameLabelLevel = new JLabel();
    JButton gameTimeOut = new JButton("Break");
    
    // Components for TimeOut
    JLabel timeoutLabel = new JLabel("Break");  
    JButton timeoutBtnContinue = new JButton("Continue Game");
    JButton timeoutBtnSaveAndContinue = new JButton("Save and Continue Game");
    JButton timeoutBtnSaveAndBackToMenu = new JButton("Save and Back to Menu");
    JButton timeoutBtnBackToMenuWithoutSaving = new JButton("Back to Menu without Saving");
    
    // Components for Menu    
    JLabel menuLabel = new JLabel("Game Programming - GAME");  
    JButton menuStartGameBtn = new JButton("Start New Game");
    JButton menuLoadGameBtn = new JButton("Load Game");
    JButton menuOptionBtn = new JButton("Options");
    JButton menuImpressumBtn = new JButton("Impressum");
    JButton menuExitBtn = new JButton("Exit");
    
    // Components for Start Game
    JLabel startLabel = new JLabel("Start New Game");
    JButton startGame = new JButton("Start Game");
    JTextField startGameTextPlayerOne = new JTextField("");
    JLabel startGameLabelPlayerOne = new JLabel("Players Name");
    JLabel startFieldRequired = new JLabel("*name is required or exist already");
    boolean startNameRequired = false;
    
    // Components for Load Game
    JLabel loadLabel = new JLabel("Load Game");
    JButton loadSelectedGame = new JButton("Load Selection");
    JButton loadDeleteSelectedGame = new JButton("Delete Selection");
    
    JLabel loadStoredGamesLabel = new JLabel("Stored Games");
    JLabel loadPlayerLabelForTabel = new JLabel("Player");
    JLabel loadLevelLabelForTabel = new JLabel("Level");
    JLabel loadScoreLabelForTabel = new JLabel("Score");
    
    JLabel loadOneOfThisGameLabel = new JLabel("Type in one of the Players Name");
    JTextField loadOneOfThisGameText = new JTextField();
    JLabel loadFieldWarning = new JLabel("*players name not exist");
    boolean loadSelectionWarning = false;
    
    JTextArea loadStoredGamesTextArea = new JTextArea();
    JScrollPane loadStoredGamesScrollPane;

    ArrayList<String> loadStoredGamesWholeData;
    
    // Components for Options
    JLabel optionLabel = new JLabel("Options");
    
    JLabel optionLabelSound = new JLabel("Sound");
    JLabel optionLabelSoundOn = new JLabel("On");
    JLabel optionLabelSoundOff = new JLabel("Off");
    ButtonGroup optionRadioBtnGroupSound = new ButtonGroup();
    JRadioButton optionRadioBtnSoundOn = new JRadioButton();
    JRadioButton optionRadioBtnSoundOff = new JRadioButton();
    
    JLabel optionLabelGrade = new JLabel("Grade");
    JLabel optionLabelGradeEasy = new JLabel("Easy");
    JLabel optionLabelGradeMiddle = new JLabel("Middle");
    JLabel optionLabelGradeHeavy = new JLabel("Heavy");
    ButtonGroup optionRadioBtnGroupGrade = new ButtonGroup();
    JRadioButton optionRadioBtnGradeEasy = new JRadioButton();
    JRadioButton optionRadioBtnGradeMiddle = new JRadioButton();
    JRadioButton optionRadioBtnGradeHeavy = new JRadioButton();
    
    JLabel optionLabelColorPlayer = new JLabel("Color of Player");
    JLabel optionLabelColorBlue = new JLabel("Blue");
    JLabel optionLabelColorRed = new JLabel("Red");
    JLabel optionLabelColorBlack = new JLabel("Black");
    ButtonGroup optionRadioBtnGroupPlayerOne = new ButtonGroup();
    JRadioButton optionRadioBtnPlayerOneBlue = new JRadioButton();
    JRadioButton optionRadioBtnPlayerOneRed = new JRadioButton();
    JRadioButton optionRadioBtnPlayerOneBlack = new JRadioButton();

    // Components for Impressum
    JLabel impressumLabel = new JLabel("Impressum");
    JTextArea impressumText = new JTextArea();
    
    // Components for Save Game
    JLabel saveLabel = new JLabel("Save Game");

    // Components for all
    JButton backToMenu = new JButton("<<< Back To Menu");
    
    int xSignPos, ySignPos;
    
    int hoveredBtnId = 1;
    public int clickedPanel = 0;
    
	public GameMenu() {
        this.setSize(ConstantValues.WORLDPART_WIDTH,ConstantValues.WORLDPART_HEIGHT);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        imageBuffer = graphicsConf.createCompatibleImage(
                this.getWidth(), this.getHeight());
        graphics = imageBuffer.getGraphics();
        
        readImagesFromFiles();
        
        initializeComponentsForPanelGame();
        initializeComponentsForPanelTimeOut();
        initializeComponentsForPanelMenu();
        initializeComponentsForPanelStartGame();
        initializeComponentsForPanelLoadGame();
        initializeComponentsForPanelOption();
        initializeComponentsForPanelImpressum();
        initializeComponentsForPanelSaveGame();
        initializeComponentsForAllPanels();
    }

    public void clear() { 
    	graphics.setColor(new Color(192, 253, 255));
        graphics.fillRect(0, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLD_HEIGHT);
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(imageBG, 0, 0, ConstantValues.WORLDPART_WIDTH,ConstantValues.WORLDPART_HEIGHT, null);
    	if (clickedPanel == 100) {			// Panel GAME
    		//drawPanelGame(g);
    		
    	} else if (clickedPanel == 99) {	// Panel TimeOut
    		drawPanelTimeOut(g);
    		
    	} else if (clickedPanel == 0) {		// Panel Menu
    		drawPanelMenu(g);
	    	
    	} else if (clickedPanel == 1) {		// Panel Start Game	        
	    	drawPanelStartGame(g);
		    
    	} else if (clickedPanel == 2) {		// Panel Load Game	        
	    	drawPanelLoadGame(g);
    	
    	} else if (clickedPanel == 3) {	    // Panel Options
	    	drawPanelOption(g);
		    
    	} else if (clickedPanel == 4) {	    // Panel Impressum
	    	drawPanelImpressum(g);
		    
    	} else if (clickedPanel == 5) {		// Exit
    		System.exit(0);
    	}
    }
    
    private void readImagesFromFiles() {
    	try {
			imageBG = ImageIO.read(new File("src/Game/Textures/DBG.png"));
			imageMenuBar = ImageIO.read(new File("src/Game/Menu/Texture/item_menu_kl.png"));
	        imageMenuBarBig = ImageIO.read(new File("src/Game/Menu/Texture/item_menu.png"));
	        imageSign = ImageIO.read(new File("src/Game/Menu/Texture/sign_toRight.png"));
	        imageSignLeft = ImageIO.read(new File("src/Game/Menu/Texture/sign_toLeft_big.png"));
	        imageSignRight = ImageIO.read(new File("src/Game/Menu/Texture/sign_toRight_big.png"));
	        imageTimeOut = ImageIO.read(new File("src/Game/Menu/Texture/Sign.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}   
    }
    
	/////////////////////////////////////////////
	/////  Initialize Componentes /////////////////
	///////////////////////////////////////
    
    private void initializeComponentsForPanelGame() {
        game.setBounds(500, 300, 400, 100);
        game.setFont(new Font("Courier", Font.BOLD, 50));
        
        gameLabelPlayer.setBounds(50, 10, 300, 50);
        gameLabelPlayer.setFont(new Font("Courier", Font.BOLD, 20));
        
        gameLabelScoreTitle.setBounds(1000, 30, 100, 50);
        gameLabelScoreTitle.setFont(new Font("Courier", Font.ITALIC, 16));
        
        gameLabelLevelTitle.setBounds(1200, 30, 100, 50);
        gameLabelLevelTitle.setFont(new Font("Courier", Font.ITALIC, 16));
        
        gameLabelScore.setBounds(1000, 10, 100, 50);
        gameLabelScore.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 28));
        
        gameLabelLevel.setBounds(1200, 10, 100, 50);
        gameLabelLevel.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 28));
        
        gameTimeOut.setForeground(Color.WHITE);
        gameTimeOut.setFont(new Font("Courier", Font.BOLD, 18));
        
        gameTimeOut.setBounds(20, 50, 150, 150);		// x, y, width, height
        gameTimeOut.setIcon(new ImageIcon(imageTimeOut));
        gameTimeOut.setBackground(Color.WHITE);
        gameTimeOut.setOpaque(false);
        gameTimeOut.setHorizontalTextPosition(JButton.CENTER);
        gameTimeOut.setVerticalTextPosition(JButton.CENTER);
        gameTimeOut.setBorderPainted(false);
        gameTimeOut.addMouseListener(this);
    }
    
    private void initializeComponentsForPanelTimeOut() {
    	// Create Components for Panel Time Out
        timeoutLabel.setBounds(1150, 10, 300, 50);
        timeoutLabel.setFont(new Font("Courier", Font.BOLD, 20));
        
        timeoutBtnContinue.setForeground(Color.WHITE);
        timeoutBtnContinue.setFont(new Font("Courier", Font.BOLD, 24));
        
        timeoutBtnContinue.setBounds(450, 100, 500, 50);		// x, y, width, height
        timeoutBtnContinue.setIcon(new ImageIcon(imageMenuBar));
        timeoutBtnContinue.setBackground(Color.WHITE);
        timeoutBtnContinue.setOpaque(false);
        timeoutBtnContinue.setHorizontalTextPosition(JButton.CENTER);
        timeoutBtnContinue.setVerticalTextPosition(JButton.CENTER);
        timeoutBtnContinue.setBorderPainted(false);
        timeoutBtnContinue.addMouseListener(this);
        
        timeoutBtnSaveAndContinue.setForeground(Color.WHITE);
        timeoutBtnSaveAndContinue.setFont(new Font("Courier", Font.BOLD, 24));
        
        timeoutBtnSaveAndContinue.setBounds(380, 200, 500, 50);		// x, y, width, height
        timeoutBtnSaveAndContinue.setIcon(new ImageIcon(imageMenuBarBig));
        timeoutBtnSaveAndContinue.setBackground(Color.WHITE);
        timeoutBtnSaveAndContinue.setOpaque(false);
        timeoutBtnSaveAndContinue.setHorizontalTextPosition(JButton.CENTER);
        timeoutBtnSaveAndContinue.setVerticalTextPosition(JButton.CENTER);
        timeoutBtnSaveAndContinue.setBorderPainted(false);
        timeoutBtnSaveAndContinue.addMouseListener(this);
        
        timeoutBtnSaveAndBackToMenu.setForeground(Color.WHITE);
        timeoutBtnSaveAndBackToMenu.setFont(new Font("Courier", Font.BOLD, 24));
        
        timeoutBtnSaveAndBackToMenu.setBounds(550, 300, 500, 50);		// x, y, width, height
        timeoutBtnSaveAndBackToMenu.setIcon(new ImageIcon(imageMenuBarBig));
        timeoutBtnSaveAndBackToMenu.setBackground(Color.WHITE);
        timeoutBtnSaveAndBackToMenu.setOpaque(false);
        timeoutBtnSaveAndBackToMenu.setHorizontalTextPosition(JButton.CENTER);
        timeoutBtnSaveAndBackToMenu.setVerticalTextPosition(JButton.CENTER);
        timeoutBtnSaveAndBackToMenu.setBorderPainted(false);
        timeoutBtnSaveAndBackToMenu.addMouseListener(this);
        
        timeoutBtnBackToMenuWithoutSaving.setForeground(Color.WHITE);
        timeoutBtnBackToMenuWithoutSaving.setFont(new Font("Courier", Font.BOLD, 24));
        
        timeoutBtnBackToMenuWithoutSaving.setBounds(470, 400, 500, 50);		// x, y, width, height
        timeoutBtnBackToMenuWithoutSaving.setIcon(new ImageIcon(imageMenuBarBig));
        timeoutBtnBackToMenuWithoutSaving.setBackground(Color.WHITE);
        timeoutBtnBackToMenuWithoutSaving.setOpaque(false);
        timeoutBtnBackToMenuWithoutSaving.setHorizontalTextPosition(JButton.CENTER);
        timeoutBtnBackToMenuWithoutSaving.setVerticalTextPosition(JButton.CENTER);
        timeoutBtnBackToMenuWithoutSaving.setBorderPainted(false);
        timeoutBtnBackToMenuWithoutSaving.addMouseListener(this);
    }
    
    private void initializeComponentsForPanelMenu() {
    	// Create Components for Panel Menu
        menuLabel.setBounds(1050, 10, 300, 50);
        menuLabel.setFont(new Font("Courier", Font.BOLD, 20));
                
        menuStartGameBtn.setForeground(Color.WHITE);
        menuStartGameBtn.setFont(new Font("Courier", Font.BOLD, 24));
        
        menuStartGameBtn.setBounds(450, 100, 500, 50);		// x, y, width, height
        menuStartGameBtn.setIcon(new ImageIcon(imageMenuBar));
        menuStartGameBtn.setBackground(Color.WHITE);
        menuStartGameBtn.setOpaque(false);
        menuStartGameBtn.setHorizontalTextPosition(JButton.CENTER);
        menuStartGameBtn.setVerticalTextPosition(JButton.CENTER);
        menuStartGameBtn.setBorderPainted(false);
        menuStartGameBtn.addMouseListener(this);
        
        menuLoadGameBtn.setForeground(Color.WHITE);
        menuLoadGameBtn.setFont(new Font("Courier", Font.BOLD, 24));
        
        menuLoadGameBtn.setBounds(230, 180, 500, 50);		// x, y, width, height
        menuLoadGameBtn.setIcon(new ImageIcon(imageMenuBar));
        menuLoadGameBtn.setBackground(Color.WHITE);
        menuLoadGameBtn.setOpaque(false);
        menuLoadGameBtn.setHorizontalTextPosition(JButton.CENTER);
        menuLoadGameBtn.setVerticalTextPosition(JButton.CENTER);
        menuLoadGameBtn.setBorderPainted(false);
        menuLoadGameBtn.addMouseListener(this);
        
        menuOptionBtn.setForeground(Color.WHITE);
        menuOptionBtn.setFont(new Font("Courier", Font.BOLD, 24));
        
        menuOptionBtn.setBounds(500, 260, 500, 50);
        menuOptionBtn.setIcon(new ImageIcon(imageMenuBar));
        menuOptionBtn.setBackground(Color.WHITE);
        menuOptionBtn.setOpaque(false);
        menuOptionBtn.setHorizontalTextPosition(JButton.CENTER);
        menuOptionBtn.setVerticalTextPosition(JButton.CENTER);
        menuOptionBtn.setBorderPainted(false);
        menuOptionBtn.addMouseListener(this);
        
        menuImpressumBtn.setForeground(Color.WHITE);
        menuImpressumBtn.setFont(new Font("Courier", Font.BOLD, 24));
        
        menuImpressumBtn.setBounds(670, 340, 500, 50);
        menuImpressumBtn.setIcon(new ImageIcon(imageMenuBar));
        menuImpressumBtn.setBackground(Color.WHITE);
        menuImpressumBtn.setOpaque(false);
        menuImpressumBtn.setHorizontalTextPosition(JButton.CENTER);
        menuImpressumBtn.setVerticalTextPosition(JButton.CENTER);
        menuImpressumBtn.setBorderPainted(false);
        menuImpressumBtn.addMouseListener(this);
        
        menuExitBtn.setForeground(Color.WHITE);
        menuExitBtn.setFont(new Font("Courier", Font.BOLD, 24));
        
        menuExitBtn.setBounds(310, 400, 500, 50);
        menuExitBtn.setIcon(new ImageIcon(imageMenuBar));
        menuExitBtn.setBackground(Color.WHITE);
        menuExitBtn.setOpaque(false);
        menuExitBtn.setHorizontalTextPosition(JButton.CENTER);
        menuExitBtn.setVerticalTextPosition(JButton.CENTER);
        menuExitBtn.setBorderPainted(false);
        menuExitBtn.addMouseListener(this);
    }
    
    private void initializeComponentsForPanelStartGame() {
    	// Create Components for Panel Start Game     
        startLabel.setBounds(1150, 10, 300, 50);
        startLabel.setFont(new Font("Courier", Font.BOLD, 20));
        
        startFieldRequired.setBounds(450, 330, 300, 50);
        startFieldRequired.setFont(new Font("Courier", Font.ROMAN_BASELINE, 14));
        startFieldRequired.setForeground(Color.RED);
        
        startGame.setForeground(Color.WHITE);
        startGame.setFont(new Font("Courier", Font.BOLD, 20));
        
        startGame.setBounds(900, 620, 500, 50);
        startGame.setIcon(new ImageIcon(imageSignRight));
        startGame.setBackground(Color.WHITE);
        startGame.setOpaque(false);
        startGame.setHorizontalTextPosition(JButton.CENTER);
        startGame.setVerticalTextPosition(JButton.CENTER);
        startGame.setBorderPainted(false);
        startGame.addMouseListener(this);

        startGameTextPlayerOne.setBounds(450, 300, 300, 35);
        startGameLabelPlayerOne.setBounds(200, 300, 200, 35);
 
        startGameTextPlayerOne.setFont(new Font("Courier", Font.ROMAN_BASELINE, 22));
        startGameLabelPlayerOne.setFont(new Font("Courier", Font.BOLD, 22));
    }
    
    private void initializeComponentsForPanelLoadGame() {
    	// Create Components for Panel Load Game       
        loadLabel.setBounds(1150, 10, 300, 50);
        loadLabel.setFont(new Font("Courier", Font.BOLD, 20));
        
        loadFieldWarning.setBounds(600, 240, 300, 50);
        loadFieldWarning.setFont(new Font("Courier", Font.ROMAN_BASELINE, 14));
        loadFieldWarning.setForeground(Color.RED);
        
        loadStoredGamesLabel.setBounds(200, 150, 400, 35);
        loadStoredGamesLabel.setFont(new Font("Courier", Font.BOLD, 22));
        
        loadOneOfThisGameLabel.setBounds(600, 150, 600, 35);
        loadOneOfThisGameLabel.setFont(new Font("Courier", Font.BOLD, 22));
        
        loadOneOfThisGameText.setBounds(600, 200, 300, 35);
        loadOneOfThisGameText.setFont(new Font("Courier", Font.ROMAN_BASELINE, 22));
      
      	loadPlayerLabelForTabel.setBounds(200, 200, 200, 35);
      	loadPlayerLabelForTabel.setFont(new Font("Courier", Font.ROMAN_BASELINE, 18));
      	
      	loadLevelLabelForTabel.setBounds(350, 200, 200, 35);
      	loadLevelLabelForTabel.setFont(new Font("Courier", Font.ROMAN_BASELINE, 18));
      	
      	loadScoreLabelForTabel.setBounds(450, 200, 200, 35);
      	loadScoreLabelForTabel.setFont(new Font("Courier", Font.ROMAN_BASELINE, 18));
      	
      	loadStoredGamesScrollPane = new JScrollPane(loadStoredGamesTextArea);
      	loadStoredGamesScrollPane.setBounds(200, 250, 350, 320);
      	loadStoredGamesScrollPane.setBackground(new Color(192, 253, 255));
      	loadStoredGamesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      	
      	loadStoredGamesTextArea.setBounds(200, 250, 350, 320);
      	loadStoredGamesTextArea.setBackground(new Color(192, 253, 255));
      	loadStoredGamesTextArea.setFont(new Font("Courier", Font.ROMAN_BASELINE, 18));
      	loadStoredGamesTextArea.setOpaque(false);
      	loadStoredGamesTextArea.setEditable(false);
      	
        loadSelectedGame.setForeground(Color.WHITE);
        loadSelectedGame.setFont(new Font("Courier", Font.BOLD, 20));
        
        loadSelectedGame.setBounds(900, 550, 500, 50);
        loadSelectedGame.setIcon(new ImageIcon(imageSignRight));
        loadSelectedGame.setBackground(Color.WHITE);
        loadSelectedGame.setOpaque(false);
        loadSelectedGame.setHorizontalTextPosition(JButton.CENTER);
        loadSelectedGame.setVerticalTextPosition(JButton.CENTER);
        loadSelectedGame.setBorderPainted(false);
        loadSelectedGame.addMouseListener(this);
        
        loadDeleteSelectedGame.setForeground(Color.WHITE);
        loadDeleteSelectedGame.setFont(new Font("Courier", Font.BOLD, 20));
        
        loadDeleteSelectedGame.setBounds(900, 620, 500, 50);
        loadDeleteSelectedGame.setIcon(new ImageIcon(imageSignRight));
        loadDeleteSelectedGame.setBackground(Color.WHITE);
        loadDeleteSelectedGame.setOpaque(false);
        loadDeleteSelectedGame.setHorizontalTextPosition(JButton.CENTER);
        loadDeleteSelectedGame.setVerticalTextPosition(JButton.CENTER);
        loadDeleteSelectedGame.setBorderPainted(false);
        loadDeleteSelectedGame.addMouseListener(this);
        
        String line;
        BufferedReader fileIn = null;
        try {
             fileIn = new BufferedReader(new FileReader("src/Game/Menu/Files/ScoresOnePlayer.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        loadStoredGamesWholeData = new ArrayList<String>();
		try {
			while ((line = fileIn.readLine()) != null) {
				loadStoredGamesWholeData.add(line);
			}
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void initializeComponentsForPanelOption() {
    	// Create Components for Panel Option Game        
        optionLabel.setBounds(1150, 10, 300, 50);
        optionLabel.setFont(new Font("Courier", Font.BOLD, 20));
                 
        optionLabelSound.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelSound.setBounds(200, 120, 200, 50);
        
        optionLabelSoundOn.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelSoundOn.setBounds(550, 120, 200, 50);
        
        optionLabelSoundOff.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelSoundOff.setBounds(750, 120, 200, 50);
        
        optionRadioBtnSoundOn = new JRadioButton();
        optionRadioBtnSoundOn.setBounds(500, 130, 50, 35);
        optionRadioBtnSoundOn.setBackground(new Color(192, 253, 255));
        optionRadioBtnSoundOn.setOpaque(false);
        optionRadioBtnSoundOn.setSelected(true);

        optionRadioBtnSoundOff = new JRadioButton();
        optionRadioBtnSoundOff.setBounds(700, 130, 50, 35);
        optionRadioBtnSoundOff.setBackground(new Color(192, 253, 255));
        optionRadioBtnSoundOff.setOpaque(false);
        
        // Option Grade
 
        optionLabelGrade.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelGrade.setBounds(200, 220, 200, 50);
        
        optionLabelGradeEasy.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelGradeEasy.setBounds(550, 220, 200, 50);
        
        optionLabelGradeMiddle.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelGradeMiddle.setBounds(750, 220, 200, 50);
        
        optionLabelGradeHeavy.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelGradeHeavy.setBounds(950, 220, 200, 50);
        
        optionRadioBtnGradeEasy = new JRadioButton();
        optionRadioBtnGradeEasy.setBounds(500, 230, 50, 35);
        optionRadioBtnGradeEasy.setBackground(new Color(192, 253, 255));
        optionRadioBtnGradeEasy.setOpaque(false);
        
        optionRadioBtnGradeMiddle = new JRadioButton();
        optionRadioBtnGradeMiddle.setBounds(700, 230, 50, 35);
        optionRadioBtnGradeMiddle.setBackground(new Color(192, 253, 255));
        optionRadioBtnGradeMiddle.setOpaque(false);
        optionRadioBtnGradeMiddle.setSelected(true);
        
        optionRadioBtnGradeHeavy = new JRadioButton();
        optionRadioBtnGradeHeavy.setBounds(900, 230, 50, 35);
        optionRadioBtnGradeHeavy.setBackground(new Color(192, 253, 255));
        optionRadioBtnGradeHeavy.setOpaque(false);
        
        // Option Player Color
        optionLabelColorPlayer.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelColorPlayer.setBounds(200, 320, 300, 50);
           
        optionLabelColorBlue.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelColorBlue.setBounds(550, 320, 200, 50);
        
        optionLabelColorRed.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelColorRed.setBounds(750, 320, 200, 50);
        
        optionLabelColorBlack.setFont(new Font("Courier", Font.BOLD, 22));
        optionLabelColorBlack.setBounds(950, 320, 200, 50);

        optionRadioBtnPlayerOneBlue = new JRadioButton();
        optionRadioBtnPlayerOneBlue.setBounds(500, 320, 50, 35);
        optionRadioBtnPlayerOneBlue.setBackground(new Color(192, 253, 255));
        optionRadioBtnPlayerOneBlue.setOpaque(false);
        optionRadioBtnPlayerOneBlue.setSelected(true);
        
        optionRadioBtnPlayerOneRed = new JRadioButton();
        optionRadioBtnPlayerOneRed.setBounds(700, 320, 50, 35);
        optionRadioBtnPlayerOneRed.setBackground(new Color(192, 253, 255));
        optionRadioBtnPlayerOneRed.setOpaque(false);
        
        optionRadioBtnPlayerOneBlack = new JRadioButton();
        optionRadioBtnPlayerOneBlack.setBounds(900, 320, 50, 35);
        optionRadioBtnPlayerOneBlack.setBackground(new Color(192, 253, 255));
        optionRadioBtnPlayerOneBlack.setOpaque(false);    
    }
    
    private void initializeComponentsForPanelImpressum() {
    	// Create Components for Panel Impressum Game       
        impressumLabel.setBounds(1150, 10, 300, 50);
        impressumLabel.setFont(new Font("Courier", Font.BOLD, 20));
        
        impressumText.setBounds(200, 200, 700, 200);
        impressumText.setBackground(new Color(192, 253, 255));
        impressumText.setOpaque(false);
        impressumText.setFont(new Font("Courier", Font.ROMAN_BASELINE, 16));
        impressumText.setText("This Game was created by a group of students \nat the Hochschule Ulm in the lecture Game Programming. \n\n" +
        		"Horn, Franz \nJanzen, Maxim \nSchlathauer, Johann \nUnger, Ramona \nWeigerstorfer, Patrick");
    }
    
    private void initializeComponentsForPanelSaveGame() {
    	// Create Components for Panel Save Game      
        saveLabel.setBounds(1150, 10, 300, 50);
        saveLabel.setFont(new Font("Courier", Font.BOLD, 20));
    }
    
    private void initializeComponentsForAllPanels() {
    	// Create Components for all Panels
        backToMenu.setForeground(Color.WHITE);
        backToMenu.setFont(new Font("Courier", Font.BOLD, 20));
        
        backToMenu.setBounds(50, 620, 300, 50);
        backToMenu.setIcon(new ImageIcon(imageSignLeft));
        backToMenu.setBackground(Color.WHITE);
        backToMenu.setOpaque(false);
        backToMenu.setHorizontalTextPosition(JButton.CENTER);
        backToMenu.setVerticalTextPosition(JButton.CENTER);
        backToMenu.setBorderPainted(false);
        backToMenu.addMouseListener(this);
    }
    
	/////////////////////////////////////////////
	/////  Draw Panels /////////////////
	///////////////////////////////////////
    
    private void drawPanelGame(Graphics g) {
    	add(game);
		add(gameLabelPlayer);
		add(gameLabelScoreTitle);
		add(gameLabelLevelTitle);
		add(gameLabelScore);
		add(gameLabelLevel);	
		add(gameTimeOut);
    }
    
    private void drawPanelTimeOut(Graphics g) {
    	add(timeoutLabel);

		add(timeoutBtnContinue);
        add(timeoutBtnSaveAndContinue); 
        add(timeoutBtnSaveAndBackToMenu);
        add(timeoutBtnBackToMenuWithoutSaving);
        
        JButton hoveredBtn = new JButton();
		switch (hoveredBtnId) {
		    case 1: 
		    	hoveredBtn = timeoutBtnContinue;
		    	break;
		    case 2:
		    	hoveredBtn = timeoutBtnSaveAndContinue;
		    	break;
		    case 3:
		    	hoveredBtn = timeoutBtnSaveAndBackToMenu;
		    	break;
		    case 4:
		    	hoveredBtn = timeoutBtnBackToMenuWithoutSaving;
		    	break;
		}
	    setSignPosition(hoveredBtn);
    	g.drawImage(imageSign, xSignPos, ySignPos, null);
        
		g.setColor(Color.black);
	    g.drawLine(1150, 50, 1400, 50);
    }
    
    private void drawPanelMenu(Graphics g) {
    	add(menuLabel);
	    add(menuStartGameBtn);
	    add(menuLoadGameBtn);
	    add(menuOptionBtn);
	    add(menuImpressumBtn);
	    add(menuExitBtn);
	    		     
	    JButton hoveredBtn = new JButton();
		switch (hoveredBtnId) {
		    case 1:
		    	hoveredBtn = menuStartGameBtn;
		    	break;
		    case 2:
		    	hoveredBtn = menuLoadGameBtn;
		    	break;
		    case 3:
		    	hoveredBtn = menuOptionBtn;
		    	break;
		    case 4:
		    	hoveredBtn = menuImpressumBtn;
		    	break;
		    case 5:
		    	hoveredBtn = menuExitBtn;
		    	break;
	    }
	    
	    setSignPosition(hoveredBtn);
	    g.drawImage(imageSign, xSignPos, ySignPos, null);
	    
	    g.setColor(Color.black);
	    g.drawLine(1050, 50, 1400, 50);    	
    }
    
    private void drawPanelStartGame(Graphics g) {
    	add(startLabel);
    	
    	if(startNameRequired == true) {
    		add(startFieldRequired);
    	}
    	
    	add(startGame);
         
        add(startGameLabelPlayerOne);
        add(startGameTextPlayerOne);       
    	
    	add(backToMenu);
    	
    	g.setColor(Color.black);
	    g.drawLine(1150, 50, 1400, 50);
    }
    
    private void drawPanelLoadGame(Graphics g) {
    	add(loadLabel);
    	
    	add(loadSelectedGame);
    	add(loadDeleteSelectedGame);
    	
    	if(loadSelectionWarning == true) {
    		add(loadFieldWarning);
    	}
    	
    	add(loadStoredGamesLabel);
    	add(loadOneOfThisGameLabel);
    	
    	add(loadPlayerLabelForTabel);
    	add(loadLevelLabelForTabel);
    	add(loadScoreLabelForTabel);
    	
    	add(loadOneOfThisGameText);
    	 
    	String playerDataForTextOutput = "";

    	for (String data: loadStoredGamesWholeData) {
    		String[] playerData = data.split(" ");
    		playerDataForTextOutput += playerData[0] + "\t" + playerData[1] + "\t" + playerData[2] + "\n";
    	}
			
		loadStoredGamesTextArea.setText(playerDataForTextOutput);
		add(loadStoredGamesScrollPane);

    	add(backToMenu);
    	
    	g.setColor(Color.black);
	    g.drawLine(1150, 50, 1400, 50);
    }
    
    private void drawPanelOption(Graphics g) {
    	add(optionLabel);
    	
    	add(optionLabelSound);
    	add(optionLabelSoundOn);
    	add(optionLabelSoundOff);
    	
    	optionRadioBtnGroupSound.add(optionRadioBtnSoundOn);
    	optionRadioBtnGroupSound.add(optionRadioBtnSoundOff);	    	
    	add(optionRadioBtnSoundOn);
    	add(optionRadioBtnSoundOff);
    	
    	add(optionLabelGrade);
    	add(optionLabelGradeEasy);
    	add(optionLabelGradeMiddle);
    	add(optionLabelGradeHeavy);
    	
    	optionRadioBtnGroupGrade.add(optionRadioBtnGradeEasy);
    	optionRadioBtnGroupGrade.add(optionRadioBtnGradeMiddle);
    	optionRadioBtnGroupGrade.add(optionRadioBtnGradeHeavy);
    	add(optionRadioBtnGradeEasy);
    	add(optionRadioBtnGradeMiddle);
    	add(optionRadioBtnGradeHeavy);
    	
    	add(optionLabelColorPlayer);
    	
    	add(optionLabelColorBlue);
    	add(optionLabelColorRed);
    	add(optionLabelColorBlack);
    	
    	optionRadioBtnGroupPlayerOne.add(optionRadioBtnPlayerOneBlue);
    	optionRadioBtnGroupPlayerOne.add(optionRadioBtnPlayerOneRed);
    	optionRadioBtnGroupPlayerOne.add(optionRadioBtnPlayerOneBlack);
    	add(optionRadioBtnPlayerOneBlue);
    	add(optionRadioBtnPlayerOneRed);
    	add(optionRadioBtnPlayerOneBlack);
    	
    	add(backToMenu);
    	
    	g.setColor(Color.black);
	    g.drawLine(1150, 50, 1400, 50);
    }
    
    private void drawPanelImpressum(Graphics g) {
    	add(impressumLabel);
    	add(impressumText);
    	
    	add(backToMenu);
    	
    	g.setColor(Color.black);
	    g.drawLine(1150, 50, 1400, 50);
    }
    
    private void setSignPosition (JButton btn) {
    	xSignPos = btn.getBounds().x-120;
    	ySignPos = btn.getBounds().y-10;
    }
    
    /////////////////////////////////////////////
    /////  ActionListener /////////////////
    ///////////////////////////////////////
    

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
    	String clickedBtn = ((JButton)e.getSource()).getText();
		switch(clickedBtn) {
    		case "Start New Game":
    			clickedPanel = 1;
    			break;
    		case "Load Game":
    			clickedPanel = 2;
    			break;
    		case "Options":
    			clickedPanel = 3;
    			break;    			
    		case "Impressum":
    			clickedPanel = 4;
    			break;
    		case "Exit":
    			clickedPanel = 5;
    			break;  
    		case "<<< Back To Menu":
    			clickedPanel = 0;
    			break;
    		case "Start Game":
    	    	if (!startGameTextPlayerOne.getText().isEmpty()) {
    	    		startNameRequired = false;
    	    		currentPlayer = startGameTextPlayerOne.getText();
    	    		currentLevel = 1;
    				currentScore = 0;
    				
    	    		for (String data : loadStoredGamesWholeData) {
    	    			String playerData[] = data.split(" ");
    	    			if(playerData[0].equals(currentPlayer)) {
    	    				startNameRequired = true;
    	    				break;
    	    			}
    	    		}

    	    		if (!startNameRequired) {
	    				gameLabelPlayer.setText(currentPlayer);
	    				gameLabelLevel.setText(currentLevel + "");
	    				gameLabelScore.setText(currentScore + "");
    						
	    				clickedPanel = 100;
    	    		}
    			} else {
    				startNameRequired = true;
    			}
    	    	break;
    		case "Load Selection":
    			boolean gameLoaded = false;
       			for(String playerData: loadStoredGamesWholeData) {
    				String playerSplittedData[] = playerData.split(" ");
    				if (loadOneOfThisGameText.getText().equals(playerSplittedData[0])) {
    					loadSelectionWarning = false;
    					currentPlayer = playerSplittedData[0];
    					currentLevel = Integer.parseInt(playerSplittedData[1]);
    					currentScore = Integer.parseInt(playerSplittedData[2]);
    					gameLabelPlayer.setText(currentPlayer);
        				gameLabelLevel.setText(currentLevel + "");
        				gameLabelScore.setText(currentScore + "");
        				
    					clickedPanel = 100;
    					gameLoaded = true;
    					break;
    				} 	
    			}
       			if (!gameLoaded) {
       				loadSelectionWarning = true;
       			}
       			break;
       			
    		case "Delete Selection":
    			int index = 0;
    			boolean elementRemoved = false;
    			
    			for(String playerData: loadStoredGamesWholeData) {
    				String playerSplittedData[] = playerData.split(" ");
    				if (loadOneOfThisGameText.getText().equals(playerSplittedData[0])) {
    					loadSelectionWarning = false;
    					loadStoredGamesWholeData.remove(index);
    					
    					String textForFile = "";
    					for(String playerDataForFile: loadStoredGamesWholeData) {
    						textForFile += playerDataForFile + "\n";
    					}
    					
    					try {
							BufferedWriter fileOut = new BufferedWriter(new FileWriter("src/Menu/Files/ScoresOnePlayer.txt"));
							fileOut.write(textForFile);
							fileOut.close();
    					} catch (IOException e1) {	
							e1.printStackTrace();
						}
    					elementRemoved = true;
    					break;
    				} 	
    				index++;
    				System.out.println("print after index increment");
    			}
    			if(!elementRemoved) {
	       			loadSelectionWarning = true;
	       			System.out.println("print after set warning true");
    			}
       			break;
       			
    		case "Continue Game":
    			clickedPanel = 100;
    			break;
    			
    		case "Save and Continue Game":
				String textForFile = "";
				
				for(String playerDataForFile: loadStoredGamesWholeData) {
					String checkPlayerDuplicate[] = playerDataForFile.split(" ");				
					if(checkPlayerDuplicate[0].equals(currentPlayer)) {	
						loadStoredGamesWholeData.remove(playerDataForFile);
						break;
					}
				}
				
				loadStoredGamesWholeData.add(currentPlayer + " " + currentLevel + " " + currentScore);
				
				for(String playerDataForFile: loadStoredGamesWholeData) {	
					textForFile += playerDataForFile + "\n";
				}
				
				try {
					BufferedWriter fileOut = new BufferedWriter(new FileWriter("src/Menu/Files/ScoresOnePlayer.txt"));
					fileOut.write(textForFile);
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    			clickedPanel = 100;
    			break;
    			
    		case "Save and Back to Menu":
    			String textForFile2 = "";
				
				for(String playerDataForFile: loadStoredGamesWholeData) {
					String checkPlayerDuplicate[] = playerDataForFile.split(" ");				
					if(checkPlayerDuplicate[0].equals(currentPlayer)) {	
						loadStoredGamesWholeData.remove(playerDataForFile);
						break;
					}
				}
				
				loadStoredGamesWholeData.add(currentPlayer + " " + currentLevel + " " + currentScore);
				
				for(String playerDataForFile: loadStoredGamesWholeData) {	
					textForFile2 += playerDataForFile + "\n";
				}
				

				try {
					BufferedWriter fileOut = new BufferedWriter(new FileWriter("src/Menu/Files/ScoresOnePlayer.txt"));
					fileOut.write(textForFile2);
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
				clickedPanel = 0;
				break;
				
    		case "Back to Menu without Saving":
    			clickedPanel = 0;
    			break;
    			
    		case "Break":
    			clickedPanel = 99;
    			break;
    	}
		removeAll();
    	revalidate();
    	repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    	String hoveredBtnStr = ((JButton)e.getSource()).getText();
    	if (clickedPanel == 0) {
			switch(hoveredBtnStr) {
	    		case "Start New Game":
	    			hoveredBtnId = 1;
	    			break;
	    		case "Load Game":
	    			hoveredBtnId = 2;
	    			break;
	    		case "Options":
	    			hoveredBtnId = 3;
	    			break;    			
	    		case "Impressum":
	    			hoveredBtnId = 4;
	    			break;
	    		case "Exit":
	    			hoveredBtnId = 5;
	    			break;    		
	    	} 
    	} else if (clickedPanel == 99) {
    		switch (hoveredBtnStr) {
		    case "Continue Game": 
		    	hoveredBtnId = 1;
		    	break;
		    case "Save and Continue Game":
		    	hoveredBtnId = 2;
		    	break;
		    case "Save and Back to Menu":
		    	hoveredBtnId = 3;
		    	break;
		    case "Back to Menu without Saving":
		    	hoveredBtnId = 4;
		    	break;
    		}
    	}
    	repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) {
		if (clickedPanel == 0) {
			switch(e.getKeyCode()) {
				case 38:	//up
					if (hoveredBtnId > 1) {
						hoveredBtnId--;
					}
					break;
				case 40:	//down
					if (hoveredBtnId < 5) {
						hoveredBtnId++;
					}
					break;
				case 10:
					clickedPanel = hoveredBtnId;
			}
		} else if (clickedPanel == 99) {		
			switch(e.getKeyCode()) {
				case 38:	//up
					if (hoveredBtnId > 1) {
						hoveredBtnId--;
					}
					break;
				case 40:	//down
					if (hoveredBtnId < 4) {
						hoveredBtnId++;
					}
					break;
				case 10:
					if (hoveredBtnId == 1 || hoveredBtnId == 2 ) {
						clickedPanel = 100;
					} else if (hoveredBtnId == 3 || hoveredBtnId == 4 ) {
						clickedPanel = 0;
					}
			}
		}
		this.removeAll();
    	revalidate();
    	repaint();	
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
