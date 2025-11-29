package application;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

// Brandon Argenal Almanza, 301467830
// Tanzeef Ahamed, 301457295

public class GamePlayerApp extends Application {

    //Details needed to connect to the Oracle database
    private static final String DB_URL  = "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD";
    private static final String DB_USER = "COMP228_F25_dil_9";
    private static final String DB_PASS = "password";

    //Items shown on the screen that the user can click or type into
    private ComboBox<PlayerModel> cbPlayerSelect = new ComboBox<>();
    private ComboBox<GameModel> cbGameSelect = new ComboBox<>();

    //Text fields where we can enter information about a player
    private TextField tfFirstName = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfPostal = new TextField();
    private TextField tfProvince = new TextField();
    private TextField tfPhone = new TextField();

    //Text field for entering a game’s title
    private TextField tfGameTitle = new TextField();

    //Fields used when recording that a player played a game
    private DatePicker dpPlayingDate = new DatePicker(LocalDate.now());
    private TextField tfScore = new TextField();

    //Table that displays all play records
    private TableView<ReportRow> tableReport = new TableView<>();
    private ObservableList<ReportRow> reportData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	System.out.println("Brandon Argenal Almanza, 301467830");
    	System.out.println("Tanzeef Ahamed, 301457295 \n");
    	
