package img;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Boule extends Ball {
	private Circle2D boule2D;
	private Ball3D boule3D;
	private Board board;
	private Position pos;
	private Group game3dBox;
	private Player[] gammers;
	private Label[] labelPlayers;
	public static int remove = 0;
	public static boolean debug = true;

	public Boule(Board board, Group game3dBox, Player[] gammers, Label[] labelPlayers, int lv, int x, int y) {
		this.board = board;
		this.game3dBox = game3dBox;
		this.gammers = gammers;
		this.labelPlayers = labelPlayers;
		boule2D = new Circle2D(20.0f);
		boule3D = new Ball3D(40);
		pos = new Position(lv, x, y);
	}

	public Boule(boolean isBlack) {
		super(isBlack);

		boule2D = new Circle2D(20.0f);
		boule3D = new Ball3D(40);

		if (isBlack)
			boule3D.setBallColor("black");
		else
			boule3D.setBallColor("white");

		boule2D.getCircle().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				boule2D.setColor(Color.RED);
			}
		});
	}

	public Circle2D getBoule2D() {
		return boule2D;
	}

	public void setBoule2D(Circle2D boule2d) {
		boule2D = boule2d;

		boule2D.getCircle().setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (GameGUI.board.playableCase(pos.lv, pos.x, pos.y))
					boule2D.setColor(Color.GREEN);
				else boule2D.setColor(Color.GREY);
			}

		});
		boule2D.getCircle().setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				boule2D.setColor(Color.WHITE);
			}
		});

		boule2D.getCircle().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				// les boutons
				if (remove == 0) {
					ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
					ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

					// show close dialog

					Alert alert = new Alert(null, "Make this move?", yes, no);
					alert.setTitle("Confirmation Moves");

					if (GameGUI.board.playableCase(pos.lv, pos.x, pos.y)) {
						Optional<ButtonType> result = alert.showAndWait();

						if (result.get() == yes) {
							if (gammers[0].canPlay()) {
								if (gammers[0].placeBallOn(board, pos.lv, pos.x, pos.y, game3dBox)) {
									if (board.onTop()) {
										AnnonceGagnant.gagnant("1");
									}

									if ((board.squareFull(pos.lv, pos.x, pos.y))) {
										remove = RemoveIfPossible.remove();
									}
									if (remove == 0) {
										gammers[0].setTurn(false);
										gammers[1].setTurn(true);
										labelPlayers[1].setStyle(
												"-fx-background-color: grey; -fx-border-width: 1; -fx-border-color: black");
										labelPlayers[0].setStyle("-fx-border-width: 1; -fx-border-color: black");
									}
								}
							} else if (gammers[1].canPlay()) {
								if (gammers[1].placeBallOn(board, pos.lv, pos.x, pos.y, game3dBox)) {
									if (board.onTop()) {
										AnnonceGagnant.gagnant("2");
									}
									if ((board.squareFull(pos.lv, pos.x, pos.y))) {
										remove = RemoveIfPossible.remove();
									}
									if (remove == 0) {
										gammers[1].setTurn(false);
										gammers[0].setTurn(true);
										labelPlayers[0].setStyle(
												"-fx-background-color: grey; -fx-border-width: 1; -fx-border-color: black");
										labelPlayers[1].setStyle("-fx-border-width: 1; -fx-border-color: black");
									}
								}
							}
						}
						boule2D.setColor(Color.WHITE);
					}
				} else {
					ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
					ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

					// show close dialog

					Alert alert = new Alert(null, "Retire this ball?", yes, no);
					alert.setTitle("Confirmation Moves");
					// boule2d.setColor(Color.BLACK);
					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == yes) {
						if (gammers[0].canPlay()) {
							if (GameGUI.board.removeBall(gammers[0], pos.lv, pos.x, pos.y)) {
								remove--;
								if (remove == 0) {
									gammers[0].setTurn(false);
									gammers[1].setTurn(true);
									labelPlayers[1].setStyle(
											"-fx-background-color: grey; -fx-border-width: 1; -fx-border-color: black");
									labelPlayers[0].setStyle("-fx-border-width: 1; -fx-border-color: black");
								}
							}

						} else if (gammers[1].canPlay()) {
							if (GameGUI.board.removeBall(gammers[1], pos.lv, pos.x, pos.y)) {
								remove--;
								if (remove == 0) {
									gammers[1].setTurn(false);
									gammers[0].setTurn(true);
									labelPlayers[0].setStyle(
											"-fx-background-color: grey; -fx-border-width: 1; -fx-border-color: black");
									labelPlayers[1].setStyle("-fx-border-width: 1; -fx-border-color: black");
								}
							}

						}
					}
					boule2D.setColor(Color.WHITE);
				}
				if (debug) {
					System.out.println(GameGUI.board);
					System.out.println(GameGUI.gammers[0]);
					System.out.println(GameGUI.gammers[1]);

				}
			}

		});
	}

	public Ball3D getBoule3D() {
		return boule3D;
	}

	public void setBoule3D(Ball3D boule3d) {
		boule3D = boule3d;
	}

	public Position getPos() {
		return pos;
	}

	public void placeOnGroup3D(Group group) {
		Platform.runLater(new Place3D(boule3D, group));

	}

	public void removeOnGroup3D(Group group) {
		Platform.runLater(new Remove3D(boule3D, group));
	}

	public void place3D(int lv, int x, int y) {
		if (lv == 3)
			setTranslate3D(120 - (80 * x), 0, 120 - (80 * y));
		if (lv == 2)
			setTranslate3D(80 - (80 * x), 40, 80 - (80 * y));
		if (lv == 1)
			setTranslate3D(40 - (80 * x), 80, 40 - (80 * y));
		if (lv == 0)
			setTranslate3D(0, 120, 0);
	}

	public void setTranslate3D(int x, int y, int z) {
		boule3D.setTranslate("x", x);
		boule3D.setTranslate("y", y);
		boule3D.setTranslate("z", z);
	}

}