        // Try to load the database driver early so we know if something is wrong during startup
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            System.out.println("Oracle JDBC driver loaded.");
            
        } catch (ClassNotFoundException e) {
        	
            System.err.println("Oracle JDBC Driver not found. Add ojdbc jar to classpath.");
            e.printStackTrace();
        }

        //Screen layout and controls
        VBox root = new VBox(10);
        root.setPadding(new Insets(12));

        //Area where we view and edit player information
        GridPane playerGrid = new GridPane();
        playerGrid.setHgap(8);
        playerGrid.setVgap(8);
        playerGrid.add(new Label("Players (select to edit / filter):"), 0, 0);
        cbPlayerSelect.setPrefWidth(380);
        playerGrid.add(cbPlayerSelect, 0, 1, 2, 1);
        cbPlayerSelect.setPromptText("Choose player by name [id]");

        playerGrid.add(new Label("First name:"), 0, 2);
        playerGrid.add(tfFirstName, 1, 2);
        playerGrid.add(new Label("Last name:"), 0, 3);
        playerGrid.add(tfLastName, 1, 3);
        playerGrid.add(new Label("Address:"), 0, 4);
        playerGrid.add(tfAddress, 1, 4);
        playerGrid.add(new Label("Postal code:"), 0, 5);
        playerGrid.add(tfPostal, 1, 5);
        playerGrid.add(new Label("Province:"), 0, 6);
        playerGrid.add(tfProvince, 1, 6);
        playerGrid.add(new Label("Phone:"), 0, 7);
        playerGrid.add(tfPhone, 1, 7);

        Button btnInsertPlayer = new Button("Insert Player");
        Button btnUpdatePlayer = new Button("Update Player");
        HBox playerButtons = new HBox(8, btnInsertPlayer, btnUpdatePlayer);
        playerGrid.add(playerButtons, 1, 8);

        //When a player is picked from the list, fill the text boxes with their info
        cbPlayerSelect.setOnAction(e -> populatePlayerForm(cbPlayerSelect.getSelectionModel().getSelectedItem()));

        //Area where new games can be added
        GridPane gameGrid = new GridPane();
        gameGrid.setHgap(8);
        gameGrid.setVgap(8);
        gameGrid.add(new Label("Games (add new):"), 0, 0);
        gameGrid.add(new Label("Game title:"), 0, 1);
        gameGrid.add(tfGameTitle, 1, 1);
        Button btnInsertGame = new Button("Insert Game");
        gameGrid.add(btnInsertGame, 1, 2);

        //Area where we record which player played which game
        GridPane playGrid = new GridPane();
        playGrid.setHgap(8);
        playGrid.setVgap(8);
        playGrid.add(new Label("Record a play (link player + game):"), 0, 0);
        playGrid.add(new Label("Player:"), 0, 1);
        playGrid.add(cbPlayerSelect, 1, 1);
        playGrid.add(new Label("Game:"), 0, 2);
        playGrid.add(cbGameSelect, 1, 2);
        playGrid.add(new Label("Date:"), 0, 3);
        playGrid.add(dpPlayingDate, 1, 3);
        playGrid.add(new Label("Score:"), 0, 4);
        playGrid.add(tfScore, 1, 4);

        Button btnInsertPlay = new Button("Insert Play Record");
        playGrid.add(btnInsertPlay, 1, 5);

        //Grouping screen sections together
        HBox topRow = new HBox(20, playerGrid, gameGrid, playGrid);
        topRow.setPadding(new Insets(8));

        //Set up the table that shows the report of all play records
        TableColumn<ReportRow, Number> colPlayId = new TableColumn<>("PlayId");
        colPlayId.setCellValueFactory(new PropertyValueFactory<>("playerGameId"));

        TableColumn<ReportRow, Number> colPlayerId = new TableColumn<>("PlayerId");
        colPlayerId.setCellValueFactory(new PropertyValueFactory<>("playerId"));

        TableColumn<ReportRow, String> colPlayerName = new TableColumn<>("Player");
        colPlayerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<ReportRow, Number> colGameId = new TableColumn<>("GameId");
        colGameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

        TableColumn<ReportRow, String> colGameTitle = new TableColumn<>("Game Title");
        colGameTitle.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

        TableColumn<ReportRow, Date> colDate = new TableColumn<>("Date");
        colDate.setCellValueFactory(new PropertyValueFactory<>("playingDate"));

        TableColumn<ReportRow, Number> colScore = new TableColumn<>("Score");
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableReport.getColumns().addAll(colPlayId, colPlayerId, colPlayerName, colGameId, colGameTitle, colDate, colScore);
        tableReport.setItems(reportData);
        tableReport.setPrefHeight(300);

        //If user double clicks a row, highlight that player in the dropdown menu
        tableReport.setRowFactory(tv -> {
            TableRow<ReportRow> row = new TableRow<>();
            row.setOnMouseClicked(evt -> {
                if (evt.getClickCount() == 2 && !row.isEmpty()) {
                    ReportRow r = row.getItem();
                    selectPlayerById(r.getPlayerId());
                }
            });
            return row;
        });

        //Buttons for loading and refreshing the report
        Button btnLoadReport = new Button("Load Report");
        Button btnRefreshLists = new Button("Refresh Players & Games");
        HBox reportButtons = new HBox(10, btnLoadReport, btnRefreshLists);

        //Add everything to the main screen layout
        root.getChildren().addAll(new Label("COMP-228 Lab - Game Player Manager"), topRow, reportButtons, tableReport);

        //buttons that do a certain action when clicked
        btnInsertPlayer.setOnAction(e -> insertPlayer());
        btnUpdatePlayer.setOnAction(e -> updatePlayer());
        btnInsertGame.setOnAction(e -> insertGame());
        btnInsertPlay.setOnAction(e -> insertPlayerGame());
        btnLoadReport.setOnAction(e -> loadReport());
        btnRefreshLists.setOnAction(e -> {
            loadPlayers();
            loadGames();
        });

        //Load the list of games and players when on run
        loadGames();
        loadPlayers();

        //Display the window on the screen
        Stage stage = primaryStage;
        stage.setTitle("Game Player Manager (COMP-228)");
        stage.setScene(new Scene(root, 1200, 600));
        stage.show();
    }

    //METHODS that help connect to the database
    //Connects to the database and turns off auto commit so we save changes manually.
    private Connection getConnection() throws SQLException {
        try {
            System.out.println("> Start Program ...");
            //Load the driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("> Driver Loaded successfully.");

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("> Database connected successfully.");

            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Oracle JDBC Driver not found. Ensure ojdbc jar is on classpath.", e);
        }
    }

    //LOAD LISTS/DATA
     //Load all games from the database so they appear in the game dropdown.
    private void loadGames() {
        cbGameSelect.getItems().clear();
        String sql = "SELECT game_id, game_title FROM Game ORDER BY game_title";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cbGameSelect.getItems().add(new GameModel(rs.getInt("game_id"), rs.getString("game_title")));
            }
        } catch (SQLException ex) {
            showError("loadGames", ex);
        }
    }

    //Load all players so they appear in the player dropdown.
    private void loadPlayers() {
        cbPlayerSelect.getItems().clear();
        String sql = "SELECT player_id, first_name, last_name FROM Player ORDER BY first_name, last_name";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cbPlayerSelect.getItems().add(new PlayerModel(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ));
            }
        } catch (SQLException ex) {
            showError("loadPlayers", ex);
        }
    }

    /* INSERT & UPDATE OPERATIONS
    * Add a new player to the database.
    * If insert is successful, reload player list.
    */
    private void insertPlayer() {
        String first = tfFirstName.getText().trim();
        String last  = tfLastName.getText().trim();

        if (first.isEmpty() || last.isEmpty()) {
            showInfo("Please enter both first and last name for the player.");
            return;
        }

        String sql = "INSERT INTO Player (first_name, last_name, address, postal_code, provincc, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"player_id"})) {

            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, tfAddress.getText().trim());
            ps.setString(4, tfPostal.getText().trim());
            ps.setString(5, tfProvince.getText().trim());
            ps.setString(6, tfPhone.getText().trim());

            int rows = ps.executeUpdate();
            if (rows == 1) {
                conn.commit();
                showInfo("Player inserted successfully.");
                loadPlayers();
                clearPlayerForm();
            }
        } catch (SQLException ex) {
            showError("insertPlayer", ex);
        }
    }

    /*
    * Save changes made to the selected player.
    * Requires a player to be selected in the cbPlayerSelect combo box.
    */
    private void updatePlayer() {
        PlayerModel selected = cbPlayerSelect.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showInfo("Select a player to update from the player dropdown first.");
            return;
        }

        String sql = "UPDATE Player SET first_name=?, last_name=?, address=?, postal_code=?, provincc=?, phone_number=? WHERE player_id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tfFirstName.getText().trim());
            ps.setString(2, tfLastName.getText().trim());
            ps.setString(3, tfAddress.getText().trim());
            ps.setString(4, tfPostal.getText().trim());
            ps.setString(5, tfProvince.getText().trim());
            ps.setString(6, tfPhone.getText().trim());
            ps.setInt(7, selected.getPlayerId());

            int updated = ps.executeUpdate();
            if (updated == 1) {
                conn.commit();
                showInfo("Player updated successfully.");
                loadPlayers();
            } else {
                showInfo("No rows updated. Verify the selected player exists.");
            }
        } catch (SQLException ex) {
            showError("updatePlayer", ex);
        }
    }

    //Add a new game to the database.
    private void insertGame() {
        String title = tfGameTitle.getText().trim();
        if (title.isEmpty()) {
            showInfo("Enter a game title to insert.");
            return;
        }

        String sql = "INSERT INTO Game (game_title) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"game_id"})) {

            ps.setString(1, title);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                conn.commit();
                showInfo("Game inserted successfully.");
                tfGameTitle.clear();
                loadGames();
            }
        } catch (SQLException ex) {
            showError("insertGame", ex);
        }
    }

    //Record that a player played a game on a certain date with a score.
    private void insertPlayerGame() {
        PlayerModel pm = cbPlayerSelect.getSelectionModel().getSelectedItem();
        GameModel gm = cbGameSelect.getSelectionModel().getSelectedItem();

        if (pm == null || gm == null) {
            showInfo("Select both a player and a game before inserting a play record.");
            return;
        }

        //parse the score
        int score = 0;
        String sText = tfScore.getText().trim();
        if (!sText.isEmpty()) {
            try {
                score = Integer.parseInt(sText);
            } catch (NumberFormatException nfe) {
                showInfo("Score must be a numeric integer value.");
                return;
            }
        }

        java.sql.Date sqlDate = java.sql.Date.valueOf(dpPlayingDate.getValue());

        String sql = "INSERT INTO PlayerAndGame (game_id, player_id, playing_date, score) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"player_game_id"})) {

            ps.setInt(1, gm.getGameId());
            ps.setInt(2, pm.getPlayerId());
            ps.setDate(3, sqlDate);
            ps.setInt(4, score);

            int rows = ps.executeUpdate();
            if (rows == 1) {
                conn.commit();
                showInfo("Play record inserted.");
                loadReport();
            }
        } catch (SQLException ex) {
            showError("insertPlayerGame", ex);
        }
    }

    //REPORT (JOINED)
    //Load and display all play records in the table. If a player is selected, only show their records.
    private void loadReport() {
        reportData.clear();

        String baseSql = "SELECT pag.player_game_id, pag.player_id, p.first_name, p.last_name, pag.game_id, g.game_title, pag.playing_date, pag.score " +
                "FROM PlayerAndGame pag " +
                "JOIN Player p ON pag.player_id = p.player_id " +
                "JOIN Game g ON pag.game_id = g.game_id ";

        PlayerModel filter = cbPlayerSelect.getSelectionModel().getSelectedItem();
        boolean hasFilter = (filter != null);
        String sql = baseSql + (hasFilter ? " WHERE pag.player_id = ? " : "") + " ORDER BY pag.playing_date DESC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (hasFilter) ps.setInt(1, filter.getPlayerId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reportData.add(new ReportRow(
                            rs.getInt("player_game_id"),
                            rs.getInt("player_id"),
                            rs.getString("first_name") + " " + rs.getString("last_name"),
                            rs.getInt("game_id"),
                            rs.getString("game_title"),
                            rs.getDate("playing_date"),
                            rs.getInt("score")
                    ));
                }
            }
        } catch (SQLException ex) {
            showError("loadReport", ex);
        }
    }

    private void populatePlayerForm(PlayerModel p) {
        if (p == null) {
            clearPlayerForm();
            return;
        }

        String sql = "SELECT * FROM Player WHERE player_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getPlayerId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tfFirstName.setText(rs.getString("first_name"));
                    tfLastName.setText(rs.getString("last_name"));
                    tfAddress.setText(rs.getString("address"));
                    tfPostal.setText(rs.getString("postal_code"));
                    tfProvince.setText(rs.getString("provincc"));
                    tfPhone.setText(rs.getString("phone_number"));
                }
            }
        } catch (SQLException ex) {
            showError("populatePlayerForm", ex);
        }
    }

    //Method that empties all player text boxes
    private void clearPlayerForm() {
        tfFirstName.clear();
        tfLastName.clear();
        tfAddress.clear();
        tfPostal.clear();
        tfProvince.clear();
        tfPhone.clear();
    }

    //Find player by ID and highlight them in the dropdown menu
    private void selectPlayerById(int playerId) {
        for (PlayerModel pm : cbPlayerSelect.getItems()) {
            if (pm.getPlayerId() == playerId) {
                cbPlayerSelect.getSelectionModel().select(pm);
                populatePlayerForm(pm);
                return;
            }
        }
    }

    //Small info/alert pop up
    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    //Error that also prints the stack trace
    private void showError(String where, Exception ex) {
        ex.printStackTrace();
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Error: " + where);
        a.setContentText(ex.getMessage());
        a.showAndWait();
    }

    //MODELS
    // Holds information about a game so we can show it in the dropdown menu
    public static class GameModel {
        private final int gameId;
        private final String title;

        public GameModel(int gameId, String title) {
            this.gameId = gameId;
            this.title = title;
        }

        public int getGameId() { return gameId; }
        public String getTitle() { return title; }

        @Override
        public String toString() {
            return title + " [" + gameId + "]";
        }
    }

    //Holds information about a player in the dropdown list
    public static class PlayerModel {
        private final int playerId;
        private final String first;
        private final String last;

        public PlayerModel(int playerId, String first, String last) {
            this.playerId = playerId;
            this.first = first;
            this.last = last;
        }

        public int getPlayerId() { return playerId; }

        @Override
        public String toString() {
            return first + " " + last + " [" + playerId + "]";
        }
    }

    //Holds one row of information shown in the report table
    public static class ReportRow {
        private final IntegerProperty playerGameId = new SimpleIntegerProperty();
        private final IntegerProperty playerId = new SimpleIntegerProperty();
        private final StringProperty playerName = new SimpleStringProperty();
        private final IntegerProperty gameId = new SimpleIntegerProperty();
        private final StringProperty gameTitle = new SimpleStringProperty();
        private final ObjectProperty<Date> playingDate = new SimpleObjectProperty<>();
        private final IntegerProperty score = new SimpleIntegerProperty();

        public ReportRow(int pgid, int pid, String pname, int gid, String gtitle, Date date, int sc) {
            this.playerGameId.set(pgid);
            this.playerId.set(pid);
            this.playerName.set(pname);
            this.gameId.set(gid);
            this.gameTitle.set(gtitle);
            this.playingDate.set(date);
            this.score.set(sc);
        }

        public int getPlayerGameId() { return playerGameId.get(); }
        public int getPlayerId() { return playerId.get(); }
        public String getPlayerName() { return playerName.get(); }
        public int getGameId() { return gameId.get(); }
        public String getGameTitle() { return gameTitle.get(); }
        public Date getPlayingDate() { return playingDate.get(); }
        public int getScore() { return score.get(); }
    }
}